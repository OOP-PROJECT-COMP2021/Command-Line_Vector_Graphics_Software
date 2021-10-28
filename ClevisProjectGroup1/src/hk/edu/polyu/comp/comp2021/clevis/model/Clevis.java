package hk.edu.polyu.comp.comp2021.clevis.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import static hk.edu.polyu.comp.comp2021.clevis.model.Calculate.*;
import static java.lang.Math.*;

public class Clevis {
    public Clevis(){}

    private HashMap<String,Shape> storage = new HashMap<String,Shape>();
    private ArrayList<String> shapeLevel;


    public void drawRectangle(String inName, double inX, double inY, double inW, double inH){
        addShape(inName, new Rectangle(inName,inX,inY,inW,inH));
        shapeLevel.add(inName);
    }

    public void drawLine(String inName, double inX1, double inY1, double inX2, double inY2){
        addShape(inName, new Line(inName,inX1,inY2,inX2,inY2));
        shapeLevel.add(inName);
    }

    public void drawCircle(String inName, double inX, double inY, double inR){
        addShape(inName, new Circle(inName,inX,inY,inR));
        shapeLevel.add(inName);
    }

    public void drawSquare(String inName, double inX, double inY, double inL){
        addShape(inName, new Square(inName, inX, inY, inL));
        shapeLevel.add(inName);
    }

    public void addShape(String inName, Shape inShape) {
        // try catch needed
        if (!containsName(inName)) {
            storage.put(inName,inShape);
            shapeLevel.add(inName);
        }
    }

    public boolean containsShape(Shape inShape) {
        return storage.containsValue(inShape);
    }

    public boolean containsName(String inName) {
        return storage.containsKey(inName);
    }




    public void deleteShapeWithName(String inName) {
        if (storage.get(inName) instanceof Group) {
            Group tmp = (Group) storage.get(inName);
            Shape[] container = tmp.getShapeList();
            for (Shape a : container) {
                storage.remove(a.getName());
            }
        }
        storage.remove(inName);
    }

    public void createGroup(String inName, Shape[] inShapeList) {
        Group tmp = new Group(inName, inShapeList);
        addShape(inName, tmp);
        for (Shape tmpShape : inShapeList) {
            tmpShape.incGroupState();
        }
    }

    public void unGroup(String inName) {
        // try catch needed
        for (Shape tmp : ((Group)storage.get(inName)).getShapeList()){
            tmp.decGroupState();
        }
        storage.remove(inName);
    }

    public void moveShape(String inName, double inDx, double inDy) {
        storage.get(inName).move(inDx, inDy);
    }

    public void pickAndMoveShape (double inX, double inY, double inDx, double inDy){
        
    }

    public String listShape(String inName) {
        return storage.get(inName).listInfo();
    }

    public int getSize() {
        return storage.size();
    }
}
