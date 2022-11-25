package javaapplication5;
import java.awt.geom.Rectangle2D;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Fireball extends JPanel{
    public ImageIcon[] imfire = new ImageIcon[5];
    public int y;
    public int x;
    public int count=0;

    int speed = 3;

    Fireball(int x,int y){
        for(int i=0;i<imfire.length;i++){
            String imageLocation = "img/b"+(i+1)+".png";
            imfire[i] = new ImageIcon(this.getClass().getResource(imageLocation));
	}
            this.x=x;
            this.y=y;
    }
	
    public void move(){
	    this.x += speed;
    }

    
    public Rectangle2D getbound(){
    	return (new Rectangle2D.Double(x,y,15,15));
    }
}
