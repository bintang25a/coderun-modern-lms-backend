#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define MAX_STR_LEN 100

struct Node {
    char *data;
    struct Node *next;
};

typedef struct Node Node;

Node *head = NULL;

Node *createNode(const char *data) {
    Node *newNode = (Node *)malloc(sizeof(Node));
    // https://stackoverflow.com/a/252792
    newNode->data = strdup(data);
    newNode->next = NULL;
    return newNode;
}

void insert(const char *data) {
    Node *newNode = createNode(data);
    if (head == NULL) {
        head = newNode;
    } else {
        Node *temp = head;
        while (temp->next != NULL) {
            temp = temp->next;
        }
        temp->next = newNode;
    }
    // https://www.w3schools.com/c/c_strings_esc.php
    printf("Data \"%s\" berhasil ditambahkan!\n", data);
}

void display() {
    if (head == NULL) {
        puts("Entri kosong!");
        return;
    }
    puts("Isi Linked List: ");
    Node *temp = head;
    while (temp != NULL) {
        printf("\"%s\"", temp->data);
        if (temp->next != NULL)
            printf(", ");
        temp = temp->next;
    }
    printf("\n");
}

void del(const char *data) {
    if (head == NULL) {
        puts("Entri kosong!");
        return;
    }
    if (strcmp(head->data, data) == 0) {
        Node *temp = head;
        head = head->next;
        free(temp->data);
        free(temp);
        printf("Data \"%s\" berhasil dihapus!\n", data);
        return;
    }
    Node *current = head;
    while (current->next != NULL && strcmp(current->next->data, data) != 0) {
        current = current->next;
    }
    if (current->next == NULL) {
        printf("Data \"%s\" tidak ada!\n", data);
        return;
    }
    Node *temp = current->next;
    current->next = temp->next;
    free(temp->data);
    free(temp);
    printf("Data \"%s\" berhasil dihapus!\n", data);
}

void search(const char *data) {
    Node *temp = head;
    int pos = 1;
    while (temp != NULL) {
        if (strcmp(temp->data, data) == 0) {
            printf("Data \"%s\" ditemukan di posisi [%d].\n", data, pos);
            return;
        }
        temp = temp->next;
        pos++;
    }
    printf("Data \"%s\" tidak ditemukan!\n", data);
}

// https://stackoverflow.com/a/7898516
void clearInputBuffer() {
    int c;
    while ((c = getchar()) != '\n' && c != EOF);
}

int main(void) {
    puts("=== PROGRAM SINGLE LINKED LIST ===");
    int opt;
    char input[MAX_STR_LEN];

    while (1) {
        printf("MENU:\n1. Insert\n2. Delete\n3. Search\n4. Display\n5. Exit\n");
        printf("--> ");
        scanf("%d", &opt);
        clearInputBuffer();

        switch (opt) {
            case 1:
                printf("Masukkan string: ");
                // https://stackoverflow.com/q/38767967
                if (fgets(input, sizeof(input), stdin) != NULL) {
                    input[strcspn(input, "\n")] = 0;
                    insert(input);
                }
                break;
            case 2:
                printf("Masukkan string yang akan dihapus: ");
                if (fgets(input, sizeof(input), stdin) != NULL) {
                    input[strcspn(input, "\n")] = 0;
                    del(input);
                }
                break;
            case 3:
                printf("Masukkan string yang dicari: ");
                if (fgets(input, sizeof(input), stdin) != NULL) {
                    input[strcspn(input, "\n")] = 0;
                    search(input);
                }
                break;
            case 4:
                display();
                break;
            case 5:
                puts("Program selesai. Terima kasih!");
                return 0;
            default:
                puts("Pilihan tidak ada!");
                break;
        }
    }

    return 0;
}
