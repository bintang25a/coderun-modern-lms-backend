#include <stdio.h>

int main() {
    int matriks[3][3]; // Matriks 3x3

    // Inisialisasi matriks dengan nilai 0
    for (int i = 0; i < 3; i++) {
        for (int j = 0; j < 3; j++) {
            matriks[i][j] = 0;
        }
    }

    // Tampilkan matriks
    printf("Matriks 3x3 dengan elemen bernilai 0:\n");
    for (int i = 0; i < 3; i++) {
        for (int j = 0; j < 3; j++) {
            printf("%d ", matriks[i][j]);
        }
        printf("\n"); // Baris baru setelah setiap baris matriks
    }

    return 0;
}

