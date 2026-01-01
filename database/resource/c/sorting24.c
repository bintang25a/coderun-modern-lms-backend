#include <stdio.h>
main(){
    int i,j,n,temp;
    printf("Masukkan banyak array: ");
    scanf("%d", &n);
    int array[n];
    for(i=0; i<n; i++){
        printf("Insert A[%d]: ", i);
        scanf("%d",&array[i]);
    }
    printf("Array acak: ");
    for(i=0; i<n; i++) {
        printf ("%d ", array[i]);
    }
    printf("\n");
     for (i = 1; i < n; i++) {
        temp = array[i];
        j = i - 1;
        printf("Key: %d\n", temp);
        while (j >= 0 && array[j] < temp) {
            array[j + 1] = array[j];
            j--;
        }
        array[j + 1] = temp;
        printf("Hasil iterasi %d: ", i);
        for (int k = 0; k < n; k++) {
            printf("%d ", array[k]);
        }
        printf("\n");
    }
    printf("\nArray Urut: ");
    for (i = 0; i < n; i++) {
        printf("%d ", array[i]);
    }
    printf("\n---------------------------------------\n");
    
   





 

}
