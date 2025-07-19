#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "lista.h"

// funcao para inicializar a lista de produtos e a fila de pessoas (fila com todas as pessoas que deram algum lance)
void criar_lista_produtos(lista_produtos *l, fila_pessoas *f) {
    l = (lista_produtos*)malloc(sizeof(lista_produtos));
    // verificar erro
    l->inicio = NULL;
    l->fim = NULL;
    l->tamanho = 0;

    f = (fila_pessoas*)malloc(sizeof(fila_pessoas));
    //verificar erro
    f->inicio = NULL;
    f->fim = NULL;
}

// funcao para inserir um produto na lista em ordem alfabetica (inserir um item na lista)
void inserir_produto(lista_produtos *l, char *nome_produto, char *desc) {
    produto *p = (produto*)malloc(sizeof(produto));
    //verificar erro
    strcpy(p->nome_produto, nome_produto); //copia o nome do produto para a variavel da lista
    strcpy(p->descricao, desc); //copia a descricao para a variavel da lista
    p->lances = NULL;
    if(l->inicio == NULL){ // caso a lista esteja vazia, sera o primeiro produto que sera cadastrado
        p->prox = NULL;
        l->inicio = p;
        l->fim = p;
    }
    else if(strcmp(p->nome_produto, l->inicio->nome_produto) < 0){ // caso o primeiro elemento venha antes do segundo >> produto deve ser o primeiro elemento da lista
        p->prox = l->inicio;
        l->inicio = p;
    }
    else{ // nao sera o primeiro elemento da lista
        produto *aux = l->inicio;
        while((aux->prox != NULL) && strcmp(p->nome_produto, aux->prox->nome_produto) > 0){ // achar a posicao em que o produto deve ser colocado na lista
            aux = aux->prox;
        }
        // quando o while parar, o novo produto p devera estar entre aux e aux->prox
        p->prox = aux->prox;
        aux->prox = p;
        if(aux->prox == NULL) // caso o produto seja o última 
            l->fim = p;
        
    }
    l->tamanho = l->tamanho + 1;
}

// funcao para inserir um lance no produto 'nome_prod' (insere um item em uma das pilhas)
void inserir_lance(lista_produtos *l, fila_pessoas *f, char *nome_prod, char *nome, float *valor) {
    produto *aux = l->inicio;
    //verificar erro
    while((aux != NULL) && strcmp(aux->nome_produto, nome_prod) != 0) { // achar o produto no qual o lance sera dado
        aux = aux->prox;
    } 
    if(aux != NULL) { // se o produto existir
        if(aux->lances == NULL || aux->lances->valor <= *valor) { // se o valor do lance dado eh valido (menor ou igual ao anterior)
            pessoa *p = (pessoa*)malloc(sizeof(pessoa)); // aloca memoria para a pessoa que deu o lance (fila do elemento da pilha)
            //verificar erro
            strcpy(p->nome, nome);
            p->prox = NULL;

            if(f->inicio == NULL){ // se eh a primeira pessoa a dar um lance, considerando todos os produtos (fila esta vazia)
                pessoa *p_fila = (pessoa*)malloc(sizeof(pessoa)); //fila de todas as pessoas de todos os produtos
                //verificar erro
                strcpy(p_fila->nome, nome);
                p_fila->prox = NULL;
                f->inicio = p_fila;
                f->fim = p_fila;
            }
            else{ // tem outras pessoas que ja deram lances
                pessoa *aux_fila = f->inicio;
                while(aux_fila != NULL) { // verifica se a pessoa ja deu algum outro lance (se ela tiver dado, ela ja estara na lista)
                    if(strcmp(aux_fila->nome, nome) == 0)
                        break;
                    aux_fila = aux_fila->prox;
                }
                if(aux_fila == NULL) { // pessoa nao foi achada na lista >> esse eh o primeiro lance que ela da
                    pessoa *p_fila = (pessoa*)malloc(sizeof(pessoa)); //fila
                    //verificar erro
                    strcpy(p_fila->nome, nome);
                    p_fila->prox = NULL;
                    f->fim->prox = p_fila;
                    f->fim = p_fila;
                }
            }
            
            if(aux->lances->valor < *valor){ // valor do lance anterior eh estritamente menor do que o lance dado >> lance vai para o topo da pilha
                lance *lan = (lance*)malloc(sizeof(lance));
                //verificar erro
                lan->valor = *valor;
                lan->prox = aux->lances;
                lan->inicio_pessoas = p; // insere a pessoa na fila de pessoas que deram um lance de determinado valor
                lan->fim_pessoas = p;
                aux->lances = lan;
            }
            else if(aux->lances->valor == *valor){ // valor eh igual ao anterior >> so insere a pessoa na fila do valor
                aux->lances->fim_pessoas->prox = p;
                aux->lances->fim_pessoas = p;
            }
            else if (aux->lances == NULL){ // nenhum lance foi dado anteriormente
                lance *lan = (lance*)malloc(sizeof(lance));
                //verificar erro
                lan->valor = *valor;
                lan->prox = NULL;
                lan->inicio_pessoas = p;
                lan->fim_pessoas = p;
                aux->lances = lan;
            }
        }
        //else erro (valor menor do que o necessario)
    }
    //else erro (caiu fora da lista >> produto nao existe)
}

