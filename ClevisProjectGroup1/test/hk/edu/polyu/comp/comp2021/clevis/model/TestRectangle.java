package hk.edu.polyu.comp.comp2021.clevis.model;

import org.junit.Test;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

public class TestRectangle {
    /** ---------------------Rectangle and Rectangle intersection test-----------------------*/
    @Test
    public void testRectRectInter0() {
        Rectangle a = new Rectangle("RectTestA",0f,3f,4f,3f);
        Rectangle b = new Rectangle("RectTestB",4f,7f,2f,3f);
        assertFalse(a.isIntersected(b));
    }
    @Test
    public void testRectRectInter1() {
        Rectangle a = new Rectangle("RectTestA",0f,3f,4f,3f);
        Rectangle c = new Rectangle("RectTestD",3f,1f,2f,1f);
        assertFalse(a.isIntersected(c));
    }
    @Test
    public void testRectRectInter2() {
        Rectangle a = new Rectangle("RectTestA",0f,3f,4f,3f);
        Rectangle d = new Rectangle("RectTestD",4.00257f,2.99868f,2f,1f);
        assertFalse(a.isIntersected(d));
    }
    @Test
    public void testRectRectInter3() {
        Rectangle a = new Rectangle("RectTestA",0f,3f,4f,3f);
        Rectangle e = new Rectangle("RectTestE",1.28367f,4.80119f,2f,0.8f);
        assertFalse(a.isIntersected(e));
    }
    @Test
    public void testRectRectInter4() {
        Rectangle a = new Rectangle("RectTestA",0f,3f,4f,3f);
        Rectangle f = new Rectangle("RectTestE",1f,2f,2f,1f);
        assertTrue(a.isIntersected(f));
    }
    @Test
    public void testRectRectInter5() {
        Rectangle a = new Rectangle("RectTestA",0f,3f,4f,3f);
        Rectangle g = new Rectangle("RectTestE",3.59914f,1.79601f,0.4f,0.4f);
        assertFalse(a.isIntersected(g));
    }
    @Test
    public void testRectRectInter6() {
        Rectangle a = new Rectangle("RectTestA",0f,3f,4f,3f);
        Rectangle h = new Rectangle("RectTestE",2f,2.99684f,0.6f,0.2f);
        assertTrue(a.isIntersected(h));
    }
    @Test
    public void testRectRectInter7() {
        Rectangle a = new Rectangle("RectTestA",0f,3f,4f,3f);
        Rectangle i = new Rectangle("RectTestI",3.99993f,1.9f,0.5f,0.6f);
        assertFalse(a.isIntersected(i));
    }
    @Test
    public void testRectRectInter8() {
        Rectangle a = new Rectangle("RectTestA",0f,3f,4f,3f);
        Rectangle j = new Rectangle("RectTestJ",0.30006f,3.39966f,0.6f,0.4f);
        assertFalse(a.isIntersected(j));
    }
    @Test
    public void testRectRectInter9() {
        Rectangle a = new Rectangle("RectTestA",0f,3f,4f,3f);
        Rectangle k = new Rectangle("RectTestK",4f,1.95f,0.7f,0.8f);
        assertFalse(a.isIntersected(k));
    }
    @Test
    public void testRectRectInter10() {
        Rectangle a = new Rectangle("RectTestA",0f,3f,4f,3f);
        Rectangle l = new Rectangle("RectTestL",1f,0.4f,1.2f,0.4f);
        assertFalse(a.isIntersected(l));
    }
    @Test
    public void testRectRectInter11() {
        Rectangle a = new Rectangle("RectTestA",0f,3f,4f,3f);
        Rectangle m = new Rectangle("RectTestM",-1.5f,4f,1.5f,1f);
        assertTrue(a.isIntersected(m));
    }
    @Test
    public void testRectRectInter12() {
        Rectangle a = new Rectangle("RectTestA",0f,3f,4f,3f);
        assertTrue(a.isIntersected(a));
    }
}