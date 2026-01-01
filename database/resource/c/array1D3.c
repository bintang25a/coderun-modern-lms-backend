#include <stdio.h>

int main() {
    int asli[100], balik[100], jml_data, i;

    printf("Masukkan jumlah data = ");
    scanf("%d", &jml_data);

    printf("Bilangan = ");
    for (i = 0; i < jml_data; i++)
        scanf("%d", &asli[i]);

    for (i = 0; i < jml_data; i++)
        balik[i] = asli[jml_data - i - 1];

    printf("Data setelah dibalik: ");
    for (i = 0; i < jml_data; i++)
        printf("%d ", balik[i]);

    return 0;
}