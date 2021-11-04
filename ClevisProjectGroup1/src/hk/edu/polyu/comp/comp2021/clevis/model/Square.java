package hk.edu.polyu.comp.comp2021.clevis.model;

/** Square inherited the Shape of Rectangle*/
class Square extends Rectangle{

    /** constructor */
    Square (String inName ,double inX, double inY, double inL){
        super(inName, inX,inY, inL, inL);
    }

    /** list out information of a shape*/
    public String listInfo(){
        return "[Square] Name:"+getName()+"; Top-left corner:"+"("+getTopLeftCorner().getX()+","+getTopLeftCorner().getY()+")"+"; Side length:"+getWidth();
    }
}
