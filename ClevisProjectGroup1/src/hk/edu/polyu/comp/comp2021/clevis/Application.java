package hk.edu.polyu.comp.comp2021.clevis;

import hk.edu.polyu.comp.comp2021.clevis.model.Clevis;
import java.io.*;
import java.util.Random;
import java.util.Scanner;
/** Application Class to run the CLI*/
public class Application{
    /** main() method
     * @param args : intake parameter
     * @throws Exception : exception thrown from main()*/
    public static void main(String[] args) throws Exception {
        String line;
        int position = 0;
        Clevis clevis = new Clevis();
        File writeF = new File("record.txt");
        BufferedWriter out=new BufferedWriter(new FileWriter(writeF));
//        File readF = new File("record.txt");
//        InputStreamReader reader = new InputStreamReader(new FileInputStream(readF));
        FileReader fr = new FileReader("record.txt");
        BufferedReader br = new BufferedReader(fr);
        PrintStream printStream = new PrintStream(new FileOutputStream("log.html"));
        StringBuilder outStr = new StringBuilder();
        System.out.println("Welcome to use our graphics function");
        System.out.println("Input record store in the \"record.txt\"");
//        int lineCount = 0;
//        int times = 0;
//        int undoCount = 0;
        outStr.append("<html>");
        outStr.append("<head>");
        outStr.append("<title>htmlTest</title>");
        outStr.append("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" /></title>");
        outStr.append("<body>");
        outStr.append("<table border=\"1\">");
        outStr.append("<tr>" + "<th>Index</th>" + "<th>cmd</th>" + "</tr>"); // table head
        outStr.append("<table></body></html>");

        while (true) {
            System.out.println("Please type your code:");
            Scanner scanner = new Scanner(System.in);
            out.write(scanner.nextLine()+"\n");
            out.flush();
//            lineCount++;
            line = br.readLine();
            Scanner scan = new Scanner(line);
            outStr.insert(outStr.length() - "<table></body></html>".length(), "<tr>" + "<td>" + position + "</td>" + "<td>" +line+ "</td>" + "</tr>"); // table row 0 (table data:0; table data: rectangle A 1 1 2 2)
            position++;

            if (scan.hasNext()) {
                String str = scan.next();
                /** [Rectangle]*/
                if (str.equals("rectangle")) {
                    String name = scan.next();
                    double inX = Double.parseDouble(scan.next());
                    double inY = Double.parseDouble(scan.next());
                    double inW = Double.parseDouble(scan.next());
                    double inH = Double.parseDouble(scan.next());
                    try{
                        if (!scan.hasNext()) {
                            clevis.drawRectangle(name, inX, inY, inW, inH);
                            System.out.println("Successfully add new "+str+" called " + name+ ", whose top-left corner is at location (" + inX+ "," + inY+ "), and the width and height are " + inW + " and " + inH);
                        }
                    }catch(IllegalArgumentException e){
                        System.out.println("Error for: "+e);
                    }
                }

                /** [Line]*/
                else if (str.equals("line")) {
                    String name = scan.next();
                    double X1 = Double.parseDouble(scan.next());
                    double Y1 = Double.parseDouble(scan.next());
                    double X2 = Double.parseDouble(scan.next());
                    double Y2 = Double.parseDouble(scan.next());
                    try{
                        if (!scan.hasNext()) {
                            clevis.drawLine(name, X1, Y1, X2, Y2);
                            System.out.println("Successfully add new " + str + " called " + name + ", whose two ends are at locations (" + X1 + "," + Y1 + "), and (" + X2 + "," + Y2 + ")");
                        }
                    }catch(IllegalArgumentException e){
                        System.out.println("Error for: "+e);
                    }
                }

                /** [Circle]*/
                else if (str.equals("circle")) {
                    String name = scan.next();
                    double inX = Double.parseDouble(scan.next());
                    double inY = Double.parseDouble(scan.next());
                    double inR = Double.parseDouble(scan.next());

                    try{
                        if (!scan.hasNext()){
                            clevis.drawCircle(name,inX,inY,inR);
                            System.out.println("Successfully add new "+str+" called " + name + ", whose center is at location (" + inX + "," + inY + "), and whose radius is " + inR);
                        }
                    }catch(IllegalArgumentException e){
                        System.out.println("Error for: "+e);
                    }
                }

                /** [Square]*/
                else if (str.equals("square")) {
                    String name = scan.next();
                    double inX = Double.parseDouble(scan.next());
                    double inY = Double.parseDouble(scan.next());
                    double inL = Double.parseDouble(scan.next());
                    try{
                        if (!scan.hasNext()) {
                            clevis.drawSquare(name, inX, inY, inL);
                            System.out.println("Successfully add new " + str + " called " + name + ", whose top-left corner is at location (" + inX + "," + inY + "), and whose side length is " + inL);
                        }
                    }catch(IllegalArgumentException e){
                        System.out.println("Error for: "+e);
                    }
                }

                /** [Group]*/
                else if (str.equals("group")) {
                    String name = scan.next();
                    String[] strList = scan.nextLine().trim().split(" ");
                    StringBuilder sb = new StringBuilder();
                    for (String s : strList) {
                        sb.append(s).append(" ");
                    }
                        try {
                            clevis.createGroup(name, strList);
                            System.out.println("Successfully create a new group called " + name + " which contains " + sb);
                        } catch (IllegalArgumentException e) {
                            System.out.println("Error for " + e);
                        }
                }

                /** [Ungroup]*/
                else if (str.equals("ungroup")){
                    String name = scan.next();
                    try{
                        clevis.unGroup(name);
                        System.out.println("Successfully ungroup the "+name);
                    }catch(IllegalArgumentException e){
                        System.out.println("Unsuccessfully ungroup "+name+" because can't find the name in storage!");
                    }
                }

                /** [Delete]*/
                else if (str.equals("delete")){
                    String name = scan.next();
                    try{
                        if (!scan.hasNext()) {
                            clevis.deleteShapeWithName(name);
                            System.out.println("Successfully delete the shape called " + name);
                        }
                    }catch(IllegalArgumentException e){
                        System.out.println("Unsuccessfully delete because can't find the name independently in storage!");
                    }
                }

                /** [boudningbox]*/
                else if (str.equals("boundingbox")){
                    String name = scan.next();
                    try {
                        if (!scan.hasNext()) {
                            System.out.println(clevis.createBoundingBox(name));
                            System.out.println("Successfully create boundingbox of " + name);
                        }
                    }
                    catch(IllegalArgumentException e){
                        System.out.println("Error for: "+e);
                    }
                }

                /** [move]*/
                else if (str.equals("move")){
                    String name = scan.next();
                    double inX = Double.parseDouble(scan.next());
                    double inY = Double.parseDouble(scan.next());
                    try {
                        if (!scan.hasNext()) {
                            clevis.moveShape(name, inX, inY);
                            System.out.println("Successfully move " + inX + " on X-axis and move " + inY + " on Y-axis");
                        }
                    }
                    catch(IllegalArgumentException e){
                        System.out.println("Error for: "+e);
                    }
                }

                /** [pick-and-move]*/
                else if (str.equals("pick-and-move")){
                    double inX = Double.parseDouble(scan.next());
                    double inY = Double.parseDouble(scan.next());
                    double inDx = Double.parseDouble(scan.next());
                    double inDy = Double.parseDouble(scan.next());
                    try {
                        if (!scan.hasNext()) {
                            clevis.pickAndMoveShape(inX, inY, inDx, inDy);
                            System.out.println("Successfully pick and move point (" + inX + "," + inY + ") to point (" + (inDx + inX) + "," + (inDy + inY) + ")");
                        }
                    } catch(IllegalArgumentException e){
                        System.out.println("Error for: "+e);
                    }
                }

                /** [intersect]*/
                else if (str.equals("intersect")){
                    String shape1 = scan.next();
                    String shape2 = scan.next();
                    try{
                        if (!scan.hasNext()) {
                            clevis.isIntersected(shape1, shape2);
                            if (clevis.isIntersected(shape1, shape2)) {
                                System.out.println("They are intersected!");
                            } else {
                                System.out.println("They are not intersected");
                            }
                        }
                    }
                    catch(IllegalArgumentException e){
                        System.out.println("Error for: "+e);
                    }

                }

                /** [List]*/
                else if (str.equals("list")){
                    String name = scan.next();
                    try {
                        if (!scan.hasNext()) {
                            System.out.println();
                            System.out.println("Here is the information about " + clevis.listShape(name));
                        }
                    }catch(IllegalArgumentException e){
                        System.out.println("Unsuccessfully list "+name+" because can't find the name in storage!");
                    }
                }

                /** [ListAll]*/
                else if (str.equalsIgnoreCase("listAll")){
                    try {
                        if (!scan.hasNext()) {
                            System.out.println(clevis.listAllShape());
                        }
                    }catch(IllegalArgumentException e){
                        System.out.println("Unsuccessfully listAll because can't find the name in storage!");
                    }
                }

                /** [Undo]*/
                else if(str.equals("undo")){
                    try {
                        if (!scan.hasNext()) {
//                        lineCount-=undoCount;
//                        FileReader fr = new FileReader("record.txt");
//                        BufferedReader BR = new BufferedReader(fr);
//                        int count= 0;
//                        String targetLine = "";
//                        String Line = "";
//                        while(count < lineCount-1){
//                            if (count >= lineCount-1) {
//                                Line = BR.readLine();
//                            }
//                            else{
//                                targetLine = BR.readLine();
//                                Line = targetLine;
//                            }
//                            count++;
//                        }
//                        undoCount +=2;
//                        if (Line.split(" ")[0].equals("boundingbox")||Line.split(" ")[0].equals("intersect")||Line.split(" ")[0].equals("list")||Line.equals("listAll")||Line.equals("quit")){
//
//                        }
//                        else {
                            System.out.println("Already undo!");
                            clevis.UndoControl();
//                        }
                        }
                    }catch (IllegalArgumentException e){
                        System.out.println("No operation for undo！");
                    }
                }

                /** [Redo]*/
                else if (str.equals("redo")){
                    try {
                        if (!scan.hasNext()) {
                            clevis.RedoControl();
                            System.out.println("Already redo!");
                        }
                    }catch (IllegalArgumentException e){
                        System.out.println("No operation for undo！");
                    }
                }

                /** [quit]*/
                else if (str.equalsIgnoreCase("quit")){
                    printStream.println(outStr.toString());
                    break;
                }
            }
        }
    }
}
/*        int quit = 0;
        int i = 0;
        System.out.println("Function List:\n1.Add graphics\n2.Delete graphics\n3.Graphics group\n4.Graphics boundary\n5.Move graphics\n6.Graphics intersection\n7.Graphics information\n8.Quit");
        Scanner scan = new Scanner(System.in);
            while (scan.hasNextInt()) {
                while (quit==0) {
                    if (i !=0){
                        System.out.println("Function List:\n1.Add graphics\n2.Delete graphics\n3.Graphics group\n4.Graphics boundary\n5.Move graphics\n6.Graphics intersection\n7.Graphics information\n8.Quit");
                    }
                    i++;
                    int choice = scan.nextInt();
                switch (choice) {
                    case 1:
                        System.out.println("Please input the graphic that you want to add: ");
                        break;
                    case 2:
                        System.out.println("Please input the graphic that you want to delete: ");
                        break;
                    case 3:
                        System.out.println("Do you want to group or ungroup, Please input 1 or 2: ");
                        choice = scan.nextInt();
                        if (choice == 1) {
                            System.out.println("Please input graphics that you want to group together: ");
                            break;

                        } else if (choice == 2) {
                            System.out.println("Please input graphics that you want to ungroup: ");
                            break;
                        } else {
                            System.out.println("please choose the correct choice");
                            break;
                        }

                    case 4:
                        System.out.println("Please input the graphics boundary that you want to calculate: ");
                        break;

                    case 5:
                        System.out.println("Do you want to move the shape or the shape contains target point, Please input 1 or 2: ");
                        choice = scan.nextInt();
                        if (choice == 1) {
                            System.out.println("Please input the shape that you want to move: ");
                            break;
                        } else if (choice == 2) {
                            System.out.println("Please input the shape that contains the point that you want pick and move: ");
                            break;
                        } else {
                            System.out.println("please choose the correct choice");
                            break;
                        }

                    case 6:
                        System.out.println("Please input two shapes for judging intersection: ");
                        break;

                    case 7:
                        System.out.println("Do you want to print all information or a shape information, Please input 1 or 2: ");
                        choice = scan.nextInt();
                        if (choice == 1) {
                                System.out.println("Layout of all current graphics information: ");
                                break;
                            }   else if (choice == 2) {
                                System.out.println("Layout of target shape's information: ");
                                break;
                            }   else {
                                System.out.println("please choose the correct choice");
                                break;
                        }

                    case 8:
                        System.out.println("Thanks for your using!");
                        quit=1;
                        break;

                }
            }
                break;
            }
    }
}

 */