#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define MAX_LEN 100

typedef struct Node {
    char data[MAX_LEN];
    struct Node* next;
} Node;

Node* head = NULL;

void insertString(char str[]) {
    Node* newNode = (Node*)malloc(sizeof(Node));
    strcpy(newNode->data, str);
    newNode->next = NULL;

    if (head == NULL) {
        head = newNode;
    } else {
        Node* temp = head;
        while (temp->next != NULL)
            temp = temp->next;
        temp->next = newNode;
    }
    printf("'%s' telah ditambahkan.\n", str);
}

void deleteString(char str[]) {
    Node *temp = head, *prev = NULL;

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
    printf("'%s' telah dihapus.\n", str);
}

void searchString(char str[]) {
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
    printf("Isi Linked List:\n");
    if (temp == NULL) {
        printf("(kosong)\n");
        return;
    }
    while (temp != NULL) {
        printf("- %s\n", temp->data);
        temp = temp->next;
    }
}

int main() {
    int choice;
    char input[MAX_LEN];

    do {
        printf("\nMenu:\n");
        printf("1. Tambah string\n");
        printf("2. Hapus string\n");
        printf("3. Cari string\n");
        printf("4. Tampilkan semua\n");
        printf("5. Keluar\n");
        printf("Pilihan: ");
        scanf("%d", &choice);
        getchar();

        switch (choice) {
            case 1:
                printf("Masukkan string: ");
                fgets(input, MAX_LEN, stdin);
                input[strcspn(input, "\n")] = '\0';
                insertString(input);
                break;
            case 2:
                printf("Masukkan string yang ingin dihapus: ");
                fgets(input, MAX_LEN, stdin);
                input[strcspn(input, "\n")] = '\0';
                deleteString(input);
                break;
            case 3:
                printf("Masukkan string yang ingin dicari: ");
                fgets(input, MAX_LEN, stdin);
                input[strcspn(input, "\n")] = '\0';
                searchString(input);
                break;
            case 4:
                displayList();
                break;
            case 5:
                printf("TERIMAKASIH!!!!.\n");
                break;
            default:
                printf("TERIMAKASIH!!!!.\n");
        }
    } while (choice != 5);

    Node* temp;
    while (head != NULL) {
        temp = head;
        head = head->next;
        free(temp);
    }

    return 0;
}

