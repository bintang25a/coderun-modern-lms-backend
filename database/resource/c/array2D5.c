#include <stdio.h>

int main() {
    int k[3][3];
    for (int i = 0; i < 3; i++) {
        for (int j = 0; j < 3; j++) {
            k[i][j] = i + j;
            printf("%d ", k[i][j]);
        }
        printf("\n");
    }
    return 0;
}