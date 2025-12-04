#include <stdio.h>
//Apridho_Fuadil_Hadid[24040700011]
//Tugas1SDA_Diagonal_Matriks
int main() {
    int baris, kolom;
	printf("===============Program Menjumlahkan Diagonal Matriks===============\n\n");
    printf("Masukkan jumlah baris matriks: "); scanf("%d", &baris);
    printf("Masukkan jumlah kolom matriks: "); scanf("%d", &kolom);

    int matriks[baris][kolom], i, j;
	int diagonal_utama = 0, diagonal_sekunder = 0;

    printf("\nMasukkan elemen matriks:\n");
    for (i = 0; i < baris; i++) {
        for (j = 0; j < kolom; j++) {
            printf("Elemen [%d][%d]: ", i, j); scanf("%d", &matriks[i][j]);
        }
    }

    printf("\nMatriks yang dimasukkan:\n");
    for (i = 0; i < baris; i++) {
        for (j = 0; j < kolom; j++) {
            printf("%d\t", matriks[i][j]);
        }
        printf("\n");
    }

    int min_elemen;
		if (baris < kolom) {
    		min_elemen = baris;
		}else {
    		min_elemen = kolom;
	}
    for (i = 0; i < min_elemen; i++) {
        diagonal_utama += matriks[i][i];
    }

    for (i = 0; i < baris && i < kolom; i++) {
        diagonal_sekunder += matriks[i][kolom - 1 - i];
    }

  
    printf("\nJumlah diagonal utama: %d\n", diagonal_utama);
    printf("Jumlah diagonal sekunder: %d\n", diagonal_sekunder);
 	printf("Total kedua diagonal: %d\n",diagonal_utama + diagonal_sekunder);

    return 0;
}
