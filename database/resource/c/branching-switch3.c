#include <stdio.h>

int main() {
    int hari = 3;
    switch (hari) {
        case 1: printf("Senin"); break;
        case 2: printf("Selasa"); break;
        case 3: printf("Rabu"); break;
        default: printf("Hari tidak valid");
    }
    return 0;
}