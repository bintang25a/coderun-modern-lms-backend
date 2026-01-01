#include <stdio.h>

int perulanganFor() {
	
	int i, batas;
	printf("Tabel perkalian Lima \n");
	printf("Input batas perkalian = ");
	scanf("%d",&batas);
	
	for(i=1; i<=batas; i++) {
		printf("5 * %d = %d\n", i, 5*i);
	}
	
	return 0;
}

int perulanganWhile() {
	
	int bilangan, jumlah, n;
	float rerata;
	jumlah = 0;
	n = 0;
	
	printf("Masukan Bilangan = ");
	scanf("%d", &bilangan);
	
	while (bilangan != 0) {
		jumlah+=bilangan;
		n++;
		
		printf("\nMasukan Bilangan = ");
		scanf("%d", &bilangan);
	}
	
	printf("\n");
	rerata = jumlah/n;
	
	printf("rerata = %.1f\n", rerata);
	return 0;
}

int main() {
	
	//perulanganFor();
	perulanganWhile();
}