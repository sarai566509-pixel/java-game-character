public class Warrior extends GameCharacter {
    public Warrior(String name) { super(name, 140, 18); }

    @Override public String getType() { return "Warrior"; }
    @Override public String getAbilityName() { return "Shield Bash"; }
    @Override public String attack() { return getName() + " swings a mighty sword for " + getAttackPower() + " damage!"; }
    @Override public String specialMove() { return getName() + " uses Shield Bash and braces for the next hit!"; }
}
