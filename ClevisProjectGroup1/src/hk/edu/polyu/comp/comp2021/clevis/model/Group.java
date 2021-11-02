package hk.edu.polyu.comp.comp2021.clevis.model;

class Group implements Shape {
    private final String name; // store name
    private Shape[] shapeList;
    private int groupState = 0;

    /** LinkedListDeque methods*/
    private Shape parent = this;
    private Shape left;
    private Shape right;

    /** constructor */
    Group(String inName, Shape[] inShapeList){
        name = inName;
        shapeList = inShapeList;
    }

    public Shape[] getShapeList(){ // get the shapeList
        return shapeList;
    }

    public int getGroupState() { return groupState; }
    public void incGroupState() { groupState++; }
    public void decGroupState() { groupState--; }

    /** LinkedListDeque methods*/
    public Shape getParent() { return parent; }

    public void setParent(Shape father) {
        // try catch needed
        if (!(father instanceof Group)) return;
        parent = father;
    }

    public Shape getAncester() {
        Shape ptr = this;
        while (!ptr.getName().equals(ptr.getParent().getName())) ptr = ptr.getParent();
        return ptr;
    }

    public Shape getLeft() { return left; }

    public Shape getRight() { return right; }

    public void setLeft(Shape l) { left = l; }

    public void setRight(Shape r) { right = r; }

    public void removeRefer() {
        Group ptr = this;
        ptr.left.setRight(ptr.right);
        ptr.right.setLeft(ptr.left);
        parent = this;
        shapeList = null;
    }

    public void pointToMe() {
        this.parent = this;
    }

    // check the state before ungroup
    public void ungroup() {
        for (Shape item : shapeList) {
            item.pointToMe();
        }
        removeRefer();
    }

    public void deleteElements() {
        for (Shape item : shapeList) {
            if (item instanceof Group) ((Group) item).deleteElements();
            // if is an individual shape
            item.removeRefer();
        }
        removeRefer();
    }

    /** method for get the name */
    public String getName(){return name;} // get the name

    /** check Group is intersected with other Line */
    public boolean isIntersected(Line other){
        for (Shape s: this.getShapeList()){
            if (s.isIntersected(other)){return true;}
        }
        return false;
    }

    /** check Group is intersected with other Rectangle(and its subclass Square) */
    public boolean isIntersected(Rectangle other){
        for (Shape s: this.getShapeList()){
            if (s.isIntersected(other)){return true;}
        }
        return false;
    }

    /** check Group is intersected with other Circle */
    public boolean isIntersected(Circle other){
        for (Shape s: this.getShapeList()){
            if (s.isIntersected(other)){return true;}
        }
        return false;
    }

    /** check Group is intersected with other Group*/
    public boolean isIntersected(Group other){
        for (Shape s: this.getShapeList()){
            if (s.isIntersected(other)){return true;}
        }
        return false;
    }

    /** move a Group method*/
    public void move(double inDx,double inDy){
        for (Shape s: this.getShapeList()){
            s.move(inDx,inDy);
        }
    }

    /** bounding box method */
    public double getLeftBounding(){ // get the Left Bounding of a Group
        double minLeft = this.getShapeList()[0].getLeftBounding();
        for (Shape s: this.getShapeList()){
            minLeft = Math.min(s.getLeftBounding(),minLeft);
        }
        return (minLeft);
    }
    public double getRightBounding(){ // get the Right Bounding of a Group
        double maxRight = this.getShapeList()[0].getRightBounding();
        for (Shape s: this.getShapeList()){
            maxRight = Math.max(s.getRightBounding(),maxRight);
        }
        return (maxRight);
    }
    public double getTopBounding(){ // get the Top Bounding of a Group
        double maxTop = this.getShapeList()[0].getTopBounding();
        for (Shape s: this.getShapeList()){
            maxTop = Math.max(s.getTopBounding(),maxTop);
        }
        return (maxTop);
    }
    public double getBottomBounding(){ // get the Bottom Bounding of a Group
        double minBottom = this.getShapeList()[0].getBottomBounding();
        for (Shape s: this.getShapeList()){
            minBottom = Math.min(s.getBottomBounding(),minBottom);
        }
        return (minBottom);
    }


    /** list out information of a shape */
    public String listInfo(){
        StringBuilder outStr = new StringBuilder();
        StringBuilder outInfo = new StringBuilder();
        StringBuilder outIndex = new StringBuilder("");
        for (int i = 0; i < this.getShapeList().length; i++){
            //outStr.append(this.getShapeList()[i].getName()+", ");
            if (this.getShapeList()[i] instanceof Group){outInfo.append("\n"+(this.getShapeList()[i].listInfo()+", "));}
            else {outInfo.append(this.getShapeList()[i].listInfo()+", ");}
        }
        outInfo.delete(outInfo.length()-2,outInfo.length());

        return ("[Group]: Name: "+getName()+"; Contained shapes: {"+ outInfo+"\n}");
    }
}
