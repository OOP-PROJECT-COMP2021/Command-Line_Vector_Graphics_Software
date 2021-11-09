package hk.edu.polyu.comp.comp2021.clevis.view;

import hk.edu.polyu.comp.comp2021.clevis.model.Clevis;
import hk.edu.polyu.comp.comp2021.clevis.model.*;
import hk.edu.polyu.comp.comp2021.clevis.model.Rectangle;
import hk.edu.polyu.comp.comp2021.clevis.model.Shape;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/** the main GUI */
public class ClevisView extends JFrame{
    Clevis clevis = new Clevis();

    private MyCanvas drawArea;
    private Rectangle boundingBoxShape;
    private boolean flag = false;

    private class MyCanvas extends JPanel {
        //重写paint以绘制图形
        @Override
        public void paint(Graphics g) {
            g.setColor(Color.black);
            g.drawRect(0,0,600,300);
            for(Shape shape : clevis.getShapeLevel()){

                g.setColor(Color.red);
                if (shape.getSHAPE_TYPE().equals("REC")) {
                    g.drawRect((int) (((Rectangle)shape).getTopLeftCorner().getX()),(int)((Rectangle)shape).getTopLeftCorner().getY(),(int)(((Rectangle)shape).getWidth()),(int)(((Rectangle)shape).getHeight()));
                    g.drawString(shape.getName(),(int) (((Rectangle)shape).getTopLeftCorner().getX()),(int)((Rectangle)shape).getTopLeftCorner().getY());
                }
                else if (shape.getSHAPE_TYPE().equals("LINE")) {
                    g.drawLine((int) (((Line)shape).getA().getX()),(int)(((Line)shape).getA().getY()),(int)(((Line)shape).getB().getX()),(int)(((Line)shape).getB().getY()));
                    g.drawString(shape.getName(),(int) (((Line)shape).getA().getX()),(int)(((Line)shape).getA().getY()));

                }
                else if (shape.getSHAPE_TYPE().equals("CIR")) {
                    g.drawOval((int) (shape.getLeftBounding()),(int) shape.getTopBounding(),(int)(((Circle)shape).getRadius()*2),(int)(((Circle)shape).getRadius()*2));
                    g.drawString(shape.getName(),(int) (shape.getLeftBounding()),(int) shape.getTopBounding());
                }

                else if (shape.getSHAPE_TYPE().equals("GRP")) {
                    g.setColor(Color.darkGray);
                    Rectangle grpBound = new BoundingBox(shape);
                    g.drawRect((int) (grpBound.getTopLeftCorner().getX()),(int) grpBound.getTopLeftCorner().getY(),(int)(grpBound.getWidth()),(int)(grpBound.getHeight()));
                    g.drawString(shape.getName(),(int) (shape.getRightBounding()),(int) shape.getTopBounding());
                }
            }
            if (flag) {
                g.setColor(Color.gray);
                g.drawRect((int) (boundingBoxShape.getTopLeftCorner().getX()),(int) boundingBoxShape.getTopLeftCorner().getY(),(int)(boundingBoxShape.getWidth()),(int)(boundingBoxShape.getHeight()));
                flag = false;
            }
            //绘制所有圆
        }
    }
    //all component' parameters
    private final int DIALOG_X = 100;
    private final int DIALOG_Y = 100;

    private final int PANEL_X = 0;
    private final int PANEL_Y = 300;
    private final int PANEL_HEIGHT = 300;



    private final int WIDTH = 600;
    private final int HEIGHT = 600;
    private final int LocationX = 0;

    private final int BUTTON_WIDTH = 100;
    private final int BUTTON_HEIGHT = 50;

    private final int DRAW_BTN_LOCATION_Y = 200;
    private final int QUIT_BTN_LOCATION_Y = 200;
    private final int QUIT_BTN_LOCATION_X = 500;
    private final int DRAW_CIRCLE_BTN_LOCATION_Y = 100;

    // for main page buttons
    private final int FIRST_CO_X = 150;
    private final int SECOND_CO_X = 350;

