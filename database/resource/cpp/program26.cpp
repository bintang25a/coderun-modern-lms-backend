#include <stdio.h>

void cetakSegitiga(int tinggi) {
    for (int i = 1; i <= tinggi; i++) {
        // Mencetak spasi
        for (int j = i; j < tinggi; j++) {
            printf(" ");
        }
        // Mencetak bintang
        for (int k = 1; k <= (2 * i - 1); k++) {
            printf("*");
        }
        // Pindah ke baris berikutnya
        printf("\n");
    }
}

int main() {
    int tinggi;

    // Meminta input tinggi segitiga dari pengguna
    printf("Masukkan tinggi segitiga bintang: ");
    scanf("%d", &tinggi);

    // Memanggil fungsi untuk mencetak segitiga
    cetakSegitiga(tinggi);

    return 0;
}
