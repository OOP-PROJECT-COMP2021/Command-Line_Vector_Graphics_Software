package hk.edu.polyu.comp.comp2021.clevis.model;

import org.junit.Test;

import javax.lang.model.element.NestingKind;
import java.util.ArrayList;
import java.util.Arrays;

public class ClevisTest {

//    @Test
//    public void testClevisConstructor(){
//        Clevis clevis = new Clevis();
//
//        clevis.drawLine("LineTestA", 0d,4d,4d,0d);
//        clevis.drawLine("LineTestB", 0d,2d,2d,4d);
//        clevis.drawSquare("SquareTestC",1d,4d,2d);
//
//        String[] shapeList = {"LineTestA","LineTestB"};
//        clevis.createGroup("GroupTestD",shapeList);
//        clevis.drawCircle("E",0,0,1);
//
//        clevis.listAllShape();
//        clevis.pickAndMoveShape(1,0,1,1);
//        clevis.listAllShape();
//
//    }
//    @Test
//    public void testClevisUndo2(){
//        Clevis clevis = new Clevis();
//
//        clevis.drawRectangle("RecTestA", 1d,2d,2d,2d);
//        clevis.drawRectangle("RecTestB", 0d,2d,2d,4d);
//        clevis.drawRectangle("RecTestC", 2d,2d,3d,3d);
//
//        System.out.println(clevis.listAllShape());
//
//        String[] shapeList = {"RecTestA","RecTestB"};
//        clevis.createGroup("G1",shapeList);
//        System.out.println(clevis.listAllShape());
//
//        clevis.unGroup("G1");
//
//        clevis.UndoControl();
//
//        System.out.println(clevis.listAllShape());
//
//    }
//
//    @Test
//    public void testDel() {
//        Clevis clevis = new Clevis();
//
//        clevis.drawLine("LineTestA", 1d,2d,2d,2d);
//        clevis.drawRectangle("RecTestB", 0d,2d,2d,4d);
//
//        String[] shapeList = {"RecTestB","LineTestA"};
//        clevis.createGroup("C",shapeList);
//
//        clevis.drawCircle("CircleTestD", 2d,2d,3d);
//
//        clevis.drawSquare("SquTestE", 0d,2d,4d);
//
//        String[] shapeList2 = {"SquTestE","CircleTestD"};
//        clevis.createGroup("F",shapeList2);
//
//        clevis.drawLine("LineTestG", 3d,2d,4d,2d);
//        System.out.println("-------original--------");
//        System.out.println(clevis.listAllShape());
//
//        System.out.println("-------after move--------");
//        clevis.moveShape("F",1,1);
//        System.out.println(clevis.listAllShape());
//
//        System.out.println("-------after undo2--------");
//        clevis.UndoControl();
//        System.out.println(clevis.listAllShape());
//    }
//    @Test
//    public void testRedo() {
//        Clevis clevis = new Clevis();
//
//        clevis.drawLine("LineTestA", 1d,2d,2d,2d);
//        clevis.drawRectangle("RecTestB", 0d,2d,2d,4d);
//
//        clevis.deleteShapeWithName("LineTestA");
//        clevis.deleteShapeWithName("RecTestB");
//
//        System.out.println("-------original--------");
//        System.out.println(clevis.listAllShape());
//
//        System.out.println("-------after undo twice--------");
//        clevis.UndoControl();
//        clevis.UndoControl();
//        System.out.println(clevis.listAllShape());
//
//        System.out.println("-------after redo once--------");
//        clevis.RedoControl();
//        System.out.println(clevis.listAllShape());
//
//        System.out.println("-------after undo once--------");
//        clevis.UndoControl();
//        System.out.println(clevis.listAllShape());
//    }

    @Test
    public void rectangleTest() {
        Clevis clevis = new Clevis();
        clevis.drawRectangle("RecTestA",0.0d,2.0d,4d,2d);
        clevis.drawRectangle("RecTestB",4.344563454234696d,3.324738463657d,10.3472384987652397d,2.2370138476523d);
        System.out.println(clevis.listAllShape());

        clevis.UndoControl();
        clevis.UndoControl();
        System.out.println(clevis.listAllShape());
        clevis.RedoControl();
        System.out.println(clevis.listAllShape());
        clevis.drawRectangle("RecTestC",0.0d,2.0d,4d,2d);
        System.out.println(clevis.listShape("RecTestC"));


    }

