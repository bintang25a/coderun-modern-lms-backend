#include <stdio.h>
 
 
 
 
int main() {
	int i,batas;
	printf("table perkalian Lima \n");
	printf("input batas perkalian = ");
	scanf("%d",&batas);

	for(i=1; i<=batas; i++) {
		printf("5 * %d = %d\n",i ,5*i);
	}
}