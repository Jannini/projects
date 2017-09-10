


import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.EventListener;

public class Main extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception{
        Felhasznalo felh=new Felhasznalo();
        Aruhaz aruhaz=new Aruhaz();
        primaryStage.setResizable(false);
        Adatbazis adatbazis=new Adatbazis();

        VBox vbox=new VBox();
        Button reg=new Button("Regisztracio");
        reg.setMinWidth(86);
        reg.setMinHeight(75);
        Button bej=new Button("Bejelentkezes");
        bej.setMinWidth(75);
        bej.setMinHeight(75);
        Button regbejelent=new Button("Regisztracio");
        Button bej2=new Button("Bejelentkezes");
        vbox.setPadding(new Insets(10,10,10,10));
        vbox.getChildren().addAll(reg, bej);
        Scene main=new Scene(vbox,510,400);
        primaryStage.setScene(main);
        Button vissza=new Button("Vissza");
        felh.keresztnev="";
        felh.vezeteknev="";
        vissza.setOnAction(e->{
            primaryStage.setScene(main);
        });
        reg.setOnAction(e->{
            GridPane lay=new GridPane();
            lay.setPadding(new Insets(10,10,10,10));
            lay.setVgap(10);
            lay.setHgap(10);
            GridPane.setConstraints(felh.asd, 0, 0);
            GridPane.setConstraints(felh.text,1,0);
            GridPane.setConstraints(felh.asd1,0,1);
            GridPane.setConstraints(felh.text2,1,1);
            GridPane.setConstraints(felh.butt,1,2);
            GridPane.setConstraints(vissza,1,3);
            lay.getChildren().addAll(felh.asd,felh.text,felh.asd1,felh.text2,felh.butt,vissza);
            felh.asd.setMaxSize(300,50);
            felh.asd1.setMaxSize(300,50);
            felh.text2.setMaxSize(300,50);
            felh.text.setMaxSize(300,50);
            felh.butt.setMaxSize(300,50);
            primaryStage.setScene(new Scene(lay, 520, 400));

        });
        bej.setOnAction(e->{
            GridPane las=new GridPane();
            las.setPadding(new Insets(10,10,10,10));
            las.setVgap(10);
            las.setHgap(10);
            GridPane.setConstraints(felh.asd2,0,0);
            GridPane.setConstraints(felh.text3,1,0);
            GridPane.setConstraints(felh.asd3,0,1);
            GridPane.setConstraints(felh.text4,1,1);
            GridPane.setConstraints(vissza,1,3);
            GridPane.setConstraints(bej2,1,2);
            las.getChildren().addAll(felh.asd2,felh.text3,felh.asd3,felh.text4,vissza,bej2);
            felh.asd2.setMaxSize(300,50);
            felh.asd3.setMaxSize(300,50);
            felh.text3.setMaxSize(300,50);
            felh.text4.setMaxSize(300,50);
            bej2.setMaxSize(300,50);
            primaryStage.setScene(new Scene(las,520,400));

        });

        primaryStage.setTitle("Webaruhaz");


        felh.butt.setOnAction(e ->{
            try{
            felh.vezeteknev=felh.text.getText();
            felh.keresztnev=felh.text2.getText();
            aruhaz.Nevek(felh.vezeteknev,felh.keresztnev);
            GridPane la=new GridPane();
            la.setPadding(new Insets(10,10,10,10));
            la.setVgap(10);
            la.setHgap(10);
            GridPane.setConstraints(felh.asd2, 0, 0);
            GridPane.setConstraints(felh.text3,1,0);
            GridPane.setConstraints(felh.asd3,0,1);
            GridPane.setConstraints(felh.text4,1,1);
            GridPane.setConstraints(regbejelent,1,2);
            GridPane.setConstraints(vissza,1,3);
            la.getChildren().addAll(felh.asd2, felh.text3, felh.asd3, felh.text4,vissza,regbejelent);
            felh.asd2.setMaxSize(300,50);
            felh.asd3.setMaxSize(300,50);
            felh.text3.setMaxSize(300,50);
            felh.text4.setMaxSize(300,50);
            regbejelent.setMaxSize(300,50);

            primaryStage.setScene(new Scene(la,520,400));
                felh.text2.setText("");
            felh.text.setText("");
            }
            catch(Exception exc){}


        });

                bej2.setOnAction(e->{
                    try {
                        Adatbazis ujadatbazis=new Adatbazis();
                        String a = felh.text3.getText();
                        String b = felh.text4.getText();
                        String azon = a + b;
                        Scene felulet = (Scene) aruhaz.Felulet();
                        primaryStage.setScene(felulet);
                        aruhaz.Bejelentkezes(ujadatbazis.Azonositas(a,b), azon, primaryStage, main, vbox);
                        felh.text3.setText("");
                        felh.text4.setText("");
                    }
                    catch(Exception ek){}


                });

                regbejelent.setOnAction(e-> {
                    try{
                        String a=felh.text3.getText();
                        String b=felh.text4.getText();
                        String azon=a+b;
                        Scene felulet=(Scene)aruhaz.Felulet();
                    primaryStage.setScene(felulet);
                    aruhaz.Frissit(felh.regelj(aruhaz,primaryStage,azon,main,vbox),aruhaz.Beolvas());
                    adatbazis.SetBazis(felh.vezeteknev,felh.keresztnev,a,b);
                        felh.text3.setText("");
                        felh.text4.setText("");}
                    catch(Exception s){}

                });
                primaryStage.show();

    }



    public static void main(String[] args) {
        launch(args);
    }
}
