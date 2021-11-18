package hk.edu.polyu.comp.comp2021.clevis.model;

import org.junit.Test;

import java.io.IOException;
import static org.junit.Assert.*;

/** Test for the model package */
public class ClevisModelTest {

    @Test
    public void rectangleTest() {
        ClevisModel clevisModel = new ClevisModel();
        clevisModel.drawRectangle("RecTestA",0.0d,2.0d,4d,2d);
        clevisModel.drawRectangle("RecTestB",4.344563454234696d,3.324738463657d,10.3472384987652397d,2.2370138476523d);
        System.out.println(clevisModel.listAllShape());
        try{
            clevisModel.drawRectangle("RecTestA",0.0d,2.0d,4d,2d);
        }
        catch (Exception e){
            System.out.println("Caught!");
        }

        clevisModel.UndoControl();
        clevisModel.UndoControl();
        System.out.println(clevisModel.listAllShape());
        clevisModel.RedoControl();
        System.out.println(clevisModel.listAllShape());
        clevisModel.drawRectangle("RecTestC",0.0d,2.0d,4d,2d);
        System.out.println(clevisModel.listShape("RecTestC"));
    }

    @Test
    public void lineTest() {
        ClevisModel clevisModel = new ClevisModel();
        clevisModel.drawLine("LineTestC",0.0d,2.0d,4d,5d);
        clevisModel.drawLine("LineTestD",4.344563454234696d,3.324738463657d,10.3472384987652397d,2.2370138476523d);
        System.out.println(clevisModel.listAllShape());
        try{
            clevisModel.drawLine("LineTestD",4.344563454234696d,3.324738463657d,10.3472384987652397d,2.2370138476523d);
        }
        catch (Exception e){
            System.out.println("Caught!");
        }

        clevisModel.UndoControl();
        clevisModel.UndoControl();
        System.out.println(clevisModel.listAllShape());
        clevisModel.RedoControl();
        System.out.println(clevisModel.listAllShape());
        clevisModel.drawLine("LineTestE",0.0d,2.0d,4d,5d);
    }

    @Test
    public void circleTest() {
        ClevisModel clevisModel = new ClevisModel();
        clevisModel.drawCircle("CircleTestE",0.0d,2.0d,4d);
        clevisModel.drawCircle("CircleTestF",4.344563454234696d,3.324738463657d,10.3472384987652397d);
        System.out.println(clevisModel.listAllShape());
        try{
            clevisModel.drawCircle("CircleTestF",4.344563454234696d,3.324738463657d,10.3472384987652397d);
        }
        catch (Exception e){
            System.out.println("Caught!");
        }

        clevisModel.UndoControl();
        clevisModel.UndoControl();
        System.out.println(clevisModel.listAllShape());
        clevisModel.RedoControl();
        System.out.println(clevisModel.listAllShape());
        clevisModel.drawCircle("CircleTestG",0.0d,2.0d,4d);
    }

    @Test
    public void squareTest() {
        ClevisModel clevisModel = new ClevisModel();
        clevisModel.drawSquare("SquareTestG",1.0d,2.0d,4d);
        clevisModel.drawSquare("SquareTestH",4.344563454234696d,2.324738463657d,10.3472384987652397d);
        System.out.println(clevisModel.listAllShape());
        try{
            clevisModel.drawSquare("SquareTestH",4.344563454234696d,2.324738463657d,10.3472384987652397d);
        }
        catch (Exception e){
            System.out.println("Caught!");
        }

        clevisModel.UndoControl();
        clevisModel.UndoControl();
        System.out.println(clevisModel.listAllShape());
        clevisModel.RedoControl();
        System.out.println(clevisModel.listAllShape());
        clevisModel.drawSquare("SquareTestI",1.0d,2.0d,4d);
    }

    @Test
    public void drawMulTest() {
        ClevisModel clevisModel = new ClevisModel();
        clevisModel.drawRectangle("RecTestA",0.0d,2.0d,4d,2d);
        clevisModel.drawRectangle("RecTestB",4.344563454234696d,3.324738463657d,10.3472384987652397d,2.2370138476523d);
        clevisModel.drawLine("LineTestC",0.0d,2.0d,4d,5d);
        clevisModel.drawLine("LineTestD",4.344563454234696d,3.324738463657d,10.3472384987652397d,2.2370138476523d);
        clevisModel.drawCircle("CircleTestE",0.0d,2.0d,4d);
        clevisModel.drawCircle("CircleTestF",4.344563454234696d,3.324738463657d,10.3472384987652397d);
        clevisModel.drawSquare("SquareTestG",1.0d,2.0d,4d);
        clevisModel.drawSquare("SquareTestH",4.344563454234696d,2.324738463657d,10.3472384987652397d);
        System.out.println(clevisModel.listAllShape());

        clevisModel.UndoControl();
        clevisModel.UndoControl();
        System.out.println(clevisModel.listAllShape());
        clevisModel.RedoControl();
        clevisModel.RedoControl();
        System.out.println(clevisModel.listAllShape());
    }

