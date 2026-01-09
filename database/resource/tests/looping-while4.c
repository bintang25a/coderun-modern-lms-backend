#include <stdio.h>

int main() {
    char pilihan = 'y';
    while (pilihan != 'n') {
        printf("Lanjut? (y/n): ");
        scanf(" %c", &pilihan);
    }
    return 0;
}