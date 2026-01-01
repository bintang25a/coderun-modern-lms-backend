
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

typedef struct Node {
    char data[100];
    struct Node *next;
} Node;

Node *head = NULL;

// Fungsi untuk insert node
void insert(char str[]) {
    Node *newNode = (Node *)malloc(sizeof(Node));
    strcpy(newNode->data, str);
    newNode->next = NULL;

    if (head == NULL) {
        head = newNode;
    } else {
        Node *temp = head;
        while (temp->next != NULL)
            temp = temp->next;
        temp->next = newNode;
    }

    printf("Data \"%s\" berhasil ditambahkan\n", str);
}

// Fungsi untuk delete node
void delete(char str[]) {
    Node *temp = head, *prev = NULL;

    while (temp != NULL && strcmp(temp->data, str) != 0) {
        prev = temp;
        temp = temp->next;
    }

    if (temp == NULL) {
        printf("Data \"%s\" tidak ditemukan\n", str);
        return;
    }

    if (prev == NULL) {
        head = temp->next;
    } else {
        prev->next = temp->next;
    }

    free(temp);
    printf("Data \"%s\" berhasil dihapus\n", str);
}

// Fungsi untuk search node
void search(char str[]) {
    Node *temp = head;
    int pos = 1;
    while (temp != NULL) {
        if (strcmp(temp->data, str) == 0) {
            printf("String \"%s\" ditemukan di posisi %d\n", str, pos);
            return;
        }
        temp = temp->next;
        pos++;
    }
    printf("String \"%s\" tidak ditemukan\n", str);
}

// Fungsi untuk display linked list
void display() {
    Node *temp = head;
    printf("Isi Linked List:\n");
    if (temp == NULL) {
        printf("(kosong)\n");
    }
    while (temp != NULL) {
        printf("%s ", temp->data);
        temp = temp->next;
    }
    printf("\n");
}

int main() {
    int choice;
    char str[100];

    printf("=== PROGRAM SINGLE LINKED LIST ===\n");

    do {
        printf("\nMENU:\n");
        printf("1. Insert\n");
        printf("2. Delete\n");
        printf("3. Search\n");
        printf("4. Display\n");
        printf("5. Exit\n");

        printf("\nPilih menu: ");
        scanf("%d", &choice);
        getchar(); // untuk membersihkan newline dari buffer

        switch (choice) {
            case 1:
                printf("Masukkan string: ");
                fgets(str, sizeof(str), stdin);
                str[strcspn(str, "\n")] = '\0'; // hapus newline
                insert(str);
                break;
            case 2:
                printf("Masukkan string yang akan dihapus: ");
                fgets(str, sizeof(str), stdin);
                str[strcspn(str, "\n")] = '\0';
                delete(str);
                break;
            case 3:
                printf("Masukkan string yang dicari: ");
                fgets(str, sizeof(str), stdin);
                str[strcspn(str, "\n")] = '\0';
                search(str);
                break;
            case 4:
                display();
                break;
            case 5:
                printf("Program selesai. Terima kasih!\n");
                break;
            default:
                printf("Pilihan tidak valid.\n");
        }
    } while (choice != 5);

    // Bersihkan memory
    while (head != NULL) {
        Node *temp = head;
        head = head->next;
        free(temp);
    }

    return 0;
}

