#include <stdio.h>

int main(void) {

  printf("# ## ## ## ## ## ## ## ## ## ## ## ## ## ## ## ## ## #\n");
  printf("#               Kinda looks like Pyramid             #\n");
  printf("# ## ## ## ## ## ## ## ## ## ## ## ## ## ## ## ## ## #\n");

  short x;

  printf("Jumlah baris: ");
  scanf("%d", &x);

  printf("like a ladder:\n");
  printf("---------------------------------");
  for (size_t r = 0; r <= x; r++) { // Print row(s) based on given input value.

    for (size_t c = 0; c < r;
         c++) { // Print column(s) based on current row index ugh.
      printf("*");
    }

    printf("\n");
  }

  printf("\nlike a ladder but reversed:\n");
  printf("---------------------------------\n");

  for (size_t r = x; r > 0; r--) { // Print row(s) based on given input value.

    for (size_t c = 0; c < r;
         c++) { // Print column(s) based on current row index ugh.
      printf("*");
    }

    printf("\n");
  }

  return 0;
}
