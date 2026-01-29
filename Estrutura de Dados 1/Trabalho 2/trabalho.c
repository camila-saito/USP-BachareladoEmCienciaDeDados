#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "trabalho.h"

// estruturas
typedef struct no_lista_arvore{
    char nome_filme[50];
    struct no_lista_arvore *prox;
} No_lista;

typedef struct no_arvore{
    int numero_USP;
    char nome[50];
    struct no_arvore *esquerda, *direita;
    No_lista *lista_inicio, *lista_fim;
    int altura;
} No_arvore;

typedef struct no_lista_filme{
    char nome_filme[50];
    int contador;
    struct no_lista_filme *prox;
} No_lista_geral;

typedef struct l{
    No_lista_geral *inicio, *fim;
} lista;


// Função para retornar a raiz de uma árvore
No_arvore *raiz(No_arvore *a){
    return a;
}

// Função que cria um novo nó da árvore
No_arvore* novo_no_arvore(int x, char *nome_pessoa){
    No_arvore *novo = (No_arvore*)malloc(sizeof(No_arvore));
    if(novo) {
        novo->numero_USP = x;
        strcpy(novo->nome, nome_pessoa);
        novo->esquerda = NULL;
        novo->direita = NULL;
        novo->lista_inicio = NULL;
        novo->lista_fim = NULL;
        novo->altura = 0;
    }
    return novo;
}

//calcular a altura de um nó
int altura_no(No_arvore *no){
    if(no == NULL) {
        return -1;
    } else {
        return no->altura;
    }
}

// Função para verificar qual altura da subárvore é maior
int maior(int a, int b){
    if(a > b) {
        return a;
    } else {
        return b;
    }
}

//fator de balanceamento
int fb(No_arvore *r){
    if(r) {
        return (altura_no(r->esquerda) - altura_no(r->direita));
    } else {
        return 0;
    }
}

//rotacao simples à esquerda
No_arvore* rotacao_esquerda(No_arvore **r){
    No_arvore *y = (*r)->direita;
    No_arvore *f = y->esquerda;

    y->esquerda = *r;
    (*r)->direita = f;
    *r = y;

    (*r)->esquerda->altura = maior(altura_no((*r)->esquerda->esquerda), altura_no((*r)->esquerda->direita)) + 1;
    (*r)->altura = maior(altura_no((*r)->esquerda), altura_no((*r)->direita)) + 1;
    return y;
}

//rotacao simples a direita
No_arvore* rotacao_direita(No_arvore **r){
    No_arvore *x = (*r)->esquerda;
    No_arvore *y = x->direita;

    x->direita = *r;
    (*r)->esquerda = y;
    *r = x;

    (*r)->altura = maior(altura_no((*r)->esquerda), altura_no((*r)->direita)) + 1;
    x->altura = maior(altura_no(x->esquerda), altura_no(x->direita)) + 1;
    return x;
}

//rotacao esquerda-direita
No_arvore* rotacao_esquerda_direita(No_arvore **r){
    No_arvore *pai = *r;
    No_arvore *filho = pai->esquerda;
    No_arvore *neto = filho->direita;

    pai->esquerda = neto->direita;
    filho->direita = neto->esquerda;
    neto->esquerda = filho;
    neto->direita = pai;

    *r = neto;
    pai->altura = maior(altura_no(pai->esquerda), altura_no(pai->direita)) + 1;
    filho->altura = maior(altura_no(filho->esquerda), altura_no(filho->direita)) + 1;
    neto->altura = maior(altura_no(neto->esquerda), altura_no(neto->direita)) + 1;
    return neto;
}

//rotacao direita-esquerda
No_arvore* rotacao_direita_esquerda(No_arvore **r) {
    No_arvore *x = *r;
    No_arvore *y = x->direita;
    No_arvore *z = y->esquerda;

    x->direita = z->esquerda;
    y->esquerda = z->direita;
    z->esquerda = x;
    z->direita = y;

    *r = z;
    x->altura = maior(altura_no(x->esquerda), altura_no(x->direita)) + 1;
    y->altura = maior(altura_no(y->esquerda), altura_no(y->direita)) + 1;
    z->altura = maior(altura_no(z->esquerda), altura_no(z->direita)) + 1;
    return z;
}

