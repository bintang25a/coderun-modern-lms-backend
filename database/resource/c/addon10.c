#include <stdio.h>

main() {
	int a, b, c;
	
	printf("a = ");
	scanf("%i", &a);
	
	printf("b = ");
	scanf("%d", &b);
	
	a+=1;
	c=a+b;
	
	printf("nilai a setelah ditambah 1 = %i\n", a);
	printf("%i + %i = %i", a, b, c);	
}
	