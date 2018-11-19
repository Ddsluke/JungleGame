package Board;

import Piece.*;

public class GameBoard {
    private static final int WIDTH = 7;
    private static final int LENGTH = 9;
    private Area[][] board = new Area[WIDTH][LENGTH];
    private Piece[][] pieceBoard = new Piece[WIDTH][LENGTH];

    public GameBoard(){
        board[2][0] = Area.TRAP;
        board[3][0] = Area.DEN;// Coordinate 3,0
        board[4][0] = Area.TRAP;
        board[3][1] = Area.TRAP;
        // Player1 side
        board[2][8] = Area.TRAP;
        board[3][8] = Area.DEN;// Coordinate 3,8
        board[4][8] = Area.TRAP;
        board[3][7] = Area.TRAP;
        // Player2 side

        for (int i = 1; i < 3; i++){
            for(int j = 3; j < 6; j++) {
                board[i][j] = Area.RIVER;
                board[i + 3][j] = Area.RIVER;
            }
        }
        // Player1 side
        pieceBoard[0][0] = new Tiger(0,0,0);
        pieceBoard[1][1] = new Cat(1,1,0);
        pieceBoard[0][2] = new Elephant(0,2,0);
        pieceBoard[2][2] = new Wolf(2,2,0);
        pieceBoard[4][2] = new Leopard(4,2,0);
        pieceBoard[5][1] = new Dog(5,1,0);
        pieceBoard[6][0] = new Lion(6,0,0);
        pieceBoard[6][2] = new Rat(6,2,0);
        // Player2 side
        pieceBoard[0][8] = new Lion(0,8,1);
        pieceBoard[0][6] = new Rat(0,6,1);
        pieceBoard[2][6] = new Leopard(2,6,1);
        pieceBoard[4][6] = new Wolf(4,6,1);
        pieceBoard[6][6] = new Elephant(6,6,1);
        pieceBoard[1][7] = new Dog(1,7,1);
        pieceBoard[5][7] = new Cat(5,7,1);
        pieceBoard[6][8] = new Tiger(6,8,1);
    }

    public boolean isAdj(int oldX, int oldY, int newX, int newY){
        if (oldX == newX){
            if (newY == oldY + 1 || newY == oldY - 1) {
                return true;
            }
            else {
                return false;
            }
        }
        else if (oldY == newY) {
            if (newX == oldX + 1 || newX == oldX - 1) {
                return true;
            } else {
                return false;
            }
        }
        else{
            return false;
        }
    }

    public boolean isRiver(int x, int y){
        return board[x][y] == Area.RIVER;
    }

    public boolean isTrap(int x, int y){ // This method is not complete
        return board[x][y] == Area.TRAP;
    }

    public boolean isAcrossWater(int oldX, int oldY, int newX, int newY){
        if (newX == oldX){
            if(oldY<newY){//jump rightward
                for(int i = oldY+1;i<newY;i++){
                    if(board[oldX][i] != Area.RIVER||getPiece(oldX,i)!=null){
                        return false;
                    }
                }
            else{//jump leftward
                for(int i = oldY-1;i>newY;i--){
                    if(board[oldX][i] != Area.RIVER||getPiece(oldX,i)!=null){
                        return false;
                    }
                }
            }
            return true;
        }
        else if (newY == oldY){
            if(oldX<newX){//jump downward
                for(int i = oldX+1; i <newX;i++){
                    if(board[i][oldY] != Area.RIVER||getPiece(i,oldY)!=null){
                        return false;
                    }
                }
            }
            else{//jump upward
                for(int i = oldX-1;i>newX;i--){
                    if(board[i][oldY] != Area.RIVER||getPiece(i,oldY)!=null){
                        return false;
                    }
                }
            }
            return true;
        }
        else {
            return false;
        }
    }

    public Piece getPiece(int x, int y){
        return pieceBoard[x][y];
    }
    public void setPiece(int x, int y, Piece piece){
        pieceBoard[x][y] = piece;
        pieceBoard[piece.getX()][piece.getY()] = null;
    }
}
