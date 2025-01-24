package DungeonCrawler.models;

import DungeonCrawler.controllers.DungeonCrawlController;

import java.sql.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Objects;
import java.util.Scanner;

public class PlayerCharacter {

    /// Player stats!

    // Character name
    String name;

    // The player playing this character
    Player player;

    // Character's species--used to augment base stats
    String species;

    // Character class--used to augment base stats
    String characterClass;

    // Rarely-used variable, just matches "Warrior," "Thief," and "Sorcerer" to "1," "2," and "3," respectively
    int classType;

    // Character level--part of many random calculations to generate monsters, rooms, loot,
    //and tallied for final player score
    int level = 1;

    // BASE strength score--unchanging and used to calculate base stats--part of "The Big Three"
    //Represents physical prowess
    static int baseStrength = 30;

    // TOTAL strength score--present stat
    int strength;

    // ALLOTTED strength score--keeps track of how many points the player has added to their strength;
    //used to calculate present stats
    int leveledStrength;

    // BASE smarts score--unchanging and used to calculate base stats--part of "The Big Three"
    //Represents intelligence and wisdom
    static int baseSmarts = 30;

    // TOTAL smarts score--present stat
    int smarts;

    // ALLOTTED smarts score--keeps track of how many points the player has added to their smarts;
    //used to calculate present stats
    int leveledSmarts;

    // BASE stealth score--unchanging and used to calculate base stats--part of "The Big Three"
    //Represents nimbleness and ability to remain hidden
    static int baseStealth = 30;

    // TOTAL stealth score--present stat
    int stealth;

    // ALLOTTED stealth score--keeps track of how many points the player has added to their stealth;
    //used to calculate present stats
    int leveledStealth;

    // BASE health score--unchanging and used to calculate base stats.
    //This is how much damage the player character can take before modifiers
    static int baseHealth = 100;

    // TOTAL MAXIMUM health score--stored as the maximum current HP; the cap you can heal to
    int totalHealth;

    // TOTAL CURRENT health score--present stat; how much health the player has right now
    int health;

    // BASE damage score--unchanging and used to calculate base stats.
    //This is how much damage the player character can deal with weapons before modifiers
    static int baseDamage = 10;

    // TOTAL damage score--present stat
    int damage;

    // BASE spell damage score--unchanging and used to calculate base stats.
    //This is how much damage the player character can deal with spells before modifiers
    static int baseSpellDamage = 10;

    // TOTAL spell damage score--present stat
    int spellDamage;

    // Used to track initiative
    double initiative;

    // Equipped weapon--item used to modify damage stat
    Weapon weapon;

    // Equipped armor--item used to modify various stats
    Armor armor;

    // Equipped trinket--item used to modify various stats
    Trinket trinket;

    // TOTAL armor rating--present stat, derived from equipped armor
    //This is how much damage is removed from incoming attacks against the player character.
    int armorRating;

    // Character's net acquired gold--factored into final playthrough score
    int gold;

    // Character's stock of potions, which can be used in battle to restore health
    int potions;

    // ArrayList storing the moves the player has access to in battle
    ArrayList<Move> moves;

    // Boolean to track a Shambles-exclusive species ability--used to revive from death once and only once
    boolean deathTokenUsed = false;

    // booleans to be passed into and used in battle methods for damage calculations
    // set to false by default just in case
    boolean defendMelee = false;
    boolean defendRanged = false;
    boolean defendMagic = false;
    boolean reckless = false;

    // Tracks how many total rooms the player character has cleared in every dungeon; used to calculate score
    int roomsCleared;

    /// Constructors

    // Define starting spells
    // Identical mechanical effects; purely aesthetic differences for player choice
    // Stored here to be referenced in the populateMoves() method

//    Move startingSpell01 = new Move("Flamestrike",
//            "A simple incantation, it strikes a foe with a modest jet of magical fire.",
//            "Player",
//            3,
//            1,
//            3,
//            false);
//
//    Move startingSpell02 = new Move("Thunderbolt",
//            "With a snap, it strikes a foe with a modest bolt of magical lightning.",
//            "Player",
//            3,
//            1,
//            3,
//            false);
//
//    Move startingSpell03 = new Move("Icicle",
//            "Quick and cold, it strikes a foe with a modest spike of magical ice.",
//            "Player",
//            3,
//            1,
//            3,
//            false);

    public PlayerCharacter() {
    }

    public PlayerCharacter(String name) {
        this.name = name;
    }

    public PlayerCharacter(String name,
                           Player player,
                           String species,
                           String characterClass,
                           int classType,
                           int level,
                           int strength,
                           int leveledStrength,
                           int smarts,
                           int leveledSmarts,
                           int stealth,
                           int leveledStealth,
                           int health,
                           int damage,
                           int spellDamage,
                           double initiative,
                           Weapon weapon,
                           Armor armor,
                           Trinket trinket,
                           int armorRating,
                           int gold,
                           int potions,
                           ArrayList<Move> moves,
                           boolean deathTokenUsed) {
        this.name = name;
        this.player = player;
        this.species = species;
        this.characterClass = characterClass;
        this.classType = classType;
        this.level = level;
        this.strength = strength;
        this.leveledStrength = leveledStrength;
        this.smarts = smarts;
        this.leveledSmarts = leveledSmarts;
        this.stealth = stealth;
        this.leveledStealth = leveledStealth;
        this.health = health;
        this.damage = damage;
        this.spellDamage = spellDamage;
        this.initiative = initiative;
        this.weapon = weapon;
        this.armor = armor;
        this.trinket = trinket;
        this.armorRating = armorRating;
        this.gold = gold;
        this.potions = potions;
        this.moves = moves;
        this.deathTokenUsed = deathTokenUsed;

    }

    /// Getters and Setters

