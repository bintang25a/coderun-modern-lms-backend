# ===============================
# IMPORT
# ===============================

import numpy as np
import pandas as pd
import json

from sklearn.pipeline import Pipeline
from sklearn.neighbors import KNeighborsRegressor
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

# ==============================
# CONFIG
# ==============================

SCORE_PASS_TH = 60
N_COMPONENTS  = 30
N_NEIGHBORS   = 5

# METRIC = "euclidean" # Leave-one-out
METRIC = "cosine" # Train-test-split

COMMA = 4

# ==============================
# OUTPUT CONTAINER
# ==============================

# Train-test-split
all_pred_scores = np.full(len(df), np.nan)
all_distances   = np.full(len(df), np.nan)

pipelines = {}
max_dists = {}
fallback_scores = {}

for sc in ["high", "medium", "low"]:

    idx = scale == sc
    if idx.sum() < N_NEIGHBORS:
        print(f"Skip scale: {sc.upper()} (data terlalu sedikit)")
        continue

    print(f"\nProcessing scale: {sc.upper()}")

    X_sc = X[idx].reset_index(drop=True)
    y_sc = y[idx].reset_index(drop=True)
    idx_global = np.where(idx)[0]

    # split data
    X_train, X_test, y_train, y_test, idx_tr, idx_te = train_test_split(
        X_sc,
        y_sc,
        idx_global,
        test_size=0.2,
        random_state=42,
        shuffle=True
    )

    # auto pca dimension
    n_pca = min(20, X_train.shape[0] - 1, X_train.shape[1])
    if n_pca < 2:
        print("Skip (PCA dim terlalu kecil)")
        continue

    pipeline = Pipeline([
        ("scaler", StandardScaler()),
        ("pca", PCA(n_components=n_pca, random_state=42)),
        ("knn", KNeighborsRegressor(
            n_neighbors=min(N_NEIGHBORS, len(X_train) - 1),
            metric=METRIC,
            weights="distance"
        ))
    ])

    # train
    pipeline.fit(X_train, y_train)

    # train dist (threshold)
    Xtr_t = pipeline.named_steps["pca"].transform(
        pipeline.named_steps["scaler"].transform(X_train)
    )

    train_dist, _ = pipeline.named_steps["knn"].kneighbors(
        Xtr_t, n_neighbors=2
    )

    # ambil tetangga ke-1 (bukan diri sendiri)
    MAX_DIST = np.percentile(train_dist[:, 1], 90)
    fallback = y_train.mean()

    print(f"[{sc.upper()}] PCA={n_pca}, max_dist={MAX_DIST:.4f}")

    # simpan ke dalam variabel global
    pipelines[sc] = pipeline
    max_dists[sc] = MAX_DIST
    fallback_scores[sc] = fallback

    # predict train
    train_preds = pipeline.predict(X_train)
    all_pred_scores[idx_tr] = train_preds
    all_distances[idx_tr]   = train_dist[:, 1]

    # predict test
    Xte_t = pipeline.named_steps["pca"].transform(
        pipeline.named_steps["scaler"].transform(X_test)
    )

    distances, _ = pipeline.named_steps["knn"].kneighbors(
        Xte_t, n_neighbors=1
    )

    raw_preds = pipeline.predict(X_test)

    final_preds = np.where(
        distances[:, 0] > MAX_DIST,
        fallback,
        raw_preds
    )

    all_pred_scores[idx_te] = final_preds
    all_distances[idx_te]   = distances[:, 0]

# =========================
# EVALUATION
# =========================
mask = ~np.isnan(all_pred_scores)

y_eval = y[mask]
y_pred = all_pred_scores[mask]

# ==============================
# PASS / FAIL CLASSIFICATION
# ==============================

y_true_cls = np.where(y >= SCORE_PASS_TH, "Pass", "Fail")
y_pred_cls = np.where(all_pred_scores >= SCORE_PASS_TH, "Pass", "Fail")

# ==============================
# FINAL OUTPUT
# ==============================

output = pd.DataFrame({
    "row_id": row_id,
    "true_score": y,
    "pred_score": all_pred_scores,
    "distance": all_distances,
    "true_status": y_true_cls,
    "pred_status": y_pred_cls
})

# batasi desimal
output["true_score"] = output["true_score"].round(COMMA)
output["pred_score"] = output["pred_score"].round(COMMA)
output["distance"]   = output["distance"].round(COMMA)

"""**IMLEMENTASI**"""

# ==============================
# LOAD DATA IMPLEMENTASI
# ==============================

test_file = input("")

df_test = pd.read_csv(
    test_file,
    sep=None,
    engine="python",
)

# ==============================
# SPLIT KOLOM UTAMA & FITUR
# ==============================

row_id = df_test.iloc[:, 0]
scale  = df_test.iloc[:, 2]

# fitur AST
X_test = df_test.iloc[:, 3:]
X_test = X_test.fillna(0)

# ==============================
# SIAPKAN OUTPUT CONTAINER
# ==============================

all_pred_scores = np.zeros(len(df_test))
all_distances   = np.zeros(len(df_test))

# ==============================
# SCORING PER SCALE (IMPLEMENTASI INTI)
# ==============================

all_pred_scores = np.zeros(len(df_test))
all_distances   = np.zeros(len(df_test))

for sc in ["high", "medium", "low"]:

    if sc not in pipelines:
        print(f"Skip scoring scale: {sc.upper()} (model tidak tersedia)")
        continue

    idx = scale == sc
    if idx.sum() == 0:
        continue

    print(f"Scoring scale: {sc.upper()}")

    X_sc = X_test[idx]

    pipeline = pipelines[sc]
    MAX_DIST = max_dists[sc]
    fallback = fallback_scores[sc]

    X_t = pipeline.named_steps["pca"].transform(
        pipeline.named_steps["scaler"].transform(X_sc)
    )

    distances, _ = pipeline.named_steps["knn"].kneighbors(X_t)
    raw_preds = pipeline.named_steps["knn"].predict(X_t)

    final_preds = np.where(
        distances[:, 0] > MAX_DIST,
        fallback,
        raw_preds
    )

    all_pred_scores[idx] = final_preds
    all_distances[idx]   = distances[:, 0]

status = np.where(all_pred_scores >= SCORE_PASS_TH, "Pass", "Fail")

output = pd.DataFrame({
    "submission_number": row_id,
    "score": np.round(all_pred_scores, 2),
    "status": status
})

output_json_path = input("")

with open(output_json_path, "w") as f:
    json.dump(output.to_dict(orient="records"), f, indent=2)

print("JSON saved to:", output_json_path)