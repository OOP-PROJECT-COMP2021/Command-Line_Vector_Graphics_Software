package hk.edu.polyu.comp.comp2021.clevis.model;

import java.util.*;

/**Clevis Class*/
public class ClevisModel {
    /**Clevis Constructor with empty body*/
    public ClevisModel(){}

    /** fields for internal storage*/
    private HashMap<String,Shape> storage = new HashMap<>();
    private LinkedListDeque<Shape> shapeLevel = new LinkedListDeque<>();

        /** fields for Undo & Redo*/
    private Stack<String[][]> cmdStack = new Stack<>();
    private Stack<String[][]> cmdRedoStack = new Stack<>();
    private boolean undoFlag = false;
    private boolean redoFlag = false;
    private Stack<Shape> delTargets = new Stack<>();
    private Stack<Shape> delRedoTargets = new Stack<>();
        /** ---------------*/

    /** --------------------basic methods --------------------*/
    /** add a shape to storage
     * @param inName: shape name
     * @param inShape: the shape*/
    public void addShape(String inName, Shape inShape) {
        if (!containsName(inName)) {
            storage.put(inName,inShape);
            shapeLevel.addLast(inShape);
        }
    }

    /** check if the storage contains the Name(key)
     * @param inName: shape name
     * @return : true-contained, false-not contained*/
    public boolean containsName(String inName) {
        return storage.containsKey(inName);
    }

    /** return the shape from name
     * @param inName: shape name
     * @return : the shape*/
    public Shape getShape(String inName) {
        return storage.get(inName);
    }

    /** return the shapeLevel
     * @return : shapeLevel*/
    public LinkedListDeque<Shape> getShapeLevel() {
        return shapeLevel;
    }

    /** --------------------requirement methods --------------------*/
    /** [REQ2] rectangle n x y w h
     * @param inName: rectangle name n
     * @param inX: x
     * @param inY: y
     * @param inW: width
     * @param inH: height*/

    public void drawRectangle(String inName, double inX, double inY, double inW, double inH){
        if (containsName(inName)) {
            throw new IllegalArgumentException();
        }

        /** for Undo & Redo*/
        if ((undoFlag || redoFlag) &&
                (cmdStack.isEmpty()||(!cmdStack.peek()[0][0].equals("undo") && (!cmdStack.peek()[0][0].equals("redo"))))) {
            cmdRedoStack.clear();
            undoFlag = false;
            redoFlag = false;
        }
        if (cmdStack.isEmpty() || !cmdStack.peek()[0][0].equals("undo") && !cmdStack.peek()[0][0].equals("redo")) {
            String[][] cmdStr = new String[6][1];
            cmdStr[0][0] = "rectangle";
            cmdStr[1][0] = inName;
            cmdStr[2][0] = String.valueOf(inX);
            cmdStr[3][0] = String.valueOf(inY);
            cmdStr[4][0] = String.valueOf(inW);
            cmdStr[5][0] = String.valueOf(inH);
            cmdStack.push(cmdStr);
        }
        else if(cmdStack.peek()[0][0].equals("undo")||cmdStack.peek()[0][0].equals("redo")) {
            cmdStack.pop();
        }
        /** ---------------*/

        addShape(inName, new Rectangle(inName,inX,inY,inW,inH));
    }

