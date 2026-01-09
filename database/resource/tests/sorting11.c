#include <stdio.h>
//Apridho_Fuadil_Hadid[24040700011]
//Tugas1SDA_Selection_Sort
void printArray(int arr[], int size) {
	int i;
    for (i = 0; i < size; i++) {
        printf("%d ", arr[i]);
    }
    printf("\n");
}

void selectionSort(int arr[], int n) {
    int i, j, min_idx;
    
    for (i = 0; i < n-1; i++) {
        min_idx = i;
        for (j = i+1; j < n; j++) {
            if (arr[j] < arr[min_idx]) {
                min_idx = j;
            }
        }
        
        int temp = arr[min_idx];
        arr[min_idx] = arr[i];
        arr[i] = temp;
        
        printf("Iterasi %d: ", i+1);
        printArray(arr, n);
    }
}

int main() {
    int n, i;
    float balik;
    
	kembali:
    printf("\nMasukkan jumlah elemen array: ");
    scanf("%d", &n);
    
    int arr[n];  
    
    printf("\nMasukkan %d elemen array:\n", n);
    for (i = 0; i < n; i++) {
        scanf("%d", &arr[i]);
    }
    
    printf("\nArray sebelum sorting: ");
    printArray(arr, n);
    
    printf("\nProses Selection Sort:\n");
    selectionSort(arr, n);
    
    printf("\nArray setelah sorting: ");
    printArray(arr, n);
    
   printf("\n==========Ingin mencoba kembali?========== (1/0): ");
	scanf("%f" ,&balik);
	if(balik==1)
	goto kembali;
	else
	printf("\n\n Program Selesai \n\n");
    return 0;
}
