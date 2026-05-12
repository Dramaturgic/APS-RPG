public class SixsesSeventh extends Personagem {

    public SixsesSeventh(String nome, int pontosDeVida, int ataque, int defesa) {
        super(nome, pontosDeVida, ataque, defesa);
    }

    @Override
    public void atacar(Personagem alvo) {
        // Ignora TODA a defesa do alvo e multiplica o dano por 6.7
        int dano = (int)(this.ataque * 6.7);
        if (dano < 0) {
            dano = 0;
        }
        alvo.setPontosDeVida(alvo.getPontosDeVida() - dano);

        // Limpa a tela
        System.out.print("\033[H\033[2J");
        System.out.flush();

        System.out.println("FARMOU AURA E DEU " + dano + " DE DANO");
        
        if (alvo.getPontosDeVida() <= 0) {
            try { Thread.sleep(2000); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
            System.out.println("FEELS THE AURA!!");
        }   
    }
}