//balancear a arvore
No_arvore* balancear(No_arvore* raiz){
    if(raiz == NULL) {
        return NULL;
    }

    int fatb = fb(raiz);

    //Caso 1: Rotação à esquerda
    if(fatb < -1 && fb(raiz->direita)<= 0){
        raiz = rotacao_esquerda(&raiz);
    }
    //Caso 2: Rotação à direita
    else if(fatb> 1 && fb(raiz->esquerda)>= 0){
        raiz = rotacao_direita(&raiz);
    }
    //Caso 3: Rotação esquerda-direita
    else if(fatb> 1 && fb(raiz->esquerda)< 0){
        raiz = rotacao_esquerda_direita(&raiz);
    }
    //Caso 4: Rotação direita-esquerda
    else if(fatb< -1 && fb(raiz->direita) >0){
        raiz = rotacao_direita_esquerda(&raiz);
    }

    return raiz;
}

//inserir um aluno na árvore
No_arvore* inserir(No_arvore* raiz,int num,char *nome_aluno){
    if (raiz == NULL) {
        return novo_no_arvore(num, nome_aluno);
    } else {
        if (num < raiz->numero_USP) {
            raiz->esquerda = inserir(raiz->esquerda, num, nome_aluno);
        } else {
            raiz->direita = inserir(raiz->direita, num, nome_aluno);
        }
    }

    //atualizando a altura do no
    raiz->altura = maior(altura_no(raiz->esquerda), altura_no(raiz->direita)) + 1;

    //Balanceamento apos inserção
    raiz = balancear(raiz);

    return raiz;
}

//remover um aluno da árvore
No_arvore* remover_aluno(No_arvore *raiz, int num, lista *l){
    if (raiz == NULL) {
        return NULL;
    }

    if (num < raiz->numero_USP){
        raiz->esquerda =remover_aluno(raiz->esquerda, num, l);
    } 
    else if (num > raiz->numero_USP){
        raiz->direita = remover_aluno(raiz->direita, num, l);
    } 
    else {
        //no encontrado
        if(raiz->esquerda == NULL && raiz->direita == NULL){
            remover_filme_arvore(l, raiz); //remove filmes associados ao aluno
            free(raiz);
            return NULL;
        } 
        else if(raiz->esquerda == NULL) {
            No_arvore *temp = raiz->direita;
            remover_filme_arvore(l, raiz); 
            free(raiz);
            return temp;
        } 
        else if(raiz->direita ==NULL){
            No_arvore *temp = raiz->esquerda;
            remover_filme_arvore(l, raiz);
            free(raiz);
            return temp;
        } 
        else{
            //Nó com dois filhos: encontrar o ancestral (máximo da subárvore esquerda)
            No_arvore *temp = raiz->esquerda;
            while (temp->direita != NULL){
                temp = temp->direita;
            }

            // Copiar os dados do ancestral para o nó atual
            raiz->numero_USP = temp->numero_USP;
            strcpy(raiz->nome, temp->nome);

            // Remover o ancestral
            raiz->esquerda = remover_aluno(raiz->esquerda, temp->numero_USP, l);
        }
    }

    // Atualizar a altura do nó
    raiz->altura =maior(altura_no(raiz->esquerda), altura_no(raiz->direita)) + 1;

    // Balancear a árvore
    raiz = balancear(raiz);

    return raiz;
}


// Função para listar os alunos
void listar_alunos(No_arvore *raiz){
    if(raiz != NULL){
        printf("%s\n", raiz->nome);
        listar_alunos(raiz->esquerda);
        listar_alunos(raiz->direita);
    }
}

// Função para obter a altura de um nó
int altura(No_arvore *a){
    if (a == NULL){
        return -1;
    }
    return a->altura;
}

//auxiliar para calcular a quantidade de nós na árvore
aux_quantidade_no(No_arvore *raiz,int *cont){
    if (raiz != NULL){
        *cont = *cont + 1;
        aux_quantidade_no(raiz->esquerda, cont);
        aux_quantidade_no(raiz->direita, cont);
    }
}

// calcular a quantidade de nós na árvore
int quantidade_no(No_arvore *raiz){
    int a = 0;
    aux_quantidade_no(raiz, &a);
    return a;
}

//auxiliar para calcular a maior diferença de fatores de balanceamento (fb)
void aux_maior_diferenca(No_arvore *raiz, int *maior_fb){
    if (raiz == NULL) return;

    int aux = fb(raiz);

    if (aux > *maior_fb) {
        *maior_fb = aux;
    }

    aux_maior_diferenca(raiz->esquerda, maior_fb);
    aux_maior_diferenca(raiz->direita, maior_fb);
}

