package ia;

import java.io.FileReader;
import weka.classifiers.lazy.IBk;
import weka.core.DenseInstance;
import weka.core.Instance;
import weka.core.Instances;

/**
 * Este exemplo contém o uso da implementação do algoritmo Vizinho Mais Próximo
 * disponível na biblioteca Weka.
 *
 * @author Prof. Me. Leonardo Villani
 */
public class Exemplo05_ClassificandoExemplos1 {

    /**
     * Treina o algoritmo Vizinho Mais Próximo com a base de dados dos Simpsons
     * e identifica a classe de um personagem desconhecido a partir do
     * conhecimento adquirido.
     *
     * @param args Não utiliza argumentos externos.
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {

        // DEFININDO CONJUNTO DE TREINAMENTO
        // - Definindo o arquivo ARFF que fornecerá os valores dos exemplos.
        FileReader arff = new FileReader("simpsons.arff");

        // - Tratando o conjunto de treinamento como um objeto Java.
        Instances simpsons = new Instances(arff);

        // - Definindo o índice do atributo classe.
        simpsons.setClassIndex(3);

        // DEFININDO EXEMPLO DESCONHECIDO
        // - Definindo a quantidade de atributos que o exemplo terá.
        Instance comic = new DenseInstance(4);

        // - Definindo como esses atributos devem ser (tipo dos atributos).
        comic.setDataset(simpsons);

        // - Definindo valores desses atributos
        comic.setValue(0, 8.0);     // comprimento do cabelo
        comic.setValue(1, 150.0);   // peso
        comic.setValue(2, 38.0);    // idade

        // DEFININDO ALGORITMO DE CLASSIFICAÇÃO (Vizinho Mais Próximo)
        IBk vizinho = new IBk();

        // TREINANDO O CLASSIFICADOR
        // - Aqui o classificador aprende a resolver problemas do tipo Simpsons
        vizinho.buildClassifier(simpsons);

        // IDENTIFICANDO CLASSE DO EXEMPLO DESCONHECIDO
        // - Aqui o classificador identifica o grupo em que o Comic deve fazer parte (resolução do problema).
        double resposta = vizinho.classifyInstance(comic);

        // - Mostrando a solução encontrada
        System.out.println("Resposta: " + resposta);

    }
}
