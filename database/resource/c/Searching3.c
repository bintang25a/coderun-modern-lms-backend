#include <stdio.h>
int main() {
    int arr[] = {1, 3, 5, 7}, cari = 5, ketemu = 0;
    for(int i=0; i<4; i++) {
        if(arr[i] == cari) ketemu = 1;
    }
    printf(ketemu ? "Ada" : "Tidak");
    return 0;
}