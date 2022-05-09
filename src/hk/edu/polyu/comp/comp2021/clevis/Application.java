package hk.edu.polyu.comp.comp2021.clevis;

import hk.edu.polyu.comp.comp2021.clevis.view.ClevisGUI;

import java.util.Scanner;

/** An Application to launch the Clevis or ClevisGUI*/
public class Application {
    /** main method to run
     * @param args : args
     * @throws Exception : exception from Clevis and ClevisGUI*/
    public static void main(String[] args) throws Exception {
        String strHtml;
        String strTxt;
        if(args.length != 0) {strHtml = args[1];}
        else {strHtml = "log.html";}
        if(args.length != 0) {strTxt = args[3];}
        else {strTxt = "log.txt";}

        Scanner input = new Scanner(System.in);
        System.out.print("Launch CLI(0) or GUI(1)? Enter 0 or 1 :");
        String flag = input.next();
        if (flag.equals("0")) {new Clevis(strHtml,strTxt);}
        else if(flag.equals("1")) {
            System.out.println("ClevisGUI launched!"); new ClevisGUI(strHtml,strTxt);}
        else {System.exit(0);}
    }
}
