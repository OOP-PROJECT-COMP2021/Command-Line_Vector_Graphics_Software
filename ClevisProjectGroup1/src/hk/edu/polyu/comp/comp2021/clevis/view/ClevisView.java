package hk.edu.polyu.comp.comp2021.clevis.view;

import hk.edu.polyu.comp.comp2021.clevis.model.Clevis;
import hk.edu.polyu.comp.comp2021.clevis.model.*;
import hk.edu.polyu.comp.comp2021.clevis.model.Rectangle;
import hk.edu.polyu.comp.comp2021.clevis.model.Shape;

import javax.swing.*;
import java.awt.*;

/** the main GUI */
public class ClevisView extends JFrame{
    private Clevis clevis = new Clevis();

    //all component' parameters
    private final int DIALOG_X = 100;
    private final int DIALOG_Y = 100;

    private final int PANEL_X = 30;
    private final int DEF_PANEL_Y = 305;
    private int PANEL_Y = DEF_PANEL_Y;


    private final int PANEL_HEIGHT = 300;
    private final int DRAW_AREA_POS_X = 30;
    private final int DRAW_AREA_POS_Y = 5;
    private final int TWELVE = 12;
    private final int TWO = 2;
    private final int FF = 55;

    private final float DASH_5 = 5.0f;
    private final float LIM_10 = 10.0f;

    private final int ADD330 = 330;
    private final int ADD60 = 60;


    private final int DEF_FRAME_WIDTH = 660;
    private int FRAME_WIDTH = DEF_FRAME_WIDTH;

    private final int DEF_FRAME_HEIGHT = 630;
    private int FRAME_HEIGHT = DEF_FRAME_HEIGHT;

    private final int BUTTON_WIDTH = 100;
    private final int BUTTON_HEIGHT = 50;


    // for main page buttons
    private final int TEXT_AREA_X = 100;
    private final int TEXT_AREA_Y = 10;
    private final int TEXT_AREA_W = 350;
    private final int TEXT_AREA_H = 40;
    private final int LIST_AREA_R = 10;
    private final int LIST_AREA_C = 25;




    private final int FIRST_CO_X = 150;
    private final int SECOND_CO_X = 350;

    private final int FIRST_RO_Y = 70;
    private final int SECOND_RO_Y = 120;
    private final int THIRD_RO_Y = 170;
    private final int FOURTH_RO_Y =220;



    //----
    private final int DIALOG_WIDTH = 500;
    private final int DIALOG_HEIGHT = 500;

    private final int DEF_DRAW_AREA_X = 600;
    private int DRAW_AREA_X = DEF_DRAW_AREA_X;

    private final int DEF_DRAW_AREA_Y = 300;
    private int DRAW_AREA_Y = DEF_DRAW_AREA_Y;

    private final int LIST_Y = 105;
    private final int LIST_WIDTH = 450;
    private final int LIST_HEIGHT = 150;



    private final int LABEL_WIDTH = 80;
    private final int LABEL_HEIGHT = 30;
    private final int NAME_LOCATION_Y = 75;
    private final int P1_LOCATION_Y = 110;
    private final int P2_LOCATION_Y = 145;
    private final int P3_LOCATION_Y = 180;
    private final int P4_LOCATION_Y = 215;

    private final int LOCATION_X = 50;
    private final int RADIO_LOCATION_Y = 30;
    private final int RADIO_WIDTH = 100;
    private final int RADIO_HEIGHT = 50;
    private final int RADIO_LINE_X = 180;
    private final int RADIO_CIR_X = 310;
    private final int RADIO_SQU_X = 440;


    private final int TEXT_WIDTH = 150;
    private final int SHAPE_LIST_TEXT_WIDTH = 250;
    private final int POINT_XY_TEXT_WIDTH = 75;
    private final int POINT_Y_X = 205;
    private final int MOVE_NOTICE_Y = 112;
    private final int MOVE_NOTICE_WIDTH = 350;



    private final int TEXT_HEIGHT = 30;
    private final int TEXT_LOCATION_X = 130;

    private final int BTN_WIDTH = 100;
    private final int BTN_HEIGHT = 25;

    private final int BTN_DRAW_X = 500;
    private final int BTN_DRAW_Y = 175;

    private final int BACK_BTN_LOCATION_Y = 200;
    private final int BACK_BTN_LOCATION_X = 500;

    private final int UNDO_BTN_LOCATION_X = 500;
    private final int REDO_BTN_LOCATION_X = 500;
    private final int UNDO_BTN_LOCATION_Y = 225;
    private final int REDO_BTN_LOCATION_Y = 250;




    private MyCanvas drawArea;
    private Rectangle boundingBoxShape;
    private boolean boundingBoxFlag = false;

    private float[] dash1 = {DASH_5};
    private BasicStroke s = new BasicStroke(1.0f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, LIM_10, dash1, 0.0f);