// calcular a maior diferença de fatores de balanceamento (fb)
int maior_diferenca(No_arvore *raiz) {
    int maior_diferenca = 0;
    aux_maior_diferenca(raiz, &maior_diferenca);
    return maior_diferenca;
}

//criar/inicializar a lista geral de filmes
int criar_lista(lista **l) {
    *l = (lista*)malloc(sizeof(lista));
    if (*l == NULL) return 0;
    (*l)->inicio = NULL;
    (*l)->fim = NULL;
    return 1;
}

//criar um novo nó para a lista de filmes(árvore)
No_lista* novo_no_lista(char *nome_filme) {
    No_lista *novo = (No_lista*)malloc(sizeof(No_lista));
    if (novo) {
        strcpy(novo->nome_filme, nome_filme);
        novo->prox = NULL;
    }
    return novo;
}

//criar um novo nó para a lista geral de filmes
No_lista_geral* novo_no_lista_geral(char *nome_filme){
    No_lista_geral *novo = (No_lista_geral*)malloc(sizeof(No_lista_geral));
    if (novo){
        strcpy(novo->nome_filme, nome_filme);
        novo->contador = 0;
        novo->prox = NULL;
    }
    return novo;
}

//inserir na lista geral de filmes (apenas se o filme não existir)
int inserir_no_lista_geral(lista *l, char *nome_filme){
    if(l == NULL || nome_filme == NULL){
        return 0;
    }

    No_lista_geral *aux = l->inicio;
    No_lista_geral *anterior = NULL;

    //usca pelo filme na lista
    while(aux != NULL && strcmp(nome_filme, aux->nome_filme) >0){
        anterior = aux;
        aux = aux->prox;
    }

    if(aux != NULL && strcmp(nome_filme, aux->nome_filme) ==0){
        //filme já existe, incrementa o contador
        aux->contador++;
        return 1;
    }

    //não existe, cria um novo nó
    No_lista_geral *novo = novo_no_lista_geral(nome_filme);
    if(novo == NULL){
        return 0;
    }

    novo->contador = 1;

    //insere na posição correta
    if(anterior == NULL){
        novo->prox = l->inicio;
        l->inicio = novo;
        if (l->fim == NULL) {
            l->fim = novo;
        }
    } else {
        novo->prox = anterior->prox;
        anterior->prox = novo;
        if (anterior->prox == NULL) {
            l->fim = novo;
        }
    }

    return 1;
}

//aumentar o contador de um filme da lista geral
void aumentar_contador(No_lista_geral *no){
    no->contador++;
}

//inserir um novo filme na lista de filmes de uma pessoa(árvore)
int inserir_no_lista_arvore(No_arvore *no, char *nome_filme){
    No_lista *novo = novo_no_lista(nome_filme);
    if (novo == NULL) {
        return 0;
    }
    if(no == NULL){
        return 0;
    }

    //caso a lista esteja vazia, será o primeiro filme
    if(no->lista_inicio == NULL) {
        no->lista_inicio = novo;
        no->lista_fim = novo;
    }
    //caso o primeiro filme venha antes do segundo
    else if(strcmp(novo->nome_filme, no->lista_inicio->nome_filme)< 0){
        novo->prox = no->lista_inicio;
        no->lista_inicio = novo;
    }
    //caso o filme deva ser inserido no meio ou no final
    else{
        No_lista *aux = no->lista_inicio;
        while(aux->prox != NULL && strcmp(novo->nome_filme, aux->prox->nome_filme) >0){
            aux = aux->prox;
        }
        novo->prox = aux->prox;
        if (aux->prox == NULL){
            no->lista_fim = novo;
        }
        aux->prox = novo;
    }

    return 1;
}

//remover um filme da lista geral de filmes (se o contador for 0)
int remover_filme_lista_geral(lista *l, char *nome_filme){
    if (l == NULL || nome_filme == NULL || l->inicio == NULL){
        return 0;
    }

    No_lista_geral *aux = l->inicio;
    No_lista_geral *anterior = NULL;

    //busca pelo filme na lista
    while (aux != NULL && strcmp(aux->nome_filme, nome_filme) != 0){
        anterior = aux;
        aux = aux->prox;
    }

    if(aux != NULL){
        //decrementa o contador primeiro
        aux->contador--;
        if(aux->contador <= 0){
           
            if(anterior == NULL){
                l->inicio = aux->prox;
                if(l->inicio == NULL){
                    l->fim = NULL;
                }
            } 
            else{
                anterior->prox = aux->prox;
                if(aux->prox == NULL){
                    l->fim = anterior;
                }
            }
            free(aux);
        }
        return 1;
    }

    return 0;
}

