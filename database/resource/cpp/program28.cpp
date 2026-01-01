#include <stdio.h>

#define SIZE 3 // Ukuran matriks 3x3

int main() {
    int A[SIZE][SIZE], B[SIZE][SIZE], C[SIZE][SIZE];
    int i, j;

    // Memasukkan elemen matriks A
    printf("Masukkan elemen matriks A (3x3):\n");
    for (i = 0; i < SIZE; i++) {
        for (j = 0; j < SIZE; j++) {
            printf("A[%d][%d]: ", i, j);
            scanf("%d", &A[i][j]);
        }
    }

    // Memasukkan elemen matriks B
    printf("\nMasukkan elemen matriks B (3x3):\n");
    for (i = 0; i < SIZE; i++) {
        for (j = 0; j < SIZE; j++) {
            printf("B[%d][%d]: ", i, j);
            scanf("%d", &B[i][j]);
        }
    }

    // Menghitung penjumlahan matriks A dan B, hasil disimpan di C
    for (i = 0; i < SIZE; i++) {
        for (j = 0; j < SIZE; j++) {
            C[i][j] = A[i][j] + B[i][j];
        }
    }

    // Menampilkan hasil penjumlahan matriks C
    printf("\nHasil penjumlahan matriks A dan B (C = A + B):\n");
    for (i = 0; i < SIZE; i++) {
        for (j = 0; j < SIZE; j++) {
            printf("%d ", C[i][j]);
        }
        printf("\n"); // Pindah ke baris berikutnya
    }

    
}
