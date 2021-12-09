package repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.io.*;
import java.util.List;

public abstract class FileUtils<T> {
    public static void writeToFile(String fileUrl, String content) throws Exception{
        BufferedWriter bufferedWriter = null;

        try{
            bufferedWriter = new BufferedWriter(new FileWriter(fileUrl));
            bufferedWriter.write(content);
        } catch (Exception e){
            e.printStackTrace();
        }
        finally {
                bufferedWriter.close();
        }
    }

    public static String readFile(String fileUrl){
        BufferedReader bufferedReader = null;
        String res = "";
        try {
            bufferedReader = new BufferedReader(new FileReader(fileUrl));
            String s;
            while ((s = bufferedReader.readLine()) != null){
                res+=s;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return res;
    }
}
