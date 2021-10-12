package hk.edu.polyu.comp.comp2021.clitoris.model;
import static hk.edu.polyu.comp.comp2021.clitoris.model.Calculate.*;
import static hk.edu.polyu.comp.comp2021.clitoris.model.Calculate.vectorSubtract;
import static java.lang.Math.*;

public class Line {
    public static final float EPS = 1E-5f;
    private Vec a;
    private Vec b;

    public Vec getA() {
        return a;
    }

    public Vec getB() {
        return b;
    }

    // a -> x1, b -> y1, c -> x2, d -> y2
    public Line(float a, float b, float c, float d) {
        this.a = new Vec(a, b);
        this.b = new Vec(c, d);
    }

    public boolean isIntersected(Line other) {
        // rapid exclusion
        if (max(a.x, b.x) < min(other.getA().x, other.getB().x) || max(a.y, b.y) < min(other.getA().y, other.getB().y)
                || max(other.getA().x, other.getB().x) < min(a.x, b.x) || max(other.getA().y, other.getB().y) < min(a.y, b.y)) {
            return false;
        }
        if (outerProduct(vectorSubtract(b, a), vectorSubtract(other.getA(),a))
                * outerProduct(vectorSubtract(b, a), vectorSubtract(other.getB(), a)) > 0 + EPS ||
        outerProduct(vectorSubtract(other.getB(), other.getA()), vectorSubtract(a, other.getA()))
                    * outerProduct(vectorSubtract(other.getB(), other.getA()), vectorSubtract(b, other.getA())) > 0 + EPS) {
            return false;
        }
        return true;
    }

    public boolean isIntersected(Circle c) {
        // case 1: if l_oa = r or l_ob = r, then intersect
        if (abs(vectorSubtract(a, c.getCenter()).getDis() - c.getRadius()) < EPS
                || abs(vectorSubtract(b, c.getCenter()).getDis() - c.getRadius()) < EPS) {
            return true;
        }

        // case 2: if l_oa < r and l_ob < r, then not intersect
        if (vectorSubtract(a, c.getCenter()).getDis() - c.getRadius() < 0 - EPS
                && vectorSubtract(b, c.getCenter()).getDis() - c.getRadius() < 0 - EPS) { // we see numeric very close to 0 as 0
            return false;
        }

        // if l_oa > r and l_ob < r, then intersect
        if (
                ((vectorSubtract(a, c.getCenter()).getDis() - c.getRadius() > 0 + EPS)
                        && (vectorSubtract(b, c.getCenter()).getDis() - c.getRadius() < 0 - EPS))
                ||
                ((vectorSubtract(a, c.getCenter()).getDis() - c.getRadius() < 0 - EPS)
                        && (vectorSubtract(b, c.getCenter()).getDis() - c.getRadius() > 0 + EPS))
        ) {
            return true;
        }

        // if innerproduct(bo, ba) < 0 or innerproduct(ao, ab) < 0, then not intersect
        if (innerProduct(vectorSubtract(c.getCenter(), b), vectorSubtract(a, b)) < 0 - EPS
                || innerProduct(vectorSubtract(c.getCenter(), a), vectorSubtract(b, a)) < 0 - EPS) {
            return false;
        }

        // if outerproduct(oa, ob) - (|ab| * r) <= 0, then intersect
        if (abs(outerProduct(vectorSubtract(a, c.getCenter()), vectorSubtract(b, c.getCenter())))
                - (vectorSubtract(a, b).getDis() * c.getRadius()) < 0 + EPS)  { // <= 0
            return true;
        } else {
            return false;
        }
    }

    public static void main(String[] args) {
//        Line a = new Line(1f, 0f, 4f, 3f);
//        Line b = new Line(3f, 2f, 5f, 0f);
//        System.out.println(a.isIntersected(b)); // should return true
//
//        Line c = new Line(2f, 1f, 4.54455f, 2.90361f);
//        Line d = new Line(5f, 1f, 3.76700f, 2.25936f);
//        System.out.println(c.isIntersected(d)); // should return false
//
//        Line e = new Line(2f, 1f, 5.58439f, 1.84772f);
//        Line f = new Line(7.76492f, 3.50908f, 5.58570f, 1.84792f);
//        System.out.println(e.isIntersected(f)); // should return false
//
//        // Test Parallel
//        Line g = new Line(2f, 1f, 5.58439f, 1.84772f);
//        Line h = new Line(4.75361f, 2.53426f, 7.25039f, 3.12476f);
//        System.out.println(g.isIntersected(h)); // should return false

        // Two line segments are collinear and overlap.
//        Line i = new Line(0.4f, 0.2f, 1f, 0.4f);
//        Line j = new Line(0.98853f, 0.39617f, 1.02908f, 0.40942f); // 6.5494454E-10 == 0
//        System.out.println(i.isIntersected(j)); // should return true

        // Test the case 1 in isIntersected
        Line a = new Line(1.58108f, 0.52904f, 1.81107f, 0.58522f);// // C=(1.5810852441577,0.529044492787), E=(1.8110792856131,0.5852268110847)
        Circle b = new Circle(1.4f, 0.6f, 0.41134f); // r = 0.4113446561837, r^2 = 0.1692044261709
        System.out.println(a.isIntersected(b)); // should return true. EPS = E-5 -> return true, EPS = E-6 return false.

        // Test the case 2 in isIntersected
        Line c = new Line(1.58108f, 0.52904f, 1.80967f, 0.56514f); //  C=(1.5810852441577,0.529044492787)
        Circle d = new Circle(1.4f, 0.6f, 0.41134f);
        System.out.println(c.isIntersected(d)); // should return false;

        // Test the case 3 in isIntersected
        Line e = new Line();
    }
}

class Vec {
    float x;
    float y;
    Vec (float a, float b) {
        x = a;
        y = b;
    }

    /** Gets a Vector's Magnitude.*/
    public float getDis() {
        return sqrtNewton(x * x + y * y);
    }

    private float sqrtNewton(float n) {
        final float D = 1E-7f;
        float x = 1;
        while (true) {
            float nx = (x + n / x) / 2;
            if (abs(x - nx) < D) break;
            x = nx;
        }
        return x;
    }
}

