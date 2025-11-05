#include <stdio.h>
#include <stdlib.h>

int main() {
    int rows, cols;
    printf("Masukkan jumlah baris: ");
    scanf("%d", &rows);
    printf("Masukkan jumlah kolom: ");
    scanf("%d", &cols);

    int **matrix = (int **)malloc(rows * sizeof(int *));
    for (int i = 0; i < rows; i++) {
        matrix[i] = (int *)malloc(cols * sizeof(int));
    }

    printf("Masukkan elemen matriks:\n");
    for (int i = 0; i < rows; i++) {
        for (int j = 0; j < cols; j++) {
            printf("Elemen [%d][%d]: ", i, j);
            scanf("%d", &matrix[i][j]);
        }
    }

    int sum_main_diag = 0;
    int sum_sec_diag = 0;

    for (int i = 0; i < rows; i++) {
        if (i < cols) {
            sum_main_diag += matrix[i][i];
        }
        int sec_diag_col = cols - 1 - i;
        if (sec_diag_col >= 0 && sec_diag_col < cols) {
            sum_sec_diag += matrix[i][sec_diag_col];
        }
    }

    printf("Jumlah elemen diagonal utama: %d\n", sum_main_diag);
    printf("Jumlah elemen diagonal sekunder: %d\n", sum_sec_diag);

    for (int i = 0; i < rows; i++) {
        free(matrix[i]);
    }
    free(matrix);

    return 0;
}
