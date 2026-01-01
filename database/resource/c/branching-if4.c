#include<stdio.h>

int main() {
	int dd, mm, yy, tahun_ini, usia;
	
	printf("tanggal lahir [dd - mm - yy] = ");
	scanf("%d-%d-%d", &dd, &mm, &yy);
	
	printf("tahun sekarang [yyyy] = ");
	scanf("%d", &tahun_ini);
	
	tahun_ini = tahun_ini % 100;
	usia = tahun_ini - yy;
	
	if (usia < 0) {
		usia = usia + 100;
	}
	
	printf("Usia = %d", usia);
}