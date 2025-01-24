package DungeonCrawler.models;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Objects;
import java.util.Scanner;

public class Trinket {

    String name;
    String description;
    int level;
    int strengthModifier;
    int smartsModifier;
    int stealthModifier;
    int healthModifier;
    int damageModifier;
    int spellDamageModifier;
    int armorRatingModifier;
    int value;

    public Trinket() {
    }

    public Trinket(String name) {
        this.name = name;
    }

    public Trinket(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public Trinket(String name, String description, int armorRatingModifier, int value) {
        this.name = name;
        this.description = description;
        this.armorRatingModifier = armorRatingModifier;
        this.value = value;
    }

    public Trinket(String name, String description, int level, int strengthModifier, int smartsModifier, int stealthModifier, int healthModifier, int damageModifier, int spellDamageModifier, int armorRatingModifier, int value) {
        this.name = name;
        this.description = description;
        this.level = level;
        this.strengthModifier = strengthModifier;
        this.smartsModifier = smartsModifier;
        this.stealthModifier = stealthModifier;
        this.healthModifier = healthModifier;
        this.damageModifier = damageModifier;
        this.spellDamageModifier = spellDamageModifier;
        this.armorRatingModifier = armorRatingModifier;
        this.value = value;
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

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getStrengthModifier() {
        return strengthModifier;
    }

    public void setStrengthModifier(int strengthModifier) {
        this.strengthModifier = strengthModifier;
    }

    public int getSmartsModifier() {
        return smartsModifier;
    }

    public void setSmartsModifier(int smartsModifier) {
        this.smartsModifier = smartsModifier;
    }

    public int getStealthModifier() {
        return stealthModifier;
    }

    public void setStealthModifier(int stealthModifier) {
        this.stealthModifier = stealthModifier;
    }

    public int getHealthModifier() {
        return healthModifier;
    }

    public void setHealthModifier(int healthModifier) {
        this.healthModifier = healthModifier;
    }

    public int getDamageModifier() {
        return damageModifier;
    }

    public void setDamageModifier(int damageModifier) {
        this.damageModifier = damageModifier;
    }

    public int getSpellDamageModifier() {
        return spellDamageModifier;
    }

    public void setSpellDamageModifier(int spellDamageModifier) {
        this.spellDamageModifier = spellDamageModifier;
    }

    public int getArmorRatingModifier() {
        return armorRatingModifier;
    }

    public void setArmorRatingModifier(int armorRatingModifier) {
        this.armorRatingModifier = armorRatingModifier;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Trinket{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", strengthModifier=" + strengthModifier +
                ", smartsModifier=" + smartsModifier +
                ", stealthModifier=" + stealthModifier +
                ", healthModifier=" + healthModifier +
                ", damageModifier=" + damageModifier +
                ", spellDamageModifier=" + spellDamageModifier +
                ", armorRatingModifier=" + armorRatingModifier +
                ", value=" + value +
                '}';
    }

    public void generateTrinket(PlayerCharacter pc) {

        /// Generate trinket stats

        // Instantiate things we're about to use
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        ArrayList<Trinket> trinkets = new ArrayList<>();

        try {
            // Load MySQL driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Read in some text
            String readPath = "src/data/connectionString.txt";
            String connectionString = "";
            try {
                File file = new File(readPath);
                BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
                String string;
                while ((string = bufferedReader.readLine()) != null) {
                    connectionString = string;
                }
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }

            // Set up the connection with the DB
            connection = DriverManager
                    .getConnection(connectionString);
            statement = connection.createStatement();

                // Generate a number for a 1 in 3 chance to add or subtract 1 from the levels to pull from, for some wiggle room
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
                        .executeQuery("select * from dungeoncrawler.trinkets"
                                + " WHERE Level between 1 and " + (pc.getLevel() + generatedModifier));

                // loop through the result set while there are more records
                while (resultSet.next()) {

                    // Get trinket stats
                    String name = resultSet.getString("Name");
                    String description = resultSet.getString("Description");
                    int level = resultSet.getInt("Level");
                    int strengthModifier = resultSet.getInt("Strength Modifier");
                    int smartsModifier = resultSet.getInt("Smarts Modifier");
                    int stealthModifier = resultSet.getInt("Stealth Modifier");
                    int healthModifier = resultSet.getInt("Health Modifier");
                    int damageModifier = resultSet.getInt("Damage Modifier");
                    int spellDamageModifier = resultSet.getInt("Spell Damage Modifier");
                    int armorRatingModifier = resultSet.getInt("Armor Rating Modifier");
                    int value = resultSet.getInt("Value");

                    // Add what we just pulled as a new armor to the arraylist to sort through later
                    trinkets.add(new Trinket(
                            name,
                            description,
                            level,
                            strengthModifier,
                            smartsModifier,
                            stealthModifier,
                            healthModifier,
                            damageModifier,
                            spellDamageModifier,
                            armorRatingModifier,
                            value));
                }

                // Random number generator to pick from the arraylist we just made
                int trinketToPick = (int)(Math.random() * trinkets.size());
                // Instantiate a trinket to apply the picked trinket to so we don't have to get it from the arraylist every time
                Trinket trinketToAdd;

                    trinketToAdd = trinkets.get(trinketToPick);

                    // assign properties to this trinket
                    this.setName(trinketToAdd.getName());
                    this.setDescription(trinketToAdd.getDescription());
                    this.setLevel(trinketToAdd.getLevel());
                    this.setStrengthModifier(trinketToAdd.getStrengthModifier());
                    this.setSmartsModifier((trinketToAdd.getSmartsModifier()));
                    this.setStealthModifier(trinketToAdd.getStealthModifier());
                    this.setHealthModifier(trinketToAdd.getHealthModifier());
                    this.setDamageModifier(trinketToAdd.getDamageModifier());
                    this.setSpellDamageModifier(trinketToAdd.getSpellDamageModifier());
                    this.setArmorRatingModifier(trinketToAdd.getArmorRatingModifier());
                    this.setValue(trinketToAdd.getValue());

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

    }

    public void printStats() {

        System.out.println(this.getName() + ": " + this.getDescription() + ".");
        System.out.println("It has the following magical effects:");

        // Strength Modifier
        if (!Objects.equals(this.getStrengthModifier(), null)) {
            if (this.getStrengthModifier() > 0) {
                System.out.println("Enhances your Strength by " + this.getStrengthModifier() + " points.");
            } else if (this.getStrengthModifier() < 0) {
                System.out.println("Reduces your Strength by " + this.getStrengthModifier() + " points.");
            }
        }

        // Smarts Modifier
        if (!Objects.equals(this.getSmartsModifier(), null)) {
            if (this.getSmartsModifier() > 0) {
                System.out.println("Enhances your Smarts by " + this.getSmartsModifier() + " points.");
            } else if (this.getSmartsModifier() < 0) {
                System.out.println("Reduces your Smarts by " + this.getSmartsModifier() + " points.");
            }
        }

        // Stealth Modifier
        if (!Objects.equals(this.getStealthModifier(), null)) {
            if (this.getStealthModifier() > 0) {
                System.out.println("Enhances your Stealth by " + this.getStealthModifier() + " points.");
            } else if (this.getStealthModifier() < 0) {
                System.out.println("Reduces your Stealth by " + this.getStealthModifier() + " points.");
            }
        }

        // Health Modifier
        if (!Objects.equals(this.getHealthModifier(), null)) {
            if (this.getHealthModifier() > 0) {
                System.out.println("Enhances your Health by " + this.getHealthModifier() + " points.");
            } else if (this.getHealthModifier() < 0) {
                System.out.println("Reduces your Health by " + this.getHealthModifier() + " points.");
            }
        }

        // Damage
        if (!Objects.equals(this.getDamageModifier(), null)) {
            if (this.getDamageModifier() > 0) {
                System.out.println("Enhances your Damage by " + this.getDamageModifier() + " points.");
            } else if (this.getDamageModifier() < 0) {
                System.out.println("Reduces your Damage by " + this.getDamageModifier() + " points.");
            }
        }

        // Spell Damage
        if (!Objects.equals(this.getSpellDamageModifier(), null)) {
            if (this.getSpellDamageModifier() > 0) {
                System.out.println("Enhances your Spell Damage by " + this.getSpellDamageModifier() + " points.");
            } else if (this.getSpellDamageModifier() < 0) {
                System.out.println("Reduces your Spell Damage by " + this.getSpellDamageModifier() + " points.");
            }
        }

        // Armor Rating
        if (!Objects.equals(this.getArmorRatingModifier(), null)) {
            if (this.getArmorRatingModifier() > 0) {
                System.out.println("Enhances your Armor Rating by " + this.getArmorRatingModifier() + " points.");
            } else if (this.getArmorRatingModifier() < 0) {
                System.out.println("Reduces your Armor Rating by " + this.getArmorRatingModifier() + " points.");
            }
        }

        // Value
        System.out.println("It has a value of " + this.getValue() + " gold.");

    }

    public boolean trinketCollected(PlayerCharacter pc) {

        /// Method to handle the player character collecting a new trinket and showing them their options
        // Returns true if the trinket was equipped, letting the calling method remove it from wherever it was found

        System.out.println("You have collected ");
        this.printStats();

        System.out.println("Your current trinket is ");
        pc.getTrinket().printStats();

        System.out.println("Do you want to equip " + this.getName() + ", replacing " + pc.getTrinket().getName() + "?" +
                "\nYou can only carry one with you, and you will leave behind whichever you do not take.");
        System.out.println("1. Yes");
        System.out.println("2. No");

        Scanner scanner = new Scanner(System.in);
        boolean deciding = true;

        while (deciding) {

            try {

                int decision = scanner.nextInt();
                switch (decision) {
                    case 1:
                        pc.setTrinket(this);
                        System.out.println("You equip " + this.getName() + ".");
                        return true;
                    case 2:
                        System.out.println("You leave behind " + this.getName() + ".");
                        return false;
                    default:
                        System.out.println("You have entered an invalid choice. Please try again.");
                }

            } catch (InputMismatchException exception) {
                scanner.nextLine();
                System.out.println("You have entered an invalid choice. Please try again.");
            }

        }

        // if all else somehow fails, at least let the trinket keep existing
        return false;
    }

}