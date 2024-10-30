package src;
public class Hero {
    private  int x;
    private int y;
    public static int sizeHero = 30;
    private int speed = 10;

    private boolean up, down, left, right;

    public Hero(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void up() {
        y-=speed;
    }
    public void down() {
        y+=speed;
    }
    public void left() {
        x-=speed;
    }

    public void setUp(boolean up) {
        this.up = up;
    }

    public void setDown(boolean down) {
        this.down = down;
    }

    public void setLeft(boolean left) {
        this.left = left;
    }

    public void setRight(boolean right) {
        this.right = right;
    }

    public void right() {
        x+=speed;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public  int getSizeHero() {
        return sizeHero;
    }

    public int getSpeed() {
        return speed;
    }

    public boolean isUp() {
        return up;
    }

    public boolean isDown() {
        return down;
    }

    public boolean isLeft() {
        return left;
    }

    public boolean isRight() {
        return right;
    }
}