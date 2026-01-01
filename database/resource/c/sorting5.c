#include <stdio.h>

void merge(int a[], int l, int m, int r) {
    int i, j, k, n1 = m-l+1, n2 = r-m;
    int L[n1], R[n2];
    for(i=0; i<n1; i++) L[i] = a[l+i];
    for(j=0; j<n2; j++) R[j] = a[m+1+j];
    i=0; j=0; k=l;
    while(i<n1 && j<n2) a[k++] = (L[i] <= R[j]) ? L[i++] : R[j++];
    while(i<n1) a[k++] = L[i++];
    while(j<n2) a[k++] = R[j++];
}
void mergeSort(int a[], int l, int r) {
    if(l < r) {
        int m = l+(r-l)/2;
        mergeSort(a, l, m);
        mergeSort(a, m+1, r);
        merge(a, l, m, r);
    }
}
int main() {
    int d[] = {5, 2, 9, 1};
    mergeSort(d, 0, 3);
    for(int i=0; i<4; i++) printf("%d ", d[i]);
    return 0;
}