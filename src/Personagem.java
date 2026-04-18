public class Personagem {

    // Atributos
    protected String nome;
    protected int pontosDeVida;
    protected int ataque;
    protected int defesa;

    // Construtor
    public Personagem(String nome, int pontosDeVida, int ataque, int defesa) {
        this.nome = nome;
        setPontosDeVida(pontosDeVida); // usa validação
        this.ataque = ataque;
        this.defesa = defesa;
    }

    // Getters
    public String getNome() {
        return nome;
    }

    public int getPontosDeVida() {
        return pontosDeVida;
    }

    public int getAtaque() {
        return ataque;
    }

    public int getDefesa() {
        return defesa;
    }

    // Setters
    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setPontosDeVida(int pontosDeVida) {
        if (pontosDeVida < 0) {
            this.pontosDeVida = 0;
        } else {
            this.pontosDeVida = pontosDeVida;
        }
    }

    public void setAtaque(int ataque) {
        this.ataque = ataque;
    }

    public void setDefesa(int defesa) {
        this.defesa = defesa;
    }

    // Método atacar
    public void atacar(Personagem alvo) {
        int dano = this.ataque - alvo.defesa;

        if (dano < 0) {
            dano = 0;
        }

        alvo.setPontosDeVida(alvo.getPontosDeVida() - dano);

        System.out.println(this.nome + " atacou " + alvo.nome + " causando " + dano + " de dano.");
    }

    // Exibir ficha
    public void exibirFicha() {
        System.out.println("===== FICHA DO PERSONAGEM =====");
        System.out.println("Nome: " + nome);
        System.out.println("Vida: " + pontosDeVida);
        System.out.println("Ataque: " + ataque);
        System.out.println("Defesa: " + defesa);
        System.out.println("================================");
    }
}

