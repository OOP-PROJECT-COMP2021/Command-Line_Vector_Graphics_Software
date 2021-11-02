package hk.edu.polyu.comp.comp2021.clevis.model;

import static hk.edu.polyu.comp.comp2021.clevis.model.Calculate.vectorSubtract;

/** Shape of Circle*/
class Circle implements Shape{
    private final String name; // store name
    private double radius; // store radius
    private Vec center; // store the center vector
    private int groupState = 0;

    /** LinkedListDeque methods*/
    private Shape parent = this; // parent is itself
    private Shape left;
    private Shape right;

    /** constructor */
    Circle (String inName, double inX, double inY, double inR){
        name = inName;
        center = new Vec(inX, inY);
        radius = inR;
    }

    public Vec getCenter() { // get the center vector
        return center;
    } // method for get the center
    public double getRadius() { // get the radius
        return radius;
    } // method for get the radius

    public int getGroupState() { return groupState; }
    public void incGroupState() { groupState++; }
    public void decGroupState() { groupState--; }

    /** LinkedListDeque methods*/
    public Shape getParent() { return parent; }

    public void setParent(Shape father) {
        // try catch needed
        if (!(father instanceof Group)) return;
        parent = father;
    }

    public Shape getAncester() {
        Shape ptr = this;
        while (!ptr.getName().equals(ptr.getParent().getName())) ptr = ptr.getParent();
        return ptr;
    }

    public Shape getLeft() { return left; }

    public Shape getRight() { return right; }

    public void setLeft(Shape l) { left = l; }

    public void setRight(Shape r) { right = r; }

    /** Removes the left reference and right reference. */

    public void removeRefer() {
        Circle ptr = this;
        ptr.left.setRight(ptr.right);
        ptr.right.setLeft(ptr.left);
        parent = this;
    }

    public void pointToMe() {
        this.parent = this;
    }

    /** method for get the name */
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
        double d = vectorSubtract(this.getCenter(),other.getCenter()).getDis();
        double RAddr = this.getRadius() + other.getRadius();
        double RSubr = Math.abs(this.getRadius() - other.getRadius());

        if (d - RSubr > 0 - EPS && d - RAddr < 0 + EPS){return true;}
        return false;
    }

    /** check Line is intersected with other Group*/
    public boolean isIntersected(Group other){
        return other.isIntersected(this);
    }

    /** move a Circle method*/
    public void move(double inDx,double inDy){
        this.getCenter().setX(this.getCenter().getX()+inDx); // move Center's x by inDx
        this.getCenter().setY(this.getCenter().getY()+inDy); // move Center's y by inDy
    }

    /** bounding box method */
    public double getLeftBounding(){return getCenter().getX() - getRadius();}
    public double getRightBounding(){return getCenter().getX() + getRadius();}
    public double getTopBounding(){return getCenter().getY() + getRadius();}
    public double getBottomBounding(){return getCenter().getY() - getRadius();}

    /** list out information of a shape*/
    public String listInfo(){
        return "[Circle]: Name:"+getName()+"; Center:"+getCenter().getX()+","+getCenter().getX()+"; Radius:"+getRadius();
    }
}