    @Test
    public void lineTest() {
        Clevis clevis = new Clevis();
        clevis.drawLine("LineTestC",0.0d,2.0d,4d,5d);
        clevis.drawLine("LineTestD",4.344563454234696d,3.324738463657d,10.3472384987652397d,2.2370138476523d);
        System.out.println(clevis.listAllShape());

        clevis.UndoControl();
        clevis.UndoControl();
        System.out.println(clevis.listAllShape());
        clevis.RedoControl();
        System.out.println(clevis.listAllShape());
        clevis.drawLine("LineTestE",0.0d,2.0d,4d,5d);

    }

    @Test
    public void circleTest() {
        Clevis clevis = new Clevis();
        clevis.drawCircle("CircleTestE",0.0d,2.0d,4d);
        clevis.drawCircle("CircleTestF",4.344563454234696d,3.324738463657d,10.3472384987652397d);
        System.out.println(clevis.listAllShape());

        clevis.UndoControl();
        clevis.UndoControl();
        System.out.println(clevis.listAllShape());
        clevis.RedoControl();
        System.out.println(clevis.listAllShape());
        clevis.drawCircle("CircleTestG",0.0d,2.0d,4d);

    }

    @Test
    public void squareTest() {
        Clevis clevis = new Clevis();
        clevis.drawSquare("SquareTestG",1.0d,2.0d,4d);
        clevis.drawSquare("SquareTestH",4.344563454234696d,2.324738463657d,10.3472384987652397d);
        System.out.println(clevis.listAllShape());

        clevis.UndoControl();
        clevis.UndoControl();
        System.out.println(clevis.listAllShape());
        clevis.RedoControl();
        System.out.println(clevis.listAllShape());
        clevis.drawSquare("SquareTestI",1.0d,2.0d,4d);

    }

    @Test
    public void drawMulTest() {
        Clevis clevis = new Clevis();
        clevis.drawRectangle("RecTestA",0.0d,2.0d,4d,2d);
        clevis.drawRectangle("RecTestB",4.344563454234696d,3.324738463657d,10.3472384987652397d,2.2370138476523d);
        clevis.drawLine("LineTestC",0.0d,2.0d,4d,5d);
        clevis.drawLine("LineTestD",4.344563454234696d,3.324738463657d,10.3472384987652397d,2.2370138476523d);
        clevis.drawCircle("CircleTestE",0.0d,2.0d,4d);
        clevis.drawCircle("CircleTestF",4.344563454234696d,3.324738463657d,10.3472384987652397d);
        clevis.drawSquare("SquareTestG",1.0d,2.0d,4d);
        clevis.drawSquare("SquareTestH",4.344563454234696d,2.324738463657d,10.3472384987652397d);
        System.out.println(clevis.listAllShape());

        clevis.UndoControl();
        clevis.UndoControl();
        System.out.println(clevis.listAllShape());
        clevis.RedoControl();
        clevis.RedoControl();
        System.out.println(clevis.listAllShape());
    }

    @Test
    public void groupTest() {
        Clevis clevis = new Clevis();
        clevis.drawRectangle("RecTestA",0.0d,2.0d,4d,2d);
        clevis.drawRectangle("RecTestB",4.344563454234696d,3.324738463657d,10.3472384987652397d,2.2370138476523d);
        String[] shapeListAB = {"RecTestA","RecTestB"};
        clevis.createGroup("GAB",shapeListAB);

        clevis.drawLine("LineTestC",0.0d,2.0d,4d,5d);
        clevis.drawLine("LineTestD",4.344563454234696d,3.324738463657d,10.3472384987652397d,2.2370138476523d);
        String[] shapeListGAB_CD = {"GAB","LineTestC","LineTestD"};
        clevis.createGroup("G_GAB_CD",shapeListGAB_CD);

        clevis.drawCircle("CircleTestE",0.0d,2.0d,4d);
        clevis.drawCircle("CircleTestF",4.344563454234696d,3.324738463657d,10.3472384987652397d);
        clevis.drawSquare("SquareTestG",1.0d,2.0d,4d);
        String[] shapeListEFG = {"CircleTestE","CircleTestF","SquareTestG"};
        clevis.createGroup("G_EFG",shapeListEFG);

        clevis.drawSquare("SquareTestH",4.344563454234696d,2.324738463657d,10.3472384987652397d);

        System.out.println(clevis.listAllShape());

        clevis.UndoControl();
        clevis.UndoControl();
        System.out.println(clevis.listAllShape());
        clevis.RedoControl();
        clevis.RedoControl();
        System.out.println(clevis.listAllShape());
        String[] shapeListEFGABCD = {"G_EFG","G_GAB_CD"};
        clevis.createGroup("shapeListEFGABCD",shapeListEFGABCD);
    }

