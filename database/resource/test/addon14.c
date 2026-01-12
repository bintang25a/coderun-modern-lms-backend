#include <stdio.h>

int main(){
	int tinggi;
	
	//Meminta pengguna memasukkan tinggi segitiga
	printf("Masukkan tinggi segitiga: ");
	scanf("%d", &tinggi);
	
	//Membuat segitiga siku siku
	for (int i = 1; i<= tinggi; i++){
		for (int j = 1; j <= i; j++){
			printf("*");
		}
		printf("\n");
	}
}