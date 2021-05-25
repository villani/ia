import weka.classifiers.bayes.NaiveBayes;               // Implementação do algoritmo Naïve Bayes
import weka.core.DenseInstance;                         // Implementação do tipo Instance
import weka.core.Instance;                              // Interface que descreve um objeto Exemplo
import weka.core.Instances;                             // Descreve um conjunto de dados
import weka.core.converters.ConverterUtils.DataSource;  // Origem dos dados

class RecomendarLentes {

    public static void main(String[] args) throws Exception {
        
        // Definir a origem dos dados.
        DataSource arff = new DataSource("contact-lenses.arff");

        // Definir o objeto que representa o conjunto de dados.
        Instances lenses = arff.getDataSet(4); // - atributo com índice 4 (atributo classe)

        // Definir o objeto que representa o paciente que receberá a recomendação.
        Instance paciente = new DenseInstance(5);   // - 5 atributos.
        paciente.setDataset(lenses);                // - como serão os 5 atributos;
        paciente.setValue(0, "presbyopic");              // 0 - age
        paciente.setValue(1, "myope");              // 1 - spectacle-prescrip
        paciente.setValue(2, "no");                 // 2 - astigmatism
        paciente.setValue(3, "reduced");             // 3 - tear-prod-rate
        
        // Definir o objeto que fará a recomendação (o classificador).
        NaiveBayes bayes = new NaiveBayes();

        // Treinar o classificador.
        bayes.buildClassifier(lenses);

        // Fazer a recomendação.
        double recomendacao = bayes.classifyInstance(paciente);
        paciente.setValue(4, recomendacao);

        // Exibir a recomendação feita.
        System.out.println("\n\nRecomendação: " + recomendacao);
        System.out.println("Rótulo: " + paciente.stringValue(4));

        // Exibindo o modelo construído pelo classificador
        System.out.println(bayes);

    }
}