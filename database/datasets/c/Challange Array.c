#include <stdio.h>

int main() {
	
	float angka[8], jum=0;
	int i, j;
	
	for (i=0; i<8; i++) {
		printf("input [%d]: ", i);
		scanf("%f", &angka[i]);
		jum+=angka[i];
	}
	
	printf("Rata-ratanya adalah %.2f\n\n", jum/8);
	
	int A[3][3], B[3][3], C[3][3];
	
	for (i=0; i<3; i++) {
		for (j=0; j<3; j++) {
			printf("A[%d][%d]: ", i, j);
			scanf("%d", &A[i][j]);
			printf("B[%d][%d]: ", i, j);
			scanf("%d", &B[i][j]);
			
			C[i][j] = A[i][j]+B[i][j];
		}
	}
	
	printf("\n");
	
	for (i=0; i<3; i++) {
		for (j=0; j<3; j++) {
			printf("%d ", C[i][j]);
		}
		printf("\n");
	}
	
	printf("\n\n");
	
	int matriks[3][3] = {0};
	for (i=0; i<3; i++) {
		for (j=0; j<3; j++) {
			if (i==j) {
				matriks[i][j]+=1;
			}
			
			printf("%d ", matriks[i][j]);
		}
		printf("\n");
	}
}