package hk.edu.polyu.comp.comp2021.clevis.model;

import static java.lang.Math.abs;

public class Clevis {
    public Clevis(){}
}

interface Shape{
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
