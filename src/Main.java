import java.util.Random;
import java.util.Scanner;

public class Main {

    // Inimigos

    public static Inimigo gerarInimiroAleatorio() {
        Random random = new Random();
        int tipo = random.nextInt(3);

        switch (tipo) {
            case 0: return new Goblin("Goblin", 60, 14, 5);
            case 1: return new Troll("Troll", 150, 18, 12, 8);
            case 2: return new Dragao("Dragão", 200, 30, 10, 0.3);
            default: return new Goblin("Goblin", 60, 14, 5);
        }
    }

    public static void delay(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    // Menu de batalha
    
    public static void batalha(Scanner scanner, Personagem jogador, Inimigo inimigo) {
        int acao;

        do {
            System.out.println("\n===== BATALHA =====");
            System.out.println("Inimigo: " + inimigo.getNome() + " | Vida: " + inimigo.getPontosDeVida());
            System.out.println("Você: " + jogador.getNome() + " | Vida: " + jogador.getPontosDeVida());
            System.out.println("-------------------");
            System.out.println("1 - Atacar");
            System.out.println("2 - Ver ficha do inimigo");
            System.out.println("3 - Ver ficha do jogador");
            System.out.println("0 - Fugir");
            System.out.print("Escolha: ");

            acao = scanner.nextInt();
            scanner.nextLine();

            switch (acao) {
                case 1:
                    
                    // Jogador ataca
                    
                    System.out.println("\n--- Sua vez ---");
                    jogador.atacar(inimigo);
                    delay(1000);

                    if (inimigo.getPontosDeVida() <= 0) {
                        System.out.println("\nVocê derrotou o " + inimigo.getNome() + "!");
                        delay(1000);
                        return;
                    }

                    // Inimigo contra-ataca
                    
                    System.out.println("\n--- Vez do inimigo ---");
                    inimigo.atacar(jogador);
                    delay(1000);

                    if (jogador.getPontosDeVida() <= 0) {
                        System.out.println("\nVocê foi derrotado pelo " + inimigo.getNome() + "!");
                        delay(1000);
                        return;
                    }
                    break;

                case 2:
                    inimigo.exibirFicha();
                    delay(1000);
                    break;

                case 3:
                    jogador.exibirFicha();
                    delay(1000);
                    break;

                case 0:
                    System.out.println("Você fugiu da batalha!");
                    delay(1000);
                    break;

                default:
                    System.out.println("Ação inválida!");
                    delay(1000);
            }

        } while (acao != 0 && jogador.getPontosDeVida() > 0 && inimigo.getPontosDeVida() > 0);
    }

    // Menu principal
    
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        Personagem jogador = null;
        Inimigo inimigo = null;

        int opcao;

        do {
            System.out.println("\n===== RPG FODA =====");
            System.out.println("1 - Criar personagem");
            System.out.println("2 - Ver ficha do jogador");
            System.out.println("3 - Batalhar");
            System.out.println("0 - Sair");
            System.out.print("Escolha: ");

            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {

                 // Criando personagem com a classe escolhida
                    
                case 1:
                    System.out.print("Digite o nome do personagem: ");
                    String nome = scanner.nextLine();

                    System.out.println("Escolha a classe:");
                    System.out.println("1 - Guerreiro");
                    System.out.println("2 - Mago");
                    System.out.println("3 - Arqueiro");
                    System.out.print("Opção: ");
                    int classe = scanner.nextInt();

                    switch (classe) {
                        case 1:
                            jogador = new Guerreiro(nome, 120, 20, 15);
                            break;
                        case 2:
                            jogador = new Mago(nome, 80, 25, 8);
                            break;
                        case 3:
                            jogador = new Arqueiro(nome, 100, 18, 10);
                            break;
                        case 67:

                        // Classe secreta então não vai aparecer no menu de escolha de classe
                            
                            jogador = new SixsesSeventh(nome, 6767, 67, 67);
                            System.out.println("Alexa, ligar aura");
                            break;
                        default:
                            System.out.println("Classe inválida!");
                            break;
                    }

                    if (jogador != null) {
                        System.out.println("Personagem criado com sucesso!");
                    }
                    break;

                case 2:
                    if (jogador == null) {
                        System.out.println("Crie um personagem primeiro!");
                    } else {
                        jogador.exibirFicha();
                    }
                    break;

                case 3:
                    if (jogador == null) {
                        System.out.println("Crie um personagem primeiro!");
                    } else if (jogador.getPontosDeVida() <= 0) {
                        System.out.println("Seu personagem está morto! Crie um novo personagem.");
                    } else {
                        inimigo = gerarInimiroAleatorio();
                        System.out.println("Um " + inimigo.getNome() + " apareceu!");
                        delay(1000);
                        batalha(scanner, jogador, inimigo);
                    }
                    break;

                case 0:
                    System.out.println("Saindo do jogo...");
                    break;

                default:
                    System.out.println("Opção inválida!");
            }

        } while (opcao != 0);

        scanner.close();
    }
}
