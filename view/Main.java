package view;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import Piece.*;
import board.*;
import java.awt.*;

import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;

public class Main extends JFrame implements MouseListener {
    private static final int Height = 700;
    private static final int Width = 1110;
    private Player White = null, Black = null;
    private JPanel board = new JPanel(new GridLayout(7, 9));
    private JPanel wdetails = new JPanel(new GridLayout(3, 3));
    private JPanel bdetails = new JPanel(new GridLayout(3, 3));
    private JPanel wcombopanel = new JPanel();
    private JPanel bcombopanel = new JPanel();
    private JPanel controlPanel, WhitePlayer, BlackPlayer, temp, displayTime, showPlayer, time;
    private JSplitPane split;
    private JLabel label, mov;
    private static JLabel CHNC;
    private Time timer;
    public static Main Mainboard;
    private boolean selected = false, end = false;
    private Container content = null;
    private ArrayList<Player> wplayer, bplayer;
    private ArrayList<String> Wnames = new ArrayList<String>();
    private ArrayList<String> Bnames = new ArrayList<String>();
    private JComboBox<String> wcombo, bcombo;
    private String wname = null, bname = null, winner = null;
    static String move;
    private Player tempPlayer;
    private JScrollPane wscroll, bscroll;
    private String[] WNames = {}, BNames = {};
    private JSlider timeSlider;
    private BufferedImage image;
    private Cell c,previous;
    private Button start, wselect, bselect, WNewPlayer, BNewPlayer;
    public static int timeRemaining = 60;

    public static void main(String[] args) {
        Mainboard = new Main();
        Mainboard.setVisible(true);
        Mainboard.setResizable(false);
    }

