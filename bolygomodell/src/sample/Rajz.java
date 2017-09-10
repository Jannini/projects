package sample;

import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Jani on 2017.04.07..
 */
public class Rajz{

double[] x;
double[] y;
double[] x1;
double[] y1;
double xlocmars;
double ylocmars;
double xlocfold;
double ylocfold;

    Rajz(double[] x,double[] y,double[] x1,double[] y1,double xlocmars,double ylocmars,double xlocfold,double ylocfold){

        this.x=x;
        this.y=y;
        this.x1=x1;
        this.y1=y1;
        this.xlocmars=xlocmars;
        this.ylocmars=ylocmars;
        this.xlocfold=xlocfold;
        this.ylocfold=ylocfold;
        Canvas waz=new Canvas();



    }


    //Rajz rajz=new Rajz(osszx, osszy, osszx1, osszy1,  mars.x,  mars.y,  fold.x,  fold.y);

   public void paint(GraphicsContext g) {
            g.translate(700,377);
            g.strokeText("   MARS", xlocmars, ylocmars);
            g.strokeText("   FOLD", xlocfold, ylocfold);
            g.strokePolyline(x, y, x.length);
            g.strokePolyline(x1, y1, x1.length);
        }
}
