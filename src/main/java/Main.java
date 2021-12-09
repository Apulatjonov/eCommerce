import auxiliary.FrontEnd;
import models.Role;
import models.User;
import repository.FileUtils;
import service.UserService;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {
        UserService userService = new UserService();
        User user = new User("Oxunjon","oxunjon","12345", Role.ADMIN,5_000_000,"+998905625896","arabboy@gmail.com");
    }
}