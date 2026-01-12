#include <stdio.h>

main() {
  int i, j, n, c, x, y, temp;
  printf("Masukan banyak array: ");
  scanf("%d", &n);

  int array[n];

  for (i=0; i<n; i++) {
    printf("Array [%d]: ", i);
    scanf("%d", &array[i]);
  }

  printf("\nArray Acak: ");
  for (i=0; i<n; i++) {
    printf("%d ", array[i]);
  }

  printf("\n\n>> Insertion sort\n"); printf("====================");
  for (i=1; i<=(n - 1); i++) {

    j = i-1;
    temp = array[i];
    printf("\nMain [%d]: \n", temp);

    while ((j >= 0) && (temp >= array[j])) {
      array[j + 1] = array[j];
      j--;
    }

    array[j + 1] = temp;
    for (c=0; c<n; c++) {
      if (array[c] == temp) {
        printf(" [%d] ", array[c]);
      } else {
        printf(" %d ", array[c]);
      }
    }
    printf("\n");
  }
  printf("====================");
  
  printf("\n\nArray Urut: ");
  for (i=0; i<n; i++) {
    printf("%d ", array[i]);
  }
}
