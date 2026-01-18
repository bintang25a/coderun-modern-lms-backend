#include <stdio.h>
#include <stdio.h>
#include <string.h>

int main() {
    // Daftar kelurahan dan kode pos
    const char *kelurahan[] = {"Bendungan Hilir", "Karet Tengsin", "Kebon Melati", 
                               "Kebon Kacang", "Kampung Bali", "Petamburan", "Gelora"};
    const int kodePos[] = {10210, 10220, 10230, 10240, 10250, 10260, 10270};
    int jumlahKelurahan = sizeof(kodePos) / sizeof(kodePos[0]);

    char input[50];
    printf("Masukkan nama kelurahan: ");
    fgets(input, sizeof(input), stdin);
    input[strcspn(input, "\n")] = '\0'; // Menghapus karakter newline dari input

    // Mencari kelurahan
    int ditemukan = 0;
    int i;
	for (i = 0; i < jumlahKelurahan; i++) {
        if (strcasecmp(input, kelurahan[i]) == 0) { // Bandingkan input dengan daftar kelurahan
            printf("Kode Pos %s adalah %d\n", kelurahan[i], kodePos[i]);
            ditemukan = 1;
            break;
        }
    }

    if (!ditemukan) {
        printf("Kelurahan tidak ditemukan dalam daftar.\n");
    }

    return 0;
}


intmain() {
    // Daftar kelurahan dan kode pos
    const char *kelurahan[] = {"Bendungan Hilir", "Karet Tengsin", "Kebon Melati", 
                               "Kebon Kacang", "Kampung Bali", "Petamburan", "Gelora"};
    const int kodePos[] = {10210, 10220, 10230, 10240, 10250, 10260, 10270};
    int jumlahKelurahan = sizeof(kodePos) / sizeof(kodePos[0]);

    char input[50];
    printf("Masukkan nama kelurahan: ");
    fgets(input, sizeof(input), stdin);
    input[strcspn(input, "\n")] = '\0'; // Menghapus karakter newline

    int ditemukan = 0;
    int i;
	for (i = 0; i < jumlahKelurahan; i++) {
   
        if (strcasecmp(input, kelurahan[i]) == 0) {
            printf("Kode Pos %s adalah %d\n", kelurahan[i], kodePos[i]);
            ditemukan = 1;
            break;
        }
    }

    if (!ditemukan) {
        printf("Kelurahan tidak ditemukan dalam daftar.\n");
    }

    return 0;
}

