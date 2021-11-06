package hk.edu.polyu.comp.comp2021.clevis.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class circleDialog extends JDialog {
    private final String OVAL_SHAPE = "oval";
    private String shape = "";
    private JTextField locationX,locationY,radius;

    public void setY(int i){
        locationY.setText(locationX.getText());
    }
    //弹窗
    public circleDialog() {
        JFrame dialog = new JFrame("DrawCircle弹窗");
        dialog.setVisible(true);
        dialog.setBounds(100, 100, 500, 500);
        dialog.getContentPane().setLayout(null);

        // 输入参数界面
        // 标签
        JLabel labelCircle = new JLabel();
        JLabel labelName = new JLabel();
        JLabel labelLocationX = new JLabel();
        JLabel labelLocationY = new JLabel();
        JLabel labelRadius = new JLabel();

        labelCircle.setText("Please enter the relevant arg");
        labelName.setText("Name:");
        labelLocationX.setText("LocationX:");
        labelLocationY.setText("LocationY:");
        labelRadius.setText("Radius:");

        labelCircle.setBounds(0,300,150,50);
        labelName.setBounds(0,350,50,30);
        labelLocationX.setBounds(0,380,80,30);
        labelLocationY.setBounds(0,410,80,30);
        labelRadius.setBounds(0,440,80,30);

        dialog.add(labelCircle);
        dialog.add(labelName);
        dialog.add(labelLocationX);
        dialog.add(labelLocationY);
        dialog.add(labelRadius);

        // 文本框
        JTextField name = new JTextField("");
        locationX = new JTextField("");
        locationY = new JTextField("");
        radius = new JTextField("");
        // 设置输入文本框的位置信息
        name.setBounds(80,350,180,30);
        locationX.setBounds(80,380,180,30);
        locationY.setBounds(80,410,180,30);
        radius.setBounds(80,440,180,30);
        // 添加输入文本框
        dialog.getContentPane().add(name);
        dialog.getContentPane().add(locationX);
        dialog.getContentPane().add(locationY);
        dialog.getContentPane().add(radius);

        // 添加按钮back及其响应事件
        JButton backButton = new JButton("Back");
        dialog.add(backButton);
        backButton.setBounds(400, 425, 100,25);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {new drawDialog();}
        });

        //创建画布对象
        MyCanvas drawArea = new MyCanvas();
        // 绘图区域
        //drawArea.setPreferredSize(new Dimension(300,300));
        drawArea.setVisible(true);
        drawArea.setBounds(0,0,400,400);// 必须设置大小及坐标，因为当前layout为null

        // 创建画圆按钮
        JButton circleButton = new JButton("drawCircle");
        circleButton.setBounds(400, 400, 100, 25);
        dialog.add(circleButton);
        circleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //setY(Integer.parseInt(locationX.getText()));
                shape = OVAL_SHAPE;
                drawArea.repaint();
            }
        });
        //dialog.setLayout(null);?????加上之后不显示？？？？
        dialog.getContentPane().add(drawArea);
    }

    private class MyCanvas extends Canvas {
        //重写paint以绘制图形
        @Override
        public void paint(Graphics g) {
            //绘制圆
            if(shape.equals(OVAL_SHAPE)){
                g.setColor(Color.red);
                g.drawOval(Integer.parseInt(locationX.getText()),Integer.parseInt(locationY.getText()),Integer.parseInt(radius.getText()),Integer.parseInt(radius.getText()));
            }
        }
    }
}
