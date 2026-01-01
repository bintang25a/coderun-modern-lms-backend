#include <stdio.h>
#include <stdlib.h>
#include <string.h>

struct Node {
    char data[100]; 
    struct Node *next;
};
struct Node *head = NULL;

void insert() {
    char inputString[100];
    printf("Masukkan string: ");
    scanf("%99s", inputString);
    struct Node *newNode = (struct Node*) malloc(sizeof(struct Node));
    strcpy(newNode->data, inputString);
    newNode->next = NULL; 

    if (head == NULL) {
        head = newNode;
    } else {
        struct Node *temp = head;
        while (temp->next != NULL) {
            temp = temp->next;
        }
        temp->next = newNode;
    }

    printf("Data \"%s\" berhasil ditambahkan\n", inputString);
}
void delete() {
    if (head == NULL) {
        printf("List masih kosong, tidak ada data untuk dihapus.\n");
        return;
    }

    char stringDihapus[100];
    printf("Masukkan string yang akan dihapus: ");
    scanf("%99s", stringDihapus);

    struct Node *current = head;
    struct Node *previous = NULL;

    while (current != NULL && strcmp(current->data, stringDihapus) != 0) {
        previous = current;
        current = current->next;
    }
    if (current == NULL) {
        printf("Data \"%s\" tidak ditemukan.\n", stringDihapus);
        return;
    }
    if (previous == NULL) {
        head = current->next; 
    } else {
        previous->next = current->next; 
    }

    free(current);
    printf("Data \"%s\" berhasil dihapus\n", stringDihapus);
}
void search() {
    if (head == NULL) {
        printf("List masih kosong, tidak ada data untuk dicari.\n");
        return;
    }

    char stringDicari[100];
    printf("Masukkan string yang dicari: ");
    scanf("%99s", stringDicari);

    struct Node *temp = head;
    int posisi = 1;
    int ditemukan = 0;

    while (temp != NULL) {
        if (strcmp(temp->data, stringDicari) == 0) {
            printf("String \"%s\" ditemukan di posisi %d\n", stringDicari, posisi);
            ditemukan = 1;
            break;
        }
        temp = temp->next;
        posisi++;
    }

    if (ditemukan == 0) {
        printf("String \"%s\" tidak ditemukan.\n", stringDicari);
    }
}
void display() {
    struct Node *temp = head;

    printf("Isi Linked List:\n");
    if (temp == NULL) {
        printf("Kosong\n");
        return;
    }
    while (temp != NULL) {
        printf("%s ", temp->data); 
        temp = temp->next;        
    }
    printf("\n");
}
int main() {
    int pilihan = 0;

    while (pilihan != 5) {
        printf("\n=== PROGRAM SINGLE LINKED LIST ===\n");
        printf("MENU:\n");
        printf("1. Insert\n");
        printf("2. Delete\n");
        printf("3. Search\n");
        printf("4. Display\n");
        printf("5. Exit\n");
        printf("Pilih menu: ");
        scanf("%d", &pilihan);

        switch (pilihan) {
            case 1:
                insert();
                break;
            case 2:
                delete();
                break;
            case 3:
                search();
                break;
            case 4:
                display();
                break;
            case 5:
                printf("Program selesai. Terima kasih!\n");
                break;
            default:
                printf("Pilihan tidak valid. Silakan coba lagi.\n");
                break;
        }
    }

    return 0;
}