#include <stdio.h>
#include <stdlib.h>

struct Node {
    int data;
    struct Node *prev;
    struct Node *next;
};

struct Node *head = NULL;

struct Node* createNode(int data) {
    struct Node *newNode = (struct Node*)malloc(sizeof(struct Node));
    newNode->data = data;
    newNode->prev = newNode->next = NULL;
    return newNode;
}

void insertFront(int data) {
    struct Node *newNode = createNode(data);
    if (head == NULL) {
        head = newNode;
        head->next = head;
        head->prev = head;
    } else {
        struct Node *last = head->prev;
        newNode->next = head;
        newNode->prev = last;
        head->prev = newNode;
        last->next = newNode;
        head = newNode;
    }
    printf("Data %d ditambahkan di depan\n", data);
}

void insertEnd(int data) {
    struct Node *newNode = createNode(data);
    if (head == NULL) {
        head = newNode;
        head->next = head;
        head->prev = head;
    } else {
        struct Node *last = head->prev;
        last->next = newNode;
        newNode->prev = last;
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

    struct Node *current = head;
    do {
        if (current->data == data) {
            if (current->next == current) {
                head = NULL;
            } else {
                current->prev->next = current->next;
                current->next->prev = current->prev;
                if (current == head)
                    head = current->next;
            }
            free(current);
            printf("Data %d berhasil dihapus\n", data);
            return;
        }
        current = current->next;
    } while (current != head);

    printf("Data %d tidak ditemukan\n", data);
}

void search(int data) {
    if (head == NULL) {
        printf("List kosong\n");
        return;
    }

    struct Node *current = head;
    int pos = 1;
    do {
        if (current->data == data) {
            printf("Data %d ditemukan di posisi %d\n", data, pos);
            return;
        }
        current = current->next;
        pos++;
    } while (current != head);

    printf("Data %d tidak ditemukan\n", data);
}

void display() {
    if (head == NULL) {
        printf("List kosong\n");
        return;
    }

    struct Node *current = head;
    int count = 0;
    printf("List: ");
    do {
        printf("%d ", current->data);
        current = current->next;
        count++;
        if (current != head)
            printf("? ");
    } while (current != head);
    printf("? (kembali ke %d)\n", head->data);
    printf("Jumlah node: %d\n", count);
}

int main() {
    int choice, data;
    printf("=== DOUBLE CIRCULAR LINKED LIST ===\n");
    while (1) {
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
                exit(0);
            default:
                printf("Pilihan tidak valid\n");
        }
    }
    return 0;
}
