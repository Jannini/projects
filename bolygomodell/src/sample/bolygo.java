package sample;

/**
 * Created by Jani on 2017.04.07..
 */
public class bolygo { double r;
    double rx;
    double ry;
    double m;
    double s;
    double x;
    double y;
    bolygo(double r,double m){
        this.r=r;
        this.m=m;

    }
    void SetSpeed(double s){
        this.s=s;

    }
    void SetCoordinates(int x,int y){
        this.x=x;
        this.y=y;
        this.rx=r+x;
        this.ry=r+y;
    }
}


