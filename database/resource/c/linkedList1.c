#include <stdio.h>
#include <stdlib.h>
#include <string.h>

// Definisi struktur node
struct Node {
    char data[100];          // Menyimpan string
    struct Node* next;       // Pointer ke node berikutnya
};

// Fungsi untuk menambahkan string ke linked list
void insertNode(struct Node** head, char* str) {
    struct Node* newNode = (struct Node*) malloc(sizeof(struct Node));
    strcpy(newNode->data, str);  // Salin string ke node baru
    newNode->next = NULL;        // Set next node ke NULL (akhir list)

    if (*head == NULL) {
        *head = newNode;  // Jika list kosong, set head ke node baru
    } else {
        struct Node* temp = *head;
        while (temp->next != NULL) {
            temp = temp->next;  // Cari node terakhir
        }
        temp->next = newNode;  // Tambahkan node baru di akhir list
    }
}

// Fungsi untuk menghapus string dari linked list
void deleteNode(struct Node** head, char* str) {
    if (*head == NULL) {
        printf("List kosong.\n");
        return;
    }

    struct Node* temp = *head;
    struct Node* prev = NULL;

    // Jika node yang ingin dihapus adalah head node
    if (temp != NULL && strcmp(temp->data, str) == 0) {
        *head = temp->next; // Move head ke node berikutnya
        free(temp); // Hapus node
        printf("'%s' berhasil dihapus dari list.\n", str);
        return;
    }

    // Cari node yang ingin dihapus
    while (temp != NULL && strcmp(temp->data, str) != 0) {
        prev = temp;
        temp = temp->next;
    }

    // Jika string tidak ditemukan dalam list
    if (temp == NULL) {
        printf("'%s' tidak ditemukan dalam list.\n", str);
        return;
    }

    prev->next = temp->next;  // Lepaskan node dari list
    free(temp);  // Hapus node
    printf("'%s' berhasil dihapus dari list.\n", str);
}

// Fungsi untuk mencari string dalam linked list
void searchNode(struct Node* head, char* str) {
    struct Node* temp = head;
    while (temp != NULL) {
        if (strcmp(temp->data, str) == 0) {
            printf("'%s' ditemukan dalam list.\n", str);
            return;
        }
        temp = temp->next;
    }
    printf("'%s' tidak ditemukan dalam list.\n", str);
}

// Fungsi untuk menampilkan seluruh isi linked list
void printList(struct Node* head) {
    if (head == NULL) {
        printf("List kosong.\n");
        return;
    }

    struct Node* temp = head;
    while (temp != NULL) {
        printf("%s -> ", temp->data);
        temp = temp->next;
    }
    printf("NULL\n");
}

// Fungsi utama
int main() {
    struct Node* head = NULL;  // Inisialisasi head sebagai NULL
    char input[100];           // Untuk menyimpan input string
    int choice;

    while (1) {
        printf("\nMenu:\n");
        printf("1. Masukkan string\n");
        printf("2. Hapus string\n");
        printf("3. Cari string\n");
        printf("4. Tampilkan seluruh isi list\n");
        printf("5. Keluar\n");
        printf("Pilihan: ");
        scanf("%d", &choice);
        getchar();  // Membersihkan newline setelah input pilihan

        switch (choice) {
            case 1:
                printf("Masukkan string yang ingin ditambahkan: ");
                fgets(input, sizeof(input), stdin);
                input[strcspn(input, "\n")] = 0;  // Menghapus karakter newline
                insertNode(&head, input);
                break;
            case 2:
                printf("Masukkan string yang ingin dihapus: ");
                fgets(input, sizeof(input), stdin);
                input[strcspn(input, "\n")] = 0;  // Menghapus karakter newline
                deleteNode(&head, input);
                break;
            case 3:
                printf("Masukkan string yang ingin dicari: ");
                fgets(input, sizeof(input), stdin);
                input[strcspn(input, "\n")] = 0;  // Menghapus karakter newline
                searchNode(head, input);
                break;
            case 4:
                printf("\nIsi linked list:\n");
                printList(head);
                break;
            case 5:
                printf("Keluar dari program.\n");
                exit(0);
            default:
                printf("Pilihan tidak valid, coba lagi.\n");
        }
    }

    return 0;
}
                
                
                
                
                
                
                
                
                
                

