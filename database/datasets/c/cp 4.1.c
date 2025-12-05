#include <stdio.h>

float fak(float n) {
    if (n < 2) {
        return 1;
    } else {
        return n * fak(n - 1);
    }
}
int main() {
    float n;
    printf("Masukkan n = ");
    scanf("%f", &n);
    printf("\n\n");
    printf(" Faktorial = %0.f ", fak(n));
    getchar(); 
    return 0;
}