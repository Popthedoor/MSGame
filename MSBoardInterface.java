/**
* Name: Ysaach Romac Habon
* Pennkey: yhabon
* Execution: Implements MSBoardInterface for board file.
*
* Description: An interface to guide functions required for Board
**/
public interface MSBoardInterface
{
    /**
     * Inputs: N/A
     * Outputs: N/A
     * Description: This will draw the board, and then call the bombies and tiles.
     * This is to simplify code. 
    */
    public void draw();
    

    /**
     * Inputs: N/A
     * Outputs: N/A
     * Description: This updates the board based on any click in the respective
     * 9x9
    */
    public void updateBoardData();
    
    /**
     * Inputs: N/A
     * Outputs: N/A
     * Description: This is used to draw the end screen and the play again text.
    */
    public void drawEndScreen();
    
    //Method that Determines if Game Finished to Restart
    /**
     * Inputs: N/a
     * Outputs: Boolean of isGameFinished
     * Description: This is only used to end the current playing loop or to starting
     * the game. 
    */
    public boolean isGameFinished();
    
}
