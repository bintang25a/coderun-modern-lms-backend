#include <stdio.h>

int main() {

	// Meminta input dari pengguna
	printf("===== POSITIF/NEGATIF =====");
	printf("Masukkan sebuah bilangan: ");
	scanf("%d", &bilangan);
	
	// Meminta apakah bilangan positif, negatif, atau nol
	if (bilangan > 0) {
		printf("Bilangan %d adalah positif.\n", bilangan);
	} else if (bilangan < 0) {
		printf("Bilangan %d adalah negatif.\n", bilangan);
	} else {
		printf("Bilangan adalah nol.\n");
	}
	
	return 0;
}