# The Finding of I, Zack

The player used WASD to move their character and the arrow keys to fire directionally.
The goal of the game is to find the exit, to do this they must walk through multiple rooms, fighting enemies and collecting items which provide benefits to the player (eg. more damage). These retrievable items will appear visually onto the player.


Libraries:
SWING for GUI because we are familiar with it and has enough tools to produce the desired outcome.

JavaFX as a possible alternative.

JUnit will be used for all of our testing.

Library Discussion

Saving/loading/info

Author: Jesse

Reviewer: Theo

Tester: Bryn

    Take the current state of all elements in the game and save them to a file and also handle loading from a file.




The TheFindingOfIZack.World or whatever?

Author: Ryan

Reviewer: Jesse

Tester: Theo

    Levels
    Rooms(item locations etc.)


TheFindingOfIZack.TheFindingOfIZack.Entities/TheFindingOfIZack.Items

Author: Ben

Reviewer: Ryan

Tester: Jesse

    Health
    Position
    TheFindingOfIZack.Behaviour - (This is its own library)
    Player
    Different enemy flavours, just cabbage things/bosses
    TheFindingOfIZack.Items   (Effect on player
            Position in game
            Maybe like a weight/size thing maybe?
            Health pickups)

TheFindingOfIZack.View
Author: Bryn

Reviewer: Ben

Tester: Ryan

    Throws everything together
    JFrame or whatever
    Lots of panels
    Open menu pause menu etc.
    Show player score/health/equipped items
    
TheFindingOfIZack.Behaviour

Author: Theo

Reviewer: Bryn

Tester: Ben

    AI movements etc.
    Keyboard input for player
    Collisions with walls/obstaTester:cles 


Other things to do

    Make it all pretty
    Make a story
    Add some phat beats maybe
    
