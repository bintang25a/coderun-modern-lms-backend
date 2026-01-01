#include <stdio.h>

int main(){
    float x[] = {1, 2, 3, 4, 5, 6, 7, 8, 9}; 
    float jum = 0;  
    float rata_rata;
    int i, n = 8;

    //Looping buat Input
    for (i = 0; i < n; i++) {
        printf("X[%d]: ", i);
        scanf("%f", &x[i]);
    }
    //Looping Menghitung 
    for (i = 0; i < n; i++) {
        jum += x[i];
    }
    //menghitung rata-rata
    rata_rata = (float) jum / n;

    //Output
    printf("Jumlah = %.2f\n", jum);
    printf("Rata-rata = %.3f\n", rata_rata);

    return 0;
}