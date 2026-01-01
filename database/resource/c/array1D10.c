#include <stdio.h>

int main() {
    int data[3];
    for (int i = 0; i < 3; i++) {
        scanf("%d", &data[i]);
    }
    for (int i = 0; i < 3; i++) {
        printf("%d ", data[i]);
    }
    return 0;
}