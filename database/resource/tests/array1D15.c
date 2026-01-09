#include <stdio.h>

main(){
	int angka[8];
	int i, jumlah = 0;
	float rata_rata;
	
	printf("Masukan 8 angka:\n");
	for (i = 0; i < 8; i ++) {
		scanf("%d", &angka[i]);
		jumlah += angka[i];
	} 
	
	rata_rata = (float)jumlah / 8;
	
	printf("Rata-rata dari 8 angka adalah: %.2f\n", rata_rata);
	
	return 0;
}
	
	