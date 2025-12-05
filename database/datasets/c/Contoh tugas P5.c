#include<stdio.h>

main() {
	int i, j;
	int x[3][4];
	
	for (i=0; i<3; i++) {
		for (j=0; j<4; j++) {
			printf("Masukan Array Baris[%d] dan Kolom[%d] = ", i, j);
			scanf("%d", &x[i][j]);
		}
		printf("\n");
	}
	
	for (i=0; i<3; i++) {
		for (j=0; j<4; j++) {
			printf("%d\t", x[i][j]);
		}
		printf("\n");
	}
}