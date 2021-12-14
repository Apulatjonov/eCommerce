package auxiliary;

import models.Category;
import models.Product;
import models.user.User;
import service.CategoryService;
import service.ProductService;

import java.util.List;
import java.util.Scanner;
import java.util.function.Consumer;

public abstract class ShopPanel {
    public static void run(User shop){

        ProductService productService = new ProductService();
        CategoryService categoryService = new CategoryService();

        Scanner scannerInt = new Scanner(System.in);
        Scanner scannerStr = new Scanner(System.in);

        int stepCode = 1;

        while(stepCode != 0){
            System.out.println("1.Get Product List\t 2.Get Product List by Category\t 3.Add Product\t 4.Delete Product" +
                    "\t 5.Edit Product\t 6.Set Discount\t 7.Statistics/List\t 8.Date");
            stepCode = scannerInt.nextInt();

            Consumer<List<Category>> showCategoryList = categoryList -> {   // this is a lambda expression which shows all categories
                int index = 1;
                for (Category category : categoryList) {
                    System.out.println(index + ") " + category.getName());
                    index++;
                }
            };
            List<Category> categoryList = categoryService.getList();   // getting all the categories to the categoryList list
            List<Product> products = productService.getList();         // getting all the products to products list
            switch (stepCode){
                case 1 -> {
                    showProducts(products);
                }

                case 2 -> {
                    System.out.println("Select the Category to add Product");
                    showCategoryList.accept(categoryList);
                    int categoryNum = scannerInt.nextInt() - 1;
                    products =  productService.getProductsByCategory(categoryList.get(categoryNum).getId());
                    showProducts(products);
                }
                case 3 -> {
                    System.out.println("Select the Category to add Product");
                    showCategoryList.accept(categoryList);
                    int categoryNum = scannerInt.nextInt() - 1;
                    Product product = new Product();
                    product.setCategoryId(categoryList.get(categoryNum).getId());
                    product.setShopId(shop.getId());

                    System.out.println("Enter name: ");
                    product.setName(scannerStr.nextLine());

                    System.out.println("Enter price: ");
                    product.setPrice(scannerInt.nextDouble());

                    System.out.println("Enter  net price: ");
                    product.setNetPrice(scannerInt.nextDouble());

                    System.out.println("Enter quantity: ");
                    product.setQuantity(scannerStr.nextInt());

                    System.out.println("Enter discount: ");
                    product.setDiscount(scannerInt.nextDouble());

                    productService.add(product);
                }
                case 4 -> {
                    System.out.println("Select the product order (index): ");
                    products = productService.getList();
                    showProducts(products);
                    int productIndex = scannerInt.nextInt() - 1;
                    productService.remove(products.get(productIndex).getId());
                }
                case 5 -> {
                    System.out.println("This is gonna work in our bot! ");
                }

                case 6 -> {
                    System.out.println("This is gonna work in our bot");
                }
                case 7 -> {
                    System.out.println("This is gonna work in our bot!");
                }
                case 8 -> {
                    System.out.println("This is gonna work in our bot ");
                }
            }
        }
    }

    public static void showProducts(List<Product> products){
        int index = 1;
        for (Product product : products) {
            System.out.println(index + ") Name:" + product.getName() + "| price: " + product.getPrice()
                    + "| netPrice: " + product.getNetPrice() + " | quantity: " + product.getQuantity()
                    + "| discount: " + product.getDiscount());
            index++;
        }
    }
}
