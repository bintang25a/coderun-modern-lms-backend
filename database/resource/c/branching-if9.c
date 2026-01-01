#include <stdio.h>

int main() {
    int num;

    printf("Masukkan sebuah bilangan: ");
    scanf("%d", &num);

    // Tambahkan pernyataan if di sini untuk menentukan apakah num positif, negatif, atau nol
    if (num>0){
    	printf("Bilangan tersebut adalah positif.\n");
    	}else if(num<0){
    	printf("Bilangan tersebut adalah negatif.\n");
    	}else{
    	printf("Bilangan tersebut adalah nol.\n");
	}



    return 0;
}

