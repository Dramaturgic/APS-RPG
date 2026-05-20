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
        IO.println(this.nome + " atacou " + alvo.getNome() + " causando " + dano + " de dano.");
    }

    // Exibir ficha
    @Override
    public void exibirFicha() {
        IO.println("===== FICHA DO INIMIGO =====");
        IO.println("Nome: " + nome);
        IO.println("Vida: " + pontosDeVida);
        IO.println("Ataque: " + ataque);
        IO.println("Defesa: " + defesa);
        IO.println("============================");
    }
}
