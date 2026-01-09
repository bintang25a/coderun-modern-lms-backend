#include <stdio.h>

int main() {
    int A[3][3] = { {0, 0, 0}, {0, 0, 0}, {0, 0, 0} };
    int B[3][3] = { {0, 0, 0}, {0, 0, 0}, {0, 0, 0} };
    int C[3][3];
    int i, j;

    // Penjumlahan Matriks A dan B, hasil disimpan di Matriks C
    for (i = 0; i < 3; i++) {
        for (j = 0; j < 3; j++) {
            C[i][j] = A[i][j] + B[i][j];
        }
    }

    // Menampilkan Matriks A
    printf("Matriks A:\n");
    for (i = 0; i < 3; i++) {
        for (j = 0; j < 3; j++) {
            printf("%d\t", A[i][j]);
        }
        printf("\n");
    }

    // Menampilkan Matriks B
    printf("\nMatriks B:\n");
    for (i = 0; i < 3; i++) {
        for (j = 0; j < 3; j++) {
            printf("%d\t", B[i][j]);
        }
        printf("\n");
    }

    // Menampilkan Matriks C (hasil penjumlahan)
    printf("\nHasil penjumlahan Matriks A dan B (Matriks C):\n");
    for (i = 0; i < 3; i++) {
        for (j = 0; j < 3; j++) {
            printf("%d\t", C[i][j]);
        }
        printf("\n");
    }

    return 0;
}