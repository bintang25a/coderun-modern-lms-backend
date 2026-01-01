#include <stdio.h>

int main() {
	int angka;
	
	printf("Masukan angka: ");
	scanf("%d", &angka);
	
	if (angka < 5) {
		printf("angka %d kurang dari 5", angka);
	}
}
//
//#include <stdio.h>
//
//int main() {
//	int bil;
//	
//	
//	
//	printf("masukan bilangan: ");
//	scanf("%d", &bil);
//	
//	if (bil > 0) {
//		printf("bilangan positif");
//	}
//	else if (bil == 0) {
//		printf("bilangan netral");
//	}
//	else {
//		printf("bilangan negatif");
//	}
//	
//	return 0;
//}