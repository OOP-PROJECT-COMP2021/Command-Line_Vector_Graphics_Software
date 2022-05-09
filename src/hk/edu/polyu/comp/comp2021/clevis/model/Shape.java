package hk.edu.polyu.comp.comp2021.clevis.model;
/** Shape interface */
public interface Shape{
    /** EPS */
    double EPS = 1E-6;

    /** get the name
     * @return name */
    String getName();

    /** get the shape type
     * @return shape type */
    String getSHAPE_TYPE();

    /** LinkedListDeque methods
     * @return parent shape */
    Shape getParent();

    /** set parent
     * @param father: father */
    void setParent(Shape father);

    /** get ancestor
     * @return ancestor*/
    Shape getAncestor();

    /** get left shape
     * @return left shape*/
    Shape getLeft();

    /** get right shape
     * @return right shape*/
    Shape getRight();

    /**  set left shape
     * @param l: l*/
    void setLeft(Shape l);

    /**  set right shape
     * @param r : r*/
    void setRight(Shape r);

    /** let shape points to itself */
    void pointToMe();

    /** remove shape's reference */
    void removeRefer();

    /** isIntersected() method
     * @param other: other Shape to check
     * @return is intersected or not */
    boolean isIntersected(Shape other); // check Shape is intersected with other Line

    /** bounding box method
     * @return double of left */
    double getLeftBounding(); // get the Left Bounding of a shape

    /** bounding box method
     * @return double of right */
    double getRightBounding(); // get the Right Bounding of a shape

    /** bounding box method
     * @return double of top */
    double getTopBounding(); // get the Top Bounding of a shape

    /** bounding box method
     * @return double of bottom */
    double getBottomBounding(); // get the Bottom Bounding of a shape

    /** move a shape method
     * @param dX: move by x
     * @param dY: move by y */
    void move(double dX, double dY);

    /** list out information of a shape
     * @return one list */
    String listInfo();
}
