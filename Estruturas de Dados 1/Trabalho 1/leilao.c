#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "leilao.h"

typedef struct pessoa{
    char nome[50];
    struct pessoa *prox;
}pessoa;

typedef struct lance{
    float valor;
    pessoa *inicio_pessoas;
    struct lance *prox;
}lance;

typedef struct produto{
    char nome_produto[50];
    char descricao[300];
    lance *lances;
    struct produto *prox;
}produto;

typedef struct lista_produtos{
    produto *inicio;
    produto *fim;
    int tamanho;
}lista_produtos;


//criar lista de produtos 
int criar_lista_produtos(lista_produtos **l){
    *l =(lista_produtos*)malloc(1*sizeof(lista_produtos));
    if(l == NULL) {
        return 0;
    }
    (*l)->inicio = NULL;
    (*l)->fim = NULL;
    (*l)->tamanho = 0;
    return 1;
}

//inserir produto na lista em ordem alfabetica
int inserir_produto(lista_produtos *l, char *nome_produto, char *desc)    {
    produto *p = (produto*)malloc(sizeof(produto));
    if(p == NULL) {
        return 0;
    }
    strcpy(p->nome_produto,nome_produto); //copia o nome do produto para a variavel da lista
    strcpy(p->descricao, desc); //copia a descricao para a variavel da lista
    p->lances = NULL;
    p->prox = NULL;

    if (l->inicio == NULL){ // caso a lista esteja vazia, sera o primeiro produto que sera cadastrado
        l->inicio = p;
        l->fim = p;
    } 
    else if(strcmp(p->nome_produto, l->inicio->nome_produto) <0){ // caso o primeiro elemento venha antes do segundo >> produto deve ser o primeiro elemento da lista
        p->prox = l->inicio;
        l->inicio = p;
    } 
    else{ // nao sera o primeiro elemento da lista
        produto *aux =l->inicio;
        while (aux->prox != NULL &&strcmp(p->nome_produto, aux->prox->nome_produto)>0){
            aux = aux->prox;
        }
        // quando o while parar, o novo produto p devera estar entre aux e aux->prox
        p->prox = aux->prox;
        if (aux->prox == NULL){ // caso o produto seja o último
            l->fim = p;
        }
        aux->prox = p;
    }
    l->tamanho++;
    return 1;
}

//inserir um lance em um produto (insere um item na pilha)
int inserir_lance(lista_produtos *l,char *nome_produto, char *nome_pessoa,float valor){
    produto *p = l->inicio;

    //encontro o produto pelo nome
    while (p != NULL && strcmp(p->nome_produto, nome_produto) != 0){
        p = p->prox;
    }

    // produto existe e foi encontrado
    if (p != NULL){
        lance *lan_atual = p->lances;
        float ultimo_valor = 0;
        
        if(lan_atual != NULL){
            ultimo_valor = lan_atual->valor;
        }
        //verificar se o novo lance eh maior ou menor ao ultimo lance cadastrado
        if(valor < ultimo_valor){
            return 0; //se for menor, lance rejeitado 
        }

        //criar um novo lance
        lance *novo_lance = (lance*)malloc(1*sizeof(lance));
        if(novo_lance == NULL) {
            return 0;
        }
        novo_lance->valor =valor;
        novo_lance->prox =NULL;

        //criar uma nova pessoa para o lance
        pessoa *nova_pessoa = (pessoa*)malloc(1*sizeof(pessoa));
        if(nova_pessoa == NULL) {
            return 0;
        }
        strcpy(nova_pessoa->nome, nome_pessoa);
        nova_pessoa->prox = NULL;

        novo_lance->inicio_pessoas = nova_pessoa; //coloca a pessoa na lista daquele lance

        //inserir lance no topo da pilha de lances (pilha dinamica)
        novo_lance->prox = p->lances;
        p->lances = novo_lance;
    
        return 1; //lance foi aceito
    }
    return 0; //produto nao foi encontrado
}
//obter quantidade de produtos
int quantidade_produtos(lista_produtos*l){
    return l->tamanho;
}

//verificar se um produto existe ou nao
int produto_nao_existe(lista_produtos *l, char *nome_produto){
    produto *p = l->inicio;
    while (p != NULL){ //percorrer lista de produtos
        if(strcmp(p->nome_produto, nome_produto) != 0){
            return 1; //produto foi encontrado na lista
        }
        p = p->prox;
    }
    return 0;
}

// obter a quantidade de lances de um produto
int quantidade_lances(lista_produtos *l,char *nome_produto){
    produto *p = l->inicio;
    //percorre a lista para achar o produto pelo nome
    while(p != NULL && strcmp(p->nome_produto,nome_produto)!= 0){
        p = p->prox;
    }
    //produto foi encontrado
    if(p!= NULL){
        //percorre pelos lances
        lance *lan_atual = p->lances;
        int cont = 0;
        while(lan_atual != NULL){
            cont++;
            lan_atual = lan_atual->prox;
        }
        return cont;
    }
    return 0;  //produto nao encontrado ou nao tem lances
}

//obter o próximo produto na lista
produto* proximo_produto(lista_produtos *l,produto *prod_atual){
    //retorna o primeiro produto
    if(prod_atual == NULL){
        return l->inicio;
    }
    //retorna o proximo
    return prod_atual->prox;
}

//obter o próximo lance de um produto
lance *proximo_lance(produto *p, lance *lan_atual){
    //retorna o primeiro lance
    if(lan_atual == NULL) {
        return p->lances;
    }
    //retorna o proximo
    return lan_atual->prox;
}

