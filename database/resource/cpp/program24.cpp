#include<stdio.h>

int max, min;
int A[100];

void maxmin (int i, int j) {
	int max1, min1, mid;
	if (i==j) {
		max = min = A[i];
 	}
	else {
		if (i == j-1) {
			if (A[i] < A[j]) {
    			max = A[j];
    			min = A[i];
   			}
			else {
				max = A[i];
    			min = A[j];
			}
		}
		else {
			mid = (i+j)/2;
			maxmin (i, mid);
			max1 = max; min1 = min;
			maxmin (mid+1, j);
			if (max <max1)
			max = max1;
			if (min > min1)
			min = min1;
		}
	}
}

void sort(int A[], int n) {
    int i, j, temp;
	for (i=1; i<=n-1; i++){
		j = i - 1;
		temp = A[i];
		while ((j>=0) && (temp<=A[j])) {
			A[j+1] = A[j];
			j--;
		}
		A[j+1] = temp;
	}
}

void sorted(int A[], int size){
	int i;
    for (i = 1; i <= size; i++) {
        printf("%d ", A[i]);
    }
    printf("\n");
}

int main () {
	int i, n;
	printf ("Masukan Banyak Array : "); scanf ("%d",&n);
	printf ("\n");
	
	for (i=1;i<=n;i++) {
		printf ("Array [%d] : ", i); 
		scanf ("%d",&A[i]); 
	}

	printf ("\n===========================\n");
	printf("\nArray Urut : \n");
	sort (A, n);
	sorted (A, n);
	
	max = A[0];
	min = A[0];
	maxmin (1, n);
	
	printf ("\n===========================\n\n");
	printf ("Nilai Min Dari Array : %d\n", min);
	printf ("\n");
	printf ("Nilai Max Dari Array : %d\n", max);
	printf ("\n===========================\n\n");
}
