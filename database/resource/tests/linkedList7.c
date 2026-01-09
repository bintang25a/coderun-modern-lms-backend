#include <stdio.h>
#include <stdlib.h>
#include <string.h>


struct Node {
    char data[100];      
    struct Node* next;   
};


void insert(struct Node** head, const char* new_data) {
    struct Node* new_node = (struct Node*)malloc(sizeof(struct Node));
    struct Node* temp = *head;
    
   
    strcpy(new_node->data, new_data);
    new_node->next = NULL;
    
    if (*head == NULL) {
        *head = new_node;  
    } else {
        
        while (temp->next != NULL) {
            temp = temp->next;
        }
        temp->next = new_node;
    }
}


void deleteNode(struct Node** head, const char* target) {
    struct Node *temp = *head, *prev = NULL;

    
    if (temp != NULL && strcmp(temp->data, target) == 0) {
        *head = temp->next;  
        free(temp);          
        return;
    }


    while (temp != NULL && strcmp(temp->data, target) != 0) {
        prev = temp;
        temp = temp->next;
    }


    if (temp == NULL) {
        printf("String '%s' tidak ditemukan.\n", target);
        return;
    }


    prev->next = temp->next;
    free(temp);
}


int search(struct Node* head, const char* target) {
    struct Node* temp = head;
    int index = 0;

    while (temp != NULL) {
        if (strcmp(temp->data, target) == 0) {
            return index;
        }
        temp = temp->next;
        index++;
    }

    return -1;
}


void display(struct Node* head) {
    if (head == NULL) {
        printf("Linked list kosong.\n");
        return;
    }

    struct Node* temp = head;
    while (temp != NULL) {
        printf("%s\n", temp->data);
        temp = temp->next;
    }
}

int main() {
    struct Node* head = NULL;
    int choice;
    char str[100];

    while (1) {
        printf("\nMenu:\n");
        printf("1. Masukkan string\n");
        printf("2. Hapus string\n");
        printf("3. Cari string\n");
        printf("4. Tampilkan seluruh isi linked list\n");
        printf("5. Keluar\n");
        printf("Pilih opsi (1-5): ");
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
                int index = search(head, str);
                if (index != -1) {
                    printf("String '%s' ditemukan pada posisi %d.\n", str, index);
                } else {
                    printf("String '%s' tidak ditemukan.\n", str);
                }
                break;

            case 4:
                display(head);
                break;

            case 5:
                printf("Keluar dari program...\n");
                exit(0);

            default:
                printf("Pilihan tidak valid.\n");
        }
    }

    return 0;
}

