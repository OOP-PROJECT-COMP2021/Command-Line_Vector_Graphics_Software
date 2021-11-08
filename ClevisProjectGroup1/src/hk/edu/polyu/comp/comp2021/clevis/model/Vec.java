package hk.edu.polyu.comp.comp2021.clevis.model;

/** create a Vec type for vector object*/
public class Vec {
    private double x;
    private double y;
    /** vector
     * @param a:double a
     * @param b:double b*/
    Vec (double a, double b) {
        x = a;
        y = b;
    }

    /** Gets a Vector's Magnitude.
     * @return distance*/
    public double getDis() {
        return Math.sqrt(x * x + y * y);
    }
    /** @return x */
    public double getX(){return x;}
    /** @return y */
    public double getY(){return y;}
    /** set x
     * @param inX:x*/
    public void setX(double inX){x = inX;}
    /** set y
     * @param inY:y*/
    public void setY(double inY){y = inY;}
}
