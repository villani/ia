import java.util.Arrays;

import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;

public class Exercicio12_bN {
    public static void main(String[] args) throws Exception {
        
        // Objeto que contém a origem dos dados.
        DataSource arff = new DataSource("simpsons.arff");

        // Objeto que contém o conjunto de dados a partir da origem definida.
        Instances simpsons = arff.getDataSet();

        // Criar uma matriz a partir dos dados desse conjunto.
        double[][] conjunto = new double[simpsons.numInstances()-1][simpsons.numAttributes()];
        for (int i = 0; i < conjunto.length; i++) {
            conjunto[i] = simpsons.instance(i+1).toDoubleArray();
        }

        // Criar um vetor como exemplo de teste.
        double[] teste = simpsons.instance(0).toDoubleArray();

        // Usar o kNN para identificar o grupo desse vetor (tested_positive ou tested_negative).
        double classe = kNN(conjunto, teste, 3); // k = 3

        // Exibir o grupo identificado.
        System.out.println("Grupo identificado: " + classe);
    }

    public static double kNN(double[][] conjunto, double[] teste, int k) {

        // Criar um vetor que vai armazenar todas as (distâncias e classes) calculadas.
        Distancia[] distancias = new Distancia[conjunto.length];

        // Calcula a distância para cada um dos exemplos do conjunto em relação ao de teste.
        for (int i = 0; i < distancias.length; i++) {
            double distancia = getEucl(conjunto[i], teste);
            distancias[i] = new Distancia(distancia, conjunto[i][conjunto[i].length-1]);
        }

        // Ordenar o vetor com as distâncias.
        Arrays.sort(distancias);

        // Selecionar os k exemplos mais próximo (com as menores distâncias).
        // Identificar a classe que mais aparece entre os selecionados.
        int[] votos = new int[2];
        for (int i = 0; i < k; i++) {
            System.out.println(distancias[i]);
            votos[(int)distancias[i].classe]++;
        }

        // Retorna a classe identificada.
        return (votos[0] > votos[1])?0.0:1.0;
    }

    public static double getEucl(double[] ex1, double[] ex2) {
        double distancia = 0;
        for (int i = 0; i < ex1.length -1; i++) {
            distancia += Math.pow((ex1[i] - ex2[i]), 2);
        }
        distancia = Math.sqrt(distancia);
        return distancia;
    }
}

class Distancia implements Comparable<Distancia> {
    public double valor;
    public double classe;

    public Distancia(double v, double c) {
        valor = v;
        classe = c;
    }

    @Override
    public int compareTo(Distancia d) {
        int retorno = 0;
        if (this.valor > d.valor){
            retorno = 1;
        } else if (this.valor < d.valor) {
            retorno = -1;
        } else {
            retorno = 0;
        }
        return retorno;
    }

    @Override
    public String toString() {
        return String.valueOf(valor) + ";" + String.valueOf(classe);
    }
}