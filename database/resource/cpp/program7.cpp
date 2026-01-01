#include <stdio.h>
#include <stdlib.h>

struct Node {
    int data;
    struct Node* prev;
    struct Node* next;
};

struct Node* createNode(int data) {
    struct Node* newNode = (struct Node*)malloc(sizeof(struct Node));
    newNode->data = data;
    newNode->prev = newNode;
    newNode->next = newNode;
    return newNode;
}

void insertFront(struct Node** head, int data) {
    struct Node* newNode = createNode(data);
    if (*head == NULL) {
        *head = newNode;
    } else {
        struct Node* tail = (*head)->prev;

        newNode->next = *head;
        newNode->prev = tail;
        tail->next = newNode;
        (*head)->prev = newNode;
        *head = newNode;
    }
}

void insertEnd(struct Node** head, int data) {
    struct Node* newNode = createNode(data);
    if (*head == NULL) {
        *head = newNode;
    } else {
        struct Node* tail = (*head)->prev;

        newNode->next = *head;
        newNode->prev = tail;
        tail->next = newNode;
        (*head)->prev = newNode;
    }
}

void deleteNode(struct Node** head, int data) {
    if (*head == NULL) return;

    struct Node* current = *head;
    struct Node* tail = (*head)->prev;

    do {
        if (current->data == data) {
            if (current == *head && current->next == *head) {
                free(current);
                *head = NULL;
                return;
            } else {
                current->prev->next = current->next;
                current->next->prev = current->prev;

                if (current == *head) {
                    *head = current->next; 
                }
                free(current);
                return;
            }
        }
        current = current->next;
    } while (current != *head);
}

int search(struct Node* head, int data) {
    if (head == NULL) return -1;

    struct Node* current = head;
    int position = 0;

    do {
        if (current->data == data) {
            return position;
        }
        current = current->next;
        position++;
    } while (current != head);

    return -1; 
}

void display(struct Node* head) {
    if (head == NULL) {
        printf("List kosong.\n");
        return;
    }

    struct Node* current = head;
    do {
        printf("%d -> ", current->data);
        current = current->next;
    } while (current != head);
    printf("(kembali ke %d)\n", head->data);
}

int main() {
    struct Node* head = NULL;
    int choice, data;

    do {
        printf("\nMenu:\n");
        printf("1. Insert Front\n");
        printf("2. Insert End\n");
        printf("3. Delete Node\n");
        printf("4. Search Node\n");
        printf("5. Display List\n");
        printf("6. Exit\n");
        printf("Pilih opsi: ");
        scanf("%d", &choice);

        switch (choice) {
            case 1:
                printf("Masukkan data: ");
                scanf("%d", &data);
                insertFront(&head, data);
                break;
            case 2:
                printf("Masukkan data: ");
                scanf("%d", &data);
                insertEnd(&head, data);
                break;
            case 3:
                printf("Masukkan data yang ingin dihapus: ");
                scanf("%d", &data);
                deleteNode(&head, data);
                break;
            case 4:
                printf("Masukkan data yang ingin dicari: ");
                scanf("%d", &data);
                int position;
                position = search(head, data);
                if (position != -1) {
                    printf("Data %d ditemukan pada posisi %d.\n", data, position);
                } else {
                    printf("Data %d tidak ditemukan.\n", data);
                }
                break;
            case 5:
                printf("Isi list: ");
                display(head);
                break;
            case 6:
                printf("Keluar dari program.\n");
                break;
            default:
                printf("Pilihan tidak valid. Silakan coba lagi.\n");
        }
    } while (choice != 6);

    while (head != NULL) {
        struct Node* temp = head;
        head = head->next;
        deleteNode(&head, temp->data);
    }

    return 0;
}

