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