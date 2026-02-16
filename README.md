# The Finding of IZack

[![Java CI with Gradle](https://github.com/woodj/finding-of-i-zack/actions/workflows/ci.yml/badge.svg?branch=master)](https://github.com/woodj/finding-of-i-zack/actions/workflows/ci.yml)
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)

A 2D top-down dungeon crawler game developed in Java using Swing and AWT. Navigate through rooms, defeat enemies, collect items, and defeat the final boss.

## 🚀 Features

- **Fixed Save/Load System:** Robust serialization that preserves game state, player attributes, and room configurations.
- **Dynamic Dungeon Generation:** Randomly generated room contents and enemy placements.
- **Diverse Enemies:** Multiple enemy types with unique movement and attack behaviors.
- **Combat System:** Projectile-based combat with upgrades and item interaction.

## 🛠️ Prerequisites

- **Java JDK:** 17 or newer (Fully compatible with Java 25).
- **Gradle:** 9.3.1 (Included via Gradle Wrapper).

## 📥 Installation

Clone the repository to your local machine:

```bash
git clone https://github.com/woodj/finding-of-i-zack.git
cd finding-of-i-zack
```

## 🎮 How to Run

You can run the game directly using the provided Gradle Wrapper:

```bash
./gradlew run
```

## 🏗️ Building and Testing

### Build the project
Generates an executable JAR in `build/libs/`.
```bash
./gradlew build
```

### Run the executable JAR
After building, you can run the standalone JAR:
```bash
java -jar build/libs/The-Finding-Of-I-Zack.jar
```

### Run Tests
Executes the JUnit 4 test suite.
```bash
./gradlew test
```

### Linting
Check code quality using Checkstyle.
```bash
./gradlew checkstyleMain
```

## ⌨️ Controls

| Action | Key |
| :--- | :--- |
| **Movement** | `W`, `A`, `S`, `D` |
| **Shoot** | Arrow Keys (Up, Left, Down, Right) |
| **Menu / Pause** | `ESC` |
| **Interact** | Collision-based |

## 📁 Repository Structure

- `src/main/java`: Core game logic and engine.
- `src/main/resources`: Game assets (sprites, backgrounds, icons).
- `src/test/java`: Comprehensive JUnit test suite.
- `.github/workflows`: Automated CI/CD pipelines.

## 📄 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.
