#include <stdio.h>
#include <stdlib.h>

typedef struct Node {
    int data;
    struct Node *prev, *next;
} Node;

Node *head = NULL;

Node* createNode(int data) {
    Node *newNode = (Node *)malloc(sizeof(Node));
    newNode->data = data;
    newNode->prev = newNode->next = NULL;
    return newNode;
}

void insertFront(int data) {
    Node *newNode = createNode(data);
    if (head == NULL) {
        head = newNode;
        head->next = head->prev = head;
    } else {
        Node *tail = head->prev;
        newNode->next = head;
        newNode->prev = tail;
        tail->next = newNode;
        head->prev = newNode;
        head = newNode;
    }
    printf("Data %d ditambahkan di depan\n", data);
}

void insertEnd(int data) {
    Node *newNode = createNode(data);
    if (head == NULL) {
        head = newNode;
        head->next = head->prev = head;
    } else {
        Node *tail = head->prev;
        tail->next = newNode;
        newNode->prev = tail;
        newNode->next = head;
        head->prev = newNode;
    }
    printf("Data %d ditambahkan di akhir\n", data);
}

void deleteNode(int data) {
    if (head == NULL) {
        printf("List kosong\n");
        return;
    }
    Node *curr = head;
    Node *temp = NULL;
    do {
        if (curr->data == data) {
            if (curr->next == curr) {
                // Hanya 1 node
                free(curr);
                head = NULL;
            } else {
                curr->prev->next = curr->next;
                curr->next->prev = curr->prev;
                if (curr == head) {
                    head = curr->next;
                }
                free(curr);
            }
            printf("Data %d berhasil dihapus\n", data);
            return;
        }
        curr = curr->next;
    } while (curr != head);

    printf("Data %d tidak ditemukan\n", data);
}

void search(int data) {
    if (head == NULL) {
        printf("List kosong\n");
        return;
    }
    Node *curr = head;
    int pos = 1;
    do {
        if (curr->data == data) {
            printf("Data %d ditemukan di posisi %d\n", data, pos);
            return;
        }
        curr = curr->next;
        pos++;
    } while (curr != head);

    printf("Data %d tidak ditemukan\n", data);
}

void display() {
    if (head == NULL) {
        printf("List kosong\n");
        return;
    }
    Node *curr = head;
    printf("List: ");
    do {
        printf("%d -> ", curr->data);
        curr = curr->next;
    } while (curr != head);
    printf("(kembali ke %d)\n", head->data);

    // Hitung jumlah node
    int count = 0;
    curr = head;
    do {
        count++;
        curr = curr->next;
    } while (curr != head);
    printf("Jumlah node: %d\n", count);
}

int main() {
    int choice, data;
    printf("==== DOUBLE CIRCULAR LINKED LIST ====\n");

    do {
        printf("\nMenu:\n");
        printf("1. Insert Front\n");
        printf("2. Insert End\n");
        printf("3. Delete\n");
        printf("4. Search\n");
        printf("5. Display\n");
        printf("6. Exit\n");

        printf("Pilihan: ");
        scanf("%d", &choice);

        switch (choice) {
            case 1:
                printf("Masukkan data: ");
                scanf("%d", &data);
                insertFront(data);
                break;
            case 2:
                printf("Masukkan data: ");
                scanf("%d", &data);
                insertEnd(data);
                break;
            case 3:
                printf("Data yang akan dihapus: ");
                scanf("%d", &data);
                deleteNode(data);
                break;
            case 4:
                printf("Data yang dicari: ");
                scanf("%d", &data);
                search(data);
                break;
            case 5:
                display();
                break;
            case 6:
                printf("Program selesai\n");
                break;
            default:
                printf("Pilihan tidak valid\n");
        }

    } while (choice != 6);

    // Bersihkan memory
    while (head != NULL) {
        deleteNode(head->data);
    }

    return 0;
}

