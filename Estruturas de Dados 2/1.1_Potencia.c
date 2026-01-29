#include <stdio.h>
#include <stdlib.h>

long potencia(int n, long k) {
    if(k == 0) //caso base -> n^0 = 1
        return 1;
    if(k == 1) //caso base -> n^1 = n
        return n;

    long temp = potencia(n, k/2);

    if(k % 2 == 0) { //k eh par
        return (temp * temp) % 1000;
    }
    else {
        return (n * temp * temp) % 1000;
    }
}

int main() {
    int n;
    long k;

    scanf("%d %ld", &n, &k);

    long resultado = potencia(n, k);

    printf("%ld\n", resultado); //imprime os ultimos 3 digitos do resultado

    return 0;
}

/*
n^k
n int; k long

1. Base Case:
    Se o k=0, o resultado eh 1. 
    Se k=1, o resultado eh n.
2. Divisao:
    Se o k>1, dividimos o problema em subproblemas menores -> potencia com metade do expoente (n^k/2).
3. Conquista:
    Se k for par, entao potencia eh (n^k/2) * (n^k/2).
    Se k for impar, entao potencia eh n * (n^k/2) * (n^k/2). 

resultado = mod 1000
*/