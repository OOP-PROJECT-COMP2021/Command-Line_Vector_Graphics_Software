package hk.edu.polyu.comp.comp2021.clevis.model;

import org.junit.Test;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

public class TestGroup {
    @Test
    public void testGroupConstructor0() {
        Rectangle a = new Rectangle("RectTestA",0d,3d,4d,3d);
        Line b = new Line("LineTestB", 4d,5d,6d,2d);
        Circle c = new Circle("CircleTestC",2d,5d,1d);
        Square d = new Square("SquareTestD",6d,4d,1d);
        Shape[] shapeList = {a,b,c,d};
        Group g1 = new Group("GroupTest", shapeList);
        System.out.println(g1.listInfo());
    }

    @Test
    public void testGroupLineIntersected0() {
        Rectangle a = new Rectangle("RectTestA",0d,3d,4d,3d);
        Line b = new Line("LineTestB", 4d,5d,6d,2d);
        Circle c = new Circle("CircleTestC",2d,5d,1d);
        Square d = new Square("SquareTestD",6d,4d,1d);
        Line e = new Line("LineTestE", 5d,5d,7d,5d);
        Circle f = new Circle("CircleTestF",5d,0d,2d);
        Rectangle g = new Rectangle("RectTestG", 6d,1d,2d,1d);

        Shape[] shapeList = {a,b,c,d};
        Group g1 = new Group("GroupTest", shapeList);

        Shape[] shapeList1 = {e,f};
        Group g2 = new Group("GroupTest2", shapeList1);

        Shape[] shapeList2 = {g1,g2};
        Group g3 = new Group("GroupTest3", shapeList2);

        Shape[] shapeList3 = {g1,g2,g3};
        Group g4 = new Group("GroupTest4", shapeList3);

        System.out.println(g4.listInfo());
        assertTrue(g1.isIntersected(e));
        assertTrue(g1.isIntersected(g2));
        assertTrue(g3.isIntersected(g));
    }
}
