//estruturas
typedef struct no_lista_arvore No_lista;
typedef struct no_arvore No_arvore;
typedef struct no_lista_filme No_lista_geral;
typedef struct l lista;

typedef struct Recomendacao{
    No_arvore *aluno;
    int count;
    struct Recomendacao *prox;
}Recomendacao;

typedef struct RecomendacaoDif {
    No_arvore *aluno;
    int count;
} RecomendacaoDif;

//funcoes
No_arvore *raiz(No_arvore *a);
No_arvore* novo_no_arvore(int x, char *nome_pessoa);
int altura_no(No_arvore *no);
int maior(int a, int b);
int fb(No_arvore *r);
No_arvore* rotacao_esquerda(No_arvore **r);
No_arvore* rotacao_direita(No_arvore **r);
No_arvore* rotacao_esquerda_direita(No_arvore **r);
No_arvore* rotacao_direita_esquerda(No_arvore **r);
No_arvore* balancear(No_arvore* raiz);
No_arvore* inserir(No_arvore* raiz, int num, char *nome_aluno);
No_arvore* remover_aluno(No_arvore *raiz, int num, lista *l);
void listar_alunos(No_arvore *raiz);
int altura(No_arvore *a);
void aux_quantidade_no(No_arvore *raiz,int *cont);
int quantidade_no(No_arvore *raiz);
void aux_maior_diferenca(No_arvore *raiz, int *maior_fb);
int maior_diferenca(No_arvore *raiz);
int criar_lista(lista **l);
No_lista* novo_no_lista(char *nome_filme);
No_lista_geral* novo_no_lista_geral(char *nome_filme);
int inserir_no_lista_geral(lista *l, char *nome_filme);
void aumentar_contador(No_lista_geral *no);
int inserir_no_lista_arvore(No_arvore *no, char *nome_filme);
int remover_filme_lista_geral(lista *l, char *nome_filme);
int contar_filmes_comuns(No_lista *lista1, No_lista *lista2);
void remover_filme_arvore(lista *l, No_arvore *no);
No_arvore* buscar_arvore(No_arvore *r, int numero);
No_lista_geral* buscar_lista(lista *a, char *nome_filme);
void listar_filmes(lista *a);
No_lista_geral* proximo_filme(lista *l, No_lista_geral *no_atual);
int contador_lista(No_lista_geral *no);
int nome_do_filme(No_lista_geral *no, char *nome_filme);
No_lista* proximo_filme_arvore(No_arvore *l, No_lista *no_atual);
void aux_rec_similar(No_arvore *raiz, int num, No_lista *lista_aluno, Recomendacao **rec);
Recomendacao* obter_recomendacao_similar(No_arvore *a, int num);
Recomendacao* obter_proxima_recomendacao(Recomendacao *rec);
void aux_rec_diferente(No_arvore *raiz, int num, No_lista *lista_aluno, RecomendacaoDif *rec);
No_lista *proximo_lista_arvore(No_arvore *no, No_lista *filme_atual);
int nome_do_filme_arvore(No_lista *no, char *nome_filme);
void aux_liberar_filmes(No_lista *l);
void liberar_arvore(No_arvore *raiz);
void arquivo(FILE *arq, No_arvore *no);
int obter_numero_USP(No_arvore *no);
void obter_nome_aluno(No_arvore *no,char *nome);
No_lista*obter_lista_inicio(No_arvore *no);
void obter_nome_filme(No_lista_geral *no, char *nome_filme);
int obter_contador_filme(No_lista_geral *no);
RecomendacaoDif obter_recomendacao_diferente(No_arvore *a, int num);
void filmes_mais_populares(lista *l, char filmes[][50], int *quantidade, int *max_contador);
No_arvore* novo_no_arvore(int x, char *nome_pessoa);

/*
//funcoess para acessar campos do TAD
int obter_numero_USP(No_arvore *no);
void obter_nome_aluno(No_arvore *no, char *nome);
No_lista* obter_lista_inicio(No_arvore *no);
void obter_nome_filme(No_lista_geral *no, char *nome_filme);
int obter_contador_filme(No_lista_geral *no);

//funcoes para obter recomendacoes
Recomendacao* obter_recomendacao_similar(No_arvore *a, int num);
RecomendacaoDif obter_recomendacao_diferente(No_arvore *a, int num);
//funcao para obter os filmes mais populares
void filmes_mais_populares(lista *l, char filmes[][50], int *quantidade, int *max_contador);
Recomendacao* obter_proxima_recomendacao(Recomendacao *rec);*/
