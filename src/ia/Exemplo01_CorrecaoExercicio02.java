package ia;

/**
 * Este exemplo contém uma possível implementação para a função de similaridade
 * proposta no Exercício 02 do Caderno de Exercícios
 * (<a href="https://github.com/villani/ia/blob/master/src/ia/Exemplo01_CorrecaoExercicio02.java" target="_blank">Ver
 * código</a>).
 *
 * @author Prof. Me. Leonardo Villani
 */
public class Exemplo01_CorrecaoExercicio02 {

    /**
     * Executa o método 'similaridade' e exibe a similaridade entre duas
     * palavras.
     *
     * @param args Não utiliza argumentos externos.
     */
    public static void main(String[] args) {
        String referencia = "Carlos";
        String consulta = "Carlo";
        double similaridade = similaridade(referencia, consulta);
        System.out.print("A similaridade entre " + referencia + " e " + consulta + " é: ");
        System.out.println(similaridade);
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
