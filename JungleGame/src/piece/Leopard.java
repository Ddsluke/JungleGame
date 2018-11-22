package piece;
/**
 * This class is a subclass of piece, corresponding to a leopard
 * in the gameboard.
 */
public class Leopard extends Piece {
    private static final int _rank = 5;
    /**
     * Constructor.
     * @param side indicates which user it belongs to
     */
    public Leopard( int side){
        super( side,"leopard");
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
