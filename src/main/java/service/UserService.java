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
        if (check(getList(),user)!=null)
            return USER_ALREADY_EXIST;

        List<User> userList = getList();
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
        return fromJson(readFile(userFileUrl));
    }

    @Override
    public User login(String username, String password) {
        User checkedUser = check(getList(), new User(username));
        if (checkedUser.getPassword().equals(password))
            return checkedUser;
        return null;
    }

    public List<User> fromJson(String json){
        ObjectMapper objectMapper = new ObjectMapper();
        List<User> list = null;
        try {
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

    private void writeFile(List<User> list){
        String json = toJson(list);
        writeToFile(userFileUrl, json);
    }
}
