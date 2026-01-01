#include <stdio.h>
#include <stdlib.h>

// Definisi struct Node
typedef struct Node {
    int data;
    struct Node* prev;
    struct Node* next;
} Node;

Node* head = NULL;

// Membuat node baru
Node* createNode(int data) {
    Node* newNode = (Node*)malloc(sizeof(Node));
    newNode->data = data;
    newNode->prev = newNode->next = NULL;
    return newNode;
}

// Insert di depan
void insertFront(int data) {
    Node* newNode = createNode(data);
    if (!head) {
        newNode->next = newNode->prev = newNode;
        head = newNode;
    } else {
        Node* tail = head->prev;
        newNode->next = head;
        newNode->prev = tail;
        tail->next = head->prev = newNode;
        head = newNode;
    }
    printf("Data %d ditambahkan di depan\n", data);
}

// Insert di akhir
void insertEnd(int data) {
    Node* newNode = createNode(data);
    if (!head) {
        newNode->next = newNode->prev = newNode;
        head = newNode;
    } else {
        Node* tail = head->prev;
        newNode->next = head;
        newNode->prev = tail;
        tail->next = head->prev = newNode;
    }
    printf("Data %d ditambahkan di akhir\n", data);
}

// Menghapus node berdasarkan nilai
void delete(int data) {
    if (!head) {
        printf("List kosong\n");
        return;
    }
    Node* curr = head;
    int found = 0;
    do {
        if (curr->data == data) {
            found = 1;
            if (curr->next == curr) {
                free(curr);
                head = NULL;
            } else {
                curr->prev->next = curr->next;
                curr->next->prev = curr->prev;
                if (curr == head) head = curr->next;
                free(curr);
            }
            printf("Data %d berhasil dihapus\n", data);
            break;
        }
        curr = curr->next;
    } while (curr != head);
    if (!found) {
        printf("Data %d tidak ditemukan\n", data);
    }
}

// Mencari node
void search(int data) {
    if (!head) {
        printf("List kosong\n");
        return;
    }
    Node* curr = head;
    int pos = 1;
    do {
        if (curr->data == data) {
            printf("Data %d ditemukan di posisi %d\n", data, pos);
            return;
        }
        curr = curr->next;
        pos++;
    } while (curr != head);
    printf("Data %d tidak ditemukan\n", data);
}

// Menampilkan isi list secara terurut dari kecil ke besar
void display() {
    if (!head) {
        printf("List kosong\n");
        return;
    }

    // Hitung jumlah node
    int count = 0;
    Node* curr = head;
    do {
        count++;
        curr = curr->next;
    } while (curr != head);

    // Simpan data ke array
    int* arr = (int*)malloc(count * sizeof(int));
    curr = head;
    for (int i = 0; i < count; i++) {
        arr[i] = curr->data;
        curr = curr->next;
    }

    // Urutkan array (bubble sort)
    for (int i = 0; i < count - 1; i++) {
        for (int j = 0; j < count - i - 1; j++) {
            if (arr[j] > arr[j + 1]) {
                int temp = arr[j];
                arr[j] = arr[j + 1];
                arr[j + 1] = temp;
            }
        }
    }

    // Tampilkan data
    printf("List (urut dari kecil): ");
    for (int i = 0; i < count; i++) {
        printf("%d", arr[i]);
        if (i < count - 1) printf(" ↔ ");
    }
    printf("\nJumlah node: %d\n", count);
    free(arr);
}

// Menu utama
int main() {
    int pilihan, data;
    while (1) {
        printf("\n=== DOUBLE CIRCULAR LINKED LIST ===\n");
        printf("Menu:\n");
        printf("1. Insert Front\n");
        printf("2. Insert End\n");
        printf("3. Delete\n");
        printf("4. Search\n");
        printf("5. Display (urut kecil)\n");
        printf("6. Exit\n");
        printf("Pilihan: ");
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
                printf("Data yang akan dihapus: ");
                scanf("%d", &data);
                delete(data);
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
                printf("Pilihan tidak valid!\n");
        }
    }
    return 0;
}
