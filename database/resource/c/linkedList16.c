#include <stdio.h>
#include <stdlib.h>

struct Node {
    int data;
    struct Node *prev;
    struct Node *next;
};

struct Node *head = NULL;
struct Node *tail = NULL;

struct Node* createNode(int data) {
    struct Node *newNode = (struct Node*)malloc(sizeof(struct Node));
    newNode->data = data;
    newNode->prev = NULL;
    newNode->next = NULL;
    return newNode;
}

void insertFront() {
    int data;
    printf("Masukkan data: ");
    scanf("%d", &data);

    struct Node *newNode = createNode(data);

    if (head == NULL) {
        head = tail = newNode;
        head->next = head;
        head->prev = head;
    } else {
        newNode->next = head;
        head->prev = newNode;
        tail->next = newNode;
        newNode->prev = tail;
        head = newNode;
    }

    printf("Data %d ditambahkan di depan\n", data);
}

void insertEnd() {
    int data;
    printf("Masukkan data: ");
    scanf("%d", &data);

    struct Node *newNode = createNode(data);

    if (head == NULL) {
        head = tail = newNode;
        head->next = head;
        head->prev = head;
    } else {
        tail->next = newNode;
        newNode->prev = tail;
        newNode->next = head;
        head->prev = newNode;
        tail = newNode;
    }

    printf("Data %d ditambahkan di akhir\n", data);
}

void deleteNode() {
    int dataYangDihapus;
    printf("Data yang akan dihapus: ");
    scanf("%d", &dataYangDihapus);

    if (head == NULL) {
        printf("List kosong, tidak ada data yang bisa dihapus.\n");
        return;
    }

    struct Node *nodeSekarang = head;
    int ditemukan = 0;

    do {
        if (nodeSekarang->data == dataYangDihapus) {
            ditemukan = 1;
            break;
        }
        nodeSekarang = nodeSekarang->next;
    } while (nodeSekarang != head);

    if (ditemukan == 0) {
        printf("Data %d tidak ditemukan\n", dataYangDihapus);
        return;
    }

    if (head == tail) {
        free(head);
        head = tail = NULL;
    } else if (nodeSekarang == head) {
        head = head->next;
        head->prev = tail;
        tail->next = head;
        free(nodeSekarang);
    } else if (nodeSekarang == tail) {
        tail = tail->prev;
        tail->next = head;
        head->prev = tail;
        free(nodeSekarang);
    } else {
        nodeSekarang->prev->next = nodeSekarang->next;
        nodeSekarang->next->prev = nodeSekarang->prev;
        free(nodeSekarang);
    }

    printf("Data %d berhasil dihapus\n", dataYangDihapus);
}

void search() {
    int dataYangDicari;
    printf("Data yang dicari: ");
    scanf("%d", &dataYangDicari);

    if (head == NULL) {
        printf("List masih kosong.\n");
        return;
    }

    struct Node *nodeSekarang = head;
    int posisi = 1;
    int ditemukan = 0;

    do {
        if (nodeSekarang->data == dataYangDicari) {
            printf("Data %d ditemukan di posisi %d\n", dataYangDicari, posisi);
            ditemukan = 1;
            break;
        }
        nodeSekarang = nodeSekarang->next;
        posisi++;
    } while (nodeSekarang != head);

    if (ditemukan == 0) {
        printf("Data %d tidak ditemukan\n", dataYangDicari);
    }
}

void display() {
    if (head == NULL) {
        printf("List kosong.\n");
        return;
    }

    struct Node *nodeSekarang = head;
    int jumlahNode = 0;

    printf("List: ");
    do {
        printf("%d ", nodeSekarang->data);
        jumlahNode++;
        if (nodeSekarang->next != head) {
            // ---- PERUBAHAN DI SINI ----
            printf("<-> ");
        }
        nodeSekarang = nodeSekarang->next;
    } while (nodeSekarang != head);

    // ---- DAN PERUBAHAN DI SINI ----
    printf("<-> (kembali ke %d)\n", head->data);
    printf("Jumlah node: %d\n", jumlahNode);
}

int main() {
    int pilihan = 0;

    while (1) {
        printf("\n=== DOUBLE CIRCULAR LINKED LIST ===\n");
        printf("Menu:\n");
        printf("1. Insert Front\n");
        printf("2. Insert End\n");
        printf("3. Delete\n");
        printf("4. Search\n");
        printf("5. Display\n");
        printf("6. Exit\n");
        printf("Pilihan: ");
        scanf("%d", &pilihan);

        switch (pilihan) {
            case 1:
                insertFront();
                break;
            case 2:
                insertEnd();
                break;
            case 3:
                deleteNode();
                break;
            case 4:
                search();
                break;
            case 5:
                display();
                break;
            case 6:
                printf("Program selesai\n");
                exit(0);
            default:
                printf("Pilihan tidak valid, coba lagi.\n");
                break;
        }
    }

    return 0;
}
