#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "lista.h"

void imprimir(lista_produtos *l){
    char nome_pessoa[50];
    float valor;
    char produto[50];
    char desc[300];
    lista_produtos *aux;

    while(!lista_vazia(l)) {
        retornar_produto(l, produto, desc);
        printf("%s\n", produto);
        inserir_produto(aux, produto, desc);
        while(!sem_lances()){
            pop_pilha(l, produto, nome_pessoa, &valor);
            printf("%s\n%f\n", nome_pessoa, valor);
            push_pilha(aux, produto, nome_pessoa, &valor);
        }
    }
    while(!lista_vazia(aux)) {
        retornar_produto(aux, produto, desc);
        inserir_produto(l, produto, desc);
        while(!sem_lances()) {
            pop_pilha(aux, produto, nome_pessoa, &valor);
            push_pilha(aux, produto, nome_pessoa, &valor);
        }
    }
}

int main() {

    return 0;
}