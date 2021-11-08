package hk.edu.polyu.comp.comp2021.clevis.view;

import hk.edu.polyu.comp.comp2021.clevis.model.Clevis;
import hk.edu.polyu.comp.comp2021.clevis.model.*;
import hk.edu.polyu.comp.comp2021.clevis.model.Shape;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/** the main GUI */
public class ClevisView extends JFrame{
    Clevis clevis = new Clevis();

    private MyCanvas drawArea;

    private class MyCanvas extends JPanel {
        //重写paint以绘制图形
        @Override
        public void paint(Graphics g) {
            g.setColor(Color.white);
            g.drawLine(0,0,1,1);
            for(Shape shape : clevis.getShapeLevel()){
                g.setColor(Color.red);
                g.drawOval((int) (((Circle)shape).getCenter().getX()),(int)((Circle)shape).getCenter().getY(),(int)(((Circle)shape).getRadius()*2),(int)(((Circle)shape).getRadius()*2));
            }
            //绘制所有圆
        }
    }
    //all component' parameters
    private final int DIALOG_X = 100;
    private final int DIALOG_Y = 100;

    private final int WIDTH = 500;
    private final int HEIGHT = 500;
    private final int LocationX = 0;

    private final int BUTTON_WIDTH = 100;
    private final int BUTTON_HEIGHT = 50;

    private final int DRAW_BTN_LOCATION_Y = 100;
    private final int QUIT_BTN_LOCATION_Y = 100;
    private final int QUIT_BTN_LOCATION_X = 400;
    private final int DRAW_CIRCLE_BTN_LOCATION_Y = 100;

//----
    private final int DIALOG_WIDTH = 500;
    private final int DIALOG_HEIGHT = 500;

    private final int DRAW_AREA_X = 500;
    private final int DRAW_AREA_Y = 300;

    private final int LABEL_WIDTH = 80;
    private final int LABEL_HEIGHT = 30;
    private final int NAME_LOCATION_Y = 10;
    private final int X_LOCATION_Y = 45;
    private final int Y_LOCATION_Y = 80;
    private final int RADIUS_LOCATION_Y = 115;

    private final int TEXT_WIDTH = 150;
    private final int TEXT_HEIGHT = 30;
    private final int TEXT_LOCATION_X = 80;

    private final int BTN_WIDTH = 100;
    private final int BTN_HEIGHT = 25;
    private final int BTN_BACK_X = 400;
    private final int BTN_BACK_Y = 425;
    private final int BTN_DRAW_X = 400;
    private final int BTN_DRAW_Y = 75;
    private final int BTN_DELETE_X = 400;
    private final int BTN_DELETE_Y = 400;

    private final int DRAW_REC_BTN_LOCATION_X = 125;
    private final int DRAW_REC_BTN_LOCATION_Y = 400;
    private final int BACK_BTN_LOCATION_Y = 100;
    private final int BACK_BTN_LOCATION_X = 400;


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

        // Panel for main page
        JPanel MainContainer = new JPanel();
        MainContainer.setVisible(true);
        MainContainer.setBounds(0,300,WIDTH,200);
        MainContainer.setLayout(null);
        container.add(MainContainer);

        container.add(drawArea);
        drawArea.repaint();

        // Panel for draw Selection page
        JPanel drawSelectionContainer = new JPanel();
        drawSelectionContainer.setVisible(false);
        drawSelectionContainer.setBounds(0,300,WIDTH,200);
        drawSelectionContainer.setLayout(null);


        // Panel for draw Circle page
        JPanel drawCircleContainer = new JPanel();
        drawCircleContainer.setVisible(false);
        drawCircleContainer.setBounds(0,300,WIDTH,200);
        drawCircleContainer.setLayout(null);
        //container.add(drawSelectionContainer);


        /** -------------------------------*/
        //Button Draw, Quit on MainContainer
        JButton drawButton = new JButton("Draw");
        drawButton.setBounds(LocationX,DRAW_BTN_LOCATION_Y,BUTTON_WIDTH,BUTTON_HEIGHT);
        MainContainer.add(drawButton);
        JButton quitButton = new JButton("Quit");
        quitButton.setBounds(QUIT_BTN_LOCATION_X,QUIT_BTN_LOCATION_Y,BUTTON_WIDTH,BUTTON_HEIGHT);
        MainContainer.add(quitButton);

