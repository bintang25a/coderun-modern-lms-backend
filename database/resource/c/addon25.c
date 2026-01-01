#include<stdio.h>
main(){
	int i, j, k, jumlah, pilihan;
	float balik;
	printf("Selamat Datang di Program Membuat Segitiga Bintang Apridho Fuadil Hadid");
	printf("\n================================================\n");
	printf("Berikut segitiga bintang yang tersedia: ");
	printf("\n1. Segitiga Siku-Siku Kiri\n");
	printf("\n2. Segitiga Siku-Siku Kanan\n");
	printf("\n3. Segitiga Piramida\n");
	printf("\n4. Segitiga Siku-Siku Kiri Terbalik\n");
	printf("\n5. Segitiga Siku-Siku Kanan Terbalik\n");
	printf("\n6. Segitiga Piramida Terbalik\n");
	
	printf("================================================");
	kembali:
	
	printf("\nMasukkan pilihan anda (1-6): ");
	scanf("%d" ,&pilihan);
	
	switch (pilihan) {
        case 1:
            printf("\nMasukkan banyak baris bintang segitiga siku-siku kiri: ");
            scanf("%d" ,&jumlah);
		for(i = 1; i <= jumlah; i++){ //loop baris
		for(j = 1; j <= i; j++){ //loop kolom
			printf("*");
		}
			printf("\n"); //pindah baris
		}
            break;
        case 2:
            printf("\nMasukkan banyak baris bintang segitiga siku-siku kanan: ");
            scanf("%d" ,&jumlah);
		for (i = 1; i <= jumlah; i++) {  //loop baris
        for (j = 1; j <= jumlah - i; j++) { //loop spasi
            printf(" ");
        }
        for (k = 1; k <= i; k++) { //loop bintang
            printf("*");
        }
        	printf("\n"); //pindah baris
    	}
            break;
        case 3:
            printf("\nMasukkan banyak baris segitiga piramida: ");
  			scanf("%d",&jumlah);
 
  			printf("\n");
 
  		for(i = 1; i <=jumlah; i++) { //loop baris
    	for(j = 1; j <=jumlah-i; j++) { //loop spasi
      		printf(" ");
    	}
 
    	for(k = 1; k <= i; k++) { //loop bintang
      		printf("* ");
    	}
    		printf("\n"); //pindah baris
  		}
            break;
            case 4:
            printf("Masukkan banyak baris bintang segitiga siku-siku kiri terbalik: ");
            scanf("%d" ,&jumlah);
		for (i = jumlah; i >= 1; i--) { //loop baris
        for (j = 1; j <= jumlah - i; j++) { //loop spasi
            printf(" ");
        }
        for (k = 1; k <= i; k++) { //loop bintang
            printf("*");
        }
        	printf("\n");// pindah baris
    	}
            break;
            case 5:
            printf("\nMasukkan banyak baris bintang segitiga siku-siku kanan terbalik: ");
            scanf("%d" ,&jumlah);
        for (i = jumlah; i >= 1; i--) { //loop baris
        for (j = 1; j <= i; j++) { //loop bintang
            printf("*");
        
        }
    
        	printf("\n");//pindah baris
    	}
    		break;
    		case 6:
            printf("\nMasukkan banyak baris bintang segitiga piramida terbalik: ");
  			scanf("%d",&jumlah);
 
  			printf("\n");
 
  		for(i = jumlah; i >= 1; i--) { //loop baris
 
    	for(j = 1; j <=jumlah-i; j++) { //loop spasi
      		printf(" ");
    	}
 
    	for(k = 1; k <= i; k++) { //loop bintang
      		printf("* ");
    	}
    		printf("\n"); //pindah baris
  		}
            break;
        default:
            printf("\nMaaf tidak tersedia, mohon untuk mengisi sesuai dengan apa yang tersedia!\n");
    	}

	//go to code
	printf("\n==========Ingin buat segitiga bingtang lagi?========== (1/0): ");
	scanf("%f" ,&balik);
	if(balik==1)
	goto kembali;
	else
	printf("\n\n Program Selesai \n\n");
	
}
