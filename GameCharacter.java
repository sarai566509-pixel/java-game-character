/**
 * Common blueprint for all game characters.
 * Abstraction: subclasses define their own attack and special ability.
 */
public abstract class GameCharacter {
    private final String name;
    private int health;
    private final int maxHealth;
    private final int attackPower;

    protected GameCharacter(String name, int health, int attackPower) {
        this.name = name;
        this.health = health;
        this.maxHealth = health;
        this.attackPower = attackPower;
    }

    public String getName() { return name; }
    public int getHealth() { return health; }
    public int getMaxHealth() { return maxHealth; }
    public int getAttackPower() { return attackPower; }
    public boolean isAlive() { return health > 0; }

    public abstract String getType();
    public abstract String getAbilityName();
    public abstract String attack();
    public abstract String specialMove();

    public int takeDamage(int damage) {
        int actual = Math.max(0, damage);
        health = Math.max(0, health - actual);
        return actual;
    }

    public int heal(int amount) {
        if (!isAlive()) return 0;
        int before = health;
        health = Math.min(maxHealth, health + Math.max(0, amount));
        return health - before;
    }

    @Override
    public String toString() {
        return getType() + " — " + name + " (HP " + health + "/" + maxHealth + ")";
    }
}
