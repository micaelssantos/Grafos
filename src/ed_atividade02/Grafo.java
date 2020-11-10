package ed_atividade02;

/**
 *
 * @author Micael de Sousa Santos
 */
public class Grafo {

    private int V; //Número de Vértices
    private int E; //Número de Arestas (Edges)

    private boolean adjacencias[][];

    // Atributos abaixo armazenam resultados de busca no Grafo.
    private int distancia[];
    private int pai[];

    /**
     * Recebe o indice de um vertice e retorna o seu GRAU 
     * O Grau de um vértice é a quantidade de vizinhos.
     *
     * @param vertice
     * @return
     */
    public int grau(int vertice) {
        int nvizinhos = 0;

        for (int i = 0; i < V; i++) {
            if (adjacencias[vertice][i]) {
                nvizinhos++;
            }
        }
        return nvizinhos;
    }

    /**
     * Recebe o Índice de um Vértice e impre na tela todos os seus vizinhos,
     * Isto é, todos os vértices com os quais ele está diretamente ligado.
     *
     * @param vertice
     */
    public void exibeVizinhos(int vertice) {
        System.out.println("Vizinhos do " + vertice + ":");
        // Código Clássico para Descobrir os Vizinhos dos Vértices
        for (int i = 0; i < V; i++) {
            //Podemos usar o método vizinhos ou percorrer a matriz de Adjacencias
            if (vizinhos(vertice, i)) {
                System.out.print(i + " ");
            }
        }
        System.out.println();
    }

    /**
     * Cria um Grafo com a quantidade de Vertices recebida como argumento
     *
     * @param numVertices
     */
    public Grafo(int numVertices) {
        V = numVertices;
        E = 0;
        adjacencias = new boolean[V][V];
    }

    /**
     * Recebe dois rótulos de vértice e cria aresta entre eles
     *
     * @param x
     * @param y
     */
    public void criaAresta(int x, int y) {
        if (vizinhos(x, y)) {
            // Se já são vizinhos, não faço nada.
            return;
        }
        adjacencias[x][y] = true;
        adjacencias[y][x] = true;

        E++;
    }

    /**
     * Recebe dois rótulos de vértices e destrói a aresta x-y, Se existir
     *
     * @param x
     * @param y
     */
    public void removeAresta(int x, int y) {
        if (vizinhos(x, y)) {
            adjacencias[x][y] = false;
            adjacencias[y][x] = false;
            E--;
        }
    }

    /**
     * Recebe dois rótulos de vértice e retorna true, se eles forem vizinhos;
     * false, caso contário.
     *
     * @param x
     * @param y
     * @return
     */
    public boolean vizinhos(int x, int y) {
        return adjacencias[x][y];
    }

    /**
     * Retorna o número de Arestas do Grafo
     *
     * @return
     */
    public int numeroArestas() {
        return E;
    }

    /**
     * Retorna o número de vértices que o grafo possui.
     *
     * @return
     */
    public int numeroVertices() {
        return V;
    }

    /**
     * Exibe na Tela a matriz de Adjacências. na Representação interna: True ou
     * False Também na Representação de Zeros e 1;
     */
    public void mostraMatriz() {

        //Obs.: A Matriz é V por V
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                if (adjacencias[i][j]) {
                    System.out.print("1 ");
                } else {
                    System.out.print("0 ");
                }
//                System.out.print(adjacencias[i][j]);
//                System.out.print(" | ");
            }
            System.out.println();
        }
    }

    /**
     * Busca em Largura (Breadth-First Search)
     *
     * Recebe o vértice inicial (s) e descobre os sucessivos níveis de
     * vizinhança: N0 (Próprio s), N1(Vizinhos), N2 (Vizinhos dos vizinhos),
     * etc...
     *
     * @param s
     */
    public void BFS(int s) {

        distancia = new int[V];
        pai = new int[V];

        for (int i = 0; i < V; i++) {
            // -1 Significa ainda não calculado
            distancia[i] = -1;
            pai[i] = -1;
        }

        // Guarda quem ainda precisa ser examinado.
        Fila F = new Fila(V);

        // Primeiro vértice descoberto é o próprio s
        // visitado[s] = true;
        distancia[s] = 0;
        pai[s] = s; // convenção: raiz da busca é o próprio pai
        F.insere(s);

        // Enquanto a fila não estiver vazia...
        while (F.tamanho() > 0) {
            //removo um vértice da fila para examinar
            int x = F.remove();

            // Vou examinar a vizinhança do X
            for (int y = 0; y < V; y++) {
                // com boolean
                // if (vizinhos(x,y) && !visitado[y]){

                // Com int para descobrir a distancia
                if (vizinhos(x, y) && distancia[y] < 0) {
                    // descobri um novo vértice (y)
                    // y com certeza está em outra camada de vizinhança

                    //print de "debug"
                    System.out.println("Vertice " + x + " descobriu o vértice " + y);

                    pai[y] = x;

                    // y está à distância 1 a mais do que a distância do x
                    distancia[y] = distancia[x] + 1;
                    F.insere(y);
                    // com boolean
                    // visitado[y] = true;
                }
            }
        }
        
        System.out.println();
    }

    public void mostraDistancia() {
        for (int i = 0; i < V; i++) {
            System.out.println("Distancia[" + i + "] = " + distancia[i]);
        }
    }
    
    public void distanciaAte(int vertice){
        System.out.println("Distancia: " + distancia[vertice]);
    }

    public void mostraPai() {
        for (int i = 0; i < V; i++) {
            System.out.println("Pai[" + i + "] = " + pai[i]);
        }
    }

    public void caminhoAte(int vertice) {
        // versão 1: imprime na ordem "certa", da raiz até
        // o vértice

        if (vertice == pai[vertice]) {
            System.out.print(vertice);
        } else {
            // o caminho até o vértice é o caminho até o pai,
            // seguido do próprio vértice
            caminhoAte(pai[vertice]);
            System.out.print(" -> " + vertice);
        }
    }
}