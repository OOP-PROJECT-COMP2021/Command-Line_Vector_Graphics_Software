package hk.edu.polyu.comp.comp2021.clevis.model;
/** bounding box extends rectangle*/
public class BoundingBox extends Rectangle {

    /** Bounding box constructor
     * @param inShape : shape*/
    public BoundingBox(Shape inShape) {
        super("Bounded " + inShape.getName(),
                inShape.getLeftBounding(), inShape.getTopBounding(),
                inShape.getRightBounding() - inShape.getLeftBounding(),
                Math.abs(inShape.getTopBounding() - inShape.getBottomBounding()));
    }


    @Override
    public String listInfo(){
        return "[BoundingBox] Name: "+getName()+"; Top-left corner:"+"("
                +String.format("%.2f", getTopLeftCorner().getX())+","
                +String.format("%.2f", getTopLeftCorner().getY())+")"+"; Width, Height:"
                +String.format("%.2f", getWidth())+","+String.format("%.2f", getHeight());
    }
}
