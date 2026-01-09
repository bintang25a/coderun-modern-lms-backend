#include <stdio.h>
#include <stdlib.h>

typedef struct Node {
    int data;
    struct Node *prev;
    struct Node *next;
} Node;

Node *head = NULL;

Node* createNode(int data) {
    Node *newNode = (Node*)malloc(sizeof(Node));
    newNode->data = data;
    newNode->prev = newNode->next = NULL;
    return newNode;
}

void insertFront(int data) {
    Node *newNode = createNode(data);
    if (!head) {
        newNode->next = newNode->prev = newNode;
        head = newNode;
    } else {
        Node *last = head->prev;
        newNode->next = head;
        newNode->prev = last;
        head->prev = last->next = newNode;
        head = newNode;
    }
    printf("Data %d ditambahkan di depan\n", data);
}

void insertEnd(int data) {
    Node *newNode = createNode(data);
    if (!head) {
        newNode->next = newNode->prev = newNode;
        head = newNode;
    } else {
        Node *last = head->prev;
        newNode->next = head;
        newNode->prev = last;
        last->next = head->prev = newNode;
    }
    printf("Data %d ditambahkan di akhir\n", data);
}

void deleteByData(int data) {
    if (!head) {
        printf("List kosong, tidak ada yang dihapus\n");
        return;
    }

    Node *current = head;
    Node *toDelete = NULL;
    
    do {
        if (current->data == data) {
            toDelete = current;
            break;
        }
        current = current->next;
    } while (current != head);

    if (!toDelete) {
        printf("Data %d tidak ditemukan\n", data);
        return;
    }

    if (toDelete == head) {
        head = head->next;
    }

    if (toDelete->next == toDelete) { // Hanya satu node
        head = NULL;
    } else {
        toDelete->prev->next = toDelete->next;
        toDelete->next->prev = toDelete->prev;
    }

    printf("Data %d berhasil dihapus\n", data);
    free(toDelete);
}

void search(int data) {
    if (!head) {
        printf("List kosong\n");
        return;
    }
    
    Node *current = head;
    int position = 1;
    
    do {
        if (current->data == data) {
            printf("Data %d ditemukan di posisi %d\n", data, position);
            return;
        }
        current = current->next;
        position++;
    } while (current != head);
    
    printf("Data %d tidak ditemukan\n", data);
}

void display() {
    if (!head) {
        printf("List kosong\n");
        return;
    }
    
    Node *current = head;
    int count = 0;
    
    printf("[Isi List] ");
    do {
        printf("%d ", current->data);
        current = current->next;
        count++;
    } while (current != head);
    
    printf("\nJumlah node: %d\n", count);
}

void showMenu() {
    printf("\n=== DOUBLE CIRCULAR LINKED LIST ===\n1. Insert Front\n2. Insert End\n3. Delete\n4. Search\n5. Display\n6. Exit\n");
}

int main() {
    int choice, data;
    
    showMenu();
    
    while (1) {
        printf("\nPilihan: ");scanf("%d", &choice);
        
        switch (choice) {
            case 1:
                printf("Masukkan data: ");scanf("%d", &data);
                insertFront(data);
                break;
            case 2:
                printf("Masukkan data: ");scanf("%d", &data);
                insertEnd(data);
                break;
            case 3:
                printf("Data yang akan dihapus: ");scanf("%d", &data);
                deleteByData(data);
                break;
            case 4:
                printf("Data yang dicari : ");scanf("%d", &data);
                search(data);
                break;
            case 5:
                display();
                break;
            case 6:
                printf("Program selesai\n");
                exit(0);
            default:
                printf("Pilihan tidak valid!\n");
                showMenu();
        }
    }
    
    return 0;
}
