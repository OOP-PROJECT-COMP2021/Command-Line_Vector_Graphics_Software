package hk.edu.polyu.comp.comp2021.clevis.model;

public class Calculate {

    public static float innerProduct (Vec i, Vec k) {
        return (i.x * k.x) + (i.y * k.y);
    }

    public static float outerProduct (Vec i, Vec k) {
        return (i.x * k.y) - (i.y * k.x);
    }

    /** return vector i - vector k.*/
    public static Vec vectorSubtract (Vec i, Vec k) {
        return new Vec(i.x - k.x, i.y - k.y);
    }

}
