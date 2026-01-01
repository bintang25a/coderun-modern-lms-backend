#include <stdio.h>
main ()
{
  printf("Program Looping Output Pola Bangun Datar\nDina Andriani\n");
  printf("\n==========================================================\n\n");
  int A,i,j,k,l;
  printf("Masukan Jumlah (*) Yang Diinginkan = "); scanf("%d",&A);
  
  printf("\n==========================================================\n\n");
 	//Persegi
 	printf ("Pola Persegi: \n");
 	for (i=1; i<=A; i++){
 	for (j=1; j<=A; j++){
 	printf ("* ");
	}
	printf("\n");
	}
	
printf("\n==========================================================\n\n");
	//Persegi Panjang
	printf ("Pola Persegi Panjang: \n");
 	for (i=2; i<=A; i++){
 	for (j=0; j<=A; j++){
 	printf ("* ");
	}
	printf("\n");
	}
	
printf("\n==========================================================\n\n");
	//Segitiga Siku - Siku
 	printf ("Pola Segitiga Siku - Siku: \n");
 	for (i=1; i<=A; i++){
 	for (j=1; j<=i; j++){
	printf ("* ");
	}
	printf("\n");
	}
	
printf("\n==========================================================\n\n");
 	// Piramida
 	printf ("Pola Piramida: \n");
 	for(i=1; i<=A; i++) {
    for(j=1; j<=A-i; j++) {
    printf(" ");
    }
    for(k=1; k<=i; k++) {
    printf("* ");
    }
    printf("\n");
	}
	
printf("\n==========================================================\n\n");
	//Piramida Terbalik
 	printf ("Pola Piramida Terbalik: \n");
	for(i=0; i<A; i++) {
    for(j=1; j<=i; j++) {
    printf(" ");
    }
    for(k=1; k<=A-i; k++){
	printf("* ");
    }
    printf("\n");
	}

printf("\n==========================================================\n\n");
	//Jajargenjang
 	printf ("Pola Jajargenjang: \n");	
	for (i=1; i<=A; i++) { 
	for (j=i; j<A; j++) {
	printf (" "); 
	}
	for (j=1; j<=A+1 ;j++) { 
	printf ("* ");
	}
	printf("\n");
	}

printf("\n==========================================================\n\n");
	//Trapesium
 	printf ("Pola Trapesium: \n");	
	for (i=1; i<=A; i++) { 
	for (j=i; j<A; j++) {
	printf (" "); 
	}
    for(k=1; k<=i+3; k++){
	printf("* ");
    }
    printf("\n");
	}

printf("\n==========================================================\n\n");
printf ("Udah se - kreatif itu dapet 100 dong ni :> \nya gak? \n 1. Ya\n 2. Tidak \n"); 
kembali:
printf ("(1/2) = "); scanf ("%d", &l);
  switch (l){
    case 1 : printf ("\n\nTrimakasihhh!! :> \n\n");
    		for(i=10/2; i<=10; i+=2)
    {
        for(j=1; j<10-i; j+=2)
        {
            printf(" ");
        }
        for(j=1; j<=i; j++)
        {
            printf("*");
        }
        for(j=1; j<=10-i; j++)
        {
            printf(" ");
        }
        for(j=1; j<=i; j++)
        {
            printf("*");
        }
        printf("\n");
    }
    	for(i=10; i>=1; i--)
    	{
        for(j=i; j<10; j++)
        {
            printf(" ");
        }
        for(j=1; j<=(i*2)-1; j++)
        {
            printf("*");
        }
        printf("\n");
    }
    // Iya kalo coding lope yg ini nyontek google tapi sisanya gk, peace :>
    		break;
    case 2 : printf ("\nDih, apa coba gitu >:[ \n\nPilih lagi \n");
    		goto kembali;
    		break;
    default : printf ("\nGk ada cuma 1 atau 2 -_- \n\nPilih Lagi \n");
    		goto kembali;
    		break;
	}
}

