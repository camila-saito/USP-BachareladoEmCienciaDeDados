d, k = input().split()  
d = int(d) # dimensao
k = int(k) # numero de vetores

base = [] # inicializa uma lista vazia, onde serão colocados os vetores
for i in range(0, k):
    vet = input().split()
    for j in range(0, d):
        vet[j] = int(vet[j])
    base.append(vet)

é_base = True
if d > k: # se número de vetores for menor do que a dimensão, não tem como ser uma base canônica
    é_base = False
else:
    # verificar coluna por coluna se tem pelo menos um '1'
    for i in range(0, d):
        s = 0
        for j in range(0, k):
            s = s + base[j][i]
        if s == 0: # não encontrou nenhum '1' na coluna i
            é_base = False
            break

if é_base == True:
    print('SimTia')
else:
    print('Não')
