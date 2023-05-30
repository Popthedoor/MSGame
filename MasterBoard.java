// /**
//  * Name: Ysaach Habon
//  * Pennkey: yhabon
//  * Execution: N/A class
//  *
//  * Description: Board draws the minefield, all cells. This handles the formation of
//  * the entire game: Headers, Outline, Board, and Clicks.  
//  * 
//  * Outline of Board File: 
//  * 1. General 
//  * 2. Generation of Board and Board Entities 
//  * 3. Update and Gameplay 
//  * 
//  *
//  *
//  *  
// **/
// public class Board implements MSBoardInterface {
    
//     //instance variables but these change with EC
//     private int width, height;
    
//     //The Bombs & SafeSpace Array for organization
//     private Bomb[] bombies;
//     private SafeSpace[] safeSpacies;
    
//     /* This would be the keyMineField, that shows all bombs and SafeSpace locations.
//     * 1 would mean there is a bomb at that specfic x,y coord.
//     * 0 would mean there is no bomb at that specfic x,y coord.
//     */
//     private int[][] minefield;

//     private boolean mouseListeningMode;
    
//     //This is used to makesure that first click isn't a bomb.
//     private boolean didFirstPLayerClick;
    
//     //Constructor |
//     public Board() {
//         //This contains the basic statistics of the game.
//         this.width = 600;
//         this.height = 700;
//         PennDraw.setXscale(0, 11);
//         PennDraw.setYscale(0, 12);
        
//         //Bombies.length = # of bombs left.
//         bombies = new Bomb[10];
//         safeSpacies = new SafeSpace[71];
//         //Provides the key used for surroundingbombcounter.
//         minefield = new int[9][9];
        
//         mouseListeningMode = true;
//         didFirstPLayerClick = false;
        
//         /* Basics Drawings
//         *  1. Background & Header
//         *  2. The Playable Areana Outline
//         *  3. Outlines for Squares
//         */

//         // 1a) Background
//         PennDraw.clear(PennDraw.LIGHT_GRAY);
        
//         // 1b) Headers
//         PennDraw.setPenColor(PennDraw.GRAY);
//         PennDraw.filledRectangle(5.5, 10.5, 5.5, 1.5);
//         PennDraw.setPenColor(PennDraw.LIGHT_GRAY);
//         PennDraw.filledRectangle(5.5, 10.5, 5, 1.25);
        
//         // 2) Outline of the Playable Arena
//         PennDraw.setPenColor(PennDraw.BLACK);
//         PennDraw.setPenRadius(.0175);
//         PennDraw.square(4.5, 4.5, 4.5);
        
//         //3) Outline of the little tiles
//         PennDraw.setPenRadius();
//         for (int row = 0; row < 9 ; row++) {
//             for (int col = 0; col < 9; col++) {
//                 PennDraw.square(row + .5 , col +.5, .5);
//             }
//         }

//         //FIX in OH, this places a 
//         minefieldCreator();
//     }
    
//     //2 GENERATION of BOARD and BOARD's ENTITIES | Following Two Functions

//     /**
//      * Inputs: N/a
//      * Outputs: N/A
//      * Description: This minefieldCreator: 1st) Generates the Bombs in a random
//      * position between values [0-9][0-9], then adds 0.5 to center on program. 
//      * Simultaneously, makes a minefield to record where bombs are with 1. 2) Then,
//      * the code itterates the entire minefield, checking where there isn't a 1, Puts
//      * a 0 to indiciate that is a safespace. 3) Then, using the minefield[][]==0, 
//      * creates a safespace at the minefield's row and col, placing it into an array. 
//      * 4) Using minefield as a key, this checks each tile and counts the bombs.  
//      *  
//      * Issues is, I cannot access the safespace's x and y coords in my array. As safe
//      * space array is unsorted based on like. 
//     */

//     public void minefieldCreator() {
//         /* Placement and Grid Formation
//         * 1. Placement of Bombs 
//         * 2. Making the minefield
//         * 3. Finish minefield to create safe spaces.
//         * 4. Creation of SafeSpaces and their surroundingBombs
//         */
        
