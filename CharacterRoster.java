import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * ADT wrapper around a List: the GUI uses add, get, remove, and size
 * rather than managing the underlying storage directly.
 */
public class CharacterRoster {
    private final List<GameCharacter> characters = new ArrayList<>();

    public void add(GameCharacter character) { characters.add(character); }
    public GameCharacter get(int index) { return characters.get(index); }
    public GameCharacter remove(int index) { return characters.remove(index); }
    public int size() { return characters.size(); }
    public boolean isEmpty() { return characters.isEmpty(); }
    public List<GameCharacter> getAll() {
        return Collections.unmodifiableList(characters);
    }
}
