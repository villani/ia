import weka.classifiers.bayes.NaiveBayes;
import weka.core.DenseInstance;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;

class RecomendaLentes {
    public static void main(String[] args) throws Exception {
        
        // Definir um objeto que representa a origem dos dados.
        DataSource arff = new DataSource("contact-lenses.arff");

        // Definir um objeto que representa o conjunto de dados.
        Instances lenses = arff.getDataSet(4); // 4 é o índice do atributo classe
        
        // Definir um objeto que representa o paciente que receberá a recomendação.
        Instance paciente = new DenseInstance(5); // 5 é a quantidade de atributos que o exemplo possui

        // - Definir como serão os atributos desse objeto.
        paciente.setDataset(lenses);

        // - Definir os valores atribuídos a esses atributos.
        paciente.setValue(0, "young");      // 0 - age
        paciente.setValue(1, "hypermetrope");      // 1 - spectacle-prescrip
        paciente.setValue(2, "yes");         // 2 - astigmatism
        paciente.setValue(3, "normal");     // 3 - tear-prod-rate

        // Definir um objeto que representar o classificador (quem faz a recomendação - IA).
        NaiveBayes bayes = new NaiveBayes();

        // - Treinar o classificador (construção do modelo).
        bayes.buildClassifier(lenses);

        // - Fazer a recomendação para o paciente definido (classificação).
        double recomendacao = bayes.classifyInstance(paciente);
        paciente.setValue(4, recomendacao);

        // Exibir a recomendação atribuída.
        System.out.println("\n\nRecomendação: " + recomendacao);
        System.out.println("Rótulo: " + paciente.stringValue(4));

        // Exibir o modelo usado para recomendar.
        System.out.println(bayes);
    }
}