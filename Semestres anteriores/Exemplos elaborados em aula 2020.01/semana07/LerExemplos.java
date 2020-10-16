import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;

class LerExemplos {
    public static void main(String[] args) throws Exception {
        
        DataSource arff = new DataSource("simpsons.arff");
        Instances simpsons = arff.getDataSet();
        System.out.println(simpsons);

    }
}