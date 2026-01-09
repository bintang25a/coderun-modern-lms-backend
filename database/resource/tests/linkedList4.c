#include <stdio.h>
#include <stdlib.h>

struct Node {
    int data;
    struct Node *next;
    struct Node *prev;
};

typedef struct Node Node;

Node *head = NULL;
Node *tail = NULL;

Node *createNode(int data) {
    Node *newNode = (Node *)malloc(sizeof(Node));
    newNode->data = data;
    newNode->next = NULL;
    newNode->prev = NULL;
    return newNode;
}

int getLength() {
    int l = 0;
    Node *temp = head;

    if (head == NULL) return 0; // simply none inside.

    do {
        l++;
        temp = temp->next;
    } while (temp != head); // stop on head.

    return l;
}

void insertFront(int data) {
    Node *newNode = createNode(data);

    if (head == NULL) {
        head = tail = newNode;
        newNode->next = newNode->prev = newNode;
    } else {
        newNode->next = head;
        newNode->prev = tail;
        tail->next = newNode;
        head->prev = newNode;
        head = newNode;
    }
    printf("Data %d ditambahkan di depan\n", data);

    /**
    for my sake of understanding, hopefully.
    */
    Node *temp = head;
    do {
        if (temp == newNode) {
            printf("[%d]", temp->data);
        } else {
            printf("%d", temp->data);
        }
        temp = temp->next;
        if (temp != head)
            printf(" -> ");
    } while (temp != head);
    printf("\n");
}

void insertRear(int data) {
    Node *newNode = createNode(data);

    if (head == NULL) {
        head = tail = newNode;
        newNode->next = newNode->prev = newNode;
    } else {
        newNode->prev = tail;
        newNode->next = head;
        tail->next = newNode;
        head->prev = newNode;
        tail = newNode;
    }
    printf("Data %d ditambahkan di akhir\n", data);

    /**
    didn't use modular approach.
    */
    Node *temp = head;
    do {
        if (temp == newNode) {
            printf("[%d]", temp->data);
        } else {
            printf("%d", temp->data);
        }
        temp = temp->next;
        if (temp != head)
            printf(" -> ");
    } while (temp != head);
    printf("\n");
}

void delete(int data) {
    if (head == NULL) {
        puts("List kosong");
        return;
    }

    Node *current = head;

    do {
        if (current->data == data) {
            if (current == head && current == tail) {
                head = tail = NULL;
            } else {
                current->prev->next = current->next;
                current->next->prev = current->prev;

                if (current == head) head = current->next;
                if (current == tail) tail = current->prev;
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
        puts("List kosong");
        return;
    }

    Node *temp = head;
    int position = 1; // why? because we search for the exact position not index.

    do {
        if (temp->data == data) {
            printf("Data %d ditemukan di posisi %d\n", data, position);
            return;
        }
        temp = temp->next;
        position++;
    } while (temp != head);

    printf("Data %d tidak ditemukan\n", data);
}

void display() {
    if (head == NULL) {
        puts("List kosong");
        return;
    }

    printf("List: ");
    Node *temp = head;
    do {
        printf("%d", temp->data);
        temp = temp->next;
        if (temp != head)
            printf(" -> ");
    } while (temp != head);
    printf(" -> (kembali ke %d)\n", head->data);
    printf("Jumlah node: %d\n", getLength());
}

int main() {
    puts("=== DOUBLE LINKED LIST ===");

    int pilihan, data;

    puts("\nMENU:");
    printf("1. Insert Front\n2. Insert Rear\n3. Delete\n4. Search\n5. Display\n6. Exit");

    while (1) {
        puts("\nPilihan: ");
        scanf("%d", &pilihan);

        switch (pilihan) {
        case 1:
            puts("Masukkan data: ");
            scanf("%d", &data);
            insertFront(data);
            break;
        case 2:
            puts("Masukkan data: ");
            scanf("%d", &data);
            insertRear(data);
            break;
        case 3:
            puts("Data yang akan dihapus: ");
            scanf("%d", &data);
            delete(data);
            break;
        case 4:
            puts("Data yang dicari: ");
            scanf("%d", &data);
            search(data);
            break;
        case 5:
            display();
            break;
        case 6:
            puts("Program selesai\n");
            return 0;
        default:
            puts("Pilihan tidak valid\n");
        }
    }

    return 0;
}