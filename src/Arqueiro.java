import java.util.Random;

public class Arqueiro extends Personagem {

    private final Random random = new Random();
    private int cooldownChuva = 0;

    public Arqueiro(String nome, int pontosDeVida, int ataque, int defesa) {
        super(nome, pontosDeVida, ataque, defesa);
    }

    // Rola 1d12 e soma ao ataque base
    private int rolarDado() {
        return random.nextInt(12) + 1;
    }

    @Override
    public void atacar(Personagem alvo) {
        int rolagem = rolarDado();
        int dano = (this.ataque + rolagem) - alvo.getDefesa();
        if (dano < 0) dano = 0;

        boolean critico = random.nextInt(100) < 31;
        if (critico) {
            dano *= 2;
            IO.println("💥 ATAQUE CRÍTICO!");
        }

        alvo.setPontosDeVida(alvo.getPontosDeVida() - dano);
        IO.println(this.nome + " disparou uma flecha em " + alvo.getNome());
        IO.println("🎲 Rolagem 1d12: " + rolagem);
        IO.println("Dano causado: " + dano);

        if (cooldownChuva > 0) cooldownChuva--;
    }

    public void chuvaDeFlecha(Personagem alvo) {
        IO.println("🏹 " + this.nome + " dispara uma CHUVA DE FLECHAS em " + alvo.getNome() + "!");
        int totalDano = 0;

        for (int i = 1; i <= 3; i++) {
            int rolagem = rolarDado();
            int dano = (this.ataque + rolagem) - alvo.getDefesa();
            if (dano < 0) dano = 0;
            totalDano += dano;
            IO.println("  Flecha " + i + " → 🎲 " + rolagem + " → " + dano + " de dano");
        }

        alvo.setPontosDeVida(alvo.getPontosDeVida() - totalDano);
        IO.println("Dano total: " + totalDano);
        cooldownChuva = 3;
    }

    public boolean chuvaDisponivel()  { return cooldownChuva == 0; }
    public int getCooldownChuva()     { return cooldownChuva; }
}