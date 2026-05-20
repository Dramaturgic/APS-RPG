// Precisa ter o JAVA 25 ou superior para executar

// Inimigos

public static Inimigo gerarInimigoAleatorio() {
    Random random = new Random();
    int tipo = random.nextInt(5);

    return switch (tipo) {
        case 0 -> new Goblin("Goblin", 60, 18, 5);
        case 1 -> new Troll("Troll", 150, 20, 12, 4);
        case 2 -> new Dragao("Dragão", 200, 25, 10, 0.2);
        case 3 -> new Betinha("Betinha", 0, 0, 0);
        case 4 -> new Esqueleto("Esqueleto", 80, 16, 6);
        default -> new Goblin("Goblin", 60, 14, 5);
    };
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
            int acao = -1;

            do {
                IO.println("\n===== BATALHA =====");
                if (inimigo instanceof Betinha) {
                    IO.println("Inimigo: " + inimigo.getNome() + " | Brutal");
                } else {
                    IO.println("Inimigo: " + inimigo.getNome() + " | Vida: " + inimigo.getPontosDeVida());
                }
                IO.println("Você: " + jogador.getNome() + " | Vida: " + jogador.getPontosDeVida());
                IO.println("-------------------");
                IO.println("1 - Atacar");
                IO.println("2 - Ver ficha do inimigo");
                IO.println("3 - Ver ficha do jogador");
                IO.println("0 - Fugir");
                IO.print("Escolha: ");

                if (scanner.hasNextInt()) {
                    acao = scanner.nextInt();
                } else {
                    IO.println("Digite um número");
                    scanner.nextLine();
                    continue;
                }
                switch (acao) {
                    case 1:

                        // Jogador ataca

                        IO.println("\n--- Sua vez ---");
                        jogador.atacar(inimigo);
                        if (inimigo instanceof Betinha) {
                            ((Betinha) inimigo).receberGolpe();
                        }
                        delay(1000);

                        if (inimigo.getPontosDeVida() <= 0) {
                            IO.println("\nVocê derrotou o " + inimigo.getNome() + "!");
                            delay(1000);
                            return;
                        }

                        // Inimigo contra-ataca

                        IO.println("\n--- Vez do inimigo ---");
                        inimigo.atacar(jogador);
                        delay(1000);

                        if (jogador.getPontosDeVida() <= 0) {
                            IO.println("\nVocê foi derrotado pelo " + inimigo.getNome() + "!");
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
                        IO.println("Você fugiu da batalha!");
                        delay(1000);
                        break;

                    default:
                        IO.println("Ação inválida!");
                        delay(1000);
                }

            } while (acao != 0 && jogador.getPontosDeVida() > 0 && inimigo.getPontosDeVida() > 0);
        }

        // Menu principal

        void main() {

            Scanner scanner = new Scanner(System.in);

            Personagem jogador = null;
            Inimigo inimigo;

            int opcao = -1;

            do {
                IO.println("\n======== RPG ========");
                IO.println("1 - Criar personagem");
                IO.println("2 - Ver ficha do jogador");
                IO.println("3 - Batalhar");
                IO.println("0 - Sair");
                IO.print("Escolha: ");

                if (scanner.hasNextInt()) {
                    opcao = scanner.nextInt();
                } else {
                    IO.println("Digite um número");
                    scanner.nextLine();
                    continue;
                }

                switch (opcao) {

                    // Criando personagem com a classe escolhida

                    case 1:
                        IO.print("Digite o nome do personagem: ");
                        String nome = scanner.nextLine();

                        IO.println("Escolha a classe:");
                        IO.println("1 - Guerreiro");
                        IO.println("2 - Mago");
                        IO.println("3 - Arqueiro");
                        IO.print("Opção: ");
                        int classe = scanner.nextInt();

                        switch (classe) {
                            case 1:
                                jogador = new Guerreiro(nome, 120, 18, 15);
                                break;
                            case 2:
                                jogador = new Mago(nome, 80, 25, 8);
                                break;
                            case 3:
                                jogador = new Arqueiro(nome, 100, 20, 10);
                                break;
                            case 67:

                                // Classe secreta então não vai aparecer no menu de escolha de classe

                                jogador = new SixsesSeventh(nome, 6767, 67, 67);
                                IO.println("Alexa, ligar aura");
                                break;
                            default:
                                IO.println("Classe inválida!");
                                break;
                        }

                        if (jogador != null) {
                            IO.println("Personagem criado com sucesso!");
                        }
                        break;

                    case 2:
                        if (jogador == null) {
                            IO.println("Crie um personagem primeiro!");
                        } else {
                            jogador.exibirFicha();
                        }
                        break;

                    case 3:
                        if (jogador == null) {
                            IO.println("Crie um personagem primeiro!");
                        } else if (jogador.getPontosDeVida() <= 0) {
                            IO.println("Seu personagem está morto! Crie um novo personagem.");
                        } else {
                            inimigo = gerarInimigoAleatorio();
                            IO.println("Um " + inimigo.getNome() + " apareceu!");
                            if (inimigo instanceof Betinha) {
                                IO.println(((Betinha) inimigo).getIntroducao());
                            }
                            delay(1000);
                            batalha(scanner, jogador, inimigo);
                        }
                        break;

                    case 0:
                        IO.println("Saindo do jogo...");
                        break;

                    default:
                        IO.println("Opção inválida!");
                }

            } while (opcao != 0);

            scanner.close();
        }
