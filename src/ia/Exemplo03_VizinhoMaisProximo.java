package ia;

/**
 * Este exemplo contém uma possível implementação do algoritmo Vizinho Mais
 * Proximo e da distância euclidiana
 * (<a href="https://github.com/villani/ia/blob/master/src/ia/Exemplo03_VizinhoMaisProximo.java" target="_blank">Ver
 * código</a>).
 *
 * @author Prof Me. Leonardo Villani
 */
public class Exemplo03_VizinhoMaisProximo {

    /**
     * Executa a implementação do algoritmo Vizinho Mais Próximo e exibe o
     * rótulo da classe do exemplo mais próximo do exemplo informado.
     *
     * @param args Não utiliza argumentos externos.
     * @throws Exception Apenas propagando as exceções dos métodos utilizados.
     * Maiores detalhes na documentação dos métodos utilizados.
     */
    public static void main(String[] args) throws Exception {

        // Definindo o conjunto de treinamentos
        double[][] simpsons = {
            {0, 250, 36, 1}, // Homer
            {10, 150, 34, 0}, // Marge
            {2, 90, 10, 1}, // Bart
            {6, 78, 8, 0}, // Lisa
            {4, 20, 1, 0}, // Maggie
            {1, 170, 70, 1}, // Abe
            {8, 160, 41, 0}, // Selma
            {10, 180, 38, 1}, // Otto
            {6, 200, 45, 1} // Krusty
        };

        // Definindo o exemplo desconhecido
        double[] comic = {8, 290, 38, -1};

        // Passando como parâmetros a base de conhecimento e o problema para ser solucionado
        double resposta = VizinhoMaisProximo(simpsons, comic);

        // Exibindo a resposta encontrada
        System.out.println("Resposta: " + resposta);
    }

    /**
     * Calcula a distância euclidiana entre dois vetores de características.
     *
     * @param exemplo1 Vetor contendo as características do exemplo 1.
     * @param exemplo2 Vetor contendo as características do exemplo 2.
     * @return A distância euclidiana entre os dois vetores.
     * @throws Exception Se os vetores não tiver a mesma quantidade de
     * características.
     */
    public static double obtertEuclidiana(double[] exemplo1, double[] exemplo2) throws Exception {
        if (exemplo1.length != exemplo2.length) {
            throw new Exception("Os exemplos precisam ter a mesma quantidade de atributos.");
        }
        double distancia = -1;
        for (int i = 0; i < exemplo1.length - 1; i++) {
            distancia += Math.pow((exemplo1[i] - exemplo2[i]), 2);
        }
        return Math.sqrt(distancia);
    }

    /**
     * Encontra o exemplo do conjunto de treinamento que está mais próximo do
     * exemplo desconhecido e retorna o valor do atributo classe do exemplo
     * encontrado
     *
     * @param conjunto Conjunto que contém os exemplos do mesmo contexto do
     * exemplo desconhecido.
     * @param desconhecido Exemplo a ser classificado.
     * @return O valor do atributo classe do exemplo mais próximo encontrado.
     * @throws Exception Se não houver ao menos um exemplo no conjunto ou se os
     * vetores não tiverem a mesma quantidade de características.
     */
    public static double VizinhoMaisProximo(double[][] conjunto, double[] desconhecido) throws Exception {

        // DEFININDO AS RETRIÇÕES
        if (conjunto.length < 1) { // Precisa ter ao menos um exemplo no conjunto.
            throw new Exception("O conjunto precisa ter ao menos um exemplo.");
        } else if (conjunto[0].length != desconhecido.length) { // Os exemplos comparados precisam ter a mesma quantidade de características.
            throw new Exception("Os exemplos comparados precisam ter a mesma quantidade de atributos.");
        }

        // DEFININDO PARÂMETROS INTERNOS DO ALGORÍTMO
        int atributoClasse = conjunto[0].length - 1; // Normalmento o último atributo do vetor é usado para armazenar a classe.
        double menorDistancia = -1; // Apenas inicializando a menor distância
        int exemploProximo = -1; // Apenas inicializando o exemplo mais próximo. -1 representa que o exemplo está indefinido.

        // COMPARANDO TODOS OS EXEMPLOS DE TREINAMENTO COM O EXEMPLO DESCONHECIDO
        for (int exemplo = 0; exemplo < conjunto.length; exemplo++) {

            // Calcula a distância de um exemplo de treinamento em relação ao exemplo desconhecido.
            double distancia = obtertEuclidiana(conjunto[exemplo], desconhecido);

            // Verifica se essa distância é a menor que já foi encontrada.
            if (distancia < menorDistancia || exemploProximo == -1) {
                menorDistancia = distancia;
                exemploProximo = exemplo;
            }
        }

        // RETORNANDO O VALOR DA CLASSE DO EXEMPLO MAIS PRÓXIMO
        //return menorDistancia; // Trocar comentário da linha para ver a menor distância.
        return conjunto[exemploProximo][atributoClasse];
    }

}
