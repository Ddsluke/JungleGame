package piece;
/**
 * This class is a subclass of piece, corresponding to a lion
 * in the gameboard.
 */
public class Lion extends Piece {
    private static final int _rank = 7;
    /**
     * Constructor.
     * @param side indicates which user it belongs to
     */
    public Lion(int side){
        super(side,"lion");
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
}
