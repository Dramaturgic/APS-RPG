import java.util.Random;

public class Dragao extends Inimigo {
    private double chanceCritico; // ex: 0.3 = 30%

    public Dragao(String nome, int pontosDeVida, int ataque, int defesa, double chanceCritico) {
        super(nome, pontosDeVida, ataque, defesa);
        this.chanceCritico = chanceCritico;
    }

    @Override
    public void atacar(Personagem alvo) {
        int dano = this.ataque - alvo.getDefesa();
        if (dano < 0) dano = 0;

        // Verifica se o ataque é crítico
        Random random = new Random();
        boolean critico = random.nextDouble() < chanceCritico;
        if (critico) {
            dano *= 2;
            System.out.println(" CRÍTICO! " + this.nome + " soltou fogo em " + alvo.getNome() + "!");
        } else {
            System.out.println(this.nome + " atacou " + alvo.getNome() + " com suas garras!");
        }

        alvo.setPontosDeVida(alvo.getPontosDeVida() - dano);
        System.out.println("Dano: " + dano);
    }
}
