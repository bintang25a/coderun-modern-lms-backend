#include <stdio.h>





int main() {
    float x[5], jum = 0; 
    int i;
    
    for (i = 0; i < 5; i++) {
    	printf("X[%d]: ", i);
		scanf("%f", &x[i]);
		jum += x[i];
	}
        

    printf("Jumlah = %.1f\n", jum);

    return 0;
}