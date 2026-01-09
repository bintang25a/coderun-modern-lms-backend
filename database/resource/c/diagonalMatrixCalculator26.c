#include <stdio.h>

int main() {
    int n, i, j, x;
    int utama = 0, sekunder = 0;

    scanf("%d", &n);

    for (i = 0; i < n; i++) {
        for (j = 0; j < n; j++) {
            scanf("%d", &x);
            if (i == j) utama += x;
            if (j == n - i - 1) sekunder += x;
        }
    }

    printf("%d\n%d\n", utama, sekunder);
    return 0;
}
