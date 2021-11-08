package hk.edu.polyu.comp.comp2021.clevis.model;

import static java.lang.Math.abs;

/** create a Vec type for vector object*/
public class Vec {
    private double x;
    private double y;
    Vec (double a, double b) {
        x = a;
        y = b;
    }

    /** Gets a Vector's Magnitude.*/
    public double getDis() {
        return Math.sqrt(x * x + y * y);
    }

    public double getX(){
        return x;
    }
    public double getY(){
        return y;
    }

    public void setX(double inX){x = inX;}
    public void setY(double inY){y = inY;}
}
