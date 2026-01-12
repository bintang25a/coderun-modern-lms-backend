#include <stdio.h>

int main() {
    int bilangan;
    char ulang;

    do {
        printf("Enter an integer number: ");
        scanf("%d", &bilangan);

        if (bilangan % 2 == 0) {
            printf("The number %d is an EVEN number\n", bilangan);
        } else {
            printf("The number %d is an ODD number\n", bilangan);
        }

        printf("\nDo you want to enter another number? [Y/N]: ");
        scanf(" %c", &ulang);

    } while (ulang == 'Y' || ulang == 'y');

    printf("Program finished.\n");
    return 0;
}
