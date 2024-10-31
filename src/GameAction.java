package src;

import src.SupportClass.Cordinate;
import src.SupportClass.Menace;
import src.SupportClass.Vector;

import javax.swing.*;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.util.*;

import static java.awt.event.KeyEvent.*;

public class GameAction extends JPanel implements ActionListener {
    long ID = Long.MIN_VALUE;
    boolean lose = false;
    boolean mouseMoved = false;

   final int timerDelay = 10;
    int iter = 0;
    final int iterSpawnMenace = 5;
   final int menaceSize = 40;
   final int menaceSpeed = 10;


  final   int width = Main.dimension.width;
   final int height = Main.dimension.height;

    Hero hero;
    Timer timer;
    Map<Long, Menace> menace;
    ArrayList<Long> delete = new ArrayList<>();
    JFrame jFrame;
    JLabel label;

    public GameAction(JFrame jFrame) {
        this.jFrame = jFrame;
        timer = new Timer(timerDelay, this);
        timer.start();
        hero = new Hero(width / 2 - Hero.sizeHero, height / 2 - Hero.sizeHero);
        setFocusable(true);
        keyAction();
        menace = new HashMap<>();
        settingLabel();
        mouseMotion();
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (!mouseMoved) {
            if (hero.isUp()) hero.up();
            if (hero.isDown()) hero.down();
            if (hero.isLeft()) hero.left();
            if (hero.isRight()) hero.right();
        }

        iter++;

        label.setText(String.valueOf(iter / (1000 / timerDelay)));

        if (iter % iterSpawnMenace == 0) {
            generateMenaces();
        }
        moveMenaces();
        if (iter % 50 == 0) {
            for (Long id : delete) {
                menace.remove(id);
            }
            delete.clear();
            jFrame.setTitle(String.valueOf(menace.size()));
        }
        repaint();
    }

    public void keyAction() {
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                switch (e.getKeyCode()) {
                    case VK_W:
                        hero.setUp(true);
                        break;
                    case VK_S:
                        hero.setDown(true);
                        break;
                    case VK_A:
                        hero.setLeft(true);
                        break;
                    case VK_D:
                        hero.setRight(true);
                        break;
                    case VK_M:
                        mouseMoved = !mouseMoved;
                        break;
                    case VK_R:
                        if (!timer.isRunning()) {
                            timer.start();
                            lose = false;
                        }
                        break;
                }

            }

            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                switch (e.getKeyCode()) {
                    case VK_W:
                        hero.setUp(false);
                        break;
                    case VK_S:
                        hero.setDown(false);
                        break;
                    case VK_A:
                        hero.setLeft(false);
                        break;
                    case VK_D:
                        hero.setRight(false);
                        break;
                }
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        printHero((Graphics2D) g);
        printMenaces((Graphics2D) g);
        if (lose)
            printLose((Graphics2D) g);
    }

    private void printHero(Graphics2D g) {
        Ellipse2D ellipse2D = new Ellipse2D.Double(hero.getX(), hero.getY(), hero.getSizeHero(), hero.getSizeHero());
        g.setPaint(Color.GREEN);
        g.fill(ellipse2D);
    }

    private void printLose(Graphics2D g) {
        g.setPaint(Color.BLUE);
        g.setFont(new Font("Arial", Font.PLAIN, 50));
        g.drawString("Вы проиграли! Нажмите 'R' для респауна.", width/5, height/2);
    }

    private void printMenaces(Graphics2D g) {
        g.setPaint(Color.red);
        for (Menace m : menace.values()) {
            Rectangle2D rectangle2D = new Rectangle2D.Double(m.cordinate.x, m.cordinate.y, menaceSize, menaceSize);
            g.fill(rectangle2D);

            Ellipse2D ellipse2D = new Ellipse2D.Double(hero.getX(), hero.getY(), hero.getSizeHero(), hero.getSizeHero());
            if (ellipse2D.intersects(rectangle2D)) {
                timer.stop();
                    hero.setX(width / 2 - Hero.sizeHero);
                    hero.setY(height / 2 - Hero.sizeHero);
                    menace = new HashMap<>();
                    iter = 0;
                    lose = true;
            }
        }
    }

    int length = 50;

    public void generateMenaces() {
        Random random = new Random();
        int v = random.nextInt(4);
        src.SupportClass.Vector vector = src.SupportClass.Vector.values()[v];
        Cordinate cordinate = null;
        switch (vector) {
            case DOWN:
                cordinate = new Cordinate(random.nextInt(width), 0 - length);
                break;
            case UP:
                cordinate = new Cordinate(random.nextInt(width), height + length);
                break;
            case LEFT:
                cordinate = new Cordinate(width + length, random.nextInt(height));
                break;
            case RIGHT:
                cordinate = new Cordinate(0 - length, random.nextInt(height));
                break;
            default:
             return;
        }
        menace.put(ID++, new Menace(cordinate, vector));
        check();
    }

    public void moveMenaces() {
        for (var m : menace.entrySet()) {
            Cordinate cor = m.getValue().cordinate;
            if (m.getValue().vector == src.SupportClass.Vector.DOWN) {
                menace.put(m.getKey(), new Menace(new Cordinate(cor.x, cor.y + menaceSpeed), m.getValue().vector));
            } else if (m.getValue().vector == src.SupportClass.Vector.UP) {
                menace.put(m.getKey(), new Menace(new Cordinate(cor.x, cor.y - menaceSpeed), m.getValue().vector));
            } else if (m.getValue().vector == src.SupportClass.Vector.LEFT) {
                menace.put(m.getKey(), new Menace(new Cordinate(cor.x - menaceSpeed, cor.y), m.getValue().vector));
            } else if (m.getValue().vector == src.SupportClass.Vector.RIGHT) {
                menace.put(m.getKey(), new Menace(new Cordinate(cor.x + menaceSpeed, cor.y), m.getValue().vector));
            }

        }
    }

    public void check() {

        for (var cora : menace.entrySet()) {
            Cordinate cor = cora.getValue().cordinate;
            if (cora.getValue().vector == src.SupportClass.Vector.UP && cor.y < 0)
                delete.add(cora.getKey());
            else if (cora.getValue().vector == src.SupportClass.Vector.DOWN && cor.y > height)
                delete.add(cora.getKey());
            else if (cora.getValue().vector == src.SupportClass.Vector.LEFT && cor.x < 0)
                delete.add(cora.getKey());
            else if (cora.getValue().vector == Vector.RIGHT && cor.x > width)
                delete.add(cora.getKey());
        }
    }

    public void settingLabel() {
        label = new JLabel("0");
        label.setLocation(width / 2 - width / 10, height / 25);
        label.setBounds(width / 2 - width / 10, height / 25, width / 10, width / 25);
        label.setBackground(Color.CYAN);
        label.setOpaque(true);
        this.add(label);
    }

    public void mouseMotion() {
        MouseMotionListener mouseMotionListener = new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                if (mouseMoved) {
                    hero.setX(e.getX());
                    hero.setY(e.getY());
                }
            }
        };
        jFrame.addMouseMotionListener(mouseMotionListener);
    }

}
