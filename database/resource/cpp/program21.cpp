
#include <stdio.h>

main() {
  int i, j, k, n, c, x, temp;
  printf("Masukan banyak array: ");  scanf("%d", &n);

  int array[n];

  for (i=0; i<n; i++) {
    printf("Array [%d]: ", i);
    scanf("%d", &array[i]);
  }

  printf("\nArray Acak: ");
  for (i=0; i<n; i++) {
    printf("%d ", array[i]);
  }

  printf("\n\n\n>> Bubble sort:\n\n");
	for (i=1; i<=(n-1); i++) {
		for (j=1; j<=(n-1); j++) {
			if(array[j] > array[j-1]) {
				temp = array[j];
				array[j] = array[j-1];
				array[j-1] = temp;
      }
      for (c=0; c<n; c++) {
        printf(" %d ", array[c]);
      }
      printf("\n");
    }
    printf("------------\n");
    printf("Hasil Iterasi Ke [%d] =", i);
    for (x=0; x<n; x++) {
      printf(" %d ", array[x]);
    }
    printf("\n\n");
  }

  printf("\nArray urut: ");
  for (i = 0; i < n; i++) {
    printf("%d ", array[i]);
  }
}

