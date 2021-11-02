package hk.edu.polyu.comp.comp2021.clevis.model;

import org.junit.Test;

public class ClevisTest {

    @Test
    public void testClevisConstructor(){
        Clevis clevis = new Clevis();


        clevis.drawLine("LineTestA", 0f,4f,4f,0f);
        clevis.drawLine("LineTestB", 0f,2f,2f,4f);
        String[] shapeList = {"LineTestA","LineTestB"};
        clevis.createGroup("GroupTestC",shapeList);
        clevis.drawSquare("SquareTestD",1f,4f,2f);
        clevis.drawCircle("CircleTestE",0f,0f,1);

        System.out.println(clevis.listShape("SquareTestD"));
        clevis.pickAndMoveShape(1,3,1,1);
        System.out.println(clevis.listShape("SquareTestD"));

        clevis.listAllShape();
        //System.out.println(clevis.isIntersected(g1,b));
        //assert true;
    }

}