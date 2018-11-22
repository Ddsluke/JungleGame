package piece;

/**
 * This class is a subclass of piece, corresponding to a dog
 * in the gameboard.
 */
public class Dog extends Piece {
    private static final int _rank = 3;
    /**
     * Constructor.
     * @param side indicates which user it belongs to
     */
    public Dog(int side){
        super( side,"dog");
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
