#include <stdio.h>
#define MAX 5
#define true 1
#define false 0

char stack[MAX];
int top=0;
int isEmpty();
int isFull();
void push();
void pop();
void baca();
void clear();

void push() {
	char add;
	
	if (isFull() != true) {
		
		printf(" Masukan data dalam tumpukan : ");
		scanf("%s", &add); //5
		
		stack[top] = add; // stack[0] = 5
		top++; //0+1 = 1
	}
	else {
		
		printf(" Tumpukan mencapai batas ketinggian maksimal\n");
	}
}

void pop() {
	int i;
	
	if (isEmpty() != true) {
		
		printf(" Tumpukan yang diambil --> %c\n", stack[top-1]); //top - 1 = 1-1 = 0
		
		top--; // top-1 = 1-1 = 0
	}
	else {
	
		printf(" Tumpukan masih kosong, tidak ada tumpukan yang diambil\n");
	}
}

void baca() {
	int i;
	
	if (isEmpty() == true) {
		
		printf(" Stack masih kosong!!!\n");
	}
	else {
		
		printf(" Isi stack:\n");
		for (i=0; i<top; i++) {
			
			printf(" [ %c ]\n", stack[top-i-1]);
		}
	}
}

void clear() {
	int i;
	
	if (isEmpty() != true) {
		
		printf(" Data : ");
		for (i=0; i<top; i++) {
			printf("[ %c ] ", stack[top-i-1]);
		}
		printf("dibersihkan dari tumpukan\n");
		top = 0;
	}
	else {
		
		printf(" Data dibersihkan dari tumpukan\n");
		top = 0;
	}
}

int isEmpty() {
	if (top == 0) {
		return true;
	}
	else {
		return false;
	}
}

int isFull() {
	if (top == MAX) {
		return true;
	}
	else {
		return false;
	}
}

int main() {
	int pilih;
	
	do {
		
		printf("\nOPERASI STACK\n");
		printf("[1] Push\n");
		printf("[2] Pop\n");
		printf("[3] Clear\n");
		printf("[4] Baca\n");
		printf("[5] Keluar\n");
		printf("Masukan pilihan : "); scanf("%d", &pilih);
		
		switch(pilih) {
			case 1 : {
				push();
				break;
			}
			case 2 : {
				pop();
				break;
			}
			case 3 : {
				clear();
				break;
			}
			case 4 : {
				baca();
				break;
			}
			case 5 : {
				break;
			}
			default : {
				printf(" Pilihan salah!!\n");
			}
		}
	} while (pilih != 5);
}