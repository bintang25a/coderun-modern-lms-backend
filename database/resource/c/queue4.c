#include <stdio.h>
int q[5] = {10, 20}, front = 0, rear = 1;
void dequeue() {
    if (front > rear) printf("Kosong");
    else {
        printf("Keluar: %d", q[front]);
        front++;
    }
}
int main() {
    dequeue();
    return 0;
}