#include <stdio.h>

int main() {
    int i, j, n;

    // Input jumlah baris segitiga
    printf("Masukkan jumlah baris segitiga: ");
    scanf("%d", &n);

    // Loop untuk membuat segitiga
    for (i = 1; i <= n; i++) {
        // Loop untuk mencetak bintang
        for (j = 1; j <= i; j++) {
            printf("* ");
        }
        // Pindah ke baris berikutnya
        printf("\n");
    }

    return 0;
}