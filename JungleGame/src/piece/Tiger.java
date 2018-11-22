package piece;
/**
 * This class is a subclass of piece, corresponding to a tiger
 * in the gameboard.
 */
public class Tiger extends Piece {
    private static final int _rank = 6;
    /**
     * Constructor.
     * @param side indicates which user it belongs to
     */
    public Tiger(int side) {
        super(side, "tiger");
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
