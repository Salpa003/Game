package src.SupportClass;

public class Cordinate {
    public int x;
    public int y;

    public Cordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public int hashCode() {
        int hash = 17;
        hash +=Integer.hashCode(x);
        hash*=31;
        hash+=Integer.hashCode(y);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
       if (! (obj instanceof Cordinate))
           return false;
       Cordinate cordinate = (Cordinate) obj;
        return this.y == cordinate.y && this.x == cordinate.x;
    }
}
