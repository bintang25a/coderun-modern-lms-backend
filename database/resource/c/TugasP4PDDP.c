#include <stdio.h>

int main(){
	int baris, kolom, i,  j;
	
	printf("Masukkan Baris: ");
	scanf("%d", &baris);
	printf("Masukkan Kolom: ");
	scanf("%d", &kolom);
	
	int A[baris][kolom], B[baris][kolom];
	
	for(i = 0; i<baris; i++){
		for(j = 0; j<kolom; j++){
			printf("Masukan A[%d][%d] = ", i,j);
			scanf("%d", &A[i][j]);
			if(A[i][j]%2 == 0){
				B[i][j] = 0;
			} else {
				B[i][j] = 1;
			}
		}
	}
	
	printf("Menampilkan Array Input: \n");
	for(i = 0; i<baris; i++){
		for(j = 0; j<kolom; j++){
			printf("%d ", A[i][j]);
		}
		printf("\n");
	}
	
	printf("\nMenentukan Angka pada Array A Ganjil[1]/Genap[0]: \n");
	for(i = 0; i<baris; i++){
		for(j = 0; j<kolom; j++){
			printf("%d ",B[i][j]);
		}
		printf("\n");
	}
}