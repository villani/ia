import weka.classifiers.lazy.IBk;
import weka.core.DenseInstance;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;

class Exercicio12 {
    public static void main(String[] args) throws Exception {
        
        // Definir a origem dos dados.
        DataSource arff = new DataSource("simpsons.arff");
        
        // Definir um conjunto a partir da origem definida.
        Instances simpsons = arff.getDataSet(1); // 1 é o índice do atributo classe
        
        // Definir o exemplo de teste.
        Instance comic = new DenseInstance(2); // 2 é o número de atributos.

        // Definir como (tipos) serão os atributos do exemplo de teste.
        comic.setDataset(simpsons);

        // Definir os valores dos atributos desse exemplo de teste.
        comic.setValue(0, 1); // compr. do cabelo = 8

        // Instanciar o objeto classificador.
        IBk knn = new IBk(3); // implementação do knn

        // Treinar o classificador.
        knn.buildClassifier(simpsons); // recebe os exemplos dos conceitos que devem ser aprendidos

        // Identificar o conceito que o exemplo de teste representa.
        double conceito = knn.classifyInstance(comic); // identifica a qual conceito pertence

        // Exibir o conceito identificado.
        System.out.println("Conceito: " + conceito);
        comic.setValue(1, conceito);
        System.out.println("Rótulo: " + comic.stringValue(1));

    }
}