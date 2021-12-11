package service;
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
    {
        isSuperAdminExist();
    }

    @Override
    public String add(User user) {
        write(user,userFileUrl);
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
        if (newUser == null){
            return SOMETHING_WENT_WRONG;
        }
        int index = 0;
        List<User> list = getList();
        for (User user : list) {
            if (user.getId().equals(id)) {
                User editedUser = editUser(newUser,user);
                list.set(index,editedUser); // editing list
                writeList(list, userFileUrl); // writing to file
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
                writeList(list, userFileUrl); // writing list to file
                return SUCCESS;
            }
            index++;
        }
        return USER_NOT_FOUND;
    }

    @Override
    public List<User> getList() {
        ObjectMapper objectMapper = new ObjectMapper();
        List<User> userList = objectMapper.convertValue(read(userFileUrl), new TypeReference<List<User>>() { });
        return userList;
    }

    @Override
    public User login(String username, String password) {
        if(getList().isEmpty())
            return null;
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

    // to set user active or block
    public String setStatus(UUID id, boolean status) {
        int index = 0;
        List<User> list = getList();
        for (User user : list) {
            if (user.getId().equals(id)) {
                user.setActive(status);
                list.set(index,user); // blocking user in list
                writeList(list,userFileUrl); // writing list to file
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

    public  void isSuperAdminExist(){
        List<User> list = getUsersByRole(Role.SUPER_ADMIN);
        if (list.size()==0){
            User user = new User("Arabboy","admin","admin",Role.SUPER_ADMIN,0,"+998907777777","admin@gmail.com",true);
            write(user, userFileUrl);
        }
    }

    public User nullify(User user){
        user.setUsername("");
        user.setBalance(0);
        user.setEmail("");
        user.setName("");
        user.setPhoneNumber("");
        return user;
    }
}