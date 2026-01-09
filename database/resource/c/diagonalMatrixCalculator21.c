#include <stdio.h>

int main() {
    int n, i, j;
    int utama = 0, sekunder = 0;

    scanf("%d", &n);
    int m[n][n];

    for (i = 0; i < n; i++)
        for (j = 0; j < n; j++)
            scanf("%d", *(m + i) + j);

    for (i = 0; i < n; i++) {
        utama += *(*(m + i) + i);
        sekunder += *(*(m + i) + (n - i - 1));
    }

    printf("%d\n%d\n", utama, sekunder);
    return 0;
}
