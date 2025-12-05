#include <stdio.h>

int main() {
	int bil;
	
	printf("masukan bilangan: ");
	scanf("%d", &bil);
	
	if (bil<10) {
		printf("%d kurang dari 10", bil);
	}
	else if (bil>10) {
		printf("%d lebih dari 10", bil);
	}
	else {
		printf("%d sama dengan 10", bil);
	}
}