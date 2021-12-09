package repository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.util.List;

public abstract class FileUtils<T> {
    public void writeToFile(String fileUrl,String json){
        try(BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileUrl))){
            bufferedWriter.write(json);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public String  readFile(String fileUrl){
        String json = "";
        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(fileUrl))){
            String s;
            while ((s = bufferedReader.readLine()) != null){
                json+=s;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return json;
    }
}
