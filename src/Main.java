package src;

import javax.swing.*;
import java.awt.*;

public class Main {
  static   Toolkit toolkit =Toolkit.getDefaultToolkit();
   static Dimension dimension = toolkit.getScreenSize();
    public static void main(String[] args) {
        JFrame jFrame = getJFrame();
        Hero hero =new Hero(dimension.width/2-Hero.sizeHero,dimension.height/2-Hero.sizeHero);
        jFrame.add(hero);
        hero.setBackground(Color.BLACK);
        Menace menace = new Menace(dimension.width, dimension.height, 0,0);
        hero.setMenace(menace);
        hero.requestFocusInWindow();
    }

    private static JFrame getJFrame() {
        JFrame jFrame = new JFrame();
        jFrame.setTitle("The world");
        jFrame.setVisible(true);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setBounds(0,0,dimension.width,dimension.height);
        return jFrame;
    }
}
