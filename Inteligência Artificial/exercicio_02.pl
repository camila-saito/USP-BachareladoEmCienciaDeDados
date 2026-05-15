%PARTICIPANTES
% Ana Paula de Abreu Batista - Nº USP -12688424, 
% Camila Aya Saito - Nº USP - 15635649
% Victoria Fávero Nunes - Nº USP - 15698302


classe(Numero, positivo) :- Numero > 0.
classe(0, zero).
classe(Numero, negativo) :- Numero < 0.


/**
 verifica a classe do número inserido, 
 informando se é positivo, negativo ou zero.
 
 funcionamento: 
 recebe um número, depois
 o programa verifica se o número é positivo 
 senão verifica se é zero 
 se ainda não satifez nenhuma das regras anteriores então
 verifica se o número é negativo.
 */


% PROGRAMA COM CORTE VERDE
classe(Numero, positivo) :- Numero > 0, !.
classe(0, zero) :- !.
classe(Numero, negativo) :- Numero < 0.


/**
 após a implementação do corte continuamos fazendo as mesmas verificações 
 porém de forma mais eficiente, evitando verificações desnecessárias.
 
 então 
 recebe um número, depois
 o programa verifica se o número é positivo, se for então corta e termina a verificação. 
 senão verifica se é zero, se for então corta e termina a verificação.
 se ainda não satifez nenhuma das regras anteriores então
 verifica se o número é negativo.
 */
