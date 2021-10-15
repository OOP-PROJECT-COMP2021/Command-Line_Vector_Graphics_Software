package hk.edu.polyu.comp.comp2021.clevis.model;

class Group implements Shape {
    private final String name; // store name
    private Shape shapeA;
    private Shape shapeB;
    private Shape[] shapeList;

    /** constructor */
    Group(String inName, Shape inShapeA, Shape inShapeB, Shape[] inShapeList){
        name = inName;
        shapeA = inShapeA;
        shapeB = inShapeB;
        shapeList = inShapeList;
    }

    /** method for get the name */
    public String getName(){return name;} // get the name

    /** check Group is intersected with other Line */
    public boolean isIntersected(Line other){
        for (Shape s: shapeList){
            if (s.isIntersected(other)){return true;}
        }
        return true;
    }

    /** check Group is intersected with other Rectangle(and its subclass Square) */
    public boolean isIntersected(Rectangle other){
        for (Shape s: shapeList){
            if (s.isIntersected(other)){return true;}
        }
        return true;
    }

    /** check Group is intersected with other Circle */
    public boolean isIntersected(Circle other){
        for (Shape s: shapeList){
            if (s.isIntersected(other)){return true;}
        }
        return true;
    }

    /** bounding box method */
    public float getLeftBounding(){ // get the Left Bounding of a Group
        float minLeft = shapeList[0].getLeftBounding();
        for (Shape s: shapeList){
            minLeft = Math.min(s.getLeftBounding(),minLeft);
        }
        return (minLeft);
    }
    public float getRightBounding(){ // get the Right Bounding of a Group
        float maxRight = shapeList[0].getRightBounding();
        for (Shape s: shapeList){
            maxRight = Math.max(s.getRightBounding(),maxRight);
        }
        return (maxRight);
    }
    public float getTopBounding(){ // get the Top Bounding of a Group
        float maxTop = shapeList[0].getTopBounding();
        for (Shape s: shapeList){
            maxTop = Math.max(s.getTopBounding(),maxTop);
        }
        return (maxTop);
    }
    public float getBottomBounding(){ // get the Bottom Bounding of a Group
        float minBottom = shapeList[0].getBottomBounding();
        for (Shape s: shapeList){
            minBottom = Math.min(s.getBottomBounding(),minBottom);
        }
        return (minBottom);
    }


    /** list out information of a shape */
    public String listInfo(){
        return ("[Group]: Name: "+getName()+"; Contained shapes:"+shapeA.getName()+","+shapeB.getName());
    }
}
