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
        storage.remove(inName);
    }

    public int getSize() {
        return storage.size();
    }
}
