#include <stdio.h>
	
	int a[10];
	int i, j, n, m, min, temp;
	
void Array (int i, int n, int a[]) {
	printf ("| ");
	for (i=0; i<n; i++) {
		printf (" %d ", a[i]);
	}
	printf (" |\n\n");
}
	
void Sorting (int a[], int i, int n, int m, int min, int temp){
	printf ("\n\n\tSelection Sort\n");
	printf ("\t~~~~~~~~~~~~~~\n");
	
	for (i=0; i<n; i++) {
		min = i;
		for (j=i+1; j<n; j++) {
			if (a[j] < a[min])
				min = j;			
			}
			printf ("\nMain Key [%d] :\n\t", min);
			printf ("| ");
				for (m=0; m<n; m++) {
					if (a[m] == min) {
						printf ("[%d]", a[m]);
					} else {
      				printf(" %d ", a[m]);
					}
				}	
			printf (" |\n");
		if (min != i) {
            int temp = a[i];
            a[i] = a[min];
            a[min] = temp;
        }
        printf("\nIterasi Ke [%d] =\n\t", i+1);
        Array (i ,n ,a );
		printf ("--------------------------------\n");
	}
}
	
int main () {
	printf ("Masukan banyak array A = "); scanf ("%d", &n);
	printf ("\nMasukan Nilai array A \n");
	for (i=0; i<n; i++) {
		printf ("\tNilai A[%d] = ", i);
		scanf ("%d", & a[i]);
	}
	
	printf ("\nArray Acak = ");
	Array (i ,n ,a );
	
	Sorting (a, i, n, m, min, temp);
	
	printf ("\n\nArray Urut = ");
	Array (i ,n ,a );
}
