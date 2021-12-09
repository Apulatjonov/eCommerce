package repository;

import models.user.User;

import java.util.List;

public interface UserRepository extends BaseRepository<User, String, List<User>>{
    User login(String username, String password);
}
