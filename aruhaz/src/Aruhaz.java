
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.awt.*;
import java.awt.Button;
import java.awt.TextField;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;
import com.mysql.jdbc.Driver;

/**
 * Created by Jani on 2017.03.17..
 */
public class Aruhaz {
    int egyenleg=0;
    String azonosito;
    Label egyenlegLabel=new Label("Egyenleged: "+egyenleg);
    Label sikeresVasar=new Label("");
    String osszvasarlas="";

    Adatbazis adat=new Adatbazis();

    {try{
        adat.GetBazis();

    }
    catch(Exception ex){
        System.out.println(ex.toString());
    }
    }


    ArrayList<String> Beolvas()throws Exception {
        ArrayList<String> tarolo = new ArrayList<String>();
        FileReader read=new FileReader("tarolo.txt");
        Scanner scan = new Scanner(read);
        while (scan.hasNextLine()) {
            tarolo.add(scan.nextLine());
        }
        scan.close();
        return tarolo;

    }
    void Nevek(String v,String k)throws Exception{
        FileWriter neviro=new FileWriter("regnevek.txt", true);
        neviro.write(v+k);
        neviro.write("\n");
        neviro.close();

    }





        ArrayList<String> Frissit(String azonosito, ArrayList<String> tarolo)throws Exception{
        tarolo=Beolvas();
        tarolo.add(azonosito);
            this.azonosito=azonosito;
        FileWriter tarolj=new FileWriter("tarolo.txt");
        int meret = tarolo.size();
        for (int i=0;i<meret;i++) {
            String string = tarolo.get(i);
            tarolj.write(string);
            tarolj.write("\n");

        }
        tarolj.close();
        return tarolo;

    }

    void Bejelentkezes(int a,String s,Stage sz,Scene sc,VBox v) {
        Label hiba=new Label("Hibas azonosito");
        this.azonosito=s;
        if(a==1){




        }
        else{
        v.getChildren().add(hiba);
        sz.setScene(sc);}

    }
    Object Felulet()throws Exception{
        GridPane bp=new GridPane();
        bp.setHgap(10);
        bp.setVgap(10);
        TableView<Product> tableView=new TableView<>();
        TableColumn<Product,String> column1=new TableColumn<>("Termeknev");
        column1.setCellValueFactory(new PropertyValueFactory<>("nev"));
        column1.setMinWidth(145);
        TableColumn<Product,Double> column2=new TableColumn<>("Ar");
        column2.setCellValueFactory(new PropertyValueFactory<>("ar"));
        column2.setMinWidth(145);
        TableColumn<Product,String> column3=new TableColumn<>("Elerheto");
        column3.setCellValueFactory(new PropertyValueFactory<>("van"));
        column3.setMinWidth(145);
        TableColumn<Product,CheckBox> column4=new TableColumn<>("Kivalasztas");
        column4.setCellValueFactory(new PropertyValueFactory<>("c"));
        column4.setMinWidth(45);
        tableView.setMaxHeight(200);
        tableView.setItems(getprod());
        tableView.getColumns().addAll(column1,column2,column3,column4);
        javafx.scene.control.TextField egyenlegT=new javafx.scene.control.TextField();
        javafx.scene.control.Button feltolt=new javafx.scene.control.Button("Feltoltes");
        egyenlegT.setMaxWidth(55);
        HBox hbox=new HBox();
        hbox.getChildren().addAll(egyenlegT,sikeresVasar);
        hbox.setSpacing(222);




        javafx.scene.control.Button vasarlas=new javafx.scene.control.Button("Kijelolt targyak vasarlasa");
        vasarlas.setOnAction(e->{try{
                    kijeloltVasar(CheckboxOsszes());
                }
                catch(Exception exc){System.out.println(exc.toString());}
                }
        );
        feltolt.setOnAction(e->{
            egyenleg(egyenlegT.getText());
            egyenlegLabel.setText("Egyenleged: "+egyenleg);
            System.out.println(egyenleg);

        });
        GridPane.setConstraints(vasarlas,0,5);
        GridPane.setConstraints(hbox,0,6);
        GridPane.setConstraints(feltolt,0,7);
        GridPane.setConstraints(egyenlegLabel,0,8);





        bp.getChildren().addAll(tableView,vasarlas,hbox,egyenlegLabel,feltolt);

        Scene scene=new Scene(bp,520,400);
        return scene;
    }
    ObservableList<Product> getprod()throws Exception{
        ObservableList<Product> prod= FXCollections.observableArrayList();
        for(int i=0;i<adat.rowLength;i++) {
            prod.add(adat.products[i]);

        }
        return prod;
    }
    CheckBox[] CheckboxOsszes(){
        CheckBox[] checkB=new CheckBox[adat.rowLength];
        for(int i=0;i<adat.rowLength;i++){
            checkB[i]=adat.products[i].getC();
        }
        return checkB;
    }
    void kijeloltVasar(CheckBox[] boxArray)throws Exception{
        double osszeg=0;
        for(int i=0;i<adat.rowLength;i++) {
            if (boxArray[i].isSelected()) {
                osszeg += adat.products[i].getAr();
                osszvasarlas += adat.products[i].getNev() + " ";
            }
        }
        if(egyenleg>=osszeg){
            VasarKiir(osszvasarlas);
            egyenleg=egyenleg-(int)osszeg;
            egyenlegLabel.setText("Egyenleged: "+egyenleg);
            System.out.println("SIkeres vasarlas");
            sikeresVasar.setText("Sikeres vasarlas");

        }
        else{sikeresVasar.setText("Nincs eleg penzed");}
    }
       void egyenleg(String st){
            egyenleg+=Integer.parseInt(st);


    }
    void VasarKiir(String string)throws Exception{
        FileWriter filew=new FileWriter("vevolista.txt",true);
        filew.write(azonosito+"\t");
        filew.write(string);
        filew.write("\n");
        filew.close();
    }
    }



