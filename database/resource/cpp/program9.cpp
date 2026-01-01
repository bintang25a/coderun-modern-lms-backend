#include <stdio.h>

int main() {
    int i, j, n;

    // Meminta mahasiswa A1 untuk memasukkan tinggi segitiga
    printf("Masukkan jumlah segitiga dari arah kiri: ");
    scanf("%d", &n);

    // Mencetak segitiga bintang ke arah kiri
    for (i = 1; i <= n; i++) {
        for (j = 1; j <= i; j++) {
            printf("*");
        }
        printf("\n");
    }

// Meminta mahasiswa A1 untuk memasukkan tinggi segitiga
    printf("Masukkan tinggi segitiga dari arah kanan: ");
    scanf("%d", &n);

    // Mencetak segitiga bintang ke arah kanan
    for (i = 1; i <= n; i++) {
        // Mencetak spasi
        for (j = 1; j <= n - i; j++) {
            printf(" ");
        }
        // Mencetak bintang
        for (j = 1; j <= i; j++) {
            printf("*");
        }
        printf("\n");
    }

    
// Meminta pengguna untuk memasukkan tinggi segitiga
    printf("Masukkan tinggi segitiga sempurna: ");
    scanf("%d", &n);

    // Mencetak segitiga bintang
    for(i = 1; i <= n; i++) {
        // Mencetak spasi
        for(j = 1; j <= n - i; j++) {
            printf(" ");
        }
        // Mencetak bintang
        for(j = 1; j <= (2 * i - 1); j++) {
            printf("*");
        }
        // Pindah ke baris berikutnya
        printf("\n");
    }

    return 0;
}
