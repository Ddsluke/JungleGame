package Piece;

import Board.GameBoard;

public class Lion extends Piece {
    public Lion(int x, int y, int side){
        super(x, y, side);
        rank = 7;
    }
    @Override
    public void move(int newXPosition, int newYPosition, GameBoard gameBoard){
        if(newXPosition<0||newXPosition>6||newYPosition<0||newYPosition>8){
            return;//indexOutofBound
        }
        else if (gameBoard.isRiver(newXPosition, newYPosition)){// If the new position is type RIVER then return.
            return;
        }
        else if (!(gameBoard.isAcrossWater(xPosition, yPosition, newXPosition, newYPosition) || gameBoard.isAdj(xPosition,yPosition,newXPosition,newYPosition))){
            return;//if the lion neither move to adjacent position nor jump to the otherside over the river then return.
        }
        Piece other = gameBoard.getPiece(newXPosition, newYPosition);
        if (other != null){ // if there is a piece on it
            if (ableToCapture(other, gameBoard)){
                gameBoard.setPiece(newXPosition, newYPosition, this);
                xPosition = newXPosition;
                yPosition = newYPosition;
            }
        }
        else {// There is no piece on it and we can directly move to the new position
            gameBoard.setPiece(newXPosition, newYPosition, this);
            xPosition = newXPosition;
            yPosition = newYPosition;
        }
    }
}
