package hk.edu.polyu.comp.comp2021.clevis;

import hk.edu.polyu.comp.comp2021.clevis.view.ClevisGUI;

import java.util.Scanner;

public class Application {
    public static void main(String[] args) throws Exception {
        Scanner input = new Scanner(System.in);
        System.out.print("Launch CLI(0) or GUI(1)? Enter 0 or 1 :");
        String flag = input.next();
        if (flag.equals("0")) {new Clevis(args[1],args[3]);}
        else if(flag.equals("1")) {new ClevisGUI(args[1],args[3]);}
        else {System.exit(0);}
    }
}
