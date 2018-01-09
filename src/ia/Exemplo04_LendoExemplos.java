package ia;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

// Classes da biblioteca Weka que serão usadas neste exemplo
import weka.core.Instance;      // Representa um exemplo do conjunto
import weka.core.Instances;     // Representa um conjunto de dados

/**
 * Este é um exemplo de como classes da biblioteca Weka podem ser usadas em uma
 * aplicação Java.
 *
 * @author Prof. Me. Leonardo Villani
 */
public class Exemplo04_LendoExemplos {

    /**
     * Lê os dados de um conjunto de dados a partir de um arquivo ARFF e utiliza
     * os dados lidos para identificar a classe de um exemplo desconhecido.
     *
     * @param args Não utiliza argumentos externos.
     * @throws FileNotFoundException
     * @throws IOException
     * @throws Exception
     */
    public static void main(String[] args) throws FileNotFoundException, IOException, Exception {

        // Leitor do arquivo ARFF
        FileReader arff = new FileReader("simpsons.arff");

        // Criando conjunto a partir de um arquivo ARFF
        Instances simpsons = new Instances(arff);

        // Obtendo primeiro exemplo do conjunto
        Instance exemplo = simpsons.instance(1);
        System.out.println(exemplo);

        // Obtendo atributos do exemplo
        double cabelo = exemplo.value(0);
        double peso = exemplo.value(1);
        System.out.println("Cabelo: " + cabelo);
        System.out.println("Peso: " + peso);

        // Tratando atributos como elementos de um vetor
        double[] vetor = exemplo.toDoubleArray();
        System.out.println("Idade: " + vetor[2]);
        System.out.println("Classe: " + vetor[3]);

        // ============= EXERCICIO DE FIXAÇÃO ================
        // CRIANDO MATRIZ DE DOUBLE A PARTIR DO ARQUIVO ARFF
        // - Definindo dimensões da matriz
        int qtdeExemplos = simpsons.numInstances();
        int qtdeAtributos = simpsons.numAttributes();
        double[][] matrizSimpsons = new double[qtdeExemplos][qtdeAtributos];

        // - Definindo conteúdo da matriz
        for (int i = 0; i < qtdeExemplos; i++) {
            matrizSimpsons[i] = simpsons.instance(i).toDoubleArray();
        }
        // - Testando conteúdo
        System.out.println("Matriz: " + Arrays.toString(matrizSimpsons[0]));

        // DEFININDO EXEMPLO DESCONHECIDO
        double[] comic = {8, 290, 38, -1};

        // IDENTIFICANDO A CLASSE DO EXEMPLO DESCONHECIDO
        double resposta = Exemplo03_VizinhoMaisProximo.VizinhoMaisProximo(matrizSimpsons, comic);

        // - Exibindo a classe identificada
        System.out.println("Resposta: " + resposta);
    }

}
