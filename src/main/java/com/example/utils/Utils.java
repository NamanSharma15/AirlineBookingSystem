package com.example.utils;
import java.io.*;
import java.nio.file.Paths;

public class Utils {
    static String dir_path = Paths.get(".").toAbsolutePath().normalize().toString();
    public static String getFileContents(String filename){
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
        }catch(IOException e){
            System.out.println(e.getMessage());
            System.exit(1);
            return "";
        }
    } 
    public static void createTextFile(String filename,String folder,String content){
        String filepath = String.format("%s//out//%s//%s",dir_path,folder,filename);
        try{
            FileWriter file = new FileWriter(filepath);
            file.write(content);
            file.close();
        }catch(IOException e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
}
