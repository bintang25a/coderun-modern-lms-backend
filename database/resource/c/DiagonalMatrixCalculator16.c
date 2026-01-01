#include<stdio.h>
int main () {
	
	int A[3][3], B[3][3], C[3][3];
	int i, j;
	
	// input data untuk Matriks A
	printf("Masukkan elemen-elemen Matriks A (3x3):\n");
	for (i = 0; i < 3; i++) {
		for (j = 0; j < 3; j++){
			printf("A[%d][%d]: ", i + 1, j + 1);
				scanf("%d", &A[i][j]);
			
		}
		
	}


    // input data untuk Matriks B
    printf("\nMasukkan elemen-elemen Matriks B (3x3):\n");
    for (i = 0; i < 3; i++) {
    	for (j = 0; j < 3; j++) {
    		printf("B[%d][%d]: ", i + 1, j + 1);
    		scanf("%d", &B[i][j]);
		}
	}
	
	// penjumlahan Matriks A dan B untuk Matriks C
	for (i = 0; i < 3; i++) {
		for (j = 0; j < 3; j++){
		    C[i][j] = A[i][j] + B [i][j];	
		}
	}
	// hasil Matriks  C
	printf("\nHasil penjumlahan Matriks A dan Matriks B (Matriks C):\n");
	for (i = 0; i < 3; i++) {
		for (j = 0; j < 3; j++) {
			printf("%d ", C[i][j]);
			
		}
		printf("\n");
	}
	
	return 0;
}