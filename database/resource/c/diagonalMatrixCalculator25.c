#include <stdio.h>

int main() {
    int n, i;
    int utama = 0, sekunder = 0;

    scanf("%d", &n);
    int m[n * n];

    for (i = 0; i < n * n; i++)
        scanf("%d", &m[i]);

    for (i = 0; i < n; i++) {
        utama += m[i * n + i];
        sekunder += m[i * n + (n - 1 - i)];
    }

    printf("%d\n%d\n", utama, sekunder);
    return 0;
}
