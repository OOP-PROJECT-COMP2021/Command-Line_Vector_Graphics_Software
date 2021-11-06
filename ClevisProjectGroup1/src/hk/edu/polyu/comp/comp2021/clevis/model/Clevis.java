package hk.edu.polyu.comp.comp2021.clevis.model;

import java.util.*;

import static hk.edu.polyu.comp.comp2021.clevis.model.Calculate.*;
import static java.lang.Math.*;

public class Clevis {
    public Clevis(){}
    private HashMap<String,Shape> storage = new HashMap<String,Shape>();
    //private ArrayList<String> shapeLevel = new ArrayList<String>();
    private LinkedListDeque<Shape> shapeLevel = new LinkedListDeque<>();


    /** for Undo*/
    private Stack<String[][]> cmdStack = new Stack<>();
    private Stack<String[][]> cmdRedoStack = new Stack<>();
    private boolean undoFlag = false;

    private Stack<Shape> delTargets = new Stack<>();
    private Stack<Shape> delRedoTargets = new Stack<>();

    /** [REQ2] rectangle n x y w h */
    public void drawRectangle(String inName, double inX, double inY, double inW, double inH){
        if (containsName(inName)) {
            throw new IllegalArgumentException();
        }

        /** for Undo*/

        if (undoFlag == true && !cmdStack.peek()[0][0].equals("undo")) {
            cmdRedoStack.clear();
        }
        if (cmdStack.isEmpty() || !cmdStack.peek()[0][0].equals("undo")) {

//            cmdStack.clear();cmdRedoStack.clear();
            String[][] cmdStr = new String[6][1];
            cmdStr[0][0] = "rectangle";
            cmdStr[1][0] = inName;
            cmdStr[2][0] = String.valueOf(inX);
            cmdStr[3][0] = String.valueOf(inY);
            cmdStr[4][0] = String.valueOf(inW);
            cmdStr[5][0] = String.valueOf(inH);
            cmdStack.push(cmdStr);

        }
        else if(cmdStack.peek()[0][0].equals("undo")) {
            cmdStack.pop();
        }

        addShape(inName, new Rectangle(inName,inX,inY,inW,inH));
    }

    /** [REQ3] line n x1 y1 x2 y2 */
    public void drawLine(String inName, double inX1, double inY1, double inX2, double inY2){
        if (containsName(inName)) {
            throw new IllegalArgumentException();
        }

        /** for Undo*/
        if (undoFlag == true && !cmdStack.peek()[0][0].equals("undo")) {
            cmdRedoStack.clear();
        }
        if (cmdStack.isEmpty() || !cmdStack.peek()[0][0].equals("undo")) {

            String[][] cmdStr = new String[6][1];
            cmdStr[0][0] = "line";
            cmdStr[1][0] = inName;
            cmdStr[2][0] = String.valueOf(inX1);
            cmdStr[3][0] = String.valueOf(inY1);
            cmdStr[4][0] = String.valueOf(inX2);
            cmdStr[5][0] = String.valueOf(inX2);
            cmdStack.push(cmdStr);

        }
        else if(cmdStack.peek()[0][0].equals("undo")) {
            cmdStack.pop();
        }

        addShape(inName, new Line(inName,inX1,inY1,inX2,inY2));
    }

    /** [REQ4] circle n x y r */
    public void drawCircle(String inName, double inX, double inY, double inR){
        if (containsName(inName)) {
            throw new IllegalArgumentException();
        }

        /** for Undo*/
        if (undoFlag == true && !cmdStack.peek()[0][0].equals("undo")) {
            cmdRedoStack.clear();
        }
        if (cmdStack.isEmpty() || !cmdStack.peek()[0][0].equals("undo")) {

            String[][] cmdStr = new String[5][1];
            cmdStr[0][0] = "circle";
            cmdStr[1][0] = inName;
            cmdStr[2][0] = String.valueOf(inX);
            cmdStr[3][0] = String.valueOf(inY);
            cmdStr[4][0] = String.valueOf(inR);
            cmdStack.push(cmdStr);

        }
        else if(cmdStack.peek()[0][0].equals("undo")) {
            cmdStack.pop();
        }

        addShape(inName, new Circle(inName,inX,inY,inR));
    }

