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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AudioParser {

    List<Mp3File> mp3Parser(List<File> list) {
        List <Mp3File> mp3FileList = new ArrayList<>();

        try {
            for (int i = 0; i < list.size(); i++) {
                Pattern p = Pattern.compile(".+[.mp3]");
                Matcher m = p.matcher(list.get(i).getName());

                if (!m.matches()) {
                    continue;
                } else {
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
                       char[] array =  metadata.get("xmpDM:album").toCharArray();
                       int count= 0;
                       for(char symbol : array){
                           if (symbol ==' '){
                               count++;
                           }
                           if(count == array.length){
                               albumInFile = "Non Album";
                           }
                           else {
                               albumInFile = metadata.get("xmpDM:album");                           }
                       }

                    } else {
                        albumInFile = "Non Album";
                    }

                    if (metadata.get("dc:title") != null) {
                        songInFile = metadata.get("dc:title");
                    } else {
                        songInFile = "Non Title";
                    }

                    duration = Math.round(Double.parseDouble(metadata.get("xmpDM:duration"))/1000);

                    Mp3File mp3File = new Mp3File(list.get(i).getName(), artistName, albumInFile
                            , songInFile, list.get(i).toString(),duration);

                    mp3FileList.add(mp3File);
                }
            }
        }catch (NullPointerException e){
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (TikaException e) {
            e.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }


        return mp3FileList;
    }
}