//         // 1) The Placement of Bombs || Puts the Bcoord in the minefield
//         for (int p = 0; p < bombies.length; p++) {
//             int xRandoPositon = (int) (Math.random() * 9);
//             int yRandoPosition = (int) (Math.random() * 9);
//             bombies[p] = new Bomb(xRandoPositon + 0.5, yRandoPosition + 0.5);
//             minefield[xRandoPositon][yRandoPosition] = 1;
//         }
        
//         // 2) Fills in the rest of MineField with filler placement, 0.
//         for (int row = 0; row < minefield.length; row++ ) {
//             for (int col = 0; col < minefield.length; col++) {
//                 if (minefield[row][col] != 1) {
//                     minefield[row][col] = 0;
//                 }
//             }
//         }
//         // 3) This creates safespaces using minefield's 0 and 1 as guide.
//         int incremeter = 70;
//         for (int row = 0; row < 9; row++) {
//             for (int col = 0; col < 9; col++) {
//                 if (minefield[row][col] == 0) {
//                     System.out.println(incremeter);
//                     safeSpacies[70 - incremeter] = new
//                     SafeSpace(row + 0.5, col + 0.5);
//                     incremeter = incremeter - 1;
//                 }
//             }
//         }
        
//         // 4) SafeSpace surrBombs Values are COUNTED and SET. 
//         surrBombsCounterandSetter();
//     }

//     //FUNCTIONS BELOW COUNTS THE BOMB VIA MINEFIELD KEY. 
//     /**
//      * Inputs: N/A
//      * Outputs: N/A
//      * Description: This code 
//     */

//     public void surrBombsCounterandSetter() {
//         // This part of the counts the number of bombs.
//         for (int i = 0; i < safeSpacies.length; i++) {
//             int xPos = (int) (safeSpacies[i].xPositionGetter() - .5);
//             int yPos = (int) (safeSpacies[i].yPositionGetter() - .5);
//             int surroundingBombs = 0;
            
//             if (xPos != 0 && yPos < minefield.length - 1) {
//                 if (minefield[xPos - 1][yPos + 1] == 1) {
//                     surroundingBombs++;
//                 }
//             }
            
//             if (yPos < minefield.length - 1) {
//                 if (minefield[xPos][yPos + 1] == 1){
//                     surroundingBombs++;
//                 }
//             }
            
//             if (yPos < minefield.length - 1 && xPos < minefield.length - 1) {
//                 if (minefield[xPos + 1][yPos + 1] == 1) {
//                     surroundingBombs++;
//                 }
//             }
            
//             if (xPos != 0) {
//                 if (minefield[xPos - 1][yPos] == 1) {
//                     surroundingBombs++;
//                 }
//             }
            
//             if (xPos < minefield.length - 1) {
//                 if (minefield[xPos + 1][yPos] == 1) {
//                     surroundingBombs++;
//                 }
//             }
            
//             if (xPos != 0 && yPos != 0) {
//                 if (minefield[xPos - 1][yPos - 1] == 1) {
//                     surroundingBombs++;
//                 }
//             }
            
//             if (yPos != 0) {
//                 if (minefield[xPos][yPos - 1] == 1) {
//                     surroundingBombs++;
//                 }
//             }
            
//             if (yPos != 0 && xPos < minefield.length -1) {
//                 if (minefield[xPos + 1][yPos - 1] == 1) {
//                     surroundingBombs++;
//                 }
//             }
            
//             //changes the surroundingBombs value in that safe space.
//             safeSpacies[i].surrBombsSetter(surroundingBombs);
//         }
//     }
    
    
//     // 2) Update and Gameplay Functions | Following 

