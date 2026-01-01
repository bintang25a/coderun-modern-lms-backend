import json
import numpy as np
import pandas as pd
from pathlib import Path

from sklearn.preprocessing import StandardScaler
from sklearn.model_selection import train_test_split
from sklearn.neighbors import KNeighborsRegressor
from sklearn.metrics import mean_squared_error, r2_score, confusion_matrix

# ==============================
# KONFIGURASI
# ==============================
DATASET_PATH = Path("data/DATASET_CS25C3-01.csv")
ANSWER_PATH  = Path("data/ANSWER_CS25C3-01.csv")
OUTPUT_PATH  = Path("output/prediction_results.json")

DELIMITER = r"\s+"   # pemisah spasi
K_VALUE   = 7
PASS_THRESHOLD = 50

# ==============================
# LOAD DATA
# ==============================
df_train = pd.read_csv(
    DATASET_PATH,
    sep=DELIMITER,
    header=None,
    engine="python"
)

df_test = pd.read_csv(
    ANSWER_PATH,
    sep=DELIMITER,
    header=None,
    engine="python"
)

# ==============================
# SPLIT FITUR & TARGET
# ==============================
submission_numbers = df_test.iloc[:, 0].astype(str)

y = df_train.iloc[:, 1]      # target (score)
X = df_train.iloc[:, 2:]     # fitur

X_test_features = df_test.iloc[:, 2:]

# ==============================
# NORMALISASI
# ==============================
scaler = StandardScaler()
X_scaled = scaler.fit_transform(X)
X_test_scaled = scaler.transform(X_test_features)

# ==============================
# TRAIN / TEST SPLIT
# ==============================
X_train, X_val, y_train, y_val = train_test_split(
    X_scaled, y, test_size=0.2, random_state=42
)

k = min(K_VALUE, len(X_train))

knn = KNeighborsRegressor(
    n_neighbors=k,
    weights="distance"
)

knn.fit(X_train, y_train)

# ==============================
# EVALUASI MODEL
# ==============================
y_pred_val = knn.predict(X_val)

print("=== Evaluasi Model ===")
print("MSE :", mean_squared_error(y_val, y_pred_val))
print("R²  :", r2_score(y_val, y_pred_val))

# Confusion Matrix (berdasarkan kelulusan)
y_val_bin  = (y_val >= PASS_THRESHOLD).astype(int)
y_pred_bin = (y_pred_val >= PASS_THRESHOLD).astype(int)

tn, fp, fn, tp = confusion_matrix(y_val_bin, y_pred_bin).ravel()

print("\nConfusion Matrix:")
print("TP:", tp, "FP:", fp, "TN:", tn, "FN:", fn)

# ==============================
# PREDIKSI JAWABAN MAHASISWA
# ==============================
predicted_scores = knn.predict(X_test_scaled)
predicted_scores = np.clip(predicted_scores, 0, 100)

# ==============================
# SIMPAN HASIL KE JSON
# ==============================
results = []

for sub, score in zip(submission_numbers, predicted_scores):
    results.append({
        "submission_number": sub,
        "score": round(float(score), 2)
    })

OUTPUT_PATH.parent.mkdir(exist_ok=True)

with open(OUTPUT_PATH, "w") as f:
    json.dump(results, f, indent=2)

print(f"\nHasil prediksi disimpan ke: {OUTPUT_PATH}")
