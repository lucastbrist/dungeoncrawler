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

public class Weapon {

    String name;
    String description;
    int level;
    int damageRating;
    int spellDamageModifier;
    int strengthModifier;
    int value;

    public Weapon() {
    }

    public Weapon(String name) {
        this.name = name;
    }

    public Weapon(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public Weapon(String name, String description, int damageRating, int strengthModifier, int value) {
        this.name = name;
        this.description = description;
        this.damageRating = damageRating;
        this.strengthModifier = strengthModifier;
        this.value = value;
    }

    public Weapon(String name, String description, int level, int damageRating, int spellDamageModifier, int strengthModifier, int value) {
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

    public int getDamageRating() {
        return damageRating;
    }

    public void setDamageRating(int damageRating) {
        this.damageRating = damageRating;
    }

    public int getSpellDamageModifier() {
        return spellDamageModifier;
    }

    public void setSpellDamageModifier(int spellDamageModifier) {
        this.spellDamageModifier = spellDamageModifier;
    }

    public int getStrengthModifier() {
        return strengthModifier;
    }

    public void setStrengthModifier(int strengthModifier) {
        this.strengthModifier = strengthModifier;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Weapon{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", damageRating=" + damageRating +
                ", strengthModifier=" + strengthModifier +
                ", value=" + value +
                '}';
    }

    public void generateWeapon(PlayerCharacter pc) {

        /// Generate weapon stats

        // Instantiate things we're about to use
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        ArrayList<Weapon> weapons = new ArrayList<>();

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
                    .executeQuery("select * from dungeoncrawler.weapons"
                            + " WHERE Level between 1 and " + (pc.getLevel() + generatedModifier));

            // loop through the result set while there are more records
            while (resultSet.next()) {

                // Get weapon stats
                String name = resultSet.getString("Name");
                String description = resultSet.getString("Description");
                int level = resultSet.getInt("Level");
                int damageRating = resultSet.getInt("Damage Rating");
                int spellDamageModifier = resultSet.getInt("Spell Damage Modifier");
                int strengthModifier = resultSet.getInt("Strength Modifier");
                int value = resultSet.getInt("Value");

                // Add what we just pulled as a new weapon to the arraylist to sort through later
                weapons.add(new Weapon(
                        name,
                        description,
                        level,
                        damageRating,
                        spellDamageModifier,
                        strengthModifier,
                        value));
            }

            // Random number generator to pick from the arraylist we just made
            int weaponToPick = (int)(Math.random() * weapons.size());
            // Instantiate an armor to apply the picked armor to so we don't have to get it from the arraylist every time
            Weapon weaponToAdd;

            weaponToAdd = weapons.get(weaponToPick);

            // assign properties to this armor
            this.setName(weaponToAdd.getName());
            this.setDescription(weaponToAdd.getDescription());
            this.setLevel(weaponToAdd.getLevel());
            this.setDamageRating(weaponToAdd.getDamageRating());
            this.setSpellDamageModifier(weaponToAdd.getSpellDamageModifier());
            this.setStrengthModifier(weaponToAdd.getStrengthModifier());
            this.setValue(weaponToAdd.getValue());

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
        System.out.println("It deals " + this.getDamageRating() + " damage.");
        System.out.println("It has the following magical effects:");

        // Strength Modifier
        if (!Objects.equals(this.getStrengthModifier(), null)) {
            if (this.getStrengthModifier() > 0) {
                System.out.println("Enhances your Strength by " + this.getStrengthModifier() + " points.");
            } else if (this.getStrengthModifier() < 0) {
                System.out.println("Reduces your Strength by " + this.getStrengthModifier() + " points.");
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

    public boolean weaponCollected(PlayerCharacter pc) {

        /// Method to handle the player character collecting a new weapon and showing them their options
        // Returns true if the weapon was equipped, letting the calling method remove it from wherever it was found

        System.out.println("You have collected ");
        this.printStats();

        System.out.println("Your current weapon is ");
        pc.getWeapon().printStats();

        System.out.println("Do you want to equip " + this.getName() + ", replacing " + pc.getWeapon().getName() + "?" +
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
                        pc.setWeapon(this);
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

        // if all else somehow fails, at least let the weapon keep existing
        return false;
    }

}
