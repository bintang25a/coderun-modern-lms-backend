#include <stdio.h>
#include <stdlib.h>

typedef struct Node {
    int data;
    struct Node* prev;
    struct Node* next;
} Node;

Node* head = NULL;

Node* createNode(int data) {
    Node* newNode = (Node*)malloc(sizeof(Node));
    newNode->data = data;
    newNode->prev = newNode->next = NULL;
    return newNode;
}

void insertFront(int data) {
    Node* newNode = createNode(data);
    if (head == NULL) {
        head = newNode;
        head->next = head->prev = head;
    } else {
        Node* tail = head->prev;

        newNode->next = head;
        newNode->prev = tail;

        tail->next = head->prev = newNode;
        head = newNode;
    }
    printf("Node %d ditambahkan di awal.\n", data);
}

void insertEnd(int data) {
    Node* newNode = createNode(data);
    if (head == NULL) {
        head = newNode;
        head->next = head->prev = head;
    } else {
        Node* tail = head->prev;

        tail->next = newNode;
        newNode->prev = tail;
        newNode->next = head;
        head->prev = newNode;
    }
    printf("Node %d ditambahkan di akhir.\n", data);
}

void deleteNode(int data) {
    if (head == NULL) {
        printf("List kosong.\n");
        return;
    }

    Node* curr = head;
    Node* toDelete = NULL;

    do {
        if (curr->data == data) {
            toDelete = curr;
            break;
        }
        curr = curr->next;
    } while (curr != head);

    if (toDelete == NULL) {
        printf("Data %d tidak ditemukan.\n", data);
        return;
    }

    if (toDelete->next == toDelete) {
        head = NULL;
    } else {
        toDelete->prev->next = toDelete->next;
        toDelete->next->prev = toDelete->prev;

        if (toDelete == head) {
            head = toDelete->next;
        }
    }

    free(toDelete);
    printf("Node %d dihapus.\n", data);
}

void search(int data) {
    if (head == NULL) {
        printf("List kosong.\n");
        return;
    }

    Node* curr = head;
    int pos = 1;
    do {
        if (curr->data == data) {
            printf("Data %d ditemukan di posisi %d.\n", data, pos);
            return;
        }
        curr = curr->next;
        pos++;
    } while (curr != head);

    printf("Data %d tidak ditemukan.\n", data);
}

void display() {
    if (head == NULL) {
        printf("List kosong.\n");
        return;
    }

    Node* curr = head;
    printf("Isi List: ");
    do {
        printf("%d ", curr->data);
        curr = curr->next;
    } while (curr != head);
    printf("\n");
}

int main() {
    int choice, data;

    do {
        printf("\nMenu:\n");
        printf("1. Insert di depan\n");
        printf("2. Insert di akhir\n");
        printf("3. Hapus node\n");
        printf("4. Cari data\n");
        printf("5. Tampilkan list\n");
        printf("6. Keluar\n");
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
                printf("Masukkan data yang ingin dihapus: ");
                scanf("%d", &data);
                deleteNode(data);
                break;
            case 4:
                printf("Masukkan data yang dicari: ");
                scanf("%d", &data);
                search(data);
                break;
            case 5:
                display();
                break;
            case 6:
                printf("Program selesai.\n");
                break;
            default:
                printf("Pilihan tidak valid.\n");
        }
    } while (choice != 6);

    while (head != NULL) {
        deleteNode(head->data);
    }

    return 0;
}

