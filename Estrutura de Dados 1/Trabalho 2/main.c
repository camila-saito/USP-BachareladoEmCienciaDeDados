#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "trabalho.h"

void menu() {
    printf("----------------------------------------------------\n");
    printf("\n**PREFERENCIA DE FILMES DOS ALUNOS USP**\n");
    printf("1) Cadastrar um aluno\n");
    printf("2) Listar alunos\n");
    printf("3) Buscar aluno\n");
    printf("4) Listar filmes\n");
    printf("5) Buscar filme\n");
    printf("6) Recomendacao\n");
    printf("7) Gerar arquivo texto\n");
    printf("8) Dados Tecnicos\n");
    printf("9) Remover aluno\n");
    printf("10) Listar filme mais popular\n");
    printf("0) Sair\n");
}

void imprimir_recomendacoes(Recomendacao *rec){
    while(rec != NULL){
        if(obter_proxima_recomendacao(rec) != NULL) {
            char nome[50];
            obter_nome_aluno(rec->aluno, nome);
            printf("Recomendamos o aluno %s.\n", nome);
            rec = obter_proxima_recomendacao(rec);
        }
        else return;
    }
}

int main(){
    No_arvore *raiz = NULL;
    lista *lista_filmes;
    criar_lista(&lista_filmes);
    int opcao, numero_USP,quantidade, max_contador;
    char nome[50], nome_filme[50],filmes[100][50];
    FILE *arq;

    do {
        menu();
        printf("Escolha uma opcao: ");
        scanf("%d", &opcao);

        switch(opcao){
            case 1:
                printf("\n");
                printf("----------------------------------------------------\n");
                printf("CADASTRAR ALUNO\n");
                printf("Digite o numero USP do aluno: ");

                scanf("%d", &numero_USP);
                printf("Digite o nome do aluno: ");
                scanf(" %[^\n]", nome);

                raiz = inserir(raiz, numero_USP, nome);
                printf("\n");
                printf("Pressione 'ENTER' apos digitar o nome do filme\n");
                printf("Digite o nome dos filmes favoritos do aluno (digite 'fim' para terminar):\n");

                while(1){
                    scanf(" %[^\n]", nome_filme);
                    if (strcmp(nome_filme, "fim") == 0) break;
                    inserir_no_lista_arvore(raiz, nome_filme);
                    inserir_no_lista_geral(lista_filmes, nome_filme);
                }
                break;

            case 2:
                printf("\n");
                printf("----------------------------------------------------\n");
                printf("LISTAR TODOS OS ALUNOS\n");
                listar_alunos(raiz);
                break;

            case 3:
                printf("\n");
                printf("----------------------------------------------------\n");
                printf("BUSCAR ALUNO\n");
                printf("Digite o numero USP do aluno: ");
                scanf("%d", &numero_USP);

                No_arvore *aluno = buscar_arvore(raiz, numero_USP);
                if(aluno){
                    obter_nome_aluno(aluno, nome);
                    printf("Aluno encontrado: %s (%d)\n", nome, obter_numero_USP(aluno));
                } 
                else{
                    printf("Aluno nao encontrado.\n");
                }
                break;

            case 4:
                printf("\n");
                printf("----------------------------------------------------\n");
                printf("LISTAR TODOS OS FILMES\n");
                listar_filmes(lista_filmes);
                break;

            case 5:
                printf("\n");
                printf("----------------------------------------------------\n");
                printf("BUSCAR FILME\n");
                printf("Digite o nome do filme: ");
                scanf(" %[^\n]", nome_filme);
                No_lista_geral *filme = buscar_lista(lista_filmes, nome_filme);
                if(filme){
                    obter_nome_filme(filme, nome_filme);
                    printf("Filme encontrado: %s\n", nome_filme);
                } 
                else{
                    printf("Filme nao encontrado.\n");
                }
                break;

            case 6:
                printf("\n");
                printf("----------------------------------------------------\n");
                printf("RECOMENDACAO DE COLEGA\n");
                printf("Digite o numero USP do aluno para recomendacao: ");
                scanf("%d", &numero_USP);
                Recomendacao *rec_similar = obter_recomendacao_similar(raiz, numero_USP);
                if (rec_similar != NULL && rec_similar->aluno != NULL){
                    imprimir_recomendacoes(rec_similar);
                }
                else{
                    printf("Nenhum aluno similar encontrado.\n");
                }
                break;
            case 7:
                printf("\n");
                printf("----------------------------------------------------\n");
                printf("GERAR ARQUIVO TEXTO\n");
                arq = fopen("dados_alunos.txt", "w");
                if(arq){
                    arquivo(arq, raiz);
                    fclose(arq);
                    printf("Arquivo gerado com sucesso.\n");
                }
                else{
                    printf("Erro ao gerar arquivo.\n");
                }
                break;

            case 8:
                printf("\n");
                printf("----------------------------------------------------\n");
                printf("DADOS TECNICOS\n");
                printf("Quantidade de nos da arvore: %d\n", quantidade_no(raiz));
                printf("Altura da arvore: %d\n", altura(raiz));
                printf("Maior diferenca de altura: %d\n", maior_diferenca(raiz));
                break;

            case 9:
                printf("\n");
                printf("----------------------------------------------------\n");
                printf("REMOVER ALUNO\n");
                printf("Digite o numero USP do aluno a ser removido: ");
                scanf("%d", &numero_USP);
                raiz = remover_aluno(raiz, numero_USP, lista_filmes);
                break;

            case 10:
                printf("\n");
                printf("----------------------------------------------------\n");
                printf("FILME MAIS POPULAR\n");
                filmes_mais_populares(lista_filmes, filmes, &quantidade, &max_contador);
                printf("Filmes mais populares:\n");
                for (int i = 0; i < quantidade; i++){
                    printf("%s (%d)\n", filmes[i], max_contador);
                }
                break;

            case 0:
                printf("\n");
                printf("----------------------------------------------------\n");
                printf("ENCERRAR PROGRAMA\n");
                liberar_arvore(raiz);
                printf("Programa encerrado.\n");
                break;
                
            default:
                printf("Opcao invalida.\n");
        }
    } while(opcao != 0);

    return 0;
}
