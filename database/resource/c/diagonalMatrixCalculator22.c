#include <stdio.h>

int main() {
    int n, i = 0, j;
    int utama = 0, sekunder = 0;

    scanf("%d", &n);
    int m[n][n];

    do {
        j = 0;
        do {
            scanf("%d", &m[i][j]);
            j++;
        } while (j < n);
        i++;
    } while (i < n);

    for (i = 0; i < n; i++) {
        utama += m[i][i];
        sekunder += m[i][n - i - 1];
    }

    printf("%d\n%d\n", utama, sekunder);
    return 0;
}
