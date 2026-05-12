import java.util.Random;

public class Betinha extends Inimigo {

    private int golpesSofridos = 0;
    private static final int GOLPES_PARA_DERROTAR = 5;
    private static final double CHANCE_ATAQUE = 0.09;
    private static final int DANO_ATAQUE = 1000;
    private final Random random = new Random();

    public Betinha(String nome, int pontosDeVida, int ataque, int defesa) {
        super(nome, pontosDeVida, ataque, defesa);
        super.setPontosDeVida(Integer.MAX_VALUE); // bypassa o override
    }

    public String getIntroducao() {
        Random random = new Random();
        int introducao = random.nextInt(3);

        return switch (introducao) {
            case 0 -> "Betinha fica triste pois descobriu que sua 67º web namorada o traiu.";
            case 1 ->
                    "Betinha te olha com uma expressão vazia enquanto pensa em perdoar sua web namorada pela 7ª vez após trair ele.";
            case 2 -> "Betinha balança levemente sem motivo aparente, parece que ele só ficou tonto mesmo.";
            default -> "Betinha fica triste pois descobriu que sua 67º web namorada o traiu.";
        };
    }

    // Betinha ignora dano numérico — só morre por golpes
    @Override
    public void setPontosDeVida(int valor) {
        // Só permite zerar via receberGolpe()
    }

    // Evita overflow do Integer.MAX_VALUE em comparações externas
    @Override
    public int getPontosDeVida() {
        int real = super.getPontosDeVida();
        return real <= 0 ? 0 : 1; // 1 = viva, 0 = morta
    }

    // Chamado pelo Main a cada ataque do jogador
    public void receberGolpe() {
        golpesSofridos++;
        int golpesRestantes = GOLPES_PARA_DERROTAR - golpesSofridos;

        if (golpesSofridos >= GOLPES_PARA_DERROTAR) {
            super.setPontosDeVida(0);
            System.out.println("Betinha foi moggado, no final não sobrou nada.");
            System.out.println("Brutal.");
        } else {
            System.out.println("Betinha recebeu um golpe! (" + golpesSofridos + "/" + GOLPES_PARA_DERROTAR + " golpes)");
            System.out.println("Ainda precisa de " + golpesRestantes + " golpes para ser derrotado.");
        }
    }

    @Override
    public void atacar(Personagem alvo) {
        if (this.getPontosDeVida() <= 0) return;

        if (random.nextDouble() < CHANCE_ATAQUE) {
            alvo.setPontosDeVida(alvo.getPontosDeVida() - DANO_ATAQUE);
            System.out.println("Betinha te olhou de um jeito estranho...");
            System.out.println("Dano: " + DANO_ATAQUE);
        } else {
            System.out.println("Betinha não fez nada.");
        }
    }

    @Override
    public void exibirFicha() {
        System.out.println("===== FICHA DO INIMIGO =====");
        System.out.println("Nome: Betinha");
        System.out.println("Golpes sofridos: " + golpesSofridos + "/" + GOLPES_PARA_DERROTAR);
        System.out.println("Chance de ataque: 9%");
        System.out.println("Dano: " + DANO_ATAQUE);
        System.out.println("============================");
    }
}
