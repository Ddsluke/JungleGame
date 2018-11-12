package Player;

public class Player {
    private String name;
    private int side;
    public Player(String name, int side){
        this.name = name;
        this.side = side;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public int getSide(){
        return side;
    }
}
