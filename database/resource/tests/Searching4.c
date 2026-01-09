#include <stdio.h>
int main() {
    int a[] = {10, 20, 30, 40}, k = 30, low = 0, high = 3;
    while(low <= high) {
        int mid = (low + high) / 2;
        if(a[mid] == k) { printf("Found"); break; }
        if(a[mid] < k) low = mid + 1; else high = mid - 1;
    }
    return 0;
}