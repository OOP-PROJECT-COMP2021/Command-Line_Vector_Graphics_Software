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

    public void removeShapeWithName(String inName) {
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
    }

    public void unGroup(String inName) {
        storage.remove(inName);
    }

    public void moveShape(String inName, double inDx, double inDy) {
        storage.get(inName).move(inDx, inDy);
    }

    public String listShape(String inName) {
        return storage.get(inName).listInfo();
    }

    public int getSize() {
        return storage.size();
    }
}
