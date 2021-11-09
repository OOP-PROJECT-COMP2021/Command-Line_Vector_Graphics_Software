package hk.edu.polyu.comp.comp2021.clevis.model;

import org.junit.Test;

import java.io.*;


public class HTMLTest {

    @Test
    public void htmlTest1() throws FileNotFoundException,IOException {
        PrintStream printStream = new PrintStream(new FileOutputStream("log.html"));

        StringBuilder outStr = new StringBuilder();
        File file = new File("record.txt");
        InputStreamReader reader = new InputStreamReader(new FileInputStream(file));
        BufferedReader br = new BufferedReader(reader);
        outStr.append("<html>");
        outStr.append("<head>");
        outStr.append("<title>htmlTest</title>");
        outStr.append("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" /></title>");
        outStr.append("<body>");
        outStr.append("<table border=\"1\">");
        outStr.append("<tr>" + "<th>Index</th>" + "<th>cmd</th>" + "</tr>"); // table head
        // insert here
        outStr.append("<table></body></html>");
        String line = br.readLine();
        int position = 0;
        while(line!=null) {
            outStr.insert(outStr.length() - "<table></body></html>".length(), "<tr>" + "<td>" + position + "</td>" + "<td>" +line+ "</td>" + "</tr>"); // table row 0 (table data:0; table data: rectangle A 1 1 2 2)
            line = br.readLine();
            position++;
        }
        printStream.println(outStr.toString());
    }

}
