package piece;

import board.GameBoard;
import view.Cell;

/**
 * This class is a subclass of piece, corresponding to an elephant
 *  in the gameboard.
 */
public class Elephant extends Piece {
    private static final int _rank = 8;
    /**
     * Constructor.
     * @param side indicates which user it belongs to
     */
    public Elephant( int side){

        super(side,"elephant");
        rank = _rank;
        index = 2*rank-side;

    }
    /**
     *
     * @return static rank value of a specific piece
     */
    public static int getrank(){
        return _rank;
    }

@Override
public boolean ableToCapture(Cell other, GameBoard gameBoard) {
    if (other.getPiece().getRank() <= this.rank && other.getPiece().getRank() != 1) {
        return true;
    } else if (gameBoard.isTrap(other.getx(), other.gety())) {
        return true;
    } else {
        return false;
    }
}}

