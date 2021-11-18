package hk.edu.polyu.comp.comp2021.clevis.model;
/** group implements shape */
class Group implements Shape {
    private final String name; // store name
    private Shape[] shapeList;

    /** LinkedListDeque methods*/
    private Shape parent = this;
    private Shape left;
    private Shape right;

    /** constructor
     * @param inName: the name of Group
     * @param inShapeList: the shapeList of Group */
    Group(String inName, Shape[] inShapeList){
        name = inName;
        shapeList = inShapeList;
    }

    @Override
    public String getSHAPE_TYPE() {
        return "GRP";
    }

    /** get shape list
     * @return shape list*/
    public Shape[] getShapeList(){ // get the shapeList
        return shapeList;
    }

    /** LinkedListDeque methods*/

    @Override
    public Shape getParent() { return parent; }

    @Override
    public void setParent(Shape father) {
        // try catch needed
        if (!(father instanceof Group)) return;
        parent = father;
    }

    @Override
    public Shape getAncestor() {
        Shape ptr = this;
        while (!ptr.getName().equals(ptr.getParent().getName())) ptr = ptr.getParent();
        return ptr;
    }

    @Override
    public Shape getLeft() { return left; }

    @Override
    public Shape getRight() { return right; }

    @Override
    public void setLeft(Shape l) { left = l; }

    @Override
    public void setRight(Shape r) { right = r; }

    /** to delete a Group Shape together with all its child */
    @Override
    public void removeRefer() {
        for (Shape item : shapeList) {
            item.removeRefer();
        }
        getLeft().setRight(getRight());
        getRight().setLeft(getLeft());
    }

    @Override
    public void pointToMe() {
        this.parent = this;
    }

    // check the state before ungroup
    /** ungroup */
    public void ungroup() {
        for (Shape item : shapeList) {
            item.pointToMe();
        }
        getLeft().setRight(getRight());
        getRight().setLeft(getLeft());
    }

    /** method for get the name */
    @Override
    public String getName(){return name;} // get the name

    @Override
    public boolean isIntersected(Shape other) {

        if (other.getSHAPE_TYPE().equals("LINE")) {
            return Intersected((Line) other);
        }
        if (other.getSHAPE_TYPE().equals("REC")) {
            return Intersected((Rectangle) other);
        }
        if (other.getSHAPE_TYPE().equals("CIR")) {
            return Intersected((Circle) other);
        }
        if (other.getSHAPE_TYPE().equals("GRP")) {
            return Intersected((Group) other);
        }
        return false;
    }

    /** check Group is intersected with other Line
     * @param other:other
     * @return is intersected or not */
    public boolean Intersected(Line other){
        for (Shape s: this.getShapeList()){
            if (s.isIntersected(other)) {return true;}
        }
        return false;
    }

    /** check Group is intersected with other Rectangle(and its subclass Square)
     * @param other:other
     * @return is intersected or not */
    public boolean Intersected(Rectangle other){
        for (Shape s: this.getShapeList()){
            if (s.isIntersected(other)){return true;}
        }
        return false;
    }

    /** check Group is intersected with other Circle
     * @param other:other
     * @return is intersected or not */
    public boolean Intersected(Circle other){
        for (Shape s: this.getShapeList()){
            if (s.isIntersected(other)){return true;}
        }
        return false;
    }

    /** check Group is intersected with other Group
     * @param other:other
     * @return is intersected or not */
    public boolean Intersected(Group other){
        for (Shape s: this.getShapeList()){
            if (s.isIntersected(other)){return true;}
        }
        return false;
    }

    /** move a Group method*/
    @Override
    public void move(double inDx,double inDy){
        for (Shape s: this.getShapeList()){
            s.move(inDx,inDy);
        }
    }

    /** bounding box method */
    @Override
    public double getLeftBounding(){ // get the Left Bounding of a Group
        double minLeft = Double.MAX_VALUE;
        for (Shape s: this.getShapeList()){
            minLeft = Math.min(s.getLeftBounding(),minLeft);
        }
        return (minLeft);
    }

    @Override
    public double getRightBounding(){ // get the Right Bounding of a Group
        double maxRight = Double.MIN_VALUE;
        for (Shape s: this.getShapeList()){
            maxRight = Math.max(s.getRightBounding(),maxRight);
        }
        return (maxRight);
    }

    @Override
    public double getTopBounding(){ // get the Top Bounding of a Group
        double minTop = Double.MAX_VALUE;
        for (Shape s: this.getShapeList()){
            minTop = Math.min(s.getTopBounding(),minTop);
        }
        return (minTop);
    }

    @Override
    public double getBottomBounding(){ // get the Bottom Bounding of a Group
        double maxBottom = Double.MIN_VALUE;
        for (Shape s: this.getShapeList()){
            maxBottom = Math.max(s.getBottomBounding(),maxBottom);
        }
        return (maxBottom);
    }

    /** list out information of a shape */
    private static int levelCount = 0;

    /** reset level count */
    public static void resetLevelCount() {
        levelCount = 0;
    }
    private static void inFour() {
        levelCount+=4;
    }
    private static void deFour() {
        levelCount-=4;
    }
        /** recursion for listInfo */

    @Override
    public String listInfo(){
        StringBuilder outInfo = new StringBuilder();
        inFour();
        for (int i = 0; i < this.getShapeList().length; i++){
            outInfo.append("\n").append(spaceGen(levelCount)).append(this.getShapeList()[i].listInfo());
        }
        deFour();
        return ("[Group] Name: "+getName()+"; Contained shapes: "+ outInfo);
    }

    private String spaceGen(int inNum) {
        StringBuilder outStr = new StringBuilder();
        for (int i = 0; i < inNum; i++) {
            outStr.append(" ");
        }
        return outStr.toString();
    }
}
