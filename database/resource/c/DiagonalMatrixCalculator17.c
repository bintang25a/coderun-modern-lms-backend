#include <stdio.h>
#include <stdlib.h>

int main(){
	// mendeklarasikan variabel untuk ukuran matriks
	int i, j;
	int n = 3; // ukuran matriks 3×3
	
	// alokasi memori dinamis untuk matriks A, B, dan C
	int **A = (int **)malloc(n * sizeof(int*));
	int **B = (int **)malloc(n * sizeof(int*));
	int **C = (int **)malloc(n * sizeof(int*));
	
	// memeriksa apakah alokasi memori berhasil
	if (A == NULL || B == NULL || C == NULL)
	{
		printf("alokasi memori gagal!\n");
		return 1;
	}
	// alokasi memori untuk setiap baris matriks
	for (i = 0; i < n; i++) {
		A[i] = (int *)malloc(n * sizeof(int));
		B[i] = (int *)malloc(n * sizeof(int));
		C[i] = (int *)malloc(n * sizeof(int));
		
		//memeriksa apakah alokasi memori berhasil untuk setiap baris
		if (A[i] == NULL || B[i] == NULL || C[i] == NULL) {
			printf("alokasi memori gagal!\n");
			return 1;
		}	
		}
		// input matriks A
		printf("masukan elemen-elemen matriks A(3*3):\n");
		for (i = 0; i < n; i++) {
			for (j = 0; j < n; j++) {
				printf("A[%d][%d]: ", i + 1, j + 1);
				scanf("%d", &A[i][j]);
			}
		}
		
		// input matriks B
		printf("masukan elemen-elemen matriks B(3*3):\n");
		for (i = 0; i < n; i++) {
			for (j = 0; j < n; j++) {
				printf("B[%d][%d]: ", i + 1, j + 1);
				scanf("%d", &B[i][j]);
			}
		}
		
		// menjumlahkan matriks A dan B, hasil disimpan di matriks C
		for (i = 0; i < n; i++) {
			for (j = 0; j < n; j++) {
				C[i][j] = A[i][j] + B[i][j];
			}
		}
		// menampilkan hasil penjumlahan matriks A dan B (matriks C)
		printf("\nHasil penjumlahan matriks A dan B (matriks C):\n");
		for (i = 0; i < n; i++) {
			for (j = 0; j < n; j++) {
				printf("%d", C[i][j]);
			}
			printf("\n");
		}
		
		// membebaskan memori yang telah dialokasikan
		for (i = 0; i < n; i++) {
			free(A[i]);
			free(B[i]);
			free(C[i]);	
		}
		free(A);
		free(B);
		free(C);
		
		return 0;		
	}
	
	
	

