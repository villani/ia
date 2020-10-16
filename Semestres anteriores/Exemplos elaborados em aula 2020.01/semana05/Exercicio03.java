class Exercicio03 {

    /**
     * Retorna a similaridade entre 'p1' e 'p2', considerando as
     * características propostas no Exercício 03.
     * @param p1 Palavra do usuário.
     * @param p2 Palavra do dicionário
     * @return Retorna 1.0 se as palavras forem 100% parecidas, ou
     * um valor próximo de 0.0 de acordo com o tamanho da diferença
     * entre elas.
     */
    public static double obterSimilaridade(String p1, String p2) {
        double similaridade = 0;

        double maxima = compararIgual(p1, p1)
                        + compararProporcao(p1, p1)
                        + compararPosicao(p1, p1);

        double pontos = compararIgual(p1, p2)
                        + compararProporcao(p1, p2)
                        + compararPosicao(p1, p2);

        similaridade = pontos / maxima;
                                
        return similaridade;
    }

    /**
     * Soma 1 ponto para cada caractere de 'p1' que existir na 
     * em 'p2'.
     * @param p1 Palavra do usuário.
     * @param p2 Palavra do dicionário.
     * @return O somatório de pontos.
     */
    public static int compararIgual(String p1, String p2) {
        int pontos = 0;
        for (int i = 0; i < p1.length(); i++) {
            char letra = p1.charAt(i);
            if (p2.contains(String.valueOf(letra))) pontos++;
        }
        return pontos;
    }

    /**
     * Soma 2 pontos para cada caractere de 'p1' que existir na 
     * mesma proporção em 'p2'.
     * @param p1 Palavra do usuário.
     * @param p2 Palavra do dicionário.
     * @return O somatório de pontos.
     */
    public static int compararProporcao(String p1, String p2) {
        int pontos = 0;
        for (int i = 0; i < p1.length(); i++) {
            char letra = p1.charAt(i);
            int qtP1 = contarLetras(p1, letra);
            int qtP2 = contarLetras(p2, letra);
            if (qtP1 == qtP2) pontos += 2;
        }
        return pontos;
    }

    /**
     * Conta quantas vezes 'letra' aparece em 'p'.
     * @param p Palavra a ser analisada.
     * @param letra Letra a ser contada.
     * @return A quantidade de vezes.
     */
    public static int contarLetras(String p, char letra) {
        int qtd = 0;
        for (int i = 0; i < p.length(); i++) {
            char l = p.charAt(i);
            if (l == letra) qtd++;
        }
        return qtd;
    }

    /**
     * Soma 3 pontos para cada caractere de 'p1' que existir na 
     * mesma posição de 'p2'.
     * @param p1 Palavra do usuário.
     * @param p2 Palavra do dicionário.
     * @return O somatório de pontos.
     */
    public static int compararPosicao(String p1, String p2) {
        int pontos = 0;
        for (int i = 0; i < p1.length(); i++) {
            if (p2.length() <= i) break;
            if (p1.charAt(i) == p2.charAt(i)) pontos += 3;
        }
        return pontos;
    }
}