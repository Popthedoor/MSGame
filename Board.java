/**
 * Name: Ysaach Habon
 * Pennkey: yhabon
 * Execution: N/A class
 *
 * Description: Board draws the minefield, all cells. This handles the formation of
 * the entire game: Headers, Outline, Board, and Clicks.  
 * 
 * Outline of Board File: 
 * 1. General 
 * 2. Generation of Board and Board Entities 
 * 3. Update and Gameplay 
 *
**/
public class Board implements MSBoardInterface {
     //instance variables but these change with EC
    private int width, height;
    
    //The Bombs & SafeSpace Array for organization
    private Bomb[] bombies;
    private SafeSpace[] safeSpacies;
    
    /* This would be the keyMineField, that shows all bombs and SafeSpace locations.
    * 1 would mean there is a bomb at that specfic x,y coord.
    * 0 would mean there is no bomb at that specfic x,y coord.
    */
    
    private int[][] minefield;

    private boolean mouseListeningMode;
    
    //This is used to makesure that first click isn't a bomb.
    private boolean didFirstPLayerClick;
    
    //Constructor |
    public Board() {
         //This contains the basic statistics of the game.
        this.width = 600;
        this.height = 700;
        PennDraw.setXscale(0, 11);
        PennDraw.setYscale(0, 12);
        
        //Bombies.length = # of bombs left.
        bombies = new Bomb[10];
        safeSpacies = new SafeSpace[71];
        //Provides the key used for surroundingbombcounter.
        minefield = new int[9][9];
        
        mouseListeningMode = true;
        didFirstPLayerClick = false;
        
        /* Basics Drawings
        *  1. Background & Header
        *  2. The Playable Areana Outline
        *  3. Outlines for Squares
        */

       // 1a) Background
        PennDraw.clear(PennDraw.LIGHT_GRAY);
        
        // 1b) Headers
        PennDraw.setPenColor(PennDraw.GRAY);
        PennDraw.filledRectangle(5.5, 10.5, 5.5, 1.5);
        PennDraw.setPenColor(PennDraw.LIGHT_GRAY);
        PennDraw.filledRectangle(5.5, 10.5, 5, 1.25);
        
        // 2) Outline of the Playable Arena
        PennDraw.setPenColor(PennDraw.BLACK);
        PennDraw.setPenRadius(.0175);
        PennDraw.square(4.5, 4.5, 4.5);
        
        //3) Outline of the little tiles
        PennDraw.setPenRadius();
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                PennDraw.square(row + .5, col + .5, .5);
            }
        }
    }
    
    //2 GENERATION of BOARD and BOARD's ENTITIES | Following three Functions

    /** 1) Starting Screen
     * Inputs: N/A
     * Outputs: 
     * Description: This function draws the inital screen indictating where to click.
     * this is only used before the game starts to show that game is inactive. so
     * this is the first draw function. 
    */

    public void firstdraw() {
        PennDraw.clear(105, 105, 105);
        PennDraw.setPenColor(PennDraw.GRAY);
        PennDraw.filledRectangle(5.5, 10.5, 5.5, 1.5);
        PennDraw.setPenColor(PennDraw.LIGHT_GRAY);
        PennDraw.filledRectangle(5.5, 10.5, 5, 1.25);
        PennDraw.filledRectangle(10, 4.5, 1, 4.5);

        //TILECHECK OG TITLE 
        PennDraw.setPenColor(PennDraw.BLACK);
        PennDraw.setFontSize(35);
        PennDraw.setFontBold();
        PennDraw.text(5.5, 10.5, "|MINESWEEPER-ISH|");
        PennDraw.setFontSize(18);

        //This is a smiley face for a win! 
        PennDraw.setPenColor(PennDraw.YELLOW);
        PennDraw.filledCircle(10, 8, .7);
        PennDraw.setPenColor(PennDraw.BLACK);
        PennDraw.filledCircle(10.25, 8.2, .10);
        PennDraw.filledCircle(9.75, 8.2, .10);
        PennDraw.polyline(9.65, 7.9, 10, 7.5, 10.35, 7.9);

        //outline of the squares 
        PennDraw.setPenColor(PennDraw.BLACK);
        PennDraw.setPenRadius(.0175);
        PennDraw.square(4.5, 4.5, 4.5);
        PennDraw.setPenRadius();
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                PennDraw.square(row + .5 , col + .5, .5);
            }
        }
        //Click any sqaure to begin! 
        PennDraw.setPenColor(PennDraw.LIGHT_GRAY);
        PennDraw.filledRectangle(4.5, 4.5, 3.5, .5);
        PennDraw.setPenColor(PennDraw.WHITE);
        PennDraw.filledRectangle(4.5, 4.5, 4.3, .5);
        PennDraw.setPenColor(PennDraw.BLACK);
        PennDraw.text(4.5, 4.5, "Click Any Square, within the border, to START!!");
        PennDraw.setFontPlain();

    }

    /** 2) Generation of bombs, safespacies, and minefield. 
     * Inputs: Starting X and Y Position 
     * Outputs: N/A
     * Description: This spawn function: Pre1st: Using the starting X and Y, it 
     * creates a safe space and does not allow bombs to spawn there. 
     * 1st) Generates the Bombs in a random position between values [0-9][0-9], 
     * then adds 0.5 to center on program. Simultaneously, makes a minefield to 
     * record where bombs are with 1. 2) The code itterates the entire minefield, 
     * checking where there isn't a 1, Puts a 0 to indiciate that is a safespace. 
     * 3) Then, using the minefield[][]==0, creates a safespace at the minefield's 
     * row and col, placing it into an array. 4) Using minefield as a key, this 
     * checks each tile and counts the bombs. 
    */

    public void spawn(double startingX, double startingY) {
        int firstXSpace = (int) startingX;
        int firstYSpace = (int) startingY; 
        minefield[firstXSpace][firstYSpace] = 0;
        safeSpacies[0] = new SafeSpace(firstXSpace + 0.5, firstYSpace + 0.5);

        //Creation of Bombs
        int counter = 0;
        while (bombies[9] == null) {
            int xRandoPositon = (int) (Math.random() * 9);
            int yRandoPosition = (int) (Math.random() * 9);
            //this ensures that no two mines share the same coords or is the 
            // the starting number. 
            if (minefield[xRandoPositon][yRandoPosition] == 0 && xRandoPositon != 
                firstXSpace && yRandoPosition != firstYSpace) {
                bombies[counter] = new Bomb(xRandoPositon + 0.5, 
                yRandoPosition + 0.5);
                minefield[xRandoPositon][yRandoPosition] = 1;
                counter++;
            }
        }

        //2) Creation of minefield
         for (int row = 0; row < minefield.length; row++) {
            for (int col = 0; col < minefield.length; col++) {
                if (minefield[row][col] != 1) {
                    minefield[row][col] = 0;
                }
            }
        }

        // 3) This creates safespaces using minefield's 0 and 1 as guide.
        int incremeter = 70;
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                if (minefield[row][col] == 0) {
                    if (incremeter >= 0) {
                    safeSpacies[70 - incremeter] = new
                    SafeSpace(row + 0.5, col + 0.5);
                    incremeter = incremeter - 1;
                    }
                }
            }
        }
        
        surrBombsCounterandSetter();
    }

    /** 3) Counts the safespacies values. 
     * Inputs: None
     * Outputs: None
     * Description: This function counts a safespacies surronding bombs by using 
     * the minefield key. Checking if the minefield position [xpos +/- 1] and 
     * [ypos +/-] around the safespacies has a bombs, then counts that and then 
     * changes the safespacies value. 
    */

    public void surrBombsCounterandSetter() {
        // This part of the counts the number of bombs.
        for (int i = 0; i < safeSpacies.length; i++) {
            int xPos = (int) (safeSpacies[i].xPositionGetter() - .5);
            int yPos = (int) (safeSpacies[i].yPositionGetter() - .5);
            int surroundingBombs = 0;
            
            if (xPos != 0 && yPos < minefield.length - 1) {
                if (minefield[xPos - 1][yPos + 1] == 1) {
                    surroundingBombs++;
                }
            }
            
            if (yPos < minefield.length - 1) {
                if (minefield[xPos][yPos + 1] == 1) {
                    surroundingBombs++;
                }
            }
            
            if (yPos < minefield.length - 1 && xPos < minefield.length - 1) {
                if (minefield[xPos + 1][yPos + 1] == 1) {
                    surroundingBombs++;
                }
            }
            
            if (xPos != 0) {
                if (minefield[xPos - 1][yPos] == 1) {
                    surroundingBombs++;
                }
            }
            
            if (xPos < minefield.length - 1) {
                if (minefield[xPos + 1][yPos] == 1) {
                    surroundingBombs++;
                }
            }
            
            if (xPos != 0 && yPos != 0) {
                if (minefield[xPos - 1][yPos - 1] == 1) {
                    surroundingBombs++;
                }
            }
            
            if (yPos != 0) {
                if (minefield[xPos][yPos - 1] == 1) {
                    surroundingBombs++;
                }
            }
            
            if (yPos != 0 && xPos < minefield.length - 1) {
                if (minefield[xPos + 1][yPos - 1] == 1) {
                    surroundingBombs++;
                }
            }
            
            //changes the surroundingBombs value in that safe space.
            safeSpacies[i].surrBombsSetter(surroundingBombs);
        }
    }
    
    
    // 2) Update, Draw, and Gameplay Functions | Following 4 functions 

    /**
     * Inputs: None
     * Outputs: None
     * Description: This functions updates the board or tiles, to reveal them, if
     * & only if the mouse has been pressed. 
    */
    public void updateBoardData() {
        //If mouseListeningMode is true, this means it ready to be clicked.
        if (mouseListeningMode) {
            mouseListeningMode = false; 
            double xMouse = PennDraw.mouseX();
            double yMouse = PennDraw.mouseY();
            if (PennDraw.mousePressed()) {
                for (int i = 0; i < bombies.length; i++) {
                    bombies[i].updateData();
                }
                for (int i = 0; i < safeSpacies.length; i++) {
                    safeSpacies[i].updateData();
                    double xTemp = safeSpacies[i].xPositionGetter() - .5;
                    double yTemp = safeSpacies[i].yPositionGetter() - .5;
                }
            }
            mouseListeningMode = true;
        }
    }
    
     /**
     * Inputs: N/A
     * Outputs: Draws the Bombs and Bombies
     * Description: This draws all the safespacies and bombies,
     * and screen after their update.
    */

    public void draw() {

        PennDraw.clear(105, 105, 105);
        PennDraw.setPenColor(PennDraw.GRAY);
        PennDraw.filledRectangle(5.5, 10.5, 5.5, 1.5);
        PennDraw.setPenColor(PennDraw.LIGHT_GRAY);
        PennDraw.filledRectangle(5.5, 10.5, 5, 1.25);
        PennDraw.filledRectangle(10, 4.5, 1, 4.5);

        //TILECHECK OG TITLE 
        PennDraw.setPenColor(PennDraw.BLACK);
        PennDraw.setFontSize(35);
        PennDraw.setFontBold();
        PennDraw.text(5.5, 10.5, "|MINESWEEPER-ISH|");
        PennDraw.setFontSize(18);
        PennDraw.setPenRadius();

        //This is a smiley face for a win! 
        PennDraw.setPenColor(PennDraw.YELLOW);
        PennDraw.filledCircle(10, 8, .7);
        PennDraw.setPenColor(PennDraw.BLACK);
        PennDraw.filledCircle(10.25, 8.2, .10);
        PennDraw.filledCircle(9.75, 8.2, .10);
        PennDraw.polyline(9.65, 7.9, 10, 7.5, 10.35, 7.9);

        //Outline of the Box 
        PennDraw.setPenColor(PennDraw.BLACK);
        PennDraw.setPenRadius(.0175);
        PennDraw.square(4.5, 4.5, 4.5);
        //Outline of the Tiles 
        PennDraw.setPenColor(PennDraw.BLACK);
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                PennDraw.square(row + .5 , col + .5, .5);
            }
        }

        //Draws the Bombs
        for (int p = 0; p < bombies.length; p++) {
            bombies[p].draw();
        }
        //Draws the SafeSpaces
        for (int j = 0; j < safeSpacies.length; j++) {
            safeSpacies[j].draw();
        }
        PennDraw.advance();
    }

    /**
     * Inputs: Coords of the starting click.
     * Outputs: N/A
     * Description: DISCLAIMER: This function works but has a bug for its borders.
     * This functions checks each surrounding cell in based upon the first click,and
     * if the cell is a blank cell. It will reveal that blank cell and then 
     * goes to the next cell. 
    */

