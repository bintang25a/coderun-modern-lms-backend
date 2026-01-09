/**
 *
 * This is assignment point a. There are three aspects to do; this code behaviour ia dynamic, 
 * so all inputs come from user directly. The user can input two different values,for rows 
 * and columns dynamically. Then the code will process two summation operations; first is to 
 * determine total value of main Diagonal, second for secondary diagonal. Lastly from two 
 * opeartions earlier, we get the result, then sum the two results. 
 *
 */

#include <stdio.h> 

int main() {
    printf("Matrix\n");

    int baris, kolom; 

    printf("Baris: ");
    scanf("%d", &baris);
    printf("Kolom: ");
    scanf("%d", &kolom);

    int matriks[baris][kolom];

    for (int i = 0; i < baris; i++) {
        for (int j = 0; j < kolom; j++) {
            printf("Masukkan angka [%d][%d]: ", i, j); 
            scanf("%d", &matriks[i][j]); 
        }
    }

    printf("\nMatriks yang dimasukkan:\n");
    for (int i = 0; i < baris; i++) {
        for (int j = 0; j < kolom; j++) {
            /**
             * Link: https://stackoverflow.com/a/25609500/20202249
             * */
            printf("%4d ", matriks[i][j]); 
        }
        printf("\n"); 
    }

    int jumlahDiagonalUtama = 0;
    int jumlahDiagonalSekunder = 0;

    /**
     * Which has the same match row and column index.
     */
    for (int i = 0; i < baris; i++) {
        if (i < kolom) {
            jumlahDiagonalUtama += matriks[i][i];
        }
    }

    // Diagonal one.
    for (int i = 0; i < baris; i++) {
        for (int j = 0; j < kolom; j++) {
            if (i + j == (kolom - 1)) { 
                jumlahDiagonalSekunder += matriks[i][j];
            }
        }
    }

    printf("\nJumlah angka pada diagonal utama: %d\n", jumlahDiagonalUtama);
    printf("Jumlah angka pada diagonal sekunder: %d\n", jumlahDiagonalSekunder);
    printf("Jumlah semua diagonal: %d\n", jumlahDiagonalUtama + jumlahDiagonalSekunder);

    return 0; 
}
