package hk.edu.polyu.comp.comp2021.clevis.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/** Dialog of drawing circle */
public class circleDialog extends JDialog {
    private JTextField locationX,locationY,Radius;
    private MyCanvas drawArea;
    private int[][] currentElement;//int型嵌套数组，用来储存所有参数，使得可以repaint所有图形
    private int numOfButtonClick = 0;//点击按钮的次数

    private final int DIALOG_WIDTH = 500;
    private final int DIALOG_HEIGHT = 500;

    private final int DRAW_AREA = 300;

    private final int LABEL_WIDTH = 80;
    private final int LABEL_HEIGHT = 30;
    private final int NAME_LOCATION_Y = 320;
    private final int X_LOCATION_Y = 350;
    private final int Y_LOCATION_Y = 380;
    private final int RADIUS_LOCATION_Y = 410;

    private final int TEXT_WIDTH = 150;
    private final int TEXT_HEIGHT = 30;
    private final int TEXT_LOCATION_X = 80;

    private final int BTN_WIDTH = 100;
    private final int BTN_HEIGHT = 25;
    private final int BTN_BACK_X = 400;
    private final int BTN_BACK_Y = 450;
    private final int BTN_DRAW_X = 400;
    private final int BTN_DRAW_Y = 400;
    private final int BTN_DELETE_X = 400;
    private final int BTN_DELETE_Y = 425;


    //弹窗
    /**constructor of circleDialog */
    public circleDialog() {
        // 整体框架
        JFrame dialog = new JFrame("DrawCircle弹窗");
        dialog.setVisible(true);
        dialog.setBounds(0, 0, DIALOG_WIDTH, DIALOG_HEIGHT);
        dialog.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //内容窗格
        Container dialogContainer = this.getContentPane();
        dialogContainer.setBounds(0, 0, DIALOG_WIDTH, DIALOG_HEIGHT);
        dialogContainer.setLayout(null);
        dialog.add(dialogContainer);

        // 输入参数界面
        // 标签
        JLabel labelName = new JLabel();
        JLabel labelLocationX = new JLabel();
        JLabel labelLocationY = new JLabel();
        JLabel labelRadius = new JLabel();

        labelName.setText("Name:");
        labelLocationX.setText("LocationX:");
        labelLocationY.setText("LocationY:");
        labelRadius.setText("Radius:");

        labelName.setBounds(0,NAME_LOCATION_Y,LABEL_WIDTH,LABEL_HEIGHT);
        labelLocationX.setBounds(0,X_LOCATION_Y,LABEL_WIDTH,LABEL_HEIGHT);
        labelLocationY.setBounds(0,Y_LOCATION_Y,LABEL_WIDTH,LABEL_HEIGHT);
        labelRadius.setBounds(0,RADIUS_LOCATION_Y,LABEL_WIDTH,LABEL_HEIGHT);

        dialogContainer.add(labelName);
        dialogContainer.add(labelLocationX);
        dialogContainer.add(labelLocationY);
        dialogContainer.add(labelRadius);

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
        dialogContainer.add(name);
        dialogContainer.add(locationX);
        dialogContainer.add(locationY);
        dialogContainer.add(Radius);

        // 添加按钮back及其响应事件
        JButton backButton = new JButton("Back");
        dialogContainer.add(backButton);
        backButton.setBounds(BTN_BACK_X, BTN_BACK_Y, BTN_WIDTH,BTN_HEIGHT);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {new drawDialog();}
        });

        //创建画布对象
        drawArea = new MyCanvas();
        // 绘图区域
        drawArea.setBounds(0,0,DRAW_AREA,DRAW_AREA);// 必须设置大小及坐标，因为当前layout为null
        //创建参数数组
        currentElement = new int[100][4];//是否可以跟随numOfButtonClick而改变储存长度？？
        // 创建画圆按钮及响应事件
        JButton circleButton = new JButton("drawCircle");
        circleButton.setBounds(BTN_DRAW_X, BTN_DRAW_Y, BTN_WIDTH, BTN_HEIGHT);
        dialogContainer.add(circleButton);
        circleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                drawArea.setVisible(true);
                currentCircle(Integer.parseInt(locationX.getText()),Integer.parseInt(locationY.getText()),Integer.parseInt(Radius.getText()));
                numOfButtonClick++;
                dialogContainer.add(drawArea);
                drawArea.repaint();
            }
        });

        // 添加按钮delete及其响应事件
        JButton deleteButton = new JButton("Delete");
        dialogContainer.add(deleteButton);
        deleteButton.setBounds(BTN_DELETE_X, BTN_DELETE_Y, BTN_WIDTH,BTN_HEIGHT);
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                drawArea.setVisible(false);
            }
        });
    }

    private class MyCanvas extends JPanel {
        //重写paint以绘制图形
        @Override
        public void paint(Graphics g) {
            //绘制所有圆
            for(int[] i:currentElement){
                g.setColor(Color.red);
                g.drawOval(i[0],i[1],i[2],i[3]);
            }
        }
    }
    //将所有的参数全部存进currentElement以便全部重绘
    private void currentCircle(int x,int y,int z){
        for(int i = 0;i <= numOfButtonClick;i++){//i应该对应按钮响应的次数
            if(i == numOfButtonClick){
                currentElement[i][0] = x;
                currentElement[i][1] = y;
                currentElement[i][2] = z;
                currentElement[i][3] = z;
            }
        }
    }
}
