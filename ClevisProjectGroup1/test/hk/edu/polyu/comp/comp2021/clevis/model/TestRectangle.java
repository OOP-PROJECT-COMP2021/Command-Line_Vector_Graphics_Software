package hk.edu.polyu.comp.comp2021.clevis.model;

import org.junit.Test;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

public class TestRectangle {
    /** ---------------------Rectangle and Rectangle intersection test-----------------------*/
    @Test
    public void testRectRectInter0() {
        Rectangle a = new Rectangle("RectTestA",0,3d,4d,3d);
        Rectangle b = new Rectangle("RectTestB",4d,7d,2d,3d);
        assertFalse(a.isIntersected(b));
    }
    @Test
    public void testRectRectInter1() {
        Rectangle a = new Rectangle("RectTestA",0d,3d,4d,3d);
        Rectangle c = new Rectangle("RectTestD",3d,1d,2d,1d);
        assertFalse(a.isIntersected(c));
    }
    @Test
    public void testRectRectInter2() {
        Rectangle a = new Rectangle("RectTestA",0d,3d,4d,3d);
        Rectangle d = new Rectangle("RectTestD",4.00257d,2.99868d,2d,1d);
        assertFalse(a.isIntersected(d));
    }
    @Test
    public void testRectRectInter3() {
        Rectangle a = new Rectangle("RectTestA",0d,3d,4d,3d);
        Rectangle e = new Rectangle("RectTestE",1.28367d,4.80119d,2d,0.8d);
        assertFalse(a.isIntersected(e));
    }
    @Test
    public void testRectRectInter4() {
        Rectangle a = new Rectangle("RectTestA",0d,3d,4d,3d);
        Rectangle f = new Rectangle("RectTestE",1d,2d,2d,1d);
        assertTrue(a.isIntersected(f));
    }
    @Test
    public void testRectRectInter5() {
        Rectangle a = new Rectangle("RectTestA",0d,3d,4d,3d);
        Rectangle g = new Rectangle("RectTestE",3.59914d,1.79601d,0.4d,0.4d);
        assertFalse(a.isIntersected(g));
    }
    @Test
    public void testRectRectInter6() {
        Rectangle a = new Rectangle("RectTestA",0d,3d,4d,3d);
        Rectangle h = new Rectangle("RectTestE",2d,2.99684d,0.6d,0.2d);
        assertTrue(a.isIntersected(h));
    }
    @Test
    public void testRectRectInter7() {
        Rectangle a = new Rectangle("RectTestA",0d,3d,4d,3d);
        Rectangle i = new Rectangle("RectTestI",3.99993d,1.9d,0.5d,0.6d);
        assertFalse(a.isIntersected(i));
    }
    @Test
    public void testRectRectInter8() {
        Rectangle a = new Rectangle("RectTestA",0d,3d,4d,3d);
        Rectangle j = new Rectangle("RectTestJ",0.30006d,3.39966d,0.6d,0.4d);
        assertFalse(a.isIntersected(j));
    }
    @Test
    public void testRectRectInter9() {
        Rectangle a = new Rectangle("RectTestA",0d,3d,4d,3d);
        Rectangle k = new Rectangle("RectTestK",4d,1.95d,0.7d,0.8d);
        assertFalse(a.isIntersected(k));
    }
    @Test
    public void testRectRectInter10() {
        Rectangle a = new Rectangle("RectTestA",0d,3d,4d,3d);
        Rectangle l = new Rectangle("RectTestL",1d,0.4d,1.2d,0.4d);
        assertFalse(a.isIntersected(l));
    }
    @Test
    public void testRectRectInter11() {
        Rectangle a = new Rectangle("RectTestA",0d,3d,4d,3d);
        Rectangle m = new Rectangle("RectTestM",-1.5d,4d,1.5d,1d);
        assertTrue(a.isIntersected(m));
    }
    @Test
    public void testRectRectInter12() {
        Rectangle a = new Rectangle("RectTestA",0d,3d,4d,3d);
        assertTrue(a.isIntersected(a));
    }
}