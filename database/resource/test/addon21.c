#include <stdio.h>

//Fungsi untuk penjumlahan
int penjumlahan (int a, int b) {
	return a + b;
}

//Fungsi untuk pengurangan 
int pengurangan (int a, int b) {
	return a - b;
}

//Fungsi untuk perkalian 
int perkalian (int a, int b) {
	return a * b;
}

//Fungsi untuk pembagian
float pembagian (float a, float b) {
	if(b == 0) {
		printf("Tidak dapat membagi dengan nol.\n");
		return 0;
    } else {
    	return a / b;
	}
}

int main() {
	int  a, b, pilihan;
	float hasil;
	
	printf("Masukkan bilangan A: ");
	scanf("%d", &a);
	printf("Masukkan bilangan B: ");
	scanf("%d", &b);
	
	printf("\nPilihan operasi:\n");
	printf("[1] +\n");
	printf("[2] -\n");
	printf("[3] *\n");
	printf("[4] /\n");
	printf("Masukkan pilihan: ");
	scanf("%d", &pilihan);
	
	switch (pilihan) {
		case 1:
			hasil = penjumlahan (a, b);
			printf("Hasil %d + %d = %.f\n", a, b, hasil);
			break;
		case 2:
			hasil = pengurangan (a, b);
			printf("Hasil %d - %d = %.f\n", a, b, hasil);
			break;
		case 3:
			hasil = perkalian (a, b);
			printf("Hasil %d * %d = %.f\n", a, b, hasil);
			break;
		case 4:
			hasil = pembagian (a, b);
			printf("Hasil %d / %d = %.f\n", a, b, hasil);
			break;
		default:
			printf("Pilihan tidak valid.\n");
	}
	
	return 0;
}