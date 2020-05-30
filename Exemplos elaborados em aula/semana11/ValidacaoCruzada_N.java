import java.util.Random;

import weka.classifiers.lazy.IBk;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;

class ValidacaoCruzada_N {
    public static void main(String[] args) throws Exception {
        
        // Objeto que representa a origem dos dados.
        DataSource arff = new DataSource("iris.arff");

        // Objeto que representa o conjunto de dados a partir da origem definida.
        Instances iris = arff.getDataSet(4); // 4 é o índice do atributo classe.
        iris = iris.resample(new Random()); // embaralhando a base.

        // Obter o conjunto para treinar o classificador.
        Instances irisTreino = iris.trainCV(3, 0);
        //System.out.println("\n\nPARTIÇÃO DE TREINAMENTO");
        //System.out.println(irisTreino);

        // Obter o conjunto para testar o classificador.
        Instances irisTeste = iris.testCV(3, 0);
        //System.out.println("\n\nPARTIÇÃO DE TESTE");
        //System.out.println(irisTeste);

        // Objeto classificador (IA).
        IBk knn = new IBk(3);
        
        // IMPLEMENTANDO O HOLD OUT
        // Treinando o classificador.
        knn.buildClassifier(irisTreino);

        // Repetição - Fornecendo os exemplos do conjunto de teste.
        System.out.println("ID;REAL;IDENTIFICADO");
        for (int i = 0; i < irisTeste.numInstances(); i++) {
            // - Exibir o 'id' da iteração.
            String saida = (i + 1) + ";";

            // - Obter o exemplo de teste.
            Instance teste = irisTeste.instance(i);

            // - Exibir o conceito real.
            saida += teste.value(4) + ";";

            // - Remover o conceito do exemplo.
            teste.setClassMissing();

            // - Identificar o conceito que o exemplo representa.
            double conceito = knn.classifyInstance(teste);

            // - Exibir o conceito identificado.
            saida += conceito;
            System.out.println(saida);
        }
    }
}