    @Test
    public void groupTest() {
        ClevisModel clevisModel = new ClevisModel();
        clevisModel.drawRectangle("RecTestA",0.0d,2.0d,4d,2d);
        clevisModel.drawRectangle("RecTestB",4.344563454234696d,3.324738463657d,10.3472384987652397d,2.2370138476523d);
        String[] shapeListAB = {"RecTestA","RecTestB"};
        clevisModel.createGroup("GAB",shapeListAB);

        try{
            clevisModel.createGroup("GAB",shapeListAB);
        }
        catch (Exception e){
            System.out.println("Caught!");
        }

        clevisModel.drawLine("LineTestC",0.0d,2.0d,4d,5d);
        clevisModel.drawLine("LineTestD",4.344563454234696d,3.324738463657d,10.3472384987652397d,2.2370138476523d);
        String[] shapeListGAB_CD = {"GAB","LineTestC","LineTestD"};
        clevisModel.createGroup("G_GAB_CD",shapeListGAB_CD);

        clevisModel.drawCircle("CircleTestE",0.0d,2.0d,4d);
        clevisModel.drawCircle("CircleTestF",4.344563454234696d,3.324738463657d,10.3472384987652397d);
        clevisModel.drawSquare("SquareTestG",1.0d,2.0d,4d);
        String[] shapeListEFG = {"CircleTestE","CircleTestF","SquareTestG"};
        clevisModel.createGroup("G_EFG",shapeListEFG);

        clevisModel.drawSquare("SquareTestH",4.344563454234696d,2.324738463657d,10.3472384987652397d);

        System.out.println(clevisModel.listAllShape());

        clevisModel.UndoControl();
        clevisModel.UndoControl();
        System.out.println(clevisModel.listAllShape());
        clevisModel.RedoControl();
        clevisModel.RedoControl();
        System.out.println(clevisModel.listAllShape());
        String[] shapeListEFGABCD = {"G_EFG","G_GAB_CD"};
        clevisModel.createGroup("shapeListEFGABCD",shapeListEFGABCD);
    }

    @Test
    public void unGroupTest() {
        ClevisModel clevisModel = new ClevisModel();

        clevisModel.drawRectangle("RecTestA2",0.0d,2.0d,4d,2d);
        clevisModel.drawRectangle("RecTestB2",4.344563454234696d,3.324738463657d,10.3472384987652397d,2.2370138476523d);
        String[] shapeListAB2 = {"RecTestA2","RecTestB2"};
        clevisModel.createGroup("GAB2",shapeListAB2);

        clevisModel.drawRectangle("RecTestA",0.0d,2.0d,4d,2d);
        clevisModel.drawRectangle("RecTestB",4.344563454234696d,3.324738463657d,10.3472384987652397d,2.2370138476523d);
        String[] shapeListAB = {"RecTestA","RecTestB"};
        clevisModel.createGroup("GAB",shapeListAB);

        clevisModel.drawLine("LineTestC",0.0d,2.0d,4d,5d);
        clevisModel.drawLine("LineTestD",4.344563454234696d,3.324738463657d,10.3472384987652397d,2.2370138476523d);
        String[] shapeListGAB_CD = {"GAB","LineTestC","LineTestD"};
        clevisModel.createGroup("G_GAB_CD",shapeListGAB_CD);
        System.out.println(clevisModel.listAllShape());

        clevisModel.unGroup("G_GAB_CD");
        clevisModel.unGroup("GAB");
        System.out.println(clevisModel.listAllShape());

        try{
            clevisModel.unGroup("G_ABCD");
        }
        catch (Exception e){
            System.out.println("Caught!");
        }

        clevisModel.UndoControl();
        clevisModel.UndoControl();
        System.out.println(clevisModel.listAllShape());
        clevisModel.RedoControl();
        clevisModel.RedoControl();
        System.out.println(clevisModel.listAllShape());
        clevisModel.unGroup("GAB2");
    }

