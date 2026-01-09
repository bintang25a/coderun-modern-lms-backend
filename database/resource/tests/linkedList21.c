#include <stdio.h>
#include <stdlib.h>

struct Node {
    int data;
    struct Node *prev;
    struct Node *next;
};

struct Node* head = NULL;

struct Node* createNode(int data) {
    struct Node* newNode = (struct Node*)malloc(sizeof(struct Node));
    newNode->data = data;
    newNode->prev = newNode->next = NULL;
    return newNode;
}

void insertFront(int data) {
    struct Node* newNode = createNode(data);
    if (head == NULL) {
        newNode->next = newNode->prev = newNode;
        head = newNode;
    } else {
        struct Node* tail = head->prev;
        newNode->next = head;
        newNode->prev = tail;
        tail->next = head->prev = newNode;
        head = newNode;
    }
}

void insertEnd(int data) {
    struct Node* newNode = createNode(data);
    if (head == NULL) {
        newNode->next = newNode->prev = newNode;
        head = newNode;
    } else {
        struct Node* tail = head->prev;
        tail->next = newNode;
        newNode->prev = tail;
        newNode->next = head;
        head->prev = newNode;
    }
}

void delete(int data) {
    if (head == NULL) {
        printf("List kosong!\n");
        return;
    }

    struct Node* current = head;
    struct Node* toDelete = NULL;

    do {
        if (current->data == data) {
            toDelete = current;
            break;
        }
        current = current->next;
    } while (current != head);

    if (toDelete == NULL) {
        printf("Data tidak ditemukan!\n");
        return;
    }

    if (toDelete->next == toDelete) {
        head = NULL;
    } else {
        struct Node* prevNode = toDelete->prev;
        struct Node* nextNode = toDelete->next;
        prevNode->next = nextNode;
        nextNode->prev = prevNode;
        if (toDelete == head)
            head = nextNode;
    }

    free(toDelete);
    printf("Data %d berhasil dihapus.\n", data);
}

int search(int data) {
    if (head == NULL) return -1;

    struct Node* temp = head;
    int pos = 0;
    do {
        if (temp->data == data)
            return pos;
        temp = temp->next;
        pos++;
    } while (temp != head);

    return -1;
}

void display() {
    if (head == NULL) {
        printf("List kosong!\n");
        return;
    }

    struct Node* temp = head;
    printf("Isi List: ");
    do {
        printf("%d ", temp->data);
        temp = temp->next;
    } while (temp != head);
    printf("\n");
}

int main() {
    insertEnd(10);
    insertEnd(20);
    insertFront(5);
    insertEnd(30);
    display();

    int pos = search(20);
    if (pos != -1)
        printf("Data 20 ditemukan di posisi %d\n", pos);
    else
        printf("Data tidak ditemukan!\n");

    delete(10);
    display();

    delete(5);
    display(); 

    return 0;
}
