/*#include <stdio.h> // Perbaikan dari <studio.h>

int main() {
    int i;
    int num = 10;
    char letter = 'A';

    // Hapus Main() dalam main karena tidak diperlukan
    printf("Hello, World!\n");

    // Ubah If menjadi if
    if (num > 5) {
        printf("Number is greater than 5\n");
    }

    // Ganti For dengan for dan gunakan titik koma (;) sebagai pemisah
    for (i = 0; i < num; i++) {
        printf("i = %d\n", i);
    }

    // Ubah While menjadi while
    while (letter <= 'Z') {
        printf("Letter: %c\n", letter);
        letter++;
    }

    // Ubah Return menjadi return
    return 0;
}*/

/*#include <stdio.h>

int main() {
    int num;

    printf("Masukkan sebuah bilangan: ");
    scanf("%d", &num);

    // Memeriksa apakah bilangan positif, negatif, atau nol
    if (num > 0) {
        printf("Bilangan %d adalah bilangan positif.\n", num);
    } else if (num < 0) {
        printf("Bilangan %d adalah bilangan negatif.\n", num);
    } else {
        printf("Bilangan yang Anda masukkan adalah nol.\n");
    }

    return 0;
}*/

#include <stdio.h>

// Deklarasi fungsi untuk mencari nilai maksimum dalam array
int findMax(int arr[], int size);

int main() {
    int arr[] = {10, 20, 5, 25, 15};
    int size = sizeof(arr) / sizeof(arr[0]);

    // Panggil fungsi findMax dan cetak hasilnya
    int max = findMax(arr, size);
    printf("Nilai maksimum dalam array: %d\n", max);

    return 0;
}

// Definisikan fungsi findMax di sini
int findMax(int arr[], int size) {
    int max = arr[0]; // Anggap elemen pertama adalah nilai maksimum awal
    for (int i = 1; i < size; i++) {
        if (arr[i] > max) {
            max = arr[i]; // Update nilai maksimum jika ditemukan elemen yang lebih besar
        }
    }
    return max;
}