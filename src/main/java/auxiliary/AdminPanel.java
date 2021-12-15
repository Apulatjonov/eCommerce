package auxiliary;

import models.Category;
import models.user.Role;
import models.user.User;
import service.CategoryService;
import service.UserService;

import java.util.List;
import java.util.Scanner;
import java.util.function.Consumer;

public abstract class AdminPanel {
    public static void run(UserService userService) {
        Scanner scannerInt = new Scanner(System.in);
        Scanner scannerStr = new Scanner(System.in);
        CategoryService categoryService = new CategoryService();
        int stepCode  = 100;
        while (stepCode != 0){

            System.out.println("1.User list\t2. Shop CRUD\t3.Category CRUD\t0.EXIT");
            stepCode = scannerInt.nextInt();

            switch (stepCode) {
                case 1 -> {
                    List<User> userList = userService.getUsersByRole(Role.USER);
                    UserFront.getUsers(userList);
                }
                case 2 -> {
                    int stepCodeShop = 1;
                    while (stepCodeShop != 0) {
                        System.out.println("1.Get shopList\t2. Add shop\t3.Edit Shop\t4.Remove shop\t0.EXIT");
                        stepCodeShop = scannerInt.nextInt();
                        switch (stepCodeShop) {
                            case 1 -> {
                                List<User> shopList = userService.getUsersByRole(Role.SHOP);
                                UserFront.getUsers(shopList);
                            }
                            case 2 -> {
                                User user = UserFront.addShop();
                                System.out.println(userService.add(user));
                            }
                            case 3 -> {
                                User user1 = UserFront.selectUser(userService.getUsersByRole(Role.SHOP));
                                if (user1 != null) {
                                    UserFront.activateOrBlock(user1);
                                }
                            }
                            case 4 -> {
                                User shop = UserFront.selectUser(userService.getUsersByRole(Role.SHOP));
                                if (shop != null)
                                    System.out.println(userService.remove(shop.getId()));
                            }
                        }
                    }
                }
                case 3 -> {
                    int stepCodeCategory = 1;
                    while (stepCodeCategory != 0) {
                        System.out.println("1.Show categories\t2.Add category\t3.Edit category\t4.Remove category\t0.Exit");
                        stepCodeCategory = scannerInt.nextInt();
                        List<Category> categories = categoryService.getList();

                        Consumer<List<Category>> showCategoryList = categoryList -> {
                            int index = 1;
                            for (Category category : categoryList) {
                                System.out.println(index + ") " + category.getName());
                                index++;
                            }
                        };
                        switch (stepCodeCategory) {
                            case 1 -> {
                                showCategoryList.accept(categories);
                            }
                            case 2 -> {
                                System.out.println("Enter category name: ");
                                String categoryName = scannerStr.nextLine();
                                Category category = new Category(categoryName);
                                System.out.println(categoryService.add(category));
                            }
                            case 3 -> {
                                showCategoryList.accept(categories);
                                System.out.print("Enter category number you want to edit: ");
                                int index = scannerInt.nextInt();
                                Category oldCategory = selectCategory(index);
                                if(oldCategory != null) {
                                    Category newCategory = new Category();
                                    System.out.println("Enter category name: ");
                                    newCategory.setName(scannerStr.nextLine());
                                    System.out.println(categoryService.edit(oldCategory.getId(), newCategory));
                                } else
                                    System.out.println("Invalid index number! ");
                            }
                            case 4 -> {
                                showCategoryList.accept(categories);
                                System.out.print("Enter category number you want to remove: ");
                                int index = scannerInt.nextInt();
                                Category category = selectCategory(index);
                                try {
                                    System.out.println(categoryService.remove(category.getId()));
                                } catch (NullPointerException e) {
                                    System.out.println("This index is invalid!");
                                }
                            }
                        }
                    }
                }
            }
        }
    }
    public static Category selectCategory(int index) {
        CategoryService categoryService = new CategoryService();
        List<Category> categoryList = categoryService.getList();
        int ind = 1;
        for (Category category: categoryList) {
            if(index == ind) {
                return category;
            }
            ind++;                                                        // this was added to our old code
        }
        return null;
    }
}