    private Main() {
        GameBoard b = new GameBoard();

        timeRemaining = 60;
        timeSlider = new JSlider();
        move = "White";
        wname = null;
        bname = null;
        winner = null;
        bcombopanel = new JPanel();
        wcombopanel = new JPanel();
        board.setMinimumSize(new Dimension(800, 700));
        //fetch the player information;
        wplayer = Player.fetch_players();
        Iterator<Player> witr = wplayer.iterator();
        while (witr.hasNext())
            Wnames.add(witr.next().name());

        bplayer = Player.fetch_players();
        Iterator<Player> bitr = bplayer.iterator();
        while (bitr.hasNext())
            Bnames.add(bitr.next().name());
        WNames = Wnames.toArray(WNames);
        BNames = Bnames.toArray(BNames);
        //绘制开头的表格
        Cell cell;
        board.setBorder(BorderFactory.createLoweredBevelBorder());
        Piece P;
        content = getContentPane();
        setSize(Width, Height);
        setTitle("Chess");
        content.setBackground(Color.black);
        controlPanel = new JPanel();
        content.setLayout(new BorderLayout());
        controlPanel.setLayout(new GridLayout(3, 3));
        controlPanel.setBorder(BorderFactory.createTitledBorder(null, "Statistics", TitledBorder.TOP, TitledBorder.CENTER, new Font("Lucida Calligraphy", Font.PLAIN, 20), Color.ORANGE));
        //绘制下面的用户
        WhitePlayer = new JPanel();
        WhitePlayer.setBorder(BorderFactory.createTitledBorder(null, "White Player", TitledBorder.TOP, TitledBorder.CENTER, new Font("times new roman", Font.BOLD, 18), Color.RED));
        WhitePlayer.setLayout(new BorderLayout());

        BlackPlayer = new JPanel();
        BlackPlayer.setBorder(BorderFactory.createTitledBorder(null, "Black Player", TitledBorder.TOP, TitledBorder.CENTER, new Font("times new roman", Font.BOLD, 18), Color.BLUE));
        BlackPlayer.setLayout(new BorderLayout());

        // 页面跳转
        JPanel whitestats = new JPanel(new GridLayout(3, 3));
        JPanel blackstats = new JPanel(new GridLayout(3, 3));
        wcombo = new JComboBox<String>(WNames);
        bcombo = new JComboBox<String>(BNames);
        wscroll = new JScrollPane(wcombo);
        bscroll = new JScrollPane(bcombo);
        wcombopanel.setLayout(new FlowLayout());
        bcombopanel.setLayout(new FlowLayout());
        wselect = new Button("Select");
        bselect = new Button("Select");
        //wselect.addActionListener(new SelectHandler(0));
        // bselect.addActionListener(new SelectHandler(1));
        WNewPlayer = new Button("New Player");
        BNewPlayer = new Button("New Player");
        //WNewPlayer.addActionListener(new Handler(0));
        // BNewPlayer.addActionListener(new Handler(1));
        wcombopanel.add(wscroll);
        wcombopanel.add(wselect);
        wcombopanel.add(WNewPlayer);
        bcombopanel.add(bscroll);
        bcombopanel.add(bselect);
        bcombopanel.add(BNewPlayer);
        WhitePlayer.add(wcombopanel, BorderLayout.NORTH);
        BlackPlayer.add(bcombopanel, BorderLayout.NORTH);
        whitestats.add(new JLabel("Name   :"));
        whitestats.add(new JLabel("Played :"));
        whitestats.add(new JLabel("Won    :"));
        blackstats.add(new JLabel("Name   :"));
        blackstats.add(new JLabel("Played :"));
        blackstats.add(new JLabel("Won    :"));
        WhitePlayer.add(whitestats, BorderLayout.WEST);
        BlackPlayer.add(blackstats, BorderLayout.WEST);
        controlPanel.add(WhitePlayer);
        controlPanel.add(BlackPlayer);
        showPlayer = new JPanel(new FlowLayout());
        showPlayer.add(timeSlider);
        JLabel setTime = new JLabel("Set Timer(in mins):");
        start = new Button("Start");
        start.setBackground(Color.black);
        start.setForeground(Color.white);
        start.addActionListener(new START());
        start.setPreferredSize(new Dimension(120, 40));
        setTime.setFont(new Font("Arial", Font.BOLD, 16));
        label = new JLabel("Time Starts now", JLabel.CENTER);
        label.setFont(new Font("SERIF", Font.BOLD, 30));
        displayTime = new JPanel(new FlowLayout());
        time = new JPanel(new GridLayout(3, 3));
        time.add(setTime);
        time.add(showPlayer);
        displayTime.add(start);
        time.add(displayTime);
        controlPanel.add(time);
        board.setMinimumSize(new Dimension(800, 700));

        //The Left Layout When Game is inactive
        temp = new JPanel() {
            private static final long serialVersionUID = 1L;

            @Override
            public void paintComponent(Graphics g) {
                try {
                    image = ImageIO.read(this.getClass().getResource("clash.jpg"));
                } catch (IOException ex) {
                    System.out.println("not found");
                }

                g.drawImage(image, 0, 0, null);
            }
        };

        temp.setMinimumSize(new Dimension(800, 700));
        controlPanel.setMinimumSize(new Dimension(285, 700));
        split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, temp, controlPanel);

        content.add(split);
        setDefaultCloseOperation(EXIT_ON_CLOSE);


    }

    class START implements ActionListener {

        @SuppressWarnings("deprecation")
        @Override
        public void actionPerformed(ActionEvent arg0) {


            if (White == null || Black == null) {
                JOptionPane.showMessageDialog(controlPanel, "Fill in the details");
                return;
            }
            White.updateGamesPlayed();
            White.Update_Player();
            Black.updateGamesPlayed();
            Black.Update_Player();
            WNewPlayer.disable();
            BNewPlayer.disable();
            wselect.disable();
            bselect.disable();
            split.remove(temp);
            split.add(board);
            showPlayer.remove(timeSlider);
            mov = new JLabel("Move:");
            mov.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
            mov.setForeground(Color.red);
            showPlayer.add(mov);
            CHNC = new JLabel(move);
            CHNC.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
            CHNC.setForeground(Color.blue);
            showPlayer.add(CHNC);
            displayTime.remove(start);
            displayTime.add(label);
            timer = new Time(label);
            timer.start();
        }
    }

}
