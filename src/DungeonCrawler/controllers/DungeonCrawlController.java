package DungeonCrawler.controllers;

import DungeonCrawler.models.*;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import static DungeonCrawler.controllers.DungeonCrawlController.StringStore.connectionString;

public class DungeonCrawlController {

    public static void main(String[] args) {

        // LOGO!
        System.out.println(
                "                                  ,--.                       ,----..           ,--. \n" +
                "    ,---,                       ,--.'| ,----..      ,---,.  /   /   \\        ,--.'| \n" +
                "  .'  .' `\\          ,--,   ,--,:  : |/   /   \\   ,'  .' | /   .     :   ,--,:  : | \n" +
                ",---.'     \\       ,'_ /|,`--.'`|  ' |   :     :,---.'   |.   /   ;.  ,`--.'`|  ' : \n" +
                "|   |  .`\\  | .--. |  | :|   :  :  | .   |  ;. /|   |   ..   ;   /  ` |   :  :  | | \n" +
                ":   : |  '  ,'_ /| :  . |:   |   \\ | .   ; /--` :   :  |-;   |  ; \\ ; :   |   \\ | : \n" +
                "|   ' '  ;  |  ' | |  . .|   : '  '; ;   | ;  __:   |  ;/|   :  | ; | |   : '  '; | \n" +
                "'   | ;  .  |  | ' |  | |'   ' ;.    |   : |.' .|   :   ..   |  ' ' ' '   ' ;.    ; \n" +
                "|   | :  |  :  | | :  ' ;|   | | \\   .   | '_.' |   |  |-'   ;  \\; /  |   | | \\   | \n" +
                "'   : | /  ;|  ; ' |  | ''   : |  ; .'   ; : \\  '   :  ;/|\\   \\  ',  /'   : |  ; .' \n" +
                "|   | '` ,/ :  | : ;  ; ||   | '`--' '   | '/  .|   |    \\ ;   :    / |   | '`--'   \n" +
                ";   :  .'   '  :  `--'   '   : |     |   :    / |   :   .'  \\   \\ .'  '   : |       \n" +
                "|   ,.'     :  ,      .-.;   |.'      \\   \\ .'  |   | ,'     `---`    ;   |.'       \n" +
                "'---'        `--`----'   '---'         `---`    `----'                '---'         ");
        System.out.println(
                "                                                 ,---.'|                       \n" +
                "  ,----.. ,-.----.     ,---,                 .---|   | :      ,---,,-.----.    \n" +
                " /   /   \\\\    /  \\   '  .' \\               /. ./:   : |    ,'  .' \\    /  \\   \n" +
                "|   :     ;   :    \\ /  ;    '.         .--'.  ' |   ' :  ,---.'   ;   :    \\  \n" +
                ".   |  ;. |   | .\\ ::  :       \\       /__./ \\ : ;   ; '  |   |   .|   | .\\ :  \n" +
                ".   ; /--`.   : |: |:  |   /\\   \\  .--'.  '   \\' '   | |__:   :  |-.   : |: |  \n" +
                ";   | ;   |   |  \\ :|  :  ' ;.   :/___/ \\ |    ' |   | :.':   |  ;/|   |  \\ :  \n" +
                "|   : |   |   : .  /|  |  ;/  \\   ;   \\  \\;      '   :    |   :   .|   : .  /  \n" +
                ".   | '___;   | |  \\'  :  | \\  \\ ,'\\   ;  `      |   |  ./|   |  |-;   | |  \\  \n" +
                "'   ; : .'|   | ;\\  |  |  '  '--'   .   \\    .\\  ;   : ;  '   :  ;/|   | ;\\  \\ \n" +
                "'   | '/  :   ' | \\.|  :  :          \\   \\   ' \\ |   ,/   |   |    :   ' | \\.' \n" +
                "|   :    /:   : :-' |  | ,'           :   '  |--\"'---'    |   :   .:   : :-'   \n" +
                " \\   \\ .' |   |.'   `--''              \\   \\ ;            |   | ,' |   |.'     \n" +
                "  `---`   `---'                         '---\"             `----'   `---'       \n");

        // Read the contents of connectionString.txt. We need this a lot.
        String readPath = "src/connectionString.txt";
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
        StringStore.setConnectionString(connectionString);

        /// Create an instance of a Player
        Player player = new Player();

        /// Welcome the player!
        System.out.println("Welcome to Dungeon Crawler.");
        System.out.println("Please enter your username:");

        /// Take in their username and greet them
        Scanner scanner = new Scanner(System.in);
        player.setUsername(scanner.nextLine());
        System.out.println("Greetings, " + player.getUsername() + ".");

        /// Wait for an input to begin.
        // Secretly, this allows the player to create, read, update, or delete data if they type in CRUD
        System.out.println("Enter anything to begin.");

        boolean entering = true;
        Scanner entryScanner = new Scanner(System.in);

        while (entering) {

            String entry = entryScanner.nextLine();

            if (entry.equals("CRUD")) {
                CRUD();
                continue;

            } else {

                /// Initiate character creation

                PlayerCharacter pc = new PlayerCharacter();
                pc.characterCreation();
                player.setCharacter(pc);
                pc.setPlayer(player);
                // Create tutorial that explain things eventually.

                /// BEGIN DUNGEONCRAWLING
                boolean crawling = true;
                Scanner crawlScanner = new Scanner(System.in);

                while (crawling) {

                    try {
                        System.out.println("Would you like to crawl through a new dungeon?");
                        System.out.println("1. YES");
                        System.out.println("2. NO");

                        int choice = crawlScanner.nextInt();

                        switch (choice) {

                            case 1:
                                Dungeon dungeon = generateDungeon(player, pc);
                                dungeon.enterDungeon(pc);
                                break;
                            case 2:
                                endGame(pc);
                                break;

                        }

                        // catch input mismatches (strings, chars, etc)
                    } catch (InputMismatchException exception) {
                        crawlScanner.nextLine();
                        System.out.println("You have entered an invalid choice. Please try again.");
                        continue;
                    }
                }

                break;

            }

        }

    }

