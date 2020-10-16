class Exercicio04 {

    public static void main(String[] args) {
        // BASE DE CONHECIMENTO DO PROGRAMA
        String[] dicio = {"Palmeiras", "Santos", 
            "Corinthians", "São Paulo"};

        // ENTRADA DO USUÁRIO
        String termo = (args.length > 0)?args[0]:"sem argumento";

        // SUGESTÃO INICIAL: primeira palavra
        String sugestao = dicio[0];
        double menor = Exercicio03.obterDistancia(termo, dicio[0]);

        // VERIFICA SE HÁ OUTRA PALAVRA COM MENOR DISTÂNCIA
        for (int i = 0; i < dicio.length; i++) {
            double dist = Exercicio03.obterDistancia(termo, dicio[i]);
            if (dist < menor) {
                sugestao = dicio[i];
                menor = dist;
            }
        }
        // RETORNA A PALAVRA COM MENOR DISTÂNCIA
        System.out.println("Você quis dizer: " + sugestao);

    }
}