//     /**
//      * Inputs: None
//      * Outputs: None
//      * Description: TODO
//     */
//     public void updateBoardData() {
//         //If mouseListeningMode is true, this means it ready to be clicked.
//         if (mouseListeningMode) {
//             double xMouse = PennDraw.mouseX();
//             double yMouse = PennDraw.mouseY();
//             if (PennDraw.mousePressed()) {
//                 for (int i = 0; i < bombies.length; i++) {
//                     bombies[i].updateData();
//                 }
//                 for (int i = 0; i < safeSpacies.length; i++) {
//                     safeSpacies[i].updateData();
//                     double xTemp = safeSpacies[i].xPositionGetter() - .5;
//                     double yTemp = safeSpacies[i].yPositionGetter() - .5;
//                 }
//             }
            
//         }
        
//     }
    
//     /**
//      * Inputs: N/A
//      * Outputs: Draws the Bombs and Bombies
//      * Description: This draws all the safespacies and bombies, after their updaye
//     */
//     public void draw() {
//         //Draws the Bombs
//         for (int p = 0; p < bombies.length; p++) {
//             bombies[p].draw();
//         }
//         //Draws the SafeSpaces
//         for (int j = 0; j < safeSpacies.length; j++) {
//             safeSpacies[j].draw();
//         }
//         PennDraw.advance();
//     }
    
//     //Method that reveals the updates the adajacent safespacies with 0 surbombs val.
//     public void adjacentBlankRevealer(double xTemp, double yTemp) {
//         int xPos = (int) (xTemp);
//         int yPos = (int) (yTemp);

//         //Reveal the surrounding tiles using minefield key.
//          if (xPos != 0 && yPos < minefield.length - 1) {
//                 if (minefield[xPos - 1][yPos + 1] == 0) {
//                     for (int i = 0; i < safeSpacies.length; i++) {
//                         if (!safeSpacies[i].isRevealedGetter() && 
//                         safeSpacies[i].surrBombsGetter() == 0) {
//                             safeSpacies[i].updateData(xPos + 0.5, yPos + 0.5);
//                         }
//                     }
//                 }
//             }
//             if (yPos < minefield.length - 1) {
//                 if (minefield[xPos][yPos + 1] == 0){
//                     for (int i = 0; i < safeSpacies.length; i++) {
//                         if (!safeSpacies[i].isRevealedGetter() && 
//                         safeSpacies[i].surrBombsGetter() == 0) {
//                             safeSpacies[i].updateData(xPos + 0.5, yPos + 0.5);
//                         }
//                     }
//                 }
//             }
    
//             if (yPos < minefield.length - 1 && xPos < minefield.length - 1) {
//                 if (minefield[xPos + 1][yPos + 1] == 0) {
//                     for (int i = 0; i < safeSpacies.length; i++) {
//                         if (!safeSpacies[i].isRevealedGetter() && 
//                         safeSpacies[i].surrBombsGetter() == 0) {
//                             safeSpacies[i].updateData(xPos + 0.5, yPos + 0.5);
//                         }
//                     }
//                 }
//             }
    
//             if (xPos != 0) {
//                 if (minefield[xPos - 1][yPos] == 0) {
//                     for (int i = 0; i < safeSpacies.length; i++) {
//                         if (!safeSpacies[i].isRevealedGetter() && 
//                         safeSpacies[i].surrBombsGetter() == 0) {
//                             safeSpacies[i].updateData(xPos + 0.5, yPos + 0.5);
//                         }
//                     }
//                 }
//             }
    
//             if (xPos < minefield.length - 1) {
//                 if (minefield[xPos + 1][yPos] == 0) {
//                     for (int i = 0; i < safeSpacies.length; i++) {
//                         if (!safeSpacies[i].isRevealedGetter() && 
//                         safeSpacies[i].surrBombsGetter() == 0) {
//                             safeSpacies[i].updateData(xPos + 0.5, yPos + 0.5);
//                         }
//                     }
//                 }
//             }
    
//             if (xPos != 0 && yPos != 0) {
//                 if (minefield[xPos - 1][yPos - 1] == 0) {
//                     for (int i = 0; i < safeSpacies.length; i++) {
//                         if (!safeSpacies[i].isRevealedGetter() && 
//                         safeSpacies[i].surrBombsGetter() == 0) {
//                             safeSpacies[i].updateData(xPos + 0.5, yPos + 0.5);
//                         }
//                     }
//                 }
//             }
    
