package com.epam.ht0.prj2;

import com.epam.ht0.prj2.Entity.Mp3File;
import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.Parser;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;
import org.apache.tika.parser.mp3.Mp3Parser;
import org.xml.sax.helpers.DefaultHandler;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class AudioParser {

    /**
     * This method parses data from mp3-file by using Apache.Tika.Parser
     * and creates new objects Mp3File.
     *
     * @param list
     * @return List of Mp3File objects
     */
    List<Mp3File> mp3Parser(List<File> list) {
        List<Mp3File> mp3FileList = new ArrayList<>();

        try {
            for (int i = 0; i < list.size(); i++) {

                InputStream input = new FileInputStream(new File(list.get(i).toString()));
                ContentHandler handler = new DefaultHandler();
                Metadata metadata = new Metadata();
                Parser parser = new Mp3Parser();
                ParseContext parseCtx = new ParseContext();
                parser.parse(input, handler, metadata, parseCtx);
                input.close();

                String artistName;
                String albumInFile = "Non Album";
                String songInFile;
                double duration;
                if (metadata.get("xmpDM:albumArtist") != null) {
                    artistName = metadata.get("xmpDM:albumArtist");
                } else {
                    artistName = "Non Artist";
                }

                if (metadata.get("xmpDM:album") != null) {
                    char[] array = metadata.get("xmpDM:album").toCharArray();
                    int count = 0;
                    for (char symbol : array) {
                        if (symbol == ' ') {
                            count++;
                        }
                        if (count == array.length) {
                            albumInFile = "Non Album";
                        } else {
                            albumInFile = metadata.get("xmpDM:album");
                        }
                    }
                } else {
                    albumInFile = "Non Album";
                }

                if (metadata.get("dc:title") != null) {
                    songInFile = metadata.get("dc:title");
                } else {
                    songInFile = "Non Title";
                }

                duration = Math.round(Double.parseDouble(metadata.get("xmpDM:duration")) / 1000);

                Mp3File mp3File = new Mp3File(list.get(i).getName(), artistName, albumInFile
                        , songInFile, list.get(i).toString(), duration);

                mp3FileList.add(mp3File);
            }

        } catch (FileNotFoundException e) {
            System.out.println("File hasn't been found");
        } catch (IOException e) {
            System.out.println("Input/output error");
        } catch (SAXException e) {
            System.out.println("SAX Parser error");
        } catch (TikaException e) {
            System.out.println("TikaParser error");
        } catch (Exception e) {
            System.out.println("Parsing common error");
        }
        return mp3FileList;
    }
}

