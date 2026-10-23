# Fantasy Character Arena (Java Swing)

An improved version of the original Java Game Character project. It keeps the `GameCharacter`, `Warrior`, and `Mage` idea and expands it into a desktop GUI application.

## Features

- **Abstraction:** `GameCharacter` is an abstract class with abstract methods for character type, attack, and special move. `Warrior` and `Mage` implement different behaviors.
- **ADT:** `CharacterRoster` provides a list-based Abstract Data Type with `add`, `get`, `remove`, `size`, and `isEmpty` operations. The GUI uses the roster instead of directly managing its underlying list.
- **GUI:** Java Swing interface with a character roster, status display, battle log, and themed dark colors.
- **User input and validation:** Create a Warrior or Mage by entering a name. The program checks name length/characters and prevents duplicate names.
- **Actions:** Attack, use a special move, restore up to 20 HP, and remove a selected character.
- **Two character types:** Warrior has more health and a sword attack; Mage has lower health but stronger magic attack.

## Requirements

- Java Development Kit (JDK) 8 or newer
- No external libraries required

## How to run

1. Download or clone this repository.
2. Open a terminal/command prompt in the project folder.
3. Compile all Java source files:

   ```bash
   javac *.java
   ```

4. Run the application:

   ```bash
   java Main
   ```

On Windows, you can also open the folder in an IDE such as IntelliJ IDEA, Eclipse, or NetBeans and run `Main.java`.

## How to demonstrate the requirements

1. Launch the program and point out the dark themed Swing GUI.
2. Add a Warrior and a Mage with different names. Try an invalid or duplicate name to show validation.
3. Select each character and use **Attack** and **Special Move** to show polymorphic behavior.
4. Restore HP and remove a selected character to demonstrate roster management.
5. Explain that `GameCharacter` hides common character details behind an abstract API, while `CharacterRoster` wraps a Java `List` behind simple ADT operations.

## Project files

- `GameCharacter.java` — abstract parent class and shared health/stat behavior
- `Warrior.java` — Warrior-specific attacks and ability
- `Mage.java` — Mage-specific attacks and ability
- `CharacterRoster.java` — list-based roster ADT
- `Main.java` — Swing GUI and input validation

## Notes

This is a learning/demo application. Actions are recorded in the battle log; there is no opponent AI or full combat system yet.
