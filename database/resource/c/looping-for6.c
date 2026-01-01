#include <stdio.h>

int main() {
    int i, j, n = 5; // n menentukan jumlah baris segitiga

    // Loop untuk setiap baris
    for (i = 1; i <= n; i++) {
        // Loop untuk mencetak bintang di setiap baris
        for (j = 1; j <= i; j++) {
            printf("* ");
        }
        printf("\n"); // Pindah ke baris berikutnya
    }

    return 0;
}
