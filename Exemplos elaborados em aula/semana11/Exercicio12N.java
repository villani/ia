import weka.classifiers.lazy.IBk;
import weka.core.DenseInstance;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;

class Exercicio12N {
    public static void main(String[] args) throws Exception {
        
        // Objeto que representa a origem dos dados.
        DataSource arff = new DataSource("simpsons.arff");

        // Objeto que representa o conjunto a partir da origem definida.
        Instances simpsons = arff.getDataSet(1); // 1 é o índice do atributo classe.

        // Objeto que representa o exemplo a ser classificado.
        Instance comic = new DenseInstance(2); // 2 é a quantidade de atributos do exemplo.

        // Especificar como serão os atributos desse exemplo.
        comic.setDataset(simpsons);

        // Especificar os valores de cada atributo.
        comic.setValue(0, 1); // Compr. do cabelo = 8

        // Objeto classificador (IA).
        IBk knn = new IBk(3); // 3 é a quantidade de vizinhos.

        // Passar o conhecimento necessário para o classificador.
        knn.buildClassifier(simpsons);

        // Identificar o conceito que o exemplo de teste representa.
        double conceito = knn.classifyInstance(comic);

        // Exibir o conceito identificado.
        System.out.println("Conceito: " + conceito);
        comic.setValue(1, conceito);
        System.out.println("Rótulo: " + comic.stringValue(1));
    }
}