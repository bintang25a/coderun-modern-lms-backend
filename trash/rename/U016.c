#include <stdio.h>

int main (){
	int kelurahan;
	
	printf( " Masukan Kode Pos Kelurahan : " );
    kelurahan == scanf( " %d ", &kelurahan );
	
	if ( kelurahan == 10210 ){
		printf( " Kode Pos %d Bendungan Hilir ", kelurahan ); 
	}
	else if ( kelurahan == 10220 ){
		printf( " Kode Pos %d Karet Tengsin ", kelurahan );
	}
	else if ( kelurahan == 10230 ){
		printf( " Kode Pos %d Kebon Melati ", kelurahan ); 
	}
	else if ( kelurahan == 10240 ){
		printf( " Kode Pos %d Kebon Kacang ", kelurahan );
    }
    else if ( kelurahan == 10250 ){
		printf( " Kode Pos %d Kampung Bali ", kelurahan ); 
	}
	else if ( kelurahan == 10260 ){
		printf( " Kode Pos %d Petamburan ", kelurahan );
    }
    else if ( kelurahan == 10270 ){
		printf( " Kode Pos %d Gelora ", kelurahan );
    }
    else {
    	printf( " SALAH\n" );
    	printf( " Masukan Kode Pos Kelurahan : " );
    kelurahan ==scanf( " %d", &kelurahan );	
	}
    
return 0;

}