// obter a próxima pessoa de um lance
pessoa *proxima_pessoa(lance *l, pessoa*pessoa_atual){
    //retorna a primeira pessoa da fila
    if(pessoa_atual == NULL){
        return l->inicio_pessoas;
    }
    //retorna a proxima
    return pessoa_atual->prox;
}

//acessar campos internos das estruturas:
//acessar nome do produto
int nome_produto(produto *p,char *nome_produto){
    if(p != NULL){
        strcpy(nome_produto, p->nome_produto);
        return 1;
    }
    return 0;
}

//acessar descricao do produto
int descricao_produto(produto *p, char *descricao){
    if(p != NULL) {
        strcpy(descricao, p->descricao);
        return 1;
    }
    return 0;
}

//acessar valor do lance
int valor_lance(lance *l, float *valor){
    if(l != NULL) {
        *valor = l->valor;
        return 1;
    }
    return 0;
}

//acessar nome da pessoa que deu o lance
int nome_pessoa(pessoa *p, char *nome) {
    if(p != NULL){
        strcpy(nome, p->nome);
        return 1;
    }
    return 0;
}

//sugerir outros produtos para a pessoa que teve seu lance ultrapassado em um produto x
int sugerir_produtos_para_pessoa(lista_produtos *l,char *nome_pessoa, char sugeridos[][50],int *qtd_sugestoes){
    produto *p = NULL;
    //qtd de sugestoes
    *qtd_sugestoes = 0; 

    // percorre os produtos da lista
    while((p = proximo_produto(l, p))!= NULL){
        int pessoa_deu_lance = 0;
        int pessoa_ultrapassada = 0;

        lance *lan_atual = NULL;
        lance *ultimo_lance = NULL;

        //percore os lances do produto
        while((lan_atual = proximo_lance(p, lan_atual)) != NULL){
            pessoa *pessoa_atual = NULL;
            //itera sobre as pessoas de cada lance
            while((pessoa_atual = proxima_pessoa(lan_atual,pessoa_atual)) != NULL){
                //compara os nomes
                if(strcmp(pessoa_atual->nome, nome_pessoa) == 0){ //a pessoa x deu um lance para o produto
                    pessoa_deu_lance = 1;
                    if (ultimo_lance != NULL && lan_atual != ultimo_lance){ //a pessoa x teve seu lance ultrapassado pela pessoa y
                        pessoa_ultrapassada = 1;
                    }
                }
            }
            //o ultimo lance eh o atual
            ultimo_lance = lan_atual;
        }

        //se a pessoa foi ultrapassada, adiciona o nome do produto
        if(pessoa_ultrapassada){
            strcpy(sugeridos[*qtd_sugestoes], p->nome_produto);
            (*qtd_sugestoes)++;
        }
    }

    return 1;
}

//vencedor de um produto
int vencedor_produto(lista_produtos *l, char *nome_produto, char *nome_vencedor, float *valor_vencedor){
    produto *p = l->inicio;
    while(p!=NULL && strcmp(p->nome_produto, nome_produto) != 0){
        p = p->prox;
    }
    
    //produto foi encontrado e tem lances
    if(p != NULL && p->lances!=NULL){
        lance *lan_atual = p->lances;
        lance *lan_vencedor = lan_atual; //recebe o topo da pilha (ultimo lance dado)
        float maior_valor = lan_atual->valor; //primeiro lance feito no maior valor

        //percorro buscando o maior valor
        while(lan_atual!= NULL){
            if(lan_atual->valor>=maior_valor){
                //se o valor do lance atual for maior ou igual ao maior valor encontrado
                //atualiza o vencedor para o ultimo lance com o maior valor
                maior_valor = lan_atual->valor;
                lan_vencedor = lan_atual; //ultimo lance com maior valor eh vencedor
            }
            lan_atual = lan_atual->prox; //avanca para o proximo
        }

        //obtenho o nome do vencedor
        strcpy(nome_vencedor, lan_vencedor->inicio_pessoas->nome);
        *valor_vencedor = lan_vencedor->valor;

        return 1; //vencedor encontrado
    }

    return 0; //sem lances ou produto nao encontrado
}

//remover um produto pelo nome
int remover_produto(lista_produtos *l,char *nome_produto){
    //se nao tiver produtos
    if (l->inicio == NULL){
        return 0;
    }

    produto *aux = l->inicio, *anterior = NULL;
    //se o nome do produto corresponde ao primeiro produto, eu apago ele
    if (strcmp(aux->nome_produto, nome_produto) == 0){
        l->inicio = aux->prox;
        free(aux);
        return 1;
    }
    //percorro a lista em busca do prodto
    while(aux != NULL && strcmp(aux->nome_produto, nome_produto) != 0){
        anterior = aux;
        aux = aux->prox;
    }
    if(aux != NULL){ // apago o produto
        anterior->prox = aux->prox;
        free(aux);
        return 1;
    }
    return 0;
}

//esvaziar lista e liberar memoria
void esvaziar_lista(lista_produtos *l){
    produto *auxp = l->inicio;
    //percorrer produtos
    while(auxp != NULL){
        lance *auxl = auxp->lances;
        //percorrer lances
        while (auxl != NULL) {
            pessoa *auxpessoa = auxl->inicio_pessoas;
            //percorrer pessoas e liberar memoria
            while (auxpessoa != NULL) {
                pessoa *temp_p = auxpessoa;
                auxpessoa = auxpessoa->prox;
                free(temp_p);
            }
            //liberar lances
            lance *temp_l = auxl;
            auxl = auxl->prox;
            free(temp_l);
        }
        //liberar produtos
        produto *temp_p = auxp;
        auxp = auxp->prox;
        free(temp_p);
    }
}

// Funcao para verificar se a lista de produtos esta vazia
int lista_vazia(lista_produtos *l) {
    return l->inicio == NULL;
}
