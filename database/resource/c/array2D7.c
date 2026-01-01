#include <stdio.h>

int main() {
    char nama[3][10] = {"Budi", "Ani", "Siti"};
    for (int i = 0; i < 3; i++) {
        printf("%s\n", nama[i]);
    }
    return 0;
}