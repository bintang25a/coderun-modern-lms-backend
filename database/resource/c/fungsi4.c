#include <stdio.h>

float tambah(float a, float b);

float c;

int main() {
    printf("Penggunaan Variable Global dalam C\n");
    tambah(5.0, 11.0);
    printf("%.3f\n", c);
    return 0;
}

float tambah(float a, float b) {
    c = a + b;
    return c;
}