#include <stdio.h>

int x, y, res;

int addition() {
  res = x + y;
  printf("Hasil %d + %d = %d\n", x, y, res);
  return -1;
}
int substract() {
  res = x - y;
  printf("Hasil %d - %d = %d\n", x, y, res);
  return -1;
}
int multiplict() {
  res = x * y;
  printf("Hasil %d * %d = %d\n", x, y, res);
  return -1;
}
int div() {
  res = x / y;
  printf("Hasil %d / %d = %d\n", x, y, res);
  return -1;
}

int x0_fungsi() {
  int n;

  printf("Masukkan bilangan A: ");
  scanf("%d", &x);

  printf("Masukkan bilangan B: ");
  scanf("%d", &y);

  printf("Enter the option: \n");
  printf("[1] +\n");
  printf("[2] -\n");
  printf("[3] *\n");
  printf("[4] /\n");

  printf("========================================\n");

  printf("Masukkan pilihan: ");
  scanf("%d", &n);
  return n;
}

int handle_option(int n) {
  switch (n) {
  case 1:
    return addition();
  case 2:
    return substract();
  case 3:
    return multiplict();
  case 4:
    return div();
  default:
    printf("Invalid option %d.\n", n);
    break;
  }

  printf("\n");
  return 0;
}

int main(void) {
  int inp, n;

  do {
    n = x0_fungsi();
    inp = handle_option(n);

    if (inp != 0)
      break;

  } while (n != -1);

  return 0;
}
