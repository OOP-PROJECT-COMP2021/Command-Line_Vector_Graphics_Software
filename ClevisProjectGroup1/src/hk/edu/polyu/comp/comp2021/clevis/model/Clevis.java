package hk.edu.polyu.comp.comp2021.clevis.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import static hk.edu.polyu.comp.comp2021.clevis.model.Calculate.*;
import static java.lang.Math.*;

public class Clevis {
    public Clevis(){}
    private HashMap<String,Shape> storage = new HashMap<String,Shape>();
    //private ArrayList<String> shapeLevel = new ArrayList<String>();
    LinkedListDeque<Shape> shapeLevel = new LinkedListDeque<>();

    /** [REQ2] rectangle n x y w h */
    public void drawRectangle(String inName, double inX, double inY, double inW, double inH){
        if (containsName(inName)) {
            throw new IllegalArgumentException();
        }
        addShape(inName, new Rectangle(inName,inX,inY,inW,inH));
    }

    /** [REQ3] line n x1 y1 x2 y2 */
    public void drawLine(String inName, double inX1, double inY1, double inX2, double inY2){
        if (containsName(inName)) {
            throw new IllegalArgumentException();
        }
        addShape(inName, new Line(inName,inX1,inY1,inX2,inY2));
    }

    /** [REQ4] circle n x y r */
    public void drawCircle(String inName, double inX, double inY, double inR){
        if (containsName(inName)) {
            throw new IllegalArgumentException();
        }
        addShape(inName, new Circle(inName,inX,inY,inR));
    }

    /** [REQ5] square n x y l */
    public void drawSquare(String inName, double inX, double inY, double inL){
        if (containsName(inName)) {
            throw new IllegalArgumentException();
        }
        addShape(inName, new Square(inName, inX, inY, inL));
    }

    /** [REQ6] group n n1 n2... */
    public void createGroup(String inName, String[] inShapeString) {
        Shape[] inShapeList = new Shape[inShapeString.length];
        HashSet<String> exist = new HashSet<>();
        for (int i = 0; i < inShapeString.length; i++) {
            if (exist.contains(inShapeString[i]) || (!containsName(inShapeString[i]))
                    || (containsName(inName)) || (!(((storage.get(inShapeString[i])).getParent()).getName())
                    .equals((storage.get(inShapeString[i])).getName()))) throw new IllegalArgumentException();
            exist.add(inShapeString[i]);
            inShapeList[i] = storage.get(inShapeString[i]);
        }
        Group tmp = new Group(inName, inShapeList);
        addShape(inName, tmp);
        for (Shape s : inShapeList) { s.setParent(tmp);}
    }

    /** [REQ7] ungroup n */
    public void unGroup(String inName) {
        if ((!containsName(inName)) || (!(storage.get(inName) instanceof Group)) || (!(((storage.get(inName)).getParent()).getName())
                .equals((storage.get(inName)).getName()))) { throw new IllegalArgumentException(); }
        ((Group)(storage.get(inName))).ungroup();
        storage.remove(inName);
    }

    /** [REQ8] delete n*/
    public void deleteShapeWithName(String inName) {
        if (!containsName(inName)) {
            throw new IllegalArgumentException();
        }
        if (!(((storage.get(inName)).getParent()).getName())
                .equals((storage.get(inName)).getName())) {
            throw new IllegalArgumentException();
        }

        (storage.get(inName)).removeRefer();

        if (storage.get(inName) instanceof Group) {
            Group tmp = (Group) storage.get(inName);
            Shape[] container = tmp.getShapeList();
            for (Shape a : container) {
                storage.remove(a.getName());
            }
        }
        storage.remove(inName);
    }

    /** [REQ9] boundingbox n */
    public void createBoundingBox (String inName){
        if (!containsName(inName)) {
            throw new IllegalArgumentException();
        }
        BoundingBox inBoundingBox = new BoundingBox(storage.get(inName));
        inBoundingBox.getBoundingBox();
        System.out.println(inBoundingBox.listInfo());
    }

    /** [REQ10] move n dx dy */
    public void moveShape(String inName, double inDx, double inDy) {
        if (!(storage.get(inName).getParent().getName().equals(storage.get(inName).getName()) || (!containsName(inName)))) {
            throw new IllegalArgumentException();
        }
        storage.get(inName).move(inDx, inDy);
    }

    /** [REQ11] pick-and-move x y dx dy */
    public void pickAndMoveShape (double inX, double inY, double inDx, double inDy){
        Circle xyPoint = new Circle("xyPoint",inX,inY,0.05d);
        Shape finalShape = xyPoint;

        for (Shape s : shapeLevel) {
            if (s instanceof Group) {continue;}

            if (s.isIntersected(xyPoint)) { finalShape = s.getAncester();}
        }

        /** picked nothing, throw exception*/
        if (finalShape == xyPoint) {
            throw new IllegalArgumentException();
        }
        else {
            moveShape(finalShape.getName(),inDx,inDy);
        }
    }

    /** [REQ12] intersect n1 n2 */
    public boolean isIntersected (String inString1, String inString2) {
        if (!containsName(inString1) || !containsName(inString2)) {
            throw new IllegalArgumentException();
        }
        Shape inShape1 = storage.get(inString1);
        Shape inShape2 = storage.get(inString2);
        if (inShape2 instanceof Rectangle) {return inShape1.isIntersected((Rectangle)inShape2);}
        if (inShape2 instanceof Line) {return inShape1.isIntersected((Line)inShape2);}
        if (inShape2 instanceof Circle) {return inShape1.isIntersected((Circle) inShape2);}
        if (inShape2 instanceof Group) {return inShape1.isIntersected((Group) inShape2);}
        return false;
    }

    /** [REQ13] list n */
    public String listShape(String inName) {
        if (!containsName(inName)) {
            throw new IllegalArgumentException();
        }
        return storage.get(inName).listInfo();
    }

    /** [REQ14] listAll */
    public void listAllShape() {
        for (Shape inShape : shapeLevel) {
            if (inShape instanceof Group) {
                System.out.printf("%s%n", inShape.listInfo());
                for (Shape item : ((Group) inShape).getShapeList()) {
                    // try to use recursion
                }
                continue;
            }
            if (inShape.getParent().getName().equals(inShape.getName())) { continue; }

        }
    }

    /** add a shape to storage */
    public void addShape(String inName, Shape inShape) {
        if (!containsName(inName)) {
            storage.put(inName,inShape);
            shapeLevel.addLast(inShape);
        }
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
