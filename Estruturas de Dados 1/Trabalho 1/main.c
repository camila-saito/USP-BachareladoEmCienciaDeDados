#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "leilao.h"

//menu
void menu(){
    printf("----------------------------------------------------\n");
    printf("\n**LEILAO ICMC**\n");
    printf("1) Cadastrar um produto\n");
    printf("2) Listar produtos e lances\n");
    printf("3) Dar um lance\n");
    printf("4) Encerrar leilao\n");
    printf("5) Remover produto\n");
    printf("6) Sugestao de produtos\n");
    printf("0) Sair\n\n");
}

//listar produtos e os lances recebidos em cada produto
void listar_produtos_e_lances(lista_produtos *l){
    produto *p = NULL;
    while((p = proximo_produto(l, p))!= NULL){
        char nome_p[50];
        char descricao[300];
        //se as informacoes chegaram, ou seja, se o endereco recebido por p nao eh nulo
        //retorna por parametro as informacoes contidas no endereco apontado por p
        if(nome_produto(p, nome_p)&& descricao_produto(p, descricao)){
            printf("PRODUTO: %s\nDescricao: %s\n", nome_p, descricao);
        }
        int total_lances = 0;
        lance *lance_atual = NULL;
        //o ponteiro recebe o endereco da chamada das informacoes 
        while((lance_atual = proximo_lance(p, lance_atual))!= NULL){
            //variavel para guardar o valor do lance que ta sendo iterado
            float valor_lan;
            //se tem valor, ou seja se a funcao retorna a informacao, printo o valor do lance
            if(valor_lance(lance_atual, &valor_lan)){
                printf("Lance: R$%.2f---", valor_lan);
                total_lances++;
            }
            //itero sobre as pessoas qeu deram tal valor e retorno seu nome
            pessoa *pessoa_atual = NULL;
            while((pessoa_atual = proxima_pessoa(lance_atual, pessoa_atual)) != NULL){
                char nomepessoa[50];
                if (nome_pessoa(pessoa_atual, nomepessoa)){
                    printf("Nome: %s\n", nomepessoa);
                }
            }
        }
        printf("Total de lances: %d\n\n", total_lances);
    }
}

//sugere produtos na qual a pessoa que teve um lance ultrapassado ainda nao deu um lance
void sugestao_produtos(lista_produtos *l,char *nomepessoa,int max_sugestoes){
    int qtd_sugestoes = 0;
    //crio uma matriz dinamica que vai guardar todos os produtos que serao sugeridos
    char **sugeridos=(char**)malloc(max_sugestoes * sizeof(char *));
    if(sugeridos == NULL) {
        printf("Erro ao encontrar uma sugestao!\n");
        return;
    }
    for (int i = 0; i < max_sugestoes; i++){
        sugeridos[i] = (char*)malloc(50*sizeof(char));
    }

    produto *p = NULL;
    //iteracao sobre os produtos
    while((p = proximo_produto(l, p))!= NULL){
        char nome_p[50];
        float maior_valor = 0;
        int pessoa_deu_lance = 0;
        int pessoa_ultrapassada = 0;
        int alguem_deu_lance = 0;

        //recebo o nome do produto
        nome_produto(p, nome_p);
        lance *lance_atual = NULL;

        //percorre os lances do produto
        while((lance_atual = proximo_lance(p, lance_atual)) !=NULL){
            //guarda o valor de um lance
            float valor_lan;
            valor_lance(lance_atual, &valor_lan);
            //houve um lance
            alguem_deu_lance = 1;

            //atualizo o maior valor a cada iteracao
            if(valor_lan > maior_valor){
                maior_valor = valor_lan;
            }

            pessoa *pessoa_atual = NULL;
            //verifico quem deu o lance atual
            while((pessoa_atual = proxima_pessoa(lance_atual, pessoa_atual))!= NULL){
                char nome_lance[50];
                nome_pessoa(pessoa_atual, nome_lance); //recebo o nome da pessoa do lance atual
                
                //comparo o nome do parametro com a pessoa do lance 
                //se sao iguais, a pessoa deu um lance
                if(strcmp(nome_lance, nomepessoa)==0){
                    pessoa_deu_lance = 1;
                    //verifica se o lance da pessoa foi ultrapassado
                    //se o valor do lance que esta no campo retornado eh menor que o maior valor
                    //quer dizer que ela foi ultrapassada, pois era para ser igual, se ela nao fosse
                    if(valor_lan < maior_valor){
                        pessoa_ultrapassada = 1;// 1 verdadeiro
                    }
                }
            }
        }
        //se a pessoa nao deu lance(independente de haver lances no produto), sugerir o produto
        if(!pessoa_deu_lance &&qtd_sugestoes <max_sugestoes){
            strcpy(sugeridos[qtd_sugestoes], nome_p);
            qtd_sugestoes++;
        }
    }

    // Exibe os produtos sugeridos para dar lance
    if(qtd_sugestoes>0){
        printf("\nProdutos sugeridos para voce dar um lance:\n");
        for (int i = 0; i < qtd_sugestoes; i++) {
            printf("- %s\n", sugeridos[i]);
            free(sugeridos[i]);  
        }
    } 
    else{
        printf("Nenhum produto sugerido para dar lance.\n");
    }
    free(sugeridos);  
}


