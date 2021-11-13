package hk.edu.polyu.comp.comp2021.clevis.model;

/** Shape of Rectangle*/
public class Rectangle implements Shape{
    private final String name; // store name
    private double w,h; // store the width and height
    private Line[] FourLines = new Line[4]; // LineArray to store 4 line of the rectangle (0: lineLeft, 1: lineBottom, 2: lineRight, 3: lineTop)


    /** LinkedListDeque methods*/
    private Shape parent = this;
    private Shape left;
    private Shape right;

    /** constructor
     * @param inName: name of rectangle
     * @param inX: x
     * @param inY: y
     * @param inW: width
     * @param inH: height*/
    Rectangle (String inName, double inX, double inY, double inW, double inH){
        name = inName;
        w = inW;
        h = inH;
        FourLines[0] = new Line(name+"LineLeft", inX,inY,inX,inY+inH); // set the left Line AB
        FourLines[1] = new Line(name+"LineBottom", inX,inY+inH,inX+inW,inY+inH); // set the bottom Line BC
        FourLines[2] = new Line(name+"LineRight",inX+inW,inY+inH,inX+inW,inY); // set the right Line CD
        FourLines[3] = new Line(name+"LineTop", inX+inW,inY,inX,inY); // set the top Line DA
    }

    @Override
    public String getSHAPE_TYPE() {
        return "REC";
    }

    /** @return w */
    public double getWidth(){return w;} // get the width

    /** @return h */
    public double getHeight(){return h;} // get the height

    /** @return Four lines*/
    public Line[] getFourLines(){ return FourLines; } // get 4 Lines of the rectangle

    /** @param i:i
     * @return get one of the four lines*/
    public Line getWhichFourLines(int i){ // get which Line of the rectangle
        return getFourLines()[i];
    }

    /** @return location */
    public Vec getTopLeftCorner(){ // get the vector of the TopLeftCorner
        return getWhichFourLines(0).getA();
    }

    /** LinkedListDeque methods*/
    @Override
    public Shape getParent() { return parent; }

    @Override
    public void setParent(Shape father) { parent = father; }

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
//        Rectangle ptr = this;
        getLeft().setRight(getRight());
        getRight().setLeft(getLeft());
        // parent = this;
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

    /** check Rectangle(and its subclass Square) is intersected with other Line
     * @param other:other
     * @return is intersected or not */
    public boolean Intersected(Line other){
        for (Line i: this.getFourLines()){
            if(i.isIntersected(other)){return true;}
        }
        return false;
    }

    /** check Rectangle(and its subclass Square) is intersected with other Circle
     * @param other:other
     * @return is intersected or not */
    public boolean Intersected(Circle other){
        for (Line i: this.getFourLines()){
            if(i.isIntersected(other)){return true;}
        }
        return false;
    }

    /** check Rectangle(and its subclass Square) is intersected with other Rectangle(and its subclass Square)
     * @param other:other
     * @return is intersected or not */
    public boolean Intersected(Rectangle other){
        for (Line i: this.getFourLines()){
            if(i.isIntersected(other)){return true;}
        }
        return false;
    }

    /** check Rectangle(and its subclass Square) is intersected with other Group
     * @param other:other
     * @return is intersected or not */
    public boolean Intersected(Group other){
        return other.isIntersected(this);
    }

    /** move a Rectangle(and its subclass Square) method*/
    @Override
    public void move(double inDx,double inDy){
        for (Line i: this.getFourLines()){
            i.move(inDx,inDy);
        }
    }

    /** bounding box method */

    @Override
    public double getLeftBounding(){return getWhichFourLines(0).getA().getX();}
    @Override
    public double getRightBounding(){return getWhichFourLines(2).getA().getX();}
    @Override
    public double getTopBounding(){return getWhichFourLines(3).getA().getY();}
    @Override
    public double getBottomBounding(){return getWhichFourLines(1).getA().getY();}

    /** list out information of a shape*/
    @Override
    public String listInfo(){
        return "[Rectangle] Name:"+getName()+"; Top-left corner:"+"("+String.format("%.2f", getTopLeftCorner().getX())+
                ","+String.format("%.2f", getTopLeftCorner().getY())+")"+
                "; Width, Height:"+String.format("%.2f", getWidth())+","+String.format("%.2f", getHeight());
    }

}
