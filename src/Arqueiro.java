import java.util.Random;

public class Arqueiro extends Personagem {

    private Random random = new Random();

    public Arqueiro(String nome, int pontosDeVida, int ataque, int defesa) {
        super(nome, pontosDeVida, ataque, defesa);
    }

    @Override
    public void atacar(Personagem alvo) {
        int dano = this.ataque - alvo.getDefesa();

        if (dano < 0) {
            dano = 0;
        }

        // 30% de chance de crítico
        if (random.nextInt(100) < 30) {
            dano *= 2;
            System.out.println("ATAQUE CRÍTICO!");
        }

        alvo.setPontosDeVida(alvo.getPontosDeVida() - dano);

        System.out.println(this.nome + " disparou uma flecha em " + alvo.getNome());
        System.out.println("Dano causado: " + dano);
    }
}