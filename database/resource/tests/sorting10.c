#include <stdio.h>

void printArray(int arr[], int n) {
    for(int i=0; i<n; i++) {
        printf("%d ", arr[i]);
    }
    printf("\n");
}

int main() {
    int n;
    printf("Masukkan jumlah elemen array: ");
    scanf("%d", &n);

    int arr[n];
    printf("Masukkan elemen array:\n");
    for(int i=0; i<n; i++) {
        scanf("%d", &arr[i]);
    }

    printf("Proses selection sort:\n");
    for(int i=0; i<n-1; i++) {
        int min_idx = i;
        for(int j=i+1; j<n; j++) {
            if(arr[j] < arr[min_idx]) {
                min_idx = j;
            }
        }

        int temp = arr[min_idx];
        arr[min_idx] = arr[i];
        arr[i] = temp;

        printf("Iterasi %d: ", i+1);
        printArray(arr, n);
    }

    printf("Array setelah diurutkan:\n");
    printArray(arr, n);

    return 0;
}
