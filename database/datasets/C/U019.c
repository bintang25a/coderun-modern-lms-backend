#include <stdio.h>

int main() {
    const int ukuran = 3; 
    int matriks[ukuran][ukuran]; 

    
    for (int i = 0; i < ukuran; i++) {
        for (int j = 0; j < ukuran; j++) {
            matriks[i][j] = 0; 
        }
    }

    
    printf("Matriks 3x3 dengan semua elemen 0:\n");
    for (int i = 0; i < ukuran; i++) {
        for (int j = 0; j < ukuran; j++) {
            printf("%d ", matriks[i][j]); 
        }
        printf("\n"); 
    }

    return 0;
}
