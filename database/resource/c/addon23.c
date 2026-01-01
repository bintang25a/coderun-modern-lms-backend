
#include <stdio.h>

int main() {
    int array[] = {19, 35};
    int size = sizeof(array) / sizeof(array[0]);
    int max = array[0];    

  if (size == 0) {
   printf("Nilai maksimum dalam array adalah: %d\n", max);
  }

    for (int i = 1; i < size; i++) {
        if (array[i] > max) {
            max = array[i];
        }
    }
    
    printf("Nilai maksimum dalam array adalah: %d\n", max);
    
    return 0;
}
