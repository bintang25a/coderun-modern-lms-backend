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
    int max = arr[0];  
    int i;
    for (i = 1; i < size; i++) {
        if (arr[i] > max) {
            max = arr[i];  
        }
    }
    return max;  
}

