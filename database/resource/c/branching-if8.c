#include <stdio.h>

int main(void)
{
  int number;

  printf("Masukkan sebuah bilanga: ");
  scanf("%d", &number);

  if (number > 0) {
    printf("Bilangan %d positif.\n", number);
  } else if(number < 0){
    printf("Bilangan %d negatif.\n", number);
  } else {
    printf("Bilangan %d nol.\n", number);
  }

  return 0;
}
