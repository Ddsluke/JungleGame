package Piece;

import Board.GameBoard;

public abstract class Piece {
    int xPosition;
    int yPosition;
    int rank;
    int side; // 0 or 1

    public Piece(int x, int y, int side){
        this.xPosition = x;
        this.yPosition = y;
        this.side = side;
    }
    public void move(int newXPosition, int newYPosition, GameBoard gameBoard){
        if(newXPosition<0||newXPosition>6||newYPosition<0||newYPosition>8){
            return;//indexOutofBound
        }
        else if (!gameBoard.isAdj(xPosition,yPosition,newXPosition,newYPosition)){
            return; // If two blocks are not adjacent then the command is in invalid.
        }
        else if (gameBoard.isRiver(newXPosition, newYPosition)){// If the new position is type RIVER then return.
            return;
        }
        Piece other = gameBoard.getPiece(newXPosition, newYPosition);
        if (other != null){ // if there is a piece on it
            if (ableToCapture(other, gameBoard)){
                gameBoard.setPiece(newXPosition, newYPosition, this);
                this.xPosition = newXPosition;
                this.yPosition = newYPosition;
            }
        }
        else {// There is no piece on it and we can directly move to the new position
            gameBoard.setPiece(newXPosition, newYPosition, this);
            this.xPosition = newXPosition;
            this.yPosition = newYPosition;
        }

    }

    public boolean ableToCapture(Piece other, GameBoard gameBoard){
        if(other.rank <= this.rank || gameBoard.isTrap(other.getX(), other.getY())){
            return true;
        }
        else{
            return false;
        }
    }

    public int getX(){
        return xPosition;
    }

    public int getY(){
        return yPosition;
    }

    public int getSide(){
        return side;
    }


}
