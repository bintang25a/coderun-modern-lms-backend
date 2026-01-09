#include <stdio.h>

int main() {
    int baris, kolom;
    printf("Masukkan jumlah baris: ");
    scanf("%d", &baris);
    printf("Masukkan jumlah kolom: ");
    scanf("%d", &kolom);

    int matriks[100][100];

    printf("Masukkan elemen matriks:\n");
    for(int i = 0; i < baris; i++) {
        for(int j = 0; j < kolom; j++) {
            printf("Elemen [%d][%d]: ", i, j);
            scanf("%d", &matriks[i][j]);
        }
    }

    int sumDiagonalUtama = 0, sumDiagonalSekunder = 0;
    for(int i = 0; i < baris && i < kolom; i++) {
        sumDiagonalUtama += matriks[i][i];
        sumDiagonalSekunder += matriks[i][kolom - i - 1];
    }

    printf("Jumlah elemen diagonal utama: %d\n", sumDiagonalUtama);
    printf("Jumlah elemen diagonal sekunder: %d\n", sumDiagonalSekunder);

    return 0;
}
