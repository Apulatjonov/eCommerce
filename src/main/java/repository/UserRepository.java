package repository;

import models.User;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

public interface UserRepository extends BaseRepository<User, String, List<User>>{
    List<User> userList = new ArrayList<>();
    User login(String username, String password);
}
