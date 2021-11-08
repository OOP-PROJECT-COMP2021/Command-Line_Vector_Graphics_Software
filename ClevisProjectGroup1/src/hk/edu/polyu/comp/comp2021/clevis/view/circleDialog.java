package hk.edu.polyu.comp.comp2021.clevis.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class circleDialog extends JDialog {
    private JTextField locationX,locationY,Radius;
    private MyCanvas drawArea;
    private int[][] currentElement;//储存所有参数，使得可以repaint所有图形
    private int numOfButtonClick = 0;//点击按钮的次数

    //弹窗
    public circleDialog() {
        // 整体框架
        JFrame dialog = new JFrame("DrawCircle弹窗");
        dialog.setVisible(true);
        dialog.setBounds(100, 100, 500, 500);
        dialog.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //内容窗格
        Container dialogContainer = this.getContentPane();
        dialogContainer.setBounds(0, 0, 500, 500);
        dialogContainer.setLayout(null);
        dialog.add(dialogContainer);

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

        dialogContainer.add(labelCircle);
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
        name.setBounds(80,350,180,30);
        locationX.setBounds(80,380,180,30);
        locationY.setBounds(80,410,180,30);
        Radius.setBounds(80,440,180,30);
        // 添加输入文本框
        dialogContainer.add(name);
        dialogContainer.add(locationX);
        dialogContainer.add(locationY);
        dialogContainer.add(Radius);

        // 添加按钮back及其响应事件
        JButton backButton = new JButton("Back");
        dialogContainer.add(backButton);
        backButton.setBounds(400, 450, 100,25);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {new drawDialog();}
        });

        //创建画布对象
        drawArea = new MyCanvas();
        // 绘图区域
        drawArea.setBounds(0,0,300,300);// 必须设置大小及坐标，因为当前layout为null

        // 创建画圆按钮
        JButton circleButton = new JButton("drawCircle");
        circleButton.setBounds(400, 400, 100, 25);
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
        deleteButton.setBounds(400, 425, 100,25);
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
