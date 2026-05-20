# ⚔️ APS-RPG 67

Um jogo de RPG por turnos feito em Java, desenvolvido como projeto acadêmico para o curso de Ciência da Computação.

---

## 🎮 Sobre o jogo

Você cria um personagem, escolhe sua classe e parte para batalhas contra inimigos aleatórios. Cada classe tem seus próprios atributos e uma habilidade especial que pode mudar o rumo da luta.

---

## 🧙 Classes disponíveis

| Classe | Vida | Ataque | Dado | Habilidade |
|---|---|---|---|---|
| ⚔️ Guerreiro | 120 | 18 | 2d6 | Fúria — dobra o dano por toda a batalha |
| 🔮 Mago | 80 | 10 | 1d20 | Explosão Arcana — dano triplicado, ignora defesa (CD: 4 turnos) |
| 🏹 Arqueiro | 100 | 15 | 1d12 | Chuva de Flechas — 3 ataques seguidos, 31% de crítico (CD: 3 turnos) |

---

## 👹 Inimigos

- **Goblin** — fraco mas rápido
- **Esqueleto** — equilibrado
- **Troll** — tanque com muita vida
- **Dragão** — o mais poderoso, ataca com força devastadora
- **Betinha** — ??? você vai ter que descobrir

---

## 🕹️ Como jogar

**Requisitos:** Java 25 ou superior

```bash
# Clone o repositório
git clone https://github.com/Dramaturgic/APS-RPG.git

# Entre na pasta
cd APS-RPG

# Compile e execute
javac src/*.java -d out
java -cp out Main
```

---

## 🗂️ Estrutura do projeto

```
APS-RPG/
├── src/
│   ├── Personagem.java
│   ├── Guerreiro.java
│   ├── Mago.java
│   ├── Arqueiro.java
│   ├── Inimigo.java
│   ├── Goblin.java
│   ├── Troll.java
│   ├── Dragao.java
│   ├── Esqueleto.java
│   ├── Betinha.java
│   └── Main.java
```

---

## 👨‍💻 Conceitos aplicados

- Herança e polimorfismo
- Sobrescrita de métodos (`@Override`)
- Encapsulamento com getters e setters
- Programação orientada a objetos

---

> Projeto desenvolvido para a disciplina de APS — Ciência da Computação