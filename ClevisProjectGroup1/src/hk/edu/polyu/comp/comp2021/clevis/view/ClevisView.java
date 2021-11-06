package hk.edu.polyu.comp.comp2021.clevis.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.ImageObserver;
import java.text.AttributedCharacterIterator;

public class ClevisView extends JFrame{
    //主线程
    public static void main(String[] args) {
        new ClevisView();
    }
    // 初始化
    public ClevisView(){
        //主界面
        //JFrame mainGUI = new JFrame();
        this.setVisible(true);
        this.setBounds(100,100,500,500);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        //container
        Container container = this.getContentPane();
        //绝对布局
        container.setLayout(null);
        //设置背景颜色
        container.setBackground(Color.gray);
        //按钮draw实现
        JButton drawButton = new JButton("Draw");
        drawButton.setBounds(0,400,100,50);
        drawButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new drawDialog();
            }
        });
        container.add(drawButton);
        // 按钮quit的响应事件
        JButton quitButton = new JButton("Quit");
        quitButton.setBounds(400,400,100,50);
        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        container.add(quitButton);
    }
}


