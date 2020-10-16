import java.io.FileReader;

import weka.classifiers.lazy.IBk;
import weka.core.DenseInstance;
import weka.core.Instance;
import weka.core.Instances;

class LerExemplos2 {
    public static void main(String[] args) throws Exception { 
        FileReader arff = new FileReader("simpsons.arff");
        Instances simpsons = new Instances(arff);
        simpsons.setClassIndex(3); // definindo o atributo classe dos exemplos
        System.out.println(simpsons.instance(2));

        IBk vizinho = new IBk(); // Vizinho Mais Próximo
        // buildClassifier -> treinar classificador
        // classifyInstance -> identificar conceitos (classificar)
        
        // TREINANDO O CLASSIFICADOR
        vizinho.buildClassifier(simpsons);

        // CRIANDO UM NOVO EXEMPLO DE CONCEITO DESCONHECIDO
        Instance comic = new DenseInstance(4); // qtd atributos
        comic.setDataset(simpsons); // definindo como serão os 4 atributos
        comic.setValue(0, 8);   // comp. cabelo
        comic.setValue(1, 290); // peso
        comic.setValue(2, 38);  // idade
        comic.setClassMissing(); // conceito indefinido
        System.out.println(comic); // antes de ser classificado

        // IDENTIFICANDO O CONCEITO DO NOVO EXEMPLO
        double classe = vizinho.classifyInstance(comic);
        System.out.println("Conceito: " + classe);
    }
}