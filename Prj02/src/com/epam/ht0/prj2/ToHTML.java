package com.epam.ht0.prj2;

import com.epam.ht0.prj2.Entity.Artist;
import com.epam.ht0.prj2.Entity.MusicBank;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;


public class ToHTML {

    void writeHTML(String line) throws IOException {
        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new FileWriter("output.html"));
            bw.write(line);

        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            bw.close();
        }
    }

    public String prepareForHTML(String data){
        StringBuilder sb = new StringBuilder();
        sb.append("<!DOCTYPE html>")
                .append("<html>")
                .append("<head>")
                .append(" <style type=\"text/css\">")
                .append("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">")
                .append("<title></title>")
                .append("</style>")
                .append("</head>")
                .append("<body>")
                .append(data)
                .append("</body>")
                .append("</html>");

        return sb.toString();

    }

}
