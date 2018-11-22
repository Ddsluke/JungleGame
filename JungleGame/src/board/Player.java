package board;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

import javax.swing.JOptionPane;


/**
 * This is the board.Player Class
 * It provides the functionality of keeping track of all the users
 * Objects of this class is updated and written in the Game's Data Files after every Game
 *
 */
public class Player implements Serializable{

    private static final long serialVersionUID = 1L;
    private final  String name;
    private Integer gamesplayed;
    private Integer gameswon;

    //Constructor

    /**
     * constructor
     * @param name the input name of the user
     */
    public Player(String name)
    {
        this.name = name.trim();
        //this.lname = lname.trim();
        gamesplayed = new Integer(0);
        gameswon = new Integer(0);
    }

    /**
     * get the name input back
     * @return the name input
     */
    public String name()
    {
        return name;
    }

    /**
     *
     * @return Returns the number of games played
     */
    public Integer gamesplayed()
    {
        return gamesplayed;
    }

    /**
     *
     * @return the number of games won
     */
    public Integer gameswon()
    {
        return gameswon;
    }

    /**
     *
     * @return  the win percentage of the player
     */
    public int winpercent()
    {
        return ((gameswon*100)/gamesplayed);
    }


    /**
     * Increments the number of games played
     */
    public void updateGamesPlayed()
    {
        gamesplayed++;
    }

    /**
     * Increments the number of games won
     */
    public void updateGamesWon()
    {
        gameswon++;
    }

    /**
     * Function to fetch the list of the players
     * @return the list of players
     */
    public static ArrayList<Player> fetch_players() {
        Player tempplayer;
        ObjectInputStream input = null;
        ArrayList<Player> players = new ArrayList<Player>();
        try {
            File infile = new File(System.getProperty("user.dir") + File.separator + "chessgamedata.dat");
            input = new ObjectInputStream(new FileInputStream(infile));
            try {
                while (true) {
                    tempplayer = (Player) input.readObject();
                    players.add(tempplayer);
                }
            } catch (EOFException e) {
                input.close();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            players.clear();
            return players;
        } catch (IOException e) {
            e.printStackTrace();
            try {
                input.close();
            } catch (IOException e1) {
                System.out.println(e1.getMessage());
            } catch (java.lang.NullPointerException e2) {
                System.out.println(e2.getMessage());
                JOptionPane.showMessageDialog(null, "Unable to read the required Game files !!");
            } catch (Exception e3) {
                // TODO Auto-generated catch block
                e3.printStackTrace();
            }
        }
            return players;
        }
    
    /**
     * Function to update the statistics of a player
     */
    public void Update_Player()
    {
        ObjectInputStream input = null;
        ObjectOutputStream output = null;
        Player temp_player;
        File inputfile=null;
        File outputfile=null;
        try
        {
            inputfile = new File(System.getProperty("user.dir")+ File.separator + "chessgamedata.dat");
            outputfile = new File(System.getProperty("user.dir")+ File.separator + "tempfile.dat");
        } catch (SecurityException e)
        {
            JOptionPane.showMessageDialog(null, "Read-Write Permission Denied !! Program Cannot Start");
            System.exit(0);
        }
        boolean playerdonotexist;
        try
        {
            if(!outputfile.exists())
                outputfile.createNewFile();
            if(!inputfile.exists())
            {
                output = new ObjectOutputStream(new java.io.FileOutputStream(outputfile,true));
                output.writeObject(this);
            }
            else
            {
                input = new ObjectInputStream(new FileInputStream(inputfile));
                output = new ObjectOutputStream(new FileOutputStream(outputfile));
                playerdonotexist=true;
                try
                {
                    while(true)
                    {
                        temp_player = (Player)input.readObject();
                        if (temp_player.name().equals(name()))
                        {
                            output.writeObject(this);
                            playerdonotexist = false;
                        }
                        else
                            output.writeObject(temp_player);
                    }
                }
                catch(EOFException e){
                    input.close();
                }
                if(playerdonotexist)
                    output.writeObject(this);
            }
            inputfile.delete();
            output.close();
            File newf = new File(System.getProperty("user.dir")+ File.separator + "chessgamedata.dat");
            if(!outputfile.renameTo(newf))
                System.out.println("File Renameing Unsuccessful");
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Unable to read/write the required Game files !! Press ok to continue");
        }
        catch (ClassNotFoundException e)
        {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Game Data File Corrupted !! Click Ok to Continue Builing New File");
        }
        catch (Exception e)
        {
            throw new RuntimeException();

        }
    }
}