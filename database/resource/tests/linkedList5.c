#include <stdio.h>
#include <stdlib.h>
#include <string.h>

struct Node {
    char data[50];
    struct Node *next;
};

struct Node *head = NULL;

struct Node *createNode(char data[50]) {
    struct Node *newNode = (struct Node *) malloc(sizeof(struct Node));
    strcpy(newNode->data, data);
    newNode->next = NULL;
    return newNode;
}

void insert(char data[50]) {
    struct Node *newNode = createNode(data);
    newNode->next = head;
    head = newNode;
    printf("\nData %s ditambahkan\n", data);
    printf("\n========================================\n");
}

void delete(char data[50]) {
    if (head == NULL) {
        printf("\nList kosong\n");
        printf("\n========================================\n");
        return;
    }

    if (strcmp(head->data, data) == 0) {
        struct Node *temp = head;
        head = head->next;
        free(temp);
        printf("\nData %s dihapus\n", data);
        printf("\n========================================\n");
        return;
    }

    struct Node *current = head;
    while (current->next != NULL && strcmp(current->next->data, data) != 0) {
        current = current->next;
    }

    if (current->next == NULL) {
        printf("\nData %s tidak ditemukan\n", data);
        printf("\n========================================\n");
        return;
    }

    struct Node *temp = current->next;
    current->next = temp->next;
    free(temp);
    printf("\nData %s dihapus\n", data);
    printf("\n========================================\n");
}

void search(char data[50]) {
    struct Node *temp = head;
    int position = 1;

    while (temp != NULL) {
        if (strcmp(temp->data, data) == 0) {
            printf("\nData %s ditemukan di posisi %d\n", data, position);
            printf("\n========================================\n");
            return;
        }
        temp = temp->next;
        position++;
    }

    printf("\nData %s tidak ditemukan\n", data);
    printf("\n========================================\n");
}

void display() {
    if (head == NULL) {
        printf("\nList kosong\n");
        printf("\n========================================\n");
        return;
    }

    printf("\nList : ");
    struct Node *temp = head;
    while (temp != NULL) {
        printf("%s", temp->data);
        if (temp->next != NULL) {
            printf(" -> ");
        }
        temp = temp->next;
    }
    printf(" -> NULL\n");
    printf("\n========================================\n");
}

int main() {
    int pilihan;
    char data[50];

    while (1) {
        printf("\n\t~Single Linked List~\n");
        printf("\n[1] Insert\n[2] Delete\n[3] Search\n[4] Display\n[5] Exit\n");
        printf("Pilihan : ");
        scanf("%d", &pilihan);

        switch (pilihan) {
            case 1:
                printf("\n========================================\n");
                printf("\n\t\tInsert Data\n");
                printf("Masukan Data : ");
                scanf("%s", data);  // ? tanpa & untuk array
                insert(data);
                break;
            case 2:
                printf("\n========================================\n");
                printf("\n\t\tDelete Data\n");
                printf("Data yang akan dihapus : ");
                scanf("%s", data);
                delete(data);
                break;
            case 3:
                printf("\n========================================\n");
                printf("\n\t\tSearch Data\n");
                printf("Data yang akan dicari : ");
                scanf("%s", data);
                search(data);
                break;
            case 4:
                printf("\n========================================\n");
                printf("\n\t\tDisplay Data\n");
                display();
                break;
            case 5:
                printf("\n========================================\n");
                printf("\n\t\tProgram selesai\n");
                return 0;
            default:
                printf("\n========================================\n");
                printf("Pilihan tidak valid\n");
        }
    }

    return 0;
}
