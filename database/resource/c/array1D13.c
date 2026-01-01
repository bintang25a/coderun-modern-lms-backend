#include <stdio.h>

int main() {
    float data[8];
    float total = 0;
    float rata_rata;
    int i;
    
    // Memasukkan data ke dalam array
    printf("Masukkan 8 data:\n");
    for (i = 0; i < 8; i++) {
        printf("Masukkan data ke-%d: ", i + 1);
        scanf("%f", &data[i]);
        total += data[i];
    }
    
    // Menghitung rata-rata
    rata_rata = total / 8;
    
    // Menampilkan hasil
    printf("\nData yang dimasukkan: ");
    for (i = 0; i < 8; i++) {
        printf("%.2f ", data[i]);
    }

    printf("\nRata-rata dari data tersebut adalah: %.2f\n", rata_rata);

    return 0;
}