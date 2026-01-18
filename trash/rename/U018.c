#include <stdio.h>

int main() {
	int kodePosBosss;
	
	printf("Masukan Kode pos kelurahan yang ingin dicari: ");
	scanf("%d", &kodePosBosss);
	
	if (kodePosBosss == 10210) {
		printf("\n%d merupakan kode pos Kelurahan Bendungan Hilir", kodePosBosss);
	}
	else if (kodePosBosss == 10220) {
		printf("\n%d merupakan kode pos Kelurahan Karet Tengsin", kodePosBosss);
	}
	else if (kodePosBosss == 10230) {
		printf("\n%d merupakan kode pos Kelurahan Kebon Melati", kodePosBosss);
	}
	else if (kodePosBosss == 10240) {
		printf("\n%d merupakan kode pos Kelurahan Kebon Kacang", kodePosBosss);
	}
	else if (kodePosBosss == 10250) {
		printf("\n%d merupakan kode pos Kelurahan Kampung Bali", kodePosBosss);
	}
	else if (kodePosBosss == 10260) {
		printf("\n%d merupakan kode pos Kelurahan Petamburan", kodePosBosss);
	}
	else if (kodePosBosss == 10270) {
		printf("\n%d merupakan kode pos Kelurahan Gelora", kodePosBosss);
	}
	else {
		printf("\n%d KODE POS TERSEBUT TIDAK TERDAFTAR!!", kodePosBosss);
	}
}