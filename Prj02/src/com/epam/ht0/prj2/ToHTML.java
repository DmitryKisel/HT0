package com.epam.ht0.prj2;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;


class ToHTML {
    /**
     * This method writes data to HTML file
     *
     * @param line
     * @throws IOException
     */
    void writeHTML(String line) throws IOException {
        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new FileWriter("output.html"));
            bw.write(line);

        } catch (IOException e) {
            System.out.println("An ERROR occurred while writing data to HTML file");
        } finally {
            if (bw != null) {
                bw.close();
            }
        }
    }

    /**
     * This method prepares  HTML file by adding adding necessary tags and incoming data
     *
     * @param data
     * @return
     */
    String prepareForHTML(String data) {
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
