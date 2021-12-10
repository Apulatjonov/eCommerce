package auxiliary;

import models.user.User;
import service.UserService;

import java.util.Scanner;

public abstract class Authentication {
    public static User signIn(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter username:");
        String username = scanner.nextLine();

        System.out.println("Enter your password:");
        String password = scanner.nextLine();

        User logged = new UserService().login(username, password);
        return logged;
    }

    public static void signUp(){
        User user = UserFront.addUser();
        if (user != null)
            System.out.println(new UserService().add(user));
    }
}
