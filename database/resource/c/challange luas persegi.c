#include <stdio.h>

int main() {
	int sisi, luas;
	
	printf("input sisi: ");
	scanf("%d", &sisi);
	
	luas = sisi*sisi;
	printf("luas persegi dengan sisi %d adalah %d", sisi, luas);
}