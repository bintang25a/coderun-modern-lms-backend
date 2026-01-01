#include <stdio.h>
float a, b; float c;
int penjumlahan () {
	c = a + b;
	return c;
}
int pengurangan () {
	c = a - b;
	return c;
}
int perkalian () {
	c = a*b;
	return c;
}
float pembagian () {
	c = a/b;
	return c;
}
main (){
	int nomor;
	printf ("Masukan Bilangan a = "); scanf ("%f", &a); 
	printf ("Masukan Bilangan b = "); scanf ("%f", &b);
	printf ("\n1. Penjumlahan\n2. Pengurangan\n3. Perkalian\n4. Pembagian\n\n");
	printf ("Pilih Operasi Matematika = "); scanf ("%d",&nomor);
	
	switch(nomor)
	{
		case 1: printf ("Hasil = %d", penjumlahan(a,b));
		break;
		case 2: printf ("Hasil = %d", pengurangan(a,b));
		break;
		case 3: printf ("Hasil = %d", perkalian(a,b));
		break;
		case 4: printf ("Hasil = %.2f", pembagian(a,b));
		break;
		default: printf ("Pilihan tidak tersedia");
	}
}
