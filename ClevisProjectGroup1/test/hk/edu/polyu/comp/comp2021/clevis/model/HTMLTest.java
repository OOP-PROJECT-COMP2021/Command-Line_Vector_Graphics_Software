package hk.edu.polyu.comp.comp2021.clevis.model;

import org.junit.Test;

import java.io.*;


public class HTMLTest {

    @Test
    public void htmlTest1() throws FileNotFoundException {
        PrintStream printStream = new PrintStream(new FileOutputStream("log.html"));

        StringBuilder outStr = new StringBuilder();
        outStr.append("<html>");
        outStr.append("<head>");
        outStr.append("<title>htmlTest</title>");
        outStr.append("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" /></title>");
        outStr.append("<body>");
        outStr.append("<table border=\"1\">");
        outStr.append("<tr>" + "<th>Index</th>" + "<th>cmd</th>" + "</tr>"); // table head
        // insert here
        outStr.append("<table></body></html>");

        outStr.insert(outStr.length()-"<table></body></html>".length(),"<tr>" + "<td>0</td>" + "<td>rectangle A 1 1 2 2</td>" + "</tr>"); // table row 0 (table data:0; table data: rectangle A 1 1 2 2)
        outStr.insert(outStr.length()-"<table></body></html>".length(),"<tr>" + "<td>1</td>" + "<td>line B 1 2 3 4</td>" + "</tr>"); // table row 1 (table data:1; table data: line B 1 2 3 4)

        printStream.println(outStr.toString());
    }

}
