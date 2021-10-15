package hk.edu.polyu.comp.comp2021.clevis.model;

class Group implements Shape {
    private final String name; // store name
    private Shape shapeA;
    private Shape shapeB;

    /** constructor */
    Group(String inName, Shape inShapeA, Shape inShapeB){
        name = inName;
        shapeA = inShapeA;
        shapeB = inShapeB;
    }

    /** method for get the name */
    public String getName(){return name;} // get the name

    /** check Group is intersected with other Line */
    public boolean isIntersected(Line other){
        if (shapeA.isIntersected(other) || shapeB.isIntersected(other)){return true;}
        return true;
    }

    /** check Group is intersected with other Rectangle(and its subclass Square) */
    public boolean isIntersected(Rectangle other){
        if (shapeA.isIntersected(other) || shapeB.isIntersected(other)){return true;}
        return true;
    }

    /** check Group is intersected with other Circle */
    public boolean isIntersected(Circle other){
        if (shapeA.isIntersected(other) || shapeB.isIntersected(other)){return true;}
        return true;
    }

    /** bounding box method */
    public float getLeftBounding(){ // get the Left Bounding of a Group
        return (Math.min(shapeA.getLeftBounding(), shapeB.getLeftBounding()));
    }
    public float getRightBounding(){ // get the Right Bounding of a Group
        return (Math.max(shapeA.getRightBounding(), shapeB.getRightBounding()));
    }
    public float getTopBounding(){ // get the Top Bounding of a Group
        return (Math.max(shapeA.getTopBounding(), shapeB.getTopBounding()));
    }
    public float getBottomBounding(){ // get the Bottom Bounding of a Group
        return (Math.min(shapeA.getBottomBounding(), shapeB.getBottomBounding()));
    }


    /** list out information of a shape */
    public String listInfo(){
        return ("[Group]: Name: "+getName()+"; Contained shapes:"+shapeA.getName()+","+shapeB.getName());
    }
}
