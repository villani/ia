class Exercicio04 {
    public static void main(String[] args) {
        String[] dicio = {"palmeiras", "santos", "são paulo",
                "corinthians"};
        String termo = "curintia";

        String sugestao = dicio[0];
        double maior = Exercicio03.obterSimilaridade(termo, dicio[0]);

        // Conferido com as demais palavras do dicionário
        for (int i = 1; i < dicio.length; i++) {
            double simi = Exercicio03.obterSimilaridade(termo, dicio[i]);
            if (simi > maior) {
                sugestao = dicio[i];
                maior = simi;
            }
        }

        System.out.println("Você quis dizer: " + sugestao);
    }
}