package ed_atividade02;

/**
 *
 * @author Micael de Sousa Santos
 */
public class Fila {

    /**
     * A estrutura de dados FILA armazena uma sequência de dados.
     *
     * A política de acesso é do tipo FIFO: First In, First Out. Ou seja,
     * podemos inserir vários dados, e se depois fizermos remoções, os elementos
     * serão removidos NA MESMA ORDEM em que foram inseridos.
     */
    private int[] fila;         // guarda os dados inseridos na estrutura
    private int ini;            // Índice do início da fila
    private int fim;            // Índice do fim da fila
    private int tamanho;        // número de elementos atualmente presentes na estrutura
    private int tamanhoTotal;   // número de elementos total que já passaram pela estrutura

    /*
    Construtor da classe Fila. Recebe a capacidade da estrutura, isto é, quantos
    elementos podem estar armazenados de uma só vez, no máximo.
     */
    public Fila(int capacidade) {
        fila = new int[capacidade];
        ini = 0;    // linha opcional --> Java inicializa int com 0
        fim = -1;
        tamanho = 0;
        tamanhoTotal = 0;
    }

    /*
    Recebe um novo dado para armazenar na fila. Insere no fim da fila
    Lança uma exception se a fila já estiver cheia.
     */
    public void insere(int novo) {
        if (tamanho == fila.length) {
            throw new IndexOutOfBoundsException("Tentando inserir em fila lotada.");
        }
        tamanho++;
        tamanhoTotal++;
        // se fim fosse ficar igual a fila.length, volta para 0 (zero)
        fim = fim + 1 % fila.length;
        fila[fim] = novo;
    }

    /*
    Remove o elemento que está¡ no início da fila e o retorna.
    Lança uma exception se a fila estiver vazia.
     */
    public int remove() {
        // vamos remover o primeiro da fila
        int removido = this.primeiro();

        //se ini fosse ficar igual a fila.length, volta para 0 (zero)
        ini = ini + 1 % fila.length;

        tamanho--;
        return removido;
    }

    /* 
    Retorna o elemento que está no início da fila.
    Se a fila estiver vazia, lança uma exception.
     */
    public int primeiro() {
        if (tamanho == 0) {
            throw new IndexOutOfBoundsException("Tentando acessar início de fila vazia.");
        };
        return fila[ini];
    }

    /*
    Retorna o número de elementos presentes na estrutura.
     */
    public int tamanho() {
        return tamanho;
    }

    /*
    Retorna o número total de elementos que já passaram na estrutura.
     */
    public int getTamanhoTotal() {
        return tamanhoTotal;
    }
}
