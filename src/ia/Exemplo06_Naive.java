package ia;

import java.io.FileReader;
import weka.classifiers.bayes.NaiveBayes;
import weka.core.DenseInstance;
import weka.core.Instance;
import weka.core.Instances;

/**
 * Este exemplo contém o uso da implementação do algoritmo Naive Bayes
 * disponível na biblioteca Weka.
 *
 * @author Prof. Me. Leonardo Villani
 */
public class Exemplo06_Naive {

    /**
     * Treina o algoritmo Naive Bayes com a base de dados 'Lentes de Contato' e
     * identifica a classe de um paciente desconhecido a partir do conhecimento
     * adquirido.
     *
     * @param args Não utiliza argumentos externos.
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {

        // DEFININDO O CONJUNTO DE DADOS
        FileReader arff = new FileReader("contact-lenses.arff");
        Instances contact = new Instances(arff);
        contact.setClassIndex(4);
        System.out.println(contact); // Imprimindo o objeto Conjunto de Dados

        // DEFININDO O CLASSIFICADOR
        NaiveBayes naive = new NaiveBayes();
        naive.buildClassifier(contact);
        System.out.println(naive); // Imprimindo o objeto classificador

        // DEFININDO O EXEMPLO A SER CLASSIFICADO
        // young, hiper, yes, normal
        Instance exemplo = new DenseInstance(5);
        exemplo.setDataset(contact);
        exemplo.setValue(0, "young");
        exemplo.setValue(1, "hypermetrope");
        exemplo.setValue(2, "yes");
        exemplo.setValue(3, "normal");

        double classe = naive.classifyInstance(exemplo);
        System.out.println("Classe: " + classe);
        exemplo.setValue(4, classe); // definindo o valor do atributo classe
        System.out.println("Rótulo: " + exemplo.stringValue(4));

    }

}