    @Test
    public void deleteTest() {
        ClevisModel clevisModel = new ClevisModel();
        clevisModel.drawRectangle("RecTestA",0.0d,2.0d,4d,2d);
        clevisModel.drawRectangle("RecTestB",4.344563454234696d,3.324738463657d,10.3472384987652397d,2.2370138476523d);
        String[] shapeListAB = {"RecTestA","RecTestB"};
        clevisModel.createGroup("GAB",shapeListAB);

        clevisModel.drawLine("LineTestC",0.0d,2.0d,4d,5d);
        clevisModel.drawLine("LineTestD",4.344563454234696d,3.324738463657d,10.3472384987652397d,2.2370138476523d);
        String[] shapeListGAB_CD = {"GAB","LineTestC","LineTestD"};
        clevisModel.createGroup("G_GAB_CD",shapeListGAB_CD);
        System.out.println();
        clevisModel.drawSquare("SquareTestH",4.344563454234696d,2.324738463657d,10.3472384987652397d);

        clevisModel.drawCircle("CircleTestE",0.0d,2.0d,4d);
        clevisModel.drawCircle("CircleTestF",4.344563454234696d,3.324738463657d,10.3472384987652397d);
        clevisModel.drawSquare("SquareTestG",1.0d,2.0d,4d);
        String[] shapeListEFG = {"CircleTestE","CircleTestF","SquareTestG"};
        clevisModel.createGroup("G_EFG",shapeListEFG);
        System.out.println(clevisModel.listAllShape());

        try{
            clevisModel.deleteShapeWithName("RecTestA");
        }
        catch (Exception e){
            System.out.println("Caught!");
        }

        try{
            clevisModel.deleteShapeWithName("Rec");
        }
        catch (Exception e){
            System.out.println("Caught!");
        }

        clevisModel.deleteShapeWithName("SquareTestH");
        clevisModel.deleteShapeWithName("G_GAB_CD");
        System.out.println(clevisModel.listAllShape());

        clevisModel.UndoControl();
        clevisModel.UndoControl();
        System.out.println(clevisModel.listAllShape());
        clevisModel.RedoControl();
        clevisModel.RedoControl();
        System.out.println(clevisModel.listAllShape());
        clevisModel.deleteShapeWithName("G_EFG");
    }

    @Test
    public void boundingboxTest() {
        ClevisModel clevisModel = new ClevisModel();
        clevisModel.drawRectangle("RecTestA",0.0d,2.0d,4d,2d);
        clevisModel.drawRectangle("RecTestB",4.344563454234696d,3.324738463657d,10.3472384987652397d,2.2370138476523d);
        String[] shapeListAB = {"RecTestA","RecTestB"};
        clevisModel.createGroup("GAB",shapeListAB);

        clevisModel.drawLine("LineTestC",0.0d,2.0d,4d,5d);
        clevisModel.drawLine("LineTestD",4.344563454234696d,3.324738463657d,10.3472384987652397d,2.2370138476523d);
        String[] shapeListGAB_CD = {"GAB","LineTestC","LineTestD"};
        clevisModel.createGroup("G_GAB_CD",shapeListGAB_CD);

        clevisModel.drawCircle("CircleTestE",0.0d,2.0d,4d);
        clevisModel.drawCircle("CircleTestF",4.344563454234696d,3.324738463657d,10.3472384987652397d);
        clevisModel.drawSquare("SquareTestG",1.0d,2.0d,4d);
        String[] shapeListEFG = {"CircleTestE","CircleTestF","SquareTestG"};
        clevisModel.createGroup("G_EFG",shapeListEFG);

        clevisModel.drawSquare("SquareTestH",4.344563454234696d,2.324738463657d,10.3472384987652397d);

        try{
            clevisModel.createBoundingBox("RecTestA");
        }
        catch (Exception e){
            System.out.println("Caught!");
        }

        System.out.println(clevisModel.createBoundingBox("G_GAB_CD"));
        System.out.println(clevisModel.createBoundingBox("G_EFG"));
        System.out.println(clevisModel.createBoundingBox("SquareTestH"));
    }

