package aula24_grafos_valorado;

//Caique Cella, Otávio Cunha e Thales Veigas

import java.util.LinkedList;
import java.util.Queue;

public class BuscaEmLargura {
    private boolean[] visitados;
    private int[] antecessor;
    private int[] distancia;

    public BuscaEmLargura(GrafoValorado g, int verticeOrigem) {
        int numeroVertices = g.numeroVertices();
        this.visitados = new boolean[numeroVertices];
        this.antecessor = new int[numeroVertices];
        this.distancia = new int[numeroVertices];

        // Inicializa os arrays visitados, antecessor e distancia
        for (int i = 0; i < numeroVertices; i++) {
            this.visitados[i] = false;
            this.antecessor[i] = -1;
            this.distancia[i] = -1;
        }

        // Chama o método de busca em largura a partir do vértice de origem
        buscaLargura(g, verticeOrigem);
    }

    private void buscaLargura(GrafoValorado g, int vertice) {
        Queue<Integer> fila = new LinkedList<>();

        this.visitados[vertice] = true;
        this.distancia[vertice] = 0;
        fila.add(vertice);

        while (!fila.isEmpty()) {
            int verticeAtual = fila.poll();

            for (GrafoValorado.Aresta aresta : g.listaAdjacencia[verticeAtual]) {
                int proximoVertice = aresta.w;

                if (!this.visitados[proximoVertice]) {
                    this.visitados[proximoVertice] = true;
                    this.antecessor[proximoVertice] = verticeAtual;
                    this.distancia[proximoVertice] = this.distancia[verticeAtual] + 1;
                    fila.add(proximoVertice);
                }
            }
        }
    }

    public void imprimirResultado() {
        System.out.println("Visitados: ");
        for (int i = 0; i < visitados.length; i++) {
            System.out.print(visitados[i] + " ");
        }
        System.out.println();

        System.out.println("Antecessor: ");
        for (int i = 0; i < antecessor.length; i++) {
            System.out.print(antecessor[i] + " ");
        }
        System.out.println();

        System.out.println("Distância: ");
        for (int i = 0; i < distancia.length; i++) {
            System.out.print(distancia[i] + " ");
        }
        System.out.println();
    }
}
