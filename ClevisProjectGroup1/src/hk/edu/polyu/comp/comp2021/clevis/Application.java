package hk.edu.polyu.comp.comp2021.clevis;

import hk.edu.polyu.comp.comp2021.clevis.model.Clevis;
import org.w3c.dom.DOMImplementation;

import java.awt.*;
import java.util.Scanner;

import static java.rmi.activation.ActivationGroup.createGroup;
import static java.time.Clock.system;

public class Application{

    public static void main(String[] args) throws Exception {
        Clevis clevis = new Clevis();
        // Initialize and utilize the system
        System.out.println("Welcome to use our graphics function");
        while (true) {
            System.out.println("Please type your code:");
            Scanner scan = new Scanner(System.in);
            if (scan.hasNext()) {
                String str = scan.next();
                /** [Rectangle]*/
                if (str.equals("rectangle")) {
                    String name = scan.next();
                    Double inX = Double.parseDouble(scan.next());
                    Double inY = Double.parseDouble(scan.next());
                    Double inW = Double.parseDouble(scan.next());
                    Double inH = Double.parseDouble(scan.next());
                    try{
                        clevis.drawRectangle(name,inX,inY,inW,inH);
                    }catch(IllegalArgumentException e){
                        System.out.println("Error for: "+e);
                    }
                    System.out.println("Successfully add new "+str+" called " + name+ ", whose top-left corner is at location (" + inX+ "," + inY+ "), and the width and height are " + inW + " and " + inH);
                }

                /** [Line]*/
                else if (str.equals("line")) {
                    String name = scan.next();
                    Double X1 = Double.parseDouble(scan.next());
                    Double Y1 = Double.parseDouble(scan.next());
                    Double X2 = Double.parseDouble(scan.next());
                    Double Y2 = Double.parseDouble(scan.next());
                    try{
                        clevis.drawLine(name,X1,Y1,X2,Y2);
                    }catch(IllegalArgumentException e){
                        System.out.println("Error for: "+e);
                    }
                    System.out.println("Successfully add new "+str+" called " + name + ", whose two ends are at locations (" + X1 + "," + Y1 + "), and (" + X2 + "," + Y2+")");
                }

                /** [Circle]*/
                else if (str.equals("circle")) {
                    String name = scan.next();
                    Double inX = Double.parseDouble(scan.next());
                    Double inY = Double.parseDouble(scan.next());
                    Double inR = Double.parseDouble(scan.next());
                    try{
                        clevis.drawCircle(name,inX,inY,inR);
                    }catch(IllegalArgumentException e){
                        System.out.println("Error for: "+e);
                    }
                    System.out.println("Successfully add new "+str+" called " + name + ", whose center is at location (" + inX + "," + inY + "), and whose radius is " + inR);
                }

                /** [Square]*/
                else if (str.equals("square")) {
                    String name = scan.next();
                    Double inX = Double.parseDouble(scan.next());
                    Double inY = Double.parseDouble(scan.next());
                    Double inL = Double.parseDouble(scan.next());
                    try{
                        clevis.drawSquare(name,inX,inY,inL);
                    }catch(IllegalArgumentException e){
                        System.out.println("Error for: "+e);
                    }
                    System.out.println("Successfully add new "+str+" called " + name + ", whose top-left corner is at location (" + inX + "," + inY + "), and whose side length is " + inL);
                }

                /** [Group]*/
                else if (str.equals("group")) {
                    String name = scan.next();
                    String[] strList = scan.nextLine().trim().split(" ");
                    StringBuilder sb = new StringBuilder();
                    for (String s : strList) {
                        sb.append(s + " ");
                    }
                        try {
                            clevis.createGroup(name, strList);
                        } catch (IllegalArgumentException e) {
                            System.out.println("Error for " + e);
                        }
                    System.out.println("Successfully create a new group called " + name + " which contains " + sb);
                }

                /** [Ungroup]*/
                else if (str.equals("ungroup")){
                    String name = scan.next();
                    if (!clevis.containsName(name)){
                        System.out.println("Unsuccessfully ungroup "+name+" because can't find the name in storage!");
                    }
                    else{
                        clevis.unGroup(name);
                        System.out.println("Successfully ungroup the "+name);
                    }
                }

                /** [Delete]*/
                else if (str.equals("delete")){
                    String name = scan.next();
                    if (!clevis.containsName(name)){
                        System.out.println("Unsuccessfully delete because can't find the name in storage!");
                    }
                    else{
                        clevis.deleteShapeWithName(name);
                        System.out.println("Successfully delete the shape called "+name);
                    }
                }

                /** [boudningbox]*/
                else if (str.equals("boundingbox")){
                    String name = scan.next();
                    try{
                        clevis.createBoundingBox(name);
                    }
                    catch(IllegalArgumentException e){
                        System.out.println("Error for: "+e);
                    }
                    System.out.println("Successfully create boundingbox of "+name+"!");
                }

                /** [move]*/
                else if (str.equals("move")){
                    String name = scan.next();
                    double inX = Double.parseDouble(scan.next());
                    double inY = Double.parseDouble(scan.next());
                    try{
                        clevis.moveShape(name,inX,inY);
                    }
                    catch(IllegalArgumentException e){
                        System.out.println("Error for: "+e);
                    }
                    System.out.println("Successfully move to point ("+inX+","+inY+")");
                }

                /** [pick-and-move]*/
                else if (str.equals("pick-and-move")){
                    double inX = Double.parseDouble(scan.next());
                    double inY = Double.parseDouble(scan.next());
                    double inDx = Double.parseDouble(scan.next());
                    double inDy = Double.parseDouble(scan.next());
                    try{
                        clevis.pickAndMoveShape(inX,inY,inDx,inDy);
                    }catch(IllegalArgumentException e){
                        System.out.println("Error for: "+e);
                    }
                    System.out.println("Successfully pick and move point ("+inX+","+inY+") to point ("+inDx+","+inDy+")");
                }

                /** [intersect]*/
                else if (str.equals("intersect")){
                    String shape1 = scan.next();
                    String shape2 = scan.next();
                    try{
                        clevis.isIntersected(shape1,shape2);
                    }
                    catch(IllegalArgumentException e){
                        System.out.println("Error for: "+e);
                    }
                    if (clevis.isIntersected(shape1,shape2)==true){
                        System.out.println("");
                    }
                }

                /** [List]*/
                else if (str.equals("list")){
                    String name = scan.next();
                    if (!clevis.containsName(name)){
                        System.out.println("Unsuccessfully list "+name+" because can't find the name in storage!");
                    }
                    else{
                        System.out.println();
                        System.out.println("Here is the information about "+clevis.listShape(name));
                    }
                }

                /** [ListAll]*/
                else if (str.equalsIgnoreCase("listAll")){
                    if (clevis.getSize()==0){
                        System.out.println("Unsuccessfully listAll because can't find the name in storage!");
                    }
                    else{
                        System.out.println(clevis.listAllShape());
                    }
                }

                else if (str.equalsIgnoreCase("quit")){
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