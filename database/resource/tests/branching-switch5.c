#include <stdio.h>

int main() {
    char grade = 'A';
    switch (grade) {
        case 'A': printf("Sempurna"); break;
        case 'B': printf("Bagus"); break;
        case 'C': printf("Cukup"); break;
        default: printf("Gagal");
    }
    return 0;
}