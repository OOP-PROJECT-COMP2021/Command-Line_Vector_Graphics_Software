package hk.edu.polyu.comp.comp2021.clevis.model;

import org.junit.Test;

public class ClevisTest {

    @Test
    public void testClevisConstructor(){
        Clevis clevis = new Clevis();


        clevis.drawLine("LineTestA", 0f,4f,4f,0f);
        clevis.drawLine("LineTestB", 0f,2f,2f,4f);
        clevis.drawSquare("SquareTestC",1f,4f,2f);

        String[] shapeList = {"LineTestA","LineTestB"};
        clevis.createGroup("GroupTestD",shapeList);
        clevis.drawCircle("E",0,0,1);


        //clevis.drawCircle("CircleTestE",0f,0f,1);

//        String[] shapeList2 = {"GroupTestC","SquareTestD","CircleTestE"};
//        clevis.createGroup("GroupTestF",shapeList2);

        clevis.listAllShape();
        clevis.pickAndMoveShape(1,0,1,1);
        clevis.listAllShape();

        //clevis.listAllShape();
        //System.out.println(clevis.isIntersected(g1,b));
        //assert true;
    }

}