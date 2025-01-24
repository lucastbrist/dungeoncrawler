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

public class Armor {

    String name;
    String description;
    int level;
    int armorRating;
    int strengthModifier;
    int smartsModifier;
    int stealthModifier;
    int healthModifier;
    int spellDamageModifier;
    int value;

    public Armor() {
    }

    public Armor(String name) {
        this.name = name;
    }

    public Armor(String name, String description, int armorRating, int value) {
        this.name = name;
        this.description = description;
        this.armorRating = armorRating;
        this.value = value;
    }

    public Armor(String name, String description, int armorRating, int strengthModifier, int value) {
        this.name = name;
        this.description = description;
        this.armorRating = armorRating;
        this.strengthModifier = strengthModifier;
        this.value = value;
    }

    public Armor(String name, String description, int armorRating, int strengthModifier, int smartsModifier, int value) {
        this.name = name;
        this.description = description;
        this.armorRating = armorRating;
        this.strengthModifier = strengthModifier;
        this.smartsModifier = smartsModifier;
        this.value = value;
    }

    public Armor(String name, String description, int armorRating, int strengthModifier, int smartsModifier, int stealthModifier, int value) {
        this.name = name;
        this.description = description;
        this.armorRating = armorRating;
        this.strengthModifier = strengthModifier;
        this.smartsModifier = smartsModifier;
        this.stealthModifier = stealthModifier;
        this.value = value;
    }

    public Armor(String name, String description, int armorRating, int strengthModifier, int smartsModifier, int stealthModifier, int healthModifier, int value) {
        this.name = name;
        this.description = description;
        this.armorRating = armorRating;
        this.strengthModifier = strengthModifier;
        this.smartsModifier = smartsModifier;
        this.stealthModifier = stealthModifier;
        this.healthModifier = healthModifier;
        this.value = value;
    }

    public Armor(String name, String description, int armorRating, int strengthModifier, int smartsModifier, int stealthModifier, int healthModifier, int spellDamageModifier, int value) {
        this.name = name;
        this.description = description;
        this.armorRating = armorRating;
        this.strengthModifier = strengthModifier;
        this.smartsModifier = smartsModifier;
        this.stealthModifier = stealthModifier;
        this.healthModifier = healthModifier;
        this.spellDamageModifier = spellDamageModifier;
        this.value = value;
    }

    public Armor(String name, String description, int level, String armorRating, int strengthModifier, int smartsModifier, int stealthModifier, int healthModifier, int spellDamageModifier, int value) {
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

    public int getArmorRating() {
        return armorRating;
    }

    public void setArmorRating(int armorRating) {
        this.armorRating = armorRating;
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

    public int getSpellDamageModifier() {
        return spellDamageModifier;
    }

    public void setSpellDamageModifier(int spellDamageModifier) {
        this.spellDamageModifier = spellDamageModifier;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Armor{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", armorRating=" + armorRating +
                ", strengthModifier=" + strengthModifier +
                ", smartsModifier=" + smartsModifier +
                ", stealthModifier=" + stealthModifier +
                ", healthModifier=" + healthModifier +
                ", spellDamageModifier=" + spellDamageModifier +
                ", value=" + value +
                '}';
    }

    public void generateArmor(PlayerCharacter pc) {

        /// Generate armor stats

        // Instantiate things we're about to use
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        ArrayList<Armor> armors = new ArrayList<>();

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
                    .executeQuery("select * from dungeoncrawler.armors"
                            + " WHERE Level between 1 and " + (pc.getLevel() + generatedModifier));

            // loop through the result set while there are more records
            while (resultSet.next()) {

                // Get armor stats
                String name = resultSet.getString("Name");
                String description = resultSet.getString("Description");
                int level = resultSet.getInt("Level");
                String armorRating = resultSet.getString("Armor Rating");
                int strengthModifier = resultSet.getInt("Strength Modifier");
                int smartsModifier = resultSet.getInt("Smarts Modifier");
                int stealthModifier = resultSet.getInt("Stealth Modifier");
                int healthModifier = resultSet.getInt("Health Modifier");
                int spellDamageModifier = resultSet.getInt("Spell Damage Modifier");
                int value = resultSet.getInt("Value");

                // Add what we just pulled as a new armor to the arraylist to sort through later
                armors.add(new Armor(
                        name,
                        description,
                        level,
                        armorRating,
                        strengthModifier,
                        smartsModifier,
                        stealthModifier,
                        healthModifier,
                        spellDamageModifier,
                        value));
            }

            // Random number generator to pick from the arraylist we just made
            int armorToPick = (int)(Math.random() * armors.size());
            // Instantiate an armor to apply the picked armor to so we don't have to get it from the arraylist every time
            Armor armorToAdd;

            armorToAdd = armors.get(armorToPick);

            // assign properties to this armor
            this.setName(armorToAdd.getName());
            this.setDescription(armorToAdd.getDescription());
            this.setLevel(armorToAdd.getLevel());
            this.setArmorRating(armorToAdd.getArmorRating());
            this.setStrengthModifier(armorToAdd.getStrengthModifier());
            this.setSmartsModifier((armorToAdd.getSmartsModifier()));
            this.setStealthModifier(armorToAdd.getStealthModifier());
            this.setHealthModifier(armorToAdd.getHealthModifier());
            this.setSpellDamageModifier(armorToAdd.getSpellDamageModifier());
            this.setValue(armorToAdd.getValue());

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
        System.out.println("It has an Armor Rating of " + this.getArmorRating() + ".");
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

        // Spell Damage
        if (!Objects.equals(this.getSpellDamageModifier(), null)) {
            if (this.getSpellDamageModifier() > 0) {
                System.out.println("Enhances your Spell Damage by " + this.getSpellDamageModifier() + " points.");
            } else if (this.getSpellDamageModifier() < 0) {
                System.out.println("Reduces your Spell Damage by " + this.getSpellDamageModifier() + " points.");
            }
        }

        // Value
        System.out.println("It has a value of " + this.getValue() + " gold.");

    }

    public boolean armorCollected(PlayerCharacter pc) {

        /// Method to handle the player character collecting a new armor and showing them their options
        // Returns true if the armor was equipped, letting the calling method remove it from wherever it was found

        System.out.println("You have collected ");
        this.printStats();

        System.out.println("Your current armor is ");
        pc.getArmor().printStats();

        System.out.println("Do you want to equip " + this.getName() + ", replacing " + pc.getArmor().getName() + "?" +
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
                        pc.setArmor(this);
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

        // if all else somehow fails, at least let the armor keep existing
        return false;
    }

}