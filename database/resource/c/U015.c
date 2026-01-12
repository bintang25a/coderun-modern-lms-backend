#include <stdio.h>

int main()

 {
    int i, j, n, m;
    int diagonalUtama = 0, diagonalSekunder = 0;

    // Menerima input ukuran matriks
    printf("Masukkan jumlah baris matriks: ");
    scanf("%d", &n);
    printf("Masukkan jumlah kolom matriks: ");
    scanf("%d", &m);

    // Memastikan matriks adalah persegi untuk diagonal
    if (n != m) {
        printf("Matriks harus persegi untuk menghitung diagonal.\n");
        return 1;
    }

    int matriks[n][m];

    // Menerima input elemen matriks
    printf("Masukkan elemen matriks:\n");
    for (i = 0; i < n; i++) {
        for (j = 0; j < m; j++) {
            printf("Elemen [%d][%d]: ", i, j);
            scanf("%d", &matriks[i][j]);
        }
    }

    // Menghitung jumlah diagonal utama dan sekunder
    for (i = 0; i < n; i++) {
        diagonalUtama += matriks[i][i]; // Elemen diagonal utama
        diagonalSekunder += matriks[i][n - 1 - i]; // Elemen diagonal sekunder
    }

    // Menampilkan hasil
    printf("Jumlah diagonal utama: %d\n", diagonalUtama);
    printf("Jumlah diagonal sekunder: %d\n", diagonalSekunder);

    return 0;
}
