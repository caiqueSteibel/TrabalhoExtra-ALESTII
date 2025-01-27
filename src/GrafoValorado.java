package aula24_grafos_valorado;

//Caique Cella, Otávio Cunha e Thales Veigas

import java.util.ArrayList;

public class GrafoValorado {

    class Aresta {
        public int v;
        public int w;
        public int peso;

        public Aresta(int v, int w, int peso) {
            this.v = v;
            this.w = w;
            this.peso = peso;
        }
    }

    ArrayList<Aresta> listaAdjacencia[];
    private int numeroVertices;

    public GrafoValorado(int numeroVertices) {
        this.numeroVertices = numeroVertices;
        this.listaAdjacencia = new ArrayList[this.numeroVertices];
        for (int i = 0; i < this.numeroVertices; i++) {
            this.listaAdjacencia[i] = new ArrayList<Aresta>();
        }
    }

    public void adicionarAresta(int v, int w, int peso) {
        Aresta e1 = new Aresta(v, w, peso);
        listaAdjacencia[v].add(e1);

        Aresta e2 = new Aresta(w, v, peso);
        listaAdjacencia[w].add(e2);
    }

    public String toDot() {
        String resultado = "graph G { " + System.lineSeparator();
        for (int i = 0; i < numeroVertices; i++) {
            resultado = resultado + "\t" + i + ";" + System.lineSeparator();
        }
        boolean[][] jaImpresso = new boolean[numeroVertices][numeroVertices];
        for (int i = 0; i < numeroVertices; i++) {
            for (int j = 0; j < listaAdjacencia[i].size(); j++) {
                if (!jaImpresso[i][j]) {
                    resultado += "\t" + listaAdjacencia[i].get(j).v + "--" + listaAdjacencia[i].get(j).w + "  [label="
                            + listaAdjacencia[i].get(j).peso + "]" + ";" + System.lineSeparator();
                    jaImpresso[listaAdjacencia[i].get(j).v][listaAdjacencia[i].get(j).w] = true;
                    jaImpresso[listaAdjacencia[i].get(j).w][listaAdjacencia[i].get(j).v] = true;
                }
            }
        }
        resultado += "}";
        return resultado;
    }

    public static void main(String[] args) {
        GrafoValorado g = new GrafoValorado(4);
        g.adicionarAresta(0, 1, 33);
        g.adicionarAresta(0, 2, 10);
        g.adicionarAresta(1, 2, 99);
        g.adicionarAresta(0, 3, 200);

        System.out.println("O grafo possui " + g.numeroVertices() + " vértices"); // Saída: 4
        System.out.println("O grafo possui " + g.numeroArestas() + " arestas"); // Saída: 4

        g.removerAresta(0, 2);
        System.out.println("Agora o grafo possui " + g.numeroArestas() + " arestas"); // Saída: 3

        BuscaEmProfundidade buscaProfundidade = new BuscaEmProfundidade(g, 0);
        buscaProfundidade.imprimirResultado();

        BuscaEmLargura buscaLargura = new BuscaEmLargura(g, 0);
        buscaLargura.imprimirResultado();

        String caminhoArquivo = "grafo.txt";

        boolean sucesso = GrafoArquivo.salvarArquivoGrafo(g, caminhoArquivo);

        if (sucesso) {
            System.out.println("Arquivo de exemplo criado com sucesso.");
        } else {
            System.out.println("Falha ao criar o arquivo de exemplo.");
        }

        System.out.println(g.toDot());
    }

    public void removerAresta(int v, int w) {
        for (int i = 0; i < listaAdjacencia[v].size(); i++) {
            if (listaAdjacencia[v].get(i).w == w) {
                listaAdjacencia[v].remove(i);
                break;
            }
        }
        for (int i = 0; i < listaAdjacencia[w].size(); i++) {
            if (listaAdjacencia[w].get(i).w == v) {
                listaAdjacencia[w].remove(i);
                break;
            }
        }
    }

    public int grau(int vertice) {
        ArrayList<Aresta> listaVertices = listaAdjacencia[vertice];
        int qtd = listaVertices.size();
        return qtd;
    }

    public int numeroVertices() {
        return numeroVertices;
    }

    public int numeroArestas() {
        int numeroArestas = 0;
        for (int i = 0; i < numeroVertices; i++) {
            numeroArestas += listaAdjacencia[i].size();
        }
        // Como cada aresta é adicionada duas vezes (uma vez para cada vértice
        // adjacente),
        // o número real de arestas é metade do valor calculado
        return numeroArestas / 2;
    }
}
