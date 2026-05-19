public class Guerreiro extends Personagem {

    public Guerreiro(String nome, int pontosDeVida, int ataque, int defesa) {
        super(nome, pontosDeVida, ataque, defesa);
    }

    @Override
    public void atacar(Personagem alvo) {
        int dano = this.ataque - alvo.getDefesa();

        // Guerreiro nunca causa menos que 5 de dano
        if (dano < 5) {
            dano = 5;
        }

        alvo.setPontosDeVida(alvo.getPontosDeVida() - dano);

        IO.println(this.nome + " desferiu um golpe de espada em " + alvo.getNome());
        IO.println("Dano causado: " + dano);
    }
}
