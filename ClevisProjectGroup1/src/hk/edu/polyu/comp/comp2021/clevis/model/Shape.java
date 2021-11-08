package hk.edu.polyu.comp.comp2021.clevis.model;

interface Shape{
    public static final double EPS = 1E-6;

    /** get the name */
    String getName();

    String getSHAPE_TYPE();

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
    boolean isIntersected(Shape other); // check Shape is intersected with other Shape

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
