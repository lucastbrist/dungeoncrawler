package DungeonCrawler.models;

import DungeonCrawler.controllers.DungeonCrawlController;
import com.sun.security.jgss.GSSUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Objects;
import java.util.Scanner;
import static DungeonCrawler.controllers.DungeonCrawlController.StringStore.connectionString;

public class Room {

    String name;
    String description;
    String theme;
    ArrayList<Monster> monsters;
    boolean bossRoom;
    boolean cleared;
    Trinket roomTrinket;
    Armor roomArmor;
    Weapon roomWeapon;
    int potions;
    int deadCount;

    public Room() {
    }

    public Room(String name, String description, ArrayList<Monster> monsters, boolean bossRoom, boolean cleared) {
        this.name = name;
        this.description = description;
        this.monsters = monsters;
        this.bossRoom = bossRoom;
        this.cleared = cleared;
    }

    public Room(String name, String description) {
    }

    public Room(String name, String description, String theme) {
        this.setName(name);
        this.setDescription(description);
        this.setTheme(theme);
    }

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

    public ArrayList<Monster> getMonsters() {
        return monsters;
    }

    public void setMonsters(ArrayList<Monster> monsters) {
        this.monsters = monsters;
    }

    public boolean isBossRoom() {
        return bossRoom;
    }

    public void setBossRoom(boolean bossRoom) {
        this.bossRoom = bossRoom;
    }

    public boolean isCleared() {
        return cleared;
    }

    public void setCleared(boolean cleared) {
        this.cleared = cleared;
    }

    public Trinket getRoomTrinket() {
        return roomTrinket;
    }

    public void setRoomTrinket(Trinket roomTrinket) {
        this.roomTrinket = roomTrinket;
    }

    public Armor getRoomArmor() {
        return roomArmor;
    }

    public void setRoomArmor(Armor roomArmor) {
        this.roomArmor = roomArmor;
    }

    public Weapon getRoomWeapon() {
        return roomWeapon;
    }

    public void setRoomWeapon(Weapon roomWeapon) {
        this.roomWeapon = roomWeapon;
    }

    public int getPotions() {
        return potions;
    }

    public void setPotions(int potions) {
        this.potions = potions;
    }

    public int getDeadCount() {
        return deadCount;
    }

    public void setDeadCount(int deadCount) {
        this.deadCount = deadCount;
    }

    public void generateRoomLoot(PlayerCharacter pc) {

        /// Method to generate if a room has loot and if so, what kind, drawing from the database with SQL statements

        int lootType = (int)((Math.random() * 4) + 1);

        switch (lootType) {

            /// Trinket
            case 1:

                // Instantiate trinket
                Trinket trinket = new Trinket();
                // Generate it
                trinket.generateTrinket(pc);
                // Add it to the room
                this.setRoomTrinket(trinket);
                break;

            /// Armor
            case 2:
                // Instantiate armor
                Armor armor = new Armor();
                // Generate it
                armor.generateArmor(pc);
                // Add it to the room
                this.setRoomArmor(armor);
                break;

            /// Weapon
            case 3:
                // Instantiate weapon
                Weapon weapon = new Weapon();
                // Generate it
                weapon.generateWeapon(pc);
                // Add it to the room
                this.setRoomWeapon(weapon);
                break;

            /// Don't add anything
            case 4:
                break;

            default:
                // still don't add anything
                break;

        }

        // potions yes or no
        int potionsYN = (int)(Math.random() * 2) + 1;
        if (potionsYN == 2) {
            int potionsToAdd = (int)(Math.random() * 3) + 1;
            this.setPotions(potionsToAdd);
        }

        /// END

    }

    public boolean enterRoom(PlayerCharacter pc) {

        /// Method to handle when a player enters a room.
        // Returns true when cleared

        // If it's the first time, they get the description of the room.
        // Otherwise, they are  just given the name, and then reminded they've been here before
        if (!this.isCleared()) {
            System.out.println("You enter " + this.getName() + ".");
            System.out.println(this.getDescription());
            this.generateMonsters(pc);
            this.generateRoomLoot(pc);
        } else {
            System.out.println("You enter " + this.getName() + " once again.");
            System.out.println("You've been here before.");
        }

        // instantiate that ol' scanner
        Scanner roomScanner = new Scanner(System.in);
        int choice;

        boolean choosing = true;

        while (choosing) {

            // If there are monsters in the room, the player must take action immediately
            // Otherwise, they are free to roam about and inspect the room
            if (!this.getMonsters().isEmpty() && this.getDeadCount() < this.getMonsters().size()) {
                System.out.println("You are not alone. You see " + this.getTheme() + "!");
                System.out.println("What do you do? They have not spotted you yet.");
                System.out.println("1. Attack");
                System.out.println("2. Hide");
                System.out.println("3. Inspect");
                System.out.println("4. Leave");

                try {
                    choice = roomScanner.nextInt();

                    switch (choice) {

                        /// ATTACK
                        case 1:
                            doBattle(pc);
                            choosing = false;
                            break;
                            ///

                            /// HIDE
                        case 2:
                            if (hide(pc)) {
                                inspect(pc, true);
                            } else {
                                this.doBattle(pc);
                                choosing = false;
                                break;
                            }

                            ///

                            /// INSPECT
                        case 3:
                            inspect(pc, false);
                            continue;
                            ///

                            /// LEAVE
                        case 4:
                            return false;
                            ///

                            /// DEFAULT
                        default:
                            System.out.println("You have entered an invalid choice. Please try again.");
                            continue;
                    }

                    // catch input mismatches (strings, chars, etc)
                } catch (InputMismatchException exception) {
                    roomScanner.nextLine();
                    System.out.println("You have entered an invalid choice. Please try again.");
                    continue;
                }

            } else {
                System.out.println("You seem to be alone in this room.");
                System.out.println("What do you do?");
                System.out.println("1. Inspect");
                System.out.println("2. Leave");

                try {
                    choice = roomScanner.nextInt();

                    switch (choice) {

                        /// INSPECT
                        case 1:
                            inspect(pc, false);
                            continue;
                            ///

                        /// LEAVE
                        case 2:
                            return true;
                            ///

                            /// DEFAULT
                        default:
                            System.out.println("You have entered an invalid choice. Please try again.");
                            continue;
                    }

                    // catch input mismatches (strings, chars, etc)
                } catch (InputMismatchException exception) {
                    roomScanner.nextLine();
                    System.out.println("You have entered an invalid choice. Please try again.");
                    continue;
                }

            }

        }

        if (this.isCleared()) {
            return true;
        } else {
            return false;
        }

    }

