package com.epam.ht0.prj2;



import com.epam.ht0.prj2.Entity.Mp3File;
import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import java.io.FileInputStream;
import java.io.InputStream;
import java.security.MessageDigest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CheckSum {

    private static final Logger LOGGER = LogManager.getLogger(CheckSum.class);

    public void checkSumCompare(List<Mp3File> mp3FileList){
        PropertyConfigurator.configure("log4j.properties");
       Map<String, Integer> map = new HashMap<>();
        for (Mp3File file : mp3FileList){

            try{
                if (map.containsKey(getMD5Checksum(file.getPathToFile()))) {
                    map.put(getMD5Checksum(file.getPathToFile()),
                            map.get(getMD5Checksum(file.getPathToFile())) + 1);
                }
                else {
                    map.put(getMD5Checksum(file.getPathToFile()), 1); }

            } catch (Exception e)  {
                System.out.println("Ошибка чтения файла");
            }
        }




        int count = 0;
        for (Map.Entry<String, Integer> pair: map.entrySet()){
            if(pair.getValue()>1){
              count++;
                LOGGER.log(Level.WARN,"Дубликат"+ count+ ":");
                try {
                    for (Mp3File file : mp3FileList) {
                        if (getMD5Checksum(file.getPathToFile()).equals(pair.getKey())) {
                            LOGGER.log(Level.WARN,"+" + file.getPathToFile());
                        }
                    }
                }catch (Exception e){

                }
            }
        }
    }

    public  byte[] createChecksum(String filename) throws Exception {
        InputStream fis =  new FileInputStream(filename);

        byte[] buffer = new byte[1024];
        MessageDigest complete = MessageDigest.getInstance("MD5");
        int numRead;

        do {
            numRead = fis.read(buffer);
            if (numRead > 0) {
                complete.update(buffer, 0, numRead);
            }
        } while (numRead != -1);

        fis.close();
        return complete.digest();
    }

    // see this How-to for a faster way to convert
    // a byte array to a HEX string
    public  String getMD5Checksum(String filename) throws Exception {
        byte[] b = createChecksum(filename);
        String result = "";

        for (int i=0; i < b.length; i++) {
            result += Integer.toString( ( b[i] & 0xff ) + 0x100, 16).substring( 1 );
        }
        return result;
    }

}
