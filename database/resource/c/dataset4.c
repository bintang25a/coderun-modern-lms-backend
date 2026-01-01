#include <stdio.h>

int main() {
    int data[8]; // Array untuk menyimpan 8 angka
    int jumlah = 0; // Variabel untuk menyimpan jumlah total
    float rata_rata; // Variabel untuk menyimpan hasil rata-rata

    // Input data sebanyak 8
    printf("Masukkan 8 angka:\n");
    for (int i = 0; i < 8; i++) {
        printf("Data ke-%d: ", i + 1);
        scanf("%d", &data[i]); // Input setiap elemen array
        jumlah += data[i]; // Tambahkan elemen ke jumlah total
    }

    // Hitung rata-rata
    rata_rata = jumlah / 8.0;

    // Output hasil rata-rata
    printf("Hasil rata-rata: %.2f\n", rata_rata);
printf("============≈≈==≈===================\n");
    int A[3][3], B[3][3], C[3][3]; // Matriks A, B, dan C

    // Input elemen matriks A
    printf("Masukkan elemen matriks A (3x3):\n");
    for (int i = 0; i < 3; i++) {
        for (int j = 0; j < 3; j++) {
            printf("A[%d][%d]: ", i + 1, j + 1);
            scanf("%d", &A[i][j]);
        }
    }

    // Input elemen matriks B
    printf("\nMasukkan elemen matriks B (3x3):\n");
    for (int i = 0; i < 3; i++) {
        for (int j = 0; j < 3; j++) {
            printf("B[%d][%d]: ", i + 1, j + 1);
            scanf("%d", &B[i][j]);
        }
    }

    // Penjumlahan matriks A dan B untuk menghasilkan matriks C
    for (int i = 0; i < 3; i++) {
        for (int j = 0; j < 3; j++) {
            C[i][j] = A[i][j] + B[i][j];
        }
    }

    // Output matriks hasil (C)
    printf("\nHasil penjumlahan matriks A dan B (Matriks C):\n");
    for (int i = 0; i < 3; i++) {
        for (int j = 0; j < 3; j++) {
            printf("%d\t", C[i][j]);
        }
        printf("\n");
    }
printf("=========================≈===============\n");
    int matriks[3][3] = { // Deklarasi matriks 3x3 dengan isi statis
        {1, 0, 0},
        {0, 1, 0},
        {0, 0, 1}
    };

    // Menampilkan matriks
    printf("Matriks 3x3:\n");
    for (int i = 0; i < 3; i++) {
        for (int j = 0; j < 3; j++) {
            printf("%d\t", matriks[i][j]); // Menampilkan elemen matriks
        }
        printf("\n"); // Baris baru setelah setiap baris matriks
    }

    return 0;
}
