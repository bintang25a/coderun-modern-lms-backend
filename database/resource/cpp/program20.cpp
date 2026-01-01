#include <stdio.h>

// Fungsi untuk menukar dua elemen
void swap(int *a, int *b) {
    int temp = *a;
    *a = *b;
    *b = temp;
}

// Fungsi Bubble Sort dengan tampilan iterasi
void bubbleSortWithSteps(int arr[], int n) {
    printf("\nArray Acak: ");
    for (int i = 0; i < n; i++) {
        printf("%d ", arr[i]);
    }
    printf("\n========================\n");

    for (int i = 0; i < n - 1; i++) {
        printf("\nHasil Iterasi %d:\n", i + 1);
        for (int j = 0; j < n - 1; j++) {
            if (arr[j] < arr[j + 1]) { // Mengurutkan dalam urutan menurun
                swap(&arr[j], &arr[j + 1]);
            }

            // Tampilkan array setelah setiap perubahan
            for (int k = 0; k < n; k++) {
                printf("%d ", arr[k]);
            }
            printf("\n");
        }
    }

    printf("\nArray Acak: ");
    for (int i = 0; i < n; i++) {
        printf("%d ", arr[i]);
    }
    printf("\n========================\n");
}

int main() {
    int n;

    // Meminta jumlah elemen array
    printf("Masukan banyak array: ");
    scanf("%d", &n);

    int arr[n];

    // Memasukkan elemen array
    for (int i = 0; i < n; i++) {
        printf("Insert A[%d]: ", i);
        scanf("%d", &arr[i]);
    }

    // Memanggil fungsi Bubble Sort dengan langkah-langkah
    bubbleSortWithSteps(arr, n);

    return 0;
    
}
