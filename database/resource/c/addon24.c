#include<stdio.h>
int main() {
	int i, j, n;
	
	// menginput jumlah baris segitiga
	printf("masukkan jumlah baris segitiga: ");
	scanf("%d", &n);
	
	// looping untuk setiap baris
	for(i = n; i >= 1; i--) {
		// looping untuk mencetak bintang
		for(j = 1; j <= i; j++) {
			printf("* ");
		}
		// pindah ke baris berikutnya
		printf("\n");
	}
	return 0;
}