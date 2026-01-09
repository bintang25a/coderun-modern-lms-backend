#include <stdio.h>

int main() {
    int d[2][2] = {{10, 50}, {30, 20}};
    int max = d[0][0];
    for (int i = 0; i < 2; i++) {
        for (int j = 0; j < 2; j++) {
            if (d[i][j] > max) max = d[i][j];
        }
    }
    printf("%d", max);
    return 0;
}