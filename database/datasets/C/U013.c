#include <stdio.h>

int main() {
    int i;
    float data[8], sum = 0.0, average;

    // Input 8 data dari pengguna
    printf("Masukkan 8 angka untuk dihitung rata-ratanya:\n");
    for(i = 0; i < 8; i++) {
        printf("Masukkan angka ke-%d: ", i + 1);
        scanf("%f", &data[i]);
        sum += data[i];  // Menambahkan setiap angka ke jumlah total
    }

    // Menghitung rata-rata
    average = sum / 8;

    // Menampilkan hasil rata-rata
    printf("Rata-rata dari 8 angka yang dimasukkan adalah: %.2f\n", average);

    return 0;
}
