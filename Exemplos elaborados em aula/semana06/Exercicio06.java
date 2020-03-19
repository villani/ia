class Exercicio06 {
    /**
     * Calcula a distância euclidiana entre dois vetores de tamanhos 
     * iguais.
     * @param v1 Vetor de características 1.
     * @param v2 Vetor de características 2.
     * @return A distância euclidiana entre os dois vetores.
     */
    public static double obterEuclidiana(double[] v1, double[] v2) {
        double dist = 0;
        for (int i = 0; i < v1.length - 1; i++) {
            dist += Math.pow(v1[i] - v2[i], 2);
        }
        dist = Math.sqrt(dist);
        return dist;
    }
    /**
     * Encontra o vetor da 'base' que mais se aproxima do vetor 'v1'.
     * @param base Base com os exemplos dos conceitos a serem aprendidos.
     * @param v1 Exemplo que deseja-se conhecer a qual conceito ele 
     * pertence.
     * @return O conceito a qual pertence o exemplo que mais se aproxima 
     * de 'v1'.
     */
    public static double vizinhoMaisProximo(double[][] base, double[] v1) {
        double classe = 0;
        // ATRIBUO O PRIMEIRO EXEMPLO COMO MAIS PRÓXIMO
        int vizinho = 0;
        double menor = obterEuclidiana(base[vizinho], v1);

        // VERIFICO SE HÁ OUTRO EXEMPLO DA BASE QUE SEJA MAIS PRÓXIMO
        for (int i = 1; i < base.length; i++) {
            double dist = obterEuclidiana(base[i], v1);
            if (dist < menor) {
                vizinho = i;
                menor = dist;
            }
        }
        int atributoClasse = base[vizinho].length - 1;
        classe = base[vizinho][atributoClasse];

        return classe;        
    }
}