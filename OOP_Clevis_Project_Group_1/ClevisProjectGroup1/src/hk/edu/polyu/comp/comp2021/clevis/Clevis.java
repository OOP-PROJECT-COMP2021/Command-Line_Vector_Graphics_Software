package hk.edu.polyu.comp.comp2021.clevis;

import hk.edu.polyu.comp.comp2021.clevis.model.ClevisModel;
import java.io.*;
import java.util.NoSuchElementException;
import java.util.Scanner;

/** Application Class to run the CLI*/
public class Clevis {
    private String line;
    private int position = 0;
    private String txt;
    private String html;
    private ClevisModel clevisModel = new ClevisModel();
    private StringBuilder outStr = new StringBuilder();
    private File fw;
    private BufferedWriter out;
    private FileReader fr;
    private BufferedReader br;
    private PrintStream printStream;
    /** Constructor
     * @param inTxt : txt name
     * @param inHtml : html name
     * @throws Exception :throw*/
    public Clevis(String inHtml, String inTxt) throws Exception{
        fw = new File(inTxt);
        out = new BufferedWriter(new FileWriter(fw));
        fr = new FileReader(inTxt);
        br = new BufferedReader(fr);
        printStream = new PrintStream(new FileOutputStream(inHtml));
        txt = inTxt;
        html = inHtml;
        initialize();
    }

