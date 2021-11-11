package hk.edu.polyu.comp.comp2021.clevis.model;

import org.junit.Test;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

public class TestCircle {
    @Test
    public void testIsInter0() {
        // D=(0.9889108508156,0.4949616573743) C=(0.8797683357478,0.4949616573743)
        // (x - 0.8797683357478)^(2) + (y - 0.4949616573743)^(2) = 0.0119120885953
        // A=(0.5,0.5) B=(0.8682408289923,0.1692827331788)
        // (x - 0.5)^(2) + (y - 0.5)^(2) = 0.2449752187106
        Circle a = new Circle("testCircle0_a",0.87976f, 0.49496f, 0.10914f);
        Circle b = new Circle("testCircle0_b",0.5f, 0.5f, 0.49494f);
        // d - (R - r) < -EPS
        assertFalse(a.isIntersected(b));
    }

    @Test
    public void testIsInter1() {
        Circle a = new Circle("testCircle1_a",1f, 1f, 1f);
        Circle b = new Circle("testCircle1_b",3f, 1f, 1f);
        // abs(d - (R + r)) < EPS
        assertTrue(a.isIntersected(b));
    }

    @Test
    public void testIsInter2() {
        Circle a = new Circle("testCircle2_a",1f, 1f, 1.00893f); // (x - 1)^(2) + (y - 1)^(2) = 1.0179560658241
        Circle b = new Circle("testCircle2_b",3f, 1f, 0.99643f); // (x - 3)^(2) + (y - 1)^(2) = 0.99288130254
        // d + r - R > EPS && d - (R + r) < -EPS
        assertTrue(a.isIntersected(b));
    }

    @Test
    public void testIsInter3() {
        Circle a = new Circle("testCircle3_a",1f, 1f, 1.00893f); // (x - 1)^(2) + (y - 1)^(2) = 1.0179560658241
        Circle b = new Circle("testCircle3_b",2.8f, 1f, 0.76328f);// (x - 2.8)^(2) + (y - 1)^(2) = 0.5825993131972
        // d - (R + r) > EPS
        assertFalse(a.isIntersected(b));
    }

    @Test
    public void testIsInter4() {
        Circle a = new Circle("testCircle4_a",1f, 1f, 1.00893f); // (x - 1)^(2) + (y - 1)^(2) = 1.0179560658241
        Circle b = new Circle("testCircle4_b",1.8f, 1f, 0.20893f); // (x - 1.8)^(2) + (y - 1)^(2) = 0.0436551247014
        // abs(d - (R - r)) < EPS
        assertTrue(a.isIntersected(b));
    }

    @Test
    public void testIsInter5() {
        Circle a = new Circle("testCircle5_a",1f, 1f, 1.00893f); // (x - 1)^(2) + (y - 1)^(2) = 1.0179560658241
        Circle b = new Circle("testCircle5_b",1.6f, 1f, 0.39642f); // (x - 1.6)^(2) + (y - 1)^(2) = 0.1571520195162
        // d - (R - r) < -EPS
        assertFalse(a.isIntersected(b));
    }
}

