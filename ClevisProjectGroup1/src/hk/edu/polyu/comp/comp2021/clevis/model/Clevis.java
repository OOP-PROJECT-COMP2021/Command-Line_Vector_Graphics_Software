package hk.edu.polyu.comp.comp2021.clevis.model;

import static hk.edu.polyu.comp.comp2021.clevis.model.Calculate.*;
import static java.lang.Math.*;



public class Clevis {
    public Clevis(){}
}

interface Shape{
    public static final float EPS = 1E-5f;
    String listInfo(); // list out information of a shape
    String getName(); // get the name
    boolean isIntersected(Line other); // check Shape is intersected with other Line
    boolean isIntersected(Rectangle other); // check Shape is intersected with other Rectangle(and its subclass Square)
    boolean isIntersected(Circle other); // check Shape is intersected with other Circle
    float getLeftBounding(); // get the Left Bounding of a shape
    float getRightBounding(); // get the Right Bounding of a shape
    float getTopBounding(); // get the Top Bounding of a shape
    float getBottomBounding(); // get the Bottom Bounding of a shape

}

/** Shape of Line*/
class Line implements Shape{

    private String name; // store name
    private Vec a; // store the vector of end A in the line
    private Vec b; // store the vector of end B in the line

    /** constructor */
    Line(String inName, float inX1, float inY1, float inX2, float inY2){
        name = inName; // initialize the name
        this.a = new Vec(inX1, inY1); // initialize the vector of end A in the line
        this.b = new Vec(inX2, inY2); // initialize the vector of end B in the line
    }

    public Vec getA() { // get the vector of end A in the line
        return a;
    }

    public Vec getB() { // get the vector of end B in the line
        return b;
    }

    public String getName(){ // get the name
        return name;
    }

    /** check Line is intersected with other Line */
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

    /** check Line is intersected with other Circle */
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

    /** check Line is intersected with other Rectangle(and its subclass Square) */
    public boolean isIntersected(Rectangle other){
        for (Line i : other.getFourLines()){
            if (this.isIntersected(i)){return true;}
        }
        return false;
    }

    public float getLeftBounding(){return Math.max(getA().getX(),getB().getX());}
    public float getRightBounding(){return Math.min(getA().getX(),getB().getX());}
    public float getTopBounding(){return Math.max(getA().getY(),getB().getY());}
    public float getBottomBounding(){return Math.min(getA().getY(),getB().getY());}

    /** list out information of a shape*/
    public String listInfo(){
        return "[Line]: \nname: "+getName()+"\nx1, y1: "+a.getX()+", "+a.getX()+"\nx2, y2: "+b.getX()+", "+b.getY();
    }

}

/** Shape of Rectangle*/
class Rectangle implements Shape{
    private String name; // store name
    private float w,h; // store the width and height
    private Line[] FourLines = new Line[4]; // LineArray to store 4 line of the rectangle (0: lineLeft, 1: lineBottom, 2: lineRight, 3: lineTop)

    /** constructor */
    Rectangle(){}

    Rectangle (String inName, float inX, float inY, float inW, float inH){
        name = inName;
        w = inW;
        h = inH;
        FourLines[0] = new Line(name+"LineLeft", inX,inY,inX,inY-inH);
        FourLines[1] = new Line(name+"LineBottom", inX,inY+inH,inX+inW,inY-inH);
        FourLines[2] = new Line(name+"LineRight",inX+inW,inY-inH,inX+inW,inY);
        FourLines[3] = new Line(name+"LineTop", inX+inW,inY,inX,inY);
    }

    public float getWidth(){return w;} // get the width

    public float getHeight(){return h;} // get the height

    public String getName(){return name;} // get the name

    public Line[] getFourLines(){ // get 4 Lines of the rectangle
        return FourLines;
    }

    public Line getWhichFourLines(int i){ // get which Line of the rectangle
        return getFourLines()[i];
    }

    public Vec getTopLeftCorner(){ // get the vector of the TopLeftCorner
        return getWhichFourLines(0).getA();
    }

    /** check Rectangle(and its subclass Square) is intersected with other Line */
    public boolean isIntersected(Line other){
        for (Line i: this.getFourLines()){
            if(i.isIntersected(other)){return true;}
        }
        return false;
    }

    /** check Rectangle(and its subclass Square) is intersected with other Circle */
    public boolean isIntersected(Circle other){
        for (Line i: this.getFourLines()){
            if(i.isIntersected(other)){return true;}
        }
        return false;
    }

    /** check Rectangle(and its subclass Square) is intersected with other Rectangle(and its subclass Square) */
    public boolean isIntersected(Rectangle other){
        for (Line i: this.getFourLines()){
            if(i.isIntersected(other)){return true;}
        }
        return false;
    }

    public float getLeftBounding(){return getWhichFourLines(0).getA().getX();}
    public float getRightBounding(){return getWhichFourLines(2).getA().getX();}
    public float getTopBounding(){return getWhichFourLines(3).getA().getY();}
    public float getBottomBounding(){return getWhichFourLines(1).getA().getY();}

    /** list out information of a shape*/
    public String listInfo(){
        return "[Rectangle]: \nname: "+getName()+"\ntop-left corner: "+getTopLeftCorner().getX()+", "+getTopLeftCorner().getX()+"\nWidth, Height: "+getWidth()+", "+getHeight();
    }

}

/** Square inherited the Shape of Rectangle*/
class Square extends Rectangle{

    /** constructor */
    Square (String inName ,float inX, float inY, float inL){
        super(inName, inX,inY, inL, inL);
    }

    /** list out information of a shape*/
    public String listInfo(){
        return "[Square]: \nname: "+getName()+"\ntop-left corner: "+getTopLeftCorner().getX()+", "+getTopLeftCorner().getX()+"\nside length: "+getWidth();
    }
}

/** Shape of Circle*/
class Circle implements Shape{
    private String name; // store name
    private float radius; // store radius
    private Vec center; // store the center vector

    /** constructor */
    Circle (String inName, float inX, float inY, float inR){
        name = inName;
        center = new Vec(inX, inY);
        radius = inR;
    }

    public Vec getCenter() { // get the center vector
        return center;
    }

    public float getRadius() { // get the radius
        return radius;
    }

    public String getName(){return name;} // get the name

    /** check Circle is intersected with other Line */
    public boolean isIntersected(Line other){
        if (other.isIntersected(this)){return true;}
        return false;
    }

    /** check Circle is intersected with other Rectangle(and its subclass Square) */
    public boolean isIntersected(Rectangle other){
        if (other.isIntersected(this)){return true;}
        return false;
    }

    /** check Circle is intersected with other Circle */
    public boolean isIntersected(Circle other){
        if (vectorSubtract(this.getCenter(),other.getCenter()).getDis() - Math.abs(this.getRadius()-other.getRadius()) > 0 + EPS
                && vectorSubtract(this.getCenter(),other.getCenter()).getDis() - this.getRadius() + other.getRadius() < 0 - EPS){return true;}
        return false;
    }

    public float getLeftBounding(){return getCenter().getX() - getRadius();}
    public float getRightBounding(){return getCenter().getX() + getRadius();}
    public float getTopBounding(){return getCenter().getY() + getRadius();}
    public float getBottomBounding(){return getCenter().getY() - getRadius();}

    /** list out information of a shape*/
    public String listInfo(){
        return "[Circle]: \nname: "+getName()+"\ncenter: "+getCenter().getX()+", "+getCenter().getX()+"\nradius: "+getRadius();
    }
}


/** create a Vec type for vector object*/
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
