# 🕯️ The Demon King's Castle

> ⚠️ *"The Demon King's Castle" is a temporary working title and may change in future releases.*

**The Demon King's Castle** is a turn-based dark fantasy role-playing game developed in Java with a graphical user interface.
The player guides a lone character through the depths of a cursed castle, facing brutal encounters where every choice has a cost.
Game progress is saved to file, so a run can be resumed at any time.

> Mainly inspired by *Fear & Hunger* and *Vermis*, with influences from *Dark Souls*, *Berserk*, the *Persona* series and *Pilgrims of the Dusk Dome*.

---

## 🚀 How to run the project

### Prerequisites

- Java 25 (LTS)
- Gradle (provided through the Gradle Wrapper, no installation required)

### Instructions

```shell
git clone https://github.com/AdrianoGesuelli/rpg129795.git
cd rpg129795
```

### Build

```shell
./gradlew build
```

### Run

```shell
./gradlew run
```

> On Windows, use `gradlew.bat build` and `gradlew.bat run`.

---

## 🤖 Use of AI tools

AI tools were used **as a support**, never as a replacement for personal work. All the code in this repository has been written, understood and tested by me, and all design choices (class responsibilities, interfaces, package structure) are my own.

- **Claude was used for:**
  - balancing the game mechanics
  - balancing the game statistics
  - calculating probabilities and working through complex formulas
  - simulating specific scenarios to test possible caps (maximum values) and implementations
  - choosing the colours and style that best fit the game's aesthetic, based on input I provided

📌 A detailed description of how AI was used, with examples and the purpose of each use, is available in the [repository Wiki](https://github.com/AdrianoGesuelli/rpg129795/wiki).

---

## 📚 Documentation

Implemented features, responsibilities, classes and interfaces, data organisation, persistence and extension mechanisms are documented in the [Wiki](https://github.com/AdrianoGesuelli/rpg129795/wiki).
