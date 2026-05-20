// Precisa ter o JAVA 25 ou superior para executar

import java.util.Random;
import java.util.Scanner;

// ─── Gerar inimigo aleatório ──────────────────────────────────────────────────

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

// ─── Utilitários de UI ────────────────────────────────────────────────────────

public static void clearScreen() {
    for (int i = 0; i < 40; i++) System.out.println();
}

public static void delay(int ms) {
    try {
        Thread.sleep(ms);
    } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
    }
}

public static void linha() {
    IO.println("  ╔══════════════════════════════════════╗");
}

public static void linhaBaixo() {
    IO.println("  ╚══════════════════════════════════════╝");
}

public static void linhaMeio() {
    IO.println("  ╠══════════════════════════════════════╣");
}

public static void titulo(String texto) {
    int largura = 38;
    int espacos = (largura - texto.length()) / 2;
    String pad = " ".repeat(Math.max(0, espacos));
    IO.println("  ║" + pad + texto + pad + (texto.length() % 2 != 0 ? " " : "") + "║");
}

public static void info(String texto) {
    IO.println("  ║  " + texto + " ".repeat(Math.max(0, 36 - texto.length())) + "║");
}

public static void espaco() {
    IO.println("  ║                                      ║");
}

// ─── Helpers de combate ───────────────────────────────────────────────────────

private static void turnoInimigo(Personagem jogador, Inimigo inimigo) {
    clearScreen();
    linha();
    titulo("💀  VEZ DO INIMIGO  💀");
    linhaMeio();
    espaco();
    inimigo.atacar(jogador);
    espaco();
    linhaBaixo();
    delay(1500);
}

private static boolean verificarVitoria(Personagem jogador, Inimigo inimigo) {
    if (inimigo.getPontosDeVida() <= 0) {
        if (jogador instanceof Guerreiro g) g.resetarBatalha();
        clearScreen();
        linha();
        titulo("✨  VITÓRIA!  ✨");
        linhaMeio();
        espaco();
        info("  Você derrotou o " + inimigo.getNome() + "!");
        espaco();
        linhaBaixo();
        delay(2000);
        return true;
    }
    return false;
}

private static boolean verificarDerrota(Personagem jogador, Inimigo inimigo) {
    if (jogador.getPontosDeVida() <= 0) {
        if (jogador instanceof Guerreiro g) g.resetarBatalha();
        clearScreen();
        linha();
        titulo("💀  DERROTA...  💀");
        linhaMeio();
        espaco();
        info("  Você foi derrotado pelo " + inimigo.getNome() + "!");
        espaco();
        linhaBaixo();
        delay(2000);
        return true;
    }
    return false;
}

// ─── Menu de batalha ─────────────────────────────────────────────────────────

