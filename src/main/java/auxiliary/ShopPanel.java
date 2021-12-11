package auxiliary;

import models.Category;
import models.Product;
import models.user.User;
import service.CategoryService;
import service.ProductService;

import java.util.List;
import java.util.Scanner;

public abstract class ShopPanel {
    public static void run(){

        ProductService productService1 = new ProductService();
        CategoryService categoryService = new CategoryService();

        System.out.println("Shop frontend codes will be here!");

        Scanner scannerInt = new Scanner(System.in);

        int stepCode = 1;

        while(stepCode != 0){
            System.out.println("1.Add Product\t 2.Delete Product\t 3.Edit Product\t 4.Discount\t 5.Statistics/List\t 6.Date");
            stepCode = scannerInt.nextInt();

            switch (stepCode){
                case 1:
                    System.out.println("Select the Category to add Product");
                    List<Category> categoryList = categoryService.getList();
                    categoryService.getCategories(categoryList);

                    int categoryNum = scannerInt.nextInt();



                    break;
                case 2:

                    break;
                case 3:

                    break;
                case 4:

                    break;
            }
        }

    }





}
