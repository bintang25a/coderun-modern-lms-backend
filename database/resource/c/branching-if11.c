#include <stdio.h>

int main() {
	
	
	// Meminta input dari pengguna
	printf("Masukkan sebuah bilangan: ");
	scanf("%f", &bilangan);
	
	// Meminta apakah bilangan positif, negatif, atau nol
	if (bilangan > 0) {
		printf("Bilangan %.2f adalah positif.\n", bilangan);
	} else if (bilangan < 0) {
		printf("Bilangan %.2f adalah negatif.\n", bilangan);
	} else {
		printf("Bilangan adalah nol.\n");
	}
	
	return 0;
}