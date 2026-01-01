#include <stdio.h>

int main() {
    int nilai[2][2] = {{80, 90}, {70, 80}};
    for (int i = 0; i < 2; i++) {
        float s = 0;
        for (int j = 0; j < 2; j++) {
            s += nilai[i][j];
        }
        printf("Rata baris %d: %.2f\n", i, s/2);
    }
    return 0;
}