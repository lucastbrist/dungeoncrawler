package DungeonCrawler.models;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Dungeon {

    // The name of the dungeon
    String name;

    String description;

    String dungeonTheme;

    // Array list containing objects of the Room class--this is where the magic happens
    ArrayList<Room> rooms;

    // Number of rooms the player has cleared in this dungeon
    int clearedRooms;

    // The active player
    Player player;

    /// Constructors

    public Dungeon() {
    }

    public Dungeon(Player player, PlayerCharacter pc) {
        this.player = player;
        // When a dungeon is created and the player enters it, they are always set to full health.
        getPlayer().getCharacter().setHealth(getPlayer().getCharacter().getTotalHealth());
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

    public ArrayList<Room> getRooms() {
        return rooms;
    }

    public void setRooms(ArrayList<Room> rooms) {
        this.rooms = rooms;
    }

    public int getClearedRooms() {
        return clearedRooms;
    }

    public void setClearedRooms(int clearedRooms) {
        this.clearedRooms = clearedRooms;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public String getDungeonTheme() {
        return dungeonTheme;
    }

    public void setDungeonTheme(String dungeonTheme) {
        this.dungeonTheme = dungeonTheme;
    }

    ///

    public void generateRooms() {

        // Method to generate room data

        ///

        // Instantiate things we're about to use
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        ArrayList<Room> tempRooms = new ArrayList<>();

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

            // Generate a number for a 1 in 3 chance to add or subtract to the amount of rooms
            int generatedModifier = (int)((Math.random() * 2) + 1);
            // If statement to interpret the result as 0 or +2
            if (generatedModifier == 1) {
                generatedModifier = 0;
            }

            // Loop to generate rooms with a dynamic SQL statement

                resultSet = statement
                        .executeQuery("select * from dungeoncrawler.rooms"
                                + " WHERE Theme = " + "\"" + this.getDungeonTheme() + "\""
                                + " LIMIT " + (3 + generatedModifier));

                // loop through the result set while there are more records
                while (resultSet.next()) {

                    // Get room info
                    String name = resultSet.getString("Name");
                    String description = resultSet.getString("Description");

                    tempRooms.add(new Room(name, description, this.getDungeonTheme()));

                }

                // Assign our temporary room arraylist to the dungeon room arraylist
                this.setRooms(tempRooms);

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

//    public String getScore() {
//
//        this.calculateScore();
//
//        return //breakdown of score
//
//    }

    public void generateTheme() {

        int themeInt = (int)(Math.random() * 20) + 1;
        String theme;

        switch (themeInt) {
            case 1:
                theme = "Automatons";
                break;
            case 2:
                theme = "Bandits";
                break;
            case 3:
                theme = "Beasts";
                break;
            case 4:
                theme = "Demons";
                break;
            case 5:
                theme = "Deserters";
                break;
            case 6:
                theme = "Fae";
                // 1 dungeon type -- a faerie court
                break;
            case 7:
                theme = "Flooded";
                // 1 dungeon type -- a flooded cavern
                break;
            case 8:
                theme = "Ghosts";
                break;
            case 9:
                theme = "Goblins";
                break;
            case 10:
                theme = "Golems";
                break;
            case 11:
                theme = "Mages";
                break;
            case 12:
                theme = "Oozes";
                break;
            case 13:
                theme = "Pirates";
                break;
            case 14:
                theme = "Plants";
                // 1 dungeon type -- an overgrown cave full of killer plants
                break;
            case 15:
                theme = "Skeletons";
                break;
            case 16:
                theme = "Spiders";
                break;
            case 17:
                theme = "Vampires";
                break;
            case 18:
                theme = "Void Creatures";
                break;
            case 19:
                theme = "Wild Men";
                break;
            case 20:
                theme = "Zombies";
                break;

            default:
                theme = "Skeletons";
                break;
        }

        // To avoid wasting time creating tons of placeholder tables, the above is the proof of concept for
        // what could work. For the project's purposes, we will always resort to skeletons.
        theme = "Skeletons";

        this.setDungeonTheme(theme);

    }

    public void enterDungeon(PlayerCharacter pc) {

        /// Method to handle entering a dungeon.

        System.out.println("You are in " + this.getName() + ".");
        System.out.println(this.getDescription());

        // Create a "lobby" room that acts as a hub and facilitates the inspect() method in Room
        Room lobby = new Room();
        lobby.setName(this.getName());
        lobby.setDescription(this.getDescription());

        System.out.println();

        Scanner dungeonScanner = new Scanner(System.in);
        boolean deciding = true;

        while (deciding) {

            System.out.println("You see the way forward.");
            System.out.println("Do you proceed?");
            System.out.println("1. Enter");
            System.out.println("2. Inspect");

            try {

                int decision = dungeonScanner.nextInt();

                switch (decision) {

                    case 1:
                        for (int i = 0; i < this.getRooms().size(); i++) {
                            if (this.getRooms().get(i).isCleared() == false) {
                                if (this.getRooms().get(i).enterRoom(pc)) {
                                    this.setClearedRooms(this.getClearedRooms() + 1);
                                    break;
                                }
                            }
                        }

                    case 2:
                        System.out.println("There are " + this.getRooms().size() + " rooms in this dungeon.");
                        System.out.println("You have cleared " + this.getClearedRooms() + " of them.");

                        lobby.inspect(pc, false);
                        break;
                }

            } catch (InputMismatchException exception) {
                dungeonScanner.nextLine();
                System.out.println("You have entered an invalid choice. Please try again.");
            }

            if (this.getClearedRooms() == this.getRooms().size()) {
                System.out.println("Congratulations, you have cleared the dungeon!");
                return;
            }

        }
    }

}
