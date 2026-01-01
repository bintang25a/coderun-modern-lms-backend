#include <stdio.h>
main () {
	int n;
	printf ("Masukan angka = "); scanf ("%d", &n);
	
	if (n==0) {
		printf ("\nAngka tersebut adalah nol dan bernilai netral");
	}
	if (n<0) {
		printf ("\nAngka tersebut adalah %d dan bernilai negatif", n);
	}
	else n>0; {
		printf ("\nAngka tersebut adalah %d dan bernilai positif", n);
	}
	printf ("\n\n");
}
