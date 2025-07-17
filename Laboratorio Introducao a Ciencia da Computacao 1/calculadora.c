#include<stdio.h>

int main()
{
    int a, b;
    
    scanf("%d %d", &a, &b);
    
    printf("Soma: %d \n", a +b);
    printf("Subtracao: %d \n", a - b);
    printf("Multiplicacao: %d \n", a * b);
    printf("Divisao Inteira: %d \n", a / b);
    printf("Divisao Racional: %.3f \n", (float)a/b);
    
    return 0;
}