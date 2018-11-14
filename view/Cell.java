package view;
import java.awt.*;
import javax.swing.*;

import Piece.*;

public class Cell extends JPanel {
    private boolean ispossibledestination;
    private JLabel content;
    private Piece piece;
    int x,y;                             //is public because this is to be accessed by all the other class
    private boolean isSelected=false;
    private boolean ischeck=false;
    public Cell(int x,int y,Piece p)
    {
        this.x=x;
        this.y=y;

        setLayout(new BorderLayout());
        if(x==2&&(y==0||y==8))
            setBackground(Color.black);//trap
        if(x==3&&(y==0||y==8))
            setBackground(Color.orange);//den
        if(x==4&&(y==0||y==8))
            setBackground(Color.black);//trap
        if(x==3&&(y==0||y==7))
            setBackground(Color.black);//trap
        if(x>=1&&x<3&&y>=3&&y<6)
            setBackground(Color.green);







        if(p!=null)
            setPiece(p);
    }
    public void setPiece(Piece p)    //Function to inflate a cell with a piece
    {
        piece=p;
        //ImageIcon img=new javax.swing.ImageIcon(this.getClass().getResource(p.getPath()));
        content=new JLabel(p.name);
        this.add(content);
    }

}
