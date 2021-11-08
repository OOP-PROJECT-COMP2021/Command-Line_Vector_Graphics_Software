package hk.edu.polyu.comp.comp2021.clevis.model;

interface Shape{
    public static final double EPS = 1E-6;

    /** get the name */
    String getName();

    /** LinkedListDeque methods*/
    Shape getParent();
    void setParent(Shape father);
    Shape getAncester();
    Shape getLeft();
    Shape getRight();
    void setLeft(Shape l);
    void setRight(Shape r);
    void pointToMe();
    void removeRefer();
    /** isIntersected() method */
    //boolean isIntersected(Shape other); // check Shape is intersected with other Line
    boolean isIntersected(Line other); // check Shape is intersected with other Line
    boolean isIntersected(Rectangle other); // check Shape is intersected with other Rectangle(and its subclass Square)
    boolean isIntersected(Circle other); // check Shape is intersected with other Circle
    boolean isIntersected(Group other); // check Shape is intersected with other Group

    /** bounding box method */
    double getLeftBounding(); // get the Left Bounding of a shape
    double getRightBounding(); // get the Right Bounding of a shape
    double getTopBounding(); // get the Top Bounding of a shape
    double getBottomBounding(); // get the Bottom Bounding of a shape

    /** move a shape method*/
    void move(double dX, double dY);

    /** list out information of a shape */
    String listInfo();
}
