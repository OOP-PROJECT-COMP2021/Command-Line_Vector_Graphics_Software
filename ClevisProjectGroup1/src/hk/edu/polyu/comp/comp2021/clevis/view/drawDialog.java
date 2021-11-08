package hk.edu.polyu.comp.comp2021.clevis.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class drawDialog extends JDialog {
    //弹窗
    public drawDialog() {
        //整体框架
        JFrame dialog = new JFrame("Draw 弹窗");
        dialog.setVisible(true);
        dialog.setBounds(100, 100, 500, 500);
        dialog.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // 内容窗格
        Container drawContainer = this.getContentPane();
        drawContainer.setLayout(null);
        dialog.add(drawContainer);

        // 按钮back的响应事件
        JButton backButton = new JButton("Back");
        backButton.setBounds(400, 400, 100, 50);
        drawContainer.add(backButton);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dialog.setVisible(false);
                new ClevisView();
            }
        });

        // 画圆按键
        JButton circleButton = new JButton("Circle");
        drawContainer.add(circleButton);
        circleButton.setBounds(0, 400, 100, 50);
        circleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dialog.setVisible(false);
                new circleDialog();
            }
        });

        // 画矩形按键
        JButton recButton = new JButton("Rectangle");
        drawContainer.add(recButton);
        recButton.setBounds(125, 400, 100, 50);
        recButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dialog.setVisible(false);
                new recDialog();
            }
        });
    }
}
