package hk.edu.polyu.comp.comp2021.clevis.model;

public class Calculate {

    public static double innerProduct (Vec i, Vec k) {
        return (i.getX() * k.getX()) + (i.getY() * k.getY());
    }

    public static double outerProduct (Vec i, Vec k) {
        return (i.getX() * k.getY()) - (i.getY() * k.getX());
    }

    /** return vector i - vector k.*/
    public static Vec vectorSubtract (Vec i, Vec k) {
        return new Vec(i.getX() - k.getX(), i.getY() - k.getY());
    }

}
