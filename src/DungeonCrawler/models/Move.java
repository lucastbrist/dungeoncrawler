package DungeonCrawler.models;

public class Move {

    String name;
    String description;
    String theme;
    int type;
    int level;
    int damageModifier;
    boolean ignoreArmor;

    ///

    /// Constructors

    public Move() {
    }

    public Move(String name, String description, String theme, int type, int level, int damageModifier, boolean ignoreArmor) {
        this.name = name;
        this.description = description;
        this.theme = theme;
        this.type = type;
        this.level = level;
        this.damageModifier = damageModifier;
        this.ignoreArmor = ignoreArmor;
    }

    ///

    /// Getters & Setters

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

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getDamageModifier() {
        return damageModifier;
    }

    public void setDamageModifier(int damageModifier) {
        this.damageModifier = damageModifier;
    }

    public boolean doesIgnoreArmor() {
        return ignoreArmor;
    }

    public void setIgnoreArmor(boolean ignoreArmor) {
        this.ignoreArmor = ignoreArmor;
    }

    @Override
    public String toString() {
        if (this.doesIgnoreArmor()) {
            return this.getName() + ": "
                    + this.getDescription()
                    + " It deals "
                    + getDamageModifier()
                    + " extra damage "
                    + "and ignores armor!";

        }
        else {
            return this.getName() + ": "
                    + this.getDescription()
                    + " It deals "
                    + getDamageModifier()
                    + " extra damage!";
        }
    }
}
