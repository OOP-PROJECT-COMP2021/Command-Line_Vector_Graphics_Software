package hk.edu.polyu.comp.comp2021.clevis.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClevisView extends JFrame{
    //all component' parameters
    private final int WIDTH = 500;
    private final int HEIGHT = 500;
    private final int LocationX = 0;

    private final int BUTTON_WIDTH = 100;
    private final int BUTTON_HEIGHT = 50;

    private final int DRAW_BTN_LOCATION_Y = 400;
    private final int QUIT_BTN_LOCATION_Y = 400;
    private final int QUIT_BTN_LOCATION_X = 400;

    public static void main(String[] args) {
        new ClevisView();
    }
    // 初始化
    public ClevisView(){
        //主界面
        //JFrame mainGUI = new JFrame();
        this.setVisible(true);
        this.setBounds(0,0,WIDTH,HEIGHT);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        //container
        Container container = this.getContentPane();
        //绝对布局
        container.setLayout(null);
        //设置背景颜色
        container.setBackground(Color.gray);
        //按钮draw实现
        JButton drawButton = new JButton("Draw");
        drawButton.setBounds(LocationX,DRAW_BTN_LOCATION_Y,BUTTON_WIDTH,BUTTON_HEIGHT);
        container.add(drawButton);
        drawButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                new drawDialog();
            }
        });

        // 按钮quit的响应事件
        JButton quitButton = new JButton("Quit");
        quitButton.setBounds(QUIT_BTN_LOCATION_X,QUIT_BTN_LOCATION_Y,BUTTON_WIDTH,BUTTON_HEIGHT);
        container.add(quitButton);
        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }
}


