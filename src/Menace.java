package src;

import java.util.*;

public class Menace {
    long ID = Long.MIN_VALUE;
    int xMax;
    int yMax;
    int xMin;
    int yMin;
    Map<Long,MenaceO> menaces ;

    public Menace(int xMax, int yMax, int xMin, int yMin) {
        this.xMax = xMax;
        this.yMax = yMax;
        this.xMin = xMin;
        this.yMin = yMin;
        menaces = new HashMap<>();
    }

    public  void check() {
        for (var cora : menaces.entrySet()) {
          Cordinate cor = cora.getValue().cordinate;
            if (cor.x > xMax || cor.x < xMin || cor.y > yMax || cor.y < yMin)
                menaces.remove(cor);
        }
    }

    public void generateMenaces() {
        Random random = new Random();
        int v = random.nextInt(8);
       // Vector vector = Vector.values()[v];
        Vector vector =Vector.DOWN;
        Cordinate cordinate = new Cordinate(random.nextInt(xMax), yMin-50);;
//        switch (vector) {
//            case DOWN :
//                cordinate = new Cordinate(random.nextInt(xMax), yMin-50);
//        }
        menaces.put(ID++,new MenaceO(cordinate, vector));
    }

    public void move() {
        for (var menace : menaces.entrySet()) {
            Cordinate cor =menace.getValue().cordinate;
            if (menace.getValue().vector==Vector.DOWN){
                menaces.put(menace.getKey(),new MenaceO(new Cordinate(cor.x, cor.y+40),menace.getValue().vector));
            }
            check();
        }
    }
 static class MenaceO {
        public Vector vector;
        public Cordinate cordinate;

     public MenaceO(Cordinate cordinate ,Vector vector) {
         this.vector = vector;
         this.cordinate = cordinate;
     }
 }
}
