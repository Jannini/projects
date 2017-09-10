import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by Jani on 2017.03.17..
 */
public class Felhasznalo extends Main {
    String azonosito;
    String vezeteknev;
    String keresztnev;
    Label asd=new Label("Ird be a vezetekneved");
    TextArea text=new TextArea();
    Label asd1=new Label("Ird be a keresztneved");
    TextArea text2=new TextArea();
    Label asd2=new Label("Ird be a felhasznalo neved");
    TextArea text3=new TextArea();
    Label asd3=new Label("Ird be a jelszod");
    TextArea text4=new TextArea();
    Button butt=new Button("Okes");
    Label hiba=new Label("Foglalt azonosito");



    String regelj(Aruhaz a,Stage s,String st,Scene sc,VBox v)throws Exception{
        azonosito=st;
        Adatbazis adatbazis=new Adatbazis();
        ArrayList<String> lista = new ArrayList<String>(adatbazis.GetAzonosito());
        if (lista.contains(azonosito)){
            hiba.setMaxSize(300,50);
       v.getChildren().add(hiba);
            s.setScene(sc);
            return "";

        }

            return azonosito;}


    }



