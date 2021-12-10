import auxiliary.*;
import models.user.User;

import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Scanner scannerInt = new Scanner(System.in);
        int stepCode = 100;

        while (stepCode != 0){
            System.out.println("1. Sign in\t 2. Sign up\t 0. Exit");
            stepCode = scannerInt.nextInt();
            if (stepCode == 1){
                User logged = Authentication.signIn();
                if (logged != null && logged.isActive()){
                    switch (logged.getRole()){
                        case SUPER_ADMIN -> {
                            SuperAdminPanel.run();
                        }
                        case ADMIN -> {
                            AdminPanel.run();
                        }
                        case SHOP -> {
                            ShopPanel.run();
                        }
                        case USER -> {
                            UserPanel.run();
                        }
                    }
                }
                else if (logged != null){
                    System.out.println("You are blocked by admin!");
                }
                else {
                    System.out.println("Username or password is incorrect!");
                }
            }
            else if (stepCode == 2){
                Authentication.signUp();
            }
        }
    }
}