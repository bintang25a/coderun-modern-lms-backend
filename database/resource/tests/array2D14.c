#include<stdio.h>

main() {
	int matriks [3][3];
	
	printf("Masukkan elemen elemen matriks 3x3: \n");
	for (int i = 0; i < 3; i++) {
		for (int j = 0; j < 3; j++) {
			printf("Elemen [%d] [%d]: ", i + 1, j + 1);
			scanf("%d", &matriks[i][j]);
			
		}
	}
	
	printf("\nMatriks yang dimasukkan:\n");
	for (int i = 0; i < 3; i++) {
		for (int j = 0; j < 3; j++){
			printf("%d", matriks[i][j]);
			
		}
		printf("\n");
	}
	
	return 0;
}