#include<stdio.h>

	struct {
		
		float jariJari;
		float keliling;
		float luas;
	} lingkaran;

float luas(float jari) {
	
	return 2*3.1416*lingkaran.jariJari;
}

main() {
	
	printf("Jari-jari lingkaran = ");
	scanf("%f", &lingkaran.jariJari);

	lingkaran.keliling=luas(lingkaran.jariJari);
	lingkaran.luas=3.1416*lingkaran.jariJari*lingkaran.jariJari;

	printf("Keliling Lingkaran = %f \n", lingkaran.keliling);
	printf("Luas Lingkaran = %f\n", lingkaran.luas);
}
