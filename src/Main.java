package src;

import javax.swing.*;
import java.awt.*;

public class Main  {
  static   Toolkit toolkit =Toolkit.getDefaultToolkit();
   static Dimension dimension = toolkit.getScreenSize();
    public static void main(String[] args) {
    JFrame jFrame = getJFrame();
    GameAction gameAction = new GameAction(jFrame);
    gameAction.setBackground(Color.BLACK);
    jFrame.add(gameAction,SwingConstants.CENTER);
    gameAction.requestFocusInWindow();


//    Hero hero = gameAction.h();
//        MouseMotionListener mouseMotionListener = new MouseMotionAdapter() {
//            @Override
//            public void mouseMoved(MouseEvent e) {
//                hero.setX(e.getX());
//                hero.setY(e.getY());
//            }
//        };
//    jFrame.addMouseMotionListener(mouseMotionListener);
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