    private final int FIRST_RO_Y = 10;
    private final int SECOND_RO_Y = 80;
    private final int THIRD_RO_Y = 150;
    private final int FOURTH_RO_Y =220;



    //----
    private final int DIALOG_WIDTH = 500;
    private final int DIALOG_HEIGHT = 500;

    private final int DRAW_AREA_X = 600;
    private final int DRAW_AREA_Y = 300;

    private final int LABEL_WIDTH = 80;
    private final int LABEL_HEIGHT = 30;
    private final int NAME_LOCATION_Y = 75;
    private final int P1_LOCATION_Y = 110;
    private final int P2_LOCATION_Y = 145;
    private final int P3_LOCATION_Y = 180;
    private final int P4_LOCATION_Y = 215;

    private final int LOCATION_X = 50;
    private final int RADIO_LOCATION_Y = 40;
    private final int RADIO_WIDTH = 100;
    private final int RADIO_HEIGHT = 50;
    private final int RADIO_LINE_X = 180;
    private final int RADIO_CIR_X = 310;
    private final int RADIO_SQU_X = 440;






    private final int TEXT_WIDTH = 150;
    private final int SHAPE_LIST_TEXT_WIDTH = 250;
    private final int POINT_XY_TEXT_WIDTH = 75;
    private final int POINT_Y_X = 205;



    private final int TEXT_HEIGHT = 30;
    private final int TEXT_LOCATION_X = 130;

    private final int BTN_WIDTH = 100;
    private final int BTN_HEIGHT = 25;
    private final int BTN_BACK_X = 400;
    private final int BTN_BACK_Y = 425;
    private final int BTN_DRAW_X = 500;
    private final int BTN_DRAW_Y = 175;
    private final int BTN_DELETE_X = 400;
    private final int BTN_DELETE_Y = 400;

    private final int DRAW_REC_BTN_LOCATION_X = 125;
    private final int DRAW_REC_BTN_LOCATION_Y = 400;
    private final int BACK_BTN_LOCATION_Y = 200;
    private final int BACK_BTN_LOCATION_X = 500;


    /** main function
     * @param args : args*/
    public static void main(String[] args) {
        new ClevisView();
    }
    // 初始化

