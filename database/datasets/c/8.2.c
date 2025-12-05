#include <stdio.h>
 
//prototype fungsi
int tambah(int a, int b);
 
int main() {
    //memanggil fungsi tambah dan menyimpan nilai balik fungsi ke variabel hasil
    int hasil1 = tambah(1, 2);
    int hasil2 = tambah(2, 3);
     
    printf("%d\n", hasil1);
    printf("%d\n", hasil2);
    
     
    return 0;
}
 
int tambah(int a, int b) {
    a + b;
}