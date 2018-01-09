package ia;

import java.io.FileReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Set;
import weka.classifiers.lazy.IBk;
import weka.core.DenseInstance;
import weka.core.Instance;
import weka.core.Instances;

/**
 * Este exemplo contém a implementação da distância euclidiana, do algoritmo
 * Vizinho Mais Proximo e do kNN
 * (<a href="https://github.com/villani/ia/blob/master/src/ia/Exemplo08_Classificadores.java" target="_blank">Ver
 * código</a>).
 *
 * @author Prof Me. Leonardo Villani
 */
public class Exemplo08_Classificadores {

    /**
     * Testa e compara as execuções e os resultados da implementação do
     * algoritmo kNN feita em sala de aula com aquela disponível na biblioteca
     * Weka.
     *
     * @param args Não utiliza argumentos externos.
     * @throws Exception Apenas propagando as exceções dos métodos utilizados.
     * Maiores detalhes na documentação dos métodos utilizados.
     */
    public static void main(String[] args) throws Exception {

        // Definindo o conjunto de treinamentos
        double[][] simpsons = {
            {0, 250, 36, 1}, // Homer
            {10, 150, 34, 0}, // Marge
            {2, 90, 10, 1}, // Bart
            {6, 78, 8, 0}, // Lisa
            {4, 20, 1, 0}, // Maggie
            {1, 170, 70, 1}, // Abe
            {8, 160, 41, 0}, // Selma
            {10, 180, 38, 1}, // Otto
            {6, 200, 45, 1} // Krusty
        };

        // Definindo o exemplo desconhecido
        double[] comic = {8, 80, 38, -1};

        // Passando como parâmetros a base de conhecimento e o problema para ser solucionado
        //double resposta = VizinhoMaisProximo(simpsons, comic);
        int k = 3;
        double resposta = kNN(simpsons, comic, k);

        // Exibindo a resposta encontrada
        System.out.println("Resposta: " + resposta);

        // IMPLEMENTAÇÃO WEKA do KNN
        // - Instanciando a base
        FileReader leitor = new FileReader("simpsons.arff");
        Instances simpsons2 = new Instances(leitor);
        simpsons2.setClassIndex(3);

        // - Instanciando o exemplo de teste
        Instance comic2 = new DenseInstance(4);
        comic2.setDataset(simpsons2);
        comic2.setValue(0, 8); // cabelo
        comic2.setValue(1, 80); //peso
        comic2.setValue(2, 38); //idade

        // - Instanciando o classificador Weka kNN
        IBk knn2 = new IBk(3); // k = 3
        knn2.buildClassifier(simpsons2); // treinando
        double classe2 = knn2.classifyInstance(comic2);
        System.out.println("Classe weka: " + classe2);
    }

    /**
     * Cálcula a distância euclidiana entre dois vetores de características.
     *
     * @param exemplo1 Vetor contendo as características do exemplo 1.
     * @param exemplo2 Vetor contendo as características do exemplo 2.
     * @return A distância euclidiana entre os dois vetores.
     * @throws Exception Se os vetores não tiver a mesma quantidade de
     * características.
     */
    public static double obtertEuclidiana(double[] exemplo1, double[] exemplo2) throws Exception {
        if (exemplo1.length != exemplo2.length) {
            throw new Exception("Os exemplos precisam ter a mesma quantidade de atributos.");
        }
        double distancia = -1;
        for (int i = 0; i < exemplo1.length - 1; i++) {
            distancia += Math.pow((exemplo1[i] - exemplo2[i]), 2);
        }
        return Math.sqrt(distancia);
    }