int main(void){
    lista_produtos *l;
    if(!criar_lista_produtos(&l)) {
        printf("Erro ao criar a lista!\n");
    }
    int n;
    int max_sugestoes = 100;

    char nome_p[50], descricao[300],nomepessoa[50];
    float valor;

    do{
        menu();
        printf("\nEscolha uma das opcoes acima: ");
       
        scanf("%d", &n);
         printf("\n");
        
        getchar(); //limpar buffer

        switch(n){
            //cadastrar produto
            case 1:
                printf("\n");
                printf("----------------------------------------------------\n");
                printf("CADASTRAR PRODUTO\n");
                printf("Nome do Produto: ");
                fgets(nome_p,50,stdin);
                nome_p[strcspn(nome_p, "\n")] = '\0';//remover o caracter de quebra de linha

                printf("Descricao: ");
                fgets(descricao, 300, stdin);
                descricao[strcspn(descricao, "\n")] = '\0';  

                if(!inserir_produto(l,nome_p, descricao)) {
                    printf("Erro ao inserir o produto!\n");
                }
                printf("Produto cadastrado com sucesso!\n");
                break;

            //listar produtos
            case 2:
                printf("\n");
                printf("----------------------------------------------------\n");
                printf("LISTA DE PRODUTOS E LANCES:\n\n");
                listar_produtos_e_lances(l);
                break;

            //dar lance
            case 3:
                printf("\n");
                printf("----------------------------------------------------\n");
                printf("DAR LANCE:\n");
                printf("Nome do Produto: ");
                fgets(nome_p, 50, stdin);
                nome_p[strcspn(nome_p,"\n")] ='\0';

                printf("Nome da Pessoa: ");
                fgets(nomepessoa, 50, stdin);
                nomepessoa[strcspn(nomepessoa,"\n")] ='\0';  
                printf("Valor do Lance: ");
                scanf("%f", &valor);
                getchar();  

                //se lance maior ou igual ao lance anterior
                if(inserir_lance(l,nome_p, nomepessoa, valor)){
                    printf("\nLance aceito!\n");
                } 
                // se o produto nao existir
                else if (produto_nao_existe(l,nome_p)){
                    printf("\nLance rejeitado: produto nao existe.\n");
                }
                //valor do lance dado eh menor do que o necessario
                else{                    
                    printf("\nLance rejeitado: valor menor que o ultimo lance.\n");
                }
                break;

            //encerrar leilao e listar os ganhadores para cada produto
            case 4:
                printf("\n");
                printf("----------------------------------------------------\n");
                printf("ENCERRANDO LEILAO:\n\n");
                produto *p = NULL;
                while((p = proximo_produto(l, p)) != NULL){
                    char nome_p[50],nome_vencedor[50];
                    float valor_vencedor;

                    if(nome_produto(p, nome_p) && vencedor_produto(l, nome_p, nome_vencedor,&valor_vencedor)){
                        printf("Produto: %s\nVencedor: %s com lance de R$%.2f\n\n",nome_p, nome_vencedor, valor_vencedor);
                    } else {
                        printf("Produto: %s nao teve lances.\n\n",nome_p);
                    }
                }

                esvaziar_lista(l);
                free(l);
                return 0;

            //remover produto
            case 5:
                printf("\n");
                printf("----------------------------------------------------\n");
                printf("REMOVER PRODUTO:\n");
                printf("Nome do Produto: ");
                fgets(nome_p,50,stdin);
                nome_p[strcspn(nome_p, "\n")] = '\0';
            
                produto *aux = NULL;
                int produto_encontrado = 0;
            
                //itera sobre os produtos para encontrar o desejado
                while((aux =proximo_produto(l, aux))!= NULL){
                    char nome_do_produto[50];
                    //verifica o nome do produto atual
                    if(nome_produto(aux, nome_do_produto)&& strcmp(nome_do_produto, nome_p)== 0){
                        produto_encontrado = 1;
                        char nome_vencedor[50];
                        float valor_vencedor;
            
                        //se teve lances e quem eh o vencedor
                        if(vencedor_produto(l, nome_p, nome_vencedor, &valor_vencedor)){
                            printf("Produto: %s\nVencedor: %s com lance de R$%.2f\n\n",nome_p,nome_vencedor,valor_vencedor);
                        } 
                        else{
                            printf("Produto: %s nao teve lances.\n\n",nome_p);
                        }
            
                        //remover produto
                        if(remover_produto(l, nome_p)){
                            printf("Produto removido com sucesso.\n");
                        } 
                        else{
                            printf("Erro ao remover produto.\n");
                        }
                        break;
                    }
                }
                if(!produto_encontrado){
                    printf("Produto nao encontrado.\n");
                }
                break;


            //sugestao de produtos
            case 6:
                printf("\n");
                printf("----------------------------------------------------\n");
                printf("SUGESTAO DE PRODUTOS:\n");
                printf("Nome da Pessoa: ");
                fgets(nomepessoa, 50, stdin);
                nomepessoa[strcspn(nomepessoa,"\n")] ='\0';
                printf("Numero maximo de sugestoes: ");
                scanf("%d", &max_sugestoes);
                getchar();
                sugestao_produtos(l, nomepessoa, max_sugestoes);
                break;

            case 0:
                printf("Saindo...\n");
                break;

            default:
                printf("Opcao invalida!\n");
                break;
        }
    } while (n != 0);

    return 0;
}
