#include <stdio.h>
#include <stdlib.h>
#include <string.h>

struct Node {
    char data[100];
    struct Node* next;
};

void insert(struct Node** head, char* newData) {
    struct Node* newNode = (struct Node*) malloc(sizeof(struct Node));
    strcpy(newNode -> data, newData);
    newNode -> next = *head;
    *head = newNode;
}

void deleteNode(struct Node** head, char* key) {
    struct Node* temp = *head, *prev = NULL;
    if (temp != NULL && strcmp(temp -> data, key) == 0) {
        *head = temp -> next; 
        free(temp); 
        return;
    }
    while (temp != NULL && strcmp(temp -> data, key) != 0) {
        prev = temp;
        temp = temp -> next;
    }
    if (temp == NULL) return;
    prev -> next = temp -> next;
    free(temp);
}

int search(struct Node* head, char* key) {
    struct Node* current = head;
    while (current != NULL) {
        if (strcmp(current -> data, key) == 0) {
            return 1; 
        }
        current = current -> next;
    }
    return 0; 
}

void display(struct Node* node) {
    while (node != NULL) {
        printf("%s -> ", node->data);
        node = node -> next;
    }
    printf("NULL\n");
}

int main() {
    struct Node* head = NULL;
    int choice;
    char str[100];

    do {
        printf("\nMenu:\n");
        printf("1. Masukkan string\n");
        printf("2. Hapus string\n");
        printf("3. Cari string\n");
        printf("4. Tampilkan seluruh isi linked list\n");
        printf("5. Keluar\n");
        printf("Pilih opsi: ");
        scanf("%d", &choice);
        getchar(); 

        switch (choice) {
            case 1:
                printf("Masukkan string: ");
                fgets(str, sizeof(str), stdin);
                str[strcspn(str, "\n")] = 0; 
                insert(&head, str);
                break;
            case 2:
                printf("Masukkan string yang ingin dihapus: ");
                fgets(str, sizeof(str), stdin);
                str[strcspn(str, "\n")] = 0; 
                deleteNode(&head, str);
                break;
            case 3:
                printf("Masukkan string yang ingin dicari: ");
                fgets(str, sizeof(str), stdin);
                str[strcspn(str, "\n")] = 0; 
                if (search(head, str)) {
                    printf("String '%s' ditemukan dalam linked list.\n", str);
                } else {
                    printf("String '%s' tidak ditemukan dalam linked list.\n", str);
                }
                break;
            case 4:
                printf("Isi linked list: ");
                display(head);
                break;
            case 5:
                printf("Keluar dari program.\n");
                break;
            default:
                printf("Pilihan tidak valid. Silakan coba lagi.\n");
        }
    } while (choice != 5);

    while (head != NULL) {
        struct Node* temp = head;
        head = head -> next;
        free(temp);
    }
    
    return 0;
}
