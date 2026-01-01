#include <stdio.h>

int main (void)
{
	int jumlah;
	printf("Masukan Baris Bintang = ");
	scanf("%i",&jumlah);
	printf("\n");
	
	for(int i = 1; i<=jumlah; i++) {
		for(int j = 1; j<=i; j++) {
			printf("* ");
		}
		printf("\n");
	}
	return 0;
}
