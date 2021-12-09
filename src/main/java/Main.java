import models.Role;
import models.User;
import service.UserService;

public class Main {
    public static void main(String[] args){
        UserService userService = new UserService();
        User user = new User("Akmal","akmal","12346", Role.ADMIN,5_000_000,"+998905625896","arabboy@gmail.com");
    }
}