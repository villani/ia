package ia;

import weka.classifiers.trees.Id3;
import weka.classifiers.trees.J48;
import weka.core.DenseInstance;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;

/**
 * Este exemplo contém o uso da implementação do algoritmo Árvore de Decisão
 * (Id3) disponível na biblioteca Weka
 * (<a href="https://github.com/villani/ia/blob/master/src/ia/Exemplo11_ArvoreDecisao.java" target="_blank">Ver
 * código</a>).
 *
 * @author Prof. Me. Leonardo Villani
 */
public class Exemplo11_ArvoreDecisao {

    /**
     * Treina o algoritmo Árvore de Decisão com a base de dados 'Lentes de
     * Contato' e identifica a classe de um paciente desconhecido a partir do
     * conhecimento adquirido.
     *
     * @param args Não utiliza argumentos externos.
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {

        // INSTANCIANDO UM CONJUNTO DE DADOS (contact-lenses)
        DataSource arff = new DataSource("contact-lenses.arff");
        Instances contact = arff.getDataSet();

        // DEFININDO ÍNDICE DO ATRIBUTO CLASSE
        int iClasse = contact.numAttributes() - 1;
        contact.setClassIndex(iClasse);

        // INSTANCIANDO UMA ÁRVORE DE DECISÃO
        Id3 arvore = new Id3(); // pacote extra Weka

        // TREINANDO O CLASSIFICADOR
        arvore.buildClassifier(contact);

        // IMPRIMINDO O MODELO ENCONTRADO (representado na forma de árvore)
        System.out.println(arvore);

        // INSTANCIANDO UM OBJETO Instance COM OS DADOS REMOVIDOS DA BASE
        // - INFORMANDO QUANTOS ATRIBUTOS ESSE EXEMPLO TERÁ
        Instance desconhecido = new DenseInstance(contact.numAttributes());

        // - INFORMANDO COMO SERÃO ESSES ATRIBUTOS
        desconhecido.setDataset(contact);

        // - INFORMANDO OS VALORES DOS ATRIBUTOS:
        //        presbyopic,hypermetrope,yes,normal,none
        desconhecido.setValue(0, "presbyopic");
        desconhecido.setValue(1, "hypermetrope");
        desconhecido.setValue(2, "yes");
        desconhecido.setValue(3, "normal");
        // - Usaremos o classificador para identificar o valor da classe

        // - ENCONTRANDO A CLASSE DO EXEMPLO desconhecido
        double classe = arvore.classifyInstance(desconhecido);
        desconhecido.setClassValue(classe);

        // - EXIBINDO A CLASSE ENCONTRADA
        System.out.println("CLASSE: " + desconhecido.stringValue(iClasse));

        // INSTANCIANDO OUTRA ÁRVORE (Abordagem j48)
        J48 arvore2 = new J48();

        // TREINANDO O NOVO CLASSIFICADOR
        arvore2.buildClassifier(contact);

        // IMPRIMINDO O MODELO ENCONTRADO
        System.out.println("\n\n ÁRVORE J48: ");
        System.out.println(arvore2);

    }

}
