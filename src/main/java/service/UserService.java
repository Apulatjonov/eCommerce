package service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import models.User;
import repository.FileUtils;
import repository.UserRepository;
import responses.Responses;

import java.util.List;
import java.util.UUID;

public class UserService extends FileUtils<User> implements UserRepository, Responses{

    public static final String userFileUrl = TOKEN + "userList.json";

    @Override
    public String add(User user) {
        if (checkUser(getList(),user)!=null)
            return USER_ALREADY_EXIST;

        List<User> userList = getList();
        userList.add(user);

        String json = toJson(userList);
        writeToFile(userFileUrl,json);

        return SUCCESS;
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
        return fromJson(readFile(userFileUrl));
    }

    @Override
    public User login(String username, String password) {
        User user = new User(username,password);
        return checkUser(getList(),user);
    }

    public List<User> fromJson(String json){
        ObjectMapper objectMapper = new ObjectMapper();
        List<User> list = null;
        try {
            list = objectMapper.readValue(json, new TypeReference<List<User>>() {});
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return list;
    }

    public String toJson(List<User> list){
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(list);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return "Exception";
    }
}
