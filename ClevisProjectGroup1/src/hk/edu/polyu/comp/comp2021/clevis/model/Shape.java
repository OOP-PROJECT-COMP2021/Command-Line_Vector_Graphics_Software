package hk.edu.polyu.comp.comp2021.clevis.model;

interface Shape{
    public static final float EPS = 1E-5f;

    /** get the name */
    String getName();

    /** isIntersected() method */
    boolean isIntersected(Line other); // check Shape is intersected with other Line
    boolean isIntersected(Rectangle other); // check Shape is intersected with other Rectangle(and its subclass Square)
    boolean isIntersected(Circle other); // check Shape is intersected with other Circle
    boolean isIntersected(Group other); // check Shape is intersected with other Group

    /** bounding box method */
    float getLeftBounding(); // get the Left Bounding of a shape
    float getRightBounding(); // get the Right Bounding of a shape
    float getTopBounding(); // get the Top Bounding of a shape
    float getBottomBounding(); // get the Bottom Bounding of a shape

    /** move a shape method*/
    void move(float dX, float dY);

    /** list out information of a shape */
    String listInfo();

}
