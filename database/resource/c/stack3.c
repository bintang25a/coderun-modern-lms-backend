#include <stdio.h>
int s[5], top = -1;
void push(int v) {
    s[++top] = v;
}
int main() {
    push(100);
    return 0;
}