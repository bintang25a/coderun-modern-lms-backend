#include <stdio.h>

int main(void) {
  int i, j, k, n, c, x, y, temp;
  printf("Masukan banyak array: ");
  scanf("%d", &n);

  int array[n];

  for (i = 0; i < n; i++) {
    printf("Array index at [%d]: ", i);
    scanf("%d", &array[i]);
  }

  printf("\nUnsorted: ");
  printf("\n------------\n");
  for (i = 0; i < n; i++) {
    printf("%d ", array[i]);
  }

  printf("\n\n>> Bubble sort\n");
  for (i = 1; i <= (n - 1); i++) {
    for (j = 1; j <= (n - 1); j++) {
      if (array[j] > array[j - 1]) {
        temp = array[j];
        array[j] = array[j - 1];
        array[j - 1] = temp;

        if ((int)(sizeof(array) / sizeof(array[0]))) {
          /*printf(" [itr=%d]", j);*/
        }
      }
      for (c = 0; c < n; c++) {
        printf(" %d ", array[c]);
      }

      printf("\n");
    }

    printf("------------\n");
    printf("Itr res %d: ", i);

    for (size_t i = 0; i < n; i++) {
      printf(" %d ", array[i]);
    }

    printf("\n\n");
  }

  printf("\nSorted: ");
  printf("\n------------\n");
  for (i = 0; i < n; i++) {
    printf("%d ", array[i]);
  }

  return 0;
}
