import javafx.scene.control.CheckBox;

/**
 * Created by Jani on 2017.04.09..
 */
public class Product {
    private String nev;
    private double ar;
    private String van;
    private CheckBox c;
    Product(String s,double d,String v,CheckBox c){
        nev=s;
        ar=d;
        van=v;
        this.c=c;
    }
    public String getNev() {
        return nev;
    }

    public void setNev(String nev) {
        this.nev = nev;
    }



    public double getAr() {
        return ar;
    }

    public void setAr(double ar) {
        this.ar = ar;
    }
    public String getVan() {
        return van;
    }

    public void setVan(String van) {
        this.van = van;
    }

    public CheckBox getC() {
        return c;
    }

    public void setC(CheckBox c) {
        this.c = c;
    }

    public String toString(){
        return " "+nev+" "+ar;
    }
}
