public class Mage extends GameCharacter {
    public Mage(String name) { super(name, 90, 28); }

    @Override public String getType() { return "Mage"; }
    @Override public String getAbilityName() { return "Lightning Storm"; }
    @Override public String attack() { return getName() + " casts a fireball for " + getAttackPower() + " damage!"; }
    @Override public String specialMove() { return getName() + " unleashes Lightning Storm for a powerful magical strike!"; }
}
