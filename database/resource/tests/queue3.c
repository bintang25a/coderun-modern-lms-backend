#include <stdio.h>
int queue[5], rear = -1;
void enqueue(int n) {
    if (rear == 4) printf("Penuh");
    else {
        rear++;
        queue[rear] = n;
    }
}
int main() {
    enqueue(10);
    enqueue(20);
    return 0;
}