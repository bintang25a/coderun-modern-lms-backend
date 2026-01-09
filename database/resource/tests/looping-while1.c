#include <stdio.h>

int main(){
	int bil, jumlah, n;
	float rerata;
	jumlah=0;
	n=0;
	
	printf("masukkan bilangan = ");
	scanf("%d",&bil);
	
	while(bil != 0){
		jumlah+=bil;
		n++;
		scanf("%d",&bil);
	}
	
	printf("\n");
	rerata=jumlah/n;

	printf("rerata = %.1f\n", rerata);
}