import java.util.Arrays;

import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;

public class Exercicio12_aula {

    public static void main(String[] args) throws Exception {
        
        // Objeto que representa a origem dos dados.
        DataSource arff = new DataSource("simpsons.arff");

        // Conjunto de dados instanciado a partir da origem definida.
        Instances simpsons = arff.getDataSet(1); // - Índice do atributo classe.
        
        // Representa o conjunto em uma matriz de double.
        double[][] conjunto = new double[simpsons.numInstances() -1][simpsons.numAttributes()];
        for (int i = 0; i < conjunto.length; i++) {
            conjunto[i] = simpsons.instance(i+1).toDoubleArray();
        }

        // Definir os dados do exemplo de teste.
        double[] teste = simpsons.instance(0).toDoubleArray();
        System.out.println("Teste: " + Arrays.toString(teste));

        // Usar o kNN para identificar o grupo que o exemplo de teste deve fazer parte.
        double classe = kNN(conjunto, teste, 3);

        // Exibir o grupo identificado.
        System.out.println("Classe: " + classe); // 0.0 - f | 1.0 - m
    }

    public static double getEucl(double[] ex1, double[] ex2) {
        double distancia = 0;
        for (int i = 0; i < ex1.length -1; i++) {
            distancia += Math.pow(ex1[i] - ex2[i], 2);
        }
        distancia = Math.sqrt(distancia);
        return distancia;
    }

    public static double kNN (double[][] conjunto, double[] teste, int k) {
        // Calcular a distância euclidiana de cada exemplo do conjunto em relação
        //  ao exemplo de teste.
        Distancia[] distancias = new Distancia[conjunto.length];
        for (int i = 0; i < conjunto.length; i++) {
            Distancia dist = new Distancia();
            dist.valor = getEucl(conjunto[i], teste);
            dist.classe = conjunto[i][conjunto[i].length -1];

            // Armazenar em um vetor todas as distâncias calculadas.
            distancias[i] = dist;
        }
        // Por em ordem crescente as distâncias do vetor.
        Arrays.sort(distancias);
        
        // Selecionar os k exemplos com as menores distâncias.
        // Identificar a classe que mais aparece entre os exemplos mais próximos (votação).
        int[] votos = new int[2];
        for (int i = 0; i < k; i++) {
            System.out.println("Vizinho " + i + ": " + distancias[i]);
            votos[(int)distancias[i].classe]++;
        }

        // Retorno a classe mais votada.
        return (votos[0] > votos[1])?0.0:1.0;
    }
}

class Distancia implements Comparable<Distancia> {

    public double valor;
    public double classe;

    @Override
    public int compareTo(Distancia d) {
        if (d.valor < this.valor) {
            return 1;
        } else if (d.valor > this.valor) {
            return -1;
        } else {
            return 0;
        }
    }

    @Override
    public String toString() {
        return String.valueOf(this.classe) + ":" + String.valueOf(this.valor);
    }

}