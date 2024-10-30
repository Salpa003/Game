package src;
public class Hero {
  final   int width = Main.dimension.width;
  final   int height = Main.dimension.height;
    private  int x;
    private int y;
    public static final int sizeHero = 30;
    private int  speed = 10;
    public final int  dash = 80;

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
        if(y<=0)
            y=1;
    }
    public void down() {
        y+=speed;
        if (y>=height-sizeHero)
            y=height-sizeHero-1;
    }
    public void left() {
        x-=speed;
        if (x<=0)
            x=1;
    }
    public void right() {
        x+=speed;
        if (x>=width-sizeHero)
            x=width-sizeHero-1;
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