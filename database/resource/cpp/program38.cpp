#include <stdio.h>

int main() {
    const int jumlahData = 8; 
    double data[jumlahData];   
    double total = 0;          
    double rataRata;           

    
    printf("Masukkan %d angka:\n", jumlahData);
    for (int i = 0; i < jumlahData; i++) {
        printf("Data ke-%d: ", i + 1);
        scanf("%lf", &data[i]); 
        total += data[i]; 
    }

    
    rataRata = total / jumlahData;

    
    printf("Rata-rata dari data yang dimasukkan adalah: %.2lf\n", rataRata);

    return 0;
}
