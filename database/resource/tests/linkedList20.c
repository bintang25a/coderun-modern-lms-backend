#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define MAX_STR 100

typedef struct Node {
    char data[MAX_STR];
    struct Node* next;
} Node;

Node* head = NULL;

Node* createNode(char* str) {
    Node* newNode = (Node*)malloc(sizeof(Node));
    strcpy(newNode->data, str);
    newNode->next = NULL;
    return newNode;
}

void insertString(char* str) {
    Node* newNode = createNode(str);
    if (head == NULL) {
        head = newNode;
    } else {
        Node* temp = head;
        while (temp->next != NULL)
            temp = temp->next;
        temp->next = newNode;
    }
    printf("'%s' berhasil ditambahkan.\n", str);
}

void deleteString(char* str) {
    Node* temp = head;
    Node* prev = NULL;

    while (temp != NULL && strcmp(temp->data, str) != 0) {
        prev = temp;
        temp = temp->next;
    }

    if (temp == NULL) {
        printf("'%s' tidak ditemukan.\n", str);
        return;
    }

    if (prev == NULL) {
        head = temp->next;
    } else {
        prev->next = temp->next;
    }

    free(temp);
    printf("'%s' berhasil dihapus.\n", str);
}

void searchString(char* str) {
    Node* temp = head;
    int pos = 1;
    while (temp != NULL) {
        if (strcmp(temp->data, str) == 0) {
            printf("'%s' ditemukan di posisi %d.\n", str, pos);
            return;
        }
        temp = temp->next;
        pos++;
    }
    printf("'%s' tidak ditemukan.\n", str);
}

void displayList() {
    Node* temp = head;
    if (temp == NULL) {
        printf("Linked list kosong.\n");
        return;
    }
    printf("Isi linked list:\n");
    while (temp != NULL) {
        printf("-> %s\n", temp->data);
        temp = temp->next;
    }
}

int main() {
    int pilihan;
    char input[MAX_STR];

    do {
        printf("\n=== MENU LINKED LIST STRING ===\n");
        printf("1. Tambah string\n");
        printf("2. Hapus string\n");
        printf("3. Cari string\n");
        printf("4. Tampilkan list\n");
        printf("5. Keluar\n");
        printf("Pilih: ");
        scanf("%d", &pilihan);
        getchar();

        switch (pilihan) {
            case 1:
                printf("Masukkan string: ");
                fgets(input, MAX_STR, stdin);
                input[strcspn(input, "\n")] = '\0'; 
                insertString(input);
                break;
            case 2:
                printf("Masukkan string yang ingin dihapus: ");
                fgets(input, MAX_STR, stdin);
                input[strcspn(input, "\n")] = '\0';
                deleteString(input);
                break;
            case 3:
                printf("Masukkan string yang ingin dicari: ");
                fgets(input, MAX_STR, stdin);
                input[strcspn(input, "\n")] = '\0';
                searchString(input);
                break;
            case 4:
                displayList();
                break;
            case 5:
                printf("Keluar dari program.\n");
                break;
            default:
                printf("Pilihan tidak valid.\n");
        }
    } while (pilihan != 5);

    return 0;
}
