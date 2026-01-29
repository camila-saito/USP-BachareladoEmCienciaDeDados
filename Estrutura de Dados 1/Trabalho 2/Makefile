# Regra padrão que será executada quando você chamar 'make'
# Ela depende de main.o e trabalho.o
all: main.o trabalho.o
	gcc main.o trabalho.o -o main

# Comando que compila e gera o executável main 
# a partir da junção de main.o e trabalho.o

# Regra que define como gerar o arquivo objeto (main.o)
# Ela depende de main.c e trabalho.h
main.o: main.c trabalho.h
	gcc -c main.c 

# Regra que define como gerar o arquivo objeto (trabalho.o)
# Ela depende de trabalho.c e trabalho.h
trabalho.o: trabalho.c trabalho.h 
	gcc -c trabalho.c

# Regra para executar o programa
run:
	./main

# Regra para limpar os arquivos gerados
clean:
	rm *.o main