    private class MyCanvas extends JPanel {
        //重写paint以绘制图形
        @Override
        public void paint(Graphics g) {
            g.setColor(Color.black);
            g.drawRect(0,0,drawArea.getWidth(),drawArea.getHeight());
            g.setColor(Color.lightGray);
            g.drawString("(0,0)",0,TWELVE);
            g.drawString("(0,"+drawArea.getHeight()+")",0,drawArea.getHeight()-TWO);
            g.drawString("("+drawArea.getWidth()+",0)",drawArea.getWidth()-FF,TWELVE);

            for(Shape shape : clevis.getShapeLevel()){

                if (shape.getRightBounding() > DRAW_AREA_X) {
                    DRAW_AREA_X = (int) (shape.getRightBounding());
                    drawArea.setSize(DRAW_AREA_X, DRAW_AREA_Y);
                }
                if (shape.getBottomBounding() > DRAW_AREA_Y) {
                    DRAW_AREA_Y = (int) (shape.getBottomBounding());
                    drawArea.setSize(DRAW_AREA_X, DRAW_AREA_Y);
                }

                g.setColor(Color.red);
                if (shape.getSHAPE_TYPE().equals("REC")) {
                    g.drawRect((int) (((Rectangle)shape).getTopLeftCorner().getX()),
                            (int)((Rectangle)shape).getTopLeftCorner().getY(),
                            (int)(((Rectangle)shape).getWidth()),(int)(((Rectangle)shape).getHeight()));
                    g.drawString(shape.getName(),(int) (((Rectangle)shape).getTopLeftCorner().getX()),
                            (int)((Rectangle)shape).getTopLeftCorner().getY());
                }
                else if (shape.getSHAPE_TYPE().equals("LINE")) {
                    g.drawLine((int) (((Line)shape).getA().getX()),(int)(((Line)shape).getA().getY()),
                            (int)(((Line)shape).getB().getX()),(int)(((Line)shape).getB().getY()));
                    g.drawString(shape.getName(),(int) (((Line)shape).getA().getX()),(int)(((Line)shape).getA().getY()));

                }
                else if (shape.getSHAPE_TYPE().equals("CIR")) {
                    g.drawOval((int) (shape.getLeftBounding()),(int) shape.getTopBounding(),
                            (int)(((Circle)shape).getRadius()*2),(int)(((Circle)shape).getRadius()*2));
                    g.drawString(shape.getName(),(int) (shape.getLeftBounding()),(int) shape.getTopBounding());
                }

                else if (shape.getSHAPE_TYPE().equals("GRP")) {
                    Graphics2D g2d = (Graphics2D) g;
                    g2d.setColor(Color.gray);
                    g2d.setStroke(s);
                    Rectangle grpBound = new BoundingBox(shape);
                    g2d.drawRect((int) (grpBound.getTopLeftCorner().getX()),(int) grpBound.getTopLeftCorner().getY(),
                            (int)(grpBound.getWidth()),(int)(grpBound.getHeight()));
                    g.drawString(shape.getName(),(int) (shape.getRightBounding()),(int) shape.getTopBounding());
                }
            }
            if (boundingBoxFlag) {
                g.setColor(Color.blue);
                g.drawRect((int) (boundingBoxShape.getTopLeftCorner().getX()),
                        (int) boundingBoxShape.getTopLeftCorner().getY(),(int)(boundingBoxShape.getWidth()),
                        (int)(boundingBoxShape.getHeight()));
                boundingBoxFlag = false;
            }
        }
    }

    /** refresh the size of the frame*/
    public void refreshSize() {
        for(Shape shape : clevis.getShapeLevel()) {

            if (shape.getBottomBounding() > DRAW_AREA_Y) {
                FRAME_HEIGHT = (int) shape.getBottomBounding() + ADD330;
                this.setSize(FRAME_WIDTH, FRAME_HEIGHT);
                PANEL_Y = (int) shape.getBottomBounding() + 5;
            }
            if (shape.getRightBounding() > DRAW_AREA_X) {
                FRAME_WIDTH = (int) shape.getRightBounding() + ADD60;
                this.setSize(FRAME_WIDTH, FRAME_HEIGHT);
            }
        }
    }


    /** main function
     * @param args : args*/
    public static void main(String[] args) {
        new ClevisView();
    }
    // 初始化

