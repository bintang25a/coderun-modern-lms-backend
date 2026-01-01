#include <stdio.h>

int persegi(int sisi) {
	int luas;
	luas = sisi*sisi;
	return luas;
	          
}

int main() {
    int a;

    printf("Panjang sisi persegi = ");
    scanf("%d", &a);

    printf("Luas persegi dengan sisi %d adalah %d\n", a, persegi(a));

    return 0;
}