    @Test
    public void moveTest() {
        ClevisModel clevisModel = new ClevisModel();
        clevisModel.drawRectangle("RecTestA",0.0d,2.0d,4d,2d);
        clevisModel.drawRectangle("RecTestB",4.344563454234696d,3.324738463657d,10.3472384987652397d,2.2370138476523d);
        String[] shapeListAB = {"RecTestA","RecTestB"};
        clevisModel.createGroup("GAB",shapeListAB);

        clevisModel.drawLine("LineTestC",0.0d,2.0d,4d,5d);
        clevisModel.drawLine("LineTestD",4.344563454234696d,3.324738463657d,10.3472384987652397d,2.2370138476523d);
        String[] shapeListGAB_CD = {"GAB","LineTestC","LineTestD"};
        clevisModel.createGroup("G_GAB_CD",shapeListGAB_CD);

        clevisModel.drawSquare("SquareTestH",4.344563454234696d,2.324738463657d,10.3472384987652397d);

        clevisModel.drawCircle("CircleTestE",0.0d,2.0d,4d);
        clevisModel.drawCircle("CircleTestF",4.344563454234696d,3.324738463657d,10.3472384987652397d);
        clevisModel.drawSquare("SquareTestG",1.0d,2.0d,4d);
        String[] shapeListEFG = {"CircleTestE","CircleTestF","SquareTestG"};
        clevisModel.createGroup("G_EFG",shapeListEFG);
        System.out.println(clevisModel.listAllShape());

        try{
            clevisModel.moveShape("RecTestA",1d,2d);
        }
        catch (Exception e){
            System.out.println("Caught!");
        }

        clevisModel.moveShape("G_GAB_CD",1d,1d);
        clevisModel.moveShape("G_EFG",1d,1d);
        clevisModel.moveShape("SquareTestH",1d,1d);
        System.out.println(clevisModel.listAllShape());

        clevisModel.UndoControl();
        clevisModel.UndoControl();
        System.out.println(clevisModel.listAllShape());
        clevisModel.RedoControl();
        clevisModel.RedoControl();
        System.out.println(clevisModel.listAllShape());
        clevisModel.moveShape("SquareTestH",1d,1d);

    }

    @Test
    public void pickAndMoveTest() {
        ClevisModel clevisModel = new ClevisModel();
        clevisModel.drawRectangle("RecTestA",0.0d,2.0d,4d,2d);
        clevisModel.drawRectangle("RecTestB",4.344563454234696d,3.324738463657d,10.3472384987652397d,2.2370138476523d);
        String[] shapeListAB = {"RecTestA","RecTestB"};
        clevisModel.createGroup("GAB",shapeListAB);

        clevisModel.drawLine("LineTestC",0.0d,2.0d,4d,5d);
        clevisModel.drawLine("LineTestD",4.344563454234696d,3.324738463657d,10.3472384987652397d,2.2370138476523d);
        String[] shapeListGAB_CD = {"GAB","LineTestC","LineTestD"};
        clevisModel.createGroup("G_GAB_CD",shapeListGAB_CD);

        clevisModel.drawSquare("SquareTestH",4.344563454234696d,2.324738463657d,10.3472384987652397d);

        clevisModel.drawCircle("CircleTestE",0.0d,2.0d,4d);
        clevisModel.drawCircle("CircleTestF",4.344563454234696d,3.324738463657d,10.3472384987652397d);
        clevisModel.drawSquare("SquareTestG",1.0d,2.0d,4d);
        String[] shapeListEFG = {"CircleTestE","CircleTestF","SquareTestG"};
        clevisModel.createGroup("G_EFG",shapeListEFG);
        System.out.println(clevisModel.listAllShape());

        try{
            clevisModel.pickAndMoveShape(100d,200d,1d,2d);
        }
        catch (Exception e){
            System.out.println("Caught!");
        }

        clevisModel.pickAndMoveShape(4.344563454234696d,2.324738463657d,1,1);
        System.out.println(clevisModel.listAllShape());
        clevisModel.pickAndMoveShape(1,2,1,1);
        System.out.println(clevisModel.listAllShape());

        clevisModel.UndoControl();
        clevisModel.UndoControl();
        System.out.println(clevisModel.listAllShape());
        clevisModel.RedoControl();
        clevisModel.RedoControl();
        System.out.println(clevisModel.listAllShape());

        clevisModel.pickAndMoveShape(4,5,1,1);

        clevisModel.pickAndMoveShape(1,7,1,1);

    }

