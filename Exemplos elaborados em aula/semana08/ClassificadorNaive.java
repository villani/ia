import weka.classifiers.bayes.NaiveBayes;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;

class ClassificadorNaive {
    public static void main(String[] args) throws Exception {
        
        // Origem dos dados
        DataSource arff = new DataSource("weather.nominal.arff");

        // Conjunto de dados
        Instances weather = arff.getDataSet(4);

        // Classificador
        NaiveBayes bayes = new NaiveBayes();

        // Treinando o classificador
        bayes.buildClassifier(weather);

        // Mostre o conhecimento aprendido
        System.out.println(bayes);
        
    }
}