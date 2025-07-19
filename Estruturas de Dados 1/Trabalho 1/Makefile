# regra padrão que será executada quando você chamar make
# ela depende de main.o e leilao.o
all: main.o leilao.o

#  comando que compila e gera o execultavel main 
# a partir da junção da main.o e leilao.o

	gcc main.o leilao.o -o main

# regra que definirá como gerar o arquivo objeto (main.o)
# ela depende da main.c e leilao.h

main.o: main.c leilao.h
	gcc -c main.c 

# regra que definirá como gerar o arquivo objeto (leilao.o)
# ela depende da leilao.c e leilao.h
leilao.o: leilao.c leilao.h 
	gcc -c leilao.c

# regra para executar o programa
run:
	./main

# regra para limpar os arquivos gerados
clean:
	rm *.o main