    @Test
    public void unGroupTest() {
        Clevis clevis = new Clevis();

        clevis.drawRectangle("RecTestA2",0.0d,2.0d,4d,2d);
        clevis.drawRectangle("RecTestB2",4.344563454234696d,3.324738463657d,10.3472384987652397d,2.2370138476523d);
        String[] shapeListAB2 = {"RecTestA2","RecTestB2"};
        clevis.createGroup("GAB2",shapeListAB2);

        clevis.drawRectangle("RecTestA",0.0d,2.0d,4d,2d);
        clevis.drawRectangle("RecTestB",4.344563454234696d,3.324738463657d,10.3472384987652397d,2.2370138476523d);
        String[] shapeListAB = {"RecTestA","RecTestB"};
        clevis.createGroup("GAB",shapeListAB);

        clevis.drawLine("LineTestC",0.0d,2.0d,4d,5d);
        clevis.drawLine("LineTestD",4.344563454234696d,3.324738463657d,10.3472384987652397d,2.2370138476523d);
        String[] shapeListGAB_CD = {"GAB","LineTestC","LineTestD"};
        clevis.createGroup("G_GAB_CD",shapeListGAB_CD);
        System.out.println(clevis.listAllShape());

        clevis.unGroup("G_GAB_CD");
        clevis.unGroup("GAB");
        System.out.println(clevis.listAllShape());

        clevis.UndoControl();
        clevis.UndoControl();
        System.out.println(clevis.listAllShape());
        clevis.RedoControl();
        clevis.RedoControl();
        System.out.println(clevis.listAllShape());
        clevis.unGroup("GAB2");


    }

    @Test
    public void deleteTest() {
        Clevis clevis = new Clevis();
        clevis.drawRectangle("RecTestA",0.0d,2.0d,4d,2d);
        clevis.drawRectangle("RecTestB",4.344563454234696d,3.324738463657d,10.3472384987652397d,2.2370138476523d);
        String[] shapeListAB = {"RecTestA","RecTestB"};
        clevis.createGroup("GAB",shapeListAB);

        clevis.drawLine("LineTestC",0.0d,2.0d,4d,5d);
        clevis.drawLine("LineTestD",4.344563454234696d,3.324738463657d,10.3472384987652397d,2.2370138476523d);
        String[] shapeListGAB_CD = {"GAB","LineTestC","LineTestD"};
        clevis.createGroup("G_GAB_CD",shapeListGAB_CD);

        clevis.drawSquare("SquareTestH",4.344563454234696d,2.324738463657d,10.3472384987652397d);

        clevis.drawCircle("CircleTestE",0.0d,2.0d,4d);
        clevis.drawCircle("CircleTestF",4.344563454234696d,3.324738463657d,10.3472384987652397d);
        clevis.drawSquare("SquareTestG",1.0d,2.0d,4d);
        String[] shapeListEFG = {"CircleTestE","CircleTestF","SquareTestG"};
        clevis.createGroup("G_EFG",shapeListEFG);
        System.out.println(clevis.listAllShape());

        clevis.deleteShapeWithName("SquareTestH");
        clevis.deleteShapeWithName("G_GAB_CD");
        System.out.println(clevis.listAllShape());

        clevis.UndoControl();
        clevis.UndoControl();
        System.out.println(clevis.listAllShape());
        clevis.RedoControl();
        clevis.RedoControl();
        System.out.println(clevis.listAllShape());
        clevis.deleteShapeWithName("G_EFG");


    }

