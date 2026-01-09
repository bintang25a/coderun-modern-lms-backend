#include <stdio.h>
#include <stdlib.h>
#include <string.h>

int tambah_data_mhs();
void tampil_data();
void garis(int n);

struct mahasiswa {
	char nim[20];
	char namamhs[20];
	struct mahasiswa *next;
};

struct mahasiswa *ujung;

int tambah_data_mhs() {
	struct mahasiswa *tampung;
	int j = 0;
	char jawab;
	
	printf(" Link List LIFO ( Last In First Out) ");
	printf("\n");
	garis(50);
	
	while (jawab != 'T') {

		ujung = (struct mahasiswa*)malloc(sizeof(struct mahasiswa));
		
		printf("\n\n");
		printf(" Nama : "); scanf("%s", &ujung->namamhs);
		printf("\n");
		printf(" NIM : "); scanf("%s", &ujung->nim);
		
		if (j == 0) {
			
			ujung->next = NULL;
			tampung = ujung;
		}
		else {
			
			ujung->next = tampung;
			tampung = ujung;
		}
		
		jawab = 'T';
		
		while (jawab != 'Y') {
		
			printf("\n");
			printf(" Tambah Data Mahasiswa (Y/T): "); scanf("%s", &jawab);			
					
			if (jawab == 'Y') {
				j++; continue;
			}
			else if (jawab == 'T') {
				break;
			}
			else {
				printf(" Salah, ulangi pilihan\n");
			}
		}
	}
	
	return 0;

}
void tampil_data() {
	struct mahasiswa *tampil;
	
	printf("\n");
	garis(50);	
	printf(" Data Mahasiswa yang telah diinputkan :\n");
	printf("\n");
	printf(" NIM | Nama\n\n");
	
	tampil = ujung;
	
	while (tampil != NULL) {
		
		printf(" %s\t %s\t \n", tampil->nim, tampil->namamhs);
		tampil = tampil->next;
	}
}

void garis(int n) {
	for (int i=0; i<n; i++)
		printf("_");
	printf("\n");
}

int main() {
	
	tambah_data_mhs();
	tampil_data();
	return 0;
}