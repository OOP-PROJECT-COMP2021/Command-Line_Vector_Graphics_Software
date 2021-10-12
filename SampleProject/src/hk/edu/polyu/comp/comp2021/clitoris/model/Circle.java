package hk.edu.polyu.comp.comp2021.clitoris.model;

public class Circle {
    private Vec center;
    private float radius;

    public Circle(float a, float b, float r) {
        center = new Vec(a, b);
        radius = r;
    }

    public Vec getCenter() {
        return center;
    }

    public float getRadius() {
        return radius;
    }
}
