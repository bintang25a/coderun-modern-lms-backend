#include <stdio.h>

int main() {
    int n;

    printf("Masukkan jumlah elemen: ");
    scanf("%d", &n);

    int data[100];

    printf("Masukkan elemen array:\n");
    for (int i = 0; i < n; i++) {
        printf("data[%d]: ", i);
        scanf("%d", &data[i]);
    }

    printf("\nProses selection sort:\n");
    for (int i = 0; i < n - 1; i++) {
        int minIndex = i;
        for (int j = i + 1; j < n; j++) {
            if (data[j] < data[minIndex]) {
                minIndex = j;
            }
        }

        // Tukar elemen
        if (minIndex != i) {
            int temp = data[i];
            data[i] = data[minIndex];
            data[minIndex] = temp;
        }

        // Tampilkan array setelah setiap iterasi
        printf("Iterasi %d: ", i + 1);
        for (int k = 0; k < n; k++) {
            printf("%d ", data[k]);
        }
        printf("\n");
    }

    printf("\nArray setelah diurutkan: ");
    for (int i = 0; i < n; i++) {
        printf("%d ", data[i]);
    }
    printf("\n");

    return 0;
}

