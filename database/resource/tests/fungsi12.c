#include <stdio.h>
void main()
{
int c;
printf("Pilih:\n[1]+\n[2]-\n[3]*\n[4]/\n");
printf("Masukan Pilihan: ");
scanf("%d", &c);
if (c == 1){
    int a;
int b;
    printf("Masukan Bilangan A: ");
scanf("%d", &a);
printf("Masukan Bilangan B: ");
scanf("%d", &b);

int d = a+b;
printf("Hasil %d\n", d);

}
else if (c == 2){
    int a;
int b;
    printf("Masukan Bilangan A: ");
scanf("%d", &a);
printf("Masukan Bilangan B: ");
scanf("%d", &b);
int d = a-b;
printf("Hasil %d\n", d);
}
else if (c == 3){
    int a;
int b;
    printf("Masukan Bilangan A: ");
scanf("%d", &a);
printf("Masukan Bilangan B: ");
scanf("%d", &b);
int d = a*b;
printf("Hasil %d\n", d);
}
else if (c == 4){
    float a;
float b;
    printf("Masukan Bilangan A: ");
scanf("%d", &a);
printf("Masukan Bilangan B: ");
scanf("%d", &b);
float d = a/b;
printf("Hasil %f\n", d);
}

}