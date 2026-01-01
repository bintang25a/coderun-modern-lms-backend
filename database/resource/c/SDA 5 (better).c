#include <stdio.h>
#define true 1
#define false 0
#define MAX 5

int queue[MAX];
int rear = -1;
int isEmpty();

void add() {
	int data;
	
	if (rear == MAX-1) {
		
		printf(" Queue full \n");
	}
	else {
		
		printf(" Masukan data ke dalam antrian : ");
		scanf("%d", &data);
		
		rear+=1;
		queue[rear] = data;
	}
}

void Remove() {
	int i;
	
	if (isEmpty() == true) {
		
		printf(" Tidak ada data yang dihapus \n");
	}
	else {
		
		printf(" Queue yang di hapus : --> %d \n", queue[0]);
		
		for(i=1; i<=rear; i++) {
			queue[i-1] = queue[i];
		}
		
		rear-=1;
	}
}

void display() {
	int i;
	
	if (isEmpty() == true) {
		printf(" Kosong");
	}
	else {
		printf(" NIlai elemen queue adalah : \n\n");
		for (i = rear; i >= 0; i--) {
			printf("--> %d ", queue[i]);
			printf(" ");
		}
	}
}

int isEmpty() {
	if (rear == -1) {
		return(true);
	}
	else {
		return(false);
	}
}

int main() {
	int pilihan;

	while (1) {
		printf("OPERASI QUEUE\n");
		printf("[1] Input Queue\n");
		printf("[2] Hapus Queue\n");
		printf("[3] Cetak Queue\n");
		printf("[4] Keluar\n");
		printf("Masukan pilihan : "); scanf("%d", &pilihan);
		switch (pilihan) {
			case 1: {
				add(); break;
			}
			case 2: {
				Remove(); break;
			}
			case 3: {
				display(); break;
			}
			case 4: {
				exit(1); break;
			}
			default: {
				printf("salah pilih...\n");
			}
		}

		printf("\n\n\n");
	}
	
	return 0;
}