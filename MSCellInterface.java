/**
* Name: Ysaach Romac Habon
* Pennkey: yhabon
* Execution: Implements MSCellInterface
*
* Description: An interface to guide functions required for cell.
**/

public interface MSCellInterface
{
      /**
     * Inputs: None
     * Outputs: VOID 
     * Description: If called, draws respective cell bomb or safespace,
     * and if it is revealed, will draw a red screen or a surronding bombs number. 
    */
    public void draw();
    
    /**
     * Inputs: N/A
     * Outputs: returns isRevealed value
     * Description: Functions is a getter, used by the board to check Win value and
     * if a title has been already revealed to avoid recursive reveal. 
    */

    public boolean isRevealedGetter();
    
     /**
     * Inputs: N/A
     * Outputs: returns this cell's XPOS
     * Description: A simple getter function meant to get the YPosition and to be
     * used to find the row and col of the field. Purpose is for minefield placement,
     *
    */
    public double xPositionGetter();
    
     /**
     * Inputs: N/A
     * Outputs: returns this cell's YPOS
     * Description: A simple getter function meant to get the YPosition and to be
     * used to find the row and col of the field. Purpose is for minefield placement,
     *
    */
    public double yPositionGetter();
}
