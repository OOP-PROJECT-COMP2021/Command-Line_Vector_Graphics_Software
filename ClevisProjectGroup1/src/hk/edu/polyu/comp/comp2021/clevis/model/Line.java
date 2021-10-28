package hk.edu.polyu.comp.comp2021.clevis.model;

import static hk.edu.polyu.comp.comp2021.clevis.model.Calculate.*;
import static hk.edu.polyu.comp.comp2021.clevis.model.Calculate.vectorSubtract;
import static java.lang.Math.*;
import static java.lang.Math.abs;

/** Shape of Line*/
class Line implements Shape{

    private final String name; // store name
    private Vec a; // store the vector of end A in the line
    private Vec b; // store the vector of end B in the line

    /** constructor */
    Line(String inName, double inX1, double inY1, double inX2, double inY2){
        name = inName; // initialize the name
        this.a = new Vec(inX1, inY1); // initialize the vector of end A in the line
        this.b = new Vec(inX2, inY2); // initialize the vector of end B in the line
    }

    public Vec getA() { // get the vector of end A in the line
        return a;
    } // method for get the A end
    public Vec getB() { // get the vector of end B in the line
        return b;
    } // method for get the B end

    /** method for get the name */
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

    /** check Line is intersected with other Group*/
    public boolean isIntersected(Group other){
        return other.isIntersected(this);
    }

    /** move a Line method*/
    public void move(double inDx,double inDy){
        this.getA().setX(this.getA().getX()+inDx); // move end-A's x by inDx
        this.getA().setY(this.getA().getY()+inDy); // move end-A's y by inDy

        this.getB().setX(this.getB().getX()+inDx); // move end-B's x by inDx
        this.getB().setY(this.getB().getY()+inDy); // move end-B's y by inDy
    }


    /** bounding box method */
    public double getLeftBounding(){return Math.min(getA().getX(),getB().getX());}
    public double getRightBounding(){return Math.max(getA().getX(),getB().getX());}
    public double getTopBounding(){return Math.max(getA().getY(),getB().getY());}
    public double getBottomBounding(){return Math.min(getA().getY(),getB().getY());}

    /** list out information of a shape*/
    public String listInfo(){
        return "[Line]: Name:"+getName()+"; x1, y1:"+a.getX()+","+a.getX()+"; x2, y2:"+b.getX()+","+b.getY();
    }

}
