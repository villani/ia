class Exercicio03 {
    public static void main(final String[] args) {
        System.out.println("#### COMPARA IGUAL ####");
        System.out.println("curintia - palmeiras: " + compararIgual("curintia", "palmeiras"));
        System.out.println("curintia - corinthians: " + compararIgual("curintia", "corinthians"));

        System.out.println("#### COMPARA POSIÇÃO ####");
        System.out.println("curintia - palmeiras: " + compararPosicao("curintia", "palmeiras"));
        System.out.println("curintia - corinthians: " + compararPosicao("curintia", "corinthians"));

        System.out.println("#### COMPARA PROPORÇÃO ####");
        System.out.println("curintia - palmeiras: " + compararProporcao("curintia", "palmeiras"));
        System.out.println("curintia - corinthians: " + compararProporcao("curintia", "corinthians"));

        System.out.println("#### SIMILARIDADE ####");
        System.out.printf("curintia - corinthians: %.2f\n", obterSimilaridade("curintia", "palmeiras"));
        System.out.printf("curintia - corinthians: %.2f\n", obterSimilaridade("curintia", "corinthians"));

        System.out.println("#### DISTÂNCIA ####");
        System.out.println("parmera - parmera: " + obterDistancia("parmera", "parmera"));
        System.out.println("parmera - palmeiras: " + obterDistancia("parmera", "palmeiras"));
        System.out.println("parmera - corinthians: " + obterDistancia("parmera", "corinthians"));
    }

    /**
     * Verifica a distância entre duas palavras. Quanto menor a distância entre elas mais próximo do zero é o valor retornado.
     * @param p1 Palavra do usuário.
     * @param p2 Palavra do dicionário.
     * @return Quanto mais parecida as palavras são, mais próximo de zero é o valor retornado.
     */
    public static double obterDistancia(String p1, String p2) {
        double distancia = 0;
        distancia = 1 - obterSimilaridade(p1, p2);
        return distancia;
    }

    /**
     * Retorna 1.0 se as palavras forem iguais ou um valor próximo de zero, conforme suas diferenças.
     * @param p1 Palavra do usuário.
     * @param p2 Palavra do dicionário.
     * @return Grau de similaridade, sendo 1.0 igual a 100% similar.
     */
    public static double obterSimilaridade(String p1, String p2) {
        double similaridade = 0;
        double maximo = compararIgual(p1, p1) + compararPosicao(p1, p1) + compararProporcao(p1, p1);
        double pontos = compararIgual(p1, p2) + compararPosicao(p1, p2) + compararProporcao(p1, p2);
        similaridade = pontos / maximo;
        return similaridade;
    }

    /**
     * Verifica se há caractere igual e na palavra do dicionário.
     * @param p1 Palavra do usuário.
     * @param p2 Palavra do dicionário.
     * @return Soma de 1 ponto para cada caractere que satisfaz o critério.
     */
    public static int compararIgual(final String p1, final String p2) {
        int pontos = 0;
        for (int i = 0; i < p1.length(); i++) {
            final char letra = p1.charAt(i);
            if(p2.contains(String.valueOf(letra))) pontos++;
        }
        return pontos;
    }
    
    /**
     * Verifica se há caractere igual e na mesma proporção.
     * @param p1 Palavra do usuário.
     * @param p2 Palavra do dicionário.
     * @return Soma de 2 pontos para cada caractere que satisfaz o critério.
     */
    public static int compararProporcao(final String p1, final String p2) {
        int pontos = 0;
        for (int i = 0; i < p1.length(); i++) {
            final char letra = p1.charAt(i);
            final int qtP1 = contarLetras(p1, letra);
            final int qtP2 = contarLetras(p2, letra);
            if (qtP1 == qtP2) pontos += 2;
        }
        return pontos;
    }

    /**
     * Conta quantas vezes a 'letra' aparece na 'palavra'.
     * @param palavra Palavra a ser analisada.
     * @param letra Letra a ser contada.
     * @return Quantiade de vezes que 'letra' aparece na 'palavra'.
     */
    public static int contarLetras(final String palavra, final char letra) {
        int qtde = 0;
        for (int i = 0; i < palavra.length(); i++) {
            final char l = palavra.charAt(i);
            if (l == letra) qtde++;
        }
        //implantação
        return qtde;
    }

    /**
     * Verifica se há caractere igual e na mesma posição.
     * @param p1 Palavra do usuário.
     * @param p2 Palavra do dicionário.
     * @return Soma de 3 pontos para cada caractere que satisfaz o critério.
     */
    public static int compararPosicao(final String p1, final String p2) {
        int pontos = 0;
        for (int i = 0; i < p1.length(); i++) {
            if(i >= p2.length()) break;
            if (p1.charAt(i) == p2.charAt(i)) pontos += 3;
        }
        return pontos;
    }
}