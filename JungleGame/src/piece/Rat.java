package piece;
import board.GameBoard;
import view.Cell;
/**
 * This class is a subclass of piece, corresponding to a rat
 * in the gameboard.
 */
public class Rat extends Piece {
    private static final int _rank = 1;

    /**
     * Constructor.
     * @param side indicates which user it belongs to
     */
    public Rat(int side) {
        super(side, "rat");
        rank = _rank;
        index = 2*rank-side;

    }


    @Override
    public boolean ableToCapture(Cell other, GameBoard gameBoard) {
        if (other.getPiece().getRank() <= this.rank) {
            return true;
        } else if (gameBoard.isTrap(other.getx(), other.gety())) {
            return true;
        } else if (other.getPiece().getRank() == 8) {
            return true;
        } else {
            return false;
        }
    }
    /**
     *
     * @return static rank value of a specific piece
     */
    public static int getrank(){
        return _rank;
    }
}


