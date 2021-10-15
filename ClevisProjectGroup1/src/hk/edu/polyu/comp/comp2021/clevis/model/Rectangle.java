package hk.edu.polyu.comp.comp2021.clevis.model;

/** Shape of Rectangle*/
class Rectangle implements Shape{
    private final String name; // store name
    private float w,h; // store the width and height
    private Line[] FourLines = new Line[4]; // LineArray to store 4 line of the rectangle (0: lineLeft, 1: lineBottom, 2: lineRight, 3: lineTop)

    /** constructor */
    Rectangle (String inName, float inX, float inY, float inW, float inH){
        name = inName;
        w = inW;
        h = inH;
        FourLines[0] = new Line(name+"LineLeft", inX,inY,inX,inY-inH); // set the left Line AB
        FourLines[1] = new Line(name+"LineBottom", inX,inY-inH,inX+inW,inY-inH); // set the bottom Line BC
        FourLines[2] = new Line(name+"LineRight",inX+inW,inY-inH,inX+inW,inY); // set the right Line CD
        FourLines[3] = new Line(name+"LineTop", inX+inW,inY,inX,inY); // set the top Line DA
    }

    public float getWidth(){return w;} // get the width
    public float getHeight(){return h;} // get the height
    public Line[] getFourLines(){ return FourLines; } // get 4 Lines of the rectangle
    public Line getWhichFourLines(int i){ // get which Line of the rectangle
        return getFourLines()[i];
    }
    public Vec getTopLeftCorner(){ // get the vector of the TopLeftCorner
        return getWhichFourLines(0).getA();
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

    /** bounding box method */
    public float getLeftBounding(){return getWhichFourLines(0).getA().getX();}
    public float getRightBounding(){return getWhichFourLines(2).getA().getX();}
    public float getTopBounding(){return getWhichFourLines(3).getA().getY();}
    public float getBottomBounding(){return getWhichFourLines(1).getA().getY();}

    /** list out information of a shape*/
    public String listInfo(){
        return "[Rectangle]: Name:"+getName()+"; Top-left corner:"+getTopLeftCorner().getX()+","+getTopLeftCorner().getX()+"; Width, Height:"+getWidth()+","+getHeight();
    }

}