    private void initialize() throws Exception {

        System.out.println("Welcome to use the graphics functions with CLI!");
        System.out.println("All the Input records are stored in the \"" + txt + "\" and \"" + html +"\"");
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
            out.write(scanner.nextLine() + "\n");
            out.flush();
            line = br.readLine();
            Scanner scan = new Scanner(line);
            outStr.insert(outStr.length() - "<table></body></html>".length(), "<tr>" + "<td>" + position + "</td>" + "<td>" + line + "</td>" + "</tr>"); // table row 0 (table data:0; table data: rectangle A 1 1 2 2)
            position++;

            if (scan.hasNext()) {
                String str = scan.next();

                /** [Rectangle]*/
                if (str.equals("rectangle")) {
                    try {
                        if (!scan.hasNext()) {
                            try {
                                throw new NoSuchElementException();
                            } catch (NoSuchElementException e) {
                                System.out.println("Error for " + e + ". You need input data to realize the function!");
                            }
                        } else {
                            String name = scan.next();
                            double[] parameters = new double[4];
                            for (int i = 0; i < parameters.length; i++) {
                                if (scan.hasNext()) {
                                    parameters[i] = Double.parseDouble(scan.next());
                                } else {
                                    throw new IllegalArgumentException();
                                }
                            }
                            if (!scan.hasNext()) {
                                clevisModel.drawRectangle(name, parameters[0], parameters[1], parameters[2], parameters[3]);
                                System.out.println("Successfully add new " + str + " called " + name + ", whose top-left corner is at location (" + parameters[0] + "," + parameters[1] + "), and the width and height are " + parameters[2] + " and " + parameters[3]);
                            } else {
                                throw new IllegalArgumentException();
                            }
                        }
                    } catch (IllegalArgumentException e) {
                        System.out.println("Error for: " + e + ". Please find error situation in User Manual");
                    }
                }

                /** [Line]*/
                else if (str.equals("line")) {
                    try {
                        if (!scan.hasNext()) {
                            try {
                                throw new NoSuchElementException();
                            } catch (NoSuchElementException e) {
                                System.out.println("Error for " + e + ". You need input data to realize the function!");
                            }
                        } else {
                            String name = scan.next();
                            double[] parameters = new double[4];
                            for (int i = 0; i < parameters.length; i++) {
                                if (scan.hasNext()) {
                                    parameters[i] = Double.parseDouble(scan.next());
                                } else {
                                    throw new IllegalArgumentException();
                                }
                            }
                            if (!scan.hasNext()) {
                                clevisModel.drawLine(name, parameters[0], parameters[1], parameters[2], parameters[3]);
                                System.out.println("Successfully add new " + str + " called " + name + ", whose two ends are at locations (" + parameters[0] + "," + parameters[1] + "), and (" + parameters[2] + "," + parameters[3] + ")");
                            } else {
                                throw new IllegalArgumentException();
                            }
                        }
                    } catch (IllegalArgumentException e) {
                        System.out.println("Error for: " + e + ". Please find error situation in User Manual");
                    }
                }

                /** [Circle]*/
                else if (str.equals("circle")) {
                    try {
                        if (!scan.hasNext()) {
                            try {
                                throw new NoSuchElementException();
                            } catch (NoSuchElementException e) {
                                System.out.println("Error for " + e + ". You need input data to realize the function!");
                            }
                        } else {
                            String name = scan.next();
                            double[] parameters = new double[3];
                            for (int i = 0; i < parameters.length; i++) {
                                if (scan.hasNext()) {
                                    parameters[i] = Double.parseDouble(scan.next());
                                } else {
                                    throw new IllegalArgumentException();
                                }
                            }
                            if (!scan.hasNext()) {
                                clevisModel.drawCircle(name, parameters[0], parameters[1], parameters[2]);
                                System.out.println("Successfully add new " + str + " called " + name + ", whose center is at location (" + parameters[0] + "," + parameters[1] + "), and whose radius is " + parameters[2]);
                            } else {
                                throw new IllegalArgumentException();
                            }
                        }
                    } catch (IllegalArgumentException e) {
                        System.out.println("Error for: " + e + ". Please find error situation in User Manual");
                    }
                }

                /** [Square]*/
                else if (str.equals("square")) {
                    try {
                        if (!scan.hasNext()) {
                            try {
                                throw new NoSuchElementException();
                            } catch (NoSuchElementException e) {
                                System.out.println("Error for " + e + ". You need input data to realize the function!");
                            }
                        } else {
                            String name = scan.next();
                            double[] parameters = new double[3];
                            for (int i = 0; i < parameters.length; i++) {
                                if (scan.hasNext()) {
                                    parameters[i] = Double.parseDouble(scan.next());
                                } else {
                                    throw new IllegalArgumentException();
                                }
                            }
                            if (!scan.hasNext()) {
                                clevisModel.drawSquare(name, parameters[0], parameters[1], parameters[2]);
                                System.out.println("Successfully add new " + str + " called " + name + ", whose top-left corner is at location (" + parameters[0] + "," + parameters[1] + "), and whose side length is " + parameters[2]);
                            } else {
                                throw new IllegalArgumentException();
                            }
                        }
                    } catch (IllegalArgumentException e) {
                        System.out.println("Error for: " + e + ". Please find error situation in User Manual");
                    }
                }

                /** [Group]*/
                else if (str.equals("group")) {
                    try {
                        if (!scan.hasNext()) {
                            try {
                                throw new NoSuchElementException();
                            } catch (NoSuchElementException e) {
                                System.out.println("Error for " + e + ". You need input data to realize the function!");
                            }
                        } else {
                            String name = scan.next();
                            String[] strList = scan.nextLine().trim().split(" ");
                            StringBuilder sb = new StringBuilder();
                            for (String s : strList) {
                                sb.append(s).append(" ");
                            }
                            clevisModel.createGroup(name, strList);
                            System.out.println("Successfully create a new group called " + name + " which contains " + sb);
                        }
                    } catch (IllegalArgumentException e) {
                        System.out.println("Error for: " + e + ". Please find error situation in User Manual");
                    }
                }

                /** [Ungroup]*/
                else if (str.equals("ungroup")) {
                    try {
                        if (!scan.hasNext()) {
                            try {
                                throw new NoSuchElementException();
                            } catch (NoSuchElementException e) {
                                System.out.println("Error for " + e + ". You need input data to realize the function!");
                            }
                        } else {
                            String name = scan.next();
                            if (!scan.hasNext()) {
                                clevisModel.unGroup(name);
                                System.out.println("Successfully ungroup the " + name);
                            } else {
                                throw new IllegalStateException();
                            }
                        }
                    } catch (IllegalArgumentException e) {
                        System.out.println("Error for: " + e + ". Please find error situation in User Manual");
                    }
                }

                /** [Delete]*/
                else if (str.equals("delete")) {
                    try {
                        if (!scan.hasNext()) {
                            try {
                                throw new NoSuchElementException();
                            } catch (NoSuchElementException e) {
                                System.out.println("Error for " + e + ". You need input data to realize the function!");
                            }
                        } else {
                            String name = scan.next();
                            if (!scan.hasNext()) {
                                clevisModel.deleteShapeWithName(name);
                                System.out.println("Successfully delete the shape called " + name);
                            } else {
                                throw new IllegalArgumentException();
                            }
                        }
                    } catch (IllegalArgumentException e) {
                        System.out.println("Error for: " + e + ". Please find error situation in User Manual");
                    }
                }

                /** [boundingbox]*/
                else if (str.equals("boundingbox")) {
                    try {
                        if (!scan.hasNext()) {
                            try {
                                throw new NoSuchElementException();
                            } catch (NoSuchElementException e) {
                                System.out.println("Error for " + e + ". You need input data to realize the function!");
                            }
                        } else {
                            String name = scan.next();
                            if (!scan.hasNext()) {
                                System.out.println(clevisModel.createBoundingBox(name));
                                System.out.println("Successfully create boundingbox of " + name);
                            } else {
                                throw new IllegalStateException();
                            }
                        }
                    } catch (IllegalArgumentException e) {
                        System.out.println("Error for: " + e + ". Please find error situation in User Manual");
                    }
                }

                /** [move]*/
                else if (str.equals("move")) {
                    try {
                        if (!scan.hasNext()) {
                            try {
                                throw new NoSuchElementException();
                            } catch (NoSuchElementException e) {
                                System.out.println("Error for " + e + ". You need input data to realize the function!");
                            }
                        } else {
                            String name = scan.next();
                            double[] parameters = new double[2];
                            for (int i = 0; i < parameters.length; i++) {
                                if (scan.hasNext()) {
                                    parameters[i] = Double.parseDouble(scan.next());
                                } else {
                                    throw new IllegalArgumentException();
                                }
                            }
                            if (!scan.hasNext()) {
                                clevisModel.moveShape(name, parameters[0], parameters[1]);
                                System.out.println("Successfully move " + name + " " + parameters[0] + " on X-axis and move " + parameters[1] + " on Y-axis");
                            } else {
                                throw new IllegalArgumentException();
                            }
                        }
                    } catch (IllegalArgumentException e) {
                        System.out.println("Error for: " + e + ". Please find error situation in User Manual");
                    }
                }

                /** [pick-and-move]*/
                else if (str.equals("pick-and-move")) {
                    try {
                        if (!scan.hasNext()) {
                            try {
                                throw new NoSuchElementException();
                            } catch (NoSuchElementException e) {
                                System.out.println("Error for " + e + ". You need input data to realize the function!");
                            }
                        } else {
                            double[] parameters = new double[4];
                            for (int i = 0; i < parameters.length; i++) {
                                if (scan.hasNext()) {
                                    parameters[i] = Double.parseDouble(scan.next());
                                } else {
                                    throw new IllegalArgumentException();
                                }
                            }
                            if (!scan.hasNext()) {
                                clevisModel.pickAndMoveShape(parameters[0], parameters[1], parameters[2], parameters[3]);
                                System.out.println("Successfully pick point (" + parameters[0] + "," + parameters[1] + ") and move");
                            } else {
                                throw new IllegalArgumentException();
                            }
                        }
                    } catch (IllegalArgumentException e) {
                        System.out.println("Error for: " + e + ". Please find error situation in User Manual");
                    }
                }

                /** [intersect]*/
                else if (str.equals("intersect")) {
                    try {
                        if (!scan.hasNext()) {
                            try {
                                throw new NoSuchElementException();
                            } catch (NoSuchElementException e) {
                                System.out.println("Error for " + e + ". You need input data to realize the function!");
                            }
                        } else {
                            String[] parameters = new String[2];
                            for (int i = 0; i < parameters.length; i++) {
                                if (scan.hasNext()) {
                                    parameters[i] = scan.next();
                                } else {
                                    throw new IllegalArgumentException();
                                }
                            }
                            if (!scan.hasNext()) {
                                clevisModel.isIntersected(parameters[0], parameters[1]);
                                if (clevisModel.isIntersected(parameters[0], parameters[1])) {
                                    System.out.println("They are intersected!");
                                } else {
                                    System.out.println("They are not intersected");
                                }
                            } else {
                                throw new IllegalArgumentException();
                            }
                        }
                    } catch (IllegalArgumentException e) {
                        System.out.println("Error for: " + e + ". Please find error situation in User Manual");
                    }

                }

                /** [List]*/
                else if (str.equals("list")) {
                    try {
                        if (!scan.hasNext()) {
                            try {
                                throw new NoSuchElementException();
                            } catch (NoSuchElementException e) {
                                System.out.println("Error for " + e + ". You need input data to realize the function!");
                            }
                        } else {
                            String name = scan.next();
                            if (!scan.hasNext()) {
                                System.out.println("Here is the information about " + clevisModel.listShape(name));
                            } else {
                                throw new IllegalArgumentException();
                            }
                        }
                    } catch (IllegalArgumentException e) {
                        System.out.println("Error for: " + e + ". Please find error situation in User Manual");
                    }
                }

                /** [ListAll]*/
                else if (str.equalsIgnoreCase("listAll")) {
                    try {
                        if (!scan.hasNext()) {
                            System.out.println(clevisModel.listAllShape());
                        } else {
                            throw new IllegalArgumentException();
                        }
                    } catch (IllegalArgumentException e) {
                        System.out.println("Error for: " + e + ". Please find error situation in User Manual");
                    }
                }

                /** [Undo]*/
                else if (str.equals("undo")) {
                    try {
                        if (!scan.hasNext()) {
                            clevisModel.UndoControl();
                            System.out.println("Already undo!");
                        } else {
                            throw new IllegalArgumentException();
                        }
                    } catch (IllegalArgumentException e) {
                        System.out.println("Error for: " + e + ". Please find error situation in User Manual");
                    }
                }

                /** [Redo]*/
                else if (str.equals("redo")) {
                    try {
                        if (!scan.hasNext()) {
                            clevisModel.RedoControl();
                            System.out.println("Already redo!");
                        } else {
                            throw new IllegalArgumentException();
                        }
                    } catch (IllegalArgumentException e) {
                        System.out.println("Error for: " + e + ". Please find error situation in User Manual");
                    }
                }

                /** [quit]*/
                else if (str.equalsIgnoreCase("quit")) {
                    printStream.println(outStr.toString());
                    break;
                }
            }
        }

    }

    /**
     * main() method
     *
     * @param args : intake parameter
     * @throws Exception : exception thrown from main()
     */
    public static void main(String[] args) throws Exception {
        String strHtml;
        String strTxt;
        if(args.length != 0) {strHtml = args[1];}
        else {strHtml = "log.html";}
        if(args.length != 0) {strTxt = args[3];}
        else {strTxt = "log.txt";}
        new Clevis(strHtml,strTxt);
    }
}