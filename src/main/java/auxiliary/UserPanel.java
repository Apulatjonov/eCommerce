package auxiliary;

import models.user.Role;
import models.user.User;
import service.UserService;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public abstract class UserPanel {
    public static void run(UserService userService, User user){
        Scanner scannerInt = new Scanner(System.in);
        int stepCode  = 100;
        while (stepCode != 0){
            while(stepCode != 0 && stepCode != 1 && stepCode != 2 && stepCode != 3) {
                System.out.println("1. Edit account\t 2. Market\t 3. Balance\t 0. Exit");
                try{
                    stepCode = scannerInt.nextInt();
                }catch (InputMismatchException e){
                    System.out.println("Wrong Insertion!");
                }
            }
            switch (stepCode) {
                case 1 -> {
                    UUID id = user.getId();
                    user = userService.nullify(user);
                    userService.edit(id, user);
                    User newUser = UserFront.addUser();
                    System.out.println(userService.edit(id, newUser));
                }
                case 2 -> {
                    List<User> shopList = userService.getUsersByRole(Role.SHOP);
                    UserFront.getUsers(shopList);
                }
                case 3 -> {
                    UserFront.balance(user);
                    if (user!=null)
                        System.out.println(userService.add(user));
                }
            }
        }
    }
}
