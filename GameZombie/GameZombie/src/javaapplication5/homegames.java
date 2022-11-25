package javaapplication5;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

public class homegames extends JPanel{
        private ImageIcon feild = new ImageIcon(this.getClass().getResource("img/home.gif"));
	private ImageIcon exit = new ImageIcon(this.getClass().getResource("img/exitt.png"));
	private ImageIcon starts = new ImageIcon(this.getClass().getResource("img/startt.png"));
	public JButton BStart = new JButton(starts);
	public JButton BExit1  = new JButton(exit);
	homegames(){
            setLayout(null);
            BExit1.setBounds(200, 500, 170,90);
            add(BExit1);
            add(BStart);
            BStart.setBounds(200,350,170,90);
            add(BStart);
	}
	public void paintComponent(Graphics g){
            super.paintComponent(g);
            g.drawImage(feild.getImage(),0,0,1300,800,this);
            g.setColor(Color.BLACK);
            g.setFont(new Font("HalloweenSlime",Font.CENTER_BASELINE,160));		
            g.drawString("Dead Zone",100,250);	
	}
}