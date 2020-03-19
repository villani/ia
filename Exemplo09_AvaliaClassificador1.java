package ia;

import java.util.Random;
import weka.classifiers.lazy.IBk;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;

/**
 * Este exemplo contém a implementação de um arcabouço para a avaliação do
 * desempenho de um classificador
 * (<a href="https://github.com/villani/ia/blob/master/src/ia/Exemplo09_AvaliaClassificador1.java" target="_blank">Ver
 * código</a>). A abordagem de validação cruzada implementada foi a 'hold out'.
 *
 * @author Prof. Me. Leonardo Villani
 */
public class Exemplo09_AvaliaClassificador1 {

    public static void main(String[] args) throws Exception {

        // Definindo o conjunto de dados que será utilizado
        DataSource arff = new DataSource("iris.arff");
        Instances iris = arff.getDataSet();
        iris.setClassIndex(4); // índice do atributo classe

        // Embaralhando os exemplos da base
        iris = iris.resample(new Random());
        //System.out.println(iris);

        // Definindo partição de treino e de teste
        Instances baseTreino = iris.trainCV(3, 0);
        Instances baseTeste = iris.testCV(3, 0);

        // Definindo o classificador que será avaliado
        IBk knn = new IBk(3); // k = 3
        knn.buildClassifier(baseTreino); // treinando o classificador

        // Imprimindo os rótulos das colunas de saída
        System.out.println("real;knn(3)");

        // Classificando os exemplos da partição de teste
        for (int i = 0; i < baseTeste.numInstances(); i++) {
            Instance teste = baseTeste.instance(i);
            System.out.print(teste.stringValue(4) + ";");
            teste.setClassMissing();
            double classe = knn.classifyInstance(teste);
            teste.setClassValue(classe);
            System.out.println(teste.stringValue(4));
        }

    }

}
