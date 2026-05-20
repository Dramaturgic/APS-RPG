import java.util.Random;

public class Guerreiro extends Personagem {

    private final Random random = new Random();
    private boolean furiaAtiva = false;
    private boolean furiaUsada = false;

    public Guerreiro(String nome, int pontosDeVida, int ataque, int defesa) {
        super(nome, pontosDeVida, ataque, defesa);
    }

    // Rola 2d6 e soma ao ataque base
    private int rolarDado() {
        return random.nextInt(6) + 1 + random.nextInt(6) + 1;
    }

    @Override
    public void atacar(Personagem alvo) {
        int rolagem = rolarDado();
        int dano = (this.ataque + rolagem) - alvo.getDefesa();
        if (dano < 1) dano = 1;
        if (furiaAtiva) dano *= 2;

        alvo.setPontosDeVida(alvo.getPontosDeVida() - dano);
        IO.println(this.nome + " desferiu um golpe de espada em " + alvo.getNome());
        IO.println("🎲 Rolagem 2d6: " + rolagem);
        if (furiaAtiva) {
            IO.println("🔥 Dano causado: " + dano + " (Fúria ativa!)");
        } else {
            IO.println("Dano causado: " + dano);
        }
    }

    public void ativarFuria() {
        furiaAtiva = true;
        furiaUsada = true;
        IO.println("🔥 " + this.nome + " entrou em FÚRIA!");
        IO.println("  Dano dobrado por toda a batalha!");
        IO.println("  ⚠ Você não poderá fugir enquanto a fúria estiver ativa!");
    }

    public boolean isFuriaAtiva() { return furiaAtiva; }
    public boolean isFuriaUsada() { return furiaUsada; }

    public void resetarBatalha() {
        furiaAtiva = false;
        furiaUsada = false;
    }
}