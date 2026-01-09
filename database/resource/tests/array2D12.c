#include <stdio.h>
main(){
    int matriks[3][3]={0};
    for (int i = 0; i < 3; i++){
        matriks [i][i]=1;
    }
    for (int i = 0; i < 3; i++){
        for (int j = 0; j < 3; j++){
            printf("%d ", matriks[i][j]);
        }
       printf("\n");
    }

}