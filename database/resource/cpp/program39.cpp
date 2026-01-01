#include <stdio.h>

#define SIZE 3 // Ukuran matriks 3x3

void inputMatrix(int matrix[SIZE][SIZE], char name) {
    printf("Masukkan elemen-elemen matriks %c:\n", name);
    for (int i = 0; i < SIZE; i++) {
        for (int j = 0; j < SIZE; j++) {
            printf("%c[%d][%d] = ", name, i + 1, j + 1);
            scanf("%d", &matrix[i][j]);
        }
    }
}

void addMatrices(int matrixA[SIZE][SIZE], int matrixB[SIZE][SIZE], int matrixC[SIZE][SIZE]) {
    for (int i = 0; i < SIZE; i++) {
        for (int j = 0; j < SIZE; j++) {
            matrixC[i][j] = matrixA[i][j] + matrixB[i][j];
        }
    }
}

void printMatrix(int matrix[SIZE][SIZE], char name) {
    printf("Matriks %c:\n", name);
    for (int i = 0; i < SIZE; i++) {
        for (int j = 0; j < SIZE; j++) {
            printf("%d\t", matrix[i][j]);
        }
        printf("\n");
    }
}

int main() {
    int matrixA[SIZE][SIZE], matrixB[SIZE][SIZE], matrixC[SIZE][SIZE];

    // Input elemen matriks A dan B
    inputMatrix(matrixA, 'A');
    inputMatrix(matrixB, 'B');

    // Penjumlahan matriks
    addMatrices(matrixA, matrixB, matrixC);

    // Output hasil penjumlahan
    printf("\nHasil penjumlahan matriks (Matriks C):\n");
    printMatrix(matrixC, 'C');
}