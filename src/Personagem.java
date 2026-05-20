public class Personagem {

    protected String nome;
    protected int pontosDeVida;
    protected int ataque;
    protected int defesa;

    public Personagem(String nome, int pontosDeVida, int ataque, int defesa) {
        this.nome = nome;
        setPontosDeVida(pontosDeVida);
        this.ataque = ataque;
        this.defesa = defesa;
    }

    public String getNome() { return nome; }
    public int getPontosDeVida() { return pontosDeVida; }
    public int getAtaque() { return ataque; }
    public int getDefesa() { return defesa; }

    public void setNome(String nome) { this.nome = nome; }
    public void setPontosDeVida(int pontosDeVida) {
        this.pontosDeVida = pontosDeVida < 0 ? 0 : pontosDeVida;
    }
    public void setAtaque(int ataque) { this.ataque = ataque; }
    public void setDefesa(int defesa) { this.defesa = defesa; }

    public void atacar(Personagem alvo) {
        int dano = this.ataque - alvo.defesa;
        if (dano < 0) dano = 0;
        alvo.setPontosDeVida(alvo.getPontosDeVida() - dano);
        IO.println(this.nome + " atacou " + alvo.nome + " causando " + dano + " de dano.");
    }

    public void exibirFicha() {
        String dado = "?";
        String habilidade = "Nenhuma";

        if (this instanceof Guerreiro) {
            dado = "2d6";
            habilidade = "Furia (x2 dano, 1x por batalha)";
        } else if (this instanceof Mago) {
            dado = "1d20 (ignora 50% def. inimiga)";
            habilidade = "Explosao Arcana (x3, ignora def., CD4)";
        } else if (this instanceof Arqueiro) {
            dado = "1d12 (31% critico x2)";
            habilidade = "Chuva de Flechas (3x ataque, CD3)";
        }

        System.out.println("  ╔══════════════════════════════════════╗");
        System.out.println("  ║         FICHA DO JOGADOR             ║");
        System.out.println("  ╠══════════════════════════════════════╣");
        System.out.println("  ║                                      ║");
        System.out.printf( "  ║  Nome      : %-23s║%n", nome);
        System.out.printf( "  ║  Vida      : %-23s║%n", pontosDeVida);
        System.out.printf( "  ║  Ataque    : %-23s║%n", ataque + " + " + dado);
        System.out.printf( "  ║  Defesa    : %-23s║%n", defesa);
        System.out.println("  ╠══════════════════════════════════════╣");
        System.out.printf( "  ║  Habil.    : %-23s║%n", habilidade);
        System.out.println("  ║                                      ║");
        System.out.println("  ╚══════════════════════════════════════╝");
    }
}