    /** [REQ5] square n x y l */
    public void drawSquare(String inName, double inX, double inY, double inL){
        if (containsName(inName)) {
            throw new IllegalArgumentException();
        }

        /** for Undo*/
        if (undoFlag == true && !cmdStack.peek()[0][0].equals("undo")) {
            cmdRedoStack.clear();
        }
        if (cmdStack.isEmpty() || !cmdStack.peek()[0][0].equals("undo")) {

            String[][] cmdStr = new String[5][1];
            cmdStr[0][0] = "square";
            cmdStr[1][0] = inName;
            cmdStr[2][0] = String.valueOf(inX);
            cmdStr[3][0] = String.valueOf(inY);
            cmdStr[4][0] = String.valueOf(inL);
            cmdStack.push(cmdStr);

        }
        else if(cmdStack.peek()[0][0].equals("undo")) {
            cmdStack.pop();
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

        /** for Undo*/
        if (undoFlag == true && !cmdStack.peek()[0][0].equals("undo")) {
            cmdRedoStack.clear();
        }
        if (cmdStack.isEmpty() || !cmdStack.peek()[0][0].equals("undo")) {

            String[][] cmdStr = new String[3][1];
            cmdStr[0][0] = "group";
            cmdStr[1][0] = inName;
            cmdStr[2][0] = Arrays.toString(inShapeString);
            cmdStack.push(cmdStr);

        }
        else if(cmdStack.peek()[0][0].equals("undo")) {
            cmdStack.pop();
        }

        Group tmp = new Group(inName, inShapeList);
        addShape(inName, tmp);
        for (Shape s : inShapeList) { s.setParent(tmp);}
    }

    /** [REQ7] ungroup n */
    public void unGroup(String inName) {
        if ((!containsName(inName)) || (!(storage.get(inName) instanceof Group)) || (!(((storage.get(inName)).getParent()).getName())
                .equals((storage.get(inName)).getName()))) { throw new IllegalArgumentException(); }


        /** for Undo*/
        if (undoFlag == true && !cmdStack.peek()[0][0].equals("undo")) {
            cmdRedoStack.clear();
        }
        if (cmdStack.isEmpty() || !cmdStack.peek()[0][0].equals("undo")) {

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
        else if(cmdStack.peek()[0][0].equals("undo")) {
            cmdStack.pop();
        }

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

        /** for Undo*/

        if (undoFlag == true && !cmdStack.peek()[0][0].equals("undo")) {
            cmdRedoStack.clear(); delRedoTargets.clear();
        }
        if (cmdStack.isEmpty() || !cmdStack.peek()[0][0].equals("undo")) {

            String[][] cmdStr = new String[3][1];
            cmdStr[0][0] = "delete";
            cmdStr[1][0] = inName;

            if (storage.get(inName) instanceof Group) {
                String [] shapeList = new String[((Group) storage.get(inName)).getShapeList().length];
                for (int i=0;i<((Group) storage.get(inName)).getShapeList().length;i++) {
                    shapeList[i] = ((Group) storage.get(inName)).getShapeList()[i].getName();
                }

                cmdStr[2] = shapeList;
            }
            cmdStack.push(cmdStr);
        }
        else if(cmdStack.peek()[0][0].equals("undo")) {
            cmdStack.pop();
        }

        delTargets.push(storage.get(inName));
        /** for Undo*/

        (storage.get(inName)).removeRefer();

        if (storage.get(inName) instanceof Group) {
            Group tmp = (Group) storage.get(inName);
            Shape[] container = tmp.getShapeList();
            int i = container.length;
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

        /** for Undo*/
        if (undoFlag) {cmdStack.clear();cmdRedoStack.clear();}
        String[][] cmdStr = new String[4][1];
        cmdStr[0][0] = "move";
        cmdStr[1][0] = inName;
        cmdStr[2][0] = String.valueOf(inDx);
        cmdStr[3][0] = String.valueOf(inDy);
        cmdStack.push(cmdStr);
    }

    /** [REQ11] pick-and-move x y dx dy */
    public void pickAndMoveShape (double inX, double inY, double inDx, double inDy){
        Circle xyPoint = new Circle("xyPoint",inX,inY,0.05d);
        Shape finalShape = xyPoint;
        Shape inShape = shapeLevel.getSentinel().getRight();

        while (inShape != shapeLevel.getSentinel()) {
            if (inShape.isIntersected(xyPoint) && ! (inShape instanceof Group)) {
                finalShape = inShape.getAncester();
                inShape = inShape.getAncester().getRight();
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
            moveShape(finalShape.getName(),inDx,inDy);
        }

        /** for Undo*/
        if (undoFlag) {cmdStack.clear();cmdRedoStack.clear();}
        String[][] cmdStr = new String[4][1];
        cmdStr[0][0] = "pick-and-move";
        cmdStr[1][0] = finalShape.getName();
        cmdStr[2][0] = String.valueOf(inDx);
        cmdStr[3][0] = String.valueOf(inDy);
        cmdStack.push(cmdStr);
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
    public String listAllShape() {
        StringBuilder outStr = new StringBuilder();
        Group.resetLevelCount();
        for (Shape inShape : shapeLevel) {
            outStr.append(inShape.listInfo()).append("\n");
        }


        return outStr.toString();
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

    /** Undo methods: */
    public void UndoControl() {
        undoFlag = true;


        String[][] cmdStr = new String[1][1];
        cmdStr[0][0] = "undo";
        cmdStack.push(cmdStr);

        String[][] endCmd = cmdStack.elementAt(cmdStack.size()-2);

        if (endCmd[0][0].equals("rectangle")||endCmd[0][0].equals("line")||endCmd[0][0].equals("circle")||endCmd[0][0].equals("square")) {
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

    /** Undo drawing = deleteShapeWithName */
    public void UndoDraw(String inName) {
        deleteShapeWithName(inName);
    }

    /** Undo group = unGroup */
    public void UndoGroup(String inName) {
        unGroup(inName);
    }

    /** Undo ungroup = Group */
    public void UndoUnGroup(String inName, String[] inShapeString) {
       createGroup(inName,inShapeString);
    }

    /** Undo move = move back*/
    public void UndoMove(String inName, double inDx, double inDy){
        moveShape(inName,-inDx,-inDy);
    }

    /** Undo delete = add back*/
    public void UndoDelete(String inName) {
        Shape inShape = delTargets.peek();
        if (inShape.getName().equals(inName)) {
            storage.put(inName,inShape);
            if (! (inShape instanceof Group)) {
                inShape.getLeft().setRight(inShape);
                inShape.getRight().setLeft(inShape);
            }
            else {
                recursionDel(inShape);
                Shape[] inShapeList = ((Group) inShape).getShapeList();
                for (Shape innerShape : inShapeList) {
                    storage.put(innerShape.getName(),innerShape);
                }
            }
        }
    }
    private void recursionDel(Shape inShape) {
        inShape.getLeft().setRight(inShape);
        inShape.getRight().setLeft(inShape);
        if(inShape instanceof Group) {
            Shape[] inShapeList = ((Group) inShape).getShapeList();
            for (Shape innerShape : inShapeList) {
                recursionDel(innerShape);
            }
        }
    }
}
