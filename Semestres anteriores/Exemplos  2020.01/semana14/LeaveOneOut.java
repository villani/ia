import java.util.Random;

import weka.classifiers.lazy.IBk;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;

class LeaveOneOut {
    public static void main(String[] args) throws Exception {
        
        // Definir a origem dos dados.
        DataSource arff = new DataSource("iris.arff");

        // Definir conjunto de dados a partir da origem definida.
        Instances iris = arff.getDataSet(4); // 4 é o índice do atributo classe.

        // Embaralhando os dados do conjunto.
        iris = iris.resample(new Random());

        // IMPLEMENTANDO TÉCNICA DE VALIDAÇÃO CRUZADA - Hold out
        // - Definir o número de partições.
        int particoes = 3;

        // - Definir o número de iterações.
        int iteracoes = 1;

        // - Identificando as linhas do resultado.
        int id = 1;

        // - Obter a partição de treino.
        for (int i = 0; i < iteracoes; i++) {

            Instances irisTreino = iris.trainCV(particoes, i);

            // - Obter a partição de teste.
            Instances irisTeste = iris.testCV(particoes, i);

            // - Definir objeto classificador (IA).
            IBk knn20 = new IBk(20);   // k = 20 vizinhos.
            IBk knn5 = new IBk(5);  // k = 5 vizinhos.

            // - Treinar o classificador.
            knn20.buildClassifier(irisTreino);
            knn5.buildClassifier(irisTreino);

            // - Obter o exemplo de teste.
            for (int t = 0; t < irisTeste.numInstances(); t++) {

                Instance teste = irisTeste.instance(t);
                
                // - Obter o conceito real do exemplo de teste.
                String saida = (id++) + ";" + teste.value(4);

                // - Remover o conceito real do exemplo de teste.
                teste.setClassMissing();

                // - Usar o classificador para identificar o conceito do exemplo de teste.
                double conceito3 = knn20.classifyInstance(teste);
                double conceito5 = knn5.classifyInstance(teste);

                // - Exibir o conceito identificado.
                saida += ";" + conceito3 + ";" + conceito5;
                System.out.println(saida);
            }
        }
    }
}