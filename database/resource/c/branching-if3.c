#include <stdio.h>

int main() {
	int angka;
	
	printf("Masukan angka: ");
	scanf("%d", &angka);
	
	if (angka < 5) {
		printf("angka %d kurang dari 5", angka);
	}
}
