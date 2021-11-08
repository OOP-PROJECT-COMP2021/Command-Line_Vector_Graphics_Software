package hk.edu.polyu.comp.comp2021.clevis.model;
/** BoundingBox class extends Rectangle*/
class BoundingBox extends Rectangle {

    /** BoundingBox constructor
     * @param inShape : shape to be bounded*/
    BoundingBox(Shape inShape) {
        super("Bounded " + inShape.getName(), inShape.getLeftBounding(), inShape.getTopBounding(), inShape.getRightBounding() - inShape.getLeftBounding(), inShape.getTopBounding() - inShape.getBottomBounding());
    }

    /** get the BoundingBox
     * @return : the String of information of thr bounding box*/
    public String getBoundingBox() {
        return (this.getLeftBounding()+" "+this.getTopBounding()+" "+ (this.getRightBounding()-this.getLeftBounding())+" "+(this.getTopBounding()-this.getBottomBounding()));
    }

    @Override
    public String listInfo(){
        return "[BoundingBox] Name: "+getName()+"; Top-left corner:"+"("+getTopLeftCorner().getX()+","+getTopLeftCorner().getY()+")"+"; Width, Height:"+getWidth()+","+getHeight();
    }
}