        // button Circle on drawSelectionContainer
        drawSelectionContainer.setLayout(null);
        this.add(drawSelectionContainer);
        JButton circleButton = new JButton("Circle");
        drawSelectionContainer.add(circleButton);
        circleButton.setBounds(0, DRAW_CIRCLE_BTN_LOCATION_Y, BUTTON_WIDTH, BUTTON_HEIGHT);

        JButton backSelectionButton = new JButton("Back");
        backSelectionButton.setBounds(BACK_BTN_LOCATION_X, BACK_BTN_LOCATION_Y, BUTTON_WIDTH, BUTTON_HEIGHT);
        drawSelectionContainer.add(backSelectionButton);

        // elements on drawCircleContainer
        // 标签
        JLabel labelName = new JLabel();
        JLabel labelLocationX = new JLabel();
        JLabel labelLocationY = new JLabel();
        JLabel labelRadius = new JLabel();
        JTextField locationX,locationY,Radius;

        labelName.setText("Name:");
        labelLocationX.setText("LocationX:");
        labelLocationY.setText("LocationY:");
        labelRadius.setText("Radius:");

        labelName.setBounds(0,NAME_LOCATION_Y,LABEL_WIDTH,LABEL_HEIGHT);
        labelLocationX.setBounds(0,X_LOCATION_Y,LABEL_WIDTH,LABEL_HEIGHT);
        labelLocationY.setBounds(0,Y_LOCATION_Y,LABEL_WIDTH,LABEL_HEIGHT);
        labelRadius.setBounds(0,RADIUS_LOCATION_Y,LABEL_WIDTH,LABEL_HEIGHT);

        drawCircleContainer.add(labelName);
        drawCircleContainer.add(labelLocationX);
        drawCircleContainer.add(labelLocationY);
        drawCircleContainer.add(labelRadius);
        // 文本框
        JTextField name = new JTextField("");
        locationX = new JTextField("");
        locationY = new JTextField("");
        Radius = new JTextField("");
        // 设置输入文本框的位置信息
        name.setBounds(TEXT_LOCATION_X,NAME_LOCATION_Y,TEXT_WIDTH,TEXT_HEIGHT);
        locationX.setBounds(TEXT_LOCATION_X,X_LOCATION_Y,TEXT_WIDTH,TEXT_HEIGHT);
        locationY.setBounds(TEXT_LOCATION_X,Y_LOCATION_Y,TEXT_WIDTH,TEXT_HEIGHT);
        Radius.setBounds(TEXT_LOCATION_X,RADIUS_LOCATION_Y,TEXT_WIDTH,TEXT_HEIGHT);
        // 添加输入文本框
        drawCircleContainer.add(name);
        drawCircleContainer.add(locationX);
        drawCircleContainer.add(locationY);
        drawCircleContainer.add(Radius);

        JButton drawCircleButton = new JButton("drawCircle");
        drawCircleButton.setBounds(BTN_DRAW_X, BTN_DRAW_Y, BTN_WIDTH, BTN_HEIGHT);
        drawCircleContainer.add(drawCircleButton);

        // listener for drawCircleButton on drawCircleContainer page
        drawCircleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clevis.drawCircle(name.getText(),Double.parseDouble(locationX.getText()),Double.parseDouble(locationY.getText()),Double.parseDouble(Radius.getText()));

                drawArea.repaint();
            }
        });


        // listener for drawButton on main page
        drawButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainContainer.setVisible(false);
                container.remove(MainContainer);

                container.add(drawSelectionContainer);
                drawSelectionContainer.setVisible(true);
                //new drawDialog();
            }
        });

        // listener for circleButton on drawSelectionContainer page
        circleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                drawSelectionContainer.setVisible(false);
                container.remove(drawSelectionContainer);

                container.add(drawCircleContainer);
                drawCircleContainer.setVisible(true);
                //new circleDialog();
            }
        });

        // 按钮quit的响应事件
        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                System.exit(0);
            }
        });

        backSelectionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                drawSelectionContainer.setVisible(false);
                container.remove(drawSelectionContainer);

                MainContainer.setVisible(true);
                container.add(MainContainer);
            }
        });


    }
}