// auxiliar para contar filmes em comum
int contar_filmes_comuns(No_lista *lista1, No_lista *lista2){
    int count = 0;
    No_lista *filme1 = lista1;

    while(filme1 != NULL){
        No_lista *filme2 = lista2;
        while(filme2 != NULL){
            if (strcmp(filme1->nome_filme, filme2->nome_filme) == 0){
                count++;
                break;
            }
            filme2 = filme2->prox;
        }
        filme1 = filme1->prox;
    }
    return count;
}

//emover filmes de um nó da árvore
void remover_filme_arvore(lista *l, No_arvore *no){
    No_lista *aux1 = no->lista_inicio;
    No_lista *aux2 = no->lista_inicio;

    //ppercorre todos os filmes na lista do nó
    while(aux1 != NULL){
        aux1 = aux1->prox;

        if(!remover_filme_lista_geral(l, aux2->nome_filme)){
            return;
        }
        free(aux2);
        aux2 = aux1;
    }
    no->lista_inicio = NULL;
    no->lista_fim = NULL;
}

// buscar um nó na árvore
No_arvore* buscar_arvore(No_arvore *r, int numero){
    if(r == NULL) {
        return NULL;
    }
    if(numero == r->numero_USP) {
        return r;
    } else if (numero < r->numero_USP) {
        return buscar_arvore(r->esquerda, numero);
    } else {
        return buscar_arvore(r->direita, numero);
    }
}

// buscar um filme na lista geral
No_lista_geral* buscar_lista(lista *a,char *nome_filme){
    if(a->inicio == NULL){
        return NULL;
    }
    No_lista_geral *aux = a->inicio;
    while (aux != NULL && strcmp(nome_filme, aux->nome_filme) != 0) {
        aux = aux->prox;
    }
    return aux;
}

//listar filmes na lista geral
void listar_filmes(lista *a){
    if (a == NULL) 
        return;
    No_lista_geral *aux = a->inicio;
    while(aux != NULL){
        printf("%s (%d)\n", aux->nome_filme, aux->contador);
        aux = aux->prox;
    }
}

//obter o próximo filme na lista
No_lista_geral* proximo_filme(lista *l, No_lista_geral *no_atual){
    if(no_atual == NULL){
        return l->inicio;
    }
    return no_atual->prox;
}

//obter o valor do contador de um nó
int contador_lista(No_lista_geral *no){
    return no->contador;
}

//para obter o nome de um filme
int nome_do_filme(No_lista_geral *no, char *nome_filme){
    if(no != NULL){
        strcpy(nome_filme, no->nome_filme);
        return 1;
    }
    return 0;
}

//obter o próximo filme na árvore
No_lista* proximo_filme_arvore(No_arvore *l, No_lista *no_atual){
    if (no_atual == NULL) {
        return l->lista_inicio;
    }
    return no_atual->prox;
}




//auxiliar recursiva para encontrar aluno similar
void aux_rec_similar(No_arvore *raiz, int num, No_lista *lista_aluno, Recomendacao **rec){
    if (raiz == NULL){
        return;
    }
    if(raiz->numero_USP != num){
        int comum =contar_filmes_comuns(lista_aluno, raiz->lista_inicio);
        if(comum >(*rec)->count){
            //limpa a lista de recomendações anteriores
            while (*rec != NULL){
                Recomendacao *temp = *rec;
                *rec =(*rec)->prox;
                free(temp);
            }
            //add a nova recomendacao
            *rec =(Recomendacao*)malloc(sizeof(Recomendacao));
            (*rec)->aluno = raiz;
            (*rec)->count = comum;
            (*rec)->prox = NULL;
        } 
        else if(comum == (*rec)->count){
            // Add a nova recomendaçaoà listq
            Recomendacao *nova_rec =(Recomendacao*)malloc(sizeof(Recomendacao));
            nova_rec->aluno =raiz;
            nova_rec->count =comum;
            nova_rec->prox =*rec;
            *rec = nova_rec;
        }
    }

    aux_rec_similar(raiz->esquerda, num, lista_aluno, rec);
    aux_rec_similar(raiz->direita, num, lista_aluno, rec);
}