/*
    public void adjacentBlankRevealer(double xTemp, double yTemp) {
        //Refers to minefield 
        int xPos = (int) xTemp;
        int yPos = (int) yTemp;
        double xx = xPos + .5;
        double yy = yPos + .5;
        //Reveal the surrounding tiles using minefield key.
        if (xPos != 0 && yPos < minefield.length - 1) {
            if (minefield[xPos - 1][yPos + 1] == 0) {
                for (int i = 0; i < safeSpacies.length; i++) {
                    //this is base + .5
                    double xBoundedPos = safeSpacies[i].xPositionGetter();
                    double yBoundedPos = safeSpacies[i].yPositionGetter();
                    if (!safeSpacies[i].isRevealedGetter() && safeSpacies[i].surrBombsGetter() == 0 && xBoundedPos = xPos - 1 && yBoundedPos = yPos + 1) {
                            safeSpacies[i].reveal();
                            
                    }
                }   
            }
        }

        if (yPos < minefield.length - 1) {
            if (minefield[xPos][yPos + 1] == 0) {
                for (int i = 0; i < safeSpacies.length; i++) {
                    double xBoundedPos = safeSpacies[i].xPositionGetter();
                    double yBoundedPos = safeSpacies[i].yPositionGetter();
                      if (!safeSpacies[i].isRevealedGetter() && safeSpacies[i].surrBombsGetter() == 0 && xBoundedPos = xPos  && yBoundedPos = yPos + 1) {
                        safeSpacies[i].reveal();
                    }
                }
            }
        }

        if (yPos < minefield.length - 1 && xPos < minefield.length - 1) {
            if (minefield[xPos + 1][yPos + 1] == 0) {
                for (int i = 0; i < safeSpacies.length; i++) {
                    double xBoundedPos = safeSpacies[i].xPositionGetter();
                    double yBoundedPos = safeSpacies[i].yPositionGetter();
                    if (!safeSpacies[i].isRevealedGetter() && 
                    xBoundedPos == xPos + 1 && yBoundedPos = yPos + 1) {
                        safeSpacies[i].reveal();
                    }
                }
            }
        }

        if (xPos != 0) {
            if (minefield[xPos - 1][yPos] == 0) {
                for (int i = 0; i < safeSpacies.length; i++) {
                    double xBoundedPos = safeSpacies[i].xPositionGetter();
                    double yBoundedPos = safeSpacies[i].yPositionGetter();
                    if (!safeSpacies[i].isRevealedGetter() && 
                     == xPos && yBoundedPos = yPos - 1) {
                        safeSpacies[i].reveal();
                    }
                }
            }
        }

        if (xPos < minefield.length - 1) {
            if (minefield[xPos + 1][yPos] == 0) {
                for (int i = 0; i < safeSpacies.length; i++) {
                    double xBoundedPos = safeSpacies[i].xPositionGetter();
                    double yBoundedPos = safeSpacies[i].yPositionGetter();
                    if (!safeSpacies[i].isRevealedGetter() && 
                    xBoundedPos == xPos + 1 && yBoundedPos = yPos) {
                        safeSpacies[i].reveal();
                    }
                }
            }
        }

        if (xPos != 0 && yPos != 0) {
            if (minefield[xPos - 1][yPos - 1] == 0) {
                for (int i = 0; i < safeSpacies.length; i++) {
                    double xBoundedPos = safeSpacies[i].xPositionGetter();
                    double yBoundedPos = safeSpacies[i].yPositionGetter();
                    if (!safeSpacies[i].isRevealedGetter() && 
                    isInBoundedBoard(xTemp, yTemp, xBoundedPos, yBoundedPos)) {
                        safeSpacies[i].updateData(xPos + 0.5, yPos + 0.5);
                    }
                }
            }
        }

        if (yPos != 0) {
            if (minefield[xPos][yPos - 1] == 0) {
                for (int i = 0; i < safeSpacies.length; i++) {
                    double xBoundedPos = safeSpacies[i].xPositionGetter();
                    double yBoundedPos = safeSpacies[i].yPositionGetter();
                    if (!safeSpacies[i].isRevealedGetter() && 
                    isInBoundedBoard(xTemp, yTemp, xBoundedPos, yBoundedPos)) {
                        safeSpacies[i].updateData(xPos + 0.5, yPos + 0.5);
                    }
                }
            }
        }

        if (yPos != 0 && xPos < minefield.length - 1) {
            if (minefield[xPos + 1][yPos - 1] == 0) {
                for (int i = 0; i < safeSpacies.length; i++) {
                    double xBoundedPos = safeSpacies[i].xPositionGetter();
                    double yBoundedPos = safeSpacies[i].yPositionGetter();
                    if (!safeSpacies[i].isRevealedGetter() && 
                    isInBoundedBoard(xTemp, yTemp, xBoundedPos, yBoundedPos)) {
                        safeSpacies[i].updateData(xPos + 0.5, yPos + 0.5);
                    }
                }
            }
        }
    }

    */

    /**
     * Inputs: N/A
     * Outputs: N/A
     * Description: Simply uses cell.reveal() functions to visually reveal where all
     * the cells are and the bombs without exploding them.
    */
    public void reveal() {
        for (int i = 0; i < bombies.length; i++) {
            bombies[i].reveal();
        }
        
        for (int i = 0; i < safeSpacies.length; i++) {
            safeSpacies[i].reveal();
        }
    }

    //The FOLLOWING 4 FUNCTIONS are the Win|Lose Functions and Finish FUNCTIONS.  
    
    /** 1) 
     * Inputs: N/A
     * Outputs: Boolean, if PlayerWon
     * Description: This functions ensures that all the bombies are not exploded and 
     * to ensure that all safeSpaces are uncovered, to claim a victor.
    */

    public boolean didPlayerWin() {
        //Explode Check 
        for (int i = 0; i < bombies.length; i++) {
            if (bombies[i].isExplodedGetter()) {
                return false;
            }
        }
        //Uncover Check
        for (int j = 0; j < safeSpacies.length; j++) {
            if (!safeSpacies[j].isRevealedGetter()) {
                return false;
            }
        }
        //If no, then Player Wins. 
        return true;
    }
    
    /** 2)
     * Inputs: N/A
     * Outputs: Boolean, if Player Lost. 
     * Description: The function ensures that no bombs are exploded mid game, because
     * if so, the player automatically loses. 
    */

    public boolean didPlayerLose() {
        //If bomb explodes before all spaces are revealed then Player has lost.
        for (int i = 0; i < bombies.length; i++) {
            if (bombies[i].isExplodedGetter()) {
                return true;
            }
        }
        return false;
    }

    /** 3) 
     * Inputs: N/A
     * Outputs: Boolean, if Game Finished
     * Description: Game is Finished whenever either win/lose condition is true. 
    */
    public boolean isGameFinished() {
        if (didPlayerWin() || didPlayerLose()) {
            //Reveals the Rest of the Bombs
            return true;
        } else {
            return false;
        }
    }
    
    /** 4)
     * Inputs: N/A
     * Outputs: N/A 
     * Description: After Game is Over, this will display a win or lose screen. 
     * Plus the instrutions to start again. 
    */
    public void drawEndScreen() {
        if (didPlayerWin()) {
            PennDraw.setPenColor(PennDraw.YELLOW);
            PennDraw.filledRectangle(4.5, 4.5, 4.5, 1.3);
            PennDraw.setPenColor(PennDraw.WHITE);
            PennDraw.filledRectangle(4.5, 4.5, 4.5, 1);
            PennDraw.setPenColor(PennDraw.LIGHT_GRAY);
            PennDraw.filledRectangle(4.5, 4.5, 4.4, .80);
            PennDraw.setPenColor(PennDraw.BLACK);
            PennDraw.text(4.5, 4.7, "YOU WON! Want to Play Again? Click the Face,");
            PennDraw.text(4.5, 4, "then the minefield! If you don't, that's okay!");

        } else if (didPlayerLose()) {
             //This is the lose face. 
            PennDraw.setPenColor(PennDraw.BOOK_BLUE);
            PennDraw.filledCircle(10, 8, .7);
            PennDraw.setPenColor(PennDraw.BLACK);
            PennDraw.filledCircle(10.25, 8.2, .10);
            PennDraw.filledCircle(9.75, 8.2, .10);
            PennDraw.filledCircle(10, 7.6, .15);

             //This is Lose Message
            PennDraw.setPenColor(PennDraw.LIGHT_GRAY);
            PennDraw.setPenColor(PennDraw.WHITE);
            PennDraw.filledRectangle(4.5, 4.5, 4.4, 1);
            PennDraw.setPenColor(PennDraw.LIGHT_GRAY);
            PennDraw.filledRectangle(4.5, 4.5, 3.4, .80);
            PennDraw.setPenColor(PennDraw.BLACK);
            PennDraw.text(4.5, 4.7, "Want to Play Again? Click the Face then");
            PennDraw.text(4.5, 4, "Minefield Again! If you don't, that's okay!");
        }
    }

    
    /**
     * Inputs: N/A
     * Outputs: Returns didFirstPLayerClick value
     * Description: A function to see if first player clicked or not. 
    */

    public boolean didFirstPLayerClickGetter() {
        return didFirstPLayerClick;
    }
    
    /**
     * Inputs: N/A
     * Outputs: N/A
     * Description: Destroys and resets the previous spawned minefield, bombs and 
     * safespaces. Allowing no overlap of bombs or past cells. 
    */
    public void reset() {
        for (int row = 0; row < minefield.length; row++) {
            for (int col = 0; col < minefield.length; col++) {
                minefield[row][col] = 0; 
            }
        }
        for (int i = 0; i < bombies.length; i++) {
            bombies[i] = null;
        }
        
        for (int i = 0; i < safeSpacies.length; i++) {
            safeSpacies[i] = null;
        }
        firstdraw();

    }

    /**
     * Inputs: N/A
     * Outputs: Returns true or false, if it is bounded. 
     * Description: Used in adjacentBlankRevealer, this checks the bounds. 
    */

    public boolean isInBoundedBoard(double xMouse, double yMouse, double xPosTile, 
    double yPosTile) {
        // X/Y pos +.5 refers to the tile bounudary, the first two are acutal mouse.
        return xMouse < xPosTile + .6 && xMouse > xPosTile - .6  &&
        yMouse < yPosTile + .6 && yMouse > yPosTile - .6;
    }


    /**
     * Inputs: N/A
     * Outputs: N/A
     * Description: This clears the board entirely at the end. 
    */
    public void totalFinish() {
        PennDraw.clear();
        PennDraw.setFontSize(35);
        PennDraw.text(5.5, 6, "GAME is FINISHED. DONE!");
    }
}