import auxiliary.*;
import com.android.server.webkit.SystemInterface;
import models.user.User;
import repository.FileUtils;
import service.UserService;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        UserService userService = new UserService();
        Scanner scannerInt = new Scanner(System.in);
        int stepCode = 100;
        while (stepCode != 0) {
            System.out.println("1. Sign in\t 2. Sign up\t 0. Exit");
            stepCode = scannerInt.nextInt();
            if (stepCode == 1) {
                System.out.println("1. User\t2. Admin\t3. Shop");
                int ans = new Scanner(System.in).nextInt();
                if (ans == 2) {
                    User logged = Authentication.signIn();
                    if (logged != null && logged.isActive()) {
                        switch (logged.getRole()) {
                            case SUPER_ADMIN -> {
                                SuperAdminPanel.run(userService);
                            }
                            case ADMIN -> {
                                AdminPanel.run(userService);
                            }
                            case SHOP -> {
                                ShopPanel.run(logged);
                            }
                            case USER -> {
                                UserPanel.run(userService, logged);
                            }
                        }
                    } else if (logged != null) {
                        System.out.println("You are blocked by admin!");
                    } else {
                        System.out.println("Username or password is incorrect!");
                    }
                } else if (ans == 1) {
                    User logged = Authentication.signInUser();
                    if (logged != null && logged.isActive()) {
                        switch (logged.getRole()) {
                            case SUPER_ADMIN -> {
                                SuperAdminPanel.run(userService);
                            }
                            case ADMIN -> {
                                AdminPanel.run(userService);
                            }
                            case SHOP -> {
                                ShopPanel.run(logged);
                            }
                            case USER -> {
                                UserPanel.run(userService, logged);
                            }
                        }
                    } else if (logged != null) {
                        System.out.println("You are blocked by admin!");
                    } else {
                        System.out.println("Username or password is incorrect!");
                    }
                } else if (ans == 3) {
                    User logged = Authentication.signInUser();
                    if (logged != null && logged.isActive()) {
                        switch (logged.getRole()) {
                            case SUPER_ADMIN -> {
                                SuperAdminPanel.run(userService);
                            }
                            case ADMIN -> {
                                AdminPanel.run(userService);
                            }
                            case SHOP -> {
                                ShopPanel.run(logged);
                            }
                            case USER -> {
                                UserPanel.run(userService, logged);
                            }
                        }
                    } else if (logged != null) {
                        System.out.println("You are blocked by admin!");
                    } else {
                        System.out.println("Username or password is incorrect!");
                    }
                } else if (stepCode == 2) {
                    Authentication.signUp();
                }
            }

        }
    }
}