class Main {
    public static void main(String[] args) {
        double[] homer = {0, 250, 36, 1}; // 1 - M, 0 - F
        //double[][] base = new double[9][4];
        //base[0] = homer;
        double[][] base = {
            {0, 250, 36, 1},        // homer
            {10, 150, 34, 0},       // marge
            {2, 90, 10, 1},         // bart
            {6, 78, 8, 0},          // lisa
            {4, 20, 1, 0},          // maggie
            {1, 170, 70, 1},        // abe
            {8, 160, 41, 0},        // selma
            {10, 180, 38, 1},       // otto
            {6, 200, 45, 1}         // krusty
        };
        double[] comic = {8, 145, 38, -1};
        double dist = Exercicio06.obterEuclidiana(homer, comic);
        System.out.printf("Distância: %.2f \n", dist);

        double classe = Exercicio06.vizinhoMaisProximo(base, comic);
        System.out.println("Classe: " + classe);
    }
}