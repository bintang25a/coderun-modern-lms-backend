#include <stdio.h>

int main(){
	int pos;
	
	printf("Kode Pos Kelurahan Tanah Abang\n\n");
	
	printf("10220 = Karet Tengsin\n");
	printf("10230 = Kebon Melati\n");
	printf("10240 = Kebon Kacang\n");
	printf("10250 = Kampung Bali\n");
	printf("10260 = Petamburan\n");
	printf("10270 = Gelora\n\n");
	
	printf("Masukkan Kode pos : ");
	scanf("%d",&pos);
	
	switch(pos){
		case 10210 :
			printf("\nBendungan Hilir");
			break;
		case 10220 :
			printf("\nKaret Tengsin");
			break;
		case 10230 :
			printf("\nKebon Melati");
			break;
		case 10240 :
			printf("\nKebon Kacang");
			break;
		case 10250 :
			printf("\nKampung Bali");
			break;
		case 10260 :
			printf("\nPetamburan");
			break;
		case 10270 :
			printf("\nGelora");
			break;
		default :
			printf("Kode yang kamu masukkan salah, haraf coba lagi.\n");
			break;
	}
	return 0;
}