package hk.edu.polyu.comp.comp2021.clevis.model;

import org.junit.Test;

import javax.lang.model.element.NestingKind;
import java.util.ArrayList;
import java.util.Arrays;

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
    @Test
    public void testClevisUndo2(){
        Clevis clevis = new Clevis();

        clevis.drawRectangle("RecTestA", 1f,2f,2f,2f);
        clevis.drawRectangle("RecTestB", 0f,2f,2f,4f);
        clevis.drawRectangle("RecTestC", 2f,2f,3f,3f);

        System.out.println(clevis.listAllShape());

//        clevis.pickAndMoveShape(0,2,1,1);
//        System.out.println(clevis.listAllShape());
//        clevis.UndoControl();
//        System.out.println(clevis.listAllShape());
//        clevis.deleteShapeWithName("RecTestC");
//        System.out.println(clevis.listAllShape());
//
//        clevis.UndoControl();
//        System.out.println(clevis.listAllShape());
//        System.out.println(clevis.listAllShape());
//
        String[] shapeList = {"RecTestA","RecTestB"};
        clevis.createGroup("G1",shapeList);
        System.out.println(clevis.listAllShape());

        clevis.unGroup("G1");

        clevis.UndoControl();

        System.out.println(clevis.listAllShape());
//
//
//        System.out.println(clevis.listAllShape());
//
//        clevis.UndoControl();
//        System.out.println(clevis.listAllShape());

//        clevis.drawRectangle("RecTestD", 3f,2f,5f,4f);
//        clevis.UndoControl();
//        clevis.UndoControl();
//        System.out.println(clevis.listAllShape());
//
//        clevis.drawSquare("CircleTestE", 3f,2f,5f);
//        clevis.UndoControl();
//        System.out.println(clevis.listAllShape());

    }
    @Test
    public void testList() {
        ArrayList<String> shapeList = new ArrayList<>();
        shapeList.add("A");
        shapeList.add("B");
        shapeList.add("C");

        String[] outStr = (String[]) shapeList.toArray();
        System.out.println(outStr);

    }

    @Test
    public void testDel() {
        Clevis clevis = new Clevis();

        clevis.drawLine("LineTestA", 1f,2f,2f,2f);
        clevis.drawRectangle("RecTestB", 0f,2f,2f,4f);

        String[] shapeList = {"RecTestB","LineTestA"};
        clevis.createGroup("C",shapeList);

        clevis.drawCircle("CircleTestD", 2f,2f,3f);

        clevis.drawSquare("SquTestE", 0f,2f,4f);

        String[] shapeList2 = {"SquTestE","CircleTestD"};
        clevis.createGroup("F",shapeList2);

        clevis.drawLine("LineTestG", 3f,2f,4f,2f);
        System.out.println("-------original--------");
        System.out.println(clevis.listAllShape());

//        clevis.deleteShapeWithName("F");
//        System.out.println("-------after delete F --------");
//        System.out.println(clevis.listAllShape());
//
//        clevis.deleteShapeWithName("C");
//        System.out.println("-------after delete C --------");
//        System.out.println(clevis.listAllShape());
//
//        System.out.println("-------after undo1--------");
//        clevis.UndoControl();
//        System.out.println(clevis.listAllShape());

        System.out.println("-------after move--------");
        clevis.moveShape("F",1,1);
        System.out.println(clevis.listAllShape());

        System.out.println("-------after undo2--------");
        clevis.UndoControl();
        System.out.println(clevis.listAllShape());
    }
    @Test
    public void testRedo() {
        Clevis clevis = new Clevis();

        clevis.drawLine("LineTestA", 1f,2f,2f,2f);
        clevis.drawRectangle("RecTestB", 0f,2f,2f,4f);

        clevis.deleteShapeWithName("LineTestA");
        clevis.deleteShapeWithName("RecTestB");

        System.out.println("-------original--------");
        System.out.println(clevis.listAllShape());

        System.out.println("-------after undo twice--------");
        clevis.UndoControl();
        clevis.UndoControl();
        System.out.println(clevis.listAllShape());

        System.out.println("-------after redo once--------");
        clevis.RedoControl();
        System.out.println(clevis.listAllShape());

        System.out.println("-------after undo once--------");
        clevis.UndoControl();
        System.out.println(clevis.listAllShape());
    }

}