#include <stdio.h>

int main() {
    int baris, kolom;
    printf("Masukkan jumlah baris: ");
    scanf("%d", &baris);
    printf("Masukkan jumlah kolom: ");
    scanf("%d", &kolom);

    int matriks[100][100];

    printf("Masukkan elemen matriks:\n");
    for (int i = 0; i < baris; i++) {
        for (int j = 0; j < kolom; j++) {
            printf("Matriks[%d][%d]: ", i, j);
            scanf("%d", &matriks[i][j]);
        }
    }

    int jumlahDiagonalUtama = 0;
    int jumlahDiagonalSekunder = 0;

    for (int i = 0; i < baris; i++) {
        if (i < kolom) {
            jumlahDiagonalUtama += matriks[i][i];
        }
        if (kolom - i - 1 >= 0 && kolom - i - 1 < kolom) {
            jumlahDiagonalSekunder += matriks[i][kolom - i - 1];
        }
    }

    printf("\nJumlah elemen diagonal utama: %d\n", jumlahDiagonalUtama);
    printf("Jumlah elemen diagonal sekunder: %d\n", jumlahDiagonalSekunder);

    return 0;
}

