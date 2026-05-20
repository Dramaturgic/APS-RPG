public class Goblin extends Inimigo {
    public Goblin(String nome, int pontosDeVida, int ataque, int defesa) {
        super(nome, pontosDeVida, ataque, defesa);
    }

    @Override
    public void atacar(Personagem alvo) {
        // Ataca duas vezes, mas com metade do ataque cada vez
        for (int i = 1; i <= 2; i++) {
            int dano = (this.ataque) - alvo.getDefesa();
            if (dano < 0) dano = 0;
            alvo.setPontosDeVida(alvo.getPontosDeVida() - dano);
            IO.println(this.nome + " deu uma rasteira rápida! (Golpe " + i + ")");
            IO.println("Dano: " + dano);
        }
    }
}
