package hk.edu.polyu.comp.comp2021.clevis.model;

import org.junit.Test;

public class ClevisTest {

    @Test
    public void testClevisConstructor(){
        Clevis clevis = new Clevis();

        Rectangle a = new Rectangle("RectTestA",0f,3f,4f,3f);
        Line b = new Line("LineTestB", 4f,5f,6f,2f);
        Circle c = new Circle("CircleTestC",2f,5f,1f);
        Square d = new Square("SquareTestD",6f,4f,1f);
        Line e = new Line("LineTestE", 5f,5f,7f,5f);
        Circle f = new Circle("CirclrTestF",5f,0f,2);
        Rectangle g = new Rectangle("RectTestG", 6f,1f,2f,1f);

        Shape[] shapeList = {a,b,c,d};
        Group g1 = new Group("GroupTest", shapeList);

        Shape[] shapeList1 = {e,f};
        Group g2 = new Group("GroupTest2", shapeList1);

        Shape[] shapeList2 = {g1,g2};
        Group g3 = new Group("GroupTest3", shapeList2);

        Shape[] shapeList3 = {g1,g2,g3};
        Group g4 = new Group("GroupTest4", shapeList3);

        System.out.println(clevis.isIntersected(g1,b));
        //assert true;
    }

}