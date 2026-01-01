#include <stdio.h>

int main() {
    int baris, kolom;
    int i, j;
    printf("Masukkan jumlah baris matriks: ");
    scanf("%d", &baris);
    printf("Masukkan jumlah kolom matriks: ");
    scanf("%d", &kolom);

    if (baris <= 0 || kolom <= 0) {
        printf("Jumlah baris dan kolom harus lebih dari 0.\n");
        return 1;
    }

    int matriks[baris][kolom]; 
    int jumlahDiagonalUtama = 0;
    int jumlahDiagonalSekunder = 0;

    printf("\nMasukkan elemen-elemen matriks:\n");
    for (i = 0; i < baris; i++) {
        for (j = 0; j < kolom; j++) {
            printf("Elemen matriks[%d][%d]: ", i, j);
            scanf("%d", &matriks[i][j]);
        }
    }

    printf("\nMatriks yang Anda masukkan:\n");
    for (i = 0; i < baris; i++) {
        for (j = 0; j < kolom; j++) {
            printf("%d\t", matriks[i][j]);
        }
        printf("\n");
    }

    for (i = 0; i < baris && i < kolom; i++) {
        jumlahDiagonalUtama += matriks[i][i];
    }

   
    for (i = 0; i < baris; i++) {
        int j_sekunder = (kolom - 1) - i;
        if (j_sekunder >= 0 && j_sekunder < kolom) {
            jumlahDiagonalSekunder += matriks[i][j_sekunder];
        }
    }

    printf("\n--- Hasil Perhitungan ---\n");
    printf("Jumlah elemen pada diagonal utama: %d\n", jumlahDiagonalUtama);
    printf("Jumlah elemen pada diagonal sekunder: %d\n", jumlahDiagonalSekunder);
    if (baris != kolom && jumlahDiagonalSekunder != 0) {
         printf("(Diagonal sekunder dihitung untuk elemen (i,j) dengan i+j = %d)\n", kolom -1);
    } else if (baris != kolom && jumlahDiagonalSekunder == 0) {
        int adaElemenValidSekunder = 0;
        for (i = 0; i < baris; i++) {
            int j_sekunder = (kolom - 1) - i;
            if (j_sekunder >= 0 && j_sekunder < kolom) {
                adaElemenValidSekunder = 1;
                break;
            }
        }
        if (!adaElemenValidSekunder) {
             printf("(Tidak ada elemen yang membentuk jalur diagonal sekunder i+j = %d pada matriks ini)\n", kolom-1);
        } else {
             printf("(Diagonal sekunder dihitung untuk elemen (i,j) dengan i+j = %d)\n", kolom -1);
        }
    }

    return 0;
}