#include <stdio.h>

int main() {
    int nilai[] = {5, 5, 5, 5};
    int total = 0;
    for (int i = 0; i < 4; i++) {
        total += nilai[i];
    }
    printf("%d", total);
    return 0;
}