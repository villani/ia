import java.util.Random;

import weka.classifiers.lazy.IBk;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;

public class ValidacaoCruzada {

    public static void main(String[] args) throws Exception {
        
        // Definir a origem dos dados.
        DataSource arff = new DataSource("iris.arff");

        // Definir um conjunto a partir da origem.
        Instances iris = arff.getDataSet(4); // 4 é o índice do atributo classe.
        iris = iris.resample(new Random());

        // Definir a partição de treinamento.
        Instances irisTreino = iris.trainCV(3, 0); // 3 qt iteração, 0 n da iteração
        System.out.println("\n\nPARTIÇÃO DE TREINO");
        System.out.println(irisTreino);

        // Definir a partição de teste.
        Instances irisTeste = iris.testCV(3, 0); // 3 qt iteração, 0 n da iteração
        System.out.println("\n\nPARTIÇÃO DE TESTE");
        System.out.println(irisTeste);
        
        // Instanciar um classificador (objeto com IA).
        IBk knn = new IBk(3);

        // Treinar o classificador (aquisição de conhecimento).
        knn.buildClassifier(irisTreino);

        // HOLD OUT
        System.out.println("\n\nID,ORIGINAL,IDENTIFICADO");

        // Testar o classificador
        for (int i = 0; i < irisTeste.numInstances(); i++) {
            // - Id do teste
            String saida = (i + 1) + ",";

            // - Obter o exemplo de teste da base irisTeste
            Instance teste = irisTeste.instance(i);
            
            // - Mostrar o conceito original
            saida += teste.value(4) + ",";

            // - Remover o conceito original
            teste.setClassMissing();

            // - Classificador identificar o conceito
            double conceito = knn.classifyInstance(teste);

            // - Mostrar o conceito identificado
            saida += conceito;
            System.out.println(saida);
        }
    }
    
}