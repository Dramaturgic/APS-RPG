public class Mago extends Personagem {

    public Mago(String nome, int pontosDeVida, int ataque, int defesa) {
        super(nome, pontosDeVida, ataque, defesa);
    }

    @Override
    public void atacar(Personagem alvo) {
        // ignora metade da defesa do alvo
        int defesaIgnorada = alvo.getDefesa() / 2;
        int dano = this.ataque - defesaIgnorada;

        if (dano < 0) {
            dano = 0;
        }

        alvo.setPontosDeVida(alvo.getPontosDeVida() - dano);

        System.out.println(this.nome + " lançou uma magia em " + alvo.getNome());
        System.out.println("Dano mágico: " + dano);
    }
}
