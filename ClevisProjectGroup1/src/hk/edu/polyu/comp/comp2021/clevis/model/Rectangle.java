package hk.edu.polyu.comp.comp2021.clevis.model;

/** Shape of Rectangle*/
class Rectangle implements Shape{
    private final String name; // store name
    private double w,h; // store the width and height
    private Line[] FourLines = new Line[4]; // LineArray to store 4 line of the rectangle (0: lineLeft, 1: lineBottom, 2: lineRight, 3: lineTop)


    /** LinkedListDeque methods*/
    private Shape parent = this;
    private Shape left;
    private Shape right;

    /** constructor */
    Rectangle (String inName, double inX, double inY, double inW, double inH){
        name = inName;
        w = inW;
        h = inH;
        FourLines[0] = new Line(name+"LineLeft", inX,inY,inX,inY-inH); // set the left Line AB
        FourLines[1] = new Line(name+"LineBottom", inX,inY-inH,inX+inW,inY-inH); // set the bottom Line BC
        FourLines[2] = new Line(name+"LineRight",inX+inW,inY-inH,inX+inW,inY); // set the right Line CD
        FourLines[3] = new Line(name+"LineTop", inX+inW,inY,inX,inY); // set the top Line DA
    }

    public double getWidth(){return w;} // get the width
    public double getHeight(){return h;} // get the height
    public Line[] getFourLines(){ return FourLines; } // get 4 Lines of the rectangle
    public Line getWhichFourLines(int i){ // get which Line of the rectangle
        return getFourLines()[i];
    }
    public Vec getTopLeftCorner(){ // get the vector of the TopLeftCorner
        return getWhichFourLines(0).getA();
    }

    /** LinkedListDeque methods*/
    public Shape getParent() { return parent; }

    public void setParent(Shape father) { parent = father; }

    public Shape getAncester() {
        Shape ptr = this;
        while (!ptr.getName().equals(ptr.getParent().getName())) ptr = ptr.getParent();
        return ptr;
    }

    public Shape getLeft() { return left; }

    public Shape getRight() { return right; }

    public void setLeft(Shape l) { left = l; }

    public void setRight(Shape r) { right = r; }

    public void removeRefer() {
        Rectangle ptr = this;
        ptr.left.setRight(ptr.right);
        ptr.right.setLeft(ptr.left);
        parent = this;
    }

    public void pointToMe() {
        this.parent = this;
    }

    /** method for get the name */
    public String getName(){return name;} // get the name

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

    /** check Rectangle(and its subclass Square) is intersected with other Group*/
    public boolean isIntersected(Group other){
        return other.isIntersected(this);
    }

    /** move a Rectangle(and its subclass Square) method*/
    public void move(double inDx,double inDy){
        for (Line i: this.getFourLines()){
            i.move(inDx,inDy);
        }
    }

    /** bounding box method */
    public double getLeftBounding(){return getWhichFourLines(0).getA().getX();}
    public double getRightBounding(){return getWhichFourLines(2).getA().getX();}
    public double getTopBounding(){return getWhichFourLines(3).getA().getY();}
    public double getBottomBounding(){return getWhichFourLines(1).getA().getY();}

    /** list out information of a shape*/
    public String listInfo(){
        return "[Rectangle]: Name:"+getName()+"; Top-left corner:"+getTopLeftCorner().getX()+","+getTopLeftCorner().getY()+"; Width, Height:"+getWidth()+","+getHeight();
    }

}
