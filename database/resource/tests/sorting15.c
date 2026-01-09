#include<stdio.h>
main() {
//	Buat Bubble Sort agar bisa mengurutkan secara descending dan menampilkan proses dan hasil iterasi
    int i, j, n, k, temp;

    	printf("Masukkan banyak array: "); scanf("%d", &n);

    int array[n];
    
    for (i = 0; i < n; i++) {
        printf("Insert A[%d]: ", i);  scanf("%d", &array[i]);
    }

    	printf("Array acak: ");
    for (i = 0; i < n; i++) {
        printf("%d ", array[i]);
    }

    	printf("\n\n");
    
    for (i = 1; i <= n - 1; i++) {  
        for (j = 1; j <= n - 1; j++) {  
        
            if (array[j] > array[j - 1]) {  
                temp = array[j];
                array[j] = array[j - 1];
                array[j - 1] = temp;
            }
    for (k = 0; k < n; k++) {
        printf("%d ", array[k]);
    }
        printf("\n");
}
            
        printf("Hasil iterasi %d: ", i); 
    for (k = 0; k < n; k++) {
        printf("%d ", array[k]);
    }
        printf("\n\n");
}
    
    
    // Tampilkan hasil akhir
    	printf("\nArray Acak: ");
    for (i = 0; i < n; i++) {
        printf("%d ", array[i]);
    }
    	printf("\n");

}


