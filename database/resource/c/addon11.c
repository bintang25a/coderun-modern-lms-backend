#include <stdio.h>

main() {
	int a, b, c;
	kembali:
		printf("\nMasukan angka: ");
		scanf("%d", &a);
		
		b=a%2;
		
		printf("%d mod 2 = %d\n", a, b);
		printf("hitung kembali ? [1/0] : ");
		scanf("%d", &c);
		
		if(c==1) goto kembali;
}