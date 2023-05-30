/**
 * Name: Ysaach Habon
 * Pennkey: yhabon
 * Execution: class file used in Board.java 
 *
 * Description: This is the class file used to generate Number and Blank cells and
 * tiles. This helps to conslidate code into more understandable parts. 
**/

public class SafeSpace implements MSCellInterface {
    //instance variables
    private boolean isRevealed;
    private double xPos;
    private double yPos;
    private double halfLength;
    //Used to Count the Surrounding Cells with Bombs
    private int surroundingBombs;
    
    //Constructor || the Creator which counts the surroundingBombs first.
    public SafeSpace(double xPos, double yPos) {
        //Statistics and formality for tiles
        this.xPos = xPos;
        this.yPos = yPos;
        halfLength = .5;
        
        //Changes in Response to Clicks
        isRevealed = false;
        
        //The number to figure out the safeSpace.
        surroundingBombs = 0;
    }
    
    //SAFESPACE METHODS AND FUNCTIONS 
    
    /**
     * Inputs: None
     * Outputs: VOID | If called, draws the square with covered or w/o if revealed. 
     * Description: This code draws the safespace and if it is revealed using 
     * the surrounding Bombs to show 1, 2, 3, 4, 5, 6, 7, 8, 9. SurroundingBombs 
     * is counted in the Board.surrBombsCounterandSetter().
    */

    public void draw() {
        if (isRevealed) {
            //This is the background title.
            PennDraw.setPenColor(PennDraw.GRAY);
            PennDraw.filledSquare(xPos, yPos, halfLength);
            PennDraw.setPenColor(PennDraw.LIGHT_GRAY);
            PennDraw.filledSquare(xPos, yPos, halfLength - .02);

            //This changes the color of the text, depending on value. 
            if (surroundingBombs == 1) {
                PennDraw.setPenColor(PennDraw.BLUE);
                } else if (surroundingBombs == 2) {
                PennDraw.setPenColor(77, 158, 0);
                } else if (surroundingBombs == 3) {
                PennDraw.setPenColor(PennDraw.CYAN);
                } else if (surroundingBombs == 4) {
                PennDraw.setPenColor(PennDraw.MAGENTA);
                } else if (surroundingBombs == 5) {
                PennDraw.setPenColor(PennDraw.RED);
                } else if (surroundingBombs == 6) {
                PennDraw.setPenColor(PennDraw.PINK);
                } else if (surroundingBombs == 7) {
                PennDraw.setPenColor(PennDraw.BOOK_BLUE);
                } else if (surroundingBombs == 8) {
                PennDraw.setPenColor(PennDraw.BLACK);
                } else {
                    PennDraw.setPenColor(PennDraw.LIGHT_GRAY);
                }
           
            //This will parse the int into a number for text
            String strSurrBombs = "" + surroundingBombs;
            PennDraw.setPenRadius(1);
            PennDraw.setFontBold();
            PennDraw.text(xPos, yPos, strSurrBombs);

        } else {
            //A Blank Tile
            PennDraw.setPenColor(PennDraw.BLACK);
            PennDraw.filledSquare(xPos, yPos, halfLength);
            PennDraw.setPenColor(105, 105, 105);
            PennDraw.filledSquare(xPos, yPos, halfLength - .02);
        }
    }
    
    /**
     * Inputs: (temporary double XTemp & YTemp) 
     * Outputs: Void
     * Description: This intimates a click to reveal only blank slates, by using the 
     * xTemp and YTemp, and only reveals if surrounding values = 0. This is primarily
     * only use to open adajacent tiles. 
     *
    */

    public void updateData(double xTemp, double yTemp) {
        //These are variables needed to check if mouse conditions are correct.
        double xMouse = xTemp;
        double yMouse = yTemp;
        if (isInBounded(xMouse, yMouse) && this.surroundingBombs == 0) {
            isRevealed = true;
        }   
    }

     /**
     * Inputs: N/A
     * Outputs: Void
     * Description: This updates this.safespace wheneve the 
    * "click" is bounded in this.specfic safeSpace, within the loop safeSpace[i] and 
    * if so updates its isRevealed value. This is the REGULAR mouse click. 
    */

    public void updateData() {
        //These are variables needed to check if mouse conditions are correct.
        if (PennDraw.mousePressed()) {
            double xMouse = PennDraw.mouseX();
            double yMouse = PennDraw.mouseY();
            if (isInBounded(xMouse, yMouse)) {
                isRevealed = true;
            }   
    
        }
    }

    /**
     * Inputs: The xMouse and yMouse coords
     * Outputs: True, it is bounded, or False, it is not Bounded. Bounded = Within.
     * Description: By checking the title center and adding .5, this checks if the 
     * click is within the leftest, bottom or upper or righter bounds. This is a 
     * helper function for UpdateData. 
    */
    public boolean isInBounded(double xMouse, double yMouse) {
        return  xMouse < xPos + halfLength && xMouse > xPos - halfLength &&
        yMouse < yPos + halfLength && yMouse > yPos - halfLength;
    }
    
    /**
     * Inputs: # of surroundingBombs, founded by Board.surrBombsCounterandSetter. 
     * Outputs: N.A
     * Description: This updates the SurroundingBombsValue, which is used for the 
     * Draw Function. 
    */
    public void surrBombsSetter(int surroundingBombs) {
        this.surroundingBombs = surroundingBombs;
    }
    
    /**
     * Inputs: N/A
     * Outputs: Outputs the number of surroundng Bombs. 
     * Description: This is a getter function for surrBombs value. 
    */

    public int surrBombsGetter() {
        return this.surroundingBombs;
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
