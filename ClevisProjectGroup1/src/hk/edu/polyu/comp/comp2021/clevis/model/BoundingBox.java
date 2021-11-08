package hk.edu.polyu.comp.comp2021.clevis.model;
class BoundingBox extends Rectangle {

    BoundingBox(Shape inShape) {
        super("Bounded " + inShape.getName(), inShape.getLeftBounding(), inShape.getTopBounding(), inShape.getRightBounding() - inShape.getLeftBounding(), inShape.getTopBounding() - inShape.getBottomBounding());
    }

    public String getBoundingBox() {
        return (this.getLeftBounding()+" "+this.getTopBounding()+" "+ (this.getRightBounding()-this.getLeftBounding())+" "+(this.getTopBounding()-this.getBottomBounding()));
    }

    @Override
    public String listInfo(){
        return "[BoundingBox] Name: "+getName()+"; Top-left corner:"+"("+getTopLeftCorner().getX()+","+getTopLeftCorner().getY()+")"+"; Width, Height:"+getWidth()+","+getHeight();
    }
}
