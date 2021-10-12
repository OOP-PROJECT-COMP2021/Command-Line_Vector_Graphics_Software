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

        // case 3: if l_oa > r and l_ob < r, then intersect
        if (
                ((vectorSubtract(a, c.getCenter()).getDis() - c.getRadius() > 0 + EPS)
                        && (vectorSubtract(b, c.getCenter()).getDis() - c.getRadius() < 0 - EPS))
                ||
                ((vectorSubtract(a, c.getCenter()).getDis() - c.getRadius() < 0 - EPS)
                        && (vectorSubtract(b, c.getCenter()).getDis() - c.getRadius() > 0 + EPS))
        ) {
            return true;
        }

        // case 4: if innerproduct(bo, ba) < 0 or innerproduct(ao, ab) < 0, then not intersect
        if (innerProduct(vectorSubtract(c.getCenter(), b), vectorSubtract(a, b)) < 0 - EPS
                || innerProduct(vectorSubtract(c.getCenter(), a), vectorSubtract(b, a)) < 0 - EPS) { // 可以排除OCE共线且CE在圆外的情况.
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
        Line a = new Line(1f, 0f, 4f, 3f);
        Line b = new Line(3f, 2f, 5f, 0f);
        System.out.println(a.isIntersected(b)); // should return true

        Line c = new Line(2f, 1f, 4.54455f, 2.90361f);
        Line d = new Line(5f, 1f, 3.76700f, 2.25936f);
        System.out.println(c.isIntersected(d)); // should return false

        Line e = new Line(2f, 1f, 5.58439f, 1.84772f);
        Line f = new Line(7.76492f, 3.50908f, 5.58570f, 1.84792f);
        System.out.println(e.isIntersected(f)); // should return false

        // Two lines
        Line g = new Line(2f, 1f, 5.58439f, 1.84772f);
        Line h = new Line(4.75361f, 2.53426f, 7.25039f, 3.12476f);
        System.out.println(g.isIntersected(h)); // should return false

        // Two lines are collinear and overlap.
        Line i = new Line(0.4f, 0.2f, 1f, 0.4f);
        Line j = new Line(0.98853f, 0.39617f, 1.02908f, 0.40942f); // 6.5494454E-10 == 0
        System.out.println(i.isIntersected(j)); // should return true

        Line k = new Line(5f,1.5f, 3.87050f,2.98744f); // A=(5,1.5) B=(3.8705049251443,2.9874409321848)
        Line l = new Line(4.62082f,2.00062f, 3.66338f,3.26148f); // C=(4.6208296041805,2.0006273179821) D=(3.6633891610346,3.2614881548002)
        System.out.println(k.isIntersected(l)); // should return false

        System.out.println("---------------");

        // ------------------------------------------------------------

        // Test the case 1 in isIntersected (C is inside the circle, while E is on the circle.
        Line m = new Line(1.58108f, 0.52904f, 1.81107f, 0.58522f);// // C=(1.5810852441577,0.529044492787), E=(1.8110792856131,0.5852268110847)
        Circle n = new Circle(1.4f, 0.6f, 0.41134f); // r = 0.4113446561837, r^2 = 0.1692044261709
        System.out.println(m.isIntersected(n)); // should return true. EPS = E-5 -> return true, EPS = E-6 return false.

        // Test the case 2 in isIntersected (C is inside the circle, while E is also inside the circle and very close to the edge of the circle.
        Line o = new Line(1.58108f, 0.52904f, 1.80967f, 0.56514f); //  C=(1.5810852441577,0.529044492787), E=(1.8096701976124, 0.5651425422479)
        Circle p = new Circle(1.4f, 0.6f, 0.41134f);
        System.out.println(o.isIntersected(p)); // should return false;

        // Test the case 3 in isIntersected (C is inside and very close to the edge of the circle, while E is outside the circle.
        Line q = new Line(5.63272f, 2.11447f, 5.8f, 2.2f); // C=(5.6327232539627,2.1144774811828) E=(5.8,2.2)
        Circle r = new Circle(5f, 1.5f, 0.88282f); // r^2 = 0.7793879360436; 圆心(5,1.5), r = 0.8828295056485
        System.out.println(q.isIntersected(r)); // should return true

        // Test the case 4 in isIntersected -> (C is outside and very close to the edge, while E is outside, and Angle OCE is an obtuse angle.
        Line s = new Line(5.70881f, 2.02690f, 5.8f, 2.2f); //C=(5.7088162554534,2.0269028675004) E(5.8,2.2)
        Circle t = new Circle(5f, 1.5f, 0.88282f);
        System.out.println(s.isIntersected(t)); // should return false

        // Test the case 4 in isIntersected -> (E is outside and very close to the edge, while C is outsdie, and Angle OEC is an obtuse angle.
        Line u = new Line(6.14052f, 1.67203f, 5.81448f, 1.84154f); // C=(6.1405259654056,1.6720304489816) E=(5.8144842166463,1.8415481023994)
        Circle v = new Circle(5f, 1.5f, 0.88282f);
        System.out.println(u.isIntersected(v)); // should return false

        // Test the case 4 in isIntersected (OCE three points collinear, C outside and very close to edge, E outside)
        Line w = new Line(5.75173f, 1.96447f, 6.50728f, 2.43129f); // C=(5.751737320268,1.9644721307078), E=(6.5072850254938,2.4312985646709)
        Circle x = new Circle(5f, 1.5f, 0.88282f);
        System.out.println(w.isIntersected(x)); // should return false

        // Test the case 5 in isIntersected (return true case)
        Line y = new Line(5.86287f, 1.99221f, 5.00002f, 2.38373f); // C=(5.8628787616036,1.9922184547504), E=(5.0000271200151,2.3837344989143)
        Circle z = new Circle(5f, 1.5f, 0.88282f);
        System.out.println(y.isIntersected(z)); // should return true

        // Test the case 5 in isIntersected (return false case)
        Line z1 = new Line(5.62228f,2.17108f, 4.67439f,2.68657f);// C=(5.6222897599635,2.1710883379339) E=(4.6743966199911,2.6865796067336)
        Circle z2 = new Circle(5f, 1.5f, 0.88282f);
        System.out.println(z1.isIntersected(z2));
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

