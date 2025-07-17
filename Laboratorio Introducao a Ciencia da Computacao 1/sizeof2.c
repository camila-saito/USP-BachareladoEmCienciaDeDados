#include <stdio.h>

int main() {
    
    long int n;
    
    scanf("%ld", &n);
    
    printf("char: %ld\n", n*sizeof(char));
    printf("int: %ld\n", n*sizeof(int));
    printf("double: %ld\n", n*sizeof(double));
    
    return 0;
}