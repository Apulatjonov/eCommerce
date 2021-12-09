package service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import models.User;
import repository.BaseRepository;
import repository.UserRepository;
import responses.Responses;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;
import java.util.UUID;

public class UserService implements UserRepository, Responses{

    public static final String userFileUrl = TOKEN+"userList.txt";
//    FileOutputStream userFile;
//
//    {
//        try {
//            userFile = new FileOutputStream(userFileUrl);
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//    }

    @Override
    public String add(User user) {
        return null;
    }

    @Override
    public User getById(UUID id) {
        return null;
    }

    @Override
    public String edit(UUID id, User user) {
        return null;
    }

    @Override
    public String remove(UUID id) {
        return null;
    }

    @Override
    public List<User> getList() {
        return null;
    }

    @Override
    public User login(String username, String password) {
        return null;
    }

    private boolean checkUser(String username){
        return true;
    }

    public static String toJson(List list) throws Exception{
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(list);
    }

    public static List<User> fromJson(String json) throws Exception{
        ObjectMapper objectMapper = new ObjectMapper();
        List<User> userList = objectMapper.readValue(json, new TypeReference<List<User>>(){});
        return userList;
    }
}
