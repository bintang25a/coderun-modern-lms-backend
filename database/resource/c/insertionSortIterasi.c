#include <stdio.h>
int main() {
	int i, j, k, n, temp;
	
	printf("Masukan banyak array: ");
	scanf("%d", &n);
	
	
	int array[n];
	
	for (i=0; i<n; i++) {
		printf("Insert A[%d]: ", i);
		scanf("%d", &array[i]);
	}
	
	printf("Array Acak: ");
	for (i=0; i<n; i++) {
		printf("%d ", array[i]);
	}
	printf("\n\n");
	
	for (i = 1; i <= n-1; i++) {
		j = i - 1;
		temp = array[i];
		printf("Key: %d\n", temp);
		
		while ((j>=0) && (temp >= array[j])) {
			array[j+1] = array[j];
			j--;
		}
		
		array[j+1] = temp;
		
		printf("Hasil iterasi %d: ", i);
		for (k=0; k<n; k++) {
			printf("%d ", array[k]);
		}
		printf("\n\n");
	}

	printf("Array Urut: ");
	for (i=0; i<n; i++) {
		printf("%d ", array[i]);
	}
}