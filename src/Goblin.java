public class Goblin extends Inimigo {
    public Goblin(String nome, int pontosDeVida, int ataque, int defesa) {
        super(nome, pontosDeVida, ataque, defesa);
    }

    @Override
    public void atacar(Personagem alvo) {
        // Ataca duas vezes, mas com metade do ataque cada vez
        for (int i = 1; i <= 2; i++) {
            int dano = (this.ataque / 2) - alvo.getDefesa();
            if (dano < 0) dano = 0;
            alvo.setPontosDeVida(alvo.getPontosDeVida() - dano);
            System.out.println(this.nome + " deu uma rasteira rápida! (Golpe " + i + ")");
            System.out.println("Dano: " + dano);
        }
    }
}