//recomendação de alunos similares
Recomendacao* obter_recomendacao_similar(No_arvore *a, int num){
    Recomendacao *rec = (Recomendacao*)malloc(sizeof(Recomendacao));
    rec->aluno = NULL;
    rec->count = 0;
    rec->prox = NULL;
    No_arvore *aux = buscar_arvore(a, num);
    if (aux != NULL) {
        aux_rec_similar(a, num, aux->lista_inicio, &rec);
    }
    return rec;
}

//  obter a próxima recomendação
Recomendacao* obter_proxima_recomendacao(Recomendacao *rec){
    return rec->prox;
}

//auxiliar recursiva para encontrar aluno diferente
void aux_rec_diferente(No_arvore *raiz, int num, No_lista *lista_aluno, RecomendacaoDif *rec){
    if(raiz == NULL){
        return;
    }
    if(raiz->numero_USP != num){
        //conta filmes em comum
        int comum = contar_filmes_comuns(lista_aluno, raiz->lista_inicio);
        if (rec->aluno == NULL || comum < rec->count) {
            rec->count = comum;
            rec->aluno = raiz;
        }
    }

    aux_rec_diferente(raiz->esquerda, num, lista_aluno, rec);
    aux_rec_diferente(raiz->direita, num, lista_aluno, rec);
}


//obter o próximo filme na lista da árvore
No_lista *proximo_lista_arvore(No_arvore *no, No_lista *filme_atual){
    if (filme_atual == NULL){
        return no->lista_inicio;
    }
    return filme_atual->prox;
}

//obter o nome do filme na arv
int nome_do_filme_arvore(No_lista *no,char *nome_filme){
    if (no != NULL){
        strcpy(nome_filme, no->nome_filme);
        return 1;
    }
    return 0;
}

//auxiliar para liberar filmes na lista
void aux_liberar_filmes(No_lista *l){
    No_lista *aux;
    while (l != NULL) {
        aux = l->prox;
        free(l);
        l = aux;
    }
}

//liberar a memória da árvore
void liberar_arvore(No_arvore *raiz){
    if (raiz != NULL) {
        aux_liberar_filmes(raiz->lista_inicio);
        liberar_arvore(raiz->esquerda);
        liberar_arvore(raiz->direita);
        free(raiz);
    }
}

//escrever dados em um arquivo
void arquivo(FILE *arq, No_arvore *no) {
    if (arq != NULL && no != NULL) {
        fprintf(arq, "%s (%d)\n", no->nome, no->numero_USP);
        No_lista *aux = no->lista_inicio;
        while (aux != NULL) {
            fprintf(arq, " - %s\n", aux->nome_filme);
            aux = aux->prox;
        }
        fprintf(arq, "\n");
        arquivo(arq, no->esquerda);
        arquivo(arq, no->direita);
    }
}


int obter_numero_USP(No_arvore *no){
    if (no != NULL){
        return no->numero_USP;
    }
    return -1; //valor de erro
}

void obter_nome_aluno(No_arvore *no,char *nome){
    if(no != NULL){
        strcpy(nome, no->nome);
    }
}

No_lista*obter_lista_inicio(No_arvore *no){
    if (no != NULL) {
        return no->lista_inicio;
    }
    return NULL;
}

void obter_nome_filme(No_lista_geral *no, char *nome_filme){
    if (no != NULL) {
        strcpy(nome_filme, no->nome_filme);
    }
}

int obter_contador_filme(No_lista_geral *no){
    if (no != NULL) {
        return no->contador;
    }
    return -1;
}



RecomendacaoDif obter_recomendacao_diferente(No_arvore *a, int num){
    RecomendacaoDif rec ={NULL, 0};
    No_arvore *aux = buscar_arvore(a, num);
    if (aux != NULL) {
        aux_rec_diferente(a, num, aux->lista_inicio, &rec);
    }
    return rec;
}



void filmes_mais_populares(lista *l, char filmes[][50], int *quantidade, int *max_contador){
    if (l == NULL|| l->inicio ==NULL){
        *quantidade = 0;
        *max_contador = 0;
        return;
    }

    No_lista_geral *aux = l->inicio;
    *max_contador = aux->contador;

    //encontrar o maior contador
    while (aux != NULL){
        if (aux->contador >*max_contador){
            *max_contador = aux->contador;
        }
        aux = aux->prox;
    }

    //ncontrar todos os filmes com o maior contador
    aux = l->inicio;
    *quantidade = 0;
    while (aux != NULL){
        if (aux->contador == *max_contador){
            strcpy(filmes[*quantidade],aux->nome_filme);
            (*quantidade)++;
        }
        aux = aux->prox;
    }
}
