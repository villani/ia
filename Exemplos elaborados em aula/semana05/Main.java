class Main {
    public static void main(String[] args) {
        String p1 = "curintia";
        String p2 = "palmeiras";
        String p3 = "corinthians";
        int pontos1 = Exercicio03.compararIgual(p1, p2);
        int pontos2 = Exercicio03.compararIgual(p1, p3);
        int pontos3 = Exercicio03.compararPosicao(p1, p2);
        int pontos4 = Exercicio03.compararPosicao(p1, p3);
        int pontos5 = Exercicio03.compararProporcao(p1, p2);
        int pontos6 = Exercicio03.compararProporcao(p1, p3);
        double simi01 = Exercicio03.obterSimilaridade(p1, p2);
        double simi02 = Exercicio03.obterSimilaridade(p1, p3);
        System.out.println("######## COMPARAR IGUAL ##########");
        System.out.println("curintia - palmeiras: " + pontos1);
        System.out.println("curintia - corinthians: " + pontos2);
        System.out.println("######## COMPARAR POSIÇÃO ##########");
        System.out.println("curintia - palmeiras: " + pontos3);
        System.out.println("curintia - corinthians: " + pontos4);
        System.out.println("######## COMPARAR PROPORÇÃO ##########");
        System.out.println("curintia - palmeiras: " + pontos5);
        System.out.println("curintia - corinthians: " + pontos6);
        System.out.println("######## SIMILARIDADES #############");
        System.out.println("curintia - palmeiras: " + simi01);
        System.out.println("curintia - corinthians: " + simi02);
        System.out.println("#### CORRETOR ORTOGRÁFICO ####");
        Exercicio04.main(args);
    }
}