    /** [REQ3] line n x1 y1 x2 y2
     * @param inName: line name n
     * @param inX1: x1
     * @param inY1: y1
     * @param inX2: x2
     * @param inY2: y2*/
    public void drawLine(String inName, double inX1, double inY1, double inX2, double inY2){
        if (containsName(inName)) {
            throw new IllegalArgumentException();
        }

        /** for Undo & Redo*/
        if ((undoFlag || redoFlag) &&
                (cmdStack.isEmpty()||(!cmdStack.peek()[0][0].equals("undo") && (!cmdStack.peek()[0][0].equals("redo"))))) {
            cmdRedoStack.clear();
            undoFlag = false;
            redoFlag = false;
        }
        if (cmdStack.isEmpty() || !cmdStack.peek()[0][0].equals("undo") && !cmdStack.peek()[0][0].equals("redo")) {
            String[][] cmdStr = new String[6][1];
            cmdStr[0][0] = "line";
            cmdStr[1][0] = inName;
            cmdStr[2][0] = String.valueOf(inX1);
            cmdStr[3][0] = String.valueOf(inY1);
            cmdStr[4][0] = String.valueOf(inX2);
            cmdStr[5][0] = String.valueOf(inX2);
            cmdStack.push(cmdStr);
        }
        else if(cmdStack.peek()[0][0].equals("undo")||cmdStack.peek()[0][0].equals("redo")) {
            cmdStack.pop();
        }
        /** ---------------*/

        addShape(inName, new Line(inName,inX1,inY1,inX2,inY2));
    }

    /** [REQ4] circle n x y r
     * @param inName: circle name n
     * @param inX: x
     * @param inY: y
     * @param inR: radius*/
    public void drawCircle(String inName, double inX, double inY, double inR){
        if (containsName(inName)) {
            throw new IllegalArgumentException();
        }

        /** for Undo & Redo*/
        if ((undoFlag || redoFlag) &&
                (cmdStack.isEmpty()||(!cmdStack.peek()[0][0].equals("undo") && (!cmdStack.peek()[0][0].equals("redo"))))) {
            cmdRedoStack.clear();
            undoFlag = false;
            redoFlag = false;
        }
        if (cmdStack.isEmpty() || !cmdStack.peek()[0][0].equals("undo") && !cmdStack.peek()[0][0].equals("redo")) {
            String[][] cmdStr = new String[5][1];
            cmdStr[0][0] = "circle";
            cmdStr[1][0] = inName;
            cmdStr[2][0] = String.valueOf(inX);
            cmdStr[3][0] = String.valueOf(inY);
            cmdStr[4][0] = String.valueOf(inR);
            cmdStack.push(cmdStr);
        }
        else if(cmdStack.peek()[0][0].equals("undo")||cmdStack.peek()[0][0].equals("redo")) {
            cmdStack.pop();
        }
        /** ---------------*/

        addShape(inName, new Circle(inName,inX,inY,inR));
    }

    /** [REQ5] square n x y l
     * @param inName: square name n
     * @param inX: x
     * @param inY: y
     * @param inL: l*/
    public void drawSquare(String inName, double inX, double inY, double inL){
        if (containsName(inName)) {
            throw new IllegalArgumentException();
        }

        /** for Undo & Redo*/
        if ((undoFlag || redoFlag) &&
                (cmdStack.isEmpty()||(!cmdStack.peek()[0][0].equals("undo") && (!cmdStack.peek()[0][0].equals("redo"))))) {
            cmdRedoStack.clear();
            undoFlag = false;
            redoFlag = false;
        }
        if (cmdStack.isEmpty() || !cmdStack.peek()[0][0].equals("undo") && !cmdStack.peek()[0][0].equals("redo")) {
            String[][] cmdStr = new String[5][1];
            cmdStr[0][0] = "square";
            cmdStr[1][0] = inName;
            cmdStr[2][0] = String.valueOf(inX);
            cmdStr[3][0] = String.valueOf(inY);
            cmdStr[4][0] = String.valueOf(inL);
            cmdStack.push(cmdStr);
        }
        else if(cmdStack.peek()[0][0].equals("undo")||cmdStack.peek()[0][0].equals("redo")) {
            cmdStack.pop();
        }
        /** ---------------*/

        addShape(inName, new Square(inName, inX, inY, inL));
    }

    /** [REQ6] group n n1 n2...
     * @param inName: group name n
     * @param inShapeString: list of shapes contained in the group*/
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

