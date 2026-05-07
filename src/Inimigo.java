public class Inimigo extends Personagem {

    // Construtor
    public Inimigo(String nome, int pontosDeVida, int ataque, int defesa) {
        super(nome, pontosDeVida, ataque, defesa);
    }

    // Método atacar base (pode ser sobrescrito)
    @Override
    public void atacar(Personagem alvo) {
        int dano = this.ataque - alvo.getDefesa();
        if (dano < 0) dano = 0;
        alvo.setPontosDeVida(alvo.getPontosDeVida() - dano);
        System.out.println(this.nome + " atacou " + alvo.getNome() + " causando " + dano + " de dano.");
    }

    // Exibir ficha
    @Override
    public void exibirFicha() {
        System.out.println("===== FICHA DO INIMIGO =====");
        System.out.println("Nome: " + nome);
        System.out.println("Vida: " + pontosDeVida);
        System.out.println("Ataque: " + ataque);
        System.out.println("Defesa: " + defesa);
        System.out.println("============================");
    }
}
