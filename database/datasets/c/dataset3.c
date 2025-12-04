#include <stdio.h> 

	int n, m, i, j, D1=0, D2=0;
	int a[10][10];
	
void PenjumlahanDiagonalArray (int n, int m, int a[10][10]) {
	printf ("\nMatriks A :\n");
	for (i=0; i<n; i++) {
		printf ("|\t");
		for (j=0; j<m; j++){
			printf ("%d\t", a[i][j]);
		}
		printf ("|\n\n");
	}	
 	if ( n==1 || m==1 || m!=n) {
 		printf ("\nDiagonal tidak tersedia");
	 }
	else {
		for (i=0; i<n; i++) {
            D1 = D1 + a[i][i];
			D2 = D2 + a[i][n-1-i];	
		}
	printf ("\nHasil penjumlahan diagonal utama dari matriks A = %d ", D1);
	printf ("\nHasil penjumlahan diagonal sekunder dari matriks A = %d ", D2);	
	}
	printf ("\n");
}

int main () {
	printf ("Masukan banyak baris matriks = ");	scanf ("%d", &n);
	printf ("Masukan banyak kolom matriks = "); scanf ("%d", &m);
	printf ("\nMasukan nilai matriks : A\n");
	for (i=0; i<n; i++) {
		for (j=0; j<m; j++) {
			printf ("\tMatriks A[%d][%d] = ", i+1, j+1);
			scanf ("%d", &a[i][j]);
		}
	}
	PenjumlahanDiagonalArray (n, m, a);
}

