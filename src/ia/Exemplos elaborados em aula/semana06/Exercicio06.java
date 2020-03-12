class Exercicio06 {
    public static void main(String[] args) {
        double[][] base = new double[9][4];
        double[] homer = {0, 250, 36, 1};
        base[0] = homer;
        double[] comic = {8, 290, 38, -1};
        double dist = obterEuclidiana(homer, comic);
        System.out.printf("d(homer,comic) = %.2f \n", dist);
    }

    /**
     * Calcular a distância euclidiana entre dois vetores de tamanhos iguais.
     * @param v1 O vetor de característica do primeiro exemplo.
     * @param v2 O vetor de característica do segundo exemplo.
     * @return A distância euclidiana entre os vetores.
     */
    public static double obterEuclidiana(double[] v1, double[] v2) {
        double dist = 0;
        // implementar distância
        for (int i = 0; i < v1.length - 1; i++) {
            dist += Math.pow(v1[i] - v2[i], 2);
        }
        dist = Math.sqrt(dist);
        return dist;
    }

    /**
     * Identifica qual o vetor da base tem a menor distância (distância euclidiana)
     * em relação ao vetor desconhecido.
     * @param base Base com todos os exemplos dos conceitos do contexto.
     * @param desc Exemplo que pertence ao mesmo contexto, porém o conceito é desconhecido.
     * @return O conceito a qual pertence o exemplo desconhecido.
     */
    public static double vizinhoMaisProximo(double[][] base, double[] desc) {
        double classe = 0;
        // implementar algoritmo vizinho mais próximo
        return classe;
    }
}