package ia;

import weka.classifiers.lazy.IBk;
import weka.core.DenseInstance;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;

/**
 * Este exemplo contém o uso da implementação do kNN disponível na biblioteca Weka.
 *
 * @author Prof. Me. Leonardo Villani
 */
public class ClassificadorKnn2 {

    /**
     * Utiliza o kNN, com k igual a 3, para identificar a classe que deve ser
     * atribuida a um personagem desconhecido.
     *
     * @param args Não utiliza argumentos externos.
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {

        // CRIAR OBJETO Instances BASEADO NESSE ARFF
        DataSource arff = new DataSource("simpsons.arff");
        Instances simpsons = arff.getDataSet();
        simpsons.setClassIndex(3);
        System.out.println(simpsons);

        // CRIAR OBJETO Instance COM OS DADOS DO COMIC
        Instance comic = new DenseInstance(4);
        comic.setDataset(simpsons);
        comic.setValue(0, 8);
        comic.setValue(1, 290);
        comic.setValue(2, 38);

        System.out.println(comic);

        // INSTANCIAR kNN(3) PARA CLASSIFICAR O COMIC
        IBk knn = new IBk(3); // k = 3
        knn.buildClassifier(simpsons);
        double classe = knn.classifyInstance(comic);
        comic.setClassValue(classe);
        System.out.println("Resultado: " + classe);
    }

}
