package com.example.utils;
import java.io.*;
import java.nio.file.Paths;

public class Utils {
    public static String getFileContents(String filename){
        String dir_path = Paths.get(".").toAbsolutePath().normalize().toString();
        String filepath = String.format("%s//contents//%s",dir_path,filename);
        File file = new File(filepath);
        try{
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            String output = "";
            while((line=reader.readLine())!=null){
                output+=line+"\n";
            }
            reader.close();
            return output;
        }catch(Exception e){
            System.out.println(e.getMessage());
            System.exit(1);
            return "";
        }
    } 
}
