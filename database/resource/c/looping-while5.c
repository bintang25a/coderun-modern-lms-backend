#include <stdio.h>

int main() {
    int sum = 0, i = 1;
    while (i <= 3) {
        sum += i;
        i++;
    }
    printf("%d", sum);
    return 0;
}