//             if (yPos != 0) {
//                 if (minefield[xPos][yPos - 1] == 0) {
//                    for (int i = 0; i < safeSpacies.length; i++) {
//                         if (!safeSpacies[i].isRevealedGetter() && 
//                         safeSpacies[i].surrBombsGetter() == 0) {
//                             safeSpacies[i].updateData(xPos + 0.5, yPos + 0.5);
//                         }
//                     }
//                 }
//             }
    
//             if (yPos != 0 && xPos < minefield.length -1) {
//                 if (minefield[xPos + 1][yPos - 1] == 0) {
//                     for (int i = 0; i < safeSpacies.length; i++) {
//                         if (!safeSpacies[i].isRevealedGetter() && 
//                         safeSpacies[i].surrBombsGetter() == 0) {
//                             safeSpacies[i].updateData(xPos + 0.5, yPos + 0.5);
//                         }
//                     }
//                 }
//             }
//     }
     
//     /**
//      * Inputs: N/A
//      * Outputs: N/A
//      * Description: Simply uses cell.reveal() functions to visually reveal where all
//      * the cells are and the bombs without exploding them.
//     */
//     public void reveal() {
//         for (int i = 0; i < bombies.length; i++) {
//             bombies[i].reveal();
//         }
        
//         for (int i = 0; i < safeSpacies.length; i++) {
//             safeSpacies[i].reveal();
//         }
//     }

//     //The FOLLOWING 4 FUNCTIONS are the Win|Lose Functions and Finish FUNCTIONS.  
    
//     /** 1) 
//      * Inputs: N/A
//      * Outputs: Boolean, if PlayerWon
//      * Description: This functions ensures that all the bombies are not exploded and 
//      * to ensure that all safeSpaces are uncovered, to claim a victor.
//     */

//     public boolean didPlayerWin() {
//         //Explode Check 
//         for (int i = 0; i < bombies.length; i++) {
//             if (bombies[i].isExplodedGetter()) {
//                 return false;
//             }
//         }
//         //Uncover Check
//         for (int j = 0; j < safeSpacies.length; j++) {
//             if (!safeSpacies[j].isRevealedGetter()){
//                 return false;
//             }
//         }
//         //If no, then Player Wins. 
//         return true;
//     }
    
//     /** 2)
//      * Inputs: N/A
//      * Outputs: Boolean, if Player Lost. 
//      * Description: The function ensures that no bombs are exploded mid game, because
//      * if so, the player automatically loses. 
//     */

//     public boolean didPlayerLose() {
//         //If bomb explodes before all spaces are revealed then Player has lost.
//         for (int i = 0; i < bombies.length; i++) {
//             if (bombies[i].isExplodedGetter() ) {
//                 return true;
//             }
//         }
//         return false;
//     }

//     /** 3) 
//      * Inputs: N/A
//      * Outputs: Boolean, if Game Finished
//      * Description: Game is Finished whenever either win/lose condition is true. 
//     */
//     public boolean isGameFinished() {
//         if (didFirstPLayerClick) { 
//             if (didPlayerWin() || didPlayerLose()) {
//                 return true;
//             }
//             return false;
//         }
//         return false; 
//     }
    
//     /** 4)
//      * Inputs: N/A
//      * Outputs: Draws the Victory Screem
//      * Description: After Game is Over, this will display a win or lose screen. 
//     */
//     public void drawVictory() {
//         if (didPlayerWin()) {
//             PennDraw.disableAnimation();
//             PennDraw.setPenColor(PennDraw.RED);
//             PennDraw.text(.5, .5, "YOU WIN!!");
//         }
        
//     }
    
//     //Method that restarts the board
//     /**
//      * Inputs: TODO
//      * Outputs: TODO
//      * Description: TODO
//     */
//     public void restart() {
//     }
    
// }
