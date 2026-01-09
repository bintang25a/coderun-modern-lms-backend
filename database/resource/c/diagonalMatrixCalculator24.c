#include <stdio.h>

int main() {
    int r, c, i, j;
    int utama = 0, sekunder = 0;

    scanf("%d %d", &r, &c);
    if (r != c) return 0;

    int m[r][c];

    for (i = 0; i < r; i++)
        for (j = 0; j < c; j++)
            scanf("%d", &m[i][j]);

    for (i = 0; i < r; i++) {
        utama += m[i][i];
        sekunder += m[i][c - 1 - i];
    }

    printf("%d\n%d\n", utama, sekunder);
    return 0;
}
