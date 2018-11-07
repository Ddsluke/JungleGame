public class GameBoard {
    private Area[][] board = new Area[7][9];
    private Piece[][] pieceBoard;
    GameBoard(){
        board[2][0] = Area.TRAP;
        board[3][0] = Area.DEN;
        board[4][0] = Area.TRAP;
        board[3][1] = Area.TRAP;
        // On this side
        board[2][8] = Area.TRAP;
        board[3][8] = Area.DEN;
        board[4][8] = Area.TRAP;
        board[3][7] = Area.TRAP;
        // On the other side

        for (int i = 1; i < 3; i++){
            for(int j = 3; j < 6; j++) {
                board[i][j] = Area.RIVER;
                board[i + 3][j] = Area.RIVER;
            }
        }

        //pieceBoard; /*The initialization of pieceBoard
        // leaves blank until the piece classes are finished*/

    }
}
