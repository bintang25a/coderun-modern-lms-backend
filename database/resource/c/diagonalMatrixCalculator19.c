#include <stdio.h>

void hitungDiagonal(int n, int m[n][n]) {
    int i, utama = 0, sekunder = 0;

    for (i = 0; i < n; i++) {
        utama += m[i][i];
        sekunder += m[i][n - i - 1];
    }

    printf("%d\n%d\n", utama, sekunder);
}

int main() {
    int n, i, j;
    scanf("%d", &n);

    int m[n][n];
    for (i = 0; i < n; i++)
        for (j = 0; j < n; j++)
            scanf("%d", &m[i][j]);

    hitungDiagonal(n, m);
    return 0;
}
