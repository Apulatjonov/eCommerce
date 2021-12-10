package service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import models.user.Role;
import models.user.User;
import repository.FileUtils;
import repository.UserRepository;
import responses.Responses;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class UserService extends FileUtils<User> implements UserRepository, Responses{

    public static final String userFileUrl = TOKEN + "userList.json";

    @Override
    public String add(User user) {
        List<User> userList = getList(); // getting list from file
        userList.add(user); // adding user to list
        writeFile(userList); // writing list to file
        return SUCCESS;
    }

    @Override
    public User getById(UUID id) {
        for (User user : getList()) {
            if (user.getId().equals(id))
                return user;
        }
        return null;
    }

    @Override
    public String edit(UUID id, User newUser) {
        int index = 0;
        List<User> list = getList();
        for (User user : list) {
            if (user.getId().equals(id)) {
                User editedUser = editUser(newUser,user);
                list.set(index,editedUser); // editing list
                writeFile(list); // writing to file
                return SUCCESS;
            }
            index++;
        }
        return USER_NOT_FOUND;
    }

    @Override
    public String remove(UUID id) {
        int index = 0;
        List<User> list = getList();
        for (User user : list) {
            if (user.getId().equals(id)) {
                list.remove(index); // removing user from list
                writeFile(list); // writing list to file
                return SUCCESS;
            }
            index++;
        }
        return USER_NOT_FOUND;
    }

    @Override
    public List<User> getList() {
        return readFile(userFileUrl);
    }

    @Override
    public User login(String username, String password) {
        for (User user : getList()) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password))
                return user;
        }
        return null;
    }

    public String block(UUID id){
        return setStatus(id, false);
    }

    public String activate(UUID id){
        return setStatus(id, true);
    }

    public String setStatus(UUID id, boolean status) {
        int index = 0;
        List<User> list = getList();
        for (User user : list) {
            if (user.getId().equals(id)) {
                user.setActive(status);
                list.set(index,user); // blocking user in list
                writeFile(list); // writing list to file
                return SUCCESS;
            }
            index++;
        }
        return USER_NOT_FOUND;
    }

    public  List<User> getUsersByRole(Role role){
        List<User> list = getList();
        List<User> userList = new ArrayList<>();

        for (User user : list) {
            if (user.getRole().equals(role)){
                userList.add(user);
            }
        }
        return userList;
    }

    public boolean checkUsername(String username){
        for (User user : getList()) {
            if (user.getUsername().equals(username))
                return true;
        }
        return false;
    }

    public boolean checkPhoneNumber(String phoneNumber){
        for (User user : getList()) {
            if (user.getPhoneNumber().equals(phoneNumber))
                return true;
        }
        return false;
    }

    private User editUser(User newUser, User oldUser){
        oldUser.setBalance(newUser.getBalance());
        oldUser.setEmail(newUser.getEmail());
        oldUser.setUsername(newUser.getUsername());
        oldUser.setName(newUser.getName());
        oldUser.setPassword(newUser.getPassword());
        oldUser.setPhoneNumber(newUser.getPhoneNumber());
        oldUser.setRole(newUser.getRole());
        return oldUser;
    }

    // Working with files
    private void writeFile(List<User> list){
        String json = toJson(list);
        writeToFile(userFileUrl, json);
    }

    private List<User> readFile(String userFileUrl){
        return fromJson(readFromFile(userFileUrl));
    }

    public List<User> fromJson(String json){
        ObjectMapper objectMapper = new ObjectMapper();
        List<User> list = new ArrayList<>();
        try {
            if(!json.equals(""))
                list = objectMapper.readValue(json, new TypeReference<>() {});
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
        return "EXCEPTION";
    }
    //working with files end
}
