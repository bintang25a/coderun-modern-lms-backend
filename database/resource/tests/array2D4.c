#include <stdio.h>

int main() {
    int m[2][2];
    for (int i = 0; i < 2; i++) {
        for (int j = 0; j < 2; j++) {
            scanf("%d", &m[i][j]);
        }
    }
    return 0;
}