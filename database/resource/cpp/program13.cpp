#include <stdio.h>

int main() {
    int i;
    int num = 10;
    char letter = 'A';

    printf("Hello, World!\n");

    if (num > 5) {
        printf("Number is greater than 5\n");

        for (i = 0; i < num; i++) {
            printf("i = %d\n", i);
        }

        while (letter <= 'Z') {
            printf("Letter: %c\n", letter);
            letter++;
        }
    }

    return 0;
}