    @Test
    public void intersectTest() {
        ClevisModel clevisModel = new ClevisModel();
        clevisModel.drawRectangle("RecTestA",0.0d,2.0d,4d,2d);
        clevisModel.drawRectangle("RecTestB",4.344563454234696d,3.324738463657d,10.3472384987652397d,2.2370138476523d);

        clevisModel.drawLine("LineTestC",0.0d,2.0d,4d,5d);

        clevisModel.drawLine("LineTestD",4.344563454234696d,3.324738463657d,10.3472384987652397d,2.2370138476523d);

        clevisModel.drawSquare("SquareTestH",4.344563454234696d,2.324738463657d,10.3472384987652397d);

        clevisModel.drawCircle("CircleTestE",0.0d,2.0d,4d);
        clevisModel.drawCircle("CircleTestF",4.344563454234696d,3.324738463657d,10.3472384987652397d);

        clevisModel.drawSquare("SquareTestG",1.0d,2.0d,4d);

        System.out.println(clevisModel.isIntersected("RecTestA","RecTestB"));
        System.out.println(clevisModel.isIntersected("RecTestA","LineTestC"));
        System.out.println(clevisModel.isIntersected("RecTestB","CircleTestE"));
        System.out.println(clevisModel.isIntersected("RecTestB","SquareTestG"));

        System.out.println(clevisModel.isIntersected("LineTestC","RecTestB"));
        System.out.println(clevisModel.isIntersected("LineTestC","LineTestD"));
        System.out.println(clevisModel.isIntersected("LineTestC","SquareTestH"));
        System.out.println(clevisModel.isIntersected("LineTestC","CircleTestF"));

        System.out.println(clevisModel.isIntersected("CircleTestE","CircleTestF"));
        System.out.println(clevisModel.isIntersected("CircleTestE","RecTestA"));
        System.out.println(clevisModel.isIntersected("CircleTestF","LineTestD"));
        System.out.println(clevisModel.isIntersected("CircleTestF","SquareTestG"));

        System.out.println(clevisModel.isIntersected("SquareTestH","SquareTestG"));
        System.out.println(clevisModel.isIntersected("SquareTestH","RecTestB"));
        System.out.println(clevisModel.isIntersected("SquareTestG","CircleTestE"));
        System.out.println(clevisModel.isIntersected("SquareTestG","LineTestD"));

        String[] shapeListAB = {"RecTestA","RecTestB"};
        clevisModel.createGroup("GAB",shapeListAB);

        System.out.println(clevisModel.isIntersected("GAB","LineTestC"));
        System.out.println(clevisModel.isIntersected("LineTestC","GAB"));

        System.out.println(clevisModel.isIntersected("GAB","CircleTestE"));
        System.out.println(clevisModel.isIntersected("CircleTestE","GAB"));

        String[] shapeListGAB_CD = {"GAB","LineTestC","LineTestD"};
        clevisModel.createGroup("G_GAB_CD",shapeListGAB_CD);

        String[] shapeListEFG = {"CircleTestE","CircleTestF","SquareTestG"};
        clevisModel.createGroup("G_EFG",shapeListEFG);

        try{
            clevisModel.isIntersected("LineTestC","CircleTestE");
        }
        catch (Exception e){
            System.out.println("Caught!");
        }

        System.out.println(clevisModel.isIntersected("G_GAB_CD","G_EFG"));

    }

    @Test
    public void pickTest() {
        ClevisModel clevisModel = new ClevisModel();
        clevisModel.drawRectangle("A",100,100,100,200);
        System.out.println(clevisModel.listAllShape());
        clevisModel.pickAndMoveShape(100,110,10,10);
        System.out.println(clevisModel.listAllShape());

    }
    @Test
    public void groupAncestorTest() {
        ClevisModel clevisModel = new ClevisModel();
        clevisModel.drawRectangle("RectTestA",0d,3d,4d,3d);
        clevisModel.drawLine("LineTestB", 4d,5d,6d,2d);
        clevisModel.drawCircle("CircleTestC",2d,5d,1d);
        clevisModel.drawSquare("SquareTestD",6f,4f,1f);
        clevisModel.drawLine("LineTestE", 5d,5d,7d,5d);
        clevisModel.drawCircle("CircleTestF",5d,0d,2d);
        clevisModel.drawRectangle("RectTestG", 6d,1d,2d,1d);

        String[] shapeListABCD = {"RectTestA","LineTestB","CircleTestC","SquareTestD"};
        clevisModel.createGroup("G1",shapeListABCD);

        String[] shapeListEF = {"LineTestE","CircleTestF"};
        clevisModel.createGroup("G2",shapeListEF);

        String[] shapeListG12 = {"G1","G2"};
        clevisModel.createGroup("G12",shapeListG12);


        String[] shapeListG12G = {"G12","RectTestG"};
        clevisModel.createGroup("Ancestor",shapeListG12G);

        for (Shape shape: clevisModel.getShapeLevel()){
            System.out.println(shape.getAncestor().getName());
        }
        assertEquals("Ancestor",clevisModel.getShape("G1").getAncestor().getName());
        assertEquals("Ancestor",clevisModel.getShape("G2").getAncestor().getName());
        assertEquals("Ancestor",clevisModel.getShape("G12").getAncestor().getName());
        assertEquals("Ancestor",clevisModel.getShape("Ancestor").getAncestor().getName());

    }

}