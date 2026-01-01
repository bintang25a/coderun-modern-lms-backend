#include <stdio.h>

int c;

int penjumlahan(int a, int b) {
	return a+b;
}

int pengurangan(int a, int b) {
	return a-b;
}

int perkalian(int a, int b) {
	return a*b;
}

float pembagian(int a, int b) {
	return a/b;
}

int main() {
	int a, b, pilih;
	printf("masukan bilangan A: ");
	scanf("%d", &a);
	printf("masukan bilangan B: ");
	scanf("%d", &b);
	
	printf("\nPili:\n[1] +\n[2] -\n[3] *\n[4] /\nMasukan pilihan: ");
	scanf("%d", &pilih);
	
	switch(pilih) {
		case 1: printf("Hasil %d + %d = %d", a, b, penjumlahan(a, b)); break;
		case 2: printf("Hasil %d - %d = %d", a, b, pengurangan(a, b)); break;
		case 3: printf("Hasil %d * %d = %d", a, b, perkalian(a, b)); break;
		case 4: printf("Hasil %d / %d = %f", a, b, pembagian(a, b)); break;
	}
}