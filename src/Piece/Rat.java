package Piece;
import Board.GameBoard;

public class Rat extends Piece {
    public Rat(int x, int y, int side){
        super(x, y, side);
        rank = 1;
    }
    @Override
    public boolean move(int newXPosition, int newYPosition, GameBoard gameBoard){
        if(newXPosition<0||newXPosition>6||newYPosition<0||newYPosition>8){
            return false;//indexOutOfBound
        }
        else if (!gameBoard.isAdj(xPosition,yPosition,newXPosition,newYPosition)){
            return false; // If two blocks are not adjacent then the command is in invalid.
        }
        Piece other = gameBoard.getPiece(newXPosition,newYPosition);// To get the piece from the newX, newY position
        if (other != null){ // if there is a piece on it
            if (isSameSide(other,gameBoard)){
                return false;
            }
            if (gameBoard.isRiver(xPosition, yPosition) && (!gameBoard.isRiver(newXPosition, newYPosition))
                    || gameBoard.isRiver(newXPosition, newYPosition) && (!gameBoard.isRiver(xPosition,yPosition))) {
                    return false;// If any of the Area is a RIVER type then it cannot move towards it
            }
            else if (ableToCapture(other,gameBoard)){
                gameBoard.setPiece(newXPosition, newYPosition, this);
                xPosition = newXPosition;
                yPosition = newYPosition;
                return true;
            }
            else return false;
        }
        else {// There is no piece on it and we can directly move to the new position
            gameBoard.setPiece(newXPosition, newYPosition, this);
            xPosition = newXPosition;
            yPosition = newYPosition;
            return true;
        }
    }
    @Override
    public boolean ableToCapture(Piece other, GameBoard gameBoard){
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
    @Override
    public boolean isAvailable(int newXPosition, int newYPosition, GameBoard gameBoard){
        if (!gameBoard.isAdj(xPosition,yPosition,newXPosition,newYPosition)){
            return false; // If two blocks are not adjacent then the command is in invalid.
        }
        Piece other = gameBoard.getPiece(newXPosition,newYPosition);// To get the piece from the newX, newY position
        if (other == null){
            return true;
        }
        else{
            if (isSameSide(other,gameBoard)){
                return false;
            }
            if (gameBoard.isRiver(xPosition, yPosition) && (!gameBoard.isRiver(newXPosition, newYPosition))
                    || gameBoard.isRiver(newXPosition, newYPosition) && (!gameBoard.isRiver(xPosition,yPosition))){
                return false;
            }
            else if (ableToCapture(other,gameBoard)){
                return true;
            }
            else return false;
        }
    }


}
