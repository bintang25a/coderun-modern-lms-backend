#include <stdio.h>
int main() {
    int data[] = {12, 44, 23}, x = 44;
    for(int i=0; i<3; i++) {
        if(data[i] == x) printf("Indeks ke-%d", i);
    }
    return 0;
}