    public boolean hide(PlayerCharacter pc) {

        /// Method to handle the choice of hiding in enterRoom()
        // Returns true if the player successfully hides--false if not

        // instantiate some ints
        int tierToBeat = 0;
        int monsterPerception = 0;

        // Loop through the monsters in the room to find the highest tier of monster, and save its stats.
        for (int i = 0; i < this.getMonsters().size(); i++) {
            if (this.getMonsters().get(i).getTier() > tierToBeat) {
                tierToBeat = this.getMonsters().get(i).getTier();
                monsterPerception = (tierToBeat * 5) * this.getMonsters().get(i).getType();
            }
        }

        // Roll dice
        int rng = (int)(Math.random() * 20) + 1;

        // If the dice roll + the player's stealth score beats the perception of the highest monster in the room,
        // the player has successfully hidden. If not, combat!
        if ((rng + pc.getStealth()) > (rng + monsterPerception)) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("You step into the shadows, finding a place away from prying eyes.");
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("You can move around the room now--unseen.");
            return true;
        } else {
            return false;
        }

    }

    public void rollInitiative(PlayerCharacter pc) {

        // roll initiative:
        // player stat based on stealth, smarts, and level,
        // monster stat based on tier and type (warriors slowest, archers faster, mages fastest)

        // Player initiative: level + smarts and stealth divided by ten + random roll between 1 and 20
        double playerInit =
                (pc.getLevel()
                        + (pc.getSmarts() / 10)
                        + (pc.getStealth() / 10)
                        + ((Math.random() * 30) + 1));
        pc.setInitiative(playerInit);

        // iterate through the monsters in the room
        for (int i = 0; i < this.getMonsters().size(); i++) {

            // Monster initiative: tier + type + random roll between and 1 and 20
            double monsterInit =
                    (this.getMonsters().get(i).getTier()
                            + this.getMonsters().get(i).getType()
                            + ((Math.random() * 30) + 1));
            this.getMonsters().get(i).setInitiative(monsterInit);

            // This calculation results in a level 1 warrior rolling anywhere from 8 to 38, more if they're a mage or rogue
            // and low level monsters rolling anywhere from 3 to 33, more if they're an archer or mage
            // At high levels, a player might roll anywhere from 14 to 34+, more if they're a mage or rogue
            // and high level monsters rolling anywhere from 6 to 36, more if they're an archer or mage
            // This keeps the initiative heavily weighted toward the player on purpose: there are more of them than there are of you

        }

        /// END

    }

    public void generateMonsters(PlayerCharacter pc) {

        // Monster Stat calculations for BASE stats as defined in database and .CSV files
        // before multiplication and randomization:

        // BASE Health and Damage begin at 10, Armor begins at 1

        // Melee monsters get +1 to armor, damage, and health for every tier after 1
        // Archer monsters get +2 to damage for every tier after 1
        // Mage monsters get +3 to damage for every tier after 1 and -1 to health for every tier UNDER 5

        // Stats do not decrease more after tier 5
        // Stats do not increase past 20 points (before multiplication and randomization)
        // Health and damage do not decrease past 1
        // Armor does not decrease past 1

        /// Different themes have different bonuses and maluses applied to all monsters within:
        // Skeletons receive +1 damage but -1 armor
        // Spiders receive +1 damage but -1 health
        // Oozes receive +1 armor but -1 damage
        // Zombies have no bonuses or maluses
        // Bandits have no bonuses or maluses
        // Automatons receive a +2 to armor and a +1 to damage
        // Goblins receive a -1 to health
        // Void Creatures receive a +1 to all stats
        // Vampires receive a +1 to health
        // Beasts receive no bonuses or maluses
        // Barbarians receive a +2 to damage, a +1 to health, and a -2 to armor
        // Golems receive a +3 to health and armor but a -1 to damage
        // Demons receive a +1 to health and damage
        // Sorcerers receive no bonuses or maluses

        // Monster health and damage are multiplied by (tier + player level)
        // then randomized by anywhere between -10 and +10
        // Armor is multiplied by tier
        // then randomized by anywhere between -5 and +5

        ///

        // Instantiate things we're about to use
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        this.setMonsters(new ArrayList<>());

        try {
            // Load MySQL driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Set up the connection with the DB
            connection = DriverManager
                    .getConnection(connectionString);
            statement = connection.createStatement();

            // Loop to generate monsters with a dynamic SQL statement
            for (int i = 0; i < 4; i++) {
                // Generate a number between 1 and 3 to randomly draw from the three enemy classes
                int generatedType = (int) ((Math.random() * 3) + 1);
                // Generate a number for a 1 in 3 chance to add or subtract 1 from the Tiers to pull from, for some wiggle room
                int generatedModifier = (int) ((Math.random() * 3) + 1);
                // If statement to interpret the result as -1, 0, or +1
                if (generatedModifier == 1) {
                    generatedModifier = -1;
                } else if (generatedModifier == 2) {
                    generatedModifier = 0;
                } else if (generatedModifier == 3) {
                    generatedModifier = 1;
                }

                resultSet = statement
                        .executeQuery("select * from dungeoncrawler."
                                + this.getTheme()
                                + " WHERE Tier between 1 and " + (pc.getLevel() + generatedModifier)
                                + " AND Type = " + generatedType
                                + " LIMIT " + pc.getLevel());

                // loop through the result set while there are more records
                while (resultSet.next()) {

                    // Get monster stats
                    String name = resultSet.getString("Name");
                    String description = resultSet.getString("Description");
                    int type = resultSet.getInt("Type");
                    int tier = resultSet.getInt("Tier");
                    int health = resultSet.getInt("Health");
                    int damage = resultSet.getInt("Damage");
                    int armor = resultSet.getInt("Armor");

                    // Multiply health and damage by player level and monster tier,
                    // multiply armor by tier
                    health = (health * (pc.getLevel() + tier));
                    damage = (damage * (pc.getLevel() + tier));
                    armor = (armor * (tier));

                    // Random number generator between 1 and 20
                    // + monster tier so higher level monsters have a greater chance of higher stats
                    int chancePositive = (int)((Math.random() * 20) + 1) + tier;

                    // If the generated number is between 10 and 20,
                    // we ADD a random number between 1 and 10 to health and damage
                    // and a random number between 1 and 5 to armor.
                    // If the generated number is between 1 and 10,
                    // we REMOVE a random number between 1 and 10 fom health and damage
                    // and a random number between 1 and 5 from armor
                    if (chancePositive > 10) {
                        health += (int)(Math.random() * 10) + 1;
                        damage += (int)(Math.random() * 10) + 1;
                        armor += (int)(Math.random() * 5) + 1;
                    } else {
                        health -= (int)(Math.random() * 10) + 1;
                        damage -= (int)(Math.random() * 10) + 1;
                        armor -= (int)(Math.random() * 5) + 1;
                    }

                    // If any of these calculations have resulted in a stat that is a zero or a negative number,
                    // set it to 1.
                    if (health <= 0) {
                        health = 1;
                    }
                    if (damage <= 0) {
                        damage = 1;
                    }
                    if (armor <= 0) {
                        armor = 1;
                    }

                    this.getMonsters().add(new Monster(name, description, this.getTheme(), type, tier, health, damage, armor));

//                    // Print the monster's good, good stats FOR TESTING ONLY
//                    System.out.println("There is a " + name + ". "
//                            + "\n "
//                            + description
//                            + "\n"
//                            + " It is Type " + type
//                            + ", Tier " + tier
//                            + ", and has " + health + " Health, "
//                            + damage + " Damage, and "
//                            + armor + " Armor.");
                }
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

        ///

        /// Monster Themes and Tiers
        //The higher the tier the stronger, with bosses being the strongest.
        //MEL, ARC, MAG = melee, archer, magic--determines movetypes

        // Skeletons
//        String skel_T1_MEL_Name = "Skeleton Shambler";
//        String skel_T2_ARC_Name = "Skeleton Archer";
//        String skel_T2_MEL_Name = "Skeleton Warrior";
//        String skel_T3_MAG_Name = "Skeleton Mage";
//        String skel_T4_MEL_Name = "Skeleton Knight";
//        String skel_T5_MEL_Name = "Skeleton Commander";
//        String skel_T6_MEL_Name = "Skeleton King";
//        String skel_T6_MAG_Name = "Lich";
//        String skel_BOSS_MAG_Name = "The Lich King";
//        String skel_BOSS_MEL_Name = "The King of the Dead";
//        String skel_BOSS_MEL_Name_02 = "The Reaper";
//
//        // Spiders
//        String spid_T1_ARC_Name = "Giant Spiderling Spitter";
//        String spid_T1_MEL_Name = "Giant Spiderling";
//        String spid_T2_ARC_Name = "Giant Spider Spitter";
//        String spid_T2_MEL_Name = "Giant Spider";
//        String spid_T3_MEL_Name = "Giant Spider Warrior";
//        String spid_T4_MAG_Name = "Giant Spider Brood Mother";
//        String spid_BOSS_MAG_Name = "Her";
//
//        // Oozes
//        String ooze_T1_MEL_Name = "Ooze Lumpling";
//        String ooze_T2_MEL_Name = "Ooze Warrior";
//        String ooze_T3_MAG_Name = "Ooze Mother";
//        String ooze_BOSS_MAG_Name = "The Sludge of Suffering";
//
//        // Undead
//        String zomb_T1_MEL_Name = "Undead Crawler";
//        String zomb_T2_MEL_Name = "Undead Shuffler";
//        String zomb_T3_MEL_Name = "Undead Walker";
//        String zomb_T4_MEL_Name = "Undead Abomination";
//        String zomb_BOSS_MEL_Name = "The Shambling Horror";
//        String zomb_BOSS_MAG_Name_02 = "The Lumbering Host";
//
//        // Bandits
//        String band_T1_ARC_Name = "Bandit Wastrel";
//        String band_T1_MEL_Name = "Bandit Ruffian";
//        String band_T2_ARC_Name = "Bandit Archer";
//        String band_T2_MEL_Name = "Bandit Thug";
//        String band_T3_MEL_Name = "Bandit Hound";
//        String band_T4_MEL_Name = "Bandit Barbarian";
//        String band_T4_MAG_Name = "Bandit Mage";
//        String band_T5_MEL_Name = "Bandit Warlord";
//        String band_BOSS_MEL_Name = "The Bloody Baron";
//
//        // Automatons
//        String auto_T1_MEL_Name = "Automaton Worker";
//        String auto_T2_ARC_Name = "Automaton Watcher";
//        String auto_T2_MEL_Name = "Automaton Warrior";
//        String auto_T3_ARC_Name = "Automaton Sentry";
//        String auto_T3_MEL_Name = "Automaton Guardian";
//        String auto_T4_ARC_Name = "Automaton Sentinel";
//        String auto_T4_MEL_Name = "Automaton Centurion";
//        String auto_T5_ARC_Name = "Automaton Grenadier";
//        String auto_T5_MEL_Name = "Automaton Hulk";
//        String auto_T5_MAG_Name = "Automaton Spell-Constructor";
//        String auto_T6_MEL_Name = "Automaton Colossus";
//        String auto_BOSS_MEL_Name = "Churning In The Deep";
//        String auto_BOSS_MAG_Name = "The Silver Matriarch";
//
//        // Goblins
//        String gob_T1_MEL_Name = "Goblin Peon";
//        String gob_T2_ARC_Name = "Goblin Slinger";
//        String gob_T2_MEL_Name = "Goblin Warrior";
//        String gob_T3_ARC_Name = "Goblin Archer";
//        String gob_T3_MEL_Name = "Goblin Berserker";
//        String gob_T4_ARC_Name = "Goblin Bomber";
//        String gob_T4_MAG_Name = "Goblin Shaman";
//        String gob_T4_MEL_Name = "Goblin Chief";
//        String gob_T5_MEL_Name = "Cave Troll";
//        String gob_BOSS_MAG_Name = "The High Priestess of Void's Light";
//
//        // Void Creatures
//        //lategame enemy; starts at higher tier
//        String void_T4_MEL_Name = "Void Husk";
//        String void_T5_MAG_Name = "Void Spawn";
//        String void_T6_MAG_Name = "Void Horror";
//        String void_T7_MAG_Name = "Void Terror";
//        String void_T8_MAG_Name = "Void Harbinger";
//        String void_T9_MAG_Name = "Void ";
//        String void_BOSS_MAG_Name = "The Hunger";
//        String void_BOSS_MAG_Name_02 = "Void Itself";
//
//        // Vampires
//        String vamp_T1_MEL_Name = "Vampire Thrall";
//        String vamp_T2_MAG_Name = "Vampire Familiar";
//        String vamp_T2_MEL_Name = "Vampire Hound";
//        String vamp_T3_MAG_Name = "Vampire Adept";
//        String vamp_T4_MEL_Name = "Vampire Duelist";
//        String vamp_T5_MAG_Name = "Vampire Master";
//        String vamp_T6_MAG_Name = "Vampire Warlock";
//        String vamp_T7_MAG_Name = "Vampire Lord";
//        String vamp_T8_MAG_Name = "Vampire King";
//        String vamp_T9_MEL_Name = "Vampire Demon-King";
//        String vamp_BOSS_MAG_Name = "The Red Lady";
//
//        // Beasts
//        String beast_T1_MEL_Name = "Wild Cub";
//        String beast_T2_MEL_Name = "Wild Mange-Beast";
//        String beast_T3_MEL_Name = "Wild Beast";
//        String beast_T4_MEL_Name = "Wild Beast Alpha";
//        String beast_T5_MEL_Name = "Wild Beast Pack-Mother";
//        String beast_BOSS_MEL_Name = "No-Mane";
//
//        // Barbarians
//        String wild_T1_MEL_Name = "Wild-Man Brawler";
//        String wild_T1_ARC_Name = "Wild-Man Slinger";
//        String wild_T2_ARC_Name = "Wild-Man Ax-Thrower";
//        String wild_T3_MEL_Name = "Wild-Man Raider";
//        String wild_T4_MEL_Name = "Wild-Man Madcap";
//        String wild_T5_MEL_Name = "Wild-Man Barbarian";
//        String wild_T6_MEL_Name = "Wild-Man Raidleader";
//        String wild_BOSS_MEL_Name = "The Horn of Hell";
//
//        // Golems
//        String golem_T1_MEL_Name = "Mud Golem";
//        String golem_T1_MAG_Name = "Mud Golem Spellslinger";
//        String golem_T2_MEL_Name = "Rock Golem";
//        String golem_T2_MAG_Name = "Rock Golem Spellslinger";
//        String golem_T3_MEL_Name = "Ice Golem";
//        String golem_T3_MAG_Name = "Ice Golem Spellslinger";
//        String golem_T3_MEL_Name_02 = "Storm Golem";
//        String golem_T3_MAG_Name_02 = "Storm Golem Spellslinger";
//        String golem_T3_MEL_Name_03 = "Fire Golem";
//        String golem_T3_MAG_Name_03 = "Fire Golem Spellslinger";
//        String golem_T4_MAG_Name = "Void Golem";
//        String golem_BOSS_MAG_Name = "Void Incarnate";
//        String golem_BOSS_MAG_Name_02 = "Stone Incarnate";
//        String golem_BOSS_MAG_Name_03 = "Ice Incarnate";
//        String golem_BOSS_MAG_Name_04 = "Storm Incarnate";
//        String golem_BOSS_MAG_Name_05 = "Fire Incarnate";
//
//        // Demons
//        String demon_T1_MEL_Name = "Imp";
//        String demon_T2_ARC_Name = "Demon Arbalest";
//        String demon_T2_MEL_Name = "Demon Spawn";
//        String demon_T2_MAG_Name = "Demon ";
//        //more
//
//        // Sorcerers
//        String mage_T1_MAG_Name = "Novice Ice Mage";
//        String mage_T1_MAG_Name_02 = "Novice Storm Mage";
//        String mage_T1_MAG_Name_03 = "Novice Fire Mage";
//        String mage_T2_MAG_Name = "Apprentice Ice Mage";
//        String mage_T2_MAG_Name_02 = "Apprentice Storm Mage";
//        String mage_T2_MAG_Name_03 = "Apprentice Fire Mage";
//        String mage_T3_MAG_Name = "Adept Ice Mage";
//        String mage_T3_MAG_Name_02 = "Adept Storm Mage";
//        String mage_T3_MAG_Name_03 = "Adept Fire Mage";
//        String mage_T4_MAG_Name = "Master Ice Mage";
//        String mage_T4_MAG_Name_02 = "Master Storm Mage";
//        String mage_T4_MAG_Name_03 = "Master Fire Mage";
//        String mage_T5_MAG_Name = "Conjurer";
//
//        // Ghosts
//        String ghost_T1_;
//
//        // Uniques
//        String uniq_Name_01 = "Moolahg the Troll";
//        String uniq_Name_02 = "Meezer the Trickster Demon";

    }

    public void doBattle(PlayerCharacter pc) {

        // Recalculate player stats just in case
        pc.recalculateAttributes();

        // Populate player's movelist just in case
        pc.populateMoves();

        // Primary controller loop; battle runs until either:
        // a) the player is dead
        // b) all the monsters in the room are dead
        // c) the player flees (breaks the loop from within)
        while (pc.getHealth() > 0 && !this.getMonsters().isEmpty() && this.getDeadCount() < this.getMonsters().size()) {

            if (this.getMonsters().isEmpty() ^ this.getDeadCount() >= this.getMonsters().size()) {
                break;
            }

            // flavor!
            System.out.println("Your enemies have been alerted to your presence!");

            // a magic boolean
            boolean combat = true;

            // magic counting integer
            int playerTurnCount = 0;

            rollInitiative(pc);

            while (combat && pc.getHealth() > 0) {

                /// Handle death with redundancy
                if (pc.getHealth() <= 0) {
                    if (pc.youDied(pc)) {
                        DungeonCrawlController.endGame(pc);
                    }

                }

                // for loop for combat turns in a single round
                // Every enemy in the list should get a chance to go,
                // but the player gets to go first before the enemy if their initiative score is higher.
                // THEN the enemy goes.

                // The player stops receiving turns in a single round if they have gone
                // as many times as there are enemies in the room / 2

                for (int i = 0; i < this.getMonsters().size(); i++) {
                    if (!this.getMonsters().isEmpty()
                            && this.getDeadCount() < this.getMonsters().size()
                            && this.getMonsters().get(i).getInitiative() <= pc.getInitiative()
                            && playerTurnCount <= (this.getMonsters().size() / 2)) {
                        playerTurnCount++;
                        this.playerTurn(pc);
                        if (!this.getMonsters().isEmpty()
                                && this.getDeadCount() < this.getMonsters().size()
                                && this.getMonsters().get(i) != null
                                && !this.getMonsters().get(i).isDead()) {
                            this.enemyTurn(pc, this.getMonsters().get(i));
                        } else if (!this.getMonsters().isEmpty()
                                && this.getDeadCount() < this.getMonsters().size()
                                && !this.getMonsters().get(i).isDead()) {
                            this.enemyTurn(pc, this.getMonsters().get(i));
                        }
                    } else {
                        if (!this.getMonsters().isEmpty()
                                && this.getDeadCount() < this.getMonsters().size()
                                && this.getMonsters().get(i) != null
                                && !this.getMonsters().get(i).isDead()) {
                            this.enemyTurn(pc, this.getMonsters().get(i));
                        } else if (!this.getMonsters().isEmpty()
                                && this.getDeadCount() < this.getMonsters().size()
                                && !this.getMonsters().get(i).isDead()) {
                            this.enemyTurn(pc, this.getMonsters().get(i));
                        }
                        playerTurnCount++;
                        this.playerTurn(pc);
                    }

                    // Clear flags set during the round at the end of the round
                    playerTurnCount = 0;
                    pc.clearFlags();
                    for (int j = 0; j < this.getMonsters().size(); j++) {
                        this.getMonsters().get(j).clearFlags();
                    }

                    if (this.getDeadCount() == this.getMonsters().size()) {
                        System.out.println("You have defeated your enemies!");
                        pc.levelUp();
                        this.setCleared(true);
                        combat = false;
                    }

                }

            }

        }

        // Make sure it's all cleared!
        pc.clearFlags();

    }

    public void playerTurn(PlayerCharacter pc) {

        /// Handle the player turn in combat

        /// Handle death with redundancy
        if (pc.getHealth() <= 0) {
            if (pc.youDied(pc)) {
                DungeonCrawlController.endGame(pc);
            }

        }

        // Create a scanner to be used throughout the battle
        Scanner battleScanner = new Scanner(System.in);

        System.out.println("It is your turn.");

        // controller boolean
        boolean acting = true;

        while (acting && pc.getHealth() > 0) {

            /// Handle death with redundancy
            if (pc.getHealth() <= 0) {
                if (pc.youDied(pc)) {
                    DungeonCrawlController.endGame(pc);
                }
            }

            // Break loop if we somehow got in here while the room is empty
            if (this.getMonsters().isEmpty() ^ this.getDeadCount() >= this.getMonsters().size()) {
                break;
            }

            // Print out the toString() of each monster in the room, if it's not null and the player is alive.
            if (this.getMonsters() != null
                    && !this.getMonsters().isEmpty()
                    && this.getDeadCount() < this.getMonsters().size()
                    && pc.getHealth() > 0) {
                System.out.println("There are " + (this.getMonsters().size() - this.getDeadCount()) + " enemies before you.");
                for (int i = 0; i < this.getMonsters().size(); i++) {
                    if (!this.getMonsters().get(i).isDead()) {
                        System.out.println(this.getMonsters().get(i).toString());
                    }
                }
            }

            System.out.println("What would you like to do?");
            System.out.println("1. Attack");
            System.out.println("2. Defend");
            System.out.println("3. Flee");
            System.out.println("4. Inspect");

            try {
                int choice = battleScanner.nextInt();

                switch (choice) {

                    /// ATTACK
                    case 1:
                        if (attack(pc)) {
                            break;
                        } else {
                            continue;
                        }

                        ///

                        /// DEFEND
                    case 2:
                        if (defend(pc)) {
                            break;
                        } else {
                            continue;
                        }

                        ///

                        /// FLEE
                    case 3:
                        if (flee(pc)) {
                            return;
                        } else {
                            continue;
                        }

                        ///

                        /// INSPECT
                    case 4:
                        if (inspect(pc, false)) {
                            break;
                        } else {
                            continue;
                        }

                        ///

                        /// DEFAULT
                    default:
                        System.out.println("You have entered an invalid choice. Please try again.");
                        continue;
                }

                // catch input mismatches (strings, chars, etc)
            } catch (InputMismatchException exception) {
                battleScanner.nextLine();
                System.out.println("You have entered an invalid choice. Please try again.");
                continue;
            }

            acting = false;

        }

    }

    public boolean attack(PlayerCharacter pc) {

        /// Method to handle the ATTACK choice in playerTurn()
        // Returns TRUE if the player has taken an action in the method.

        // boolean to control the loop
        boolean attacking = true;

        // set up a scanner to be used throughout the method
        Scanner attackScanner = new Scanner(System.in);
        int attackChoice;

        // The main loop
        while (attacking) {
            System.out.println("Which foe will you strike?");
            for (int i = 0; i < this.getMonsters().size(); i++) {
                if (!this.getMonsters().get(i).isDead()) {
                    System.out.println(i + 1 + ". " + this.getMonsters().get(i).getName()
                            + ": " + this.getMonsters().get(i).getHealth() + " Health");
                }
            }
            System.out.println("If you do not wish to attack, type \"0\" to change your decision.");

            try {
                attackChoice = attackScanner.nextInt();

                // Send the player back to doBattle() if they change their mind
                if (attackChoice == 0) {
                    attacking = false;
                    return false;
                }

                // Another boolean to handle another loop
                boolean moving = true;

                // Loop to handle the inputs for which move the player would like to take
                while (moving && pc.getHealth() > 0) {

                    if (this.getMonsters().get((attackChoice - 1)) != null) {
                        System.out.println("Which way will you attack?");
                        for (int i = 0; i < pc.getMoves().size(); i++) {
                            System.out.println(i + 1 + ". " + pc.getMoves().get(i).toString());
                        }
                        System.out.println("If you do not wish to attack this foe, type \"0\" to change your decision.");

                        try {
                            int moveChoice = attackScanner.nextInt();

                            // If they change their mind, send the player back to where they choose which enemy to attack
                            // by forcing an early iteration of the loop
                            if (moveChoice == 0) {
                                attackScanner.nextLine();
                                break;
                                // Make sure the move choice is valid
                            } else if ((pc.getMoves().get((moveChoice - 1)) != null)) {
                                // Do the move, call methods and calculate damage.
                                // If it returns true, the monster is slain and set as dead
                                // and the initiative order
                                if (playerDoDamage(pc,
                                        this.getMonsters().get((attackChoice - 1)),
                                        pc.getMoves().get((moveChoice - 1)))) {
                                    this.getMonsters().get((attackChoice - 1)).setDead(true);
                                    this.setDeadCount(this.getDeadCount() + 1);
                                }

                                // End loop and return true to the main doBattle method so the player's turn ends
                                return true;

                            } else {
                                attackScanner.nextLine();
                                System.out.println("You have entered an invalid choice. Please try again.");
                            }

                        } catch (InputMismatchException | IndexOutOfBoundsException exception) {
                            attackScanner.nextLine();
                            System.out.println("You have entered an invalid choice. Please try again.");
                        }

                    }

                }

            } catch (InputMismatchException | IndexOutOfBoundsException exception) {
                attackScanner.nextLine();
                System.out.println("You have entered an invalid choice. Please try again.");
            }


        }

        // Return false if we've somehow arrived at this line of code
        // so that the main doBattle method will allow the player to act still
        return false;

    }

    public boolean defend(PlayerCharacter pc) {

        /// Method to handle the DEFEND choice in playerTurn()

        // boolean to control the loop
        boolean defending = true;

        // set up a scanner to be used throughout the method
        Scanner defendScanner = new Scanner(System.in);

        while (defending && pc.getHealth() > 0) {
            System.out.println("Which way will you defend yourself?");
            System.out.println("1. Against melee attacks");
            System.out.println("2. Against ranged attacks");
            System.out.println("3. Against magic attacks");
            System.out.println("If you do not wish to defend yourself, type \"0\" to change your decision.");

            try {
                int defendChoice = defendScanner.nextInt();

                // Switch statement to handle input and set appropriate flags in pc class
                switch (defendChoice) {
                    case 1:
                        System.out.println("You take a defensive stance, guarding yourself against oncoming blows.");
                        System.out.println("You will take half damage from melee attacks this round.");
                        pc.setDefendMelee(true);
                        defending = false;
                        return true;
                    case 2:
                        System.out.println("You set your feet wide, a watchful eye looking out for projectiles.");
                        System.out.println("You will take half damage from ranged attacks this round.");
                        pc.setDefendRanged(true);
                        defending = false;
                        return true;
                    case 3:
                        System.out.println("You steel your mind and your spirit to ward off foul magics.");
                        System.out.println("You will take half damage from magic attacks this round.");
                        pc.setDefendMagic(true);
                        defending = false;
                        return true;
                    case 0:
                        return false;
                    default:
                        System.out.println("You have entered an invalid choice. Please try again.");
                        continue;
                }

            } catch (InputMismatchException exception) {
                defendScanner.nextLine();
                System.out.println("You have entered an invalid choice. Please try again.");
            }

        }

        return false;

    }

    public boolean flee(PlayerCharacter pc) {

        /// Method to handle the FLEE choice in playerTurn()

        boolean fleeing = true;

        Scanner fleeScanner = new Scanner(System.in);

        while (fleeing && pc.getHealth() > 0) {
            System.out.println("Are you sure you wish to flee? Your foes will have a chance to strike you as you drop your guard.");
            System.out.println("1. Yes");
            System.out.println("2. No");

            try {
                int fleeChoice = fleeScanner.nextInt();

                switch(fleeChoice) {

                    // YES
                    case 1:
                        // rng chance to be hit based on enemy tier

                        // initialize an int
                        int hitChance;

                        // initialize a boolean--the player may only be hit once while fleeing
                        boolean hasHit = false;

                        // iterate through monsters generating a chance, augmenting with their tier
                        while (!hasHit && pc.getHealth() > 0) {
                            for (int i = 0; i < this.getMonsters().size(); i++) {
                                hitChance = (((int)(Math.random() * 20)) + 1) + this.getMonsters().get(i).getTier();
                                if (hitChance > 10) {
                                    int damage = this.getMonsters().get(i).getDamage();
                                    pc.setHealth(pc.getHealth() - damage);
                                    System.out.println(this.getMonsters().get(i).getName() + " hit you as you flee for " + damage + " damage!");
                                    hasHit = true;
                                    return true;
                                }
                            }
                            if (!hasHit) {
                                System.out.println("You deftly escape your enemies!");
                                return true;
                            }
                        }

                    // NO
                    case 2:
                        return false;

                    // INVALID INPUT
                    default:
                        fleeScanner.nextLine();
                        System.out.println("You have entered an invalid choice. Please try again.");
                        continue;
                }

            // catch wrong input
            } catch (InputMismatchException exception) {
                fleeScanner.nextLine();
                System.out.println("You have entered an invalid choice. Please try again.");
                continue;
            }

        }

        // send the player outta here if we get to this point without properly handling it
        return true;

    }

    public boolean inspect(PlayerCharacter pc, boolean hiding) {

        /// Method to handle inspecting choices in various caller methods.
        // Returns true if the player takes an action that would constitute a turn in combat

        boolean inspecting = true;

        Scanner inspectScanner = new Scanner(System.in);

        while (inspecting) {

            System.out.println("What would you like to inspect?");
            System.out.println("1. Your gear");
            System.out.println("2. Your stats");
            System.out.println("3. Your enemies");
            System.out.println("4. The room");
            System.out.println("If you do not wish to inspect anything, type \"0\" to change your decision.");

            try {

                int inspectChoice = inspectScanner.nextInt();

                switch (inspectChoice) {

                    case 1:
                        if (pc.inspectGear()) {
                            break;
                        } else {
                            continue;
                        }
                    case 2:
                        System.out.println(pc.stats());
                        continue;
                    case 3:
                        if (!this.getMonsters().isEmpty() && this.getDeadCount() < this.getMonsters().size()) {
                            for (int i = 0; i < this.getMonsters().size(); i++) {
                                if (!this.getMonsters().get(i).isDead()) {
                                    System.out.println(this.getMonsters().get(i).toString());
                                }
                            }
                        } else if (this.getMonsters().isEmpty() ^ this.getMonsters() == null ^ this.getDeadCount() >= this.getMonsters().size()) {
                            System.out.println("You have no foes in this room!");
                            continue;
                        }
                        continue;
                    case 4:
                        System.out.println("You are in " + this.getName() + ".");
                        System.out.println(this.getDescription());

                        /// if there is cool loot, inform the player
                        // if the room is empty of monsters, let them retrieve said loot

                        /// TRINKET
                        if (this.getRoomTrinket() != null) {
                            System.out.println("You spot a trinket in the room.");
                            if (this.getMonsters().isEmpty() ^ this.getDeadCount() >= this.getMonsters().size() ^ hiding) {
                                System.out.println("Would you like to collect it?");
                                System.out.println("1. Yes");
                                System.out.println("2. No");
                                Scanner trinketScanner = new Scanner(System.in);
                                boolean deciding = true;
                                while (deciding) {
                                    try {
                                        int decision = trinketScanner.nextInt();

                                        switch (decision) {
                                            case 1:
                                                Trinket tempTrinket = pc.getTrinket();
                                                if (this.getRoomTrinket().trinketCollected(pc)) {
                                                    this.setRoomTrinket(tempTrinket);
                                                }
                                                break;
                                            case 2:
                                                break;
                                        }

                                    } catch (InputMismatchException exception) {
                                        trinketScanner.nextLine();
                                        System.out.println("You have entered an invalid choice. Please try again.");
                                    }
                                }
                            }
                        } else {
                            System.out.println("You see no trinkets worth taking in this room.");
                        }

                        /// WEAPON
                        if (this.getRoomWeapon() != null) {
                            System.out.println("You spot a weapon in the room.");
                            if (this.getMonsters().isEmpty() ^ this.getDeadCount() >= this.getMonsters().size() ^ hiding) {
                                System.out.println("Would you like to collect it?");
                                System.out.println("1. Yes");
                                System.out.println("2. No");
                                Scanner weaponScanner = new Scanner(System.in);
                                boolean deciding = true;
                                while (deciding) {
                                    try {
                                        int decision = weaponScanner.nextInt();

                                        switch (decision) {
                                            case 1:
                                                Weapon tempWeapon = pc.getWeapon();
                                                if (this.getRoomWeapon().weaponCollected(pc)) {
                                                    this.setRoomWeapon(tempWeapon);
                                                }
                                                break;
                                            case 2:
                                                break;
                                        }

                                    } catch (InputMismatchException exception) {
                                        weaponScanner.nextLine();
                                        System.out.println("You have entered an invalid choice. Please try again.");
                                    }
                                }
                            }
                        } else {
                            System.out.println("You see no weapons worth taking in this room.");
                        }

                        /// ARMOR
                        if (this.getRoomArmor() != null) {
                            System.out.println("You spot some armor in the room.");
                            if (this.getMonsters().isEmpty() ^ this.getDeadCount() >= this.getMonsters().size() ^ hiding) {
                                System.out.println("Would you like to collect it?");
                                System.out.println("1. Yes");
                                System.out.println("2. No");
                                Scanner armorScanner = new Scanner(System.in);
                                boolean deciding = true;
                                while (deciding) {
                                    try {
                                        int decision = armorScanner.nextInt();

                                        switch (decision) {
                                            case 1:
                                                Armor tempArmor = pc.getArmor();
                                                if (this.getRoomArmor().armorCollected(pc)) {
                                                    this.setRoomArmor(tempArmor);
                                                }
                                                break;
                                            case 2:
                                                break;
                                        }

                                    } catch (InputMismatchException exception) {
                                        armorScanner.nextLine();
                                        System.out.println("You have entered an invalid choice. Please try again.");
                                    }
                                }
                            }
                        } else {
                            System.out.println("You see no armor worth taking in this room.");
                        }

                        /// POTIONS
                        if (this.getPotions() > 0) {
                            System.out.println("You spot some potions in the room.");
                            if (this.getMonsters().isEmpty() ^ this.getDeadCount() >= this.getMonsters().size() ^ hiding) {
                                System.out.println("Would you like to collect the potions?");
                                System.out.println("1. Yes");
                                System.out.println("2. No");
                                Scanner potionScanner = new Scanner(System.in);
                                boolean deciding = true;
                                while (deciding) {
                                    try {
                                        int decision = potionScanner.nextInt();

                                        switch (decision) {
                                            case 1:
                                                pc.setPotions(pc.getPotions() + this.getPotions());
                                                break;
                                            case 2:
                                                break;
                                        }

                                    } catch (InputMismatchException exception) {
                                        potionScanner.nextLine();
                                        System.out.println("You have entered an invalid choice. Please try again.");
                                    }
                                }
                            }
                        } else {
                            System.out.println("You see no potions in this room.");
                        }

                        continue;
                    case 0:
                        return false;
                    default:
                        System.out.println("You have entered an invalid choice. Please try again.");
                        continue;

                }

                inspecting = false;
                break;

            } catch (InputMismatchException exception) {
                inspectScanner.nextLine();
                System.out.println("You have entered an invalid choice. Please try again.");
            }

        }

        return true;

    }

    public boolean playerDoDamage(PlayerCharacter pc, Monster monster, Move move) {

        /// Method to calculate how much damage a PC does to a monster with a move in combat
        // Returns true if the monster passed in is slain at the end; false if it survives

        // Initialize a variable to assign a calculated value to later for cleaner code
        int damage = 0;

        // rng calculation for if it's a crit--Strength for Warriors, Smarts for Sorcerers, Stealth (x2) for Thieves
        double critChance = 0;
        if (Objects.equals(pc.getCharacterClass(), "Warrior")) {
            critChance = ((Math.random() * 100) + 1) + (pc.getStrength() / 10);
        } else if (Objects.equals(pc.getCharacterClass(), "Sorcerer")) {
            critChance = ((Math.random() * 100) + 1) + (pc.getSmarts() / 10);
        } else if (Objects.equals(pc.getCharacterClass(), "Thief")) {
            critChance = ((Math.random() * 100) + 1) + ((pc.getStealth() / 10) * 2);
        }

        // If the move is melee or ranged, get the player character's attack damage
        // If the move ignores armor, don't subtract monster armor rating
        if (move.getType() == 1 | move.getType() == 2) {
            if (move.doesIgnoreArmor()) {
                damage = (pc.getDamage() + move.getDamageModifier());
            } else {
                damage = (pc.getDamage() + move.getDamageModifier() - monster.getArmor());
            }

        // If the move is a spell, get the player character's spell attack damage
        // If the move ignores armor, don't subtract monster armor rating
        } else if (move.getType() == 3) {
            if (move.doesIgnoreArmor()) {
                damage = (pc.getSpellDamage() + move.getDamageModifier());
            } else {
                damage = (pc.getSpellDamage() + move.getDamageModifier() - monster.getArmor());
            }
        }

        // If it's a critical hit (rng + modifier making a number between 1 and 200 be equal to or over 100), double the damage
        if (critChance >= 90) {
            damage = damage * 2;
        }

        // If the monster is defending itself, halve the damage
        if (monster.isDefending()) {
            damage = damage / 2;
        }

        // If the calculated damage value is a negative, set it to zero so no damage or healing is done inadvertently
        if (damage < 0) {
            damage = 0;
        }

        // Apply damage
        monster.setHealth((monster.getHealth() - damage));

        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        // Inform the player how it went
        System.out.println("You attack " + monster.getName()
                + " with " + move.getName()
                + " and deal " + damage + " damage!");
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }


        // Attack was a critical hit
        if (critChance >= 90) {
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("It was a critical hit, dealing double damage!");
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        // Monster was defending
        if (monster.isDefending()) {
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println(monster.getName() + " defended against your attack, halving its damage!");
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        // Attack dealt no damage
        if (damage == 0) {
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Its armor overcame your attack!");
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        // Monster is killed
        } else if (monster.getHealth() <= 0) {
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("It is a mortal blow!");
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("You have slain " + monster.getName() + ".");
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        // Monster is still alive
        } else {
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("It has " + monster.getHealth() + " Health remaining.");
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        // Finally, return true or false if the monster is dead or alive, respectively:
        if (monster.getHealth() <= 0) {
            return true;
        } else {
            return false;
        }

        /// END

    }

    public void enemyTurn(PlayerCharacter pc, Monster monster) {

        /// Handle a monster's turn in combat!
        // Very simply, the monster will do one of three things, evaluated in reverse order:
        // 1. Attack
        // 2. Defend
        // 3. Heal

        // Future updates may expand this to calling for reinforcements (summoning new ones as flavor if they're a mage),
        // fleeing if they feel they have no chance to win,
        // healing OTHER enemies in the room that are low (likely dedicated to a type),

        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        // let the player know the haps
        System.out.println("It is " + monster.getName() + "'s turn.");

        // boolean to control loop
        boolean acting = true;

        // establish a randomly generated number to be used for these calculations
        int rng = (int) (Math.random() * 10) + 1;

        while (acting && pc.getHealth() > 0) {

            /// HEAL

            // If the monster's current health is less than or equal to half its total health
            // AND they haven't already healed, 50% chance the monster heals
            if (monster.getHealth() <= (monster.getTotalHealth() / 2) && !monster.hasHealed()) {
                // if condition is true, randomly decide whether to heal
                if (rng > 6) {
                    // amount to heal
                    int healthToAdd = (monster.getTotalHealth() / 2);
                    // store current health for printing
                    int currentHealth = monster.getHealth();
                    // heal
                    monster.setHealth(monster.getHealth() + healthToAdd);
                    // set flag
                    monster.setHasHealed(true);
                    // If the added health makes the current health pool exceed total possible health, set it to max
                    if (monster.getHealth() > monster.getTotalHealth()) {
                        monster.setHealth(monster.getTotalHealth());
                    }
                    try {
                        Thread.sleep(300);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    // inform the player
                    System.out.println(monster.getName()
                            + " heals for "
                            + healthToAdd + " points from "
                            + currentHealth + " Health to "
                            + monster.getHealth() + " Health!");
                    try {
                        Thread.sleep(300);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    acting = false;
                }

            /// DEFEND

                // if the monster's current health is less than or equal to half its total health
                // AND they HAVE already healed, 30% chance the monster defends
            } else if (monster.getHealth() <= monster.getTotalHealth() && monster.hasHealed()) {
                // if condition is true, randomly decide whether to defend
                if (rng > 7) {
                    // defend
                    monster.setDefending(true);
                    try {
                        Thread.sleep(300);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    // inform the player
                    System.out.println(monster.getHealth() + " waits for your next attack, ready to defend against it."
                     + "\n They will take half damage from your attacks this round.");
                    try {
                        Thread.sleep(300);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                acting = false;
                }

            /// ATTACK
                // if none of the above are applicable, 80% chance the monster attacks
            } else {
                // randomly decide whether to attack, heavily weighting attacking
                if (rng > 2) {
                    // ATTACK!
                    this.monsterDoDamage(pc, monster);

                    /// the monster does not attack
                } else {
                    try {
                        Thread.sleep(300);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    // The monster chooses not to attack. Tell the player!
                    System.out.println(monster.getName() + " bides time, moving about, closing the gap, waiting. Now is the time to strike!");
                    try {
                        Thread.sleep(300);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }

                acting = false;
                /// Handle death with redundancy
                if (pc.getHealth() <= 0) {
                    if (pc.youDied(pc, monster)) {
                        DungeonCrawlController.endGame(pc);
                    }

                }

            }

        }

        /// END

    }

    public void monsterDoDamage(PlayerCharacter pc, Monster monster) {

        /// Method to calculate how much damage a monster does to a PC with a move in combat

        // get the monster's move

        /// Open them connections
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            // Load MySQL driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Set up the connection with the DB
            connection = DriverManager
                    .getConnection(connectionString);
            statement = connection.createStatement();

            resultSet = statement
                    .executeQuery("select * from dungeoncrawler.moves WHERE Theme = \"" + monster.getTheme() + "\" AND Type = "
                            + monster.getType() + " AND Level = " + monster.getTier());

            // get the move
            while (resultSet.next()) {
                String name = resultSet.getString("Name");
                String description = resultSet.getString("Description");
                String theme = resultSet.getString("Theme");
                int type = resultSet.getInt("Type");
                int level = resultSet.getInt("Level");
                int damageModifier = resultSet.getInt("Damage Modifier");
                boolean ignoreArmor = resultSet.getBoolean("Ignore Armor");

                // assign the move
                Move move = new Move(name, description, theme, type, level, damageModifier, ignoreArmor);

                // Initialize a variable to assign a calculated value to later for cleaner code
                int damage;

                // If the move ignores armor, don't subtract player's armor rating
                if (move.doesIgnoreArmor()) {
                    damage = (monster.getDamage() + move.getDamageModifier());
                } else {
                    damage = (monster.getDamage() + move.getDamageModifier() - pc.getArmorRating());
                }

                // If the player is defending themselves against the attack type, halve the damage
                if (pc.isDefendMelee() || move.getType() == 1) {
                    damage = damage / 2;
                } else if (pc.isDefendRanged() || move.getType() == 2) {
                    damage = damage / 2;
                } else if (pc.isDefendMagic() || move.getType() == 3) {
                    damage = damage / 2;
                }

                // If the calculated damage value is a negative, set it to zero so no damage or healing is done inadvertently
                if (damage < 0) {
                    damage = 0;
                }

                // Apply damage
                pc.setHealth((pc.getHealth() - damage));

                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                // Inform the player how it went
                System.out.println(monster.getName() + " attacks you with " + move.getName() + "."
                        + "\n" + move.getDescription()
                        + "\nIt deals " + damage + " damage!");
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                // Attack dealt no damage
                if (damage == 0) {
                    try {
                        Thread.sleep(300);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    System.out.println("Your armor overcame the attack!");
                    try {
                        Thread.sleep(300);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                } else if (pc.getHealth() > 0) {
                    try {
                        Thread.sleep(300);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    System.out.println("You have " + pc.getHealth() + " Health remaining.");
                    try {
                        Thread.sleep(300);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }

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

        /// Handle death with redundancy
        if (pc.getHealth() <= 0) {
            if (pc.youDied(pc, monster)) {
                DungeonCrawlController.endGame(pc);
            }
        }

        /// END

    }

    public void enterBossRoom() {



    }

}