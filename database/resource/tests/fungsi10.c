#include<stdio.h>

int fib(int n) {
    if (n < 2) {
        return 1;
    } else {
        return fib(n - 1) + fib(n - 2);
    }
}

int main() {
    int n;
    printf("Masukkan n = ");
    scanf("%d", &n);
    printf("\n\n");
    printf(" Fibonacci = %d ", fib(n));
    getchar(); 
    return 0;
}