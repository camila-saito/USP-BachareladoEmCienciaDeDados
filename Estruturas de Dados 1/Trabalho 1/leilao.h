// TAD: lista encadeada e dinâmica de pilhas (lista dos produtos)

//estruturas de dados
typedef struct pessoa pessoa;
typedef struct lance lance;
typedef struct produto produto;
typedef struct lista_produtos lista_produtos;


//funcoes do TAD
int criar_lista_produtos(lista_produtos **l);
int inserir_produto(lista_produtos *l,char *nome_produto, char *desc);
int inserir_lance(lista_produtos *l,char *nome_produto, char *nome_pessoa, float valor);
int quantidade_produtos(lista_produtos *l);
int quantidade_lances(lista_produtos *l, char *nome_produto);
produto* proximo_produto(lista_produtos *l, produto *prod_atual);
lance* proximo_lance(produto *p,lance *lance_atual);
pessoa* proxima_pessoa(lance *l,pessoa *pessoa_atual);
int nome_produto(produto *p, char *nome_produto);
int descricao_produto(produto *p, char *descricao);
int valor_lance(lance *l, float *valor);
int nome_pessoa(pessoa *p, char *nome);
int vencedor_produto(lista_produtos *l, char *nome_produto, char *nome_vencedor, float *valor_vencedor);
int remover_produto(lista_produtos *l, char *nome_produto);
int sugerir_produtos_para_pessoa(lista_produtos *l, char *nome_pessoa, char sugeridos[][50], int *qtd_sugestoes);
void esvaziar_lista(lista_produtos *l);
int lista_vazia(lista_produtos *l);
int produto_nao_existe(lista_produtos *l, char *nome_produto);


