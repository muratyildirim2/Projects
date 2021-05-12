package oyunekrani;
import java.awt.BorderLayout;
import java.awt.Color;
import static java.awt.Color.WHITE;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageInputStream;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Timer;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

class cephane {
    private int x;
    private int y;

    public cephane(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public int getX() {
        return x;
    }
    public void setX(int x) {
        this.x = x;
    }
    public int getY() {
        return y;
    }
    public void setY(int y) {
        this.y = y;
    }
}

class yuva {
    private int x;
    private int y;
    public int solsag;
    public yuva(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.solsag = z;
    }
    public int getX() {
        return x;
    }
    public void setX(int x) {
        this.x = x;
    }
    public int getY() {
        return y;
    }
    public void setY(int y) {
        this.y = y;
    }
}

class yumurta {
    private int x;
    private int y;
    private int way;
    public yumurta(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.way = z;
    }
    public int getWay() {
        return way;
    }
    public void setWay(int way) {
        this.way = way;
    }
    public int getX() {
        return x;
    }
    public void setX(int x) {
        this.x = x;
    }
    public int getY() {
        return y;
    }
    public void setY(int y) {
        this.y = y;
    }
}

public class Oyun extends JPanel implements KeyListener, ActionListener {

    Timer timer = new Timer(10, this);
   
    boolean mainMenu = true;
    static Color tan = Color.decode("#F4EBC3");
    static Color darkGreen = Color.decode("#668284");
    static Color buttonColor = Color.decode("#A2896B");
    Rectangle header = new Rectangle(0, 0, 500, 100);
    Rectangle body = new Rectangle(0, 100, 500, 400);
    Rectangle start = new Rectangle(150, 150, 200, 40);
    private int zaman = 0, gecen_sure = 0, harcanan_yumurta = 0, yumurta_yol = 3, topX = 0, score = 0;
    private BufferedImage congr, image, currimage, tcurrimage, image2, egg, yuva, background, top, mizrak, yuvasol, yuvasag;
    private ArrayList < yuva > yuvalar = new ArrayList < yuva > ();

    private ArrayList < yumurta > yumurtalar = new ArrayList < yumurta > ();
    private ArrayList < cephane > mizraklar = new ArrayList < cephane > ();
    private int topdirX = 5, kusX = 200, kusY = 90, kus_yol = 40, simetri = 1;
    private int level, left, right, at, ss, at2, tata, seviye;
    public BufferedImage anime[] = new BufferedImage[9];
    public BufferedImage anime2[] = new BufferedImage[9];
    Random r = new Random();
    Random r2 = new Random();
    Random s = new Random();
    Random s3 = new Random();
    Random s4 = new Random();
    static JFrame f;
    static JButton b, b1, b2, b3;
    static JLabel l;
    private int speed = 0;
    private int frames = 9;
    private int per_frame = 0;
    private int index = 0;
    private int count = 0;
    private int speed2 = 0;
    private int frames2 = 9;
    private int per_frame2 = 0;
    private int index2 = 0;
    private int count2 = 0;

    public void drawCenteredString(String s, int w, int h, Graphics g) {
        FontMetrics fm = g.getFontMetrics();
        int x = (w - fm.stringWidth(s)) / 2;
        int y = (fm.getAscent() + (h - (fm.getAscent() + fm.getDescent())) / 2);
        g.drawString(s, x, y);
    }
    public boolean kontrolEt() {
        for (yumurta ates: yumurtalar)
            for (yuva sepet: yuvalar)
                if (new Rectangle(ates.getX(), ates.getY(), 20, 40).intersects(new Rectangle(sepet.getX() + 10, sepet.getY() + 75, 40, 80))) {

                    yuvalar.remove(sepet);
                    yumurtalar.remove(ates);
                    score += 10;
                    return true;
                }
        return false;

    }
 
