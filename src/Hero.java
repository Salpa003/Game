package src;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

import static java.awt.event.KeyEvent.*;

public class Hero extends JPanel implements ActionListener {
    private int x;
    private int y;

    private int xCenter;
    private int yCenter;
    public static int sizeHero = 30;
    private int speed = 10;
    int timerCount=0;

    private boolean up, down, left, right;
    Timer timer;
    Menace menace;

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
                       respawn();
                        break;
                    case VK_ESCAPE:
                        System.exit(0);
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
        timerCount++;
        if (timerCount%30==0) {
            timerCount=0;
            menace.generateMenaces();
            menace.move();
            System.out.println(menace.menaces.size());
        }
        catchHero();
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        printHero(g);
        printMenaces(g);
    }

    public void printMenaces(Graphics g) {
        Graphics2D graphics2D =(Graphics2D) g;
        for (var man :menace.menaces.entrySet()) {
            Rectangle2D rectangle = new Rectangle2D.Double(man.getValue().cordinate.x, man.getValue().cordinate.y,60,60);
            graphics2D.setPaint(Color.RED);
            graphics2D.fill(rectangle);
        }
    }

    public void printHero(Graphics g) {
        Graphics2D graphics2D = (Graphics2D) g;
        graphics2D.setPaint(Color.GREEN);
        Ellipse2D ellipse = new Ellipse2D.Double(x, y, sizeHero, sizeHero);
        graphics2D.fill(ellipse);
        Rectangle2D bounds = ellipse.getBounds2D();

        // Выводим координаты границ
        System.out.println("Границы эллипса:");
        System.out.println("X: " + bounds.getX());
        System.out.println("Y: " + bounds.getY());
        System.out.println("Ширина: " + bounds.getWidth());
        System.out.println("Высота: " + bounds.getHeight());
    }

    private void respawn() {
        if (!timer.isRunning())
            return;
        x = xCenter;
        y = yCenter;
    }

    public void setMenace(Menace menace) {
        this.menace = menace;
    }

    public void catchHero() {
        for (var men : menace.menaces.entrySet()) {
            Cordinate cor = men.getValue().cordinate;
            // Создаем квадрат для угрозы
            Rectangle2D dangerZone = new Rectangle2D.Double(cor.x, cor.y, 60, 60);

            // Создаем круг для героя
            Ellipse2D heroCircle = new Ellipse2D.Double(x, y, sizeHero, sizeHero);

            // Проверяем пересечение
            if (heroCircle.intersects(dangerZone)) {
                System.exit(0);
            }
        }
    }

}