package javaapplication5;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import javax.swing.*;

public class playstate1 extends JPanel implements ActionListener {

    private final ImageIcon imgstate1 = new ImageIcon(this.getClass().getResource("img/map1.gif"));
    private final ImageIcon imgstate2 = new ImageIcon(this.getClass().getResource("img/map2.gif"));
    private final ImageIcon imgstate3 = new ImageIcon(this.getClass().getResource("img/map3.gif"));
    private final ImageIcon imgstate4 = new ImageIcon(this.getClass().getResource("img/map4.jpg"));
    private final ImageIcon imgmeleon = new ImageIcon(this.getClass().getResource("img/zombie2.png"));
    private final ImageIcon pause = new ImageIcon(this.getClass().getResource("img/puse.png"));
    private final ImageIcon resum = new ImageIcon(this.getClass().getResource("img/play.png"));
    private final ImageIcon back = new ImageIcon(this.getClass().getResource("img/Replay.png"));
    
    Gun m = new Gun();

    homegames hg = new homegames();
    ImageIcon feildover = new ImageIcon(this.getClass().getResource("img/home.gif"));
    ImageIcon img_paralyze = new ImageIcon(this.getClass().getResource("img/zombie2.png"));
    ImageIcon exitover = new ImageIcon(this.getClass().getResource("img/exitt.png"));
    ImageIcon restart = new ImageIcon(this.getClass().getResource("img/startt.png"));
    JButton BStartover = new JButton(restart);
    JButton BExitover = new JButton(exitover);



    private JLabel score = new JLabel();
    public JButton BPause = new JButton(pause);
    public JButton BReplay = new JButton(back);
    public JButton Bresum = new JButton(resum);

    public ArrayList<Fireball> fireball = new ArrayList<Fireball>();
    public ArrayList<Zombie1> zombie1s = new ArrayList<Zombie1>();
    public ArrayList<Zombie2> zombie2s = new ArrayList<Zombie2>();
    public int times;
    public int HP = 5;
    public int rs1 = 1;
    public int rs2 = 2;
    public int amount = 1000;
    boolean GameRun = true;
    boolean timestart = true;
    boolean startball = false;
    public int scor = 0;
    boolean paralyze1 = false;
    int time_paralyze = 5;

    Thread time = new Thread(new Runnable() {

        public void run() {
            while (true) {
                try {
                    Thread.sleep(10);
                } catch (Exception e) {
                }

                if (timestart == false) {
                    repaint();
                }
            }
        }
    });