public static void batalha(Scanner scanner, Personagem jogador, Inimigo inimigo) {
    int acao = -1;

    do {
        clearScreen();
        linha();
        titulo("⚔  BATALHA  ⚔");
        linhaMeio();
        if (inimigo instanceof Betinha) {
            info("👹 " + inimigo.getNome() + " | ????? BRUTAL");
        } else {
            info("👹 " + inimigo.getNome() + " | ❤ " + inimigo.getPontosDeVida());
        }
        info("🧙 " + jogador.getNome() + " | ❤ " + jogador.getPontosDeVida());
        linhaMeio();
        info("  1 » Atacar");

        if (jogador instanceof Guerreiro g) {
            if (!g.isFuriaUsada()) {
                info("  2 » Entrar em Fúria (x2 dano por toda batalha)");
            } else if (g.isFuriaAtiva()) {
                info("  2 » Fúria ativa 🔥 (x2 dano)");
            } else {
                info("  2 » Fúria (já utilizada)");
            }
        } else if (jogador instanceof Arqueiro arq) {
            if (arq.chuvaDisponivel()) {
                info("  2 » Chuva de Flechas");
            } else {
                info("  2 » Chuva de Flechas (⏳ " + arq.getCooldownChuva() + " turnos)");
            }
        } else if (jogador instanceof Mago mag) {
            if (mag.explosaoDisponivel()) {
                info("  2 » Explosão Arcana");
            } else {
                info("  2 » Explosão Arcana (⏳ " + mag.getCooldownExplosao() + " turnos)");
            }
        }

        info("  3 » Ver ficha do inimigo");
        info("  4 » Ver ficha do jogador");

        if (jogador instanceof Guerreiro g && g.isFuriaAtiva()) {
            info("  0 » Fugir (🔒 bloqueado pela Fúria!)");
        } else {
            info("  0 » Fugir");
        }

        espaco();
        linhaBaixo();
        IO.print("  Escolha: ");

        if (scanner.hasNextInt()) {
            acao = scanner.nextInt();
            scanner.nextLine();
        } else {
            IO.println("  Digite um número válido!");
            scanner.nextLine();
            delay(1000);
            continue;
        }

        switch (acao) {

            case 1 -> {
                clearScreen();
                linha();
                titulo("⚔  SUA VEZ  ⚔");
                linhaMeio();
                espaco();
                jogador.atacar(inimigo);
                if (inimigo instanceof Betinha) ((Betinha) inimigo).receberGolpe();
                espaco();
                linhaBaixo();
                delay(1500);

                if (verificarVitoria(jogador, inimigo)) return;
                turnoInimigo(jogador, inimigo);
                if (verificarDerrota(jogador, inimigo)) return;
            }

            case 2 -> {
                clearScreen();
                linha();

                if (jogador instanceof Guerreiro g) {
                    if (g.isFuriaUsada()) {
                        titulo("⚠  FÚRIA JÁ UTILIZADA  ⚠");
                        linhaMeio();
                        espaco();
                        info("  Só pode ativar no início da batalha!");
                        espaco();
                        linhaBaixo();
                        delay(1500);
                        continue;
                    }
                    titulo("🔥  FÚRIA ATIVADA!  🔥");
                    linhaMeio();
                    espaco();
                    g.ativarFuria();
                    espaco();
                    linhaBaixo();
                    delay(1500);

                } else if (jogador instanceof Arqueiro arq) {
                    if (!arq.chuvaDisponivel()) {
                        titulo("⚠  HABILIDADE EM RECARGA  ⚠");
                        linhaMeio();
                        espaco();
                        info("  Ainda faltam " + arq.getCooldownChuva() + " turno(s)!");
                        espaco();
                        linhaBaixo();
                        delay(1500);
                        continue;
                    }
                    titulo("🏹  CHUVA DE FLECHAS!  🏹");
                    linhaMeio();
                    espaco();
                    arq.chuvaDeFlecha(inimigo);
                    if (inimigo instanceof Betinha) ((Betinha) inimigo).receberGolpe();
                    espaco();
                    linhaBaixo();
                    delay(1500);

                    if (verificarVitoria(jogador, inimigo)) return;
                    turnoInimigo(jogador, inimigo);
                    if (verificarDerrota(jogador, inimigo)) return;

                } else if (jogador instanceof Mago mag) {
                    if (!mag.explosaoDisponivel()) {
                        titulo("⚠  HABILIDADE EM RECARGA  ⚠");
                        linhaMeio();
                        espaco();
                        info("  Ainda faltam " + mag.getCooldownExplosao() + " turno(s)!");
                        espaco();
                        linhaBaixo();
                        delay(1500);
                        continue;
                    }
                    titulo("💥  EXPLOSÃO ARCANA!  💥");
                    linhaMeio();
                    espaco();
                    mag.explosaoArcana(inimigo);
                    if (inimigo instanceof Betinha) ((Betinha) inimigo).receberGolpe();
                    espaco();
                    linhaBaixo();
                    delay(1500);

                    if (verificarVitoria(jogador, inimigo)) return;
                    turnoInimigo(jogador, inimigo);
                    if (verificarDerrota(jogador, inimigo)) return;
                }
            }

            case 3 -> {
                clearScreen();
                inimigo.exibirFicha();
                delay(2000);
            }

            case 4 -> {
                clearScreen();
                jogador.exibirFicha();
                delay(2000);
            }

            case 0 -> {
                if (jogador instanceof Guerreiro g && g.isFuriaAtiva()) {
                    clearScreen();
                    linha();
                    titulo("🔒  FUGA BLOQUEADA!  🔒");
                    linhaMeio();
                    espaco();
                    info("  A fúria te impede de fugir!");
                    espaco();
                    linhaBaixo();
                    delay(1500);
                    acao = -1;
                } else {
                    if (jogador instanceof Guerreiro g) g.resetarBatalha();
                    clearScreen();
                    linha();
                    titulo("🏃  FUGA  🏃");
                    linhaMeio();
                    espaco();
                    info("  Você fugiu da batalha!");
                    espaco();
                    linhaBaixo();
                    delay(1500);
                }
            }

            default -> {
                IO.println("  ⚠ Ação inválida!");
                delay(1000);
            }
        }

    } while (acao != 0 && jogador.getPontosDeVida() > 0 && inimigo.getPontosDeVida() > 0);
}

