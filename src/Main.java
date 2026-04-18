import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        Personagem jogador = null;
        Personagem inimigo = new Guerreiro("Inimigo", 100, 15, 10); // inimigo padrão

        int opcao;

        do {
            System.out.println("\n===== MENU RPG =====");
            System.out.println("1 - Criar personagem");
            System.out.println("2 - Ver ficha");
            System.out.println("3 - Atacar inimigo");
            System.out.println("0 - Sair");
            System.out.print("Escolha: ");

            opcao = scanner.nextInt();
            scanner.nextLine(); // limpar buffer

            switch (opcao) {

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
                    } else {
                        jogador.atacar(inimigo);

                        if (inimigo.getPontosDeVida() <= 0) {
                            System.out.println("Inimigo derrotado!");
                        } else {
                            System.out.println("Vida do inimigo: " + inimigo.getPontosDeVida());
                        }
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