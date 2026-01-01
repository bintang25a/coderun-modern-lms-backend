#include <stdio.h>

main() {
	
	typedef char C;
	C karakter1, karakter2, *pKarakter;
	
	karakter1 = 'A';
	karakter2 = 'B';
	pKarakter = &karakter1;
	
	printf(" Isi variabel karakter1: %c\n", karakter1);
	printf(" Isi variabel karakter2: %c\n", karakter2);
	printf(" Isi variabel karakter1 melalui variabel pKarakter: %c\n", *pKarakter);
}