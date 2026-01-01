#include <stdio.h>
int main() {
	int i, j, n, temp;
	
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
	printf("\n");
	
	for (i = 1; i <= n-1; i++) {
		j = i - 1;
		temp = array[i];
		
		while ((j>=0) && (temp <= array[j])) {
			array[j+1] = array[j];
			j--;
		}
		
		array[j+1] = temp;
	}

	printf("Array Urut: ");
	for (i=0; i<n; i++) {
		printf("%d ", array[i]);
	}
}