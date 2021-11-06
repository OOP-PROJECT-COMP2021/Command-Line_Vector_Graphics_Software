package hk.edu.polyu.comp.comp2021.clevis.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class drawDialog extends JDialog {
    //弹窗
    public drawDialog() {
        JFrame dialog = new JFrame("Draw 弹窗");
        dialog.setVisible(true);
        dialog.setLayout(null);
        dialog.setBounds(100, 100, 500, 500);

        // 按钮back的响应事件
        JButton backButton = new JButton("Back");
        backButton.setBounds(400, 400, 100, 50);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ClevisView();
            }
        });
        dialog.add(backButton);

        // 画圆按键
        JButton circleButton = new JButton("Circle");
        dialog.add(circleButton);
        circleButton.setBounds(0, 400, 100, 50);
        circleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new circleDialog();
            }
        });

        // 画矩形按键
        JButton recButton = new JButton("Rectangle");
        dialog.add(recButton);
        recButton.setBounds(125, 400, 100, 50);
        recButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new recDialog();
            }
        });
    }
}