    public static void CRUD() {

        System.out.println("Welcome to CRUD:");
        System.out.println("CREATE!");
        System.out.println("READ!");
        System.out.println("UPDATE!");
        System.out.println("DELETE!");

        System.out.println("NOTE: This project is for proof of concept, and as such, the only CRUD-able data is the dungeoncrawler.skeletons table.");

        boolean deciding = true;
        Scanner crudScanner = new Scanner(System.in);

        while (deciding) {

            System.out.println("What data would you like to create, read, update, or delete?");
            System.out.println("1. Monsters");
            System.out.println("2. Rooms");
            System.out.println("3. Trinkets");
            System.out.println("4. Armors");
            System.out.println("5. Weapons");
            System.out.println("Or enter 6 to restore a backup.");
            System.out.println("Press 0 to return to the main menu.");

            try {

            int choice = crudScanner.nextInt();

            switch (choice) {

                case 1:
                    crudMonsters();
                    break;
                case 2:
                    // crudRooms();
                    break;
                case 3:
                    // crudTrinkets();
                    break;
                case 4:
                    // crudArmors();
                    break;
                case 5:
                    // crudWeapons();
                    break;
                case 6:
                    // crudRestore();
                case 0:
                    return;
                default:
                    System.out.println("You have entered an invalid choice. Please try again.");
                    continue;

            }

            } catch (InputMismatchException exception) {
                crudScanner.nextLine();
                System.out.println("You have entered an invalid choice. Please try again.");
                continue;
            }


        }

    }

    public static void crudMonsters() {

        boolean deciding = true;
        Scanner monsterScanner = new Scanner (System.in);

        while (deciding) {

            System.out.println("What would you like to do with Monster data?");
            System.out.println("1. Create");
            System.out.println("2. Read");
            System.out.println("3. Update");
            System.out.println("4. Delete");

            try {

                int choice = monsterScanner.nextInt();

                switch (choice) {

                    case 1:
                        createMonsters();
                        break;
                    case 2:
                        readMonsters();
                        break;
                    case 3:
                        updateMonsters();
                        break;
                    case 4:
                        deleteMonsters();
                        break;
                    default:
                        System.out.println("You have entered an invalid choice. Please try again.");
                        continue;
                    case 0:
                        return;

                }

            } catch (InputMismatchException exception) {
                monsterScanner.nextLine();
                System.out.println("You have entered an invalid choice. Please try again.");
                continue;
            }


        }


    }

