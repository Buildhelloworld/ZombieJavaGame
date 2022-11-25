package javaapplication5;
import java.awt.geom.Rectangle2D;
import javax.swing.ImageIcon;
public class Gun {
    public ImageIcon[] im = new ImageIcon[2];
    public int x=20;
    public int y=600;
    public int w=128;
    public int h=102;

    int speed0 = 10;
    int speed = 10;

    public int count = 0;
    
    Gun(){
        for(int i=0;i<im.length;i++){
            im[i] = new ImageIcon(this.getClass().getResource("img/shoot"+(i+1)+".png"));
        }
    }

    void Fireball(int x,int y){
        for(int i=0;i<im.length;i++){
            String imageLocation = "img/b"+(i+1)+".png";
            im[i] = new ImageIcon(this.getClass().getResource(imageLocation));
            }
            this.x=x;
            this.y=y;
    }

    public void GoLeft(){
        this.x -= speed;
    }
    public void GoRight(){
        this.x += speed;
    }

    public Rectangle2D getbound(){
        return (new Rectangle2D.Double(x,y,this.w,this.h));
    }

    public int getX(){
        return this.x;
    }
    public void setX(int x){
        this.x = x;
    }
    public int getY(){
        return this.y;
    }
    public int getW(){
        return this.w;
    }
    public int getH(){
        return this.h;
    }
}