    public boolean kontrolEt2() {
        //System.out.println(tata );
        for (cephane mermi: mizraklar)
            if (new Rectangle(mermi.getX(), mermi.getY(), 5, 15).intersects(new Rectangle(kusX + 2, kusY, 120, 50)))
                return true;
        return false;
    }
    public Oyun() {
        try {
            currimage = ImageIO.read(new FileImageInputStream(new File("kus1.png")));
            anime[0] = ImageIO.read(new FileImageInputStream(new File("kus1.png")));
            anime[1] = ImageIO.read(new FileImageInputStream(new File("kus2.png")));
            anime[2] = ImageIO.read(new FileImageInputStream(new File("kus3.png")));
            anime[3] = ImageIO.read(new FileImageInputStream(new File("kus4.png")));
            anime[4] = ImageIO.read(new FileImageInputStream(new File("kus5.png")));
            anime[5] = ImageIO.read(new FileImageInputStream(new File("kus6.png")));
            anime[6] = ImageIO.read(new FileImageInputStream(new File("kus7.png")));
            anime[7] = ImageIO.read(new FileImageInputStream(new File("kus8.png")));
            anime[8] = ImageIO.read(new FileImageInputStream(new File("kus9.png")));
            tcurrimage = ImageIO.read(new FileImageInputStream(new File("tkus1.png")));
            anime2[0] = ImageIO.read(new FileImageInputStream(new File("tkus1.png")));
            anime2[1] = ImageIO.read(new FileImageInputStream(new File("tkus2.png")));
            anime2[2] = ImageIO.read(new FileImageInputStream(new File("tkus3.png")));
            anime2[3] = ImageIO.read(new FileImageInputStream(new File("tkus4.png")));
            anime2[4] = ImageIO.read(new FileImageInputStream(new File("tkus5.png")));
            anime2[5] = ImageIO.read(new FileImageInputStream(new File("tkus6.png")));
            anime2[6] = ImageIO.read(new FileImageInputStream(new File("tkus7.png")));
            anime2[7] = ImageIO.read(new FileImageInputStream(new File("tkus8.png")));
            anime2[8] = ImageIO.read(new FileImageInputStream(new File("tkus9.png")));
            egg = ImageIO.read(new FileImageInputStream(new File("egg.png")));
            mizrak = ImageIO.read(new FileImageInputStream(new File("tiger.png")));
            background = ImageIO.read(new FileImageInputStream(new File("deneme.jpg")));
            //if(solsag==1)
            yuvasol = ImageIO.read(new FileImageInputStream(new File("yuvasol.png")));
            //if(solsag==2)
            yuvasag = ImageIO.read(new FileImageInputStream(new File("yuvasag.png")));
         

        } catch (IOException ex) {
            Logger.getLogger(Oyun.class.getName()).log(Level.SEVERE, null, ex);
        }
        timer.start();
    }
    @Override
    public void paint(Graphics g) {
        super.paint(g); //To change body of generated methods, choose Tools | Templates.
        Dimension d = this.getSize();
        gecen_sure += 5;
        g.drawImage(background, 0, 0, null);
        tata = topX + 800;
        if (simetri == 1) {
            g.drawImage(tcurrimage, kusX, kusY, tcurrimage.getWidth() / 2, tcurrimage.getHeight() / 2, this);
            index++; //1
            if (index > speed) {
                index = 0;
                for (int i = 0; i < frames; i++) {

                    if (count == i) {
                        tcurrimage = anime2[i]; //
                    }
                }
                per_frame++;
                if (per_frame % 6 == 0) {
                    count++;
                    per_frame = 0;
                }

                if (count > frames) {
                    count = 0;
                }
            }

        } else {
            g.drawImage(currimage, kusX, kusY, currimage.getWidth() / 2, currimage.getHeight() / 2, this);
            index++; //1
            if (index > speed) {
                index = 0;
                for (int i = 0; i < frames; i++) {

                    if (count == i) {

                        currimage = anime[i]; //

                    }
                }
                per_frame++;
                if (per_frame % 6 == 0) {
                    count++;
                    per_frame = 0;
                }
                if (count > frames) {
                    count = 0;
                }
            }
        }
        zaman++;
        g.setColor(Color.yellow);
        g.fillRect(header.x + 700, header.y, header.width, 30);
        g.setColor(Color.BLACK);
        g.setFont(new Font("TimesRoman", Font.BOLD, 20));
        drawCenteredString("Fly to freedom !!\n", d.width, 25, g);


        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(header.x + 700, header.y + 30, header.width, 30);
        g.setColor(Color.BLACK);
        drawCenteredString("Level :\n", d.width, 85, g);


        if (seviye == 1) {
            drawCenteredString(" 1 ", 2100, 85, g);

        }
        if (seviye == 2) {
            drawCenteredString(" 2 ", 2100, 85, g);

        }
        if (seviye == 3) {
            drawCenteredString(" 3 ", 2100, 85, g);

        }
        if (seviye == 4) {
            drawCenteredString(" 4 ", 2100, 85, g);

        }
        for (yumurta ates: yumurtalar) {
            if (ates.getY() < 50)
                yumurtalar.remove(ates);
        }
        for (cephane mermi: mizraklar) {
            g.drawImage(mizrak, mermi.getX(), mermi.getY(), mizrak.getWidth() / 7, mizrak.getHeight() / 7, this);

        }
        for (yuva sepet: yuvalar) {
            if (sepet.solsag == 1) {
                g.drawImage(yuvasol, sepet.getX(), sepet.getY(), yuvasol.getWidth() / 10, yuvasol.getHeight() / 5, this);

            } else if (sepet.solsag == 2) {
                g.drawImage(yuvasag, sepet.getX(), sepet.getY(), yuvasag.getWidth() / 10, yuvasag.getHeight() / 5, this);
            }
        }
        for (yumurta ates: yumurtalar) {
            g.drawImage(egg, ates.getX(), ates.getY(), egg.getWidth() / 25, egg.getHeight() / 25, this);
        }
        if (kontrolEt2()) {
            timer.stop();
            String message = "Game Over... İyi İş Çıkardın\n" +
                "SCORE = " + score + "\n" +
                "HARCANAN YUMURTA = " + harcanan_yumurta;
            JOptionPane.showMessageDialog(this, message);
            System.exit(0);
        }
        if (kontrolEt()) {
            
        }
    }
    @Override
    public void repaint() {
        super.repaint();
    }
    @Override
    public void keyTyped(KeyEvent e) {}
    @Override
    public void keyPressed(KeyEvent e) {
        int c = e.getKeyCode();

        if (c == KeyEvent.VK_A) {
            if (kusX <= 0) {
                kusX = 0;
            } else {
                kusX -= kus_yol;
            }
            simetri = 1;
        }
        if (c == KeyEvent.VK_W) {
            if (kusY <= 0) {
                kusY = 0;
            } else {
                kusY -= kus_yol;
            }

        }
        if (c == KeyEvent.VK_S) {
            if (kusY >= 800) {
                kusY = 800;
            } else {
                kusY += kus_yol;
            }
        } else if (c == KeyEvent.VK_D) {

            if (kusX >= 1750) {
                kusX = 1750;
            } else {
                kusX += kus_yol;
            }
            simetri = 0;
        } else if (c == KeyEvent.VK_SPACE) {
            if (simetri == 1) {
                yumurtalar.add(new yumurta(kusX, kusY + 50, simetri));
               
            } else {
                yumurtalar.add(new yumurta(kusX + 90, kusY + 50, simetri));
               
            }
            harcanan_yumurta++;
        }
    }
    @Override
    public void keyReleased(KeyEvent e) {}
    @Override
    public void actionPerformed(ActionEvent e) {

        at = r.nextInt(1850);
        at2 = r2.nextInt(1850);
        left = s3.nextInt(750) + 60;
        right = s4.nextInt(750) + 60;
        ss = s.nextInt(3);
        level = 500;
        seviye = 1;
        if (score > 50) {
            level = 400;
            seviye = 2;
            yumurta_yol = 3;
        } else if (score > 200) {
            level = 350;
            seviye = 3;
            yumurta_yol = 6;
        } else if (score > 300) {
            level = 2900;
            seviye = 4;
            yumurta_yol = 9;
        } else if (score > 500) {
            level = 180;
            seviye = 5;
            yumurta_yol = 12;
        }
        if ((gecen_sure % level) == 0) {
            mizraklar.add(new cephane(at, 800));
        }
        if ((gecen_sure % (1000)) == 0) {
            if (ss == 1) {
                yuvalar.add(new yuva(-10, left, 1));
            } else if (ss == 0) {
                yuvalar.add(new yuva(1850, right, 2));
            }
        }
        for (cephane mermi: mizraklar) {
            mermi.setY(mermi.getY() - yumurta_yol);
        }
        for (yumurta ates: yumurtalar) {
            if (ates.getWay() == 1)
                ates.setX(ates.getX() - yumurta_yol);
            else
                ates.setX(ates.getX() + yumurta_yol);
        }
        topX -= topdirX;
        repaint();
    }
}