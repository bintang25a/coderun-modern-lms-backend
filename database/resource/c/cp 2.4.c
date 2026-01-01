#include<stdio.h>  
int main() {  
  
    int nil;  
    char grade;  
  
    printf("masukkan nilai= "); scanf("%d",&nil);    
    if (nil<45) {  
        grade='E';  
    }  
    else if (nil<56) {  
        grade='D';  
    }  
    else if (nil<68) {  
    	grade='C';  
    }  
    else if (nil<80) {  
     	grade='B';  
    }  
    else {
    	grade='A';	
	}  
   
    printf("Nilai grade anda = %c",grade);  
 }   