    @Test
    public void boundingboxTest() {
        Clevis clevis = new Clevis();
        clevis.drawRectangle("RecTestA",0.0d,2.0d,4d,2d);
        clevis.drawRectangle("RecTestB",4.344563454234696d,3.324738463657d,10.3472384987652397d,2.2370138476523d);
        String[] shapeListAB = {"RecTestA","RecTestB"};
        clevis.createGroup("GAB",shapeListAB);

        clevis.drawLine("LineTestC",0.0d,2.0d,4d,5d);
        clevis.drawLine("LineTestD",4.344563454234696d,3.324738463657d,10.3472384987652397d,2.2370138476523d);
        String[] shapeListGAB_CD = {"GAB","LineTestC","LineTestD"};
        clevis.createGroup("G_GAB_CD",shapeListGAB_CD);

        clevis.drawCircle("CircleTestE",0.0d,2.0d,4d);
        clevis.drawCircle("CircleTestF",4.344563454234696d,3.324738463657d,10.3472384987652397d);
        clevis.drawSquare("SquareTestG",1.0d,2.0d,4d);
        String[] shapeListEFG = {"CircleTestE","CircleTestF","SquareTestG"};
        clevis.createGroup("G_EFG",shapeListEFG);

        clevis.drawSquare("SquareTestH",4.344563454234696d,2.324738463657d,10.3472384987652397d);
        System.out.println(clevis.createBoundingBox("G_GAB_CD"));
        System.out.println(clevis.createBoundingBox("G_EFG"));
        System.out.println(clevis.createBoundingBox("SquareTestH"));
    }

    @Test
    public void moveTest() {
        Clevis clevis = new Clevis();
        clevis.drawRectangle("RecTestA",0.0d,2.0d,4d,2d);
        clevis.drawRectangle("RecTestB",4.344563454234696d,3.324738463657d,10.3472384987652397d,2.2370138476523d);
        String[] shapeListAB = {"RecTestA","RecTestB"};
        clevis.createGroup("GAB",shapeListAB);

        clevis.drawLine("LineTestC",0.0d,2.0d,4d,5d);
        clevis.drawLine("LineTestD",4.344563454234696d,3.324738463657d,10.3472384987652397d,2.2370138476523d);
        String[] shapeListGAB_CD = {"GAB","LineTestC","LineTestD"};
        clevis.createGroup("G_GAB_CD",shapeListGAB_CD);

        clevis.drawSquare("SquareTestH",4.344563454234696d,2.324738463657d,10.3472384987652397d);

        clevis.drawCircle("CircleTestE",0.0d,2.0d,4d);
        clevis.drawCircle("CircleTestF",4.344563454234696d,3.324738463657d,10.3472384987652397d);
        clevis.drawSquare("SquareTestG",1.0d,2.0d,4d);
        String[] shapeListEFG = {"CircleTestE","CircleTestF","SquareTestG"};
        clevis.createGroup("G_EFG",shapeListEFG);
        System.out.println(clevis.listAllShape());

        clevis.moveShape("G_GAB_CD",1d,1d);
        clevis.moveShape("G_EFG",1d,1d);
        clevis.moveShape("SquareTestH",1d,1d);
        System.out.println(clevis.listAllShape());

        clevis.UndoControl();
        clevis.UndoControl();
        System.out.println(clevis.listAllShape());
        clevis.RedoControl();
        clevis.RedoControl();
        System.out.println(clevis.listAllShape());
        clevis.moveShape("SquareTestH",1d,1d);

    }

