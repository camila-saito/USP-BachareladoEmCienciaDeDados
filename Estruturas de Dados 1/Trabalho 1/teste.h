// TAD: lista encadeada e dinâmica de pilhas (lista dos produtos)

typedef struct Bloco1 {
    char nome[50];
    struct Bloco1 *prox;
}pessoa; 

typedef struct{
    pessoa *inicio, *fim;
}fila_pessoas; //sugestao

typedef struct Bloco2 {
    float valor;
    struct Bloco2 *prox;
    pessoa *inicio_pessoas;
    pessoa *fim_pessoas;
}lance; // no da lista dos lances com um ponteiro para a lista respectiva de nomes

typedef struct Bloco3 {
    char nome_produto[50];
    char descricao[300];
    struct Bloco3 *prox;
    lance *lances;
}produto; // no da lista de produtos

typedef struct {
    produto *inicio, *fim;
    int tamanho;
}lista_produtos;

void criar_lista_produtos(lista_produtos*, fila_pessoas*);
void inserir_produto(lista_produtos*, char*, char*);
void inserir_lance(lista_produtos*, fila_pessoas*, char*, char*, float*);
void esvaziar_lista(lista_produtos*, fila_pessoas*);

int lista_vazia(lista_produtos *l);
void retorno_inicio_listaProdutos(lista_produtos *l, char *nome_produto, char *descricao);
int pilha_vazia(lista_produtos *l, char *nome_produto);
void pop_pilha(lista_produtos *l, char *nome_produto, char *nome_pessoa, float *valor);
void pop_lista(lista_produtos *l, char *nome_produto);