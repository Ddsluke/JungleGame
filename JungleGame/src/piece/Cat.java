package piece;

/**
 * this class is the subclass of piece, indicating the animal cat
 */
public class Cat extends Piece{
    private static final int _rank =2;
    /**
     * constructor
     *
     * @param side indicates which user it belongs to
     */
    public Cat( int side){
        super( side,"cat");
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
