package sample;

import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.*;
import javafx.scene.layout.*;
import javafx.scene.paint.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.canvas.Canvas;


import java.awt.*;

public class Paint {
    Stage window = new Stage();

    public void Rajz(String ta1, String tav22, String hossz2, String nev1, String nev2,String m1,String m2,String r1, String r2) {
        window.setTitle("Bolygomodellezo");
        HBox asd=new HBox();
        Scene scene=new Scene(asd,1080,720);
        BackgroundImage hatter=new BackgroundImage(new javafx.scene.image.Image("/sample/maxresdefault.jpg"), BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        asd.setBackground(new Background(hatter));
        window.setScene(scene);
        window.show();
        bolygo BolygoEgy = new bolygo(10, 50);
        bolygo BolygoKetto = new bolygo(10, 50);
        bolygo BolygoHarom=new bolygo(10,50);
        double hanyados = BolygoEgy.m / BolygoKetto.m;
        double RocheLim = 2.44 * BolygoKetto.r * (Math.pow(hanyados, 0.33333));

        BolygoEgy.SetCoordinates(Integer.parseInt(ta1), (Integer.parseInt(ta1)) / 2);
        BolygoKetto.SetCoordinates(Integer.parseInt(tav22), (Integer.parseInt(tav22)) / 2);
        BolygoHarom.SetCoordinates(Integer.parseInt(m2), (Integer.parseInt(m2)) / 2);

        BolygoEgy.SetSpeed(Math.pow(Math.abs(Integer.parseInt(ta1)), 1.5)/1397);
        BolygoKetto.SetSpeed(Math.pow(Math.abs(Integer.parseInt(tav22)), 1.5)/1397);
        BolygoHarom.SetSpeed(Math.pow(Math.abs(Integer.parseInt(m2)), 1.5)/1397);

        double angle = 0;
        double xtav;
        double ytav;
        double xtav1;
        double ytav1;
        double xtav2;
        double ytav2;
        int i = -1;


        System.out.println("Roche Limit: " + RocheLim);


        double[] BolygoEgyX = new double[7888];
        double[] BolygoEgyY = new double[7888];
        double[] BolygoKettoX = new double[7888];
        double[] BolygoKettoY = new double[7888];
        double[] BolygoHaromX = new double[7888];
        double[] BolygoHaromY = new double[7888];

        final Canvas canvas = new Canvas(1600,1080);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setStroke(Color.WHITE);
        gc.translate(700, 377);

        //while (angle < Double.parseDouble(hossz2) * (2 * 3.2))
        for(i=0;angle < Double.parseDouble(hossz2) * (2 * 3.2);i++){


           // i = i + 1;
            angle = angle + 0.01;
            BolygoKetto.x = BolygoKetto.rx * Math.cos(angle / BolygoKetto.s);
            BolygoKetto.y = BolygoKetto.ry * Math.sin(angle / BolygoKetto.s);
            BolygoEgy.x = BolygoEgy.rx * Math.cos(angle / BolygoEgy.s);
            BolygoEgy.y = BolygoEgy.ry * Math.sin(angle / BolygoEgy.s);
            BolygoHarom.x = BolygoHarom.rx * Math.cos(angle / BolygoHarom.s);
            BolygoHarom.y = BolygoHarom.ry * Math.sin(angle / BolygoHarom.s);
            //System.out.println(BolygoKetto.x);
            //System.out.println(BolygoKetto.y);
            xtav = BolygoKetto.x - BolygoEgy.x;
            ytav = BolygoKetto.y - BolygoEgy.y;
            xtav1 = BolygoHarom.x - BolygoEgy.x;
            ytav1 = BolygoHarom.y - BolygoEgy.y;
            xtav2 = BolygoHarom.x - BolygoKetto.x;
            ytav2 = BolygoHarom.y - BolygoKetto.y;

            BolygoEgyX[i] =  BolygoKetto.x;
            BolygoEgyY[i] =  BolygoKetto.y;
            BolygoKettoX[i] = BolygoEgy.x;
            BolygoKettoY[i] =  BolygoEgy.y;
            BolygoHaromX[i] = BolygoHarom.x;
            BolygoHaromY[i] =  BolygoHarom.y;


            if (angle >= 6.3 || (Math.abs(xtav) < RocheLim && Math.abs(ytav) < RocheLim) || (Math.abs(xtav1) < RocheLim && Math.abs(ytav1) < RocheLim) || (Math.abs(xtav2) < RocheLim && Math.abs(ytav2) < RocheLim)) {
                if (Math.abs(xtav) < RocheLim && Math.abs(ytav) < RocheLim) {
                   // System.out.println("Utkozes jujujjuju!!!!");
                    //System.out.println("Mars x koordinataja:" + mars.x);
                    //System.out.println("Mars y koordinataja:" + mars.y);
                    //System.out.println("Fold x koordinataja:" + fold.x);
                    //System.out.println("Fold y koordinataja:" + fold.y);
                    gc.strokeText("Utkozes!!!!",-600,-300);
                    gc.strokeText(nev1 +" x koordinataja:" + BolygoKetto.x,-600,-280);
                    gc.strokeText(nev1 + " y koordinataja:" + BolygoKetto.y,-600,-260);
                    gc.strokeText(nev2 +" x koordinataja:" + BolygoEgy.x,-600,-240);
                    gc.strokeText(nev2 + " y koordinataja:" + BolygoEgy.y,-600,-220);
                    gc.strokeText(m1 +" x koordinataja:" + BolygoHarom.x,-600,-200);
                    gc.strokeText(m1 + " y koordinataja:" + BolygoHarom.y,-600,-180);
                    break;
                }
                if (angle >= Double.parseDouble(hossz2) *6.3) {
                    //System.out.println("Lejart a szimulacio!!!!");
                    gc.strokeText("Nincs utkozes",-600,-220);
                    break;
                }

            }


        }

        gc.strokeText("   "+nev1,BolygoEgy.x,BolygoEgy.y);
        gc.strokeText("   " + nev2, BolygoKetto.x, BolygoKetto.y);
        gc.strokeText("   " + m1, BolygoHarom.x, BolygoHarom.y);
        gc.strokePolygon(BolygoEgyX, BolygoEgyY, BolygoEgyX.length);
        gc.strokePolyline(BolygoKettoX, BolygoKettoY, BolygoKettoX.length);
        gc.strokePolyline(BolygoHaromX, BolygoHaromY, BolygoHaromX.length);
        asd.getChildren().add(canvas);


    }



    }




