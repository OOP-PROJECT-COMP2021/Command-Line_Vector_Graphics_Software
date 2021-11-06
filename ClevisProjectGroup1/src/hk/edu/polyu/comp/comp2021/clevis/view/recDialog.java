package hk.edu.polyu.comp.comp2021.clevis.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class recDialog extends JDialog {
    private final String RECT_SHAPE = "rect";
    private String shape = "";
    private JTextField locationX,locationY,Width,Height;

    //弹窗
    public recDialog() {
        JFrame dialog = new JFrame("DrawCircle弹窗");
        dialog.setVisible(true);
        dialog.setBounds(100, 100, 500, 500);
        dialog.setLayout(null);

        // 输入参数界面
        // 标签
        JLabel labelName = new JLabel();
        JLabel labelLocationX = new JLabel();
        JLabel labelLocationY = new JLabel();
        JLabel labelWidth = new JLabel();
        JLabel labelHeight = new JLabel();

        labelName.setBounds(0,320,50,30);
        labelLocationX.setBounds(0,350,80,30);
        labelLocationY.setBounds(0,380,80,30);
        labelWidth.setBounds(0,410,80,30);
        labelHeight.setBounds(0,440,80,30);

        labelName.setText("Name:");
        labelLocationX.setText("LocationX:");
        labelLocationY.setText("LocationY:");
        labelWidth.setText("Width:");
        labelHeight.setText("Height:");

        dialog.add(labelName);
        dialog.add(labelLocationX);
        dialog.add(labelLocationY);
        dialog.add(labelWidth);
        dialog.add(labelHeight);

        // 文本框
        JTextField name = new JTextField("");
        locationX = new JTextField("");
        locationY = new JTextField("");
        Width = new JTextField("");
        Height = new JTextField("");

        // 设置输入文本框的位置信息
        name.setBounds(80,320,180,30);
        locationX.setBounds(80,350,180,30);
        locationY.setBounds(80,380,180,30);
        Width.setBounds(80,410,180,30);
        Height.setBounds(80,440,180,30);

        // 添加输入文本框
        dialog.add(name);
        dialog.add(locationX);
        dialog.add(locationY);
        dialog.add(Width);
        dialog.add(Height);

        // 添加按钮back及其响应事件
        JButton backButton = new JButton("Back");
        dialog.add(backButton);
        backButton.setBounds(383,425,100,25);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new drawDialog();
            }
        });

        //创建画布对象
        MyCanvas drawArea = new MyCanvas();
        // 绘图区域
        drawArea.setPreferredSize(new Dimension(300,300));
        drawArea.setBounds(0,0,400,400);
        drawArea.setVisible(true);

        // 创建画圆按钮
        JButton circleButton = new JButton("drawRectangle");
        circleButton.setBounds(375, 400, 125, 25);
        dialog.add(circleButton);
        circleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                shape = RECT_SHAPE;
                drawArea.repaint();
            }
        });
        dialog.add(drawArea);
    }

    private class MyCanvas extends Canvas {
        //重写paint以绘制图形
        @Override
        public void paint(Graphics g) {
            //绘制图形
            if(shape.equals((RECT_SHAPE))){
                //绘制圆
                g.setColor(Color.BLACK);
                g.drawRect(Integer.parseInt(locationX.getText()),Integer.parseInt(locationY.getText()),Integer.parseInt(Width.getText()),Integer.parseInt(Height.getText()));
            }
        }
    }
}
