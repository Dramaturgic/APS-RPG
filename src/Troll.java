import java.util.Random;

public class Troll extends Inimigo {
    private int regeneracao;

    private Random random = new Random();

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
            if (random.nextInt(100) < 51) {
                this.setPontosDeVida(this.getPontosDeVida() + regeneracao);
                IO.println("Troll regenerou " + regeneracao + " HP");
            }
            IO.println(this.nome + " esmagou " + alvo.getNome() + " com seu porrete!");
            IO.println("Dano: " + dano);
    }
}
