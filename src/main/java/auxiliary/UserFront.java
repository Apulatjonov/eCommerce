package auxiliary;

import Validation.PhoneNumberValidation;
import models.user.Role;
import models.user.User;
import service.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public abstract class UserFront {
    public static User addUser(){
        UserService userService = new UserService();
        userService.getList();
        Scanner scannerStr = new Scanner(System.in);
        User user = new User();
        System.out.print("Enter name: ");
        user.setName(scannerStr.nextLine());

        user.setPhoneNumber(addPhoneNumber(userService));

        user.setUsername(addUserName(userService));
        System.out.print("Enter password: ");
        user.setPassword(scannerStr.nextLine());


        user.setEmail(addEmail());
        user.setRole(Role.USER);
        user.setBalance(0);
        return user;
    }

    public static String addPhoneNumber(UserService userService){
        Scanner scannerStr = new Scanner(System.in);
        boolean inValid = false;
        String number = null;
        while(number == null || userService.checkPhoneNumber(number) || inValid){
            System.out.print("Enter phone number\n0 -> Back: ");
            number = scannerStr.nextLine();
            if(number.equals("0")) {
                return null;
            }
            if(userService.checkPhoneNumber(number)){
                System.out.print("Phone number already exist!\n");
            }
            try {
                if (!PhoneNumberValidation.checkForValidPhoneNumber(number.toString())) {
                    System.out.println("Invalid Phone Number!");
                    inValid = true;
                }
                if (PhoneNumberValidation.checkForValidPhoneNumber(number.toString())) {
                    inValid = false;
                }
            }catch (Exception e){
                System.out.println("Something went wrong!");
                inValid = true;
            }
        };
        return number;
    }

    public static String addUserName(UserService userService){
        Scanner scannerStr = new Scanner(System.in);
        String username;
        do {
            System.out.print("Enter username: ");
            username = scannerStr.nextLine();
            if(userService.checkUsername(username)){
                System.out.print("Username already exist!");
            }
            if (username.equals("0"))
                return null;
        }while(userService.checkUsername(username));
        return username;
    }

    public static String addEmail(){
        Scanner scannerStr = new Scanner(System.in);
        String email;
        do{
            System.out.print("Enter email: ");
            email = scannerStr.nextLine();
        }while (email.length() < 10 || !email.substring(email.length() - 10).equals("@gmail.com"));
        return email;
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

    public static void activateOrBlock(User user){
        UserService userService = new UserService();
        if (user != null) {
            System.out.println("1. Block user\t 2. Activate user");

            int operation = UserFront.getIntInInterval(1, 2);
            if (operation == 1)
                System.out.println(userService.block(user.getId()));
            else if (operation == 2)
                System.out.println(userService.activate(user.getId()));
        }
    }

}
