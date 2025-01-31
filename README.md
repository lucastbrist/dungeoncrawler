Welcome to Dungeoncrawler!

This is a proof-of-concept text-based old-school Fantasy dungeon-crawler written in Java. The only functional enemy type is Skeletons, and almost all room and equipment names and descriptions, as well as that of moves you can take in combat, are filled with placeholder text.

Additionally, after typing in your username when prompted, you can enter a secret mod when prompted to continue by typing, in all caps and without quotes, "CRUD" This will allow you to engage in JDBC CRUD, creating, reading, updating, or deleting data from the database tables this game draws monster, room, equipment, and other data from. Currently, the only functional methods to CRUD are for the Monster tables, and the only Monster table set up for the proof of concept is the Skeleton one.

You will receive either exception errors or simple soft-locking of the game loop if you try anything else.

Otherwise, you can type literally anything else on the screen that lets you begin CRUD, and you will jump into the game.

There are lots of fun calculations going on behind the scenes that I implore anyone to dig around in and look at. On the surface, it's quite simple (and compared to AAA games, of course, it still is), but there's some cool and complex character building that can be done for how simple it is, and a lot of runtime randomization that goes on with stats and other numbers.

An intended "final product" of this basic concept would have 20 enemy themes with 4-10 monsters each, each with their own moves and level progression, as well as around 50 each of armors and weapons and magical trinkets. There would be a tutorial, followed by you traversing through 3-5 rooms of a dungeon and then the boss room, fighting a unique boss enemy. If you survive, the dungeon is cleared, your stats are printed, and you are prompted with the choice of either retiring your adventurer or taking them to a new dungeon.

This proof of concept has no tutorial, only one functioning enemy theme, only placeholder items, and no boss rooms.

Other ambitions included having NPC party member allies you can control alongside your player, sub-classes to specialize into after your initial choice of the main three archetypes, unique non-combat encounters, more interactivity to rooms, less generous level ups, a hub area to return to between dungeons and sell and buy gear, and linear, pre-built campaigns with specific characters and dungeons. But I'll save that for some Unreal Engine project, probably, where I have to build less from scratch. Lol.

There are a fair few unfinished/unused variables and methods and constructors. I do not pretend this is a flawlessly built project, but it's mine, and I put a lot of effort into it, and learned a lot. I was also setting it up to potentially be expanded on down the line.

To begin, create a server in MySQL database and import the scheme included in dungeoncrawler/data called "dungeoncrawler.sql." This should create the database the game will need to run.

Then, in the DungeonCrawler/data folder, create a txt file named "connectionString" without quotes. In it, paste the following and only the following using any text editor (without quotes):

"jdbc:mysql://localhost/dungeoncrawler?user=username&password=password&useSSL=false&allowPublicKeyRetrieval=true"

Replace the "username" and "password" fields with that of your MySQL database server.
