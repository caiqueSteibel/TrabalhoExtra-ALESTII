package aula24_grafos_valorado;

public class BuscaEmProfundidade {
    private boolean[] visitados;
    private int[] antecessor;
    public int[] preordem;
    public int[] posordem;

    public BuscaEmProfundidade(GrafoValorado g, int verticeOrigem) {
        int numeroVertices = g.numeroVertices();
        this.visitados = new boolean[numeroVertices];
        this.antecessor = new int[numeroVertices];
        this.preordem = new int[numeroVertices];
        this.posordem = new int[numeroVertices];

        // Inicializa os arrays visitados, antecessor, preordem e posordem
        for (int i = 0; i < numeroVertices; i++) {
            this.visitados[i] = false;
            this.antecessor[i] = -1;
            this.preordem[i] = -1;
            this.posordem[i] = -1;
        }

        // Chama o método de busca em profundidade recursivo a partir do vértice de
        // origem
        buscaProfundidade(g, verticeOrigem);
    }

    private void buscaProfundidade(GrafoValorado g, int vertice) {
        this.visitados[vertice] = true;
        this.preordem[vertice] = vertice;

        for (GrafoValorado.Aresta aresta : g.listaAdjacencia[vertice]) {
            int proximoVertice = aresta.w;
            if (!this.visitados[proximoVertice]) {
                this.antecessor[proximoVertice] = vertice;
                buscaProfundidade(g, proximoVertice);
            }
        }

        this.posordem[vertice] = vertice;
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

        System.out.println("Preordem: ");
        for (int i = 0; i < preordem.length; i++) {
            System.out.print(preordem[i] + " ");
        }
        System.out.println();

        System.out.println("Posordem: ");
        for (int i = 0; i < posordem.length; i++) {
            System.out.print(posordem[i] + " ");
        }
        System.out.println();
    }
}
