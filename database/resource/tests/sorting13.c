#include <stdio.h>
void selectionSortDenganIterasi(int arr[], int n) {
    int i, j, min_idx, temp;
    for (i = 0; i < n - 1; i++) {
        min_idx = i;
        for (j = i + 1; j < n; j++) {
            if (arr[j] < arr[min_idx]) {
                min_idx = j;
            }
        }
        if (min_idx != i) {
            temp = arr[i];
            arr[i] = arr[min_idx];
            arr[min_idx] = temp;
        }

        printf("Iterasi ke-%d: ", i + 1);
        for (int k = 0; k < n; k++) {
            printf("%d ", arr[k]);
        }
        printf("\n");
    }
}

int main() {
    int n;
    int i; 
    printf("Masukkan jumlah elemen array: ");
    scanf("%d", &n);

    if (n <= 0) {
        printf("Jumlah elemen harus lebih dari 0.\n");
        return 1;
    }

    int arr[n]; 

    printf("Masukkan %d elemen array:\n", n);
    for (i = 0; i < n; i++) {
        printf("Elemen ke-%d: ", i + 1);
        scanf("%d", &arr[i]);
    }

    printf("\nArray awal: ");
    for (i = 0; i < n; i++) {
        printf("%d ", arr[i]);
    }
    printf("\n\n");
    selectionSortDenganIterasi(arr, n);
    printf("\nArray setelah diurutkan: ");
    for (i = 0; i < n; i++) {
        printf("%d ", arr[i]);
    }
    printf("\n");

    return 0;
}