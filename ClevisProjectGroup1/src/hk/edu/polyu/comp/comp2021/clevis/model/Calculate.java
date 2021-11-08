package hk.edu.polyu.comp.comp2021.clevis.model;

/** calculate */
public class Calculate {

    /** @param i:i
     * @param k:k
     * @return inner product */
    public static double innerProduct (Vec i, Vec k) {
        return (i.getX() * k.getX()) + (i.getY() * k.getY());
    }

    /** @param i:i
     * @param k:k
     * @return outer product */
    public static double outerProduct (Vec i, Vec k) {
        return (i.getX() * k.getY()) - (i.getY() * k.getX());
    }

    /** return vector i - vector k.
     * @param i: Subtracted vector
     * @param k: Subtraction vector
     * @return the result vector */
    public static Vec vectorSubtract (Vec i, Vec k) {
        return new Vec(i.getX() - k.getX(), i.getY() - k.getY());
    }

}
