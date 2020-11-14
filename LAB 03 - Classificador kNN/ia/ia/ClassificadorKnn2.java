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
        DataSource arff = new DataSource("diabetes.arff");
        Instances diabetes = arff.getDataSet();
        diabetes.setClassIndex(8);
        System.out.println(diabetes);

        // CRIAR OBJETO Instance COM OS DADOS DO paciente
        //6	148	72	35	0	33.6	0.627	50	tested_positive
        // 6,148,72,35,0,33.6,0.627,50,tested_positive
        Instance paciente = new DenseInstance(9);
        paciente.setDataset(diabetes);
        paciente.setValue(0, 6);
        paciente.setValue(1, 148);
        paciente.setValue(2, 72);
        paciente.setValue(3, 35);
        paciente.setValue(4, 0);
        paciente.setValue(5, 33.6);
        paciente.setValue(6, 0.627);
        paciente.setValue(7, 50);

        System.out.println(paciente);

        // INSTANCIAR kNN(5) PARA CLASSIFICAR O paciente
        IBk knn = new IBk(5); // k = 5
        knn.buildClassifier(diabetes);
        double classe = knn.classifyInstance(paciente);
        paciente.setClassValue(classe);
        System.out.println("Resultado: " + classe);
        System.out.println("Resultado: " + paciente.stringValue(8));
    }

}
