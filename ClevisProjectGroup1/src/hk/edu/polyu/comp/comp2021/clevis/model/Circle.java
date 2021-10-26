package hk.edu.polyu.comp.comp2021.clevis.model;

import static hk.edu.polyu.comp.comp2021.clevis.model.Calculate.vectorSubtract;

/** Shape of Circle*/
class Circle implements Shape{
    private final String name; // store name
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
    } // method for get the center
    public float getRadius() { // get the radius
        return radius;
    } // method for get the radius

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
        float d = vectorSubtract(this.getCenter(),other.getCenter()).getDis();
        float RAddr = this.getRadius() + other.getRadius();
        float RSubr = Math.abs(this.getRadius() - other.getRadius());

        if (d - RSubr > 0 - EPS && d - RAddr < 0 + EPS){return true;}
        return false;
    }

    /** check Line is intersected with other Group*/
    public boolean isIntersected(Group other){
        return other.isIntersected(this);
    }

    /** move a Circle method*/
    public void move(float inDx,float inDy){
        this.getCenter().setX(this.getCenter().getX()+inDx); // move Center's x by inDx
        this.getCenter().setY(this.getCenter().getY()+inDy); // move Center's y by inDy

    }

    /** bounding box method */
    public float getLeftBounding(){return getCenter().getX() - getRadius();}
    public float getRightBounding(){return getCenter().getX() + getRadius();}
    public float getTopBounding(){return getCenter().getY() + getRadius();}
    public float getBottomBounding(){return getCenter().getY() - getRadius();}

    /** list out information of a shape*/
    public String listInfo(){
        return "[Circle]: Name:"+getName()+"; Center:"+getCenter().getX()+","+getCenter().getX()+"; Radius:"+getRadius();
    }
}