    /** constructor */
    public ClevisView(){
        //主界面
        //JFrame mainGUI = new JFrame();
        this.setVisible(true);
        this.setBounds(DIALOG_X,DIALOG_Y,WIDTH,HEIGHT);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        //container
        Container container = this.getContentPane();
        //绝对布局
        container.setLayout(null);
        //设置背景颜色
        //container.setBackground(Color.gray);

        //创建画布对象
        drawArea = new MyCanvas();
        // 绘图区域
        drawArea.setBounds(0,0,DRAW_AREA_X,DRAW_AREA_Y);// 必须设置大小及坐标，因为当前layout为null

        drawArea.setVisible(true);

        /** -----------------------------panels on container--------------------------------------------*/
        /** Panel for Main page*/
        JPanel MainContainer = new JPanel();
        MainContainer.setVisible(true);
        MainContainer.setBounds(PANEL_X,PANEL_Y,WIDTH,PANEL_HEIGHT);
        MainContainer.setLayout(null);
        container.add(MainContainer);

        container.add(drawArea);
        drawArea.repaint();


        /** Panel for Draw page for all shapes */
        JPanel drawContainer = new JPanel();
        drawContainer.setVisible(false);
        drawContainer.setBounds(PANEL_X,PANEL_Y,WIDTH,PANEL_HEIGHT);
        drawContainer.setLayout(null);
        //container.add(drawSelectionContainer);

        /** Panel for Delete page for all shapes */
        JPanel deleteContainer = new JPanel();
        deleteContainer.setVisible(false);
        deleteContainer.setBounds(PANEL_X,PANEL_Y,WIDTH,PANEL_HEIGHT);
        deleteContainer.setLayout(null);
        //container.add(drawSelectionContainer);

        /** Panel for Boundingbox page for all shapes */
        JPanel boundingBoxContainer = new JPanel();
        boundingBoxContainer.setVisible(false);
        boundingBoxContainer.setBounds(PANEL_X,PANEL_Y,WIDTH,PANEL_HEIGHT);
        boundingBoxContainer.setLayout(null);
        //container.add(drawSelectionContainer);

        /** Panel for groupContainer page for all shapes */
        JPanel groupContainer = new JPanel();
        groupContainer.setVisible(false);
        groupContainer.setBounds(PANEL_X,PANEL_Y,WIDTH,PANEL_HEIGHT);
        groupContainer.setLayout(null);
        //container.add(drawSelectionContainer);

        /** Panel for moveContainer page for all shapes */
        JPanel moveContainer = new JPanel();
        moveContainer.setVisible(false);
        moveContainer.setBounds(PANEL_X,PANEL_Y,WIDTH,PANEL_HEIGHT);
        moveContainer.setLayout(null);
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


        /** ----------------------------listener---------------------------------------------*/

        // listener for drawShapeButton on drawContainer page
        drawShapeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (radioButtonRec.isSelected()) {
                    clevis.drawRectangle(name.getText(),Double.parseDouble(textFieldP1.getText()),Double.parseDouble(textFieldP2.getText()),Double.parseDouble(textFieldP3.getText()),Double.parseDouble(textFieldP4.getText()));
                }
                else if (radioButtonLine.isSelected()) {
                    clevis.drawLine(name.getText(),Double.parseDouble(textFieldP1.getText()),Double.parseDouble(textFieldP2.getText()),Double.parseDouble(textFieldP3.getText()),Double.parseDouble(textFieldP4.getText()));
                }
                else if (radioButtonCircle.isSelected()) {
                    clevis.drawCircle(name.getText(),Double.parseDouble(textFieldP1.getText()),Double.parseDouble(textFieldP2.getText()),Double.parseDouble(textFieldP3.getText()));
                }
                else if (radioButtonSquare.isSelected()) {
                    clevis.drawSquare(name.getText(),Double.parseDouble(textFieldP1.getText()),Double.parseDouble(textFieldP2.getText()),Double.parseDouble(textFieldP3.getText()));
                }

                drawArea.repaint();
            }
        });

        // listener for deleteShapeButton on delete page
        deleteShapeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (name.getText() != "" && clevis.containsName(name.getText())) {clevis.deleteShapeWithName(name.getText());}
                drawArea.repaint();

                //new drawDialog();
            }
        });

        // listener for deleteShapeButton on bounding page
        boundingBoxButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (name.getText() != "" && clevis.containsName(name.getText())) {
                    boundingBoxShape = new BoundingBox(clevis.getShape(name.getText()));
                    flag = true;
                }
                drawArea.repaint();

                //new drawDialog();
            }
        });

        // listener for groupButton on group page
        createGroupButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (name.getText() != "" && shapeListField.getText() != "" && !clevis.containsName(name.getText())) {
                    String[] shapeList = shapeListField.getText().split(",");
                    clevis.createGroup(name.getText(),shapeList);
                }
                drawArea.repaint();

                //new drawDialog();
            }
        });

        // listener for unGroupButton on group page
        unGroupButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (name.getText() != ""  && clevis.containsName(name.getText())) {
                    clevis.unGroup(name.getText());
                }
                drawArea.repaint();

                //new drawDialog();
            }
        });

        // listener for moveShapeButton on group page
        moveShapeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (name.getText() != ""  && clevis.containsName(name.getText())) {
                    clevis.moveShape(name.getText(),Double.parseDouble(pointDXField.getText()),Double.parseDouble(pointDYField.getText()));
                }
                drawArea.repaint();

            }
        });

        // listener for moveShapeButton on group page
        pickMoveShapeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (pointXField.getText() != ""  && pointYField.getText() != "" && pointDXField.getText() != "" && pointDYField.getText() != "" ) {
                    clevis.pickAndMoveShape(Double.parseDouble(pointXField.getText()),Double.parseDouble(pointYField.getText()),Double.parseDouble(pointDXField.getText()),Double.parseDouble(pointDYField.getText()));
                }
                drawArea.repaint();

            }
        });


        // listener for drawButton on main page
        drawButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainContainer.setVisible(false);
                container.remove(MainContainer);

                drawContainer.add(labelName);
                drawContainer.add(name);

                container.add(drawContainer);
                drawContainer.setVisible(true);
                //new drawDialog();
            }
        });

        // listener for deleteButton on main page
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainContainer.setVisible(false);
                container.remove(MainContainer);

                deleteContainer.add(labelName);
                deleteContainer.add(name);

                container.add(deleteContainer);
                deleteContainer.setVisible(true);


                //new drawDialog();
            }
        });

        // listener for boundBoxButton on main page
        boundBoxButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainContainer.setVisible(false);
                container.remove(MainContainer);

                boundingBoxContainer.add(labelName);
                boundingBoxContainer.add(name);

                container.add(boundingBoxContainer);
                boundingBoxContainer.setVisible(true);


                //new drawDialog();
            }
        });

        // listener for groupButton on main page
        groupButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainContainer.setVisible(false);
                container.remove(MainContainer);

                groupContainer.add(labelName);
                groupContainer.add(name);

                groupContainer.add(shapeListLabel);
                groupContainer.add(shapeListField);
                groupContainer.add(shapeListNoteLabel);

                container.add(groupContainer);
                groupContainer.setVisible(true);

            }
        });

        // listener for groupButton on main page
        moveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainContainer.setVisible(false);
                container.remove(MainContainer);

                moveContainer.add(labelName);
                moveContainer.add(name);

                moveContainer.add(pointXYLabel);
                moveContainer.add(pointXField);
                moveContainer.add(pointYField);
                moveContainer.add(dXdYLabel);

                moveContainer.add(pointDXField);
                moveContainer.add(pointDYField);


                container.add(moveContainer);
                moveContainer.setVisible(true);

            }
        });


        // listener for quitButton on main page
        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                System.exit(0);
            }
        });

        // listener for backDrawButton on drawContainer page
        backDrawButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                name.setText("");
                textFieldP1.setText("");
                textFieldP2.setText("");
                textFieldP3.setText("");
                textFieldP4.setText("");
                drawContainer.setVisible(false);
                container.remove(drawContainer);

                MainContainer.setVisible(true);
                container.add(MainContainer);

                drawArea.repaint();

            }
        });

        // listener for backDeleteButton on deleteContainer page
        backDeleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                name.setText("");
                textFieldP1.setText("");
                textFieldP2.setText("");
                textFieldP3.setText("");
                textFieldP4.setText("");
                deleteContainer.setVisible(false);
                container.remove(deleteContainer);

                MainContainer.setVisible(true);
                container.add(MainContainer);
            }
        });

        // listener for backBoundingButton on boundingBoxContainer page
        backBoundingButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                name.setText("");
                textFieldP1.setText("");
                textFieldP2.setText("");
                textFieldP3.setText("");
                textFieldP4.setText("");

                boundingBoxContainer.setVisible(false);
                container.remove(boundingBoxContainer);

                MainContainer.setVisible(true);
                container.add(MainContainer);
                drawArea.repaint();
            }
        });

        // listener for backGroupButton on groupContainer page
        backGroupButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                name.setText("");
                textFieldP1.setText("");
                textFieldP2.setText("");
                textFieldP3.setText("");
                textFieldP4.setText("");
                shapeListField.setText("");

                groupContainer.setVisible(false);
                container.remove(groupContainer);

                MainContainer.setVisible(true);
                container.add(MainContainer);
                drawArea.repaint();
            }
        });

        // listener for backMoveButton on moveContainer page
        backMoveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
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

                MainContainer.setVisible(true);
                container.add(MainContainer);
                drawArea.repaint();
            }
        });

        // 4 radioButton
        radioButtonRec.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
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
            }
        });

        radioButtonLine.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
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
            }
        });

        radioButtonCircle.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
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
            }
        });

        radioButtonSquare.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
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
            }
        });
        // 4 radioButton end

    }
}


