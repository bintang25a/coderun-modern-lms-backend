#include <stdio.h>

int main() {
  int kode_pos;

  printf("Masukkan kode pos: ");
  scanf("%d", &kode_pos);

  switch (kode_pos) {
    case 10210:
      printf("Kelurahan: Bendungan Hilir\n");
      break;
    case 10220:
      printf("Kelurahan: Karet Tengsin\n");
      break;
    case 10230:
      printf("Kelurahan: Kebon Melati\n");
      break;
    case 10240:
      printf("Kelurahan: Kebon Kacang\n");
      break;
    case 10250:
      printf("Kelurahan: Kampung Bali\n");
      break;
    case 10260:
      printf("Kelurahan: Petamburan\n");
      break;
    case 10270:
      printf("Kelurahan: Gelora\n");
      break;
    default:
      printf("Kode pos tidak ditemukan.\n");
  }

  return 0;
}
