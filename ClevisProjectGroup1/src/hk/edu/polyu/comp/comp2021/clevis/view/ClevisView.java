package hk.edu.polyu.comp.comp2021.clevis.view;

import hk.edu.polyu.comp.comp2021.clevis.model.Clevis;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/** the main GUI */
public class ClevisView extends JFrame{
    Clevis clevis = new Clevis();

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
        container.setBackground(Color.gray);
        // Panel for main page
        JPanel drawContainer1 = new JPanel();
        JPanel drawContainer2 = new JPanel();
        drawContainer1.setVisible(true);
        drawContainer2.setVisible(true);
        drawContainer1.setBounds(0,300,WIDTH,200);
        drawContainer2.setBounds(0,300,WIDTH,200);
        drawContainer1.setLayout(null);
        drawContainer2.setLayout(null);
        container.add(drawContainer1);
        container.add(drawContainer2);
        //按钮draw、quit
        JButton drawButton = new JButton("Draw");
        drawButton.setBounds(LocationX,DRAW_BTN_LOCATION_Y,BUTTON_WIDTH,BUTTON_HEIGHT);
        drawContainer1.add(drawButton);
        JButton quitButton = new JButton("Quit");
        quitButton.setBounds(QUIT_BTN_LOCATION_X,QUIT_BTN_LOCATION_Y,BUTTON_WIDTH,BUTTON_HEIGHT);
        drawContainer1.add(quitButton);
        drawButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                drawContainer1.setVisible(false);
                //new drawDialog();
            }
        });

        drawContainer2.setLayout(null);
        this.add(drawContainer2);
        JButton circleButton = new JButton("Circle");
        drawContainer2.add(circleButton);
        circleButton.setBounds(0, DRAW_CIRCLE_BTN_LOCATION_Y, BUTTON_WIDTH, BUTTON_HEIGHT);

        // 按钮quit的响应事件

        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                System.exit(0);
            }
        });

    }
}


