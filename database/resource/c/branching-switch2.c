#include <stdio.h>

int main() {
	int nilai;
	char grade;
	
	scanf("%d", &nilai);
	switch(nilai) {
		case 0 ... 44: grade = 'E'; break;
		case 45 ... 55: grade = 'D'; break;
		case 56 ... 67: grade = 'C'; break;
		case 68 ... 79: grade = 'B'; break;
		case 80 ... 100: grade = 'A'; break;
	}
	
	printf ("grade = %c", grade);
	return 0;
}