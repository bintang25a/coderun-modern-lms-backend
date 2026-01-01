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

// Definisikan fungsi findMax
int findMax(int arr[], int size) {
    int max = arr[0]; // Asumsikan elemen pertama adalah yang terbesar
    
    for (int i = 1; i < size; i++) {
        if (arr[i] > max) {
            max = arr[i]; // Perbarui nilai maksimum jika elemen lebih besar ditemukan
        }
    }
    
    return max; // Kembalikan nilai maksimum
}

