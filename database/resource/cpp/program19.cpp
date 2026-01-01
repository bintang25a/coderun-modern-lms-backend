/*#include <stdio.h>

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
}*/

#include <stdio.h>

// Fungsi untuk mencetak array
void printArray(int arr[], int n) {
    for (int i = 0; i < n; i++) {
        printf("%d ", arr[i]);
    }
    printf("\n");
}

// Fungsi Insertion Sort dengan output langkah-langkah
void insertionSortWithSteps(int arr[], int n) {
    printf("\nArray Acak: ");
    printArray(arr, n);

    for (int i = 1; i < n; i++) {
        int key = arr[i]; // Nilai elemen yang akan diurutkan
        int j = i - 1;

        printf("\nKey: %d\n", key);

        // Geser elemen yang lebih besar ke kanan
        while (j >= 0 && arr[j] < key) { // Urutan menurun
            arr[j + 1] = arr[j];
            j--;
        }
        arr[j + 1] = key;

        // Tampilkan hasil iterasi
        printf("Hasil iterasi %d: ", i);
        printArray(arr, n);
    }

    printf("\nArray Urut: ");
    printArray(arr, n);
    printf("--------------------------\n");
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

    // Memanggil fungsi Insertion Sort dengan langkah-langkah
    insertionSortWithSteps(arr, n);

    return 0;
}