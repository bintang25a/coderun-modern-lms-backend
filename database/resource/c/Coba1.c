#include <stdio.h>

int main() {
	int i,j=33,bil,jumlah=0;
	
	for (i=1; i<=j; i++) {
		jumlah = jumlah + i*3;
		printf("%i ", i*3);
	}
	printf ("\n jumlah seluruh angka = %d", jumlah);
	return 0;
}