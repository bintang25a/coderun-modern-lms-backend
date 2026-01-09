#include <stdio.h>

int main(void) {

  printf("=======================\n");
  printf("Kinda looks Pyramid\n");
  printf("=======================\n");

  short x;

  printf("Jumlah baris: ");
  scanf("%d", &x);

  for (size_t r = 0; r <= x; r++) { // Print row(s) based on given input value.

    for (size_t c = 0; c < r;
         c++) { // Print column(s) based on current row index ugh.
      printf("*");
    }

    printf("\n");
  }

  return 0;
}
