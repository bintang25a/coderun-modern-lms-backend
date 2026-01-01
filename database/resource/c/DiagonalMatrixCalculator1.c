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
            printf("Elemen [%d][%d]: ", i, j);
            scanf("%d", &matriks[i][j]);
        }
    }

    int jumlah_diagonal_utama = 0;
    int jumlah_diagonal_sekunder = 0;

    int min_dimensi = baris < kolom ? baris : kolom;

    for (int i = 0; i < min_dimensi; i++) {
        jumlah_diagonal_utama += matriks[i][i];
        jumlah_diagonal_sekunder += matriks[i][kolom - 1 - i];
    }

    int total_jumlah_diagonal = jumlah_diagonal_utama + jumlah_diagonal_sekunder;

    printf("Jumlah diagonal utama: %d\n", jumlah_diagonal_utama);
    printf("Jumlah diagonal sekunder: %d\n", jumlah_diagonal_sekunder);
    printf("Jumlah kedua diagonal: %d\n", total_jumlah_diagonal); // Tambahan baris ini

    return 0;
}