    @Test
    public void pickAndMoveTest() {
        Clevis clevis = new Clevis();
        clevis.drawRectangle("RecTestA",0.0d,2.0d,4d,2d);
        clevis.drawRectangle("RecTestB",4.344563454234696d,3.324738463657d,10.3472384987652397d,2.2370138476523d);
        String[] shapeListAB = {"RecTestA","RecTestB"};
        clevis.createGroup("GAB",shapeListAB);

        clevis.drawLine("LineTestC",0.0d,2.0d,4d,5d);
        clevis.drawLine("LineTestD",4.344563454234696d,3.324738463657d,10.3472384987652397d,2.2370138476523d);
        String[] shapeListGAB_CD = {"GAB","LineTestC","LineTestD"};
        clevis.createGroup("G_GAB_CD",shapeListGAB_CD);

        clevis.drawSquare("SquareTestH",4.344563454234696d,2.324738463657d,10.3472384987652397d);

        clevis.drawCircle("CircleTestE",0.0d,2.0d,4d);
        clevis.drawCircle("CircleTestF",4.344563454234696d,3.324738463657d,10.3472384987652397d);
        clevis.drawSquare("SquareTestG",1.0d,2.0d,4d);
        String[] shapeListEFG = {"CircleTestE","CircleTestF","SquareTestG"};
        clevis.createGroup("G_EFG",shapeListEFG);
        System.out.println(clevis.listAllShape());

        clevis.pickAndMoveShape(4.344563454234696d,2.324738463657d,1,1);
        System.out.println(clevis.listAllShape());
        clevis.pickAndMoveShape(1,2,1,1);
        System.out.println(clevis.listAllShape());

        clevis.UndoControl();
        clevis.UndoControl();
        System.out.println(clevis.listAllShape());
        clevis.RedoControl();
        clevis.RedoControl();
        System.out.println(clevis.listAllShape());

        clevis.pickAndMoveShape(4,5,1,1);

        clevis.pickAndMoveShape(1,2,1,1);
        clevis.pickAndMoveShape(1,7,1,1);


    }

    @Test
    public void intersectTest() {
        Clevis clevis = new Clevis();
        clevis.drawRectangle("RecTestA",0.0d,2.0d,4d,2d);
        clevis.drawRectangle("RecTestB",4.344563454234696d,3.324738463657d,10.3472384987652397d,2.2370138476523d);

        clevis.drawLine("LineTestC",0.0d,2.0d,4d,5d);

        clevis.drawLine("LineTestD",4.344563454234696d,3.324738463657d,10.3472384987652397d,2.2370138476523d);

        clevis.drawSquare("SquareTestH",4.344563454234696d,2.324738463657d,10.3472384987652397d);

        clevis.drawCircle("CircleTestE",0.0d,2.0d,4d);
        clevis.drawCircle("CircleTestF",4.344563454234696d,3.324738463657d,10.3472384987652397d);

        clevis.drawSquare("SquareTestG",1.0d,2.0d,4d);

        System.out.println(clevis.isIntersected("RecTestA","RecTestB"));
        System.out.println(clevis.isIntersected("RecTestA","LineTestC"));
        System.out.println(clevis.isIntersected("RecTestB","CircleTestE"));
        System.out.println(clevis.isIntersected("RecTestB","SquareTestG"));

        System.out.println(clevis.isIntersected("LineTestC","RecTestB"));
        System.out.println(clevis.isIntersected("LineTestC","LineTestD"));
        System.out.println(clevis.isIntersected("LineTestC","SquareTestH"));
        System.out.println(clevis.isIntersected("LineTestC","CircleTestF"));

        System.out.println(clevis.isIntersected("CircleTestE","CircleTestF"));
        System.out.println(clevis.isIntersected("CircleTestE","RecTestA"));
        System.out.println(clevis.isIntersected("CircleTestF","LineTestD"));
        System.out.println(clevis.isIntersected("CircleTestF","SquareTestG"));

        System.out.println(clevis.isIntersected("SquareTestH","SquareTestG"));
        System.out.println(clevis.isIntersected("SquareTestH","RecTestB"));
        System.out.println(clevis.isIntersected("SquareTestG","CircleTestE"));
        System.out.println(clevis.isIntersected("SquareTestG","LineTestD"));


        String[] shapeListAB = {"RecTestA","RecTestB"};
        clevis.createGroup("GAB",shapeListAB);

        System.out.println(clevis.isIntersected("GAB","LineTestC"));
        System.out.println(clevis.isIntersected("LineTestC","GAB"));

        System.out.println(clevis.isIntersected("GAB","CircleTestE"));
        System.out.println(clevis.isIntersected("CircleTestE","GAB"));

        String[] shapeListGAB_CD = {"GAB","LineTestC","LineTestD"};
        clevis.createGroup("G_GAB_CD",shapeListGAB_CD);

        String[] shapeListEFG = {"CircleTestE","CircleTestF","SquareTestG"};
        clevis.createGroup("G_EFG",shapeListEFG);

        System.out.println(clevis.isIntersected("G_GAB_CD","G_EFG"));

    }
}