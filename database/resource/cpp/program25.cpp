#include <stdio.h>

int main() {
  int i, j, jumlah;
  
  printf("Masukkan angka: ");
  scanf("%i", &jumlah);
  printf("\n");
 
  
  for (i = 0; i <= jumlah; i++) {
     for(j = 1 ; j <= i; j++){
     printf("*");
     }
    printf("\n");
  }
  
  
  
}
