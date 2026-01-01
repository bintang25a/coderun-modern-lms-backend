#include<stdio.h>
main() {
	int A[2][3], B[2][3], C[2][3],i ,j;
	puts("Inputs matrik A dan B");
	for(i=0; i<2; i++){
		for(j=0; j<3; j++){
			printf("A[%d][%d]= ", i, j); scanf("%d", &A[i][j]);
			printf("B[%d][%d]= ", i, j); scanf("%d", &B[i][j]);
		}
	}
	
	puts("Jumlahkan Matrik A dan B\n");
	for(i=0; i<2; i++){
		printf("|" );
		for(j=0; j<=2; j++){
			C[i][j]=A[i][j]+B[i][j];
			printf("%d ", C[i][j]);
		}
		printf("|\n");
	}
}