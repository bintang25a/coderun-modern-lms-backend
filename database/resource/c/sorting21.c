#include <stdio.h>
//Buat Insertion Sort agar bisa mengurutkan secara descending dan menampilkan key dan hasil iterasi
int main() {
    int i, j, k, n, key, temp, c;

    	printf("Masukan banyak array: ");
    	scanf("%d", &n);

    int array[n];

    for (i = 0; i < n; i++) {
        printf("Insert A[%d]: ", i);
        scanf("%d", &array[i]);
    }

    	printf("Array Acak: ");
    for (i = 0; i < n; i++) {
        printf("%d", array[i]);
    }
    	printf("\n");

    for (i = 1; i <= (n - 1); i++) {
    j = i - 1;
    temp = array[i];
    	printf("\nKey: %d\n", temp);

    while ((j >= 0) && (temp >= array[j])) {
      array[j + 1] = array[j];
      j--;
    }

        printf("Hasil iterasi %d: ", i);
    array[j + 1] = temp;
    for (c = 0; c < n; c++) {
      if (array[c] == temp) {
        printf("%d ", array[c]);
      } else {
        printf("%d ", array[c]);
      }
    }
    	printf("\n");
  }

    	printf("\nArray Urut: ");
    for (i = 0; i < n; i++) {
        printf("%d", array[i]);
    }
    	printf("\n");

    return 0;
}

