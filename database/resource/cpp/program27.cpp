#include <stdio.h>

int main() {
    const int jumlahData = 8; 
    float data[jumlahData]; 
    float total = 0;        
    int i;                   
    // Input data dari pengguna
    printf("Masukan 8 Data:\n");
    for (i = 0; i < jumlahData; i++) {
        printf("Masukan Data ke-%d: ", i + 1);
        scanf("%f", &data[i]);
        total += data[i]; 
    }

    // Hitung rata-rata
    float rata_rata = total / jumlahData;

    // Tampilkan hasil dengan format 2 desimal
    printf("\nRata-rata dari data tersebut adalah: %.2f\n", rata_rata);

    return 0;
}
