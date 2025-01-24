package DungeonCrawler.models;

public class Player {

    /// The player profile, used to track usernames, scores, and the associated current character.

    // The player's chosen username
    String username;

    // The PlayerCharacter object currently associated with the player
    PlayerCharacter character;

    // Calculated from several elements of the player's character,
    // this is used to keep track of the current playthrough's score
    int score;

    // The player profile's highest ever achieved score
    int highScore;

    /// Constructors

    public Player() {
    }

    public Player(String username) {
        this.username = username;
    }

    public Player(PlayerCharacter character) {
        this.character = character;
    }

    public Player(String username, PlayerCharacter character) {
        this.username = username;
        this.character = character;
    }

    public Player(String username, PlayerCharacter character, int score, int highScore) {
        this.username = username;
        this.character = character;
        this.score = score;
        this.highScore = highScore;
    }

    /// Getters & Setters

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public PlayerCharacter getCharacter() {
        return character;
    }

    public void setCharacter(PlayerCharacter character) {
        this.character = character;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getHighScore() {
        return highScore;
    }

    public void setHighScore(int highScore) {
        this.highScore = highScore;
    }

    public void calculateScore() {

        /// Method to calculate the player's final score upon completing a dungeon

        /// The magic:

        // The player character's gold
        // + the value of their weapon
        // + the value of their armor
        // + the value of their trinket
        // + their level
        // + the amount of unused potions in their inventory multiplied by two
        // + the amount of rooms in the dungeon they cleared multiplied by ten

        // doThing()
        this.setScore(this.getCharacter().getGold()
                + this.getCharacter().getWeapon().getValue()
                + this.getCharacter().getArmor().getValue()
                + this.getCharacter().getTrinket().getValue()
                + this.getCharacter().getLevel()
                + (this.getCharacter().getPotions() * 2)
                + (this.getCharacter().getRoomsCleared() * 10));

        // Add 100 points if the player never used a revive
        if (!this.getCharacter().isDeathTokenUsed()) {
            this.setScore((this.getScore() + 100));
        }

    }

}
