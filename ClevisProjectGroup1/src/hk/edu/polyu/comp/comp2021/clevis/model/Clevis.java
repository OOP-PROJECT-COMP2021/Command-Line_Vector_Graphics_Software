package hk.edu.polyu.comp.comp2021.clevis.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import static hk.edu.polyu.comp.comp2021.clevis.model.Calculate.*;
import static java.lang.Math.*;

public class Clevis {
    public Clevis(){}
    private HashMap<String,Shape> storage = new HashMap<String,Shape>();
    private ArrayList<String> shapeLevel = new ArrayList<String>();

    /** [REQ2] rectangle n x y w h */
    public void drawRectangle(String inName, double inX, double inY, double inW, double inH){
        addShape(inName, new Rectangle(inName,inX,inY,inW,inH));
    }

    /** [REQ3] line n x1 y1 x2 y2 */
    public void drawLine(String inName, double inX1, double inY1, double inX2, double inY2){
        addShape(inName, new Line(inName,inX1,inY2,inX2,inY2));
    }

    /** [REQ4] circle n x y r */
    public void drawCircle(String inName, double inX, double inY, double inR){
        addShape(inName, new Circle(inName,inX,inY,inR));
    }

    /** [REQ5] square n x y l */
    public void drawSquare(String inName, double inX, double inY, double inL){
        addShape(inName, new Square(inName, inX, inY, inL));
    }

    /** [REQ6] group n n1 n2... */
    public void createGroup(String inName, Shape[] inShapeList) {
        Group tmp = new Group(inName, inShapeList);
        addShape(inName, tmp);
        for (Shape tmpShape : inShapeList) {
            tmpShape.incGroupState();
        }
    }

    /** [REQ7] ungroup n */
    public void unGroup(String inName) {
        // try catch needed
        for (Shape tmp : ((Group)storage.get(inName)).getShapeList()){
            tmp.decGroupState();
        }
        storage.remove(inName);
        shapeLevel.remove(inName);
    }

    /** [REQ8] delete n*/
    public void deleteShapeWithName(String inName) {
        if (storage.get(inName) instanceof Group) {
            Group tmp = (Group) storage.get(inName);
            Shape[] container = tmp.getShapeList();
            for (Shape a : container) {
                storage.remove(a.getName());
                shapeLevel.remove(a.getName());
            }
        }
        storage.remove(inName);
        shapeLevel.remove(inName);
    }

    /** [REQ9] boundingbox n */
    public String createBoundingBox (String inName){
        BoundingBox inBoundingBox = new BoundingBox(storage.get(inName));
        return inBoundingBox.getBoundingBox();
    }

    /** [REQ10] move n dx dy */
    public void moveShape(String inName, double inDx, double inDy) {
        storage.get(inName).move(inDx, inDy);
    }

    /** [REQ11] pick-and-move x y dx dy */
    public void pickAndMoveShape (double inX, double inY, double inDx, double inDy){
        Circle xyPoint = new Circle("xyPoint",inX,inY,0.05d);
        for (int i = shapeLevel.size()-1; i>0; i--) {
            if(storage.get(shapeLevel.get(i)).isIntersected(xyPoint)) {
                moveShape(shapeLevel.get(i),inDx,inDy); break;
            }
        }
    }

    /** [REQ12] intersect n1 n2 */
    public boolean isIntersected (Shape inShape1, Shape inShape2) {
        // try catch needed
        if (inShape2 instanceof Rectangle) {return inShape1.isIntersected((Rectangle)inShape2);}
        if (inShape2 instanceof Line) {return inShape1.isIntersected((Line)inShape2);}
        if (inShape2 instanceof Circle) {return inShape1.isIntersected((Circle) inShape2);}
        if (inShape2 instanceof Group) {return inShape1.isIntersected((Group) inShape2);}
        return false;
    }

    /** [REQ13] list n */
    public String listShape(String inName) {
        return storage.get(inName).listInfo();
    }

    /** [REQ14] listAll */
    public String listAllShape() {
        StringBuilder outStr = new StringBuilder();
        for (String inName : shapeLevel) {
            outStr.append(storage.get(inName).listInfo()).append("\n");
        }
        return outStr.toString();
    }

    /** add a shape to storage */
    public void addShape(String inName, Shape inShape) {
        // try catch needed
        if (!containsName(inName)) {
            storage.put(inName,inShape);
            shapeLevel.add(inName);
        }
    }

    /** check if the storage contains the Shape(value) */
    public boolean containsShape(Shape inShape) {
        return storage.containsValue(inShape);
    }

    /** check if the storage contains the Name(key) */
    public boolean containsName(String inName) {
        return storage.containsKey(inName);
    }

    /** get the size of storage */
    public int getSize() {
        return storage.size();
    }
}
