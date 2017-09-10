package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("Bolygoszimulacio");
        primaryStage.setResizable(false);
        VBox sp=new VBox(10);
        primaryStage.setScene(new Scene(sp, 420, 520));
        BackgroundImage hatter=new BackgroundImage(new Image("/sample/maxresdefault.jpg"), BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);

        Label tav=new Label("Ird be az elso bolygo tavolsagat a Naptol(-650 es +650 kozotti egesz szamot).\nA minusz jelkepezi hogy balrol induljon a szimulacio jobb helyett.");
        Label tav2=new Label("Ird be a masodik bolygo tavolsagat a Naptol");
        Label nev1=new Label("Ird be az elso bolygo nevet");
        Label nev2=new Label("Ird be a masodik bolygo nevet");
        Label m1=new Label("Ird be a harmadik bolygo nevet");
        Label m2=new Label("Ird be a harmadik bolygo tavolsagat");
        //Label r1=new Label("Ird be az elso bolygo sugarat");
        //Label r2=new Label("Ird be a masodik bolygo sugarat");
        Label hossz=new Label("Ird be a lefutott ciklusok szamat. MAX 12 ciklus\nEgy ciklus ideje a Mars altal megtett egy ciklus ideje");
        tav.setTextFill(Color.WHITE);
        tav2.setTextFill(Color.WHITE);
        nev1.setTextFill(Color.WHITE);
        nev2.setTextFill(Color.WHITE);
        hossz.setTextFill(Color.WHITE);
        m1.setTextFill(Color.WHITE);
        m2.setTextFill(Color.WHITE);


        TextField tav1=new TextField();
        TextField hossz1=new TextField();
        TextField tav21=new TextField();
        TextField nevt1=new TextField();
        TextField nevt2=new TextField();
        TextField mt1=new TextField();
        TextField mt2=new TextField();
        TextField rt1=new TextField();
        TextField rt2=new TextField();
        tav1.setMaxWidth(255);
        hossz1.setMaxWidth(255);
        tav21.setMaxWidth(255);
        nevt1.setMaxWidth(255);
        nevt2.setMaxWidth(255);
        mt1.setMaxWidth(255);
        mt2.setMaxWidth(255);

        Button okes=new Button("Szimulacio kezdese");
        sp.getChildren().addAll(tav,tav1,nev1,nevt1,tav2,tav21,nev2,nevt2,m2,mt2,m1,mt1,hossz,hossz1,okes);
        okes.setOnAction(e -> {
            Paint paint = new Paint();
            paint.Rajz(tav1.getText(), tav21.getText(), hossz1.getText(), nevt1.getText(), nevt2.getText(), mt1.getText(), mt2.getText(), rt1.getText(), rt2.getText());

        });

        sp.setBackground(new Background(hatter));

        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
