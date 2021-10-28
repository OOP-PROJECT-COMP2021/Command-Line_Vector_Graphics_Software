package hk.edu.polyu.comp.comp2021.clevis.model;

interface Shape{
    public static final double EPS = 1E-6;

    /** get the name */
    String getName();

    /** isIntersected() method */
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

    /** get group state*/
    public int getGroupState();
    public void incGroupState();
    public void decGroupState();

    /** list out information of a shape */
    String listInfo();

}