// funcao que esvazia a lista inteira
void esvaziar_lista(lista_produtos *l, fila_pessoas *f) { //free em tudo
    produto *auxP = l->inicio;
    while(auxP != NULL) { // ponteiro para percorrer os produtos (lista)
        lance *auxL = auxP->lances;
        while(auxP->lances != NULL) { // ponteiro para percorrer os lances (pilhas)
            pessoa *aux_pessoa = auxL->inicio_pessoas;
            while(aux_pessoa != NULL) { // ponteiro para percorrer as pessoas (filas)
                auxL->inicio_pessoas = auxL->inicio_pessoas->prox; // passa o inicio para o proximo, enquanto o auxiliar se mantem no inicio (primeiro da fila)
                free(aux_pessoa); // libera a memoria do auxiliar, que esta no começo da fila
                aux_pessoa = auxL->inicio_pessoas; // passa o auxiliar para o ponteiro do inicio
            }
            auxP->lances = auxP->lances->prox;
            free(auxL);
            auxL = auxP->lances;
        }
        l->inicio = l->inicio->prox;
        free(auxP);
        auxP = l->inicio;
    }
    pessoa *aux_lista = f->inicio;
    while(aux_lista != NULL) { // ponteiro que vai percorrer a fila de pessoas (fila com todas as pessoas de todos os lances de todos os produtos)
        f->inicio = f->inicio->prox;
        free(aux_lista);
        aux_lista = f->inicio;
    }
}

// funcao para verificar se a lista de produtos esta vazia (nenhum produto foi cadastrado)
int lista_vazia(lista_produtos *l) {
    if(l->inicio == NULL)
        return 1;
    else return 0;
}

// funcao que retorna os valores do estrutura no inicio da lista
void retorno_inicio_listaProdutos(lista_produtos *l, char *nome_produto, char *descricao) {
    strcpy(nome_produto, l->inicio->nome_produto);
    strcpy(descricao, l->inicio->descricao);
}

// funcao para verificar se a pilha de lances de determinado produto esta vazia (nenhum lance foi dado nesse produto)
int pilha_vazia(lista_produtos *l, char *nome_produto) {
    produto *aux = l->inicio;
    while(strcmp(aux->nome_produto, nome_produto) != 0) { //ponteiro para achar o produto no qual quer se fazer a verificacao
        aux = aux->prox;
    }
    // if(aux == NULL) erro se nao achar produto
    if(aux->lances == NULL)
        return 1;
    else return 0;
}


// funcao para retirar o lance do topo da pilha (desempilhar/pop o topo da pilha)
void pop_pilha(lista_produtos *l, char *nome_produto, char *nome_pessoa, float *valor) {
    produto *aux = l->inicio;
    while(strcmp(aux->nome_produto, nome_produto) != 0) { // ponteiro para achar o produto no qual se quer tirar um lance
        aux = aux->prox;
    }
    //if(aux == NULL || aux->lances == NULL) erro
    lance *aux_lance = aux->lances;
    pessoa *aux_pessoa = aux->lances->inicio_pessoas;
    if(aux->lances->inicio_pessoas == aux->lances->fim_pessoas) { // se tiver somente uma pessoa na fila de pessoas do lance do topo >> libera memoria da pessoa e do lance
        strcpy(nome_pessoa, aux_pessoa->nome);
        *valor = aux_lance->valor;
        free(aux_pessoa); // libera a memoria da pessoa 
        aux->lances = aux->lances->prox; // o topo da pilha de lances vai para o proximo
        free(aux_lance); // libera a memoria do elemento que estava no topo da pilha
    }
    else { // tem mais de uma pessoa no lance >> libera a memoria apenas da pessoa no inicio da fila
        strcpy(nome_pessoa, aux_pessoa->nome);
        *valor = aux_lance->valor;
        aux_lance->inicio_pessoas =  aux_pessoa->prox; // passa o inicio da fila para o proximo
        free(aux_pessoa); // libera a memoria do elemento/pessoa que estava no inicio da fila
    }
}

// funcao para retirar um determinado produto da lista (pop em um elemento da lista)
void pop_lista(lista_produtos *l, char *nome_produto) {
    // if(!pilha_vazia(l, nome_produto)) erro
    produto *aux = l->inicio;
    produto *anterior = NULL; // ponteiro que aponta para o anterior de aux
    while(aux != NULL && strcmp(aux->nome_produto, nome_produto) != 0) { // percorre a lista de produtos ate achar o produto que sera retirado
        anterior = aux;
        aux = aux->prox;
    }
    //if (aux == NULL || aux->lances != NULL) erro
    if(aux == l->inicio) { // se o produto eh o inicio da lista
        l->inicio = l->inicio->prox;
        if(aux == l->fim) // se o produto for o unico da lista
            l->fim = NULL;
    }
    else {
        anterior->prox = aux->prox;
        if(aux == l->fim) // se o produto estiver no final da lista
            l->fim = anterior;
    }
    free(aux);

    l->tamanho = l->tamanho - 1; // decrementa o tamanho da lista
}



void imprimir(lista_produtos *l, fila_pessoas *f){
    char nome_pessoa[50];
    float valor;
    char nome_produto[50];
    char descricao[300];
    lista_produtos *aux;

    //criar lista

    while(!lista_vazia(l)) {
        retorno_inicio_listaProdutos(l, nome_produto, descricao);
        inserir_produto(aux, nome_produto, descricao);
        while(!pilha_vazia(l, nome_produto)) {
            pop_pilha(l, nome_produto, nome_pessoa, &valor); //retorna o topo da pilha com o nome da pessoa >> se tiver 2 pessoas no lance, a funcao pop devera ser chamada 2 vezes
            inserir_lance(aux, f, nome_produto, nome_pessoa, &valor);
        }
        pop_lista(l, nome_produto);
    }
    while(!lista_vazia(aux)) {
        retorno_inicio_listaProdutos(aux, nome_produto, descricao);
        inserir_produto(l, nome_produto, descricao);
        while(!pilha_vazia(aux, nome_produto)) {
            pop_pilha(aux, nome_produto, nome_pessoa, &valor);
            inserir_lance(aux, f, nome_produto, nome_pessoa, &valor);
        }
        pop_lista(aux, nome_produto);
    }
}