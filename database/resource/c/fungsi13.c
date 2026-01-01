#include <stdio.h>
//Buat program untuk menghitung (+ - * /) menggunakan fungsi, contoh seperti di lampiran
int tambah(a, b) {
    return a + b;
}

int kurang(a, b) {
    return a - b;
}

int kali(a, b) {
    return a * b;
}

float bagi(a, b) {
    return (float)a / b; 
}

int main() {
    int a, b, pilihan;
    float hasil;

    printf("Masukan bilangan A: ");	scanf("%d", &a);
    printf("Masukan bilangan B: ");	scanf("%d", &b);

    printf("\nPilih:\n");
    printf("[1] +\n");
    printf("[2] -\n");
    printf("[3] *\n");
    printf("[4] /\n");
    printf("Masukan pilihan: "); scanf("%d", &pilihan);

    switch (pilihan) {
        case 1:
            hasil = tambah(a, b);
            printf("Hasil %d + %d = %.2f\n", a, b, hasil);
            break;
        case 2:
            hasil = kurang(a, b);
            printf("Hasil %d - %d = %.2f\n", a, b, hasil);
            break;
        case 3:
            hasil = kali(a, b);
            printf("Hasil %d * %d = %.2f\n", a, b, hasil);
            break;
        case 4:
            if (b != 0) {
                hasil = bagi(a, b);
                printf("Hasil %d / %d = %.2f\n", a, b, hasil);
            } else {
                printf("Error: Tidak bisa dibagi dengan nol\n");
            }
            break;
        default:
            printf("Pilihan tidak valid.\n");
    }

    return 0;
}

