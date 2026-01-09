#include <stdio.h>

int main() {
    // Matriks 3x3 dengan data statis
    int matriks[3][3] = {{0, 0, 0}, {0, 0, 0}, {0, 0, 0}};
    
    // Menampilkan matriks
    printf("Matriks 3x3 dengan nilai 0:\n");
    
    // Loop untuk menampilkan elemen matriks
    for(int i = 0; i < 3; i++) {
        for(int j = 0; j < 3; j++) {
            printf("%d ", matriks[i][j]);
        }
        printf("\n");
    }
    
    return 0;
}