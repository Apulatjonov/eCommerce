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
//        new FrontEnd().start();
        List<User> list = new ArrayList<>();
        list.add(new User("asx","xasxs","ass", Role.ADMIN,12,"sajknas","xasxsax"));
        list.add(new User("asx","xasxs","ass", Role.ADMIN,12,"sajknas","xasxsax"));


        String json = UserService.toJson(list);

        FileUtils.writeToFile(UserService.userFileUrl, json);

        List<User> users = UserService.fromJson(FileUtils.readFile(UserService.userFileUrl));


        System.out.println(users.get(0).getName());
    }
}