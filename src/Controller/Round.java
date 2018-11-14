package Controller;

public class Round {
    private int side = -1;

    public int getSide(){
        return side % 2;
    }

    public void nextRound(){
        this.side++;
    }
}
