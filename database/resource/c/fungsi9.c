#include <stdio.h>

int pangkat(int x, int y) {
    if (y == 0) {
        return 1;
    } else {
        return x * pangkat(x, y - 1);
    }
}

int main() {
	int a, b;
	printf("input angka = ");scanf("%d", &a);
	printf("input pangkat = ");scanf("%d", &b);
    printf("Menghitung %d pangkat %d: %d", a, b, pangkat(a, b));
    return 0;
}