    public static void createMonsters() {

        boolean deciding = true;
        Scanner createScanner = new Scanner(System.in);
        String theme = "";
        String name = "";
        String description = "";
        int tier = 0;
        int type = 0;
        int health = 0;
        int damage = 0;
        int armor = 0;

        /// THEME

        while (deciding) {

            System.out.println("What monster type would you like to create a new monster in?");
            System.out.println("1. Automatons");
            System.out.println("2. Bandits");
            System.out.println("3. Beasts");
            System.out.println("4. Demons");
            System.out.println("5. Deserters");
            System.out.println("6. Fae");
            System.out.println("7. Flooded");
            System.out.println("8. Ghosts");
            System.out.println("9. Goblins");
            System.out.println("10. Golems");
            System.out.println("11. Mages");
            System.out.println("12. Oozes");
            System.out.println("13. Pirates");
            System.out.println("14. Plants");
            System.out.println("15. Skeletons");
            System.out.println("16. Spiders");
            System.out.println("17. Vampires");
            System.out.println("18. Void Creatures");
            System.out.println("19. Wild Men");
            System.out.println("20. Zombies");

            try {

                int choice = createScanner.nextInt();

                switch (choice) {

                    case 1:
                        theme = "automatons";
                        break;
                    case 2:
                        theme = "bandits";
                        break;
                    case 3:
                        theme = "beasts";
                        break;
                    case 4:
                        theme = "demons";
                        break;
                    case 5:
                        theme = "deserters";
                        break;
                    case 6:
                        theme = "fae";
                        break;
                    case 7:
                        theme = "flooded";
                        break;
                    case 8:
                        theme = "ghosts";
                        break;
                    case 9:
                        theme = "goblins";
                        break;
                    case 10:
                        theme = "golems";
                        break;
                    case 11:
                        theme = "mages";
                        break;
                    case 12:
                        theme = "oozes";
                        break;
                    case 13:
                        theme = "pirates";
                        break;
                    case 14:
                        theme = "plants";
                        break;
                    case 15:
                        theme = "skeletons";
                        break;
                    case 16:
                        theme = "spiders";
                        break;
                    case 17:
                        theme = "vampires";
                        break;
                    case 18:
                        theme = "void Creatures";
                        break;
                    case 19:
                        theme = "wild Men";
                        break;
                    case 20:
                        theme = "zombies";
                        break;
                    default:
                        System.out.println("You have entered an invalid choice. Please try again.");
                        continue;
                }

            } catch (InputMismatchException exception) {
                createScanner.nextLine();
                System.out.println("You have entered an invalid choice. Please try again.");
                continue;
            }

            createScanner.nextLine();

            /// NAME
            while (deciding) {

                System.out.println("Please enter your new monster's name.");

                try {

                    name = createScanner.nextLine();
                    break;

                } catch (InputMismatchException exception) {
                    createScanner.nextLine();
                    System.out.println("You have entered an invalid choice. Please try again.");
                    continue;
                }

            }

            /// DESCRIPTION
            while (deciding) {

                System.out.println("Please write a description for your new monster.");

                try {

                    description = createScanner.nextLine();
                    break;

                } catch (InputMismatchException exception) {
                    createScanner.nextLine();
                    System.out.println("You have entered an invalid choice. Please try again.");
                    continue;
                }

            }

            while (deciding) {

                System.out.println("Please choose a class for your new monster.");
                System.out.println("1. Melee");
                System.out.println("2. Ranged");
                System.out.println("3. Mage");

                try {

                    type = createScanner.nextInt();
                    if (type == 1 ^ type == 2 ^ type == 3) {
                        break;
                    } else {
                        System.out.println("You have entered an invalid choice. Please try again.");
                        continue;
                    }

                } catch (InputMismatchException exception) {
                    createScanner.nextLine();
                    System.out.println("You have entered an invalid choice. Please try again.");
                    continue;
                }

            }

            /// TIER
            while (deciding) {

                System.out.println("Please enter what level you would like your new monster to be.");

                try {

                    tier = createScanner.nextInt();
                    break;

                } catch (InputMismatchException exception) {
                    createScanner.nextLine();
                    System.out.println("You have entered an invalid choice. Please try again.");
                    continue;
                }

            }

            /// HEALTH
            while (deciding) {

                System.out.println("Please enter how much Health you would like your new monster to have.");

                try {

                    health = createScanner.nextInt();
                    break;

                } catch (InputMismatchException exception) {
                    createScanner.nextLine();
                    System.out.println("You have entered an invalid choice. Please try again.");
                    continue;
                }

            }

            /// DAMAGE
            while (deciding) {

                System.out.println("Please enter how much Damage you would like your new monster to deal.");

                try {

                    damage = createScanner.nextInt();
                    break;

                } catch (InputMismatchException exception) {
                    createScanner.nextLine();
                    System.out.println("You have entered an invalid choice. Please try again.");
                    continue;
                }

            }

            /// ARMOR
            while (deciding) {

                System.out.println("Please enter how much Armor you would like your new monster to have.");

                try {

                    armor = createScanner.nextInt();
                    break;

                } catch (InputMismatchException exception) {
                    createScanner.nextLine();
                    System.out.println("You have entered an invalid choice. Please try again.");
                    continue;
                }

            }

            break;

        }

        // Instantiate things we're about to use
        Connection connection = null;
        Statement statement = null;

        try {
            // Load MySQL driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Set up the connection with the DB
            connection = DriverManager
                    .getConnection(connectionString);
            statement = connection.createStatement();

            String sql = "INSERT INTO dungeoncrawler." + theme.toLowerCase() + " (name, description, type, tier, health, damage, armor) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, description);
            preparedStatement.setInt(3, type);
            preparedStatement.setInt(4, tier);
            preparedStatement.setInt(5, health);
            preparedStatement.setInt(6, damage);
            preparedStatement.setInt(7, armor);
            preparedStatement.executeUpdate();

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
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    public static void readMonsters() {

        boolean deciding = true;
        Scanner createScanner = new Scanner(System.in);
        String theme = "";

        while (deciding) {

            System.out.println("What monster type would you like to read monster date from?");
            System.out.println("1. Automatons");
            System.out.println("2. Bandits");
            System.out.println("3. Beasts");
            System.out.println("4. Demons");
            System.out.println("5. Deserters");
            System.out.println("6. Fae");
            System.out.println("7. Flooded");
            System.out.println("8. Ghosts");
            System.out.println("9. Goblins");
            System.out.println("10. Golems");
            System.out.println("11. Mages");
            System.out.println("12. Oozes");
            System.out.println("13. Pirates");
            System.out.println("14. Plants");
            System.out.println("15. Skeletons");
            System.out.println("16. Spiders");
            System.out.println("17. Vampires");
            System.out.println("18. Void Creatures");
            System.out.println("19. Wild Men");
            System.out.println("20. Zombies");

            try {

                int choice = createScanner.nextInt();

                switch (choice) {

                    case 1:
                        theme = "automatons";
                        break;
                    case 2:
                        theme = "bandits";
                        break;
                    case 3:
                        theme = "beasts";
                        break;
                    case 4:
                        theme = "demons";
                        break;
                    case 5:
                        theme = "deserters";
                        break;
                    case 6:
                        theme = "fae";
                        break;
                    case 7:
                        theme = "flooded";
                        break;
                    case 8:
                        theme = "ghosts";
                        break;
                    case 9:
                        theme = "goblins";
                        break;
                    case 10:
                        theme = "golems";
                        break;
                    case 11:
                        theme = "mages";
                        break;
                    case 12:
                        theme = "oozes";
                        break;
                    case 13:
                        theme = "pirates";
                        break;
                    case 14:
                        theme = "plants";
                        break;
                    case 15:
                        theme = "skeletons";
                        break;
                    case 16:
                        theme = "spiders";
                        break;
                    case 17:
                        theme = "vampires";
                        break;
                    case 18:
                        theme = "void Creatures";
                        break;
                    case 19:
                        theme = "wild Men";
                        break;
                    case 20:
                        theme = "zombies";
                        break;
                    default:
                        System.out.println("You have entered an invalid choice. Please try again.");
                        continue;
                }

            } catch (InputMismatchException exception) {
                createScanner.nextLine();
                System.out.println("You have entered an invalid choice. Please try again.");
                continue;
            }

            break;

        }

        // Instantiate things we're about to use
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        ArrayList<Monster> monsters = new ArrayList<>();

        try {
            // Load MySQL driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Set up the connection with the DB
            connection = DriverManager
                    .getConnection(connectionString);
            statement = connection.createStatement();

                resultSet = statement
                        .executeQuery("select * from dungeoncrawler." + theme.toLowerCase());

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

                    monsters.add(new Monster(name, description, theme.toLowerCase(), type, tier, health, damage, armor));
                    for (int i = 0; i < monsters.size(); i++) {
                        System.out.println("There is a " + monsters.get(i).getName() + ". "
                                + "\n "
                                + monsters.get(i).getDescription()
                                + "\n"
                                + " It is Type " + monsters.get(i).getType()
                                + ", Tier " + monsters.get(i).getTier()
                                + ", and has " + monsters.get(i).getHealth() + " Health, "
                                + monsters.get(i).getDamage() + " Damage, and "
                                + monsters.get(i).getArmor() + " Armor.");
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

    }

    public static void updateMonsters() {

        boolean deciding = true;
        Scanner createScanner = new Scanner(System.in);
        String theme = "";

        while (deciding) {

            System.out.println("What monster type would you like to update monster date from?");
            System.out.println("1. Automatons");
            System.out.println("2. Bandits");
            System.out.println("3. Beasts");
            System.out.println("4. Demons");
            System.out.println("5. Deserters");
            System.out.println("6. Fae");
            System.out.println("7. Flooded");
            System.out.println("8. Ghosts");
            System.out.println("9. Goblins");
            System.out.println("10. Golems");
            System.out.println("11. Mages");
            System.out.println("12. Oozes");
            System.out.println("13. Pirates");
            System.out.println("14. Plants");
            System.out.println("15. Skeletons");
            System.out.println("16. Spiders");
            System.out.println("17. Vampires");
            System.out.println("18. Void Creatures");
            System.out.println("19. Wild Men");
            System.out.println("20. Zombies");

            try {

                int choice = createScanner.nextInt();

                switch (choice) {

                    case 1:
                        theme = "automatons";
                        break;
                    case 2:
                        theme = "bandits";
                        break;
                    case 3:
                        theme = "beasts";
                        break;
                    case 4:
                        theme = "demons";
                        break;
                    case 5:
                        theme = "deserters";
                        break;
                    case 6:
                        theme = "fae";
                        break;
                    case 7:
                        theme = "flooded";
                        break;
                    case 8:
                        theme = "ghosts";
                        break;
                    case 9:
                        theme = "goblins";
                        break;
                    case 10:
                        theme = "golems";
                        break;
                    case 11:
                        theme = "mages";
                        break;
                    case 12:
                        theme = "oozes";
                        break;
                    case 13:
                        theme = "pirates";
                        break;
                    case 14:
                        theme = "plants";
                        break;
                    case 15:
                        theme = "skeletons";
                        break;
                    case 16:
                        theme = "spiders";
                        break;
                    case 17:
                        theme = "vampires";
                        break;
                    case 18:
                        theme = "void Creatures";
                        break;
                    case 19:
                        theme = "wild Men";
                        break;
                    case 20:
                        theme = "zombies";
                        break;
                    default:
                        System.out.println("You have entered an invalid choice. Please try again.");
                        continue;
                }

            } catch (InputMismatchException exception) {
                createScanner.nextLine();
                System.out.println("You have entered an invalid choice. Please try again.");
                continue;
            }

            break;

        }

        // Instantiate things we're about to use
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        ArrayList<Monster> monsters = new ArrayList<>();

        try {
            // Load MySQL driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Set up the connection with the DB
            connection = DriverManager
                    .getConnection(connectionString);
            statement = connection.createStatement();

            resultSet = statement
                    .executeQuery("select * from dungeoncrawler." + theme.toLowerCase());

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

                monsters.add(new Monster(name, description, theme, type, tier, health, damage, armor));

            }


            Scanner pickScanner = new Scanner(System.in);
            boolean picking = true;


            while (picking) {

                System.out.println("Which monster would you like to update?");

                for (int i = 0; i < monsters.size(); i++) {
                    System.out.println((i + 1) + ". " + monsters.get(i).getName() + ". "
                            + "\n "
                            + monsters.get(i).getDescription()
                            + "\n"
                            + " It is Type " + monsters.get(i).getType()
                            + ", Tier " + monsters.get(i).getTier()
                            + ", and has " + monsters.get(i).getHealth() + " Health, "
                            + monsters.get(i).getDamage() + " Damage, and "
                            + monsters.get(i).getArmor() + " Armor.");
                }

                try {

                    int pick = pickScanner.nextInt();
                    if (pick < 0 ^ pick > monsters.size()) {
                        System.out.println("You have entered an invalid choice. Please try again.");
                        continue;
                    } else {
                        boolean editing = true;
                        while (editing) {
                            System.out.println("What would you like to edit?");
                            System.out.println("1. Name");
                            System.out.println("2. Description");
                            System.out.println("3. Type");
                            System.out.println("4. Tier");
                            System.out.println("5. Health");
                            System.out.println("6. Damage");
                            System.out.println("7. Armor");
                            System.out.println("Or type 0 to return to make a different decision.");

                            pickScanner.nextLine();
                            String sql = "";
                            PreparedStatement preparedStatement;

                            try {

                                int editChoice = pickScanner.nextInt();

                                switch (editChoice) {
                                    case 1:
                                        Scanner nameScanner = new Scanner(System.in);
                                        System.out.println("Please enter the monster's new name.");
                                        String newName = nameScanner.nextLine();

                                        sql = "UPDATE dungeoncrawler." + theme +
                                                " SET Name = ?" +
                                                " WHERE id = " + pick;
                                        preparedStatement = connection.prepareStatement(sql);
                                        preparedStatement.setString(1, newName);
                                        preparedStatement.executeUpdate();

                                        break;
                                    case 2:
                                        Scanner descriptionScanner = new Scanner(System.in);
                                        System.out.println("Please enter the monster's new description.");
                                        String newDescription = descriptionScanner.nextLine();

                                        sql = "UPDATE dungeoncrawler." + theme +
                                                " SET Description = ?" +
                                                " WHERE id = " + pick;
                                        preparedStatement = connection.prepareStatement(sql);
                                        preparedStatement.setString(1, newDescription);
                                        preparedStatement.executeUpdate();

                                        break;
                                    case 3:
                                        boolean typeBool = true;
                                        Scanner typeScanner = new Scanner(System.in);

                                        while (typeBool) {

                                            System.out.println("Please enter the monster's new Type.");
                                            System.out.println("1. Melee");
                                            System.out.println("2. Ranged");
                                            System.out.println("3. Mage");

                                            try {

                                                int newType = typeScanner.nextInt();

                                                if (newType < 1 ^ newType > 3) {
                                                    System.out.println("You have entered an invalid choice. Please try again.");
                                                    continue;
                                                } else {

                                                    sql = "UPDATE dungeoncrawler." + theme +
                                                            " SET Type = ?" +
                                                            " WHERE id = " + pick;
                                                    preparedStatement = connection.prepareStatement(sql);
                                                    preparedStatement.setInt(1, newType);
                                                    preparedStatement.executeUpdate();
                                                    break;
                                                }

                                            } catch (InputMismatchException exception) {
                                                typeScanner.nextLine();
                                                System.out.println("You have entered an invalid choice. Please try again.");
                                                continue;
                                            }
                                        }
                                        break;

                                    case 4:
                                        boolean tierBool = true;
                                        Scanner tierScanner = new Scanner(System.in);

                                        while (tierBool) {

                                            System.out.println("Please enter the monster's new Tier.");

                                            try {

                                                int newTier = tierScanner.nextInt();
                                                sql = "UPDATE dungeoncrawler." + theme +
                                                        " SET Tier = ?" +
                                                        " WHERE id = " + pick;
                                                preparedStatement = connection.prepareStatement(sql);
                                                preparedStatement.setInt(1, newTier);
                                                preparedStatement.executeUpdate();

                                            } catch (InputMismatchException exception) {
                                                tierScanner.nextLine();
                                                System.out.println("You have entered an invalid choice. Please try again.");
                                                continue;
                                            }
                                        }

                                        break;

                                    case 5:

                                        boolean healthBool = true;
                                        Scanner healthScanner = new Scanner(System.in);

                                        while (healthBool) {

                                            System.out.println("Please enter the monster's new Health.");

                                            try {

                                                int newHealth = healthScanner.nextInt();
                                                sql = "UPDATE dungeoncrawler." + theme +
                                                        " SET Health = ?" +
                                                        " WHERE id = " + pick;
                                                preparedStatement = connection.prepareStatement(sql);
                                                preparedStatement.setInt(1, newHealth);
                                                preparedStatement.executeUpdate();

                                            } catch (InputMismatchException exception) {
                                                healthScanner.nextLine();
                                                System.out.println("You have entered an invalid choice. Please try again.");
                                                continue;
                                            }
                                        }

                                        break;

                                    case 6:

                                        boolean damageBool = true;
                                        Scanner damageScanner = new Scanner(System.in);

                                        while (damageBool) {

                                            System.out.println("Please enter the monster's new Damage.");

                                            try {

                                                int newDamage = damageScanner.nextInt();
                                                sql = "UPDATE dungeoncrawler." + theme +
                                                        " SET Damage = ?" +
                                                        " WHERE id = " + pick;
                                                preparedStatement = connection.prepareStatement(sql);
                                                preparedStatement.setInt(1, newDamage);
                                                preparedStatement.executeUpdate();

                                            } catch (InputMismatchException exception) {
                                                damageScanner.nextLine();
                                                System.out.println("You have entered an invalid choice. Please try again.");
                                                continue;
                                            }
                                        }

                                        break;

                                    case 7:

                                        boolean armorBool = true;
                                        Scanner armorScanner = new Scanner(System.in);

                                        while (armorBool) {

                                            System.out.println("Please enter the monster's new Armor.");

                                            try {

                                                int newArmor = armorScanner.nextInt();
                                                sql = "UPDATE dungeoncrawler." + theme +
                                                        " SET Armor = ?" +
                                                        " WHERE id = " + pick;
                                                preparedStatement = connection.prepareStatement(sql);
                                                preparedStatement.setInt(1, newArmor);
                                                preparedStatement.executeUpdate();

                                            } catch (InputMismatchException exception) {
                                                armorScanner.nextLine();
                                                System.out.println("You have entered an invalid choice. Please try again.");
                                                continue;
                                            }
                                        }

                                        break;

                                    case 0:
                                        return;

                                    default:
                                        pickScanner.nextLine();
                                        System.out.println("You have entered an invalid choice. Please try again.");
                                        continue;

                                }

                            } catch (InputMismatchException exception) {
                                pickScanner.nextLine();
                                System.out.println("You have entered an invalid choice. Please try again.");
                                continue;
                            }

                        }
                    }

                } catch (InputMismatchException exception) {
                    pickScanner.nextLine();
                    System.out.println("You have entered an invalid choice. Please try again.");
                    continue;
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

    }

    public static void deleteMonsters() {


        boolean deciding = true;
        Scanner createScanner = new Scanner(System.in);
        String theme = "";

        while (deciding) {

            System.out.println("What monster type would you like to delete monsters from??");
            System.out.println("1. Automatons");
            System.out.println("2. Bandits");
            System.out.println("3. Beasts");
            System.out.println("4. Demons");
            System.out.println("5. Deserters");
            System.out.println("6. Fae");
            System.out.println("7. Flooded");
            System.out.println("8. Ghosts");
            System.out.println("9. Goblins");
            System.out.println("10. Golems");
            System.out.println("11. Mages");
            System.out.println("12. Oozes");
            System.out.println("13. Pirates");
            System.out.println("14. Plants");
            System.out.println("15. Skeletons");
            System.out.println("16. Spiders");
            System.out.println("17. Vampires");
            System.out.println("18. Void Creatures");
            System.out.println("19. Wild Men");
            System.out.println("20. Zombies");

            try {

                int choice = createScanner.nextInt();

                switch (choice) {

                    case 1:
                        theme = "automatons";
                        break;
                    case 2:
                        theme = "bandits";
                        break;
                    case 3:
                        theme = "beasts";
                        break;
                    case 4:
                        theme = "demons";
                        break;
                    case 5:
                        theme = "deserters";
                        break;
                    case 6:
                        theme = "fae";
                        break;
                    case 7:
                        theme = "flooded";
                        break;
                    case 8:
                        theme = "ghosts";
                        break;
                    case 9:
                        theme = "goblins";
                        break;
                    case 10:
                        theme = "golems";
                        break;
                    case 11:
                        theme = "mages";
                        break;
                    case 12:
                        theme = "oozes";
                        break;
                    case 13:
                        theme = "pirates";
                        break;
                    case 14:
                        theme = "plants";
                        break;
                    case 15:
                        theme = "skeletons";
                        break;
                    case 16:
                        theme = "spiders";
                        break;
                    case 17:
                        theme = "vampires";
                        break;
                    case 18:
                        theme = "void Creatures";
                        break;
                    case 19:
                        theme = "wild Men";
                        break;
                    case 20:
                        theme = "zombies";
                        break;
                    default:
                        System.out.println("You have entered an invalid choice. Please try again.");
                        continue;
                }

            } catch (InputMismatchException exception) {
                createScanner.nextLine();
                System.out.println("You have entered an invalid choice. Please try again.");
                continue;
            }

            break;

        }

        // Instantiate things we're about to use
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        ArrayList<Monster> monsters = new ArrayList<>();

        try {
            // Load MySQL driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Set up the connection with the DB
            connection = DriverManager
                    .getConnection(connectionString);
            statement = connection.createStatement();

            resultSet = statement
                    .executeQuery("select * from dungeoncrawler." + theme.toLowerCase());

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

                monsters.add(new Monster(name, description, theme, type, tier, health, damage, armor));

            }


            Scanner pickScanner = new Scanner(System.in);
            boolean picking = true;


            while (picking) {

                System.out.println("Which monster would you like to delete?");

                for (int i = 0; i < monsters.size(); i++) {
                    System.out.println((i + 1) + ". " + monsters.get(i).getName() + ". "
                            + "\n "
                            + monsters.get(i).getDescription()
                            + "\n"
                            + " It is Type " + monsters.get(i).getType()
                            + ", Tier " + monsters.get(i).getTier()
                            + ", and has " + monsters.get(i).getHealth() + " Health, "
                            + monsters.get(i).getDamage() + " Damage, and "
                            + monsters.get(i).getArmor() + " Armor.");
                }

                try {

                    int pick = pickScanner.nextInt();
                    if (pick < 0 ^ pick > monsters.size()) {
                        System.out.println("You have entered an invalid choice. Please try again.");
                        continue;
                    } else {
                        String sql = "";
                        PreparedStatement preparedStatement;
                        sql = "DELETE from dungeoncrawler." + theme + " WHERE id = " + pick;
                        preparedStatement = connection.prepareStatement(sql);
                        preparedStatement.executeUpdate();
                        break;
                    }
                } catch (InputMismatchException exception) {
                    createScanner.nextLine();
                    System.out.println("You have entered an invalid choice. Please try again.");
                    continue;
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


    }

    public static Dungeon generateDungeon(Player player, PlayerCharacter pc) {

        Dungeon dungeon = new Dungeon(player, pc);
        dungeon.setPlayer(player);
        dungeon.generateTheme();
        dungeon.generateRooms();

        return dungeon;

    }

    public static void endGame(PlayerCharacter pc) {

        // End the game.

        System.out.println();
        System.out.println("Your journey is over.");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Whether to be content with the path you forged is on your head alone.");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        pc.getPlayer().calculateScore();

        if (pc.getPlayer().getScore() > pc.getPlayer().getHighScore()) {
            pc.getPlayer().setHighScore(pc.getPlayer().getScore());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("You achieved a new high score!");
        }

        System.out.println("GAME OVER");

        System.exit(0);

    }

    public static final class StringStore {

        public static String connectionString;

        public String getConnectionString() {
            return connectionString;
        }

        public static void setConnectionString(String NewConnectionString) {
            connectionString = NewConnectionString;
        }
    }

}
