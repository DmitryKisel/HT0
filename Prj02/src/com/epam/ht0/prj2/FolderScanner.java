package com.epam.ht0.prj2;

import java.io.File;
import java.util.List;

/**
 * This method searching for files with EXT-extension in folder and adding them to fileList
 */
public class FolderScanner {
    public  void readFolder(List<File> fileList, String path, String ext) {
        File file = new File(path);
        for (File f : file.listFiles()) {
            if (f.isDirectory()) {
                readFolder(fileList, f.getAbsolutePath(), ext);
            } else {
                if (f.getName().endsWith(ext)) {
                    fileList.add(f);
                }
            }
        }
    }
}
