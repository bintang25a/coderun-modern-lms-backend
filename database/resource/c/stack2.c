#include <stdio.h>

int stack[5];
int top = -1;

void push(int nilai) {
    if (top == 4) {
        printf("Stack Penuh\n");
    } else {
        top++;
        stack[top] = nilai;
    }
}

void pop() {
    if (top == -1) {
        printf("Stack Kosong\n");
    } else {
        printf("Data yang keluar: %d\n", stack[top]);
        top--;
    }
}

int main() {
    push(10);
    push(20);
    push(30);

    pop();
    pop();

    return 0;
}