#include <stdio.h>

int main() {
    int angka;
    do {
        printf("Masukkan angka > 0: ");
        scanf("%d", &angka);
    } while (angka <= 0);
    return 0;
}