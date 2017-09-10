import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ByteArrayOutputStream;
import java.io.File;

/**
 * Created by Jani on 2017.03.27..
 */
//Grafikus resz
public class GUI
    extends JFrame

{

    File file;
    String fajlnev;

    public static void main(String[] args)throws Exception {
        GUI gui = new GUI();
    }

    //Uj ablak sikeres csatorna bontas esetén, szintén alapvető Swinges elemekkel.
 public void SUCCESS(){
     JFrame success=new JFrame("Sikeres folyamat");
     success.setSize(500,300);

     setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
     success.setLayout(new BorderLayout());
     ImageIcon successImage=new ImageIcon("scss.png") ;

     JButton button = new JButton(successImage);
     JLabel bal=new JLabel("SIKERES CSATORNA BONTAS");
     JLabel jobb=new JLabel("KATTINTS A KILEPESHEZ");


    success.add(bal,BorderLayout.WEST);
     success.add(jobb,BorderLayout.EAST);
     success.add(button,BorderLayout.CENTER);
     button.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
             System.exit(0);



 }});
     success.setLocation(500, 200);


 success.setVisible(true);
 }

//Ez a metódus az irányítója a fő folyamatoknak.

   public void Csatorna(String s)throws Exception{
       Fileinkezel fileinkezel = new Fileinkezel();
       Fajlbontas fajlbontas=new Fajlbontas();
       Fileoutkezel a = new Fileoutkezel();

       fajlnev=s;
       byte[] bajtwav = fileinkezel.Beolvas(fajlnev);
       a.fileir(fajlbontas.Balcsatorna(bajtwav), fajlbontas.Jobbcsatorna(bajtwav), fajlnev);

    }

    //Ez a elsodleges ablakunk. Alapvető Swinges beallitasokat es UI eszközöket tartalmat.
      GUI()throws Exception {


          super("Wav fajl bonto");
          setSize(300, 250);
          setLocation(500,200);


          JPanel panel = new JPanel();
          JLabel jtext = new JLabel("Kerem valasszon ki egy WAV fajlt");
          setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);


        panel.setLayout(new BorderLayout());
        panel.add(jtext, BorderLayout.NORTH);

        JButton button = new JButton("Tallozas");

          //Ezt mar lambdával is lehet csinalni, de a hosszabb verziót hagyom bent, mert elsőre így csináltam.
          //Gomb megnyomására felugrik egy fileChooser ami beépített Swinges eszköz, fajlt lehet kiválasztani vele.
          //Ha kiválasztottunk egyet továbbküldjük a nevét a Csatorna metódusnak ami végigpasszolja a gyártósoron(inkezel,bontas
          //es outkezel osztályoknak), Ha itt nem akad el a dolog(valahol kivételt dob)akkor jön a SUCCESS ablak.
        button.addActionListener(new ActionListener() {
                                     public void actionPerformed(ActionEvent e) {
                                         try{
                                         JFileChooser fileChooser = new JFileChooser();
                                             fileChooser.showOpenDialog(GUI.this);
                                             File selectedFile = fileChooser.getSelectedFile();
                                             fajlnev = selectedFile.getName();
                                             Csatorna(fajlnev);
                                             SUCCESS();

                                         }
                                             catch(Exception ae){}











                                     }
                                 });
          panel.add(button,BorderLayout.CENTER);


          add(panel);


            setVisible(true);


        }
    }
