package auxiliary;

import models.user.Role;
import models.user.User;
import service.UserService;

import java.util.List;
import java.util.Scanner;

public abstract class AdminPanel {
    public static void run(UserService userService) {
        Scanner scannerInt = new Scanner(System.in);
        int stepCode  = 100;
        while (stepCode != 0){
            System.out.println("1.User list\t 2.Shop list\t 3.Add shop\t 4.Activate & Block shop\t 5.Remove shop");
            stepCode = scannerInt.nextInt();

            switch (stepCode) {
                case 1 -> {
                    List<User> userList = userService.getUsersByRole(Role.ADMIN);
                    UserFront.getUsers(userList);
                }
                case 2 -> {
                    List<User> shopList = userService.getUsersByRole(Role.SHOP);
                    UserFront.getUsers(shopList);
                }
                case 3 -> {
                    User user = UserFront.addShop();
                    System.out.println(userService.add(user));
                }
                case 4 -> {
                    User user1 = UserFront.selectUser(userService.getUsersByRole(Role.SHOP));
                    if (user1 != null) {
                        System.out.println("1. Block user\t 2. Activate user");

                        int operation = UserFront.getIntInInterval(1, 2);
                        if (operation == 1)
                            System.out.println(userService.block(user1.getId()));
                        else if (operation == 2)
                            System.out.println(userService.activate(user1.getId()));
                    }
                }
                case 5 -> {
                    User shop = UserFront.selectUser(userService.getUsersByRole(Role.SHOP));
                    if (shop != null)
                        System.out.println(userService.remove(shop.getId()));
                }
            }
        }
    }
}