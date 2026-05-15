%PARTICIPANTES
% Ana Paula de Abreu Batista - Nº USP -12688424
% Camila Aya Saito - Nº USP - 15635649
% Victoria Fávero Nunes - Nº USP - 15698302


aluno("Carlos Silva", "Rua das Orquideas, 32", "016966666666", "05/05/1955",
10,5,10,2,0,0,70).
aluno("Andressa Soares", "Rua Carlos Gomes, 45", "016998567453", "12/12/2001", 10, 10, 2, 10, 5, 0, 70).
aluno("Rodrigo Santos", "Rua Jose Pires, 45", "015226987456","23/02/1997", 3, 1, 5, 2, 5, 0, 50).
 
/**
I. Desenvolva programas que informem:
A. a média de prova de um aluno, tendo a p1 peso 2 e a p2 peso 3 e considerando
a possibilidade da sub.
B. a média de trabalho de um aluno, tendo o t1 peso 1 e o t2 peso 3.
C. a média final de um aluno, considerando a situação antes da rec.
D. se o aluno foi aprovado ou reprovado, considerando a situação antes
*/

media_prova(X, MEDIA) :-
    aluno(X, _, _, _, _, P1, _, P2, SUB, _, _),
    ( SUB > 0 ->
        (P1 < P2 -> P1Aux is SUB, P2Aux is P2 ; P2Aux is SUB, P1Aux is P1)
    ;
        P1Aux = P1,
        P2Aux = P2
    ),
    MEDIA is (P1Aux * 2 + P2Aux * 3) / 5.


media_trabalho(X, MEDIA) :-
    aluno(X, _, _, _, T1, _, T2, _, _,_, _),
    MEDIA is (T1 * 1 + T2 * 3) / 4.

media_aluno(X,MEDIA) :- 
    media_prova(X, MEDIA_PROVA),
	media_trabalho(X,MEDIA_TRABALHO),
    MEDIA is (MEDIA_PROVA + MEDIA_TRABALHO)/2.

aprovacao(X, MEDIA) :-
    media_aluno(X, MEDIA_FINAL),
    (MEDIA_FINAL > 5 ->  MEDIA = "Aprovado" ; MEDIA = "Reprovado").



/**
II. Mostre as interrogações (e os resultados) para determinar:


A. Quem tirou 10 na p1 e na p2?

    ?-aluno(X, _,_,_,_,10,_,10,_,_,_,).

    RESPOSTA
    X = "Andressa Soares"



B. Quem tirou 10 no t1 e no t2?

    ?-aluno(X, _,_,_,10,_,10,_,_,_,_).

    RESPOSTA
    X = "Carlos Silva"


C. Quem ficou com nota abaixo da média (abaixo de 5), nas duas provas? (p1<5 e
p2<5).

    ?-aluno(X, _, _, _, _, P1, _, P2, _, _, _),
    P1 < 5,
    P2 < 5.

    RESPOSTA
    P1 = 1,
    P2 = 2,
    X = "Rodrigo Santos"
    

D. Quem ficou com presença abaixo de 70%?

    ?-aluno(X, _,_,_,_,_,_,_,_,_,PRESENCA),
    PRESENCA < 70.

    RESPOSTA
    PRESENCA = 50,
    X = "Rodrigo Santos"



E. Quais foram os alunos reprovados?
?-aprovacao(X, "Aprovado").

    RESPOSTA
    X = "Carlos Silva",
    X = "Andressa Soares"



F. Quais foram os alunos aprovados?

    ?-aprovacao(X, "Reprovado").

    RESPOSTA
    X = "Rodrigo Santos"

*/



/**
III. Indique as alterações necessárias na estrutura aluno para que seja possível fazer as
seguintes interrogações:
A. Quais são as informações dos alunos cujo primeiro nome é X?
B. Quais são as informações dos alunos cujo sobrenome é X?
C. Quem são os alunos nascidos no ano X?
D. Quem são os alunos cujo CEP é X?
E. Qual o número da casa do aluno X?
*/


% aluno(nome(Primeiro_Nome, Sobrenome), end(Rua, Num, CEP), Telefone, nasc(Dia, Mes, Ano), T1, P1, T2, P2, Sub, Rec, Freq).
aluno(nome(carlos, silva), end("Rua das Orquideas", 32, 12345678), "016966666666", nasc(5, 5, 1955), 9, 6, 8.5, 7, 5, 5.5, 0.7).

/*
A. Quais são as informações dos alunos cujo primeiro nome é X?
	?- aluno(nome(X, Sobrenome), end(Rua, Num, CEP), Telefone, nasc(Dia, Mes, Ano), T1, P1, T2, P2, Sub, Rec, Freq).
    
B. Quais são as informações dos alunos cujo sobrenome é X?
	?- aluno(nome(Primeiro_Nome, X), end(Rua, Num, CEP), Telefone, nasc(Dia, Mes, Ano), T1, P1, T2, P2, Sub, Rec, Freq).
    
C. Quem são os alunos nascidos no ano X?
	?- aluno(nome(Primeiro_Nome, Sobrenome), end(_, _, _), _, nasc(_, _, X), _, _, _, _, _, _, _).

D. Quem são os alunos cujo CEP é X?
	?- aluno(nome(Primeiro_Nome, Sobrenome), end(_, _, X), _, nasc(_, _, _), _, _, _, _, _, _, _).

E. Qual o número da casa do aluno X?
	?- aluno(nome(X, _), end(_, Num, _), _, nasc(_, _, _), _, _, _, _, _, _, _).
*/

