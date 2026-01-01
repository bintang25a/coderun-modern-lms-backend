#include <stdio.h>

int main() {
    int n;

    printf("Masukkan jumlah elemen: ");
    scanf("%d", &n);

    int data[n];

    // Input elemen array
    for (int i = 0; i < n; i++) {
        printf("Elemen ke-%d: ", i + 1);
        scanf("%d", &data[i]);
    }

    // Proses Selection Sort
    for (int i = 0; i < n - 1; i++) {
        int minIndex = i;

        for (int j = i + 1; j < n; j++) {
            if (data[j] < data[minIndex]) {
                minIndex = j;
            }
        }

        // Tukar elemen jika diperlukan
        if (minIndex != i) {
            int temp = data[i];
            data[i] = data[minIndex];
            data[minIndex] = temp;
        }

        // Tampilkan array setelah iterasi ke-i
        printf("Iterasi ke-%d: ", i + 1);
        for (int k = 0; k < n; k++) {
            printf("%d ", data[k]);
        }
        printf("\n");
    }

    // Tampilkan hasil akhir
    printf("Hasil akhir setelah selection sort:\n");
    for (int i = 0; i < n; i++) {
        printf("%d ", data[i]);
    }
    printf("\n");

    return 0;
}