#include <stdio.h>

// Fungsi untuk menukar dua elemen
void swap(int *a, int *b) {
    int temp = *a;
    *a = *b;
    *b = temp;
}

// Fungsi untuk menampilkan array
void printArray(int arr[], int n) {
    for (int i = 0; i < n; i++) {
        printf("%d ", arr[i]);
    }
    printf("\n");
}

// Fungsi selection sort dengan tampilan iterasi
void selectionSort(int arr[], int n) {
    int i, j, min_idx;

    for (i = 0; i < n - 1; i++) {
        min_idx = i;
        for (j = i + 1; j < n; j++) {
            if (arr[j] < arr[min_idx]) {
                min_idx = j;
            }
        }

        // Tukar elemen minimum dengan elemen pertama yang belum terurut
        if (min_idx != i) {
            swap(&arr[min_idx], &arr[i]);
        }

        // Tampilkan array setelah setiap iterasi
        printf("Iterasi %d: ", i + 1);
        printArray(arr, n);
    }
}

int main() {
    int n;

    // Input jumlah elemen
    printf("Masukkan jumlah elemen: ");
    scanf("%d", &n);

    // Alokasi array
    int arr[n];

    // Input elemen array
    printf("Masukkan %d elemen:\n", n);
    for (int i = 0; i < n; i++) {
        printf("Elemen ke-%d: ", i + 1);
        scanf("%d", &arr[i]);
    }

    // Proses selection sort
    printf("\nProses Selection Sort:\n");
    selectionSort(arr, n);

    // Tampilkan hasil akhir
    printf("\nArray setelah diurutkan:\n");
    printArray(arr, n);

    return 0;
}

