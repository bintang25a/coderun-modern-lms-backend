#include <stdio.h>
#include <stdlib.h>

struct Node {
    int data;
    struct Node* next;
    struct Node* prev;
};

struct Node *head = NULL;
struct Node *tail = NULL;

struct Node *createNode(int data) {
	struct Node *newNode = (struct Node*)malloc(sizeof(struct Node));
	newNode->data = data;
    newNode->next = NULL;
	newNode->prev = NULL;
}

void insert(int data) {
    struct Node *newNode = createNode(data);
	
    if (head == NULL) {
        head = tail = newNode;
        newNode->next = head;
    }else{
        struct Node *temp = head;
        while (temp->next != head){
            temp = temp->next;
        }

        newNode->next = head;
        temp->next = newNode;
        head = newNode;
    }
    printf("Data %d ditambahkan di depan\n", data);
}

void insertEnd(int data) {
    struct Node *newNode = createNode(data);

    if (head == NULL) {
        head = newNode;
        newNode->next = head;
    }else{
        struct Node *temp = head;
        while (temp->next != head){
            temp = temp->next;
        }
        temp->next = newNode;
        newNode->next = head;
    }
    printf("Data %d ditambahkan di akhir\n", data);
}

void display() {
	if (head == NULL){
        printf("List kosong\n");
        return;
    }
    printf("List : ");
    struct Node *temp = head;

    do {
        printf("%d ", temp->data);
        temp = temp->next;
        if (temp != head)
            printf(" <-> ");
    } while (temp != head);
    printf(" -> (kembali ke %d)\n", head->data);
}

void delete(int data) {
    if (head == NULL) {
        printf("List kosong\n");
        return;
    }

    struct Node *current = head;
    struct Node *prev = NULL;

    if (current->next == head && current->data == data) {
        head = NULL;
        free(current);
        printf("Data %d dihapus\n", data);
        return;
    }

    if (current->data == data) {
        while (current->next != head) {
            current = current->next;
        }
        current->next = head->next;
        struct Node *temp = head;
        head = head->next;
        free(temp);
        printf("Data %d dihapus\n", data);
        return;
    }

    do {
        prev = current;
        current = current->next;
    } 
	while (current != head && current->data != data);

    if (current == head) {
        printf("Data %d tidak ditemukan\n", data);
        return;
    }

    prev->next = current->next;
    free(current);
    printf("Data %d dihapus\n", data);
}

void search(int data) {
    if (head == NULL) {
        printf("Data %d tidak ditemukan\n", data);
        return;
    }

    struct Node *temp = head;
    int position = 1;

    do {
        if (temp->data == data) {
            printf("Data %d ditemukan di posisi %d\n", data, position);
            return;
        }
        temp = temp->next;
        position++;
    } 
	while (temp != head);
    printf("Data %d tidak ditemukan\n", data);
}

int main() {
    printf("~~~ Double Circular Linked List ~~~\n");

    int pilihan, data;

    while (1) {
        printf("\n1. InsertFront\n2. InsertRear\n");
		printf ("3. Display\n4. Delete\n5. Search\n6. Exit\n");
        printf("Pilihan: ");
        scanf("%d", &pilihan);

        switch (pilihan)
        {
        case 1:
            printf("Masukkan data: ");
            scanf("%d", &data);
            insert(data);
            break;
        case 2:
            printf("Masukkan data: ");
            scanf("%d", &data);
            insertEnd(data);
            break;
        case 3:
            display(data);
            break;
        case 4:
            printf("Data yang dicari: ");
            scanf("%d", &data);       	
        	delete (data);
        	break;
        case 5:
        	printf("Data yang dicari: ");
            scanf("%d", &data);
        	search(data);
        	break;
        case 6:
        	printf("Program selesai\n");
        	return 0;
        default:
            printf("Pilihan tidak valid\n");
        }
    }
    return 0;
}
