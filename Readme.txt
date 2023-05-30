Ysaach Habon Readme Reflection. 

/**********************************************************************
 *  Describe any serious problems you encountered.                    
 **********************************************************************/

    There is one main problem in my code, which is the revealing of adjacent tiles.
    I was having trouble defining the borders of my function safespace.update that 
    uses the helper "adjacentBlankRevealer()" to unreveal each safespace in a 
    certain bounds. 
    

    In my total, experience out of coding the entire project, I found that I had
    the most difficult with "matching the field with the safe spacies to reveal its
    adjacent tiles. I also had a difficult time in making sure that my first click was
    not a bomb, but after going to office hours, I realized that instead of using 
    a for loop, I should use a while loop with if statements that re-roll the 
    RANDOM positions until the entire function is filled. 

    

/**********************************************************************
 * Did you add anything special? 
 *********************************************************************/

    One extra credit feature was that I added an interactive smiley face, like the
    original minesweeper game, and so I tiled my minesweeper-ish. Further, I had
    a special border around my win statement.

    My smiley face turns blue when lost with a :0 face and turns back yellow. It is 
    also used to reset the board, when the game end. 

    I think I should get a point for these two visual affects because it does 
    show decorative but also because it is interactive and useful to the code. The 
    face with it's definite likeness to the 

/**********************************************************************
    * A brief description of each file and its purpose                          
 **********************************************************************/

    1. Readme is a readme! 

    2) MSCellInterface (File 2)
        This interface outlines the basic functions that I need to create organized
        cells or tiles, this would be my bombs and safespaces. This helped my
        thoughts.
        
        a) Bomb.java (File 3)
            This is my bomb class which helps me divides which tiles are dangerous 
            and which tiles are needed or safe. With a bomb object, I can create
            a bomb array needed to create the minefield by placing bombs first and 
            then generating the safetiles after. This differs from my safeSpace, 
            because it counts the boolean isExploded, which is used for my game's 
            win and lose functions. 

            Bombs had an simple functions, like getters for placement purposes. 

            Bombs made bombies[], needed for many functions and creation of field.

        b) Safespaces.java (File 4)
            This is my safespaces which differs from bomb because it has a surrBombs
            value which is needed to display the number of bombs around it. Further,
            the bomb class needed to be seperated because it must count the bombs and
            it would be easier to do so if bomb was its own object.

            Safespace reacted to Bombs, like how safespaces were placed in everyplace
            that a bomb is not. 
           Safespace made SafeSpacies[] which helped to create the minefield.  
    

    3)MSBoardInterface.java (file 5)
        This interface outlines the basic functions that I needed to create my board
        array. At first, this helped me outlined my thoughts and made sure that my
        board was organized. 
            
            a) Board.java (file 6) 

            This board and file is used as the main game functions and is the longest
            The outline of the board is laid as followed.  
            Outline of Board File: 
            * 1. General 
            * 2. Generation of Board and Board Entities 
            * 3. Update and Gameplay 

            1) the General constructs the logistics and the headers to ensure 
            that the game is visible, also gives values to has didFirstPLayerClick
            to ensure that win conditions and arrays to do not have null-pointers.
                I further outlined in the board file. 

            2) 
            The board first constructs an empty board and after the first click,
            randomly generates the bombs and the tiles, using the spawn function. 
            Spawn() function alongside the firstdraw() drawing the starting board,
            ensures that the game is visible even before it starts.

            3) 
            The board play functions is the updates and the its finish is in its. 
            By responding to each mouse click, this reveals the respective 
            safe space or bomb, and using the minefield, if it hits bomb, it will
            set true the boolean isExploded() and ending the game. 
        
            4) Game.java (File 7)
            This is the final file which basically uses all the previous classes to 
            create the game minesweeper. It is made be inputting java game in command
            line.

            This has if statements to tell when to end and start game. 

/**********************************************************************
    * Any additional features you added	                    
 **********************************************************************/

    I added a reveal function which is needed for testing but I could have added
    a reveal button for fun. For extra credit, I made a interactive smiley face which
    was to improve visuals effects. I also added an end and victory screen, which
    I think helped to improve the game look and experience. 

/**********************************************************************
    * Instructions for how to run your program (which class to run,
    * any command line arguments)
**********************************************************************/

    To run the program, all that is needed is java Game. No text files are read. 

/**********************************************************************
 *  List any other comments here.                                     
 **********************************************************************/

    Oddly, if you play the game and reset it many times. A black circle will appear
    where the smiley face is, but if you click on the minefield arena, the black
    circle will disappear and the game will be ready to play once more. This I 
    suspect is just a PennDraw being overloaded. 

    While the game seems easy to complete, it is because my adjacentBlankRevealer() 
    function is causing all the blank squares in the minefield to be revealed after
    the first click. However, the game will not automatically win or lose because 
    the amount of number tiles, are usually cluster and thus those, safe tiles
    are not revealed. This is the only bug that needed to be fixed. 

    I want to just say that I went to office hours and while I tried to get it
    fixed, with the TA's. I truly do think I did my best and even debugged a lot,
    I just wished that I got some time with the TAs. Anyways, thanks for a great 
    semester and I hope everyone in CIS the happy finals and holiday. SHOUTOUT to 
    professory HARRY and ERIC for helping me with my bomb implentation. 
