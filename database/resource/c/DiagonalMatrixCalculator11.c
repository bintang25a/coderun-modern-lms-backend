#include <stdio.h>

int main(void) {

  short x[8], total = 0, matrix_a[3][3], matrix_b[3][3], matrix_c[3][3];
  float avg;

  printf("######################\n");
  printf("#      Challange     #\n");
  printf("######################\n\n");

  printf("Average value for the given array of 8");
  printf("\n--------------------\n");

  for (size_t i = 0; i < 8; i++) {
    printf("The x[%d] value: ", i);
    scanf("%d", &x[i]);
    total += x[i];
  }

  avg = (float)total / 8;

  printf("Average value is: %.3f\n", avg);

  printf("\n--------------------\n");
  printf("Addition two matrixs\n\n");

  for (size_t i = 0; i <= 2; i++) {
    for (size_t j = 0; j < 3; j++) {
      printf("A[%d][%d]: ", i, j);
      scanf("%d", &matrix_a[i][j]);
      printf("B[%d][%d]: ", i, j);
      scanf("%d", &matrix_b[i][j]);
    }
  }

  printf("\n");

  for (size_t i = 0; i < 3; i++) {
    for (size_t j = 0; j < 3; j++) {
      matrix_c[i][j] = matrix_a[i][j] + matrix_b[i][j];
      printf("%d ", matrix_c[i][j]);
    }
    printf("\n");
  }

  printf("\n");

  printf("Matrix 3x3 with diagonal one");
  printf("\n--------------------\n");

  short r[3][3] = {{0, 0, 0}, {0, 0, 0}, {0, 0, 0}};

  for (size_t i = 0; i < 3; i++) {

    for (size_t j = 0; j < 3; j++) {
      if ((j == 0 && i == 0) || (j == 1 && i == 1) || (j == 2 && i == 2)) {
        printf("1 ");
      } else {
        printf("%d ", r[i][j]);
      }
    }

    printf("\n");
  }
}