// ─── Menu principal ───────────────────────────────────────────────────────────

void main() {

    Scanner scanner = new Scanner(System.in);

    Personagem jogador = null;
    Inimigo inimigo;

    int opcao = -1;

    do {
        clearScreen();
        linha();
        titulo("🗡  R  P  G  🗡");
        linhaMeio();
        espaco();
        info("  1 » Criar personagem");
        info("  2 » Ver ficha do jogador");
        info("  3 » Batalhar");
        info("  0 » Sair");
        espaco();
        linhaBaixo();
        IO.print("  Escolha: ");

        if (scanner.hasNextInt()) {
            opcao = scanner.nextInt();
            scanner.nextLine();
        } else {
            IO.println("  Digite um número válido!");
            scanner.nextLine();
            delay(1000);
            continue;
        }

        switch (opcao) {

            case 1:
                clearScreen();
                linha();
                titulo("📜  CRIAR PERSONAGEM  📜");
                linhaMeio();
                espaco();
                IO.print("  Nome do personagem: ");
                String nome = scanner.nextLine();

                espaco();
                info("  Escolha a classe:");
                info("  1 » Guerreiro  (2d6 + ataque)");
                info("  2 » Mago       (1d20 + ataque)");
                info("  3 » Arqueiro   (1d12 + ataque)");
                espaco();
                linhaBaixo();
                IO.print("  Opção: ");
                int classe = scanner.nextInt();
                scanner.nextLine();

                switch (classe) {
                    case 1 -> jogador = new Guerreiro(nome, 120, 18, 15);
                    case 2 -> jogador = new Mago(nome, 80, 25, 8);
                    case 3 -> jogador = new Arqueiro(nome, 100, 20, 10);
                    case 67 -> {
                        jogador = new SixsesSeventh(nome, 6767, 67, 67);
                        IO.println("  Alexa, ligar aura 🔥");
                    }
                    default -> IO.println("  ⚠ Classe inválida!");
                }

                if (jogador != null) {
                    clearScreen();
                    linha();
                    titulo("✅  PERSONAGEM CRIADO!  ✅");
                    linhaMeio();
                    espaco();
                    info("  Bem-vindo(a), " + jogador.getNome() + "!");
                    espaco();
                    linhaBaixo();
                }
                delay(1500);
                break;

            case 2:
                clearScreen();
                if (jogador == null) {
                    linha();
                    titulo("⚠  AVISO  ⚠");
                    linhaMeio();
                    espaco();
                    info("  Crie um personagem primeiro!");
                    espaco();
                    linhaBaixo();
                } else {
                    jogador.exibirFicha();
                }
                delay(2000);
                break;

            case 3:
                clearScreen();
                if (jogador == null) {
                    linha();
                    titulo("⚠  AVISO  ⚠");
                    linhaMeio();
                    espaco();
                    info("  Crie um personagem primeiro!");
                    espaco();
                    linhaBaixo();
                    delay(1500);
                } else if (jogador.getPontosDeVida() <= 0) {
                    linha();
                    titulo("💀  PERSONAGEM MORTO  💀");
                    linhaMeio();
                    espaco();
                    info("  Crie um novo personagem!");
                    espaco();
                    linhaBaixo();
                    delay(1500);
                } else {
                    inimigo = gerarInimigoAleatorio();
                    linha();
                    titulo("⚠  INIMIGO APARECEU!  ⚠");
                    linhaMeio();
                    espaco();
                    info("  Um " + inimigo.getNome() + " está te encarando...");
                    if (inimigo instanceof Betinha) {
                        info("  " + ((Betinha) inimigo).getIntroducao());
                    }
                    espaco();
                    linhaBaixo();
                    delay(2000);
                    batalha(scanner, jogador, inimigo);
                }
                break;

            case 0:
                clearScreen();
                linha();
                titulo("👋  ATÉ LOGO!  👋");
                linhaMeio();
                espaco();
                info("  Saindo do jogo...");
                espaco();
                linhaBaixo();
                delay(1500);
                break;

            default:
                IO.println("  ⚠ Opção inválida!");
                delay(1000);
        }

    } while (opcao != 0);

    scanner.close();
}