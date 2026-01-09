#include <stdio.h>
int s[2] = {5, 10}, top = 1;
void pop() {
    printf("Pop: %d", s[top--]);
}
int main() {
    pop();
    return 0;
}