        /** for Undo & Redo*/
        if ((undoFlag || redoFlag) &&
                (cmdStack.isEmpty()||(!cmdStack.peek()[0][0].equals("undo") && (!cmdStack.peek()[0][0].equals("redo"))))) {
            cmdRedoStack.clear();
            undoFlag = false;
            redoFlag = false;
        }
        if (cmdStack.isEmpty() || !cmdStack.peek()[0][0].equals("undo") && !cmdStack.peek()[0][0].equals("redo")) {

            String[][] cmdStr = new String[3][1];
            cmdStr[0][0] = "group";
            cmdStr[1][0] = inName;
            cmdStr[2] = inShapeString;
            cmdStack.push(cmdStr);

        }
        else if(cmdStack.peek()[0][0].equals("undo")||cmdStack.peek()[0][0].equals("redo")) {
            cmdStack.pop();
        }
        /** ---------------*/

        Group tmp = new Group(inName, inShapeList);
        addShape(inName, tmp);
        for (Shape s : inShapeList) { s.setParent(tmp);}
    }

    /** [REQ7] ungroup n
     * @param inName: the name of group to be ungrouped*/
    public void unGroup(String inName) {
        if ((!containsName(inName)) || (!(storage.get(inName) instanceof Group)) ||
                (!(((storage.get(inName)).getParent()).getName())
                .equals((storage.get(inName)).getName()))) { throw new IllegalArgumentException(); }

        /** for Undo & Redo*/
        if ((undoFlag || redoFlag) &&
                (cmdStack.isEmpty()||(!cmdStack.peek()[0][0].equals("undo") && (!cmdStack.peek()[0][0].equals("redo"))))) {
            cmdRedoStack.clear();
            undoFlag = false;
            redoFlag = false;
        }
        if (cmdStack.isEmpty() || !cmdStack.peek()[0][0].equals("undo") && !cmdStack.peek()[0][0].equals("redo")) {
            String[][] cmdStr = new String[3][1];
            cmdStr[0][0] = "ungroup";
            cmdStr[1][0] = inName;
            String [] shapeList = new String[((Group) storage.get(inName)).getShapeList().length];
            for (int i=0;i<((Group) storage.get(inName)).getShapeList().length;i++) {
                shapeList[i] = ((Group) storage.get(inName)).getShapeList()[i].getName();
            }
            cmdStr[2] = shapeList;
            cmdStack.push(cmdStr);
        }
        else if(cmdStack.peek()[0][0].equals("undo")||cmdStack.peek()[0][0].equals("redo")) {
            cmdStack.pop();
        }
        /** ---------------*/

        ((Group)(storage.get(inName))).ungroup();
        storage.remove(inName);
    }

    /** [REQ8] delete n
     * @param inName: the name of the shape to be deleted*/
    public void deleteShapeWithName(String inName) {
        if (!containsName(inName)) {
            throw new IllegalArgumentException();
        }
        if (!(((storage.get(inName)).getParent()).getName())
                .equals((storage.get(inName)).getName())) {
            throw new IllegalArgumentException();
        }

        /** for Undo & Redo*/
        if ((undoFlag || redoFlag) &&
                (cmdStack.isEmpty()||(!cmdStack.peek()[0][0].equals("undo") && (!cmdStack.peek()[0][0].equals("redo"))))) {
            cmdRedoStack.clear(); delRedoTargets.clear();
            undoFlag = false;
            redoFlag = false;
        }
        if  (cmdStack.isEmpty() || !cmdStack.peek()[0][0].equals("undo") && !cmdStack.peek()[0][0].equals("redo")) {
            String[][] cmdStr = new String[3][1];
            cmdStr[0][0] = "delete";
            cmdStr[1][0] = inName;

            if (storage.get(inName) instanceof Group) {
                int n = ((Group) storage.get(inName)).getShapeList().length;
                String [] shapeList = new String[n];
                for (int i = 0 ; i < n ;i++) {
                    shapeList[i] = ((Group) storage.get(inName)).getShapeList()[i].getName();
                }
                cmdStr[2] = shapeList;
            }
            cmdStack.push(cmdStr);
        }
        else if(cmdStack.peek()[0][0].equals("undo")||cmdStack.peek()[0][0].equals("redo")) {
            cmdStack.pop();
        }
        delTargets.push(storage.get(inName));
        /** ---------------*/

        (storage.get(inName)).removeRefer();
        removeGroup(storage.get(inName));
    }

    /**
     * Helper method for deleting a shape from storage.
     * @param inName the name of the shape to be deleted.
     */
    private void removeGroup(Shape inName) {
        if (storage.get(inName.getName()) instanceof Group) {
            Group tmp = (Group) storage.get(inName.getName());
            Shape[] container = tmp.getShapeList();
            for (Shape a : container) {
                removeGroup(a);
            }
        }
        storage.remove(inName.getName());
    }

    /** [REQ9] boundingbox n
     * @param inName: the name of the shape
     * @return : the information of the bounding box*/
    public String createBoundingBox (String inName){
        if (!containsName(inName) || !storage.get(inName).getParent().getName().equals(storage.get(inName).getName())) {
            throw new IllegalArgumentException();
        }
        BoundingBox inBoundingBox = new BoundingBox(storage.get(inName));
        return inBoundingBox.listInfo();
    }

    /** [REQ10] move n dx dy
     * @param inName : the shape name
     * @param inDx: moved x distance
     * @param inDy: moved y distance*/
    public void moveShape(String inName, double inDx, double inDy) {
        if ((!containsName(inName)) ||
                !(storage.get(inName).getParent().getName().equals(storage.get(inName).getName()) )) {
            throw new IllegalArgumentException();
        }

        /** for Undo & Redo*/

        if ((undoFlag || redoFlag) &&
                (cmdStack.isEmpty()||(!cmdStack.peek()[0][0].equals("undo") && (!cmdStack.peek()[0][0].equals("redo"))))) {
            cmdRedoStack.clear();
            undoFlag = false;
            redoFlag = false;
        }
        if (cmdStack.isEmpty() || !cmdStack.peek()[0][0].equals("undo") && !cmdStack.peek()[0][0].equals("redo")) {
            String[][] cmdStr = new String[4][1];
            cmdStr[0][0] = "move";
            cmdStr[1][0] = inName;
            cmdStr[2][0] = String.valueOf(inDx);
            cmdStr[3][0] = String.valueOf(inDy);
            cmdStack.push(cmdStr);
        }
        else if(cmdStack.peek()[0][0].equals("undo")||cmdStack.peek()[0][0].equals("redo")) {
            cmdStack.pop();
        }
        /** ---------------*/

        storage.get(inName).move(inDx, inDy);
    }

    private final double POINT_XY = 0.05d;
    /** [REQ11] pick-and-move x y dx dy
     * @param inX: x of the point
     * @param inY: y of the point
     * @param inDx: moved x distance
     * @param inDy: moved y distance*/
    public void pickAndMoveShape (double inX, double inY, double inDx, double inDy){
        Circle xyPoint = new Circle("xyPoint",inX,inY,POINT_XY);
        Shape finalShape = xyPoint;
        Shape inShape = shapeLevel.getSentinel().getRight();

        while (inShape != shapeLevel.getSentinel()) {
            if (inShape.isIntersected(xyPoint) && ! (inShape instanceof Group)) {
                finalShape = inShape.getAncestor();
                inShape = inShape.getAncestor().getRight();
            }
            else {
                inShape = inShape.getRight();
            }
        }

        /** picked nothing, throw exception*/
        if (finalShape == xyPoint) {
            throw new IllegalArgumentException();
        }
        else {
            /** for Undo & Redo*/
            if ((undoFlag || redoFlag) &&
                    (cmdStack.isEmpty()||(!cmdStack.peek()[0][0].equals("undo") && (!cmdStack.peek()[0][0].equals("redo"))))) {
                cmdRedoStack.clear();
                undoFlag = false;
                redoFlag = false;
            }
            if (cmdStack.isEmpty() || !cmdStack.peek()[0][0].equals("undo") && !cmdStack.peek()[0][0].equals("redo")) {
                String[][] cmdStr = new String[4][1];
                cmdStr[0][0] = "pick-and-move";
                cmdStr[1][0] = finalShape.getName();
                cmdStr[2][0] = String.valueOf(inDx);
                cmdStr[3][0] = String.valueOf(inDy);
                cmdStack.push(cmdStr);
            }
            else if(cmdStack.peek()[0][0].equals("undo")||cmdStack.peek()[0][0].equals("redo")) {
                cmdStack.pop();
            }
            /** ---------------*/

            storage.get(finalShape.getName()).move(inDx, inDy);
        }
    }

    /** [REQ12] intersect n1 n2
     * @param inString1: the first shape
     * @param inString2: the second shape
     * @return : true-intersected, false-not intersected*/
    public boolean isIntersected (String inString1, String inString2) {
        if (!containsName(inString1) || !containsName(inString2)||
                !storage.get(inString1).getParent().getName().equals(storage.get(inString1).getName())||
                !storage.get(inString2).getParent().getName().equals(storage.get(inString2).getName())) {
            throw new IllegalArgumentException();
        }
        Shape inShape1 = storage.get(inString1);
        Shape inShape2 = storage.get(inString2);
        return inShape1.isIntersected(inShape2);
    }

    /** [REQ13] list n
     * @param inName: the shape name
     * @return : the String of the shape information*/
    public String listShape(String inName) {
        if (!containsName(inName)) {
            throw new IllegalArgumentException();
        }
        return storage.get(inName).listInfo();
    }

    /** [REQ14] listAll
     * @return : the String of the all shapes information*/
    public String listAllShape() {
        StringBuilder outStr = new StringBuilder();

        for (Shape inShape : shapeLevel) {
            if (inShape.getParent()==inShape) {
                outStr.append(inShape.listInfo()).append("\n");
                Group.resetLevelCount();
            }
        }
        return outStr.toString();
    }

    /** -----------[BON1] undo-----------*/
        /** Undo Control methods: */
    public void UndoControl() {
        if (cmdStack.isEmpty()) {
            throw new IllegalArgumentException();
        }
        undoFlag = true;
        String[][] cmdStr = new String[1][1];
        cmdStr[0][0] = "undo";
        cmdStack.push(cmdStr);
        String[][] endCmd = cmdStack.elementAt(cmdStack.size()-2);

        if (endCmd[0][0].equals("rectangle")||endCmd[0][0].equals("line")||
                endCmd[0][0].equals("circle")||endCmd[0][0].equals("square")) {
            UndoDraw(endCmd[1][0]);
        }
        else if(endCmd[0][0].equals("group")) {
            UndoGroup(endCmd[1][0]);
        }
        else if(endCmd[0][0].equals("ungroup")) {
            UndoUnGroup(endCmd[1][0],endCmd[2]);
        }
        else if(endCmd[0][0].equals("delete")) {
            cmdStack.pop();
            UndoDelete(endCmd[1][0]);
            delRedoTargets.push(delTargets.pop());
        }
        else if(endCmd[0][0].equals("move")||endCmd[0][0].equals("pick-and-move")) {
            UndoMove(endCmd[1][0],Double.parseDouble(endCmd[2][0]),Double.parseDouble(endCmd[3][0]));
        }
        cmdRedoStack.push(cmdStack.pop());
    }

        /** Undo drawing = deleteShapeWithName
         * @param inName: shape name*/
    private void UndoDraw(String inName) {
        deleteShapeWithName(inName);
    }

        /** Undo group = unGroup
         * @param inName: shape name*/
    private void UndoGroup(String inName) {
        unGroup(inName);
    }

        /** Undo ungroup = Group
         * @param inName : shape name
         * @param inShapeString: the list of shapes contained in the group*/
    private void UndoUnGroup(String inName, String[] inShapeString) {
       createGroup(inName,inShapeString);
    }

        /** Undo move = move back
         * @param inName : shape name
         * @param inDx : shape moved by x
         * @param inDy : shape moved by y*/
    private void UndoMove(String inName, double inDx, double inDy){
        moveShape(inName,-inDx,-inDy);
    }

        /** Undo delete = add back
         * @param inName : shape name*/
    private void UndoDelete(String inName) {
        Shape inShape = delTargets.peek();
        if (inShape.getName().equals(inName)) {
            if (!(inShape instanceof Group)) {
                storage.put(inName,inShape);
                inShape.getLeft().setRight(inShape);
                inShape.getRight().setLeft(inShape);
            }
            else {
                recursionUndoDel(inShape);
                recursionAdd(inShape);
            }
        }
    }
    private void recursionAdd(Shape inShape) {
        storage.put(inShape.getName(), inShape);
        if (inShape instanceof Group) {
            Shape[] container = ((Group) inShape).getShapeList();
            for (Shape a : container) {
                recursionAdd(a);
            }
        }
    }
    private void recursionUndoDel(Shape inShape) {
        inShape.getLeft().setRight(inShape);
        inShape.getRight().setLeft(inShape);
        if(inShape instanceof Group) {
            Shape[] inShapeList = ((Group) inShape).getShapeList();
            for (Shape innerShape : inShapeList) {
                recursionUndoDel(innerShape);
            }
        }
    }
    /** ---------------*/

    /** -----------[BON2] redo----------*/
        /** Undo Control methods: */
    public void RedoControl() {
        if (cmdRedoStack.isEmpty()) {
            throw new IllegalArgumentException();
        }
        else {
            redoFlag = true;
            String[][] cmdStr = new String[1][1];
            cmdStr[0][0] = "redo";
            cmdStack.push(cmdStr);

            String[][] endRedoCmd = cmdRedoStack.peek();

            if (endRedoCmd[0][0].equals("rectangle")) {
                drawRectangle(endRedoCmd[1][0],Double.parseDouble(endRedoCmd[2][0]),Double.parseDouble(endRedoCmd[3][0]),
                        Double.parseDouble(endRedoCmd[4][0]),Double.parseDouble(endRedoCmd[5][0]));
            }
            else if (endRedoCmd[0][0].equals("line")) {
                drawLine(endRedoCmd[1][0],Double.parseDouble(endRedoCmd[2][0]),Double.parseDouble(endRedoCmd[3][0]),
                        Double.parseDouble(endRedoCmd[4][0]),Double.parseDouble(endRedoCmd[5][0]));
            }
            else if (endRedoCmd[0][0].equals("circle")) {
                drawCircle(endRedoCmd[1][0],Double.parseDouble(endRedoCmd[2][0]),
                        Double.parseDouble(endRedoCmd[3][0]),Double.parseDouble(endRedoCmd[4][0]));
            }
            else if (endRedoCmd[0][0].equals("square")) {
                drawSquare(endRedoCmd[1][0],Double.parseDouble(endRedoCmd[2][0]),
                        Double.parseDouble(endRedoCmd[3][0]),Double.parseDouble(endRedoCmd[4][0]));
            }
            else if(endRedoCmd[0][0].equals("group")) {
                createGroup(endRedoCmd[1][0],endRedoCmd[2]);
            }
            else if(endRedoCmd[0][0].equals("ungroup")) {
                unGroup(endRedoCmd[1][0]);
            }
            else if(endRedoCmd[0][0].equals("delete")) {
                deleteShapeWithName(endRedoCmd[1][0]);
            }
            else if(endRedoCmd[0][0].equals("move")||endRedoCmd[0][0].equals("pick-and-move")) {
                moveShape(endRedoCmd[1][0],Double.parseDouble(endRedoCmd[2][0]),Double.parseDouble(endRedoCmd[3][0]));
            }
            cmdStack.push(cmdRedoStack.pop());
        }
    }
    /** ---------------*/
}