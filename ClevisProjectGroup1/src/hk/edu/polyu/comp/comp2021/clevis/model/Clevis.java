package hk.edu.polyu.comp.comp2021.clevis.model;

import static hk.edu.polyu.comp.comp2021.clevis.model.Calculate.*;
import static java.lang.Math.*;



public class Clevis {
    public Clevis(){}
}

interface Shape{
    public static final float EPS = 1E-5f;
}

class Line implements Shape{
    private String name;
    private Vec a;
    private Vec b;

    // constructor
    Line(String inName, float inX1, float inY1, float inX2, float inY2){
        name = inName;
        this.a = new Vec(inX1, inY1);
        this.b = new Vec(inX2, inY2);
    }

    public Vec getA() {
        return a;
    }

    public Vec getB() {
        return b;
    }

    public boolean isIntersected(Line other) {
        // rapid exclusion
        if (max(a.x, b.x) < min(other.getA().x, other.getB().x) || max(a.y, b.y) < min(other.getA().y, other.getB().y)
                || max(other.getA().x, other.getB().x) < min(a.x, b.x) || max(other.getA().y, other.getB().y) < min(a.y, b.y)) {
            return false;
        }
        if (outerProduct(vectorSubtract(b, a), vectorSubtract(other.getA(),a))
                * outerProduct(vectorSubtract(b, a), vectorSubtract(other.getB(), a)) > 0 + EPS ||
                outerProduct(vectorSubtract(other.getB(), other.getA()), vectorSubtract(a, other.getA()))
                        * outerProduct(vectorSubtract(other.getB(), other.getA()), vectorSubtract(b, other.getA())) > 0 + EPS) {
            return false;
        }
        return true;
    }

    public boolean isIntersected(Circle c) {
        // case 1: if l_oa = r or l_ob = r, then intersect
        if (abs(vectorSubtract(a, c.getCenter()).getDis() - c.getRadius()) < EPS
                || abs(vectorSubtract(b, c.getCenter()).getDis() - c.getRadius()) < EPS) {
            return true;
        }

        // case 2: if l_oa < r and l_ob < r, then not intersect
        if (vectorSubtract(a, c.getCenter()).getDis() - c.getRadius() < 0 - EPS
                && vectorSubtract(b, c.getCenter()).getDis() - c.getRadius() < 0 - EPS) { // we see numeric very close to 0 as 0
            return false;
        }

        // case 3: if l_oa > r and l_ob < r, then intersect
        if (
                ((vectorSubtract(a, c.getCenter()).getDis() - c.getRadius() > 0 + EPS)
                        && (vectorSubtract(b, c.getCenter()).getDis() - c.getRadius() < 0 - EPS))
                        ||
                        ((vectorSubtract(a, c.getCenter()).getDis() - c.getRadius() < 0 - EPS)
                                && (vectorSubtract(b, c.getCenter()).getDis() - c.getRadius() > 0 + EPS))
        ) {
            return true;
        }

        // case 4: if innerproduct(bo, ba) < 0 or innerproduct(ao, ab) < 0, then not intersect
        if (innerProduct(vectorSubtract(c.getCenter(), b), vectorSubtract(a, b)) < 0 - EPS
                || innerProduct(vectorSubtract(c.getCenter(), a), vectorSubtract(b, a)) < 0 - EPS) { // 可以排除OCE共线且CE在圆外的情况.
            return false;
        }

        // if outerproduct(oa, ob) - (|ab| * r) <= 0, then intersect
        if (abs(outerProduct(vectorSubtract(a, c.getCenter()), vectorSubtract(b, c.getCenter())))
                - (vectorSubtract(a, b).getDis() * c.getRadius()) < 0 + EPS)  { // <= 0
            return true;
        } else {
            return false;
        }
    }

}

class Rectangle implements Shape{
    private String name;
    private float w,h;
    private Line lineLeft,lineBottom,lineRight,lineTop;

    Rectangle (String inName, float inX, float inY, float inW, float inH){
        name = inName;
        w = inW;
        h = inH;
        lineLeft = new Line(name+"LineLeft", inX,inY,inX,inY-inH);
        lineBottom = new Line(name+"LineBottom", inX,inY+inH,inX+inW,inY-inH);
        lineRight = new Line(name+"LineRight",inX+inW,inY-inH,inX+inW,inY);
        lineTop = new Line(name+"LineTop", inX+inW,inY,inX,inY);
    }

    public float getWidth(){return w;}
    public float getHeight(){return h;}

}

class Square extends Rectangle{
    Square (String inName ,float inX, float inY, float inL){
        super(inName, inX,inY, inL, inL);
    }
}

class Circle implements Shape{
    private String name;
    private float radius;
    private Vec center;

    Circle (String inName, float inX, float inY, float inR){
        name = inName;
        center = new Vec(inX, inY);
        radius = inR;
    }

    public Vec getCenter() {
        return center;
    }

    public float getRadius() {
        return radius;
    }



}
class Vec {
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
