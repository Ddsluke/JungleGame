package Piece;

import Board.GameBoard;

public class Tiger extends Piece {
    public Tiger(int x, int y, int side){
        super(x, y, side);
        rank = 6;
    }
    @Override
    public boolean move(int newXPosition, int newYPosition, GameBoard gameBoard){
        if(newXPosition<0||newXPosition>6||newYPosition<0||newYPosition>8){
            return false;//indexOutOfBound
        }
        if (gameBoard.isRiver(newXPosition, newYPosition)){// If the new position is type RIVER then return.
            return false;
        }
        else if (!(gameBoard.isAcrossWater(xPosition, yPosition, newXPosition, newYPosition) || gameBoard.isAdj(xPosition,yPosition,newXPosition,newYPosition))){
            return false;// If the tiger neither moves to adjacent position nor jumps to the other side over the river then return.
        }
        Piece other = gameBoard.getPiece(newXPosition, newYPosition);

        if (other != null){ // if there is a piece on it
            if (isSameSide(other, gameBoard)){// If the piece is on the same side then cannot capture it
                return false;
            }
            else if (ableToCapture(other, gameBoard)){
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
    public boolean isAvailable(int newXPosition, int newYPosition, GameBoard gameBoard){
        if (gameBoard.isRiver(newXPosition, newYPosition)){// If the new position is type RIVER then return.
            return false;
        }
        else if (!(gameBoard.isAcrossWater(xPosition, yPosition, newXPosition, newYPosition) || gameBoard.isAdj(xPosition,yPosition,newXPosition,newYPosition))){
            return false;// If the tiger neither moves to adjacent position nor jumps to the other side over the river then return.
        }
        Piece other = gameBoard.getPiece(newXPosition, newYPosition);
        if (other == null){// There is no piece on it and we can directly move to the new position
            return true;
        }
        else{// If there is a piece on it
            if (isSameSide(other, gameBoard)){// If the piece is on the same side then cannot capture it
                return false;
            }
            if (ableToCapture(other, gameBoard)){ // If it can capture the target
                return true;
            }
            else return false;
        }
    }
}
