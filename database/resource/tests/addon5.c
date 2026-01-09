#include<stdio.h>

int main() {
	
	union {
		
		float luas, keliling;
	} persegi;
	
	float sisi;
	
	printf(" Panjang sisi persegi = "); scanf("%f", &sisi);
	
	persegi.luas = sisi * sisi;
	printf(" Luas persegi = %0.f\n", persegi.luas);
	
	persegi.keliling = 4 * sisi;
	printf(" Keliling persegi = %0.f\n", persegi.keliling);
	
	return 0;
}
