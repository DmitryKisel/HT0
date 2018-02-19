package com.epam.ht0.prj2;



import com.epam.ht0.prj2.Entity.Mp3File;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
   public final static String EXT = "mp3";

    public static void main(String[] args) {

        //Create a set for using only different paths and skipp a repeating paths
        Set<String> pathSet = new HashSet<>();

        //Go through the array of arguments and define paths, than add them to set.
        for (int i = 0; i < (args.length); i++) {
            if (isCorrectPath(args[i])) {
                pathSet.add(args[i]);
            } else {
                printError();
                return;
            }
        }

        List<Mp3File> mp3FileList = null;
        FolderScanner folderScanner = new FolderScanner();
        List<File> fileList = new ArrayList<>();
       for(String path : pathSet) {
           folderScanner.readFolder(fileList, path, EXT);
       }

        AudioParser audioParser = new AudioParser();
        mp3FileList = audioParser.mp3Parser(fileList);

        System.out.println();
        Catalogizator catalogizator = new Catalogizator();
        catalogizator.mp3DataConverter(mp3FileList);
        System.out.println();

        CheckSum checkSum = new CheckSum();
        checkSum.checkSumCompare(mp3FileList);


    }
    static boolean isCorrectPath(String arg) {
        Pattern pattern = Pattern.compile("([a-zA-Z]:)?(\\\\[a-zA-Zа-яА-Я\\s0-9!_.-@#$%]+)+\\\\?");
        Matcher matcher = pattern.matcher(arg);
        return matcher.matches();
    }

    /**
     * This method prints error report
     */
    static void printError() {
        System.out.println("Please run application with parameters like in EXAMPLE");
        System.out.println("EXAMPLE: java Main \"C:\\Some Path1\\\" D:\\SomePath99");
    }
}
