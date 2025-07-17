#include<stdio.h>

int main()
{
    int dia, mes, ano;
    
    scanf("%2d %2d %4d", &dia, &mes, &ano);
    
    printf("dia = %.2d / mes = %.2d / ano = %.4d \n", dia, mes, ano);
    return 0;
}
