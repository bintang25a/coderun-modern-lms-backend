#include <stdio.h>
#include <stdlib.h>

// Struktur dasar Node
struct Node {
    int data;
    struct Node* prev;
    struct Node* next;
};

// Pointer global head
struct Node* head = NULL;

// Fungsi buat node baru
struct Node* createNode(int data) {
    struct Node* newNode = (struct Node*)malloc(sizeof(struct Node));
    newNode->data = data;
    newNode->prev = newNode->next = NULL;
    return newNode;
}

// Insert di depan
void insertFront(int data) {
    struct Node* newNode = createNode(data);

    if (head == NULL) {
        head = newNode;
        head->next = head;
        head->prev = head;
    } else {
        struct Node* last = head->prev;

        newNode->next = head;
        newNode->prev = last;
        head->prev = newNode;
        last->next = newNode;
        head = newNode;
    }
    printf("Data %d ditambahkan di depan\n", data);
}

// Insert di akhir
void insertEnd(int data) {
    struct Node* newNode = createNode(data);

    if (head == NULL) {
        head = newNode;
        head->next = head;
        head->prev = head;
    } else {
        struct Node* last = head->prev;

        newNode->next = head;
        newNode->prev = last;
        last->next = newNode;
        head->prev = newNode;
    }
    printf("Data %d ditambahkan di akhir\n", data);
}

// Hapus data
void deleteNode(int data) {
    if (head == NULL) {
        printf("List kosong\n");
        return;
    }

    struct Node* temp = head;
    struct Node* toDelete = NULL;

    do {
        if (temp->data == data) {
            toDelete = temp;
            break;
        }
        temp = temp->next;
    } while (temp != head);

    if (toDelete == NULL) {
        printf("Data %d tidak ditemukan\n", data);
        return;
    }

    printf("Data yang akan dihapus: %d\n", data);

    if (toDelete->next == toDelete) {
        head = NULL;
    } else if (toDelete == head) {
        struct Node* last = head->prev;
        head = head->next;
        head->prev = last;
        last->next = head;
    } else {
        toDelete->prev->next = toDelete->next;
        toDelete->next->prev = toDelete->prev;
    }

    free(toDelete);
    printf("Data %d berhasil dihapus\n", data);
}

// Cari data
void search(int data) {
    if (head == NULL) {
        printf("List kosong\n");
        return;
    }

    struct Node* temp = head;
    int posisi = 1;

    do {
        if (temp->data == data) {
            printf("Data %d ditemukan di posisi %d\n", data, posisi);
            return;
        }
        temp = temp->next;
        posisi++;
    } while (temp != head);

    printf("Data %d tidak ditemukan\n", data);
}

// Tampilkan data
void display() {
    if (head == NULL) {
        printf("List kosong\n");
        return;
    }

    struct Node* temp = head;
    int count = 0;

    printf("List: ");
    do {
        printf("%d ? ", temp->data);
        temp = temp->next;
        count++;
    } while (temp != head);
    printf("(kembali ke %d)\n", head->data);
    printf("Jumlah node: %d\n", count);
}

// Menu utama
int main() {
    int pilihan, data;

    printf("=== DOUBLE CIRCULAR LINKED LIST ===\n");

    while (1) {
        printf("\nMenu:\n");
        printf("1. Insert Front\n");
        printf("2. Insert End\n");
        printf("3. Delete\n");
        printf("4. Search\n");
        printf("5. Display\n");
        printf("6. Exit\n");

        printf("\nPilihan: ");
        scanf("%d", &pilihan);

        switch (pilihan) {
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
                printf("Masukkan data yang akan dihapus: ");
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
                return 0;
            default:
                printf("Pilihan tidak valid\n");
        }
    }
}

