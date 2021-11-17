package hk.edu.polyu.comp.comp2021.clevis.model;

import static hk.edu.polyu.comp.comp2021.clevis.model.Calculate.vectorSubtract;

/** Shape of Circle*/
public class Circle implements Shape{
    private final String name; // store name
    private double radius; // store radius
    private Vec center; // store the center vector

    /** LinkedListDeque methods*/
    private Shape parent = this; // parent is itself
    private Shape left;
    private Shape right;

    /** constructor
     * @param inName: the name of circle
     * @param inX: the x of the circle
     * @param inY: the y of the circle
     * @param inR: the radius of the circle */
    Circle (String inName, double inX, double inY, double inR){
        name = inName;
        center = new Vec(inX, inY);
        radius = inR;
    }

    @Override
    public String getSHAPE_TYPE() {
        return "CIR";
    }

    /** @return center */
    public Vec getCenter() { // get the center vector
        return center;
    } // method for get the center

    /** @return radius*/
    public double getRadius() { // get the radius
        return radius;
    } // method for get the radius

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

    /** Removes the left reference and right reference. */
    @Override
    public void removeRefer() {
        getLeft().setRight(getRight());
        getRight().setLeft(getLeft());
    }

    @Override
    public void pointToMe() {
        this.parent = this;
    }

    /** method for get the name */
    @Override
    public String getName(){return name;} // get the name

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

    /** check Circle is intersected with other Line
     * @param other:other
     * @return is intersected or not */
    public boolean Intersected(Line other){
        return other.isIntersected(this);
    }

    /** check Circle is intersected with other Rectangle(and its subclass Square)
     * @param other:other
     * @return is intersected or not */
    public boolean Intersected(Rectangle other){
        return other.isIntersected(this);
    }

    /** check Circle is intersected with other Circle
     * @param other:other
     * @return is intersected or not */
    public boolean Intersected(Circle other){
        double d = vectorSubtract(this.getCenter(),other.getCenter()).getDis();
        double RAddr = this.getRadius() + other.getRadius();
        double RSubr = Math.abs(this.getRadius() - other.getRadius());

        if (d - RSubr > 0 - EPS && d - RAddr < 0 + EPS) {return true;}
        return false;
    }

    /** check Line is intersected with other Group
     * @param other:other
     * @return is intersected or not */
    public boolean Intersected(Group other){
        return other.isIntersected(this);
    }

    /** move a Circle method*/
    @Override
    public void move(double inDx,double inDy){
        this.getCenter().setX(this.getCenter().getX()+inDx); // move Center's x by inDx
        this.getCenter().setY(this.getCenter().getY()+inDy); // move Center's y by inDy
    }

    /** bounding box method */
    @Override
    public double getLeftBounding(){return getCenter().getX() - getRadius();}
    @Override
    public double getRightBounding(){return getCenter().getX() + getRadius();}
    @Override
    public double getTopBounding(){return getCenter().getY() - getRadius();}
    @Override
    public double getBottomBounding(){return getCenter().getY() + getRadius();}

    /** list out information of a shape*/
    @Override
    public String listInfo(){
        return "[Circle] Name:"+getName()+"; Center:"+"("+String.format("%.2f", getCenter().getX())+","+
                String.format("%.2f", getCenter().getY())+")"+"; Radius:"+String.format("%.2f", getRadius());
    }
}