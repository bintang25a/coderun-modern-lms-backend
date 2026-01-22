# ===============================
# IMPORT
# ===============================

import numpy as np
import pandas as pd
import json

from sklearn.pipeline import Pipeline
from sklearn.neighbors import NearestNeighbors
from sklearn.preprocessing import StandardScaler
from sklearn.decomposition import PCA
from sklearn.model_selection import train_test_split

# ==============================
# LOAD DATA
# ==============================

file_name = input("")

df = pd.read_csv(file_name, sep=None, engine="python")

row_id = df.iloc[:, 0]
y      = df.iloc[:, 1].astype(float)     # score
scale  = df.iloc[:, 2].astype(str)       # high / medium / low
X      = df.iloc[:, 3:].astype(float)    # AST features

print("Row:", len(df))
print("Col:", len(df.columns))

# ==============================
# CONFIG
# ==============================

SCORE_PASS_TH = 60
N_COMPONENTS  = 30
N_NEIGHBORS   = 5

METRIC = "euclidean"

COMMA = 4

# ==============================
# OUTPUT CONTAINER
# ==============================

all_pred_scores = np.full(len(df), np.nan)
all_distances   = np.full(len(df), np.nan)

pipelines = {}
max_dists = {}
fallback_scores = {}

# split data
X_train, X_test, y_train, y_test, idx_tr, idx_te = train_test_split(
    X,
    y,
    np.arange(len(X)),
    test_size=0.2,
    random_state=42,
    shuffle=True
)

scale_train = scale.iloc[idx_tr].reset_index(drop=True)

# auto PCA dim
n_pca = min(20, X_train.shape[0] - 1, X_train.shape[1])

pipeline = Pipeline([
    ("scaler", StandardScaler()),
    ("pca", PCA(n_components=n_pca, random_state=42)),
    ("knn", NearestNeighbors(metric=METRIC))
])

pipeline.fit(X_train)

pipelines["global"] = pipeline
fallback = y_train.mean()
fallback_scores["global"] = fallback

print(f"[GLOBAL] PCA={n_pca}")

Xtr_t = pipeline.named_steps["pca"].transform(
    pipeline.named_steps["scaler"].transform(X_train)
)

Xte_t = pipeline.named_steps["pca"].transform(
    pipeline.named_steps["scaler"].transform(X_test)
)

def two_stage_knn_predict(
    x_test_vec,
    target_scale,
    Xtr_t,
    scale_train,
    y_train,
    k,
    metric,
    fallback
):
    # ======================
    # STAGE 1: SAME SCALE
    # ======================
    mask = scale_train == target_scale
    idx_same = np.where(mask.values)[0]

    if len(idx_same) > 0:
        nn_same = NearestNeighbors(metric=metric)
        nn_same.fit(Xtr_t[idx_same])

        k1 = min(k, len(idx_same))
        d1, i1 = nn_same.kneighbors(x_test_vec, n_neighbors=k1)

        if k1 == k:
            train_idx = idx_same[i1[0]]
            vals = y_train.iloc[train_idx].values

            weights = 1 / (d1[0] + 1e-8)
            pred = np.sum(weights * vals) / np.sum(weights)

            return pred, d1[0][0]

    # ======================
    # STAGE 2: GLOBAL
    # ======================
    nn_global = NearestNeighbors(metric=metric)
    nn_global.fit(Xtr_t)

    d2, i2 = nn_global.kneighbors(x_test_vec, n_neighbors=k)

    vals = y_train.iloc[i2[0]].values
    weights = 1 / (d2[0] + 1e-8)
    pred = np.sum(weights * vals) / np.sum(weights)

    return pred, d2[0][0]

nn_train = NearestNeighbors(metric=METRIC)
nn_train.fit(Xtr_t)

d_tr, i_tr = nn_train.kneighbors(Xtr_t, n_neighbors=2)

train_preds = y_train.values
all_pred_scores[idx_tr] = train_preds
all_distances[idx_tr]   = d_tr[:, 1]

max_dists["global"] = np.percentile(d_tr[:, 1], 90)

for i, global_idx in enumerate(idx_te):
    target_scale = scale.iloc[global_idx]

    pred, dist = two_stage_knn_predict(
        Xte_t[i:i+1],
        target_scale,
        Xtr_t,
        scale_train,
        y_train,
        N_NEIGHBORS,
        METRIC,
        fallback
    )

    all_pred_scores[global_idx] = pred
    all_distances[global_idx]   = dist

mask = ~np.isnan(all_pred_scores)

y_eval = y[mask]
y_pred = all_pred_scores[mask]

# ==============================
# LOAD DATA IMPLEMENTASI
# ==============================

test_file = input("")

df_test = pd.read_csv(
    test_file,
    sep=None,
    engine="python",
)

print("Row:", len(df_test))
print("Col:", len(df_test.columns))

# ==============================
# SPLIT KOLOM UTAMA & FITUR
# ==============================

row_id = df_test.iloc[:, 0]
scale_test = df_test.iloc[:, 2]

X_test = df_test.iloc[:, 3:].fillna(0)

pipeline = pipelines["global"]

Xte_t = pipeline.named_steps["pca"].transform(
    pipeline.named_steps["scaler"].transform(X_test)
)

# ==============================
# SIAPKAN OUTPUT CONTAINER
# ==============================

all_pred_scores = pd.Series(np.nan, index=df_test.index)
all_distances   = pd.Series(np.nan, index=df_test.index)

for i in range(len(df_test)):
    target_scale = scale_test.iloc[i]

    pred, dist = two_stage_knn_predict(
        Xte_t[i:i+1],
        target_scale,
        Xtr_t,          # dari training
        scale_train,    # dari training
        y_train,        # dari training
        N_NEIGHBORS,
        METRIC,
        fallback_scores["global"]
    )

    all_pred_scores.iloc[i] = pred
    all_distances.iloc[i]   = dist

output = pd.DataFrame({
    "submission_number": row_id,
    "score": np.round(all_pred_scores.values, 2),
})

print(output)

output_json_path = input("")

with open(output_json_path, "w") as f:
    json.dump(output.to_dict(orient="records"), f, indent=2)

print("JSON saved to:", output_json_path)