package hk.edu.polyu.comp.comp2021.clevis.model;

import static java.lang.Math.abs;

/** create a Vec type for vector object*/
public class Vec {
    float x;
    float y;
    Vec (float a, float b) {
        x = a;
        y = b;
    }

    /** Gets a Vector's Magnitude.*/
    public float getDis() {
        return sqrtNewton(x * x + y * y);
    }

    public float getX(){
        return x;
    }
    public float getY(){
        return y;
    }

    public void setX(float inX){x = inX;}
    public void setY(float inY){y = inY;}

    private float sqrtNewton(float n) {
        final float D = 1E-7f;
        float x = 1;
        while (true) {
            float nx = (x + n / x) / 2;
            if (abs(x - nx) < D) break;
            x = nx;
        }
        return x;
    }
}
