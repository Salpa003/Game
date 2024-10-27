package src;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.geom.Ellipse2D;

import static java.awt.event.KeyEvent.*;

public class Hero extends JPanel implements ActionListener {
    private int x;
    private int y;

    private int xCenter;
    private int yCenter;
    public static int sizeHero = 30;
    private int speed = 10;

    private boolean up, down, left, right;
    Timer timer;

    public Hero(int x1, int y1) {
        this.x = x1;
        this.y = y1;
        xCenter = x1;
        yCenter = y1;
        timer = new Timer(10, this);
        timer.start();

        setFocusable(true);
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                switch (e.getKeyCode()) {
                    case VK_W:
                        up = true;
                        break;
                    case VK_S:
                        down = true;
                        break;
                    case VK_A:
                        left = true;
                        break;
                    case VK_D:
                        right = true;
                        break;
                    case VK_P:
                        if (timer.isRunning())
                            timer.stop();
                        else
                            timer.start();
                        break;
                    case VK_R:
                        if (!timer.isRunning()) break;
                        x = xCenter;
                        y = yCenter;
                        break;
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                switch (e.getKeyCode()) {
                    case VK_W:
                        up = false;
                        break;
                    case VK_S:
                        down = false;
                        break;
                    case VK_A:
                        left = false;
                        break;
                    case VK_D:
                        right = false;
                        break;
                }
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (up) y -= speed;
        if (down) y += speed;
        if (left) x -= speed;
        if (right) x += speed;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        printHero(g);
    }

    public void printHero(Graphics g) {
        Graphics2D graphics2D = (Graphics2D) g;
        graphics2D.setPaint(Color.GREEN);
        Ellipse2D ellipse = new Ellipse2D.Double(x, y, sizeHero, sizeHero);
        graphics2D.fill(ellipse);
    }
}