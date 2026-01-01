#include <stdio.h>

int main() {
	int bil;
	
	for (bil=0; bil<10; bil++) {
		if (bil == 6) {
			continue;
		}
		printf("%d", bil);
	}
	
	printf("\n");
	return 0;
}