#include <stdio.h>

int main() {
    int n, i = 0, j;
    int utama = 0, sekunder = 0;

    scanf("%d", &n);
    int m[n][n];

    while (i < n) {
        j = 0;
        while (j < n) {
            scanf("%d", &m[i][j]);
            j++;
        }
        i++;
    }

    i = 0;
    while (i < n) {
        utama += m[i][i];
        sekunder += m[i][n - 1 - i];
        i++;
    }

    printf("%d\n%d\n", utama, sekunder);
    return 0;
}
