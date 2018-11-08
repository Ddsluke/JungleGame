package Module;

public abstract class piece {
    public int x_p;
    public int y_p;
    public int  rank;
    public int side;
    boolean isAlive;
    public  boolean inRiver;//0 ,it is initially in the land
    public boolean inTrap;//0, it is initially not in the trap

    abstract  void move(int newX_p,int newY_p,gameBoard board);
    abstract  boolean ableToCapture(piece other);



}
