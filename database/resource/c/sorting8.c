#include <stdio.h> 

void tampilkanArray(int arr[], int ukuran) {
    for (int i = 0; i < ukuran; i++) {
        printf("%d ", arr[i]);
    }
    printf("\n\n");
}

int main() {
    int n, arr[10];

    printf("Berapa banyak angka (maksimal 10)? ");
    scanf("%d", &n);

    if (n > 10 || n <= 0) {
        printf("Jumlah angka tidak valid. Masukkan antara 1 sampai 10.\n");
        return 1;
    }

    printf("Masukkan %d angka:\n", n);
    for (int i = 0; i < n; i++) {
        printf("Angka ke-%d: ", i + 1);
        scanf("%d", &arr[i]);
    }

    printf("\n");
    puts("Array sebelum");
    tampilkanArray(arr, n);

    for (int i = 0; i < n - 1; i++) {
        int min_idx = i;

        for (int j = i + 1; j < n; j++) {
            if (arr[j] < arr[min_idx]) {
                min_idx = j;
            }
        }

        if (min_idx != i) {
            int temp = arr[i];
            arr[i] = arr[min_idx];
            arr[min_idx] = temp; 
        }

        printf("Index [%d](%d) ditukar posisi dengan [%d](%d)\n", i, arr[min_idx], min_idx, arr[i]);
        printf("Iterasi [%d]: \n", i + 1);
        tampilkanArray(arr, n);
    }

    printf("\nArray setelah diurutkan: ");
    tampilkanArray(arr, n);

    return 0;
}
