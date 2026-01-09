#include <stdio.h>
#include <stdlib.h>
#include <string.h>

typedef struct Node {
    char data[100];
    struct Node *next;
} Node;

Node* createNode(const char *str) {
    Node *newNode = (Node *) malloc(sizeof(Node));
    if (!newNode) {
        printf("Memory allocation failed\n");
        exit(1);
    }
    strcpy(newNode->data, str);
    newNode->next = NULL;
    return newNode;
}

void insert(Node **head, const char *str) {
    Node *newNode = createNode(str);
    if (*head == NULL) {
        *head = newNode;
    } else {
        Node *temp = *head;
        while (temp->next != NULL) temp = temp->next;
        temp->next = newNode;
    }
    printf("'%s' inserted.\n", str);
}

void delete(Node **head, const char *str) {
    if (*head == NULL) {
        printf("List kosong, tidak ada yang dihapus.\n");
        return;
    }

    Node *temp = *head, *prev = NULL;

    // Jika node pertama yang akan dihapus
    if (strcmp(temp->data, str) == 0) {
        *head = temp->next;
        free(temp);
        printf("'%s' dihapus.\n", str);
        return;
    }

    // Cari node yang akan dihapus
    while (temp != NULL && strcmp(temp->data, str) != 0) {
        prev = temp;
        temp = temp->next;
    }

    if (temp == NULL) {
        printf("'%s' tidak ditemukan.\n", str);
        return;
    }

    prev->next = temp->next;
    free(temp);
    printf("'%s' dihapus.\n", str);
}

Node* search(Node *head, const char *str) {
    Node *temp = head;
    while (temp != NULL) {
        if (strcmp(temp->data, str) == 0) {
            return temp;
        }
        temp = temp->next;
    }
    return NULL;
}

void display(Node *head) {
    if (head == NULL) {
        printf("Linked list kosong.\n");
        return;
    }
    printf("Isi Linked List:\n");
    Node *temp = head;
    while (temp != NULL) {
        printf("%s\n", temp->data);
        temp = temp->next;
    }
}

int main() {
    Node *head = NULL;
    int choice;
    char input[100];

    while(1) {
        printf("\nMenu:\n");
        printf("1. Masukkan string\n");
        printf("2. Hapus string\n");
        printf("3. Cari string\n");
        printf("4. Tampilkan seluruh isi linked list\n");
        printf("5. Keluar\n");
        printf("Pilih: ");
        scanf("%d", &choice);
        getchar(); // untuk menghapus newline setelah scanf

        switch(choice) {
            case 1:
                printf("Masukkan string: ");
                fgets(input, sizeof(input), stdin);
                input[strcspn(input, "\n")] = '\0'; // hapus newline
                insert(&head, input);
                break;
            case 2:
                printf("Masukkan string yang ingin dihapus: ");
                fgets(input, sizeof(input), stdin);
                input[strcspn(input, "\n")] = '\0';
                delete(&head, input);
                break;
            case 3:
                printf("Masukkan string yang ingin dicari: ");
                fgets(input, sizeof(input), stdin);
                input[strcspn(input, "\n")] = '\0';
                if (search(head, input) != NULL) {
                    printf("'%s' ditemukan dalam linked list.\n", input);
                } else {
                    printf("'%s' tidak ditemukan.\n", input);
                }
                break;
            case 4:
                display(head);
                break;
            case 5:
                printf("Keluar...\n");
                // Bebaskan memori sebelum keluar
                while (head != NULL) {
                    Node *tmp = head;
                    head = head->next;
                    free(tmp);
                }
                return 0;
            default:
                printf("Pilihan tidak valid.\n");
        }
    }
}