    public String getName() {
        return name;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public String getCharacterClass() {
        return characterClass;
    }

    public void setCharacterClass(String characterClass) {
        this.characterClass = characterClass;
    }

    public int getClassType() {
        return classType;
    }

    public void setClassType(int classType) {
        this.classType = classType;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public static int getBaseStrength() {
        return baseStrength;
    }

    public static void setBaseStrength(int baseStrength) {
        PlayerCharacter.baseStrength = baseStrength;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public int getLeveledStrength() {
        return leveledStrength;
    }

    public void setLeveledStrength(int leveledStrength) {
        this.leveledStrength = leveledStrength;
    }

    public static int getBaseSmarts() {
        return baseSmarts;
    }

    public static void setBaseSmarts(int baseSmarts) {
        PlayerCharacter.baseSmarts = baseSmarts;
    }

    public int getSmarts() {
        return smarts;
    }

    public void setSmarts(int smarts) {
        this.smarts = smarts;
    }

    public int getLeveledSmarts() {
        return leveledSmarts;
    }

    public void setLeveledSmarts(int leveledSmarts) {
        this.leveledSmarts = leveledSmarts;
    }

    public static int getBaseStealth() {
        return baseStealth;
    }

    public static void setBaseStealth(int baseStealth) {
        PlayerCharacter.baseStealth = baseStealth;
    }

    public int getStealth() {
        return stealth;
    }

    public void setStealth(int stealth) {
        this.stealth = stealth;
    }

    public int getLeveledStealth() {
        return leveledStealth;
    }

    public void setLeveledStealth(int leveledStealth) {
        this.leveledStealth = leveledStealth;
    }

    public static int getBaseHealth() {
        return baseHealth;
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

    public static int getBaseDamage() {
        return baseDamage;
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

    public Weapon getWeapon() {
        return weapon;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    public Armor getArmor() {
        return armor;
    }

    public void setArmor(Armor armor) {
        this.armor = armor;
    }

    public Trinket getTrinket() {
        return trinket;
    }

    public void setTrinket(Trinket trinket) {
        this.trinket = trinket;
    }

    public static int getBaseSpellDamage() {
        return baseSpellDamage;
    }

    public int getSpellDamage() {
        return spellDamage;
    }

    public void setSpellDamage(int spellDamage) {
        this.spellDamage = spellDamage;
    }

    public int getArmorRating() {
        return armorRating;
    }

    public void setArmorRating(int armorRating) {
        this.armorRating = armorRating;
    }

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public int getPotions() {
        return potions;
    }

    public void setPotions(int potions) {
        this.potions = potions;
    }

    public ArrayList<Move> getMoves() {
        return moves;
    }

    public void setMoves(ArrayList<Move> moves) {
        this.moves = moves;
    }

    public boolean isDeathTokenUsed() {
        return deathTokenUsed;
    }

    public void setDeathTokenUsed(boolean deathTokenUsed) {
        this.deathTokenUsed = deathTokenUsed;
    }

    public boolean isDefendMelee() {
        return defendMelee;
    }

    public void setDefendMelee(boolean defendMelee) {
        this.defendMelee = defendMelee;
    }

    public boolean isDefendRanged() {
        return defendRanged;
    }

    public void setDefendRanged(boolean defendRanged) {
        this.defendRanged = defendRanged;
    }

    public boolean isDefendMagic() {
        return defendMagic;
    }

    public void setDefendMagic(boolean defendMagic) {
        this.defendMagic = defendMagic;
    }

    public boolean isReckless() {
        return reckless;
    }

    public void setReckless(boolean reckless) {
        this.reckless = reckless;
    }

    public int getRoomsCleared() {
        return roomsCleared;
    }

    public void setRoomsCleared(int roomsCleared) {
        this.roomsCleared = roomsCleared;
    }

    ///

    public void levelUp() {

        /// Level Up method. Called after the completion of every room.
        //Presents the player with points to allot to "the Big Three" stats, increasing their character's power

        // Max level is 10. If the player is going to exceed that this levelup, the method ends.
        if (this.getLevel() == 10) {
            return;
        }

        // levelups aren't free heals, but let's also keep you at a similar percentage of health if you up your HP in here
        // Start by grabbing current and current total
        int currentHealth = this.getHealth();
        int currentHealthMax = this.getTotalHealth();

        // Recalculate stats real quick just to make sure everything's copacetic
        this.recalculateAttributes();

        // playerCharacter.level++
        this.setLevel((this.getLevel() + 1));

        // Calculate how many points to allow the player to allot
        int pointsToAllot = ((this.getLevel() + (this.getSmarts()) / 2));

        // Variable to assign inputs to and check against
        int answer = 0;

        // Boolean to control middle loop
        boolean allotting = true;

        // Instantiate a scanner to take player's allotment choices
        Scanner scanner = new Scanner(System.in);

        // Tell the player what's going on
        System.out.println("You've leveled up!");
        System.out.println("You are now Level " + this.getLevel());

        /// Begin loops to allot points
        while (pointsToAllot > 0) {

            allotting = true;
            while (allotting) {

                /// Strength
                // Another recalc for safety
                this.recalculateAttributes();
                System.out.println(this.stats());
                System.out.println("You have " + pointsToAllot + " points to allot to your stats!");
                System.out.println("How many points would you like to allot to your Strength?");

                try {
                    answer = scanner.nextInt();

                    if (answer > pointsToAllot) {
                        System.out.println("You don't have that many points! Please try again.");
                    } else if (answer < 0) {
                        System.out.println("You have typed a negative number! Please try again.");
                    } else
                        allotting = false;
                } catch (InputMismatchException exception) {
                scanner.nextLine();
                System.out.println("You have entered an invalid choice. Please try again.");
                continue;
                }

            }

            this.setLeveledStrength((this.getLeveledStrength() + answer));
            pointsToAllot -= answer;

            if (pointsToAllot > 0) {
                allotting = true;
            } else {
                break;
            }
            while (allotting) {

                /// Smarts
                // Another recalc for safety
                this.recalculateAttributes();
                System.out.println(this.stats());
                System.out.println("You have " + pointsToAllot + " points to allot to your stats!");
                System.out.println("How many points would you like to allot to your Smarts?");

                try {
                    answer = scanner.nextInt();

                    if (answer > pointsToAllot) {
                        System.out.println("You don't have that many points! Please try again.");
                    } else if (answer < 0) {
                        System.out.println("You have typed a negative number! Please try again.");
                    } else
                        allotting = false;
                } catch (InputMismatchException exception) {
                    scanner.nextLine();
                    System.out.println("You have entered an invalid choice. Please try again.");
                    continue;
                }

            }

            this.setLeveledSmarts((this.getLeveledSmarts() + answer));
            pointsToAllot -= answer;

            if (pointsToAllot > 0) {
                allotting = true;
            } else {
                break;
            }
            while (allotting) {

                /// Stealth
                // Another recalc for safety
                this.recalculateAttributes();
                System.out.println(this.stats());
                System.out.println("You have " + pointsToAllot + " points to allot to your stats!");
                System.out.println("How many points would you like to allot to your Stealth?");

                try {
                    answer = scanner.nextInt();

                    if (answer > pointsToAllot) {
                        System.out.println("You don't have that many points! Please try again.");
                    } else if (answer < 0) {
                        System.out.println("You have typed a negative number! Please try again.");
                    } else
                        allotting = false;
                } catch (InputMismatchException exception) {
                    scanner.nextLine();
                    System.out.println("You have entered an invalid choice. Please try again.");
                    continue;
                }
            }

            this.setLeveledStealth((this.getLeveledStealth() + answer));
            pointsToAllot -= answer;

        }

        // Make sure it's all calculated
        this.recalculateAttributes();

        // make the hp make sense
        this.setHealth(this.levelHealth(currentHealth, currentHealthMax, this.getTotalHealth()));

        // Exit line
        System.out.println("Congratulations! You have finished leveling up.");

        // Print the player character's stats
        System.out.println(this.stats());

        // Max level is 10. If the player has reached that, congratulate them.
        if (this.getLevel() == 10) {
            System.out.println("You have reached max level!");
            System.out.println("While your power is not ultimate, you have summited the mountain of mastery and found yourself on a plateau.");
            System.out.println("May your enemies tremble in fear before you, and may your coffers flow.");
        }

        // create if statements checking for milestones and then explaining new moves

        /// END

    }

    public int levelHealth(int currentHealth, int currentHealthMax, int newHealthMax) {

        /// Method to take in a player character's old health and health total at levelup
        // and keep it at the same percentage of the new health total that it was of the old.

        // Returns only an int,
        // so the calling method must use this.setHealth()/pc.setHealth() and pass this method into the constructor

        int newCurrentHealth;

        double percentage = (double)currentHealth / (double)currentHealthMax;

        newCurrentHealth = (int)(newHealthMax * percentage);

        return newCurrentHealth;

    }

    ///

    public String stats() {

        /// Simple method to print out player character stats in a functional and readable format

        // Make sure everything is calculated
        this.recalculateAttributes();

        return "You are " + this.getName() + ", a Level " + this.getLevel() + " " + this.getSpecies() + " "
                + this.getCharacterClass()
                + ". You currently have "
                + this.getStrength()
                + " Strength, "
                + this.getSmarts()
                + " Smarts, and "
                + this.getStealth()
                + " Stealth. Your Health is " + this.getHealth()
                + " out of " + this.getTotalHealth()
                + ", your weapon damage is " + this.getDamage()
                + ", your spell damage is " + this.getSpellDamage()
                + ", your critical hit chance is " + this.getCritChance()
                + "%, and your armor rating is " + this.getArmorRating()
                + ". Your weapon is a " + this.getWeapon().getName()
                + " and your armor is " + this.getArmor().getName() + "."
                + " Your trinket is " + this.getTrinket().getName()
                + ", and you have " + this.getGold() + " gold.";

    }

    ///

    public void recalculateAttributes() {

        /// Method to calculate correct values for all character stats and apply those values

        // Big Three

        // Species first
        switch (this.getSpecies()) {
            case "Human":
                this.setStrength(getBaseStrength() + 1);
                this.setSmarts(getBaseSmarts() + 1);
                this.setStealth(getBaseStealth() + 1);
                break;
            case "Elf":
                this.setStrength(getBaseStrength() - 1);
                this.setSmarts(getBaseSmarts() + 2);
                this.setStealth(getBaseStealth() + 2);
                break;
            case "Dwarf":
                this.setStrength(getBaseStrength() + 3);
                this.setSmarts(getBaseSmarts() - 2);
                this.setStealth(getBaseStealth());
                break;
            case "Halfling":
                this.setStrength(getBaseStrength() - 2);
                this.setSmarts(getBaseSmarts());
                this.setStealth(getBaseStealth() + 3);
                break;
            case "Gnome":
                this.setStrength(getBaseStrength() - 3);
                this.setSmarts(getBaseSmarts() + 3);
                this.setStealth(getBaseStealth() + 1);
                break;
            case "Shamble":
                this.setStrength(getBaseStrength() - 1);
                this.setSmarts(getBaseSmarts() - 1);
                this.setStealth(getBaseStealth() - 1);
                break;
        }

        // Adding item modifiers and allotted points
        this.setStrength((this.getStrength()
                + this.getWeapon().getStrengthModifier()
                + this.getArmor().getStrengthModifier()
                + this.getTrinket().getStrengthModifier()
                + this.getLeveledStrength()));
        this.setSmarts((this.getSmarts()
                + this.getArmor().getSmartsModifier()
                + this.getTrinket().getSmartsModifier()
                + this.getLeveledSmarts()));
        this.setStealth((this.getStealth()
                + this.getArmor().getStealthModifier()
                + this.getTrinket().getStealthModifier()
                + this.getLeveledStealth()));

        // Health
        if (Objects.equals(this.getCharacterClass(), "Warrior")) {
            this.setTotalHealth(((this.getStrength()
                    + this.getArmor().getStrengthModifier()
                    + this.getTrinket().getStrengthModifier()
                    + this.getWeapon().getStrengthModifier()) / 2)
                    + getBaseHealth()
                    + this.getArmor().getHealthModifier()
                    + this.getTrinket().getHealthModifier() + 10);
        } else {
            this.setTotalHealth(((this.getStrength()
                    + this.getArmor().getStrengthModifier()
                    + this.getTrinket().getStrengthModifier()
                    + this.getWeapon().getStrengthModifier()) / 2)
                    + getBaseHealth()
                    + this.getArmor().getHealthModifier()
                    + this.getTrinket().getHealthModifier());
        }

        // Damage
        if (Objects.equals(this.getCharacterClass(), "Warrior")) {
            this.setDamage(this.getStrength()
                    + this.getArmor().getStrengthModifier()
                    + this.getTrinket().getStrengthModifier()
                    + this.getTrinket().getDamageModifier()
                    + getBaseDamage()
                    + this.getWeapon().getDamageRating() + 3);
        } else {
            this.setDamage(this.getStrength()
                    + this.getArmor().getStrengthModifier()
                    + this.getTrinket().getStrengthModifier()
                    + this.getTrinket().getDamageModifier()
                    + getBaseDamage()
                    + this.getWeapon().getDamageRating());
        }

        // Spell Damage
        if (Objects.equals(this.getCharacterClass(), "Sorcerer")) {
            this.setSpellDamage(getBaseSpellDamage()
                    + this.getSmarts()
                    + this.getArmor().getSmartsModifier()
                    + this.getArmor().getSpellDamageModifier()
                    + this.getTrinket().getSmartsModifier()
                    + this.getTrinket().getSpellDamageModifier() + 6);
        } else {
            this.setSpellDamage(getBaseSpellDamage()
                    + this.getSmarts()
                    + this.getArmor().getSmartsModifier()
                    + this.getArmor().getSpellDamageModifier()
                    + this.getTrinket().getSmartsModifier()
                    + this.getTrinket().getSpellDamageModifier());
        }

        // Armor Rating
        this.setArmorRating((this.getArmor().getArmorRating() + this.getTrinket().getArmorRatingModifier()));

        // The Thief Effect^TM
        if (Objects.equals(this.getCharacterClass(), "Thief")) {
            this.setStealth(this.getStealth() + 3);
        }

        // classType: match class to an int variable for some calculations
        if (Objects.equals(this.getCharacterClass(), "Warrior")) {
            this.setClassType(1);
        } else if (Objects.equals(this.getCharacterClass(), "Thief")) {
            this.setClassType(2);
        } else if (Objects.equals(this.getCharacterClass(), "Sorcerer")) {
            this.setClassType(3);
        }

    }

    ///

    public void characterCreation() {

        /// Method containing the entire character creation sequence

        /// Instantiate a scanner to take inputs from the player, to be used throughout the method
        Scanner scanner = new Scanner(System.in);

        /// Begin with taking in a character name
        System.out.println("Please enter your character's name:");
        this.setName(scanner.nextLine());
        System.out.println("Your character's name is now " + this.getName() + ".");

        /// Presenting species choices
        System.out.println("Choose your character's species:");
        System.out.println();
        System.out.println("Human: Hailing from the Heartlands, humans are simple and many-vocationed folk." +
                           " Humans receive a +1 to Strength, Smarts, and Stealth.");
        System.out.println();
        System.out.println("Elf: Celestial beings from sidereal forests, Elves boast long life and the wisdom garnered from it." +
                           " Elves receive a +2 to Smarts and Stealth but a -1 to Strength.");
        System.out.println();
        System.out.println("Dwarf: Hearty, hardy, and proud, the deep of the earth are where Dwarves call home." +
                           " Dwarves receive a +3 to Strength but a -2 to Smarts.");
        System.out.println();
        System.out.println("Halfling: A kindhearted and peaceful people, Halflings are known to stay out of trouble." +
                           " Halflings receive a +3 to Stealth but a -2 to Strength.");
        System.out.println();
        System.out.println("Gnome: Gnomes are recognized the realm over for their diminutive and inventive nature." +
                           " Gnomes receive a +3 to Smarts, a +1 to Stealth, but a -3 to Strength.");
        System.out.println();
        System.out.println("Shamble: Broken and accursed souls, half-wraith and half-ghoul, skeletal and lost, bound to this world for some vengeance or duty." +
                           " Shambles receive a -1 to all stats, but can return from death once.");
        System.out.println();

        System.out.println("You will begin with 30 Strength, 30 Smarts, and 30 Stealth, after which " +
                           "racial bonuses and maluses are applied. Health is calculated as Strength / 2 + 100. " +
                           "Points to allot at level up is calculated as Smarts / 2 + your new Level.");
        System.out.println();
        System.out.println("Your Strength score will be added to the damage you do, which is 10 + your weapon's damage modifier.");
        System.out.println("Your Smarts score will be added to your spell damage, which is 10 + the spell's damage modifier.");
        System.out.println("Your Stealth score will affect your ability to remain unseen by your many foes.");
        System.out.println();
        System.out.println("Some of these stats will be further modified by your Class.");
        System.out.println("Your Class will also determine which of these three affects your critical hit chance.");
        System.out.println();

        // Magic boolean to control input loop
        boolean chosen = false;
        while (!chosen) {

            System.out.println("Please type 1 for Human, 2 for Elf, 3 for Dwarf, 4 for Halfling, 5 for Gnome, or 6 for Shamble.");

            try {
                /// Loop to take in the player's choice
                int choice = scanner.nextInt();

                if (choice == 1) {
                    this.setSpecies("Human");
                    chosen = true;
                } else if (choice == 2) {
                    this.setSpecies("Elf");
                    chosen = true;
                } else if (choice == 3) {
                    this.setSpecies("Dwarf");
                    chosen = true;
                } else if (choice == 4) {
                    this.setSpecies("Halfling");
                    chosen = true;
                } else if (choice == 5) {
                    this.setSpecies("Gnome");
                    chosen = true;
                } else if (choice == 6) {
                    this.setSpecies("Shamble");
                    chosen = true;
                } else {
                    System.out.println("You have typed an invalid number. Please try again.");
                    continue;
                }

            } catch (InputMismatchException exception) {
                scanner.nextLine();
                System.out.println("You have entered an invalid choice. Please try again.");
                continue;
            }
        }

        // for readability
        System.out.println();

        // Let 'em know what they picked
        System.out.println("You are now a " + this.getSpecies() + ".");
        System.out.println();

        /// Define starting weapons
        //Crappy items designed to be quickly and desirably replaced with found loot

        // Warrior weapon
        Weapon startingWeaponWarrior = new Weapon();
        startingWeaponWarrior.setName("Rusty Iron Axe");
        startingWeaponWarrior.setDescription("A simple, worn great-axe, eaten through with rust");
        startingWeaponWarrior.setDamageRating(3);
        startingWeaponWarrior.setValue(11);

        // Sorcerer weapon
        Weapon startingWeaponSorcerer = new Weapon();
        startingWeaponSorcerer.setName("Rotting Quarterstaff");
        startingWeaponSorcerer.setDescription("A simple, worn quarterstaff, gangrenous with rot");
        startingWeaponSorcerer.setDamageRating(1);
        startingWeaponSorcerer.setValue(2);

        // Thief weapon
        Weapon startingWeaponThief = new Weapon();
        startingWeaponThief.setName("Rusty Iron Dagger");
        startingWeaponThief.setDescription("A simple, worn dagger, more rust than not");
        startingWeaponThief.setDamageRating(2);
        startingWeaponThief.setValue(5);

        /// Define starting armors
        //Same as armors; weak starting items designed to be replaced

        // Warrior armor
        Armor startingArmorWarrior = new Armor();
        startingArmorWarrior.setName("Crude Platemail");
        startingArmorWarrior.setDescription("A simple suit of tired platemail, more a liability than a valuable piece of protection");
        startingArmorWarrior.setArmorRating(3);
        startingArmorWarrior.setValue(91);

        // Sorcerer armor
        Armor startingArmorSorcerer = new Armor();
        startingArmorSorcerer.setName("Moth-Eaten Robes");
        startingArmorSorcerer.setDescription("A simple robe of worn cloth, frayed and riddled with moth-holes");
        startingArmorSorcerer.setArmorRating(1);
        startingArmorSorcerer.setValue(9);

        // Thief armor
        Armor startingArmorThief = new Armor();
        startingArmorThief.setName("Padded Clothing");
        startingArmorThief.setDescription("A simple outfit of layered plain clothing, travel-worn and muddied");
        startingArmorThief.setArmorRating(2);
        startingArmorThief.setValue(43);

        /// Instructing the user to choose a character class

        System.out.println("Please choose a class:");
        System.out.println();
        System.out.println("--WARRIOR--");
        System.out.println("Warriors are masters of martial warfare, charging headlong into battle with sword, axe, and spear.");
        System.out.println("Warriors receive a +10 to Health and a +3 to Damage." +
                "\nA Warrior's critical hit chance is based on their Strength score.");
        System.out.println();
        System.out.println("--SORCERER--");
        System.out.println("Sorcerers control the weave of magic, and rend their foes from afar with the power of the mystic arts.");
        System.out.println("Sorcerers receive a +6 to Spell Damage." +
                "\nA Sorcerer's critical hit chance is based on their Smarts score.");
        System.out.println();
        System.out.println("--THIEF--");
        System.out.println("Thieves excel in the dark, outwitting their foes with cloak and dagger and, on occasion, missile weapons.");
        System.out.println("Thieves receive a +3 to Stealth and find twice as much gold in loot." +
                "\nA Thief's critical hit chance is based on their Stealth score, counted twice.");
        System.out.println();

        /// Input loop to take in a number from the user and then assign the class

        chosen = false;

        while (!chosen) {

            System.out.println("Please type 1 for Warrior, 2 for Sorcerer, and 3 for Thief.");

            try {
                int choice = scanner.nextInt();

            if (choice == 1) {
                this.setCharacterClass("Warrior");
                this.setArmor(startingArmorWarrior);
                this.setWeapon(startingWeaponWarrior);
                this.setClassType(1);
                chosen = true;
            } else if (choice == 2) {
                this.setCharacterClass("Sorcerer");
                this.setArmor(startingArmorSorcerer);
                this.setWeapon(startingWeaponSorcerer);
                this.setClassType(3);
                chosen = true;
            } else if (choice == 3) {
                this.setCharacterClass("Thief");
                this.setArmor(startingArmorThief);
                this.setWeapon(startingWeaponThief);
                this.setClassType(2);
                chosen = true;
            } else {
                System.out.println("You have typed an invalid number. Please try again.");
                continue;
            }

            } catch (InputMismatchException exception) {
                scanner.nextLine();
                System.out.println("You have entered an invalid choice. Please try again.");
                continue;
            }

        }

        System.out.println();
        System.out.println("You are now a " + this.getCharacterClass() + ".");
        System.out.println();

//        if (Objects.equals(this.getCharacterClass(), "Sorcerer")) {
//            System.out.println("As a practitioner of the magical arts, you command the elements. Which have you favored til now?");
//        }

        /// Defining starting trinkets
        //Choice one always maximizes the best quality (positive modifiers) of the chosen species
        //Choice two shores up the weakness (negative modifiers) of the chosen species

        // Human starting trinkets
        Trinket startingHumanTrinket01 = new Trinket();
            startingHumanTrinket01.setName("Pendant of the Human Spirit");
            startingHumanTrinket01.setDescription("This amulet, a gift from a loved one, bolsters the resiliency inherent to mankind within you");
            startingHumanTrinket01.setArmorRatingModifier(1);
            startingHumanTrinket01.setValue(100);

        Trinket startingHumanTrinket02 = new Trinket();
            startingHumanTrinket02.setName("Belt of Surefire");
            startingHumanTrinket02.setDescription("A flame-varnished leather belt that strengthens the weakness of the human spirit");
            startingHumanTrinket02.setSpellDamageModifier(3);
            startingHumanTrinket02.setValue(100);

        // Elf starting trinkets
        Trinket startingElfTrinket01 = new Trinket();
            startingElfTrinket01.setName("Circlet of Elfdom");
            startingElfTrinket01.setDescription("A delicate tiara imbued with the long-lived majesty of Elf-kind");
            startingElfTrinket01.setSmartsModifier(1);
            startingElfTrinket01.setValue(100);

        Trinket startingElfTrinket02 = new Trinket();
            startingElfTrinket02.setName("Mantle of Other-Kind");
            startingElfTrinket02.setDescription("A silken half-cape blessed with the strength of other peoples");
            startingElfTrinket02.setHealthModifier(5);
            startingElfTrinket02.setValue(100);

        // Dwarf starting trinkets
        Trinket startingDwarfTrinket01 = new Trinket();
            startingDwarfTrinket01.setName("Torque of the Under-Mountain Realm");
            startingDwarfTrinket01.setDescription("An iron-forged, bronze-plated collar of rent metal from the genius of Dwarves");
            startingDwarfTrinket01.setArmorRatingModifier(1);
            startingDwarfTrinket01.setValue(100);

        Trinket startingDwarfTrinket02 = new Trinket();
            startingDwarfTrinket02.setName("Buckle of Dwarven Resolve");
            startingDwarfTrinket02.setDescription("A polished belt buckle engraved with the triumphs of Dwarves");
            startingDwarfTrinket02.setSmartsModifier(1);
            startingDwarfTrinket02.setValue(100);

        // Halfling starting trinkets
        Trinket startingHalflingTrinket01 = new Trinket();
            startingHalflingTrinket01.setName("Hat of Halfling Wit");
            startingHalflingTrinket01.setDescription("A fashionable field hat from the tanners of Half-folk heartlands");
            startingHalflingTrinket01.setSmartsModifier(1);
            startingHalflingTrinket01.setValue(100);

        Trinket startingHalflingTrinket02 = new Trinket();
            startingHalflingTrinket02.setName("Bangle of Might");
            startingHalflingTrinket02.setDescription("A colorful accessory commemorating the overlooked strength of littler peoples");
            startingHalflingTrinket02.setStrengthModifier(1);
            startingHalflingTrinket02.setValue(100);

        // Gnome starting trinkets
        Trinket startingGnomeTrinket01 = new Trinket();
            startingGnomeTrinket01.setName("Spectacles of Invention");
            startingGnomeTrinket01.setDescription("Many-lensed bifocals to aid in all things mechanical, magical, and scientific");
            startingGnomeTrinket01.setSmartsModifier(1);
            startingGnomeTrinket01.setValue(100);

        Trinket startingGnomeTrinket02 = new Trinket();
            startingGnomeTrinket02.setName("Ring of Gnomish Will");
            startingGnomeTrinket02.setDescription("A broad and riveted ring of crude make, but hardy with Gnome strength");
            startingGnomeTrinket02.setDamageModifier(1);
            startingGnomeTrinket02.setValue(100);

        // Shamble starting trinkets
        Trinket startingShambleTrinket01 = new Trinket();
            startingShambleTrinket01.setName("Crown of the Lost");
            startingShambleTrinket01.setDescription("A cracked crown of black glass, haunted with sorrow");
            startingShambleTrinket01.setHealthModifier(20);
            startingShambleTrinket01.setValue(100);

        Trinket startingShambleTrinket02 = new Trinket();
            startingShambleTrinket02.setName("Cloak of the Forgotten");
            startingShambleTrinket02.setDescription("A tattered cloak the color of night, moving unlike fabric and more like shadows cast");
            startingShambleTrinket02.setStealthModifier(2);
            startingShambleTrinket02.setValue(100);

        /// Instructing the user to pick a trinket; loop to take inputs and assign the choice to the player character
        boolean trinketPicked = false;
        int pick;

        while (!trinketPicked) {

            System.out.println("You brought a trinket from home to aid you on your venture.");
            System.out.println("Which did you choose?");
            System.out.println();

            /// Human
            if (Objects.equals(this.getSpecies(), "Human")) {
                System.out.println("Did you pick the " + startingHumanTrinket01.getName() + "? "
                        + startingHumanTrinket01.getDescription() + ", it gives you +"
                        + startingHumanTrinket01.getArmorRatingModifier() + " Armor Rating.");
                System.out.println("Or did you pick the + " + startingHumanTrinket02.getName() + "? "
                        + startingHumanTrinket02.getDescription() + ", it gives you +"
                        + startingHumanTrinket02.getSpellDamageModifier() + " Spell Damage.");
                System.out.println();
                System.out.println("Type 1 for the " + startingHumanTrinket01.getName()
                        + ", or type 2 for the " + startingHumanTrinket02.getName() + ".");

                try {
                    pick = scanner.nextInt();
                    if (pick == 1) {
                        this.setTrinket(startingHumanTrinket01);
                        trinketPicked = true;
                    } else if (pick == 2) {
                        this.setTrinket(startingHumanTrinket02);
                        trinketPicked = true;
                    } else {
                        System.out.println("You have typed an invalid number. Please try again.");
                    }
                } catch (InputMismatchException exception) {
                    scanner.nextLine();
                    System.out.println("You have entered an invalid choice. Please try again.");
                    continue;
                }

            /// Elf
            } else if (Objects.equals(this.getSpecies(), "Elf")) {
                System.out.println("Did you pick the " + startingElfTrinket01.getName() + "? "
                        + startingElfTrinket01.getDescription() + ", it gives you +"
                        + startingElfTrinket01.getSmartsModifier() + " Smarts.");
                System.out.println("Or did you pick the " + startingElfTrinket02.getName() + "? "
                        + startingElfTrinket02.getDescription() + ", it gives you +"
                        + startingElfTrinket02.getHealthModifier() + " Health.");
                System.out.println();
                System.out.println("Type 1 for the " + startingElfTrinket01.getName()
                        + ", or type 2 for the " + startingElfTrinket02.getName() + ".");

                try {
                    pick = scanner.nextInt();
                    if (pick == 1) {
                        this.setTrinket(startingElfTrinket01);
                        trinketPicked = true;
                    } else if (pick == 2) {
                        this.setTrinket(startingElfTrinket02);
                        trinketPicked = true;
                    } else {
                        System.out.println("You have typed an invalid number. Please try again.");
                    }
                } catch (InputMismatchException exception) {
                    scanner.nextLine();
                    System.out.println("You have entered an invalid choice. Please try again.");
                    continue;
                }

            /// Dwarf
            } else if (Objects.equals(this.getSpecies(), "Dwarf")) {
                System.out.println("Did you pick the " + startingDwarfTrinket01.getName() + "? "
                        + startingDwarfTrinket01.getDescription() + ", it gives you +"
                        + startingDwarfTrinket01.getArmorRatingModifier() + " Armor Rating.");
                System.out.println("Or did you pick the " + startingDwarfTrinket02.getName() + "? "
                        + startingDwarfTrinket02.getDescription() + ", it gives you +"
                        + startingDwarfTrinket02.getSmartsModifier() + " Smarts.");
                System.out.println();
                System.out.println("Type 1 for the " + startingDwarfTrinket01.getName()
                        + ", or type 2 for the " + startingDwarfTrinket02.getName() + ".");

                try {
                pick = scanner.nextInt();
                if (pick == 1) {
                    this.setTrinket(startingDwarfTrinket01);
                    trinketPicked = true;
                } else if (pick == 2) {
                    this.setTrinket(startingDwarfTrinket02);
                    trinketPicked = true;
                } else {
                    System.out.println("You have typed an invalid number. Please try again.");
                }
                } catch (InputMismatchException exception) {
                    scanner.nextLine();
                    System.out.println("You have entered an invalid choice. Please try again.");
                    continue;
                }

            /// Halfling
            } else if (Objects.equals(this.getSpecies(), "Halfling")) {
                System.out.println("Did you pick the " + startingHalflingTrinket01.getName() + "? "
                        + startingHalflingTrinket01.getDescription() + ", it gives you +"
                        + startingHalflingTrinket01.getSmartsModifier() + " Smarts.");
                System.out.println("Or did you pick the " + startingHalflingTrinket02.getName() + "? "
                        + startingHalflingTrinket02.getDescription() + ", it gives you +"
                        + startingHalflingTrinket02.getStrengthModifier() + " Strength.");
                System.out.println();
                System.out.println("Type 1 for the " + startingHalflingTrinket01.getName()
                        + ", or type 2 for the " + startingHalflingTrinket02.getName() + ".");

                try {
                pick = scanner.nextInt();
                if (pick == 1) {
                    this.setTrinket(startingHalflingTrinket01);
                    trinketPicked = true;
                } else if (pick == 2) {
                    this.setTrinket(startingHalflingTrinket02);
                    trinketPicked = true;
                } else {
                    System.out.println("You have typed an invalid number. Please try again.");
                }
                } catch (InputMismatchException exception) {
                    scanner.nextLine();
                    System.out.println("You have entered an invalid choice. Please try again.");
                    continue;
                }

            /// Gnome
            } else if (Objects.equals(this.getSpecies(), "Gnome")) {
                System.out.println("Did you pick the " + startingGnomeTrinket01.getName() + "? "
                        + startingGnomeTrinket01.getDescription() + ", it gives you +"
                        + startingGnomeTrinket01.getSmartsModifier() + " Smarts.");
                System.out.println("Or did you pick the " + startingGnomeTrinket02.getName() + "? "
                        + startingGnomeTrinket02.getDescription() + ", it gives you +"
                        + startingGnomeTrinket02.getDamageModifier() + " Damage.");
                System.out.println();
                System.out.println("Type 1 for the " + startingGnomeTrinket01.getName()
                        + ", or type 2 for the " + startingGnomeTrinket02.getName() + ".");

                try {
                pick = scanner.nextInt();
                if (pick == 1) {
                    this.setTrinket(startingGnomeTrinket01);
                    trinketPicked = true;
                } else if (pick == 2) {
                    this.setTrinket(startingGnomeTrinket02);
                    trinketPicked = true;
                } else {
                    System.out.println("You have typed an invalid number. Please try again.");
                }
                } catch (InputMismatchException exception) {
                    scanner.nextLine();
                    System.out.println("You have entered an invalid choice. Please try again.");
                    continue;
                }

            /// Shamble
            } else if (Objects.equals(this.getSpecies(), "Shamble")) {
                System.out.println("Did you pick the " + startingShambleTrinket01.getName() + "? "
                        + startingShambleTrinket01.getDescription() + ", it gives you +"
                        + startingShambleTrinket01.getHealthModifier() + " Health.");
                System.out.println("Or did you pick the " + startingShambleTrinket02.getName() + "? "
                        + startingShambleTrinket02.getDescription() + ", it gives you +"
                        + startingShambleTrinket02.getStealthModifier() + " Stealth.");
                System.out.println();
                System.out.println("Type 1 for the " + startingShambleTrinket01.getName()
                        + ", or type 2 for the " + startingShambleTrinket02.getName() + ".");

                try {
                pick = scanner.nextInt();
                if (pick == 1) {
                    this.setTrinket(startingShambleTrinket01);
                    trinketPicked = true;
                } else if (pick == 2) {
                    this.setTrinket(startingShambleTrinket02);
                    trinketPicked = true;
                } else {
                    System.out.println("You have typed an invalid number. Please try again.");
                }
                } catch (InputMismatchException exception) {
                    scanner.nextLine();
                    System.out.println("You have entered an invalid choice. Please try again.");
                    continue;
                }

            }
        }

        /// All is said and done; make sure player character stats have been calculated
        this.recalculateAttributes();

        // set health to max--gotta start your adventure at full!
        this.setHealth(this.getTotalHealth());

        /// Roll for pocket change
        this.rollLoot();

        /// Final exit notes
        System.out.println();
        System.out.println("You set out on your adventure with " + this.getGold() + " spare coin.");

        /// END

    }

    ///

    public int rollLoot(){

        /// Method to generate a gold reward

        // Random number generation multiplied by player level
        int gold = (((int)(Math.random()*100) * this.getLevel()) + 1);

        // If statement to multiply again if the player character is the Thief class
        if (Objects.equals(this.getCharacterClass(), "Thief")) {
            gold = (gold * 2);
        }

        // moneyPlease()
        this.setGold(this.getGold() + gold);

        /// PUT THIS IN DUNGEON; MAKE IT SCALE BASED ON SLAIN MONSTERS??
        //Or roll each time a monster is killed? Choice to loot or not loot from enemies individually
        //at end of room? maybe oozes often hurt you and contain no gold but sometimes have a trinket, weapon or armor?
        //Voids no loot? etc

        return gold;

    }

    public void populateMoves() {

        // Instantiate the ArrayList to be transposed onto this.moves at the end of the method
        ArrayList<Move> moves = new ArrayList<>();

        /// Open them connections
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            // Load MySQL driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            String connectionString = "jdbc:mysql://localhost/dungeoncrawler?"
                    + "user=root&password=SQLsAreUsuallyW0rse!"
                    + "&useSSL=false&allowPublicKeyRetrieval=true";

            // Set up the connection with the DB
            connection = DriverManager
                    .getConnection(connectionString);
            statement = connection.createStatement();

            resultSet = statement
                    .executeQuery("select * from dungeoncrawler.moves WHERE Theme = \"Player\" AND Type = "
                            + this.getClassType() + " AND Level between 1 and " + this.getLevel());

            // loop through the result set while there are more records
            while (resultSet.next()) {
                String name = resultSet.getString("Name");
                String description = resultSet.getString("Description");
                String theme = resultSet.getString("Theme");
                int type = resultSet.getInt("Type");
                int level = resultSet.getInt("Level");
                int damageModifier = resultSet.getInt("Damage Modifier");
                boolean ignoreArmor = resultSet.getBoolean("Ignore Armor");

                moves.add(new Move(name, description, theme, type, level, damageModifier, ignoreArmor));

            }

            // Catch exceptions
        } catch (SQLException exc) {
            System.out.println("Exception occurred");
            exc.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.out.println("Exception occurred: driver not found on classpath");
            e.printStackTrace();
        } finally {
            try {
                // close all JDBC elements
                statement.close();
                resultSet.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        // Add a class-specific special move to the end of the list
        switch (this.getCharacterClass()) {

            case "Warrior":
                Move powerAttack = new Move("Power Attack",
                        "You strike with reckless force. It deals twice your normal attack damage, but leaves you twice as likely to be hit by a foe this round.",
                        "Player",
                        1,
                        1,
                        this.getDamage(),
                        false);
                // level 10 warrior move, if statement
                // add to the movelist

            case "Sorcerer":
                // level 1 sorcerer move

                if (this.getLevel() >= 10) {
                    Move forceOfWill = new Move("Force of Will",
                            "Your mastery of the magical arts afford you keen insight into their weave; you draw on your sheer willpower to channel energy. It deals triple your normal spell attack damage, but damages you for one tenth of your own Health.",
                            "Player",
                            3,
                            10,
                            (this.getSpellDamage() * 2),
                            false);
                    moves.add(forceOfWill);
                }

            case "Thief":
                // thief moves
                // if statement
                //add them to the movelist
        }

        // set this.moves to be equal to the moveslist we've just created
        this.setMoves(moves);

    }

    public boolean youDied(PlayerCharacter pc, Monster monster) {

        // handle player death. Returns false if the player does not die

        System.out.println("You died!");
        System.out.println("You were slain by " + monster.getName() + ". ");
        System.out.println("The curtain of forever night draws around your fading vision as you breathe your last breath.");
        System.out.println("Panic and sorrow set in. So much to have done, so much to lose.");
        System.out.println("Death. It is a tragedy that comes for us but once in a lifetime.");
        if (Objects.equals(pc.getSpecies(), "Shamble") && !this.isDeathTokenUsed()) {
            if (this.deathTokenHandler()) {
                DungeonCrawlController.endGame(pc);
            } else {
                return false;
            }
        } else {
            DungeonCrawlController.endGame(pc);
        }

        // if we're here without properly handling death, print out that something went wrong and end the game
        System.out.println("You have cheated death in some inexplicable fashion. The cosmos cannot wheel on with such an error.");
        DungeonCrawlController.endGame(pc);
        // to satisfy the method signature
        return true;

    }

    public boolean youDied(PlayerCharacter pc) {

        // handle player death. Returns false if the player does not die

        System.out.println("You died!");
        System.out.println("The curtain of forever night draws around your fading vision as you breathe your last breath.");
        System.out.println("Panic and sorrow set in. So much to have done, so much to lose.");
        System.out.println("Death. It is a tragedy that comes for us but once in a lifetime.");
        if (Objects.equals(pc.getSpecies(), "Shamble") && !this.isDeathTokenUsed()) {
            if (this.deathTokenHandler()) {
                DungeonCrawlController.endGame(pc);
            } else {
                return false;
            }
        } else {
            DungeonCrawlController.endGame(pc);
        }

        // if we're here without properly handling death, print out that something went wrong and end the game
        System.out.println("You have cheated death in some inexplicable fashion. The cosmos cannot wheel on with such an error.");
        DungeonCrawlController.endGame(pc);
        // to satisfy the method signature
        return true;

    }

    public boolean deathTokenHandler() {

        // if boolean returns true, game ends. If it returns false, the player used a death token

        System.out.println();
        System.out.println("...Or is it?");
        System.out.println("You feel some pull back to the light of life. A shepherd's crook to lead you back to the mortal coil.");
        System.out.println("Such a chance--the incredible fortune to cheat death--shall never smile upon you again.");
        System.out.println();
        System.out.println("You are being pulled apart. At one end, the pull to flesh and stone. At your other, the beckon of an eternal sleep.");

        Scanner deathScanner = new Scanner(System.in);
        boolean fateChosen = false;

        while (!fateChosen) {

            System.out.println("Do you return to the land of the living? Or do you wade into the dark waters of death?");
            System.out.println("LIVE");
            System.out.println("DIE");

            String live = "LIVE";
            String die = "DIE";

            try {

                String fate = deathScanner.next();

                if (Objects.equals(fate, live)) {
                    System.out.println("You grasp that lifeline in your mind's eye. You cling to it, draw back those curtains of night, surface from the viscous waters of choking death, and return to the light.");
                    fateChosen = true;
                    this.setDeathTokenUsed(true);
                    this.setHealth((this.getTotalHealth() / 2));
                    return false;
                } else if (Objects.equals(fate, die)) {
                    System.out.println("You accept the allure of slumber everlasting, and let the tides of night wash over you. You fade from the mortal realm, and pass into the beyond.");
                    fateChosen = true;
                    return true;
                } else {
                    deathScanner.nextLine();
                    System.out.println("Make your choice.");
                    continue;
                }

            } catch (InputMismatchException exception) {
                deathScanner.nextLine();
                System.out.println("Make your choice.");
                continue;
            }
        }

        // if we're here without properly handling death, print out that something went wrong and end the game
        System.out.println("You have cheated death in some inexplicable fashion. The cosmos cannot wheel on with such an error.");
        return true;

    }

    public boolean inspectGear() {

        // Handle the choice to inspect gear in inspect()

        System.out.println("Your armor is " + this.getArmor().getName() + ": " + this.getArmor().getDescription() + ". "
                + "\nIt has an armor rating of " + this.getArmor().getArmorRating() + ". "
                + "\n"
                + "\nYour weapon is " + this.getWeapon().getName() + ": " + this.getWeapon().getDescription() + ". "
                + "\nIt deals " + this.getWeapon().getDamageRating() + " damage."
                + "\n"
                + "\nYour trinket is " + this.getTrinket().getName() + ": " + this.getTrinket().getDescription() + ". "
                + "\n"
                + "\nYou have " + this.getPotions() + " potions.");

        System.out.println();

        boolean choosing = true;

        Scanner choiceScanner = new Scanner(System.in);

        while (choosing) {

            System.out.println("What would you like to do?");
            System.out.println("1. Identify magic effects on your armor");
            System.out.println("2. Identify magic effects on your weapon");
            System.out.println("3. Identify magic effects on your trinket");
            if (this.getPotions() > 0) {
                System.out.println("4. Drink a potion");
            }
            System.out.println("If you do not wish to do anything else, type \"0\" to change your decision.");

            try {

                int choice = choiceScanner.nextInt();

                switch (choice) {

                    // IDENTIFY ARMOR
                    case 1:
                        // Systematically run through all possible magical armor properties and inform the player of them if they're not null

                        // Strength Modifier
                        if (!Objects.equals(this.getArmor().getStrengthModifier(), null)) {
                            if (this.getArmor().getStrengthModifier() > 0) {
                                System.out.println("Your armor magically enhances your Strength by " + this.getArmor().getStrengthModifier() + " points.");
                            } else if (this.getArmor().getStrengthModifier() < 0) {
                                System.out.println("Your armor magically reduces your Strength by " + this.getArmor().getStrengthModifier() + " points.");
                            }
                        }

                        // Smarts Modifier
                        if (!Objects.equals(this.getArmor().getSmartsModifier(), null)) {
                            if (this.getArmor().getSmartsModifier() > 0) {
                                System.out.println("Your armor magically enhances your Smarts by " + this.getArmor().getSmartsModifier() + " points.");
                            } else if (this.getArmor().getSmartsModifier() < 0) {
                                System.out.println("Your armor magically reduces your Smarts by " + this.getArmor().getSmartsModifier() + " points.");
                            }
                        }

                        // Stealth Modifier
                        if (!Objects.equals(this.getArmor().getStealthModifier(), null)) {
                            if (this.getArmor().getStealthModifier() > 0) {
                                System.out.println("Your armor magically enhances your Stealth by " + this.getArmor().getStealthModifier() + " points.");
                            } else if (this.getArmor().getStealthModifier() < 0) {
                                System.out.println("Your armor magically reduces your Stealth by " + this.getArmor().getStealthModifier() + " points.");
                            }
                        }

                        // Health Modifier
                        if (!Objects.equals(this.getArmor().getHealthModifier(), null)) {
                            if (this.getArmor().getHealthModifier() > 0) {
                                System.out.println("Your armor magically enhances your Health by " + this.getArmor().getHealthModifier() + " points.");
                            } else if (this.getArmor().getHealthModifier() < 0) {
                                System.out.println("Your armor magically reduces your Health by " + this.getArmor().getHealthModifier() + " points.");
                            }
                        }

                        // Spell Damage
                        if (!Objects.equals(this.getArmor().getSpellDamageModifier(), null)) {
                            if (this.getArmor().getSpellDamageModifier() > 0) {
                                System.out.println("Your armor magically enhances your Spell Damage by " + this.getArmor().getSpellDamageModifier() + " points.");
                            } else if (this.getArmor().getSpellDamageModifier() < 0) {
                                System.out.println("Your armor magically reduces your Spell Damage by " + this.getArmor().getSpellDamageModifier() + " points.");
                            }
                        }

                        continue;


                    // IDENTIFY WEAPON
                    case 2:
                        // Systematically run through all possible magical weapon properties and inform the player of them if they're not null

                        // Strength Modifier
                        if (!Objects.equals(this.getWeapon().getStrengthModifier(), null)) {
                            if (this.getWeapon().getStrengthModifier() > 0) {
                                System.out.println("Your weapon magically enhances your Strength by " + this.getWeapon().getStrengthModifier() + " points.");
                            } else if (this.getWeapon().getStrengthModifier() < 0) {
                                System.out.println("Your weapon magically reduces your Strength by " + this.getWeapon().getStrengthModifier() + " points.");
                            }
                        }

                        // Spell Damage
                        if (!Objects.equals(this.getWeapon().getSpellDamageModifier(), null)) {
                            if (this.getWeapon().getSpellDamageModifier() > 0) {
                                System.out.println("Your weapon magically enhances your Spell Damage by " + this.getWeapon().getSpellDamageModifier() + " points.");
                            } else if (this.getWeapon().getSpellDamageModifier() < 0) {
                                System.out.println("Your weapon magically reduces your Spell Damage by " + this.getWeapon().getSpellDamageModifier() + " points.");
                            }
                        }

                        continue;

                    // IDENTIFY TRINKET
                    case 3:
                        // Systematically run through all possible magical trinket properties and inform the player of them if they're not null

                        // Strength Modifier
                        if (!Objects.equals(this.getTrinket().getStrengthModifier(), null)) {
                            if (this.getTrinket().getStrengthModifier() > 0) {
                                System.out.println("Your trinket magically enhances your Strength by " + this.getTrinket().getStrengthModifier() + " points.");
                            } else if (this.getTrinket().getStrengthModifier() < 0) {
                                System.out.println("Your trinket magically reduces your Strength by " + this.getTrinket().getStrengthModifier() + " points.");
                            }
                        }

                        // Smarts Modifier
                        if (!Objects.equals(this.getTrinket().getSmartsModifier(), null)) {
                            if (this.getTrinket().getSmartsModifier() > 0) {
                                System.out.println("Your trinket magically enhances your Smarts by " + this.getTrinket().getSmartsModifier() + " points.");
                            } else if (this.getTrinket().getSmartsModifier() < 0) {
                                System.out.println("Your trinket magically reduces your Smarts by " + this.getTrinket().getSmartsModifier() + " points.");
                            }
                        }

                        // Stealth Modifier
                        if (!Objects.equals(this.getTrinket().getStealthModifier(), null)) {
                            if (this.getTrinket().getStealthModifier() > 0) {
                                System.out.println("Your trinket magically enhances your Stealth by " + this.getTrinket().getStealthModifier() + " points.");
                            } else if (this.getTrinket().getStealthModifier() < 0) {
                                System.out.println("Your trinket magically reduces your Stealth by " + this.getArmor().getStealthModifier() + " points.");
                            }
                        }

                        // Health Modifier
                        if (!Objects.equals(this.getTrinket().getHealthModifier(), null)) {
                            if (this.getTrinket().getHealthModifier() > 0) {
                                System.out.println("Your trinket magically enhances your Health by " + this.getTrinket().getHealthModifier() + " points.");
                            } else if (this.getTrinket().getHealthModifier() < 0) {
                                System.out.println("Your trinket magically reduces your Health by " + this.getTrinket().getHealthModifier() + " points.");
                            }
                        }

                        // Damage
                        if (!Objects.equals(this.getTrinket().getDamageModifier(), null)) {
                            if (this.getTrinket().getDamageModifier() > 0) {
                                System.out.println("Your trinket magically enhances your Damage by " + this.getTrinket().getDamageModifier() + " points.");
                            } else if (this.getTrinket().getDamageModifier() < 0) {
                                System.out.println("Your trinket magically reduces your Damage by " + this.getTrinket().getDamageModifier() + " points.");
                            }
                        }

                        // Spell Damage
                        if (!Objects.equals(this.getTrinket().getSpellDamageModifier(), null)) {
                            if (this.getArmor().getSpellDamageModifier() > 0) {
                                System.out.println("Your trinket magically enhances your Spell Damage by " + this.getArmor().getSpellDamageModifier() + " points.");
                            } else if (this.getArmor().getSpellDamageModifier() < 0) {
                                System.out.println("Your trinket magically reduces your Spell Damage by " + this.getArmor().getSpellDamageModifier() + " points.");
                            }
                        }

                        // Armor Rating
                        if (!Objects.equals(this.getTrinket().getArmorRatingModifier(), null)) {
                            if (this.getTrinket().getArmorRatingModifier() > 0) {
                                System.out.println("Your trinket magically enhances your Armor Rating by " + this.getTrinket().getArmorRatingModifier() + " points.");
                            } else if (this.getTrinket().getArmorRatingModifier() < 0) {
                                System.out.println("Your trinket magically reduces your Armor Rating by " + this.getTrinket().getArmorRatingModifier() + " points.");
                            }
                        }

                        continue;


                    // DRINK POTION
                    case 4:
                        // Handle an input of 4 while there are no potions
                        if (this.getPotions() <= 0) {
                            System.out.println("You have entered an invalid choice. Please try again.");
                            continue;
                        } else {

                            // Create some ints to handle calculations
                            // Potions always heal the player character for half of their total possible health (rounding however java rounds ints);
                            // i.e. a 200 total health character at 80 current health will heal for +100 to 180
                            int healthToAdd = (this.getTotalHealth() / 2);
                            int currentHealth = this.getHealth();
                            this.setHealth((currentHealth + healthToAdd));

                            // If the added health makes the current health pool exceed total possible health, set it to max
                            if (this.getHealth() > this.getTotalHealth()) {
                                this.setHealth(this.getTotalHealth());
                            }

                            // Tell the player how it went, with some flavor text
                            System.out.println("You pop the cork on the vial and scarf down the carbonated concoction of ruby-red magic medicine.");
                            System.out.println("It is a sickeningly herbal flavor, but it heals you for "
                                    + healthToAdd + " points from "
                                    + currentHealth + " Health to "
                                    + this.getHealth() + " Health.");
                            // Return true, ending the player turn if in combat
                            return true;
                        }

                    // GO BACK
                    case 0:
                        return false;

                    // NO VALID CHOICE
                    default:
                        System.out.println("You have entered an invalid choice. Please try again.");
                        continue;
                }

            } catch (InputMismatchException exception) {
                choiceScanner.nextLine();
                System.out.println("You have entered an invalid choice. Please try again.");
            }

        }

        // If we're here without choosing something somehow, let the player turn just continue, it's no big deal
        return false;

    }

    public void clearFlags() {

        // clear any true flags on defense booleans that may have been set during a battle

        this.setDefendMelee(false);
        this.setDefendRanged(false);
        this.setDefendMagic(false);
        this.setReckless(false);

    }

    public double getCritChance() {

        // Calculate and return the player character's critical hit chance
        // 10 base + the class attribute divided by ten (x2 for thieves)

        double critChance = 10;

        if (Objects.equals(this.getCharacterClass(), "Warrior")) {
            critChance = critChance + (this.getStrength() / 10);
        } else if (Objects.equals(this.getCharacterClass(), "Sorcerer")) {
            critChance = critChance + (this.getSmarts() / 10);
        } else if (Objects.equals(this.getCharacterClass(), "Thief")) {
            critChance = critChance + ((this.getStealth() / 10) * 2);
        }

        return critChance;

    }

}
