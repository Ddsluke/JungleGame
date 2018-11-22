package piece;

import board.GameBoard;
import view.*;
import javax.swing.*;

/**
 * This class is about different kinds of animals in
 * the gameboard.
        */
public  class Piece extends JLabel {
    /**
     * rank refers to the relation indicating whether an animal
     * can eat another animal
     */
   protected int rank;

    /**
     * path is the image path of one animal
     */
    private String path;
    /**
     * side refers to the user an animal belongs to
     * 0 for one user, 1 for another user
     */
    private final int side; // 0 or 1
    /**
     * index is used to  uniquely identify a piece,
     * which can be used to find the picture of a piece.
     */
    protected int index;
    /**
     * the name of this piece
     */
    private final String name;

    /**
     * Constructor
     * @param side this represents the user that the pieces belong to
     * @param name the name of pieces
     */
     Piece( int side, String name) {
        this.side = side;
        this.name = name;
    }

    /**
     * @return the file path of the picture of this piece
     */
    public String getPath() {
        return this.path;
    }

    /**
     *
     * @param path the file path of the picture of this piece
     */
    public void setPATH(String path) {
        this.path = path;
    }

    /**
     * gets which user the piece belongs to
     * @return an integer indicating the user that piece belongs to
     */
    public int getSide(){
        return side;
    }

    /**
     *
     * @return the dynamic rank value (after instantiation )
     */
    int getRank(){return  rank ;}
@Override
    /**
     * @return the name of this piece
     */
    public String getName(){return name;}

    /**
     *
     * @return the index of an instance
     */
    public int getIndex(){return index;}

    /**
     * judges whether two pieces belongs to one user
     * @param other the other piece
     * @return boolean value(if two pieces from one user, true
     * vice versa )
     */
    public boolean isSameSide(Piece other) {
        return this.getSide() == other.getSide();
    }
    /**
     * judges whether a piece can eat the other piece
     * @param c refers to one unit of a gameboard
     * @param gameBoard refers to the board holding all pieces
     * @return a boolean value; true for being able to eat, vice versa
     * @see  GameBoard
     * @see Cell
     */
    public boolean ableToCapture(Cell c, GameBoard gameBoard){
        return(c.getPiece().getRank() <= this.rank || gameBoard.isTrap(c.getx(), c.gety()));

    }










}