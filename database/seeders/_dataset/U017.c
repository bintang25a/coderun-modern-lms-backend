#include <stdio.h>

int main() {
    const int ukuran = 3; 
    int matriksA[ukuran][ukuran]; 
    int matriksB[ukuran][ukuran]; 
    int matriksC[ukuran][ukuran]; 

    
    printf("Masukkan elemen matriks A (3x3):\n");
    for (int i = 0; i < ukuran; i++) {
        for (int j = 0; j < ukuran; j++) {
            printf("A[%d][%d]: ", i, j);
            scanf("%d", &matriksA[i][j]);
        }
    }

    
    printf("Masukkan elemen matriks B (3x3):\n");
    for (int i = 0; i < ukuran; i++) {
        for (int j = 0; j < ukuran; j++) {
            printf("B[%d][%d]: ", i, j);
            scanf("%d", &matriksB[i][j]);
        }
    }

    
    for (int i = 0; i < ukuran; i++) {
        for (int j = 0; j < ukuran; j++) {
            matriksC[i][j] = matriksA[i][j] + matriksB[i][j];
        }
    }

    
    printf("Hasil penjumlahan matriks A dan B (Matriks C):\n");
    for (int i = 0; i < ukuran; i++) {
        for (int j = 0; j < ukuran; j++) {
            printf("%d ", matriksC[i][j]);
        }
        printf("\n"); 
    }

    return 0;
}
