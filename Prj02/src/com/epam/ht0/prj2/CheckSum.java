package com.epam.ht0.prj2;



import com.epam.ht0.prj2.Entity.Mp3File;

import java.io.FileInputStream;
import java.io.InputStream;
import java.security.MessageDigest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CheckSum {
    public void checkSumCompare(List<Mp3File> mp3FileList){
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

        for (Map.Entry<String, Integer> pair: map.entrySet()){
            if(pair.getValue()>1){
                try {
                    System.out.println("Контрольная сумма совпадает:");
                    for (Mp3File file : mp3FileList) {
                        if (getMD5Checksum(file.getPathToFile()).equals(pair.getKey())) {
                            System.out.println(file.getPathToFile());
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
