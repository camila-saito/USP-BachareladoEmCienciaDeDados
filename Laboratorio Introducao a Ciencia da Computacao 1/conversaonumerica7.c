#include<stdio.h>


int main()
{
    char c;
    int i;
    
    scanf("%c %d", &c, &i);
    
    printf("numero correspondente: %d\n", c);
    printf("caracter correspondente: %c\n", i);
    printf("octal: %o\n", i);
    printf("hexadecimal: %x\n", i);
    
    return 0;
}