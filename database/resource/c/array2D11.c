#include <stdio.h>
#define BARIS 3
#define KOLOM 3
main(){
    int a[BARIS][KOLOM] = {{1,2,3},
                    {4,5,6},
                    {7,8,9}};
    int b[BARIS][KOLOM] = {{1,2,3},
                    {4,5,6},
                    {7,8,9}};               
    int jum[BARIS][KOLOM];

    for(int i = 0; i < BARIS; i++){  
    for(int j = 0; j < KOLOM; j++){
    printf("A[%d][%d]= ",i,j);
    scanf("%d",&a[i][j]);
    printf("B[%d][%d]= ",i,j);
    scanf("%d",&b[i][j]);
    }     
    }     
    for(int i = 0; i < BARIS; i++){ 
    for(int j = 0; j < KOLOM; j++){
    printf("a[%d][%d]=%d\n",i,j,a[i][j]);
    printf("b[%d][%d]=%d\n",i,j,b[i][j]);
    jum[i][j] = a[i][j] + b[i][j];
    }
    }
    for(int i = 0; i < BARIS; i++){ 
    for(int j = 0; j < KOLOM; j++){
    printf("%d ", jum[i][j]);
    }
    printf("\n");
    }
        
    
   

             
    


}
