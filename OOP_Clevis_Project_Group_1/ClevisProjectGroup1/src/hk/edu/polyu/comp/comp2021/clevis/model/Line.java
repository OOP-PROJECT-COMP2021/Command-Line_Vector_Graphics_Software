package hk.edu.polyu.comp.comp2021.clevis.model;

import static hk.edu.polyu.comp.comp2021.clevis.model.Calculate.*;
import static java.lang.Math.*;

/** Shape of Line*/
public class Line implements Shape{

    private final String name; // store name
    private Vec a; // store the vector of end A in the line
    private Vec b; // store the vector of end B in the line

    /** LinkedListDeque methods*/
    private Shape parent = this;
    private Shape left;
    private Shape right;

    @Override
    public String getSHAPE_TYPE() {
        return "LINE";
    }

    /** constructor
     * @param inName: Line name
     * @param inX1: x1
     * @param inY1: y1
     * @param inX2: x2
     * @param inY2: y2 */
    Line(String inName, double inX1, double inY1, double inX2, double inY2) {
        name = inName; // initialize the name
        this.a = new Vec(inX1, inY1); // initialize the vector of end A in the line
        this.b = new Vec(inX2, inY2); // initialize the vector of end B in the line
    }
    /** @return a */
    public Vec getA() { // get the vector of end A in the line
        return a;
    } // method for get the A end

    /** @return b */
    public Vec getB() { // get the vector of end B in the line
        return b;
    } // method for get the B end

    /** LinkedListDeque methods*/
    @Override
    public Shape getParent() { return parent; }
    @Override
    public void setParent(Shape father) {
        // try catch needed
        if (!(father instanceof Group)) return;
        parent = father;
    }

    @Override
    public Shape getAncestor() {
        Shape ptr = this;
        while (!ptr.getName().equals(ptr.getParent().getName())) ptr = ptr.getParent();
        return ptr;
    }
    @Override
    public Shape getLeft() { return left; }

    @Override
    public Shape getRight() { return right; }

    @Override
    public void setLeft(Shape l) { left = l; }

    @Override
    public void setRight(Shape r) { right = r; }

    @Override
    public void removeRefer() {
        getLeft().setRight(getRight());
        getRight().setLeft(getLeft());
        //parent = this;
    }

    @Override
    public void pointToMe() { this.parent = this; }

    /** method for get the name */
    @Override
    public String getName(){ // get the name
        return name;
    }

    @Override
    public boolean isIntersected(Shape other) {

        if (other.getSHAPE_TYPE().equals("LINE")) {
            return Intersected((Line) other);
        }
        if (other.getSHAPE_TYPE().equals("REC")) {
            return Intersected((Rectangle) other);
        }
        if (other.getSHAPE_TYPE().equals("CIR")) {
            return Intersected((Circle) other);
        }
        if (other.getSHAPE_TYPE().equals("GRP")) {
            return Intersected((Group) other);
        }
        return false;
    }

    /** check Line is intersected with other Line
     * @param other:other
     * @return is intersected or not */
    public boolean Intersected(Line other) {
        // rapid exclusion
        if (max(a.getX(), b.getX()) < min(other.getA().getX(), other.getB().getX()) || max(a.getY(), b.getY()) < min(other.getA().getY(), other.getB().getY())
                || max(other.getA().getX(), other.getB().getX()) < min(a.getX(), b.getX()) || max(other.getA().getY(), other.getB().getY()) < min(a.getY(), b.getY())) {
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

    /** check Line is intersected with other Circle
     * @param c:c
     * @return is intersected or not */
    public boolean Intersected(Circle c) {
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

        // case 4: if innerproduct(bo, ba) < 0 or innerproduct(ao, ab) < 0, then not intersect1
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

    /** check Line is intersected with other Rectangle(and its subclass Square)
     * @param other:other
     * @return is intersected or not */
    public boolean Intersected(Rectangle other){
        for (Line i : other.getFourLines()){
            if (this.isIntersected(i)){return true;}
        }
        return false;
    }

    /** check Line is intersected with other Group
     * @param other:other
     * @return is intersected or not */
    public boolean Intersected(Group other){
        return other.isIntersected(this);
    }

    /** move a Line method*/
    @Override
    public void move(double inDx,double inDy){
        this.getA().setX(this.getA().getX()+inDx); // move end-A's x by inDx
        this.getA().setY(this.getA().getY()+inDy); // move end-A's y by inDy

        this.getB().setX(this.getB().getX()+inDx); // move end-B's x by inDx
        this.getB().setY(this.getB().getY()+inDy); // move end-B's y by inDy
    }


    /** bounding box method */
    @Override
    public double getLeftBounding(){return Math.min(getA().getX(),getB().getX());}
    @Override
    public double getRightBounding(){return Math.max(getA().getX(),getB().getX());}
    @Override
    public double getTopBounding(){return Math.min(getA().getY(),getB().getY());}
    @Override
    public double getBottomBounding(){return Math.max(getA().getY(),getB().getY());}

    /** list out information of a shape*/
    @Override
    public String listInfo(){
        return "[Line] Name:"+getName()+"; x1, y1:"+"("+ String.format("%.2f", a.getX())+","+
                String.format("%.2f", a.getY())+")"+"; x2, y2:"+
                "("+String.format("%.2f", b.getX())+","+
                String.format("%.2f", b.getY())+")";
    }
}
