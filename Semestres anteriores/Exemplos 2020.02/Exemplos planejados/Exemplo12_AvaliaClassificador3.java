package ia;

import weka.classifiers.bayes.NaiveBayes;

import weka.classifiers.trees.Id3;
import weka.classifiers.trees.J48;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;

/**
 * Este exemplo contém um arcabouço para avaliação de classificadores de
 * exemplos caracterizados com valores nominais
 * (<a href="https://github.com/villani/ia/blob/master/src/ia/Exemplo12_AvaliaClassificador3.java" target="_blank">Ver
 * código</a>).
 *
 * @author Prof. Me. Leonardo Villani
 */
public class Exemplo12_AvaliaClassificador3 {

    /**
     * Executa a abordagem escolhida para validação cruzada.
     *
     * @param args Não utiliza argumentos externos.
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {

        leaveOneOut();

    }

    /**
     * Imprime os resultados dos testes de três diferentes classificadores de
     * exemplos caracterizados com valores nominais.
     *
     * @throws Exception
     */
    public static void leaveOneOut() throws Exception {
        // CRIAR OBJETO Instances BASEADO NESSE ARFF
        DataSource arff = new DataSource("contact-lenses.arff");
        Instances contact = arff.getDataSet();

        // DEFININDO ÍNDICE DO ATRIBUTO CLASSE
        int iClasse = contact.numAttributes() - 1;
        contact.setClassIndex(iClasse);

        // DEFININDO RÓTULOS DAS COLUNAS        
        System.out.println("real;id3;j48;naive");

        // IMPLEMENTANDO leave-one-out
        int k = contact.numInstances();
        for (int j = 0; j < k; j++) {

            // OBTENDO SUBCONJUNTO DE teste E treino
            Instances baseTreino = contact.trainCV(k, j);
            Instances baseTeste = contact.testCV(k, j);

            // INSTANCIANDO CLASSIFICADORES A SEREM AVALIADOS
            Id3 arvore1 = new Id3();
            J48 arvore2 = new J48();
            NaiveBayes naive = new NaiveBayes();

            // TREINANDO CLASSIFICADORES
            arvore1.buildClassifier(baseTreino);
            arvore2.buildClassifier(baseTreino);
            naive.buildClassifier(baseTreino);

            for (int i = 0; i < baseTeste.numInstances(); i++) {

                // IMPRIMINDO RÓTULO REAL
                Instance teste = baseTeste.instance(i);
                System.out.print(teste.stringValue(4) + ";");

                // REMOVENDO O VALOR DO ATRIBUTO CLASSE
                teste.setClassMissing();

                // IDENTIFICANDO A CLASSE POR MEIO DOS CLASSIFICADORES
                double classe1 = arvore1.classifyInstance(teste);
                double classe2 = arvore2.classifyInstance(teste);
                double classe3 = naive.classifyInstance(teste);

                // MOSTRANDO CLASSE ENCONTRADA PELO CLASSIFICADOR 1
                teste.setClassValue(classe1);
                System.out.print(teste.stringValue(4) + ";");

                // MOSTRANDO CLASSE ENCONTRADA PELO CLASSIFICADOR 2
                teste.setClassValue(classe2);
                System.out.print(teste.stringValue(4) + ";");

                // MOSTRANDO CLASSE ENCONTRADA PELO CLASSIFICADOR 3
                teste.setClassValue(classe3);
                System.out.println(teste.stringValue(4));
            }
        }
    }

}
