#include <stdio.h>

int pertambahan(int a, int b) {
	int c = a+b;
	return c;
}

int main() {
	int q=5;
	int p=9;
	
	printf("%d", pertambahan(q, p));
}