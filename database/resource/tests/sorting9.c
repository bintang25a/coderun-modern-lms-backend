#include <stdio.h>
#include <stdio.h>

void selectionSort(int arr[], int n)
 {
    int i, j, minIdx, temp;

    for (i = 0; i < n - 1; i++) {
        minIdx = i;
        for (j = i + 1; j < n; j++) {
            if (arr[j] < arr[minIdx]) {
                minIdx = j;
            }
        }
        temp = arr[minIdx];
        arr[minIdx] = arr[i];
        arr[i] = temp;

        printf("Iterasi %d: ", i + 1);
        for (int k = 0; k < n; k++) {
            printf("%d ", arr[k]);
        }
        printf("\n");
    }
}

int main()
 {
    int n, *arr;

    printf("Masukkan jumlah elemen: ");
    scanf("%d", &n);

    arr = (int *)malloc(n * sizeof(int));
    if (arr == NULL) {
        printf("Memori tidak cukup!\n");
        return 1;
    }

    printf("Masukkan elemen array:\n");
    for (int i = 0; i < n; i++) {
        printf("Elemen [%d]: ", i);
        scanf("%d", &arr[i]);
    }


    selectionSort(arr, n);


    printf("Array yang sudah terurut: ");
    for (int i = 0; i < n; i++) {
        printf("%d ", arr[i]);
    }
    printf("\n");

    free(arr);

    return 0;
}
