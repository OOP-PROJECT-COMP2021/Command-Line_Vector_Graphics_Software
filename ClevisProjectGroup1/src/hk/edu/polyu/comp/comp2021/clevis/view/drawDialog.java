package hk.edu.polyu.comp.comp2021.clevis.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/** Dialog of draw each graphic */
class drawDialog extends JDialog {

    private final int WIDTH = 500;
    private final int HEIGHT = 500;

    private final int BUTTON_WIDTH = 100;
    private final int BUTTON_HEIGHT = 50;

    private final int DRAW_REC_BTN_LOCATION_X = 125;
    private final int DRAW_REC_BTN_LOCATION_Y = 400;
    private final int BACK_BTN_LOCATION_Y = 400;
    private final int BACK_BTN_LOCATION_X = 400;
    private final int DRAW_CIRCLE_BTN_LOCATION_Y = 400;

    //弹窗
    /** constructor of drawDialog */
    public drawDialog() {
        //整体框架
        JFrame dialog = new JFrame("Draw 弹窗");
        dialog.setVisible(true);
        dialog.setBounds(0, 0, WIDTH, HEIGHT);
        dialog.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // 内容窗格
        Container drawContainer = this.getContentPane();
        drawContainer.setLayout(null);
        dialog.add(drawContainer);

        // 按钮back的响应事件
        JButton backButton = new JButton("Back");
        backButton.setBounds(BACK_BTN_LOCATION_X, BACK_BTN_LOCATION_Y, BUTTON_WIDTH, BUTTON_HEIGHT);
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
        circleButton.setBounds(0, DRAW_CIRCLE_BTN_LOCATION_Y, BUTTON_WIDTH, BUTTON_HEIGHT);
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
        recButton.setBounds(DRAW_REC_BTN_LOCATION_X, DRAW_REC_BTN_LOCATION_Y, BUTTON_WIDTH, BUTTON_HEIGHT);
        recButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dialog.setVisible(false);
                new recDialog();
            }
        });
    }
}
