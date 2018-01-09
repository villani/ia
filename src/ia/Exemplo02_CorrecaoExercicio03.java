package ia;

/**
 * Este exemplo contém uma possível implementação de corretor ortográfico
 * proposto no Exercício 03 do Caderno de Exercícios.
 *
 * @author Prof. Me. Leonardo Villani
 */
public class Exemplo02_CorrecaoExercicio03 {

    /**
     * Utiliza a função de distância definida no Exercício 02 do Caderno de
     * Exercícios para identificar a palavra correta para o termo pesquisado.
     *
     * @param args Não utiliza argumentos externos.
     */
    public static void main(String[] args) {

        // Buscando a palavra mais similar para sugerir correção.
        String[] times = {"Palmeiras", "Corinthians", "Santos", "São Paulo"};
        String pesquisa = "parmeira";
        double distancia = 1;
        String retorno = "[sem sugestões]";
        for (String time : times) {
            double temp = getDistancia(pesquisa, time);
            if (temp < distancia) {
                distancia = temp;
                retorno = time;
            }
        }
        System.out.println("Você quis dizer: " + retorno);
    }

    /**
     * Calcula a distância entre duas palavras.
     *
     * @param referencia A palavra de referência.
     * @param consulta A palavra a ser consultada.
     * @return A distância entre as duas palavras, onde 0 representa que as
     * palavras são iguais.
     */
    public static double getDistancia(String referencia, String consulta) {
        return 1 - similaridade(referencia, consulta);
    }

    /**
     * Calcula a similaridade entre duas palavras.
     *
     * @param referencia Palavra de referência.
     * @param consulta Palavra a ser consultada.
     * @return A similaridade entre as duas palavras, onde 1 representa 100%
     * similar.
     */
    public static double similaridade(String referencia, String consulta) {
        double pontoMaximo = contaIgualProporcao(referencia, referencia);
        double pontoConsulta = contaIgualProporcao(referencia, consulta);
        return pontoConsulta / pontoMaximo;
    }

    /**
     * Calcula a quantidade de caracteres iguais e que estão na mesma posição em
     * ambas as palavras.
     *
     * @param referencia Palavra de referência.
     * @param consulta Palavra a ser consultada.
     * @return A quantidade calculada.
     */
    public static double contaIgualPosicao(String referencia, String consulta) {
        double pontos = 0;
        for (int i = 0; i < referencia.length(); i++) {
            if (i >= consulta.length()) {
                break;
            }
            if (referencia.charAt(i) == consulta.charAt(i)) {
                pontos += 3;
            }
        }
        return pontos;
    }

    /**
     * Calcula a quantidade de caracteres que são iguais e estão na mesma
     * proporção em ambas as palavras.
     *
     * @param referencia Palavra de referência.
     * @param consulta Palavra a ser consultada.
     * @return A quantidade calculada.
     */
    public static double contaIgualProporcao(String referencia, String consulta) {
        double pontos = 0;
        for (int i = 0; i < referencia.length(); i++) {
            char caractere = referencia.charAt(i);
            int qtdeRef = contaChar(referencia, caractere);
            int qtdeCon = contaChar(consulta, caractere);
            if (qtdeRef == qtdeCon) {
                pontos += 2;
            }
        }
        return pontos;
    }

    /**
     * Calcula quantos determinado caractere existe em um palavra.
     *
     * @param palavra Palavra a ser verificada.
     * @param caractere Caractere a ser contado.
     * @return A quantidade calculada.
     */
    public static int contaChar(String palavra, char caractere) {
        int qtde = 0;
        if (palavra.contains(String.valueOf(caractere))) {
            for (int i = 0; i < palavra.length(); i++) {
                if (caractere == palavra.charAt(i)) {
                    qtde++;
                }
            }
        }
        return qtde;
    }
}
