package javaapplication5;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.geom.Rectangle2D;
import java.net.URL;

public class Zombie1 {

    Image img;
    public int x = 1300;
    public int y = 600;
    public int w = 64;
    public int h = 112;

    public int count = 0;
    public int HP = 2;

    public int speed0 =2;
    public int speed =2;

    Zombie1() {        
        String imageLocation = "img/zombie1.png";
        URL imageURL = this.getClass().getResource(imageLocation);
        img = Toolkit.getDefaultToolkit().getImage(imageURL);
        runner.start();
    }
    Thread runner = new Thread(new Runnable() {
        public void run() {
            while (true) {
                x -= speed;
                if (x <= 0) {
                    img = null;
                    runner = null ;
                    x = 1300 ;
                } 
                try {
                    Thread.sleep(40);
                } catch (InterruptedException e) {
                }
            }
        }
    });
    public Image getImage() {
        return img;
    }

    public int getX() {
        return x;
    }
    public void setX(int x) {
        this.x =x;
    }

    public int getY() {
        return y;
    }
    public int getHP(){
        return this.HP ;
    }
    public void setHP(int hp){
        this.HP = hp ;
    }

    public Rectangle2D getbound() {
        return (new Rectangle2D.Double(x, y, w, h));
    }
}
