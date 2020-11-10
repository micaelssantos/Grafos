package ed_atividade02;

/**
 * Atividade Avaliativa 2 
 * 
 * Essa atividade consiste de escrever um programa em
 * Java ou C# que realize a seguinte tarefa:
 *
 * 1) Leia de um arquivo grafo.txt a descrição de um grafo. Esse arquivo será
 * organizado da seguinte forma: 
 * 
 * - A primeira linha contém o número N de vértices; 
 * - As seguintes N linhas contêm a matriz de adjacências que descreve o grafo.
 *
 * 2) Crie esse grafo como um objeto do tipo Grafo em seu programa;
 *
 * 3) Permita que o usuário digite um vértice de origem e um vértice de destino.
 *
 * O programa irá então mostrar as seguintes informações: 
 * - A distância entre os dois vértices; 
 * - Um caminho de comprimento mínimo da origem até o destino.
 *
 * Entrada do programa: 0 (vértice de origem) 3 (vértice de destino)
 *
 * Saída correspondente: 2 (distância) 0->2->3 (caminho)
 *
 * @author Micael de Sousa Santos
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        /**
         * Cria objeto Scanner para receber os valores.
         */
        Scanner leitor = new Scanner(System.in);
        
        /**
         * Caminho onde o arquivo grafo.txt está localizado.
         */
        String nome = "C:\\Users\\mcssantos\\Documents\\NetBeansProjects\\ED_Atividade02\\src\\ed_atividade02\\grafo.txt";
        
        try {
            FileReader arq = new FileReader(nome);
            BufferedReader lerArq = new BufferedReader(arq);

            /**
             * Lê a primeira linha do Arquivo.
             */
            String linha = lerArq.readLine();
            
            /**
             * Cria o Objeto Grafo e passa a primeira linha como parâmetro para
             * informar a quantidade de Vértices.
             */
            Grafo G = new Grafo(Integer.parseInt(linha));
            
            /**
             * Recebe a Quantidade de Vertices do Objeto Grafo e Exibe na Tela;
             * 
             * Obs.: Apenas para fim de Validação.
             */
            int vertices = G.numeroVertices();
            System.out.println("O Número de Vertices recebidos do Arquivo é: " + vertices);
            
            /**
             * Lê a Segunda linha.
             */
            linha = lerArq.readLine();
            
            /**
             * Realiza a leitura do de todo o Arquivo a partir da segunda Linha
             * e cria as arestas de acordo com a Matriz de Adjacencia informada.
             */
            for (int linhaMatriz = 0; linhaMatriz < vertices ; linhaMatriz++) {
                int aux = 0;
                /**
                 * Captura o primeiro caracter da linha em que está.
                 */
                char result = linha.charAt(aux);
                for (int colunaMatriz = 0; colunaMatriz < vertices; colunaMatriz++) {
                    /**
                     * Verifica se o caracter na variável é igual a 1 e 
                     * cria as Arestas correspondentes.
                     */
                    if (result == '1') {
                        G.criaAresta(linhaMatriz, colunaMatriz);
                    }
                    /**
                     * Validação para não estourar o indice de caracter da linha.
                     */
                    if (colunaMatriz + 1 >= vertices) {

                    } 
                    /**
                     * Se não ultrapassou o tamanho da linha, adiciono o próximo
                     * caracter da linha na variavel.
                     */
                    else {
                        result = linha.charAt(colunaMatriz + 1);
                    }
                }
                /**
                 * Realiza a leitura da proxima linha.
                 */
                linha = lerArq.readLine();
            }
            /**
             * Recebe o Vértice de Origem.
             */
            System.out.print("Digite o Vértice de Origem: ");
            int origem = leitor.nextInt();
            
            /**
             * Recebe o Vértice de Destino.
             */
            System.out.print("Digite o Vértice de Destino: ");
            int destino = leitor.nextInt();
            
            System.out.println();
            
            /**
             * Realiza a BFS através do vértice de Origem informado 
             * e exibe as descobertas a partir da Busca realizada.
             */
            G.BFS(origem);
            
            /**
             * Exibe a Distância até o Vertice informado.
             */
            G.distanciaAte(destino);
            
            /**
             * Exibe o caminho até o vertice informado.
             */
            System.out.println("O Caminho até o vértice " + destino + " é: ");
            G.caminhoAte(destino);
            System.out.println();
            
            /**
             * Fecho o arquivo.
             */
            arq.close();
        } catch (IOException e) {
            System.err.printf("Erro na abertura do arquivo: %s.\n",
                    e.getMessage());
        }
    }
}