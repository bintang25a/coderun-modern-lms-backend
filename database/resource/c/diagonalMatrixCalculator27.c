#include <stdio.h>

void input(int n, int m[n][n]) {
    for (int i = 0; i < n; i++)
        for (int j = 0; j < n; j++)
            scanf("%d", &m[i][j]);
}

void proses(int n, int m[n][n]) {
    int utama = 0, sekunder = 0;
    for (int i = 0; i < n; i++) {
        utama += m[i][i];
        sekunder += m[i][n - 1 - i];
    }
    printf("%d\n%d\n", utama, sekunder);
}

int main() {
    int n;
    scanf("%d", &n);
    int m[n][n];
    input(n, m);
    proses(n, m);
    return 0;
}
