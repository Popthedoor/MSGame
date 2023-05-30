/**
 * Name: Ysaach Habon
 * Pennkey: yhabon
 * Execution: java Game
 *
 * Description: This uses Board, which uses SafeSpace and Bomb, to create the
 * minesweeper game. 
**/
public class Game {
    public static void main(String[] args) {

        PennDraw.setCanvasSize(650, 700);
        Board board = new Board();
        board.firstdraw();

        PennDraw.enableAnimation(30);
        
        while (!board.didFirstPLayerClickGetter()) {
            if (PennDraw.mousePressed()) { 
                board.firstdraw();
                //This is the start of the GAME!
                if (PennDraw.mouseX() > 0 && PennDraw.mouseX() < 9 && 
                    PennDraw.mouseY() < 9 && PennDraw.mouseY() > 0) { 
                        double firstXClick = PennDraw.mouseX();
                        double firstYClick = PennDraw.mouseY();
                        board.spawn(firstXClick, firstYClick);
                        while (!board.isGameFinished()) {
                            board.updateBoardData();
                            board.draw();
                            PennDraw.advance(); 
                            }
                        board.drawEndScreen();
                        PennDraw.advance();
                    }
                if (PennDraw.mouseX() > 9.3 && PennDraw.mouseX() < 10.7 && 
                PennDraw.mouseY() > 7.3 && PennDraw.mouseY() < 8.7) {
                board.reset();
                }
            }
        } 
       board.totalFinish();
    }
}
