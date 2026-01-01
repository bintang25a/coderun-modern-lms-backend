#include <stdio.h>

int main() {
  int a, b, pilihan;
  
  printf("masukan bilangan A: ");
  scanf("%d", &a);
  printf("masukan bilangan B: ");
  scanf("%d", &b);

  printf("Pili:\n");
  printf("[1] +\n");
  printf("[2] -\n");
  printf("[3] *\n");
  printf("[4] /\n");
  printf("Masukan pilihan: ");
  scanf("%d", &pilihan);

  switch (pilihan) {
    case 1:
      printf("Hasil %d + %d = %d\n", a, b, a + b);
      break;
    case 2:
      printf("Hasil %d - %d = %d\n", a, b, a - b);
      break;
    case 3:
      printf("Hasil %d * %d = %d\n", a, b, a * b);
      break;
    case 4:
      if (b == 0) {
        printf("Error: Tidak bisa dibagi dengan 0!\n");
      } else {
        printf("Hasil %d / %d = %d\n", a, b, a / b);
      }
      break;
    default:
      printf("Pilihan invalid!\n");
  }

  printf("tekan tombol apa saja untuuk melanjutkan...");
  getchar();
  return 0;
}