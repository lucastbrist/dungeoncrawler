# **DUNGEONCRAWLER**
A proof-of-concept old-school text-based fantasy dungeon-crawler written in Java.

## About
Remember Zork? Remember the newer dungeoncrawlers inspired by it but taking it to the next level with 3D graphics, such as Legend of Grimrock? Remember parodies of Zork in popular media, like the functional Thy Dungeonman game hosted on the old homestarrunner.com website of the early 'aughts? 

Well this isn't as cool as any of those.

_But_ it's a fun proof-of-concept exploring what Java and an amateur's hand can do to produce a simulacrum of something as cool as that. In this game, you will create a character, selecting from various traditional Fantasy species (with a unique one to pick from as well), pick a character class from the classic Fantasy character archetypes of Warrior, Mage, and Thief, further diversify your character with some other unique character build choices, and then set off on  your adventure into a dungeon, where you will fight enemies in turn-based combat from room-to-room, looting as you go, or sneaking past them all and snatching the treasure from under their noses, if you're talented enough. 

Between rooms, you'll level up, and by the end of the dungeon, you may either retire or move on to a new dungeon--and it's all controlled by the command line and written in Java using entirely unique code (apart from boilerplate basic Java class libraries), with ground-up models to represent the player character, the monsters, the loot, the rooms, and more, as well as JDBC connections with MySQL databases to store and access data.

## Installation
### Requirements
* A working computer and OS
* A Java IDE such as IntelliJ IDEA
* MySQL and MySQL Workbench
* The JDBC MySQL Connector located here (select "Platform Independent" fom the dropdown): https://dev.mysql.com/downloads/connector/j/
  
###Step-by-Step
1. To begin, clone this github repository onto your machine and extract it.
2. Using MySQL Workbench, create a server and import the schema included in dungeoncrawler/data called "dungeoncrawler.sql." This should create the database the game will need to run.
3. Then, in the DungeonCrawler/src folder, create a txt file named `connectionString`. In it, paste the following and only the following using any text editor: `jdbc:mysql://localhost/dungeoncrawler?user=username&password=password&useSSL=false&allowPublicKeyRetrieval=true`. Replace the "username" and "password" fields with that of your MySQL database server. If your server is not hosted at the same address, you will also have to change the `localhost` field.
4. Create a new project in your IDE. In IntelliJ, click on `File`, then `New`, then `Project from Existing Sources` and browse to where you extracted the repository.
5. Ensure that MySQL Server is running. On Windows, you can do this by pressing `WIN + R` and typing `services` and then hitting `ENTER`, then browsing until you find `MySQL180`, right-clicking, and pressing `Start`.
6. Open `dungeoncrawler/src/controllers/dungeoncrawlercontroller.java` in your IDE and run it! The command line should open, and you will be prompted to play being entering text!
7. After entering your username, you will be asked to enter anything to continue. If you type, in all caps, `CRUD`, you will enter a special back-end mode to create, read, update, and delete data from the MySQL schema the game gets its data from.

## How to Play
If there is no text being printed actively, it means the game is waiting for your input! Read the last few lines printed to see what selection it is waiting for you to make. Your selections should always be clearly marked, usually with a number and awaiting for you to merely enter that corresponding numeral. 

Creating your character is straight forward, and efter that, upon entering the dungeon, you will proceed through its rooms in a linear fashion. When you enter a room, there will certainly be enemies--you are given the option to attack, hide, look around, or leave. Leaving (or fleeing if you're already in combat) will return you to the entrance of the dungeon, with nothing in it, where you can recuperate and get your bearings. Looking around will never waste your turn if combat has started, except if you follow up your inspection of the room by using an item, like one of your potions or attempting to loot the room. Looting the room can only be done if you are hiding from the enemy or all enemies are dead. When the room is clear and you have finished your actions in the room, you may leave and move to the next room. When all rooms in the dungeon are done, you are given the choice to retire or generate a new dungeon.

Helpful tip: Enemy warriors have the most health, but deal the least damage! Archers deal more damage, and Mages deal even more! Focus on the sorcerous foes with higher health pools!

## Caveats

This proof of concept has no tutorial, only one functioning enemy theme, only placeholder items, oodles of placeholder text, and no boss rooms.

The only functional enemy type is Skeletons, and almost all room and equipment names and descriptions, as well as that of moves you can take in combat, are filled with placeholder text.
Additionally, in CRUD mode, the only currently functional methods are for the Monster tables, and the only Monster table set up for the proof of concept is the Skeleton one.
You will receive either exception errors or simple soft-locking of the game loop if you try anything else.

There are lots of fun calculations going on behind the scenes that I implore anyone to dig around in and look at. On the surface, it's quite simple (and compared to AAA games, of course, it still is), but there's some cool and complex character building that can be done for how simple it is, and a lot of runtime randomization that goes on with stats and other numbers. Despite this, it is horribly unbalanced, and the simplicities and lack of features means you have very few recourses against such imbalance. You are likely to be overwhelmed and killed by a full room of monsters by, at the very latest, the beginning of your second dungeon.

There are a fair few unfinished/unused variables and methods and constructors, and it would also be better built as a Maven or Gradle project. As I built this for the capstone of my second Java course, I had not gotten into those resources yet, and with how long this project took, I wanted to get it out there instead of spending the time to rework it with those new resources. 

I do not pretend this is a flawlessly built project, but it's mine, and I put a lot of effort into it, and learned a lot. I was also setting it up to potentially be expanded on down the line.

There is one major bug I have not squashed. When getting into prolonged combat at higher levels, if there is a Skeleton Knight in combat and it becomes that Skeleton Knight's turn, the game will hang. Attachign a debugger at this point does nothing for me, and IntelliJ's debugger screen sits on "Collecting data..." eternally.

### Potential Improvements
An intended "final product" of this basic concept would have 20 enemy themes with 4-10 monsters each, each with their own moves and level progression, as well as around 50 each of armors and weapons and magical trinkets. There would be a tutorial, followed by you traversing through 3-5 rooms of a dungeon and then the boss room, fighting a unique boss enemy. If you survive, the dungeon is cleared, your stats are printed, and you are prompted with the choice of either retiring your adventurer or taking them to a new dungeon.

Other ambitions included having NPC party member allies you can control alongside your player, sub-classes to specialize into after your initial choice of the main three archetypes, spell choices for sorcerers and unique moves for each class gated by level, moves tied to weapon types, damage and protection types (slashing, piercing, blunt, etc), unique non-combat encounters, more interactivity to rooms, less generous level ups, a hub area to return to between dungeons and sell and buy gear, and linear, pre-built campaigns with specific characters and dungeons. But I'll save that for some Unreal Engine project, probably, where I have to build less from scratch. Lol.

Other improvements to the experience would be slower and better formatted printing of text for readability, an initiative tracker to help the player know what monster will move next to better inform their actions in a given turn (initiative in general was quite difficult to make function, and in it's current state a tracker would likely be impossible, so this would require a complete rework of initiative), and, of course, filled out placeholder text. 

## Final Notes
It was a tremendous effort to build this all from scratch, and I learned a lot! I hope you enjoy it.