    Thread actor = new Thread(new Runnable() {

        public void run() {
            while (true) {
                try {
                    Thread.sleep(1);
                } catch (Exception e) {
                }
                repaint();
            }
        }

    });
    Thread tzombie1 = new Thread(new Runnable() {

        public void run() {
            while (true) {
                try {
                    if (startball == false) {
                        Thread.sleep((long) (Math.random() * 7000) + 2000);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (startball == false) {
                    zombie1s.add(new Zombie1());
                }
            }
        }

    });
    Thread tzombie2 = new Thread(new Runnable() {

        public void run() {
            while (true) {
                try {
                    if (startball == false) {
                        Thread.sleep((long) (Math.random() * 5000) + 2000);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (startball == false) {
                    zombie2s.add(new Zombie2());
                }
            }
        }
    });
    Thread t = new Thread(new Runnable() {

        public void run() {
            while (true) {
                if (timestart == false) {
                    times = (times - 1);
                    if (paralyze1) {
                        time_paralyze--;
                    }
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    });

    playstate1() {
        this.setFocusable(true);
        this.setLayout(null);
        BPause.setBounds(850, 50, 50, 50);
        Bresum.setBounds(950, 50, 50, 50);
        BPause.addActionListener(this);
        Bresum.addActionListener(this);
        BReplay.addActionListener(this);
        this.add(BPause);
        this.add(score);
        this.add(Bresum);
        this.add(BReplay);

        this.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                int a = e.getKeyCode();
                if (!paralyze1) {
                    if (a == KeyEvent.VK_A) {
                        if(m.x>0){m.GoLeft();;}
                    } else if (a == KeyEvent.VK_D) {
                        if(m.x<1100){m.GoRight();}
                    }
                    else if (a == KeyEvent.VK_UP || a == KeyEvent.VK_SPACE) {
                        if(amount>0 && !timestart){
                            for(int i=0;i<2;i++){
                                m.count = i;
                            }
                            fireball.add(new Fireball(m.x+90, 627)); // ตำแหน่งที่บอลออก
                            amount--;// 
                        }
                    }
                }
            }
            public void keyReleased(KeyEvent e) {
                m.count = 0;
            }
        });
        m.x = 400;// ตำแหน่งเกิด
        time.start();
        actor.start();
        t.start();
        tzombie1.start();
        tzombie2.start();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (times <= 0 || HP <= 0) // ก่อนเริ่มเกม หรือ ตาย
        {   
            m.setX(400);
            this.remove(BPause);
            this.remove(Bresum);

            RemoveZomebie();
            BReplay.setBounds(200, 500, 180,80);
            add(BReplay);

            this.setLayout(null);
            g.drawImage(feildover.getImage(), 0, 0, 1300, 800, this);
            g.setColor(Color.BLACK);
            g.setFont(new Font("Hobo Std", Font.HANGING_BASELINE, 40));
            g.drawString("SCORE   " + scor, 380, 200);
            g.setFont(new Font("Hobo Std", Font.HANGING_BASELINE, 70));
            g.drawString("GAME OVER", 290, 150);
            g.drawImage(imgmeleon.getImage(), 580, 360, 400, 400, this);

        } 
        else if (times <= 40 ) 
        {
            g.drawImage(imgstate4.getImage(), 0, 0, 1300, 800, this);
            if (paralyze1) {
            } else {
                g.drawImage(m.im[m.count].getImage(), m.x, 600, 128, 102, this);
            }

            if (m.x < 0) {
                m.x = this.getWidth() - 10;
            }
            if (m.x > this.getWidth()) {
                m.x = 20;
            }

            for (int i = 0; i < fireball.size(); i++) {
                Fireball ba = fireball.get(i);
                g.drawImage(ba.imfire[ba.count % 5].getImage(), ba.x, ba.y, 25, 25, null);
                ba.move();
                ba.count++;
                if (ba.y < 0) {
                    fireball.remove(i);
                }
            }
            //zom1
            for (int i = 0; i < zombie1s.size(); i++) {
                g.drawImage(zombie1s.get(i).getImage(), zombie1s.get(i).getX(), zombie1s.get(i).getY(), 64, 112, this);
            }
            //ทำดาเมจ zombie1
            for (int i = 0; i < fireball.size(); i++) {
                for (int j = 0; j < zombie1s.size(); j++) {
                    if (Intersect(fireball.get(i).getbound(), zombie1s.get(j).getbound())) {

                        int HPzombie = zombie1s.get(j).getHP();
                        
                        if(HPzombie>1){
                            fireball.remove(i);
                            zombie1s.get(j).setHP(HPzombie-1);
                        }else{
                            scor += 10;
                            fireball.remove(i);
                            zombie1s.remove(j);
                        }

                        
                        g.drawString("+10", m.x + 100, 650);
                    }
                }
            }
            //zom2
            for (int i = 0; i < zombie2s.size(); i++) {
                g.drawImage(zombie2s.get(i).getImage(),zombie2s.get(i).getX(),
                zombie2s.get(i).getY(), 165, 164, this);

            }
            //zombie2
            for (int i = 0; i < fireball.size(); i++) {
                for (int j = 0; j < zombie2s.size(); j++) {
                    if (Intersect(fireball.get(i).getbound(), zombie2s.get(j).getbound())) {
                        int HPzombie2 = zombie2s.get(j).getHP();
                        
                        if(HPzombie2>1){
                            fireball.remove(i);
                            zombie2s.get(j).setHP(HPzombie2-1);
                        }else{
                            scor += 10;
                            fireball.remove(i);
                            zombie2s.remove(j);
                        }

                      
                        g.drawString("+10", m.x + 100, 650);
                    }
                }
            }
            //ชน
            for (int j = 0; j < zombie1s.size(); j++) {
                if (Intersect(zombie1s.get(j).getbound(),m.getbound() )) {
                    zombie1s.get(j).setX(zombie1s.get(j).getX()+50);
                    HP=HP-1;
                }
            }

            for (int j = 0; j < zombie2s.size(); j++) {
                if (Intersect(zombie2s.get(j).getbound(),m.getbound() )) {
                    zombie2s.get(j).setX(zombie2s.get(j).getX()+100);
                    HP=HP-2;
                }
            }
            
            g.setFont(new Font("Hobo Std", Font.HANGING_BASELINE, 30));
            g.setColor(Color.WHITE);
            g.drawString("SCORE =  " + scor, 50, this.getHeight() - 10);
            g.setFont(new Font("Hobo Std", Font.HANGING_BASELINE, 50));
            g.drawString("Time " + times, this.getWidth() - 200, this.getHeight() - 50);
            g.setColor(Color.WHITE);
            g.drawString("HP  " + HP, 50, this.getHeight() - 50);
        }
        else if (times <= 60 ) 
        {
            g.drawImage(imgstate3.getImage(), 0, 0, 1300, 800, this);
            if (paralyze1) {
            } else {
                g.drawImage(m.im[m.count].getImage(), m.x, 600, 128, 102, this);
            }

            if (m.x < 0) {
                m.x = this.getWidth() - 10;
            }
            if (m.x > this.getWidth()) {
                m.x = 20;
            }

            for (int i = 0; i < fireball.size(); i++) {
                Fireball ba = fireball.get(i);
                g.drawImage(ba.imfire[ba.count % 5].getImage(), ba.x, ba.y, 25, 25, null);
                ba.move();
                ba.count++;
                if (ba.y < 0) {
                    fireball.remove(i);
                }
            }
            //zom1
            for (int i = 0; i < zombie1s.size(); i++) {
                g.drawImage(zombie1s.get(i).getImage(), zombie1s.get(i).getX(), zombie1s.get(i).getY(), 64, 112, this);
            }
            //ทำดาเมจ zombie1
            for (int i = 0; i < fireball.size(); i++) {
                for (int j = 0; j < zombie1s.size(); j++) {
                    if (Intersect(fireball.get(i).getbound(), zombie1s.get(j).getbound())) {

                        int HPzombie = zombie1s.get(j).getHP();
                        
                        if(HPzombie>1){
                            fireball.remove(i);
                            zombie1s.get(j).setHP(HPzombie-1);
                        }else{
                            scor += 10;
                            fireball.remove(i);
                            zombie1s.remove(j);
                        }

                        g.drawString("+10", m.x + 100, 650);
                    }
                }
            }
            //zom2
            for (int i = 0; i < zombie2s.size(); i++) {
                g.drawImage(zombie2s.get(i).getImage(),zombie2s.get(i).getX(),
                zombie2s.get(i).getY(), 165, 164, this);

            }
            //zombie2
            for (int i = 0; i < fireball.size(); i++) {
                for (int j = 0; j < zombie2s.size(); j++) {
                    if (Intersect(fireball.get(i).getbound(), zombie2s.get(j).getbound())) {
                        int HPzombie2 = zombie2s.get(j).getHP();
                        
                        if(HPzombie2>1){
                            fireball.remove(i);
                            zombie2s.get(j).setHP(HPzombie2-1);
                        }else{
                            scor += 10;
                            fireball.remove(i);
                            zombie2s.remove(j);
                        }

                       
                        g.drawString("+10", m.x + 100, 650);
                    }
                }
            }
            //ชน
            for (int j = 0; j < zombie1s.size(); j++) {
                if (Intersect(zombie1s.get(j).getbound(),m.getbound() )) {
                    zombie1s.get(j).setX(zombie1s.get(j).getX()+50);
                    HP=HP-1;
                }
            }

            for (int j = 0; j < zombie2s.size(); j++) {
                if (Intersect(zombie2s.get(j).getbound(),m.getbound() )) {
                    zombie2s.get(j).setX(zombie2s.get(j).getX()+100);
                    HP=HP-2;
                }
            }
            
            g.setFont(new Font("Hobo Std", Font.HANGING_BASELINE, 30));
            g.setColor(Color.WHITE);
            g.drawString("SCORE =  " + scor, 50, this.getHeight() - 10);
            g.setFont(new Font("Hobo Std", Font.HANGING_BASELINE, 50));
            g.drawString("Time " + times, this.getWidth() - 200, this.getHeight() - 50);
            g.setColor(Color.WHITE);
            g.drawString("HP  " + HP, 50, this.getHeight() - 50);
        }
        else if (times <= 80 ) 
        {
            g.drawImage(imgstate2.getImage(), 0, 0, 1300, 800, this);
            if (paralyze1) {
            } else {
                g.drawImage(m.im[m.count].getImage(), m.x, 600, 128, 102, this);
            }

            if (m.x < 0) {
                m.x = this.getWidth() - 10;
            }
            if (m.x > this.getWidth()) {
                m.x = 20;
            }

            for (int i = 0; i < fireball.size(); i++) {
                Fireball ba = fireball.get(i);
                g.drawImage(ba.imfire[ba.count % 5].getImage(), ba.x, ba.y, 25, 25, null);
                ba.move();
                ba.count++;
                if (ba.y < 0) {
                    fireball.remove(i);
                }
            }
            //zom1
            for (int i = 0; i < zombie1s.size(); i++) {
                g.drawImage(zombie1s.get(i).getImage(), zombie1s.get(i).getX(), zombie1s.get(i).getY(), 64, 112, this);
            }
            //ทำดาเมจ zombie1
            for (int i = 0; i < fireball.size(); i++) {
                for (int j = 0; j < zombie1s.size(); j++) {
                    if (Intersect(fireball.get(i).getbound(), zombie1s.get(j).getbound())) {

                        int HPzombie = zombie1s.get(j).getHP();
                        
                        if(HPzombie>1){
                            fireball.remove(i);
                            zombie1s.get(j).setHP(HPzombie-1);
                        }else{
                            scor += 10;
                            fireball.remove(i);
                            zombie1s.remove(j);
                        }
                        g.drawString("+10", m.x + 100, 650);
                    }
                }
            }
            //zom2
            for (int i = 0; i < zombie2s.size(); i++) {
                g.drawImage(zombie2s.get(i).getImage(),zombie2s.get(i).getX(),
                zombie2s.get(i).getY(), 165, 164, this);

            }
            //zombie2
            for (int i = 0; i < fireball.size(); i++) {
                for (int j = 0; j < zombie2s.size(); j++) {
                    if (Intersect(fireball.get(i).getbound(), zombie2s.get(j).getbound())) {
                        int HPzombie2 = zombie2s.get(j).getHP();
                        
                        if(HPzombie2>1){
                            fireball.remove(i);
                            zombie2s.get(j).setHP(HPzombie2-1);
                        }else{
                            scor += 10;
                            fireball.remove(i);
                            zombie2s.remove(j);
                        }

                        g.drawString("+10", m.x + 100, 650);
                    }
                }
            }
            //ชน
            for (int j = 0; j < zombie1s.size(); j++) {
                if (Intersect(zombie1s.get(j).getbound(),m.getbound() )) {
                    zombie1s.get(j).setX(zombie1s.get(j).getX()+50);
                    HP=HP-1;
                }
            }

            for (int j = 0; j < zombie2s.size(); j++) {
                if (Intersect(zombie2s.get(j).getbound(),m.getbound() )) {
                    zombie2s.get(j).setX(zombie2s.get(j).getX()+100);
                    HP=HP-2;
                }
            }
            
            g.setFont(new Font("Hobo Std", Font.HANGING_BASELINE, 30));
            g.setColor(Color.WHITE);
            g.drawString("SCORE =  " + scor, 50, this.getHeight() - 10);
            g.setFont(new Font("Hobo Std", Font.HANGING_BASELINE, 50));
            g.drawString("Time " + times, this.getWidth() - 200, this.getHeight() - 50);
            g.setColor(Color.WHITE);
            g.drawString("HP  " + HP, 50, this.getHeight() - 50);
        } 
        else 
        {
            remove(BReplay);
            add(BPause);
            add(score);
            add(Bresum);
            g.drawImage(imgstate1.getImage(), 0, 0, 1300, 800, this);
            if (paralyze1) {
            } else {
                g.drawImage(m.im[m.count].getImage(), m.x, 600, 128, 102, this);
            }

            if (m.x < 0) {
                m.x = this.getWidth() - 10;
            }
            if (m.x > this.getWidth()) {
                m.x = 20;
            }

            for (int i = 0; i < fireball.size(); i++) {
                Fireball ba = fireball.get(i);
                g.drawImage(ba.imfire[ba.count % 5].getImage(), ba.x, ba.y, 25, 25, null);
                ba.move();
                ba.count++;
                if (ba.y < 0) {
                    fireball.remove(i);
                }
            }
            //zom1
            for (int i = 0; i < zombie1s.size(); i++) {
                g.drawImage(zombie1s.get(i).getImage(), zombie1s.get(i).getX(), zombie1s.get(i).getY(), 64, 112, this);
            }
            //ทำดาเมจ zombie1
            for (int i = 0; i < fireball.size(); i++) {
                for (int j = 0; j < zombie1s.size(); j++) {
                    if (Intersect(fireball.get(i).getbound(), zombie1s.get(j).getbound())) {

                        int HPzombie = zombie1s.get(j).getHP();
                        
                        if(HPzombie>1){
                            fireball.remove(i);
                            zombie1s.get(j).setHP(HPzombie-1);
                        }else{
                            scor += 10;
                            fireball.remove(i);
                            zombie1s.remove(j);
                        }

                        g.drawString("+10", m.x + 100, 650);
                    }
                }
            }
            //zom2
            for (int i = 0; i < zombie2s.size(); i++) {
                g.drawImage(zombie2s.get(i).getImage(),zombie2s.get(i).getX(),
                zombie2s.get(i).getY(), 165, 164, this);

            }
            //zombie2
            for (int i = 0; i < fireball.size(); i++) {
                for (int j = 0; j < zombie2s.size(); j++) {
                    if (Intersect(fireball.get(i).getbound(), zombie2s.get(j).getbound())) {
                        int HPzombie2 = zombie2s.get(j).getHP();
                        
                        if(HPzombie2>1){
                            fireball.remove(i);
                            zombie2s.get(j).setHP(HPzombie2-1);
                        }else{
                             scor += 10;
                            fireball.remove(i);
                            zombie2s.remove(j);
                        }

                        g.drawString("+10", m.x + 100, 650);
                    }
                }
            }
            //ชน
            for (int j = 0; j < zombie1s.size(); j++) {
                if (Intersect(zombie1s.get(j).getbound(),m.getbound() )) {
                    zombie1s.get(j).setX(zombie1s.get(j).getX()+50);
                    HP=HP-1;
                }
            }

            for (int j = 0; j < zombie2s.size(); j++) {
                if (Intersect(zombie2s.get(j).getbound(),m.getbound() )) {
                    zombie2s.get(j).setX(zombie2s.get(j).getX()+100);
                    HP=HP-2;
                }
            }
            
            g.setFont(new Font("Hobo Std", Font.HANGING_BASELINE, 30));
            g.setColor(Color.WHITE);
            g.drawString("SCORE =  " + scor, 50, this.getHeight() - 10);
            g.setFont(new Font("Hobo Std", Font.HANGING_BASELINE, 50));
            g.drawString("Time " + times, this.getWidth() - 200, this.getHeight() - 50);
            g.setColor(Color.WHITE);
            g.drawString("HP  " + HP, 50, this.getHeight() - 50);
        }
    }

    public boolean Intersect(Rectangle2D a, Rectangle2D b) {
        return (a.intersects(b));
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == BStartover) {
            
            this.setSize(100, 800);
            this.add(hg);
            this.setLocation(null);
            times = 100;
            timestart = true;
            startball = true;
            this.remove(BReplay);
            

        } else if (e.getSource() == BExitover) {
            System.exit(0);
        }

    }

    public void Pause() {
        timestart = true ;
        startball = true ;
        ZombieStop();
        PlayerStop();
    }
    public void Continue() {
        timestart = false ;
        startball = false ;

        ZombieMove();
        PlayerMove();
    }

    public void ZombieStop(){
        for(int i =0 ;i<zombie1s.size();i++){
            zombie1s.get(i).speed = 0;
        }
        for(int i =0 ;i<zombie2s.size();i++){
            zombie2s.get(i).speed = 0;
        }
    }
    public void ZombieMove(){
        for(int i =0 ;i<zombie1s.size();i++){
            zombie1s.get(i).speed = zombie1s.get(i).speed0;
        }
        for(int i =0 ;i<zombie2s.size();i++){
            zombie2s.get(i).speed = zombie2s.get(i).speed0;
        }
    }
    public void PlayerStop(){
        m.speed = 0;
    }
    public void PlayerMove(){
        m.speed = m.speed0;
    }

    public void RemoveZomebie(){
        for(int i =0 ;i<zombie1s.size();i++){
            zombie1s.remove(i);
        }
        for(int i =0 ;i<zombie2s.size();i++){
            zombie2s.remove(i);
        }
    }
}
