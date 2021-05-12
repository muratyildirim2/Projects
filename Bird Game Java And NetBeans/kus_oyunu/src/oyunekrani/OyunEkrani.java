package oyunekrani;

 
import javax.swing.JFrame;

public class OyunEkrani extends JFrame{

    public OyunEkrani (String title) {

    }

    public static void main(String[] args){

        OyunEkrani ekran = new OyunEkrani("Tavuk Oyunu");

        ekran.setResizable(false);
        ekran.setFocusable(false);
        ekran.setSize(1920,1080);
        ekran.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Oyun oyun=new Oyun();
        oyun.requestFocus();
        oyun.addKeyListener(oyun);
        oyun.setFocusable(true);
        oyun.setFocusTraversalKeysEnabled(false);
        ekran.add(oyun);
        ekran.setVisible(true);


    }

}