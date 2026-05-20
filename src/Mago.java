import java.util.Random;

public class Mago extends Personagem {

    private final Random random = new Random();
    private int cooldownExplosao = 0;

    public Mago(String nome, int pontosDeVida, int ataque, int defesa) {
        super(nome, pontosDeVida, ataque, defesa);
    }

    // Rola 1d20 e soma ao ataque base
    private int rolarDado() {
        return random.nextInt(20) + 1;
    }

    @Override
    public void atacar(Personagem alvo) {
        int rolagem = rolarDado();
        int defesaIgnorada = alvo.getDefesa() / 2;
        int dano = (this.ataque + rolagem) - defesaIgnorada;
        if (dano < 0) dano = 0;

        alvo.setPontosDeVida(alvo.getPontosDeVida() - dano);
        IO.println(this.nome + " lançou uma magia em " + alvo.getNome());
        IO.println("🎲 Rolagem 1d20: " + rolagem);
        IO.println("Dano mágico: " + dano);

        if (cooldownExplosao > 0) cooldownExplosao--;
    }

    public void explosaoArcana(Personagem alvo) {
        int rolagem = rolarDado();
        int dano = (this.ataque + rolagem) * 3;

        alvo.setPontosDeVida(alvo.getPontosDeVida() - dano);
        IO.println("💥 " + this.nome + " conjura uma EXPLOSÃO ARCANA em " + alvo.getNome() + "!");
        IO.println("🎲 Rolagem 1d20: " + rolagem);
        IO.println("Dano arcano: " + dano + " (ignora defesa!)");
        cooldownExplosao = 4;
    }

    public boolean explosaoDisponivel() { return cooldownExplosao == 0; }
    public int getCooldownExplosao()    { return cooldownExplosao; }
}