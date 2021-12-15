package auxiliary;

import Validation.PhoneNumberValidation;
import models.user.Role;
import models.user.User;
import service.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public abstract class UserFront {
    static Scanner scannerStr = new Scanner(System.in);
    static Scanner scannerInt = new Scanner(System.in);
    public static User addUser() {
        UserService userService = new UserService();
        User user = new User();
        System.out.print("Enter name: ");
        user.setName(scannerStr.nextLine());

        String phoneNumber = addPhoneNumber(user);
        if(phoneNumber == null)
            return null;
        user.setPhoneNumber(phoneNumber);

        String username = addUserName(user);
        if(username == null)
            return null;
        user.setUsername(username);

        System.out.print("Enter password: ");
        user.setPassword(scannerStr.nextLine());

        user.setEmail(addEmail());
        user.setRole(Role.USER);
        user.setBalance(0);
        return user;
    }

    public static void balance(User user){
        while(true) {
            System.out.println("1 -> Get current balance");
            System.out.println("2 -> Fill the balance");
            System.out.println("0 -> Back");
            int ans = scannerInt.nextInt();
            switch (ans) {
                case 1: {
                    getBalance(user);
                    break;
                }
                case 2: {
                    user = fillBalance(user);
                    break;
                }
                case 0: {
                    return;
                }
            }
        }
    }

    public static User fillBalance(User user){
        System.out.print("Insert amount: ");
        double amount = scannerInt.nextDouble();
        user.setBalance(user.getBalance() + amount);
        return user;
    }

    public static void getBalance(User user){
        System.out.println("Current balance: " + user.getBalance());
    }

    private static String addPhoneNumber(User user){
        UserService userService = new UserService();
        boolean inValid = false;
        String phoneNumber = null;
        while(phoneNumber!= null || userService.checkPhoneNumber(phoneNumber) || inValid) {
            System.out.println("Enter phoneNumber(Press '0' to go back): ");
            phoneNumber = scannerStr.nextLine();
            if(phoneNumber.equals("0")) {
                return null;
            } else if(userService.checkPhoneNumber(phoneNumber)){
                System.out.println("This phone number already exist!");
            }
            try {
                if (!PhoneNumberValidation.checkForValidPhoneNumber(phoneNumber)) {
                    System.out.println("Invalid Phone Number!");
                    inValid = true;
                }
                if (PhoneNumberValidation.checkForValidPhoneNumber(phoneNumber)) {
                    inValid = false;
                }
            }catch (Exception e){
                System.out.println("Something went wrong!");
                inValid = true;
            }
        }
        return phoneNumber;
    }

    private static String addUserName(User user){
        UserService userService = new UserService();
        String username;
        do {
            System.out.print("Enter username: ");
            username = scannerStr.nextLine();
            while(username.length() == 0) {
                System.out.println("Enter username(Press '0' to go back): ");
                username = scannerStr.nextLine();
                if(username.equals("0"))
                    return null;
            }
            if(userService.checkUsername(username)) {
                System.out.print("Username already exist!");
            }
        } while(!userService.checkUsername(username));
        return username;
    }


    public static String addEmail(){
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
        int index = 1;                                  // getting users by role
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
