package auxiliary;

import models.user.Role;
import models.user.User;
import service.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public abstract class UserFront {
    public static User addUser(){
        Scanner scannerStr = new Scanner(System.in);
        User user = new User();

        System.out.println("Enter name: ");
        user.setName(scannerStr.nextLine());

        System.out.println("Enter phone number: ");
        user.setPhoneNumber(scannerStr.nextLine());

        System.out.println("Enter username: ");
        user.setUsername(scannerStr.nextLine());

        System.out.println("Enter password: ");
        user.setPassword(scannerStr.nextLine());

        System.out.println("Enter email: ");
        user.setEmail(scannerStr.nextLine());

        user.setRole(Role.USER);
        user.setBalance(0);
        return user;
    }

    public static User addAdmin(){
        User user = addUser();
        user.setRole(Role.ADMIN);
        return user;
    }

    public static User addShop(){
        User user = addUser();
        user.setRole(Role.SHOP);
        return user;
    }

    public static User addSuperAdmin(){
        User user = addUser();
        user.setRole(Role.SUPER_ADMIN);
        return user;
    }

    public static User selectUser(List<User> list){
        Scanner scanner = new Scanner(System.in);
        int index = 1; // getting users by role
        for (User user : list) {
            System.out.println(index + ". " + user.getUsername() + " " + user.getPhoneNumber() + " balance: " + user.getBalance());
            index++;
        }
        //selecting user from user list
        int selected = getIntInInterval(1,index);
        if (selected!=-1)
            return list.get(selected-1);

        return null;
    }

    public static void getUsers(List<User> list){
        int index = 1;
        for (User user : list) {
            System.out.println(index + ") " + user.getUsername() + " " + user.getPhoneNumber() + "  | balance: " + user.getBalance() + " | status: " + (user.isActive()?"active" : "blocked"));
            index++;
        }
    }

    public static int getIntInInterval(int from, int to){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Select order number: ");
        int selected = scanner.nextInt();
        if (selected<from || selected>to){
            System.out.println("Your input " + selected + " is invalid!");
            return -1;
        }
        return selected;
    }
}
