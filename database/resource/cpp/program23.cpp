#include <stdio.h>

float penjumlahan(float a, float b) {
    return a + b;
}

float pengurangan(float a, float b) {
    return a - b;
}

float perkalian(float a, float b) {
    return a * b;
}

float pembagian(float a, float b) {
    if (b != 0) {
        return a / b;
    } else {
        printf("Error: Pembagian dengan nol!\n");
        return 0; 
    }
}

int main() {
    float a, b;
    int nomor;

    printf("Masukan Bilangan a = ");
    scanf("%f", &a);
    printf("Masukan Bilangan b = ");
    scanf("%f", &b);
    
    printf("\n1. Penjumlahan\n2. Pengurangan\n3. Perkalian\n4. Pembagian\n\n");
    printf("Pilih Operasi Matematika = ");
    scanf("%d", &nomor);
    
    switch(nomor) {
        case 1: 
            printf("Hasil = %.2f\n", penjumlahan(a, b));
            break;
        case 2: 
            printf("Hasil = %.2f\n", pengurangan(a, b));
            break;
        case 3: 
            printf("Hasil = %.2f\n", perkalian(a, b));
            break;
        case 4: 
            printf("Hasil = %.2f\n", pembagian(a, b));
            break;
        default: 
            printf("Pilihan tidak tersedia\n");
    }

    return 0;
}
