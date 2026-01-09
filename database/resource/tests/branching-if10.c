#include <stdio.h>
int main(){
    int num;
    printf("Masukkan sebuah bilangan: ");
    scanf("%d", &num);
    if (num > 1){
        printf("Bilangan tersebut adalah positif");
    }
    if(num < 0){
        printf("Bilangan tersebut adalah negatif");
    }
    if(num == 0){
        printf("Bilangan tersebut adalah nol");
    }
    

}