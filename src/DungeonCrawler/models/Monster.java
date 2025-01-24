package DungeonCrawler.models;

public class Monster {

    /// Non-player character stats!

    // The foe's foul name
    String name;

    // A dastardly description of this villain!
    String description;

    // The origin of the monster
    String theme;

    // Monster's "class"--1, 2, 3: melee, archer, mage. Determines moves
    int type;

    // The monster's "level"--multiplies stats
    int tier;

    /// Monster "Big Three" stats

    // TOTAL MAXIMUM health score--stored as the maximum current HP; the cap the monster can heal to
    int totalHealth;

    // TOTAL health--how much damage the monster can take
    int health;

    // TOTAL damage--how much damage the monster can deal before modifiers
    int damage;

    // Used to track initiative
    double initiative;

    // TOTAL armor rating--how much damage is removed from incoming attacks against the monster
    int armorRating;

    // boolean to track a monster having healed--a monster can heal only once in a combat
    boolean hasHealed;

    // boolean to handle defense calculations in combat; set to false by default for safety
    boolean defending = false;

    /// Constructors

    public Monster() {
    }

    public Monster(String name) {
        this.name = name;
    }

    public Monster(String name, String description, String theme, int type, int tier, int health, int damage, int armor) {
        this.name = name;
        this.description = description;
        this.theme = theme;
        this.type = type;
        this.tier = tier;
        this.totalHealth = health;
        this.health = health;
        this.damage = damage;
        this.armorRating = armor;
    }

    public Monster(Monster monster) {
    }

    /// Getters and Setters

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getTier() {
        return tier;
    }

    public void setTier(int tier) {
        this.tier = tier;
    }

    public int getTotalHealth() {
        return totalHealth;
    }

    public void setTotalHealth(int totalHealth) {
        this.totalHealth = totalHealth;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public double getInitiative() {
        return initiative;
    }

    public void setInitiative(double initiative) {
        this.initiative = initiative;
    }

    public int getArmor() {
        return armorRating;
    }

    public void setArmor(int armor) {
        this.armorRating = armor;
    }

    public boolean hasHealed() {
        return hasHealed;
    }

    public void setHasHealed(boolean hasHealed) {
        this.hasHealed = hasHealed;
    }

    public boolean isDefending() {
        return defending;
    }

    public void setDefending(boolean defending) {
        this.defending = defending;
    }

    // toString method to print the monster tidily for the player
    @Override
    public String toString() {
        return "There is a " + this.getName()
                + ". " + this.getDescription()
                + " It has " + this.getHealth() + " Health, deals "
                + this.getDamage() + " Damage, and has "
                + this.getArmor() + " Armor.";
    }

    ///

    public void monsterSlain(PlayerCharacter pc) {

        /// Simple method to congratulate the player and then roll loot

        System.out.println("You have slain " + this.getName() + "!");
        System.out.println("You found " + pc.rollLoot() + " pieces of gold in its remains!");
        System.out.println("You now have " + pc.getGold() + " gold.");

    }

    public void clearFlags() {

        /// Only needs to clear defending boolean to reset at the top of rounds
        // "hasHealed" should remain persistent throughout a battle

        this.setDefending(false);

    }

}
