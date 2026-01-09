#include <stdio.h>

int main() {
    int aseli[2][3] = {{1,2,3}, {4,5,6}};
    for (int j = 0; j < 3; j++) {
        for (int i = 0; i < 2; i++) {
            printf("%d ", aseli[i][j]);
        }
        printf("\n");
    }
    return 0;
}