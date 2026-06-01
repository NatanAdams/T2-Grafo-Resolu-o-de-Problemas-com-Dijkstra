# Trabalho 2 - Caminhos Mínimos: C. Dijkstra

Este repositório contém a resolução estruturada do problema de modelagem de grafos e caminhos mínimos proposto na disciplina.

## Informações Gerais
* **Nome do Problema:** C. Dijkstra
* **Link do Problema:** [Codeforces - Problem 20C](https://codeforces.com/problemset/problem/20/C?adcd1e=caf4fogv6ptzrn&csrf_token=1d65aacbfddc93843b8f5459a3fd9a00)
* **Linguagem Utilizada:** Java
* **Integrantes do Grupo:** Samuel Moreira, Luis Guilherme, Natan Adams, José Guilherme

---

## Como Executar a Solução
Para rodar a solução e testar com as entradas do problema, não é necessário fazer configurações complexas. Siga os passos abaixo:

1. Abra o seu terminal na pasta raiz do projeto (`T2/`).
2. Compile o código executando o comando:
   `javac src/*.java`
3. Execute a classe principal:
   `java -cp src Main`
4. Após executar o comando acima, o programa ficará aguardando a entrada de dados. **Basta copiar o texto dos casos de teste (Input) diretamente do site do Codeforces e colar tudo de uma vez no console/terminal.** O programa processará os dados instantaneamente e exibirá o caminho mínimo calculado.

---

## Modelagem do Problema e Representação
* **O Grafo:** O problema foi modelado como um grafo ponderado não direcionado. Cada vértice (de $1$ a $n$) representa uma cidade e cada aresta representa uma estrada bidirecional dotada de um peso correspondente ao seu comprimento.
* **Representação Adotada:** Optamos por utilizar Listas de Adjacência. Essa escolha é ideal para grafos esparsos, onde o número de arestas é muito menor que o quadrado do número de vértices. Uma matriz de adjacência alocaria posições demais, o que causaria um erro de estouro de memória (Out of Memory).
* **Tratamento de Pesos e Overflow:** Como o grafo pode ter até $10^5$ vértices e cada aresta pode pesar até $10^6$, o caminho máximo acumulado pode chegar a $10^{11}$. Esse valor ultrapassa o limite máximo de um inteiro de 32 bits (`int`). Portanto, modelamos os acumuladores de distância utilizando o tipo `long` (64 bits).
* **Multigrafos e Laços:** O enunciado prevê a existência de loops e múltiplas arestas entre os mesmos vértices. A nossa modelagem lida com isso de forma natural: todas as arestas são inseridas na lista de adjacência, e o algoritmo se encarrega de escolher e propagar apenas a de menor custo.

---

## Algoritmo Utilizado e Variações
Utilizamos o **Algoritmo de Dijkstra Clássico** para encontrar o caminho mínimo a partir de uma única origem (vértice $1$) até o destino (vértice $n$). 

* **Fila de Prioridade Mínima com Lazy Deletion:** Como a classe `PriorityQueue` nativa do Java não possui um método eficiente para atualizar chaves (decrease-key), contornamos essa limitação inserindo novos estados duplicados na fila sempre que encontramos uma distância menor. Para evitar reprocessamento inútil, aplicamos o mecanismo de "Deleção Preguiçosa": ao retirar um nó da fila, se a distância associada a ele for maior do que a menor distância já registrada para aquele vértice, nós simplesmente o ignoramos.
* **Reconstrução do Caminho:** Mantivemos um vetor auxiliar de predecessores chamado `parent[]`. Sempre que uma aresta sofre relaxamento com sucesso, registramos o vértice de origem no índice do vértice de destino. No final, se o vértice $n$ for alcançável, fazemos o rastreamento reverso partindo de $n$ até chegar a $1$, e invertemos a lista para imprimir o caminho na ordem correta.

---

## Análise de Complexidade
* **Complexidade de Tempo:** $O((V + E) \log V)$, onde $V$ é o número de vértices e $E$ é o número de arestas. Cada vértice e aresta é inserido e removido da Fila de Prioridade no máximo uma vez por caminho válido, levando tempo logarítmico.
* **Complexidade de Espaço:** $O(V + E)$ para armazenar a lista de adjacência do grafo, além dos arrays de controle estrutural (`dist[]` e `parent[]`), respeitando com folga o limite estrito de memória do problema (64 MB).

---

## Evidência de Submissão (Accepted)
Abaixo está a comprovação de que a solução foi submetida e aceita com sucesso pela plataforma de juiz online Codeforces:

![Resultado Accepted](evidencias/accepted.png)
