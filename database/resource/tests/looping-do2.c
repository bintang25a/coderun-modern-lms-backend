#include <stdio.h>
main() {
	int bil=1;
	
	do {
		if(bil >= 6)
		break;
		printf("%i",bil);
	} while(bil++);
	
	printf("\n");
}