package hk.edu.polyu.comp.comp2021.clevis.model;

/** Square inherited the Shape of Rectangle*/
class Square extends Rectangle{

    /** constructor
     * @param inName: name
     * @param inX: x
     * @param inY: y
     * @param inL: l */
    Square (String inName ,double inX, double inY, double inL){
        super(inName, inX,inY, inL, inL);
    }

    /** list out information of a shape*/
    @Override
    public String listInfo(){
        return "[Square] Name:"+getName()+"; Top-left corner:"+"("+String.format("%.2f", getTopLeftCorner().getX())+","+
                String.format("%.2f", getTopLeftCorner().getY())+")"+"; Side length:"+String.format("%.2f", getWidth());
    }
}
