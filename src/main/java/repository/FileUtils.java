package repository;

import java.io.*;

public abstract class FileUtils<T> {
    public void writeToFile(String fileUrl,String json){
        try(BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileUrl))){
            bufferedWriter.write(json);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public String  readFromFile(String fileUrl){
        createFile(fileUrl);
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


    private void createFile(String fileUrl){
        File file = new File(fileUrl);
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}