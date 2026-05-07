public class Troll extends Inimigo {
    private int regeneracao;

    public Troll(String nome, int pontosDeVida, int ataque, int defesa, int regeneracao) {
        super(nome, pontosDeVida, ataque, defesa);
        this.regeneracao = regeneracao;
    }

    @Override
    public void atacar(Personagem alvo) {
        int dano = this.ataque - alvo.getDefesa();
        if (dano < 0) dano = 0;
        alvo.setPontosDeVida(alvo.getPontosDeVida() - dano);

        // Regenera vida após cada ataque
        this.setPontosDeVida(this.getPontosDeVida() + regeneracao);
        System.out.println(this.nome + " esmagou " + alvo.getNome() + " com seu porrete!");
        System.out.println("Dano: " + dano + " | Troll regenerou " + regeneracao + " HP");
    }
}
