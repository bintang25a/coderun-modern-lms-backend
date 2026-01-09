#include <stdio.h>

int main() {
    int n, i, j, nilai;
    int utama = 0, sekunder = 0;

    scanf("%d", &n);

    for (i = 0; i < n; i++) {
        for (j = 0; j < n; j++) {
            scanf("%d", &nilai);
            if (i == j)
                utama += nilai;
            if (i + j == n - 1)
                sekunder += nilai;
        }
    }

    printf("%d\n%d\n", utama, sekunder);
    return 0;
}
