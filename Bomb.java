/**
 * Name: Ysaach Habon
 * Execution: class file used in Board.java 
 *
 * Description: This is the class file used to generate Bombs cells and
 * tiles. This helps to conslidate code into more understandable parts. 
**/

public class Bomb implements MSCellInterface {
    //instance variables
    private boolean isRevealed;
    private boolean isExploded;
    
    //Variables needed for Squares
    private double xPos;
    private double yPos;
    private double halfLength;
    
    //Constructor
    public Bomb(double xPos, double yPos) {
        this.xPos = xPos;
        this.yPos = yPos;
        //constant variable for all
        halfLength = .5;
        isRevealed = false;
        isExploded = false;
    }
    
    //BOMB METHODS AND FUNCTIONS

    /**
     * Inputs: None
     * Outputs: VOID | If called, draws the bomb covered or w/o cover if revealed. 
     * Description: This code draws the bomb and if it is revealed using 
     * it will be RED. 
    */

    public void draw() {
        if (isRevealed) {
            PennDraw.setPenColor(PennDraw.RED);
            PennDraw.filledSquare(xPos, yPos, halfLength);
        } else {
            PennDraw.setPenColor(PennDraw.BLACK);
            PennDraw.filledSquare(xPos, yPos, halfLength);
            PennDraw.setPenColor(105, 105, 105);
            PennDraw.filledSquare(xPos, yPos, halfLength - .02);
        }
    }
    
    /**
     * Inputs: None
     * Outputs: Void
     * Description: By using the Mousepressed function, this checks if the 
    * "click" is bounded in this safeSpace(within the loop safeSpace[i]) and if so 
    * reveals and updates this BOMB's isExploded and isRevealed values. 
    */

    public void updateData() {
        if (PennDraw.mousePressed()) {
            double xMouse = PennDraw.mouseX();
            double yMouse = PennDraw.mouseY();
            if (isInBounded(xMouse, yMouse)) {
                isRevealed = true;
                isExploded = true;
            }
        }
    }
    
    /**
     * Inputs: The xMouse and yMouse coords
     * Outputs: True, it is bounded, or False, it is not Bounded. Bounded = Within.
     * Description: By checking the title center and adding .5, this checks if the 
     * click is within the leftest, bottom or upper or righter bounds. This is a 
     * helper function for updateData(). 
    */

    public boolean isInBounded(double xMouse, double yMouse) {
        return xMouse < xPos + halfLength && xMouse > xPos - halfLength &&
        yMouse < yPos + halfLength && yMouse > yPos - halfLength;
    }
    
    /**
     * Inputs: N/A
     * Outputs: N/A
     * Description: This is a funciton used to reveal all tiles with Board.reveal().
     * This helps to minimize code. 
    */

    public void reveal() {
        isRevealed = true;
    }
    
   /**
     * Inputs: N/A
     * Outputs: returns isRevealed value
     * Description: Functions is a getter, used by the board to check Win value and
     * if a title has been already revealed to avoid recursive reveal. 
    */
    public boolean isRevealedGetter() {
        return this.isRevealed;
        
    }
    
    /**
     * Inputs: N/A
     * Outputs: returns isExplodedGetter
     * Description: Functions is a getter, used by the board to check didPlayerLose()
     * function. A exploded value that is true means that player clicked on bomb. 
    */
    public boolean isExplodedGetter() {
        return this.isExploded;
    }
    
    /**
     * Inputs: N/A
     * Outputs: returns this cell's XPOS
     * Description: A simple getter function meant to get the XPosition and to be
     * used to find the row and col of the field. 
    */
    public double xPositionGetter() {
        return this.xPos;
    }
    
    /**
     * Inputs: N/A
     * Outputs: returns this cell's YPOS
     * Description: A simple getter function meant to get the YPosition and to be
     * used to find the row and col of the field. Purpose is for minefield placement,
     *
     *
    */
    public double yPositionGetter() {
        return this.yPos;
        
    }
}