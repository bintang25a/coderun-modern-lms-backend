#include <stdio.h>
#include <stdlib.h>
#include <string.h>

typedef struct Node {
    char data[100];
    struct Node* next;
} Node;

Node* head = NULL;

void showMenu() {
    printf("\n=== PROGRAM SINGLE LINKED LIST ===\n");
    printf("MENU:\n");
    printf("1. Insert\n");
    printf("2. Delete\n");
    printf("3. Search\n");
    printf("4. Display\n");
    printf("5. Exit\n");
}

void insert() {
    char data[100];
    printf("Masukkan string: "); scanf("%s", data);  

    Node* new = (Node*)malloc(sizeof(Node));
    strcpy(new->data, data);
    new->next = NULL;

    if (!head) head = new;
    else {
        Node* temp = head;
        while (temp->next) temp = temp->next;
        temp->next = new;
    }
    printf("Data \"%s\" berhasil ditambahkan\n", data);
}

void delete() {
    if (!head) {
        printf("Linked list kosong\n");
        return;
    }

    char data[100];
    printf("Masukkan string yang akan dihapus: "); scanf("%s", data);

    Node *temp = head, *prev = NULL;
    while (temp && strcmp(temp->data, data)) {
        prev = temp;
        temp = temp->next;
    }

    if (!temp) {
        printf("Data \"%s\" tidak ditemukan\n", data);
        return;
    }

    if (!prev) head = temp->next;
    else prev->next = temp->next;

    printf("Data \"%s\" berhasil dihapus\n", temp->data);
    free(temp);
}

void search() {
    char data[100];
    printf("Masukkan string yang dicari: ");
    scanf("%s", data);

    Node* temp = head;
    int pos = 1;
    while (temp && strcmp(temp->data, data)) {
        temp = temp->next;
        pos++;
    }

    if (temp) printf("String \"%s\" ditemukan di posisi %d\n", data, pos);
    else printf("String \"%s\" tidak ditemukan\n", data);
}

void display() {
    if (!head) {
        printf("Linked List kosong\n");
        return;
    }

    printf("Linked List:\n");
    Node* temp = head;
    while (temp) {
        printf("%s ", temp->data);
        temp = temp->next;
    }
    printf("\n");
}

int main() {
    int choice;
    
    showMenu();
    
    while (1) {
        printf("\nPilih menu: ");
        scanf("%d", &choice);
        
        switch (choice) {
            case 1: insert(); break;
            case 2: delete(); break;
            case 3: search(); break;
            case 4: display(); break;
            case 5: printf("Program selesai. Terima kasih!\n");
                return 0;
            default: printf("Pilihan tidak valid\n");
        }
    }
}