    /**
     * Encontra o exemplo do conjunto de treinamento que é mais próximo do
     * exemplo desconhecido e retorna o valor do atributo classe do exemplo
     * encontrado.
     *
     * @param conjunto Conjunto que contem os exemplos do mesmo contexto do
     * exemplo desconhecido.
     * @param desconhecido Exemplo a ser classificado.
     * @return O valor do atributo classe do exemplo mais próximo encontrado.
     * @throws Exception Se não houver ao menos um exemplo no conjunto ou se os
     * vetores não tiverem a mesma quantidade de características.
     */
    public static double VizinhoMaisProximo(double[][] conjunto, double[] desconhecido) throws Exception {

        // DEFININDO AS RETRIÇÕES
        if (conjunto.length < 1) { // Precisa ter ao menos um exemplo no conjunto.
            throw new Exception("O conjunto precisa ter ao menos um exemplo.");
        } else if (conjunto[0].length != desconhecido.length) { // Os exemplos comparados precisam ter a mesma quantidade de características.
            throw new Exception("Os exemplos comparados precisam ter a mesma quantidade de atributos.");
        }

        // DEFININDO PARÂMETROS INTERNOS DO ALGORÍTIMO
        int atributoClasse = conjunto[0].length - 1; // Normalmento o último atributo do vetor é usado para armazenar a classe.
        double menorDistancia = -1; // Apenas inicializando a menor distância.
        int exemploProximo = -1; // Apenas inicializando o exemplo mais próximo. -1 representa que o exemplo está indefinido.

        // COMPARANDO TODOS OS EXEMPLOS DE TREINAMENTO COM O EXEMPLO DESCONHECIDO
        for (int exemplo = 0; exemplo < conjunto.length; exemplo++) {

            // Calcula a distância de um exemplo de treinamento em relação ao exemplo desconhecido.
            double distancia = obtertEuclidiana(conjunto[exemplo], desconhecido);

            // Verifica se essa distância é a menor que já foi encontrada.
            if (distancia < menorDistancia || exemploProximo == -1) {
                menorDistancia = distancia;
                exemploProximo = exemplo;
            }
        }

        // RETORNANDO O VALOR DA CLASSE DO EXEMPLO MAIS PRÓXIMO
        //return menorDistancia; // Trocar comentário da linha para ver a menor distância
        return conjunto[exemploProximo][atributoClasse];
    }

    /**
     * Encontra os k exemplos do conjunto de treinamento que são mais próximo do
     * exemplo desconhecido e retorna o valor do atributo classe que mais
     * aparece entre os exemplo encontrados.
     *
     * @param conjunto Conjunto que contem os exemplos do mesmo contexto do
     * exemplo desconhecido.
     * @param exemplo Exemplo a ser classificado.
     * @param k Quantidade de exempos a serem encontrados.
     * @return O valor do atributo classe que mais aparece entre os exemplo
     * encontrados
     * @throws Exception Se não houver ao menos um exemplo no conjunto ou se os
     * vetores não tiverem a mesma quantidade de características.
     */
    public static double kNN(double[][] conjunto, double[] exemplo, int k) throws Exception {

        HashMap<Integer, Double> vizinhos = new HashMap<>();
        double[] distancias = new double[conjunto.length];
        double[] proximos = new double[k];

        // Calculando a distância dos exemplos do conjunto em relação ao exemplo desconhecido
        for (int i = 0; i < conjunto.length; i++) {
            double distancia = obtertEuclidiana(conjunto[i], exemplo);
            vizinhos.put(i, distancia);
            distancias[i] = distancia;
        }

        // Ordenando as distâncias encontradas
        Arrays.sort(distancias);

        // Obtendo os k exemplos com menor distância
        for (int i = 0; i < k; i++) {
            Set<Integer> chaves = vizinhos.keySet();
            for (Integer chave : chaves) {
                double distancia = vizinhos.get(chave);
                if (distancia == distancias[i]) {
                    proximos[i] = conjunto[chave][conjunto[chave].length - 1];
                    vizinhos.remove(chave);
                    break;
                }
            }
        }

        // Calculando a frequencia de cada classe
        HashMap<Double, Integer> frequencia = new HashMap<>();
        for (int i = 0; i < proximos.length; i++) {
            if (frequencia.containsKey(proximos[i])) {
                int votos = frequencia.remove(proximos[i]);
                votos++;
                frequencia.put(proximos[i], votos);
            } else {
                frequencia.put(proximos[i], 1);
            }
        }

        // Identificando a classe com maior frequência
        double maisVotada = 0;
        int votos = 0;
        for (Double classe : frequencia.keySet()) {
            int voto = frequencia.get(classe);
            if (voto > votos) {
                maisVotada = classe;
                votos = voto;
            }
        }

        return maisVotada;
    }
}
