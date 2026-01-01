#include <stdio.h>

int main() {
    int i, n;
	char data[50], cari;

    // Meminta pengguna untuk jumlah data
    printf(" Banyak Data = ");
    scanf("%d", &n);
    printf("\n");

    // Memasukkan data ke dalam array
    for (i = 1; i <= n; i++) {
        printf("\n");
        printf(" Data [%d] = ", i);
        scanf("%s", &data[i]);
    }

    printf("\n\n");

    // Meminta pengguna untuk data yang akan dicari
    printf(" Data yang dicari = ");
    scanf("%s", &cari);

    // Menginisialisasi i di luar loop
    i = 1;

    // Mencari data dalam array
    while ((i <= n) && (data[i] != cari)) {
        i++;
    }

    // Memeriksa apakah data ditemukan
    if (i <= n) {
        printf("\n Data ditemukan dalam indeks %d", i);
    } else {
        printf("\n \a\aData yang Anda cari tidak ditemukan pada indeks ");
    }

    return 0;
}