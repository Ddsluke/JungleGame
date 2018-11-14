package view;
import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;

class RoundButton extends JButton {
    public RoundButton(String label) {
        super(label);
        Dimension size = getPreferredSize();
        size.width = size.height = Math.max(size.width, size.height);
        setPreferredSize(size);
        setContentAreaFilled(false);
        this.setBackground(Color.GRAY);
    }

    @Override
    public void setBackground(Color bg) {
        super.setBackground(bg);
    }

    protected void paintComponent(Graphics g) {
        //if (getModel().isArmed()) {
        // g.setColor(Color.lightGray);
        // } else {
        // g.setColor(getBackground());
        //}
        g.fillOval(0, 0, getSize().width - 5, getSize().width - 5);
        super.paintComponent(g);
    }

    protected void paintBorder(Graphics g) {
        //g.setColor(getForeground());
        g.drawOval(0, 0, getSize().width - 5, getSize().width - 5);
        Shape shape;

   /* public boolean contains(int x, int y) {

        if (shape == null || !shape.getBounds().equals(getBounds())) {
            shape = new Ellipse2D.Float(0, 0, getWidth(), getHeight());
        }
        return shape.contains(x, y);
    }*/
    }
}
