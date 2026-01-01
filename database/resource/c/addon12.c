#include <stdio.h>

int penjumlahan(int a, int b){
	return a+b;
}

int pengurangan(int a, int b){
	return a-b;
}

int perkalian(int a, int b){
	return a*b;
}

int pembagian(int a, int b){
	return a/b;
}

int modulus(int a, int b){
	return a%b;
}

int main(){
	char simbol, pilih;
	int a, b;
	
	printf("SIMPLE CALCULATOR PROGRAM");
	do{
		printf("\nChoose Menu: \n");
		printf("[+] Addition \n[-] Subtraction \n[*] Multiplication \n[/] Division \n[%%] Modulus \n[N] Exit Program\n");
		printf("Enter Operation Symbol: ");
		scanf(" %c", &simbol);
		
		
		
		switch(simbol){
			case '+' : 
				printf("Enter 1st Number : ");
				scanf("%d", &a);
				printf("Enter 2nd Number : ");
				scanf("%d", &b);
				printf("%d + %d = %d", a, b, penjumlahan(a,b));
				break;
			case '-' : 
				printf("Enter 1st Number  : ");
				scanf("%d", &a);
				printf("Enter 2nd Number : ");
				scanf("%d", &b);
				printf("%d - %d = %d", a, b, pengurangan(a,b));
				break;
			case '*' : 
				printf("Enter 1st Number  : ");
				scanf("%d", &a);
				printf("Enter 2nd Number : ");
				scanf("%d", &b);
				printf("%d * %d = %d", a, b, perkalian(a,b));
				break;
			case '/' : 
				printf("Enter 1st Number  : ");
				scanf("%d", &a);
				printf("Enter 2nd Number : ");
				scanf("%d", &b);
				printf("%d / %d = %d", a, b, pembagian(a,b));
				break;
			case '%' : 
				printf("Enter 1st Number : ");
				scanf("%d", &a);
				printf("Enter 2nd Number : ");
				scanf("%d", &b);
				printf("%d %% %d = %d", a, b, modulus(a,b));
				break;
			case 'N' :
				goto keluar;
				break;
			default : printf("Invalid Input Symbol!!");
		}
		
		printf("\nContinue Calculating? [Y/N]: ");
		scanf(" %c", &simbol);
		
		if (simbol!='Y'&&simbol!='N'){
			printf("Invalid Input Symbol!!\n\n");
		}
		
	} while (simbol!='N');
	
	keluar:
	printf("Exit Program!!");
	
	
	
}