    /** constructor */
    public ClevisView(){
        //主界面
        //this.setPreferredSize(new Dimension(660, 630));
        this.setVisible(true);
        //this.setLocation(DIALOG_X,DIALOG_Y);
        this.setBounds(DIALOG_X,DIALOG_Y,FRAME_WIDTH,FRAME_HEIGHT);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        //pack();
        //container
        Container container = this.getContentPane();

        //绝对布局
        container.setLayout(null);
        //设置背景颜色
        //container.setBackground(Color.gray);

        //创建画布对象
        drawArea = new MyCanvas();
        // 绘图区域
        drawArea.setBounds(DRAW_AREA_POS_X,DRAW_AREA_POS_Y,DRAW_AREA_X,DRAW_AREA_Y);// 必须设置大小及坐标，因为当前layout为null

        drawArea.setVisible(true);


        /** -----------------------------panels on container--------------------------------------------*/
        /** Panel for Main page*/
        JPanel MainContainer = new JPanel();
        MainContainer.setVisible(true);
        MainContainer.setBounds(PANEL_X,PANEL_Y,FRAME_WIDTH,PANEL_HEIGHT);
        MainContainer.setLayout(null);


        JLabel mainLabel = new JLabel();
        mainLabel.setBounds(TEXT_AREA_X,TEXT_AREA_Y,TEXT_AREA_W,TEXT_AREA_H);
        mainLabel.setHorizontalAlignment(SwingConstants.CENTER);
        mainLabel.setText("Welcome to Clevis! Click Draw to start!");

        MainContainer.add(mainLabel,BorderLayout.CENTER);
        container.add(MainContainer);

        container.add(drawArea);
        drawArea.repaint();


        /** Panel for Draw page for all shapes */
        JPanel drawContainer = new JPanel();
        drawContainer.setVisible(false);
        drawContainer.setBounds(PANEL_X,PANEL_Y,FRAME_WIDTH,PANEL_HEIGHT);
        drawContainer.setLayout(null);
        //container.add(drawSelectionContainer);

        /** Panel for Delete page for all shapes */
        JPanel deleteContainer = new JPanel();
        deleteContainer.setVisible(false);
        deleteContainer.setBounds(PANEL_X,PANEL_Y,FRAME_WIDTH,PANEL_HEIGHT);
        deleteContainer.setLayout(null);
        //container.add(drawSelectionContainer);

        /** Panel for Boundingbox page for all shapes */
        JPanel boundingBoxContainer = new JPanel();
        boundingBoxContainer.setVisible(false);
        boundingBoxContainer.setBounds(PANEL_X,PANEL_Y,FRAME_WIDTH,PANEL_HEIGHT);
        boundingBoxContainer.setLayout(null);
        //container.add(drawSelectionContainer);

        /** Panel for groupContainer page for all shapes */
        JPanel groupContainer = new JPanel();
        groupContainer.setVisible(false);
        groupContainer.setBounds(PANEL_X,PANEL_Y,FRAME_WIDTH,PANEL_HEIGHT);
        groupContainer.setLayout(null);
        //container.add(drawSelectionContainer);

        /** Panel for moveContainer page for all shapes */
        JPanel moveContainer = new JPanel();
        moveContainer.setVisible(false);
        moveContainer.setBounds(PANEL_X,PANEL_Y,FRAME_WIDTH,PANEL_HEIGHT);
        moveContainer.setLayout(null);
        //container.add(drawSelectionContainer);

        /** Panel for listContainer page for all shapes */
        JPanel listContainer = new JPanel();
        listContainer.setVisible(false);
        listContainer.setBounds(PANEL_X,PANEL_Y,FRAME_WIDTH,PANEL_HEIGHT);
        listContainer.setLayout(null);
        //container.add(drawSelectionContainer);
        //
        // /** Panel for listContainer page for all shapes */
        JPanel intersectContainer = new JPanel();
        intersectContainer.setVisible(false);
        intersectContainer.setBounds(PANEL_X,PANEL_Y,FRAME_WIDTH,PANEL_HEIGHT);
        intersectContainer.setLayout(null);
        //container.add(drawSelectionContainer);



        /** -----------------------------elements on each page--------------------------------------------*/
        //Button Draw, Quit on MainContainer
        JButton drawButton = new JButton("Draw");
        drawButton.setBounds(FIRST_CO_X,FIRST_RO_Y,BUTTON_WIDTH,BUTTON_HEIGHT);
        MainContainer.add(drawButton);

        JButton groupButton = new JButton("Un/Group");
        groupButton.setBounds(FIRST_CO_X,SECOND_RO_Y,BUTTON_WIDTH,BUTTON_HEIGHT);
        MainContainer.add(groupButton);

        JButton deleteButton = new JButton("Delete");
        deleteButton.setBounds(FIRST_CO_X,THIRD_RO_Y,BUTTON_WIDTH,BUTTON_HEIGHT);
        MainContainer.add(deleteButton);

        JButton boundBoxButton = new JButton("BoundingBox");
        boundBoxButton.setBounds(FIRST_CO_X,FOURTH_RO_Y,BUTTON_WIDTH,BUTTON_HEIGHT);
        MainContainer.add(boundBoxButton);

        JButton moveButton = new JButton("Move");
        moveButton.setBounds(SECOND_CO_X,FIRST_RO_Y,BUTTON_WIDTH,BUTTON_HEIGHT);
        MainContainer.add(moveButton);

        JButton intersectionButton = new JButton("Intersection");
        intersectionButton.setBounds(SECOND_CO_X,SECOND_RO_Y,BUTTON_WIDTH,BUTTON_HEIGHT);
        MainContainer.add(intersectionButton);

        JButton listButton = new JButton("List");
        listButton.setBounds(SECOND_CO_X,THIRD_RO_Y,BUTTON_WIDTH,BUTTON_HEIGHT);
        MainContainer.add(listButton);

        JButton quitButton = new JButton("[Quit]");
        quitButton.setBackground(Color.red);
        quitButton.setBounds(SECOND_CO_X,FOURTH_RO_Y,BUTTON_WIDTH,BUTTON_HEIGHT);
        MainContainer.add(quitButton);

        JButton undoButton = new JButton("[Undo]");
        undoButton.setBounds(UNDO_BTN_LOCATION_X,UNDO_BTN_LOCATION_Y,BTN_WIDTH,BTN_HEIGHT);
        MainContainer.add(undoButton);

        JButton redoButton = new JButton("[Redo]");
        redoButton.setBounds(REDO_BTN_LOCATION_X,REDO_BTN_LOCATION_Y,BTN_WIDTH,BTN_HEIGHT);
        MainContainer.add(redoButton);


        // elements on drawContainer page for all shapes

        // Radio Button
        JRadioButton radioButtonRec = new JRadioButton("Rectangle",true);
        JRadioButton radioButtonLine = new JRadioButton("Line",false);
        JRadioButton radioButtonCircle = new JRadioButton("Circle",false);
        JRadioButton radioButtonSquare = new JRadioButton("Square",false);
        radioButtonRec.setBounds(LOCATION_X,RADIO_LOCATION_Y,RADIO_WIDTH,RADIO_HEIGHT);
        radioButtonLine.setBounds(RADIO_LINE_X,RADIO_LOCATION_Y,RADIO_WIDTH,RADIO_HEIGHT);
        radioButtonCircle.setBounds(RADIO_CIR_X,RADIO_LOCATION_Y,RADIO_WIDTH,RADIO_HEIGHT);
        radioButtonSquare.setBounds(RADIO_SQU_X,RADIO_LOCATION_Y,RADIO_WIDTH,RADIO_HEIGHT);

        ButtonGroup DrawRadioButtonGroup = new ButtonGroup();
        DrawRadioButtonGroup.add(radioButtonRec);
        DrawRadioButtonGroup.add(radioButtonLine);
        DrawRadioButtonGroup.add(radioButtonCircle);
        DrawRadioButtonGroup.add(radioButtonSquare);

        drawContainer.add(radioButtonRec);
        drawContainer.add(radioButtonLine);
        drawContainer.add(radioButtonCircle);
        drawContainer.add(radioButtonSquare);

        // 标签
        JLabel labelName = new JLabel();
        JLabel labelP1 = new JLabel();
        JLabel labelP2 = new JLabel();
        JLabel labelP3 = new JLabel();
        JLabel labelP4 = new JLabel();


        labelName.setText("Name:");
        labelP1.setText("LocationX:");
        labelP2.setText("LocationY:");
        labelP3.setText("Width:");
        labelP4.setText("Height:");

        labelName.setBounds(LOCATION_X,NAME_LOCATION_Y,LABEL_WIDTH,LABEL_HEIGHT);
        labelP1.setBounds(LOCATION_X,P1_LOCATION_Y,LABEL_WIDTH,LABEL_HEIGHT);
        labelP2.setBounds(LOCATION_X,P2_LOCATION_Y,LABEL_WIDTH,LABEL_HEIGHT);
        labelP3.setBounds(LOCATION_X,P3_LOCATION_Y,LABEL_WIDTH,LABEL_HEIGHT);
        labelP4.setBounds(LOCATION_X,P4_LOCATION_Y,LABEL_WIDTH,LABEL_HEIGHT);

        drawContainer.add(labelName);
        drawContainer.add(labelP1);
        drawContainer.add(labelP2);
        drawContainer.add(labelP3);
        drawContainer.add(labelP4);

        // 文本框
        JTextField name = new JTextField("");
        JTextField textFieldP1,textFieldP2,textFieldP3,textFieldP4;
        textFieldP1 = new JTextField("");
        textFieldP2 = new JTextField("");
        textFieldP3 = new JTextField("");
        textFieldP4 = new JTextField("");

        // 设置输入文本框的位置信息
        name.setBounds(TEXT_LOCATION_X,NAME_LOCATION_Y,TEXT_WIDTH,TEXT_HEIGHT);
        textFieldP1.setBounds(TEXT_LOCATION_X,P1_LOCATION_Y,TEXT_WIDTH,TEXT_HEIGHT);
        textFieldP2.setBounds(TEXT_LOCATION_X,P2_LOCATION_Y,TEXT_WIDTH,TEXT_HEIGHT);
        textFieldP3.setBounds(TEXT_LOCATION_X,P3_LOCATION_Y,TEXT_WIDTH,TEXT_HEIGHT);
        textFieldP4.setBounds(TEXT_LOCATION_X,P4_LOCATION_Y,TEXT_WIDTH,TEXT_HEIGHT);
        // 添加输入文本框
        drawContainer.add(name);
        drawContainer.add(textFieldP1);
        drawContainer.add(textFieldP2);
        drawContainer.add(textFieldP3);
        drawContainer.add(textFieldP4);

        // drawShapeButton on the drawContainer page
        JButton drawShapeButton = new JButton("DrawShape");
        drawShapeButton.setBounds(BTN_DRAW_X, BTN_DRAW_Y, BTN_WIDTH, BTN_HEIGHT);
        drawContainer.add(drawShapeButton);


        JButton backDrawButton = new JButton("Back");
        backDrawButton.setBounds(BACK_BTN_LOCATION_X, BACK_BTN_LOCATION_Y, BTN_WIDTH, BTN_HEIGHT);
        drawContainer.add(backDrawButton);


        //-----deleteContainer element--

        JButton deleteShapeButton = new JButton("Delete");
        deleteShapeButton.setBounds(BTN_DRAW_X, BTN_DRAW_Y, BTN_WIDTH, BTN_HEIGHT);
        deleteContainer.add(deleteShapeButton);

        JButton backDeleteButton = new JButton("Back");
        backDeleteButton.setBounds(BACK_BTN_LOCATION_X, BACK_BTN_LOCATION_Y, BTN_WIDTH, BTN_HEIGHT);
        deleteContainer.add(backDeleteButton);


        //-----boundingBoxContainer element--
        JButton boundingBoxButton = new JButton("Bounding");
        boundingBoxButton.setBounds(BTN_DRAW_X, BTN_DRAW_Y, BTN_WIDTH, BTN_HEIGHT);
        boundingBoxContainer.add(boundingBoxButton);

        JButton backBoundingButton = new JButton("Back");
        backBoundingButton.setBounds(BACK_BTN_LOCATION_X, BACK_BTN_LOCATION_Y, BTN_WIDTH, BTN_HEIGHT);
        boundingBoxContainer.add(backBoundingButton);

        //-----groupContainer element--
        JButton createGroupButton = new JButton("Group");
        createGroupButton.setBounds(BTN_DRAW_X, BTN_DRAW_Y, BTN_WIDTH, BTN_HEIGHT);
        groupContainer.add(createGroupButton);

        JButton backGroupButton = new JButton("Back");
        backGroupButton.setBounds(BACK_BTN_LOCATION_X, BACK_BTN_LOCATION_Y, BTN_WIDTH, BTN_HEIGHT);
        groupContainer.add(backGroupButton);

        JButton unGroupButton = new JButton("Ungroup");
        unGroupButton.setBounds(BTN_DRAW_X, NAME_LOCATION_Y, BTN_WIDTH, BTN_HEIGHT);
        groupContainer.add(unGroupButton);

        JLabel shapeListLabel = new JLabel("Shapes: ");
        JTextField shapeListField = new JTextField();

        JLabel shapeListNoteLabel = new JLabel("(Please enter names separated with \",\")");

        shapeListLabel.setBounds(LOCATION_X,BTN_DRAW_Y,LABEL_WIDTH,LABEL_HEIGHT);
        shapeListField.setBounds(TEXT_LOCATION_X,BTN_DRAW_Y,SHAPE_LIST_TEXT_WIDTH,TEXT_HEIGHT);
        shapeListNoteLabel.setBounds(LOCATION_X,BACK_BTN_LOCATION_Y,SHAPE_LIST_TEXT_WIDTH,LABEL_HEIGHT);

        //-----moveContainer element--
        JButton pickMoveShapeButton = new JButton("Pick&Move");
        pickMoveShapeButton.setBounds(BTN_DRAW_X, BTN_DRAW_Y, BTN_WIDTH, BTN_HEIGHT);
        moveContainer.add(pickMoveShapeButton);

        JButton backMoveButton = new JButton("Back");
        backMoveButton.setBounds(BACK_BTN_LOCATION_X, BACK_BTN_LOCATION_Y, BTN_WIDTH, BTN_HEIGHT);
        moveContainer.add(backMoveButton);

        JButton moveShapeButton = new JButton("Move");
        moveShapeButton.setBounds(BTN_DRAW_X, NAME_LOCATION_Y, BTN_WIDTH, BTN_HEIGHT);
        moveContainer.add(moveShapeButton);

        JLabel pointXYLabel = new JLabel("Point(X,Y): ");
        pointXYLabel.setBounds(LOCATION_X,BTN_DRAW_Y,LABEL_WIDTH,LABEL_HEIGHT);
        JTextField pointXField = new JTextField();
        JTextField pointYField = new JTextField();
        pointXField.setBounds(TEXT_LOCATION_X,BTN_DRAW_Y,POINT_XY_TEXT_WIDTH,LABEL_HEIGHT);
        pointYField.setBounds(POINT_Y_X,BTN_DRAW_Y,POINT_XY_TEXT_WIDTH,LABEL_HEIGHT);


        JLabel dXdYLabel = new JLabel("dX, dY: ");
        dXdYLabel.setBounds(LOCATION_X,BACK_BTN_LOCATION_Y,LABEL_WIDTH,LABEL_HEIGHT);
        JTextField pointDXField = new JTextField();
        JTextField pointDYField = new JTextField();
        pointDXField.setBounds(TEXT_LOCATION_X,BACK_BTN_LOCATION_Y,POINT_XY_TEXT_WIDTH,LABEL_HEIGHT);
        pointDYField.setBounds(POINT_Y_X,BACK_BTN_LOCATION_Y,POINT_XY_TEXT_WIDTH,LABEL_HEIGHT);
        JLabel moveNoticeLabel = new JLabel();
        moveNoticeLabel.setBounds(LOCATION_X,MOVE_NOTICE_Y,MOVE_NOTICE_WIDTH,TEXT_HEIGHT);
        moveNoticeLabel.setText("Enter name to move or enter point to pick-and-move");

        //-----listContainer element--
        JButton listShapeButton = new JButton("List");
        listShapeButton.setBounds(BTN_DRAW_X, NAME_LOCATION_Y, BTN_WIDTH, BTN_HEIGHT);
        listContainer.add(listShapeButton);

        JButton listAllButton = new JButton("ListAll");
        listAllButton.setBounds(BTN_DRAW_X, BTN_DRAW_Y, BTN_WIDTH, BTN_HEIGHT);
        listContainer.add(listAllButton);

        JButton backListButton = new JButton("Back");
        backListButton.setBounds(BACK_BTN_LOCATION_X, BACK_BTN_LOCATION_Y, BTN_WIDTH, BTN_HEIGHT);
        listContainer.add(backListButton);

        JTextArea listAllArea = new JTextArea(LIST_AREA_R,LIST_AREA_C);
        JScrollPane jsp = new JScrollPane(listAllArea);
        jsp.setBounds(LOCATION_X,LIST_Y,LIST_WIDTH,LIST_HEIGHT);
        jsp.setVerticalScrollBarPolicy( JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        listContainer.add(jsp);

        //-----intersectContainer element--
        JButton intersectButton = new JButton("Check");
        intersectButton.setBounds(BTN_DRAW_X, BTN_DRAW_Y, BTN_WIDTH, BTN_HEIGHT);
        intersectContainer.add(intersectButton);

        JButton backIntersectButton = new JButton("Back");
        backIntersectButton.setBounds(BACK_BTN_LOCATION_X, BACK_BTN_LOCATION_Y, BTN_WIDTH, BTN_HEIGHT);
        intersectContainer.add(backIntersectButton);

        JLabel shape2Label = new JLabel("Shape2: ");
        JTextField shape2Field = new JTextField();

        shape2Label.setBounds(LOCATION_X,BTN_DRAW_Y,LABEL_WIDTH,LABEL_HEIGHT);
        shape2Field.setBounds(TEXT_LOCATION_X,BTN_DRAW_Y,TEXT_WIDTH,TEXT_HEIGHT);

        intersectContainer.add(shape2Label);
        intersectContainer.add(shape2Field);



        /** ----------------------------listener---------------------------------------------*/

        // listener for drawShapeButton on drawContainer page
        drawShapeButton.addActionListener(e -> {
            try{
                if (radioButtonRec.isSelected()) {
                    clevis.drawRectangle(name.getText(),Double.parseDouble(textFieldP1.getText()),
                            Double.parseDouble(textFieldP2.getText()),Double.parseDouble(textFieldP3.getText()),
                            Double.parseDouble(textFieldP4.getText()));
                }
                else if (radioButtonLine.isSelected()) {
                    clevis.drawLine(name.getText(),Double.parseDouble(textFieldP1.getText()),
                            Double.parseDouble(textFieldP2.getText()),Double.parseDouble(textFieldP3.getText()),
                            Double.parseDouble(textFieldP4.getText()));
                }
                else if (radioButtonCircle.isSelected()) {
                    clevis.drawCircle(name.getText(),Double.parseDouble(textFieldP1.getText()),
                            Double.parseDouble(textFieldP2.getText()),Double.parseDouble(textFieldP3.getText()));
                }
                else if (radioButtonSquare.isSelected()) {
                    clevis.drawSquare(name.getText(),Double.parseDouble(textFieldP1.getText()),
                            Double.parseDouble(textFieldP2.getText()),Double.parseDouble(textFieldP3.getText()));
                }
                mainLabel.setText("Draw successfully!");
            }
            catch (Exception e1) {
                mainLabel.setText("Illegal input, please try again!");
            }

            drawArea.repaint();
            refreshSize();
            MainContainer.setLocation(PANEL_X,PANEL_Y);
            drawContainer.setLocation(PANEL_X,PANEL_Y);
            deleteContainer.setLocation(PANEL_X,PANEL_Y);
            groupContainer.setLocation(PANEL_X,PANEL_Y);
            boundingBoxContainer.setLocation(PANEL_X,PANEL_Y);
            moveContainer.setLocation(PANEL_X,PANEL_Y);
            intersectContainer.setLocation(PANEL_X,PANEL_Y);
            listContainer.setLocation(PANEL_X,PANEL_Y);

        });

        // listener for deleteShapeButton on delete page
        deleteShapeButton.addActionListener(e -> {
            try{
                clevis.deleteShapeWithName(name.getText());
                mainLabel.setText("Delete successfully!");
            }
            catch (Exception e1) {
                mainLabel.setText("Illegal input, please try again!");
            }

            drawArea.repaint();
            refreshSize();
            MainContainer.setLocation(PANEL_X,PANEL_Y);
            drawContainer.setLocation(PANEL_X,PANEL_Y);
            deleteContainer.setLocation(PANEL_X,PANEL_Y);
            groupContainer.setLocation(PANEL_X,PANEL_Y);
            boundingBoxContainer.setLocation(PANEL_X,PANEL_Y);
            moveContainer.setLocation(PANEL_X,PANEL_Y);
            intersectContainer.setLocation(PANEL_X,PANEL_Y);
            listContainer.setLocation(PANEL_X,PANEL_Y);

            //new drawDialog();
        });

        // listener for deleteShapeButton on bounding page
        boundingBoxButton.addActionListener(e -> {
            try{
                boundingBoxShape = new BoundingBox(clevis.getShape(name.getText()));
                boundingBoxFlag = true;
                listAllArea.setText(boundingBoxShape.listInfo());
            }
            catch (Exception e1) {
                mainLabel.setText("Illegal input, please try again!");
            }
            drawArea.repaint();

        });

        // listener for groupButton on group page
        createGroupButton.addActionListener(e -> {
            try {
                String[] shapeList = shapeListField.getText().split(",");
                clevis.createGroup(name.getText(),shapeList);
                mainLabel.setText("Group created successfully!");
            }
            catch (Exception e1) {
                mainLabel.setText("Illegal input, please try again!");
            }
            drawArea.repaint();
        });

        // listener for unGroupButton on group page
        unGroupButton.addActionListener(e -> {
            try{
                clevis.unGroup(name.getText());
                mainLabel.setText("Ungroup successfully!");

            }
            catch (Exception e1) {
                mainLabel.setText("Illegal input, please try again!");
            }
            drawArea.repaint();

            //new drawDialog();
        });

        // listener for moveShapeButton on group page
        moveShapeButton.addActionListener(e -> {
            try {
                clevis.moveShape(name.getText(),Double.parseDouble(pointDXField.getText()),Double.parseDouble(pointDYField.getText()));
                mainLabel.setText("Shape moved successfully!");
            }
            catch (Exception e1) {
                mainLabel.setText("Illegal input, please try again!");
            }
            drawArea.repaint();
            refreshSize();
            MainContainer.setLocation(PANEL_X,PANEL_Y);
            drawContainer.setLocation(PANEL_X,PANEL_Y);
            deleteContainer.setLocation(PANEL_X,PANEL_Y);
            groupContainer.setLocation(PANEL_X,PANEL_Y);
            boundingBoxContainer.setLocation(PANEL_X,PANEL_Y);
            moveContainer.setLocation(PANEL_X,PANEL_Y);
            intersectContainer.setLocation(PANEL_X,PANEL_Y);
            listContainer.setLocation(PANEL_X,PANEL_Y);

        });

        // listener for moveShapeButton on group page
        pickMoveShapeButton.addActionListener(e -> {
            try {
                clevis.pickAndMoveShape(Double.parseDouble(pointXField.getText()),
                        Double.parseDouble(pointYField.getText()),Double.parseDouble(pointDXField.getText()),
                        Double.parseDouble(pointDYField.getText()));
                mainLabel.setText("Shape moved successfully!");
            }
            catch (Exception e1) {
                mainLabel.setText("Illegal input, please try again!");
            }
            drawArea.repaint();
            refreshSize();
            MainContainer.setLocation(PANEL_X,PANEL_Y);
            drawContainer.setLocation(PANEL_X,PANEL_Y);
            deleteContainer.setLocation(PANEL_X,PANEL_Y);
            groupContainer.setLocation(PANEL_X,PANEL_Y);
            boundingBoxContainer.setLocation(PANEL_X,PANEL_Y);
            moveContainer.setLocation(PANEL_X,PANEL_Y);
            intersectContainer.setLocation(PANEL_X,PANEL_Y);
            listContainer.setLocation(PANEL_X,PANEL_Y);
        });

        // listener for listShapeButton on group page
        listShapeButton.addActionListener(e -> {
            try {
                listAllArea.setText(clevis.listShape(name.getText()));
                mainLabel.setText("Shape listed successfully!");
            }
            catch (Exception e1) {
                mainLabel.setText("Illegal input, please try again!");
            }
            drawArea.repaint();

        });

        // listener for listShapeButton on group page
        listAllButton.addActionListener(e -> {
            try {
                listAllArea.setText(clevis.listAllShape());
                mainLabel.setText("All shapes listed successfully!");
            }
            catch (Exception e1) {
                mainLabel.setText("Illegal input, please try again!");
            }

            drawArea.repaint();

        });

        // listener for intersectButton on group page
        intersectButton.addActionListener(e -> {
            try {
                if (clevis.isIntersected(name.getText(),shape2Field.getText())) {
                    mainLabel.setText(name.getText()+" and "+ shape2Field.getText()+" is intersected!");
                }
                else {
                    mainLabel.setText(name.getText()+" and "+ shape2Field.getText()+" is NOT intersected!");
                }

            }
            catch (Exception e1) {
                mainLabel.setText("Illegal input, please try again!");
            }
            drawArea.repaint();

        });

        // listener for undoButton on each page
        undoButton.addActionListener(e -> {
            try {
                clevis.UndoControl();
                mainLabel.setText("Undo successfully!");
            }
            catch (Exception e1) {
                mainLabel.setText("Illegal undo, please try again later!");
            }
            drawArea.repaint();
            refreshSize();
            MainContainer.setLocation(PANEL_X,PANEL_Y);
            drawContainer.setLocation(PANEL_X,PANEL_Y);
            deleteContainer.setLocation(PANEL_X,PANEL_Y);
            groupContainer.setLocation(PANEL_X,PANEL_Y);
            boundingBoxContainer.setLocation(PANEL_X,PANEL_Y);
            moveContainer.setLocation(PANEL_X,PANEL_Y);
            intersectContainer.setLocation(PANEL_X,PANEL_Y);
            listContainer.setLocation(PANEL_X,PANEL_Y);
        });

        // listener for redoButton on each page
        redoButton.addActionListener(e -> {
            try {
                clevis.RedoControl();
                mainLabel.setText("Redo successfully!");
            }
            catch (Exception e1) {
                mainLabel.setText("Illegal redo, please try again later!");
            }
            drawArea.repaint();
            refreshSize();
            MainContainer.setLocation(PANEL_X,PANEL_Y);
            drawContainer.setLocation(PANEL_X,PANEL_Y);
            deleteContainer.setLocation(PANEL_X,PANEL_Y);
            groupContainer.setLocation(PANEL_X,PANEL_Y);
            boundingBoxContainer.setLocation(PANEL_X,PANEL_Y);
            moveContainer.setLocation(PANEL_X,PANEL_Y);
            intersectContainer.setLocation(PANEL_X,PANEL_Y);
            listContainer.setLocation(PANEL_X,PANEL_Y);
        });




        // listener for drawButton on main page
        drawButton.addActionListener(e -> {
            MainContainer.setVisible(false);
            container.remove(MainContainer);

            drawContainer.add(labelName);
            drawContainer.add(name);

            drawContainer.add(undoButton);
            drawContainer.add(redoButton);

            mainLabel.setText("Please select the shape to draw!");
            drawContainer.add(mainLabel);

            container.add(drawContainer);
            drawContainer.setVisible(true);
            //new drawDialog();
        });

        // listener for deleteButton on main page
        deleteButton.addActionListener(e -> {
            MainContainer.setVisible(false);
            container.remove(MainContainer);

            deleteContainer.add(labelName);
            deleteContainer.add(name);

            deleteContainer.add(undoButton);
            deleteContainer.add(redoButton);

            mainLabel.setText("Please enter the name of the shape to delete!");
            deleteContainer.add(mainLabel);

            container.add(deleteContainer);
            deleteContainer.setVisible(true);


            //new drawDialog();
        });

        // listener for boundBoxButton on main page
        boundBoxButton.addActionListener(e -> {
            MainContainer.setVisible(false);
            container.remove(MainContainer);

            boundingBoxContainer.add(labelName);
            boundingBoxContainer.add(name);

            mainLabel.setText("Please enter the name of the shape to bound!");
            boundingBoxContainer.add(mainLabel);

            boundingBoxContainer.add(jsp);

            container.add(boundingBoxContainer);
            boundingBoxContainer.setVisible(true);


            //new drawDialog();
        });

        // listener for groupButton on main page
        groupButton.addActionListener(e -> {
            MainContainer.setVisible(false);
            container.remove(MainContainer);

            groupContainer.add(labelName);
            groupContainer.add(name);

            groupContainer.add(undoButton);
            groupContainer.add(redoButton);

            groupContainer.add(shapeListLabel);
            groupContainer.add(shapeListField);
            groupContainer.add(shapeListNoteLabel);

            mainLabel.setText("Please enter the Group name to group or ungroup!");
            groupContainer.add(mainLabel);

            container.add(groupContainer);
            groupContainer.setVisible(true);

        });

        // listener for moveButton on main page
        moveButton.addActionListener(e -> {
            MainContainer.setVisible(false);
            container.remove(MainContainer);

            moveContainer.add(labelName);
            moveContainer.add(name);

            moveContainer.add(pointXYLabel);
            moveContainer.add(pointXField);
            moveContainer.add(pointYField);
            moveContainer.add(dXdYLabel);
            moveContainer.add(moveNoticeLabel);

            moveContainer.add(undoButton);
            moveContainer.add(redoButton);

            moveContainer.add(pointDXField);
            moveContainer.add(pointDYField);

            mainLabel.setText("Please enter the name of the shape to move!");
            moveContainer.add(mainLabel);


            container.add(moveContainer);
            moveContainer.setVisible(true);

        });

        // listener for listButton on main page
        listButton.addActionListener(e -> {
            MainContainer.setVisible(false);
            container.remove(MainContainer);

            listContainer.add(labelName);
            listContainer.add(name);


            mainLabel.setText("Please enter the name of the shape to list!");
            listContainer.add(mainLabel);

            listContainer.add(jsp);

            container.add(listContainer);
            listContainer.setVisible(true);

        });

        // listener for intersectionButton on main page
        intersectionButton.addActionListener(e -> {
            MainContainer.setVisible(false);
            container.remove(MainContainer);

            labelName.setText("Shape1: ");
            intersectContainer.add(labelName);
            intersectContainer.add(name); // shape1


            mainLabel.setText("Please enter the name of shapes to check!");
            intersectContainer.add(mainLabel);


            container.add(intersectContainer);
            intersectContainer.setVisible(true);

        });


        // listener for quitButton on main page
        quitButton.addActionListener(e -> {
            //setVisible(false);
            JDialog quitDio = new JDialog();
            quitDio.setTitle("Confirm Quit?");
            quitDio.setBounds(PANEL_HEIGHT,PANEL_HEIGHT,SHAPE_LIST_TEXT_WIDTH,RADIO_WIDTH);
            JButton conBut = new JButton("QUIT");
            JButton canBut = new JButton("CANCEL");
            quitDio.setLayout(new FlowLayout());
            quitDio.add(conBut);
            quitDio.add(canBut);
            conBut.addActionListener(e1 -> {
                System.exit(0);
            });
            quitDio.setVisible(true);
            canBut.addActionListener(e1 -> {
                quitDio.setVisible(false);
            });

        });


        // listener for backDrawButton on drawContainer page
        backDrawButton.addActionListener(e -> {
            name.setText("");
            textFieldP1.setText("");
            textFieldP2.setText("");
            textFieldP3.setText("");
            textFieldP4.setText("");
            drawContainer.setVisible(false);
            container.remove(drawContainer);

            mainLabel.setText("Please select function!");
            MainContainer.add(mainLabel);
            MainContainer.add(redoButton);
            MainContainer.add(undoButton);

            MainContainer.setVisible(true);
            container.add(MainContainer);

            drawArea.repaint();

        });

        // listener for backDeleteButton on deleteContainer page
        backDeleteButton.addActionListener(e -> {
            name.setText("");
            textFieldP1.setText("");
            textFieldP2.setText("");
            textFieldP3.setText("");
            textFieldP4.setText("");
            deleteContainer.setVisible(false);
            container.remove(deleteContainer);

            mainLabel.setText("Please select function!");
            MainContainer.add(mainLabel);
            MainContainer.add(redoButton);
            MainContainer.add(undoButton);

            MainContainer.setVisible(true);
            container.add(MainContainer);
        });

        // listener for backBoundingButton on boundingBoxContainer page
        backBoundingButton.addActionListener(e -> {
            name.setText("");
            textFieldP1.setText("");
            textFieldP2.setText("");
            textFieldP3.setText("");
            textFieldP4.setText("");
            listAllArea.setText("");

            boundingBoxContainer.setVisible(false);
            container.remove(boundingBoxContainer);

            mainLabel.setText("Please select function!");
            MainContainer.add(mainLabel);
            MainContainer.add(redoButton);
            MainContainer.add(undoButton);

            MainContainer.setVisible(true);
            container.add(MainContainer);
            drawArea.repaint();
        });

        // listener for backGroupButton on groupContainer page
        backGroupButton.addActionListener(e -> {
            name.setText("");
            textFieldP1.setText("");
            textFieldP2.setText("");
            textFieldP3.setText("");
            textFieldP4.setText("");
            shapeListField.setText("");

            groupContainer.setVisible(false);
            container.remove(groupContainer);

            mainLabel.setText("Please select function!");
            MainContainer.add(mainLabel);
            MainContainer.add(redoButton);
            MainContainer.add(undoButton);

            MainContainer.setVisible(true);
            container.add(MainContainer);
            drawArea.repaint();
        });

        // listener for backMoveButton on moveContainer page
        backMoveButton.addActionListener(e -> {
            name.setText("");
            textFieldP1.setText("");
            textFieldP2.setText("");
            textFieldP3.setText("");
            textFieldP4.setText("");
            pointXField.setText("");
            pointYField.setText("");
            pointDXField.setText("");
            pointDYField.setText("");

            moveContainer.setVisible(false);
            container.remove(moveContainer);

            mainLabel.setText("Please select function!");
            MainContainer.add(mainLabel);
            MainContainer.add(redoButton);
            MainContainer.add(undoButton);

            MainContainer.setVisible(true);
            container.add(MainContainer);
            drawArea.repaint();
        });

        // listener for backListButton on moveContainer page
        backListButton.addActionListener(e -> {
            name.setText("");
            textFieldP1.setText("");
            textFieldP2.setText("");
            textFieldP3.setText("");
            textFieldP4.setText("");
            listAllArea.setText("");
            listAllArea.setText("");


            listContainer.setVisible(false);
            container.remove(listContainer);

            mainLabel.setText("Please select function!");
            MainContainer.add(mainLabel);
            MainContainer.add(redoButton);
            MainContainer.add(undoButton);

            MainContainer.setVisible(true);
            container.add(MainContainer);
            drawArea.repaint();
        });

        // listener for backIntersectButton on moveContainer page
        backIntersectButton.addActionListener(e -> {
            name.setText("");
            textFieldP1.setText("");
            textFieldP2.setText("");
            textFieldP3.setText("");
            textFieldP4.setText("");
            shape2Field.setText("");

            intersectContainer.setVisible(false);
            container.remove(intersectContainer);

            mainLabel.setText("Please select function!");
            MainContainer.add(mainLabel);
            MainContainer.add(redoButton);
            MainContainer.add(undoButton);

            MainContainer.setVisible(true);
            container.add(MainContainer);
            drawArea.repaint();
        });

        // 4 radioButton
        radioButtonRec.addActionListener(e -> {
            name.setText("");
            textFieldP1.setText("");
            textFieldP2.setText("");
            textFieldP3.setText("");
            textFieldP4.setText("");
            labelP1.setText("LocationX:");
            labelP2.setText("LocationY:");
            labelP3.setText("Width:");
            labelP4.setVisible(true);
            labelP4.setText("Height:");
            textFieldP4.setVisible(true);
        });

        radioButtonLine.addActionListener(e -> {
            name.setText("");
            textFieldP1.setText("");
            textFieldP2.setText("");
            textFieldP3.setText("");
            textFieldP4.setText("");
            labelP1.setText("LocationX1:");
            labelP2.setText("LocationY1:");
            labelP3.setText("LocationX2:");
            labelP4.setVisible(true);
            labelP4.setText("LocationY2:");
            textFieldP4.setVisible(true);
        });

        radioButtonCircle.addActionListener(e -> {
            name.setText("");
            textFieldP1.setText("");
            textFieldP2.setText("");
            textFieldP3.setText("");
            textFieldP4.setText("");
            labelP1.setText("LocationX:");
            labelP2.setText("LocationY:");
            labelP3.setText("Radius:");
            labelP4.setVisible(false);
            textFieldP4.setVisible(false);
        });

        radioButtonSquare.addActionListener(e -> {
            name.setText("");
            textFieldP1.setText("");
            textFieldP2.setText("");
            textFieldP3.setText("");
            textFieldP4.setText("");
            labelP1.setText("LocationX:");
            labelP2.setText("LocationY:");
            labelP3.setText("Length:");
            labelP4.setVisible(false);
            textFieldP4.setVisible(false);
        });
        // 4 radioButton end
    }
}