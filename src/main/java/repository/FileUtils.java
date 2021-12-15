package repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public abstract class FileUtils<T> {


    public void write(T t, String fileUrl){
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            final List<T> list = read(fileUrl);
            list.add(t);
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File(fileUrl),list);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public  List<T> read(String fileUrl){
        ObjectMapper objectMapper = new ObjectMapper();
        if (new File(fileUrl).length()==0){
            writeList(new ArrayList<>(), fileUrl);
        }

        List<T> list = new ArrayList<>();
        try {
            list = objectMapper.readValue(new File(fileUrl), new TypeReference< ArrayList <T>>(){});
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    public void writeList(List<T> list, String fileUrl){
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File(fileUrl),list);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
