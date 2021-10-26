package hk.edu.polyu.comp.comp2021.clevis.model;

class Group implements Shape {
    private final String name; // store name
    private Shape[] shapeList;

    /** constructor */
    Group(String inName, Shape[] inShapeList){
        name = inName;
        shapeList = inShapeList;
    }

    public Shape[] getShapeList(){ // get the shapeList
        return shapeList;
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
    public void move(float inDx,float inDy){
        for (Shape s: this.getShapeList()){
            s.move(inDx,inDy);
        }
    }

    /** bounding box method */
    public float getLeftBounding(){ // get the Left Bounding of a Group
        float minLeft = this.getShapeList()[0].getLeftBounding();
        for (Shape s: this.getShapeList()){
            minLeft = Math.min(s.getLeftBounding(),minLeft);
        }
        return (minLeft);
    }
    public float getRightBounding(){ // get the Right Bounding of a Group
        float maxRight = this.getShapeList()[0].getRightBounding();
        for (Shape s: this.getShapeList()){
            maxRight = Math.max(s.getRightBounding(),maxRight);
        }
        return (maxRight);
    }
    public float getTopBounding(){ // get the Top Bounding of a Group
        float maxTop = this.getShapeList()[0].getTopBounding();
        for (Shape s: this.getShapeList()){
            maxTop = Math.max(s.getTopBounding(),maxTop);
        }
        return (maxTop);
    }
    public float getBottomBounding(){ // get the Bottom Bounding of a Group
        float minBottom = this.getShapeList()[0].getBottomBounding();
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
