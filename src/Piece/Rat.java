package Piece;
import Board.GameBoard;

public class Rat extends Piece {
    public Rat(int x, int y, int side){
        super(x, y, side);
        rank = 1;
    }
    @Override
    public void move(int newXPosition, int newYPosition, GameBoard gameBoard){
        if (!gameBoard.isAdj(xPosition,yPosition,newXPosition,newYPosition)){
            return; // If two blocks are not adjacent then the command is in invalid.
        }
        else if(newXPosition<0||newXPosition>6||newYPosition<0||newYPosition>8){
            return;//indexOutofBound
        }
        Piece other = gameBoard.getPiece(newXPosition,newYPosition);// To get the piece from the newX, newY position
        if (other != null){ // if there is a piece on it
            if (gameBoard.isRiver(xPosition, yPosition) && (!gameBoard.isRiver(newXPosition, newYPosition))
                    || gameBoard.isRiver(newXPosition, newYPosition) && (!gameBoard.isRiver(xPosition,yPosition))) {
                    return;// If any of the Area is a RIVER type then it cannot move towards it
            }
            else if (ableToCapture(other)){
                gameBoard.setPiece(newXPosition, newYPosition, this);
            }
        }
        else {// There is no piece on it and we can directly move to the new position
            gameBoard.setPiece(newXPosition, newYPosition, this);
        }
    }
    @Override
    public boolean ableToCapture(Piece other){
        if(other.rank <= this.rank){
            return true;
        }
        else if (gameBoard.isTrap(other.getX(),other.getY())){
            return true;
        }
        else if (other.rank == 8){
            return true;
        }
        else{
            return false;
        }
    }
}
