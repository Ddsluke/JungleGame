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
    public boolean move(int newXPosition, int newYPosition, GameBoard gameBoard){// true = valid, false = invalid;
        if(newXPosition<0||newXPosition>6||newYPosition<0||newYPosition>8){
            return false;//indexOutofBound
        }
        else if (!gameBoard.isAdj(xPosition,yPosition,newXPosition,newYPosition)){
            return false; // If two blocks are not adjacent then the command is in invalid.
        }
        else if (gameBoard.isRiver(newXPosition, newYPosition)){// If the new position is type RIVER then return.
            return false;
        }
        Piece other = gameBoard.getPiece(newXPosition, newYPosition);
        if (isSameSide(other, gameBoard)){
            return false;
        }
        else if (other != null){ // if there is a piece on it
            if (ableToCapture(other, gameBoard)){
                gameBoard.setPiece(newXPosition, newYPosition, this);
                this.xPosition = newXPosition;
                this.yPosition = newYPosition;
                return true;
            }
            else return false;
        }
        else {// There is no piece on it and we can directly move to the new position
            gameBoard.setPiece(newXPosition, newYPosition, this);
            this.xPosition = newXPosition;
            this.yPosition = newYPosition;
            return true;
        }
    }

    public boolean isAvailable(int newXPosition, int newYPosition, GameBoard gameBoard){
        if (!gameBoard.isAdj(xPosition,yPosition,newXPosition,newYPosition)){
            return false; // If two blocks are not adjacent then the command is in invalid.
        }
        else if (gameBoard.isRiver(newXPosition, newYPosition)){// If the new position is type RIVER then return.
            return false;
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

    public Piece[] avaiPos(GameBoard gameBoard){
        Piece[] avaiPieces = new Piece[4];
        int index = 0;
        for(int i = 0; i < 7; i++){
            for(int j = 0; j < 9; j++){
                if (this.isAvailable(i, j, gameBoard)){
                    avaiPieces[index++] = gameBoard.getPiece(i,j);
                }
            }
        }
        return avaiPieces;
    }

    public boolean isSameSide(Piece other, GameBoard gameBoard){
        if (this.getSide() != other.getSide()){
            return false;
        }
        else return true;
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
