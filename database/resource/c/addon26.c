#include<stdio.h>
main() {
	int x[8];
	int i, j;
		printf("====================Program Menghitung Rata-Rata====================\n");
	float avg, jum=0;
	for(i=1; i<=8; i++){
		printf("input [%d]: " ,i);
		scanf("%d" ,&x[i]);
		jum+=x[i];
	}
		avg = jum/8;
		printf("Rata-rata nya %.2f" ,avg);
	
		printf("\n====================Program Menjumlahkan 2 Matriks====================\n");
	int A[3][3], B[3][3], C[3][3];
	for (i=0; i<=2; i++){
		for (j=0; j<3; j++){
		printf ("A[%d][%d] = ", i, j); scanf ("%d", &A[i][j]);
		printf ("B[%d][%d] = ", i, j); scanf ("%d", &B[i][j]);	
		}
	}
		printf ("\n");
	for (i=0; i<3; i++){
		for (j=0; j<3; j++){
			C[i][j]=A[i][j]+B[i][j];
			printf ("%d ", C[i][j]);
		}
		printf ("\n");	
	}
	
	printf("\n====================Program Menampilkan Hasil====================\n");
    int matriks[3][3] = {{0, 0, 0}, {0, 0, 0}, {0, 0, 0}};
    for (i = 0; i < 3; i++) {
        matriks[i][i] = 1; 
    }
    for (i = 0; i < 3; i++) {
        for (j = 0; j < 3; j++) {
            printf("%d  ", matriks[i][j]);
        }
        printf("\n");
    }
    return 0;


}


