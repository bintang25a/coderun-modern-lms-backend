#include <stdio.h>

int main() {
    int n;
    int data[100];
    int i, j, k, minIndex, temp;

    printf("Masukkan jumlah elemen: ");
    scanf("%d", &n);

    printf("Masukkan elemen:\n");
    for (i = 0; i < n; i++) {
        printf("Elemen ke-%d: ", i + 1);
        scanf("%d", &data[i]);
    }

    for (i = 0; i < n - 1; i++) {
        minIndex = i;
        for (j = i + 1; j < n; j++) {
            if (data[j] < data[minIndex]) {
                minIndex = j;
            }
        }

        temp = data[i];
        data[i] = data[minIndex];
        data[minIndex] = temp;

        // Tampilkan iterasi
        printf("\nIterasi ke-%d: ", i + 1);
        for (k = 0; k < n; k++) {
            printf("%d ", data[k]);
        }
    }

    printf("\n\nHasil akhir: ");
    for (i = 0; i < n; i++) {
        printf("%d ", data[i]);
    }
    printf("\n");

    return 0;
}

