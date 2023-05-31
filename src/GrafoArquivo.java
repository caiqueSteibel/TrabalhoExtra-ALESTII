package aula24_grafos_valorado;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class GrafoArquivo {
    public static GrafoValorado lerArquivoGrafo(String caminhoArquivo) {
        try (BufferedReader reader = new BufferedReader(new FileReader(caminhoArquivo))) {
            String line = reader.readLine();
            int numeroVertices = Integer.parseInt(line);
            GrafoValorado grafo = new GrafoValorado(numeroVertices);

            while ((line = reader.readLine()) != null) {
                String[] tokens = line.split(" ");
                int v = Integer.parseInt(tokens[0]);
                int w = Integer.parseInt(tokens[1]);
                int peso = Integer.parseInt(tokens[2]);
                grafo.adicionarAresta(v, w, peso);
            }

            return grafo;
        } catch (IOException e) {
            System.out.println(e);
        }

        return null;
    }

    public static boolean salvarArquivoGrafo(GrafoValorado g, String caminhoArquivo) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(caminhoArquivo))) {
            writer.write(g.numeroVertices() + "\n");

            for (int v = 0; v < g.numeroVertices(); v++) {
                for (GrafoValorado.Aresta aresta : g.listaAdjacencia[v]) {
                    int w = aresta.w;
                    int peso = aresta.peso;
                    writer.write(v + " " + w + " " + peso + "\n");
                }
            }

            return true;
        } catch (IOException e) {
            System.out.println(e);
        }

        return false;
    }
}
