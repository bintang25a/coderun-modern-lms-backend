#include <stdio.h>

int main() {
	int angka;
	
	printf("masukan angka 1-5: ");
	scanf("%d", &angka);
	
	switch (angka) {
		case 1: printf("%i dibaca satu", angka); break;
		case 2: printf("%i dibaca duaa", angka); break;
		case 3: printf("%i dibaca tiga", angka); break;
		case 4: printf("%i dibaca mpat", angka); break;
		case 5: printf("%i dibaca lima", angka); break;
		default: printf("Maaf pilihan anda salah"); break;
	}
	
	return 0;
}