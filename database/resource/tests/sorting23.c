#include <stdio.h>

int main(void) {
  int i, j, n, c, x, y, temp;
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

  printf("\n\n>> Insertion sort");
  for (i = 1; i <= (n - 1); i++) {

    j = i - 1;
    temp = array[i];
    printf("\nKey: %d\n", temp);

    while ((j >= 0) && (temp >= array[j])) {
      array[j + 1] = array[j];
      j--;
    }

    array[j + 1] = temp;
    for (c = 0; c < n; c++) {
      if (array[c] == temp) {
        printf(" [%d] ", array[c]);
      } else {
        printf(" %d ", array[c]);
      }
    }
    printf("\n");
  }

  printf("\nSorted: ");
  printf("\n------------\n");
  for (i = 0; i < n; i++) {
    printf("%d ", array[i]);
  }

  return 0;
}
