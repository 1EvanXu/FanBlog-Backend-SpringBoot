package com.evan.blog.util;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;


public class FileUtil {
    public static boolean storeFile(byte[] file, String filePath, String fileName) {

        File targetFile = new File(filePath);
        if(!targetFile.exists()){
            targetFile.mkdirs();
        }
        FileOutputStream out = null;
        try {
            out = new FileOutputStream(filePath + "/" + fileName);
            out.write(file);
            out.flush();
        } catch (IOException e) {
            return false;
        } finally {

            try {
                if (out != null)
                    out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return true;
    }
}
