package auxiliary;

import models.user.Role;
import models.user.User;
import service.UserService;

import java.util.InputMismatchException;
import java.util.List;
import java.util.MissingFormatArgumentException;
import java.util.Scanner;

public abstract class SuperAdminPanel {
    public static void run(UserService userService) {
        Scanner scannerInt = new Scanner(System.in);
        int stepCode  = 100;
        while (stepCode != 0){
            while(stepCode != 1 && stepCode != 2 && stepCode != 3 && stepCode != 4 && stepCode != 0) {
                System.out.println("1.Admin list\t 2.Add admin\t 3.Activate & Block admin\t 4.Remove admin");
                try {
                    stepCode = scannerInt.nextInt();
                }
                catch (InputMismatchException e){
                    System.out.println("Wrong insertion!");
                }
            }
            switch (stepCode) {
                case 1 -> {
                    List<User> userList = userService.getUsersByRole(Role.ADMIN);
                    UserFront.getUsers(userList);
                }
                case 2 -> {
                    User user = UserFront.addAdmin();
                    if (user!=null)
                        System.out.println(userService.add(user));
                }
                case 3 -> {
                    User user1 = UserFront.selectUser(userService.getUsersByRole(Role.ADMIN));
                    if (user1!=null)
                        UserFront.activateOrBlock(user1);
                }
                case 4 -> {
                    User admin = UserFront.selectUser(userService.getUsersByRole(Role.ADMIN));
                    if (admin != null)
                        System.out.println(userService.remove(admin.getId()));
                }
            }
        }
    }
}
