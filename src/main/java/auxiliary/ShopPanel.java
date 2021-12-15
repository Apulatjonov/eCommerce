package auxiliary;

import models.Category;
import models.Product;
import models.user.User;
import repository.BaseRepository;
import responses.Responses;
import service.CategoryService;
import service.ProductService;
import service.UserService;

import java.util.List;
import java.util.Scanner;
import java.util.UUID;
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
            // IOEXCEPTION needed
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
                    // IOEXCEPTION needed
                    int categoryNum = scannerInt.nextInt() - 1;
                    products =  productService.getProductsByCategory(categoryList.get(categoryNum).getId());
                    showProducts(products);
                }
                case 3 -> {
                    System.out.println("Select the Category: ");
                    showCategoryList.accept(categoryList);
                    // IOEXCEPTION needed
                    int categoryNum = scannerInt.nextInt() - 1;
                    Product product = new Product();
                    product.setCategoryId(categoryList.get(categoryNum).getId());
                    product.setShopId(shop.getId());

                    System.out.println("Enter name: ");
                    product.setName(scannerStr.nextLine());

                    // IOEXCEPTION needed
                    System.out.println("Enter price: ");
                    product.setPrice(scannerInt.nextDouble());

                    // IOEXCEPTION needed
                    System.out.println("Enter  net price: ");
                    product.setNetPrice(scannerInt.nextDouble());

                    // IOEXCEPTION needed
                    System.out.println("Enter quantity: ");
                    product.setQuantity(scannerInt.nextInt());

                    // IOEXCEPTION needed
                    System.out.println("Enter discount: ");
                    product.setDiscount(scannerInt.nextDouble());

                    System.out.println("Enter image url: ");
                    product.setPhoto( BaseRepository.TOKEN + "images\\" + scannerStr.nextLine());

                    System.out.println(productService.add(product));
                }
                case 4 -> {
                    System.out.println("Select the product order (index): ");
                    products = productService.getList();
                    showProducts(products);
                    // IOEXCEPTION needed
                    int productIndex = scannerInt.nextInt() - 1;
                    System.out.println(productService.remove(products.get(productIndex).getId()));
                }
                case 5 -> { // Edit product
//                    System.out.println("This is gonna work in our bot!");

                    List<Product> products1 = productService.getProductsByShopId(shop.getId());
                    showProducts(products1);
                    System.out.println("Select the product order (index) to change its fields: ");
                    // IOEXCEPTION needed
                    int productIndex = scannerInt.nextInt() - 1;

                    System.out.println("Enter the fields");
                    Product product = new Product();
                    System.out.println("Name: ");
                    product.setName(scannerStr.nextLine());

                    // IOEXCEPTION needed
                    System.out.println("Price: ");
                    product.setPrice(scannerInt.nextDouble());

                    // IOEXCEPTION needed
                    System.out.println("Net price: ");
                    product.setNetPrice(scannerInt.nextDouble());

                    // IOEXCEPTION needed
                    System.out.println("Quantity: ");
                    product.setQuantity(scannerInt.nextInt());

                    // IOEXCEPTION needed
                    System.out.println("Discount: ");
                    product.setDiscount(scannerInt.nextDouble());

                    System.out.println("Enter image url: ");
                    product.setPhoto( BaseRepository.TOKEN + "images\\" + scannerStr.nextLine());


                    System.out.println(productService.edit(products.get(productIndex).getId(), product));

                }
                case 6 -> { // Set discount
//                    System.out.println("This is gonna work in our bot.  Set discount");

                    List<Product> products1 = productService.getProductsByShopId(shop.getId());
                    showProducts(products1);
                    System.out.println("Select the product order (index) to give discount: ");
                    // IOEXCEPTION needed
                    int productIndex = scannerInt.nextInt() - 1;

                    // IOEXCEPTION needed
                    System.out.println("Enter Discount amount: ");
                    products1.get(productIndex).setDiscount(scannerInt.nextDouble());

                    System.out.println(Responses.SUCCESS);

                }
                case 7 -> { // Statistics/List
                    System.out.println("This is gonna work in our bot!  Statistics/List");
                }
                case 8 -> {
                    System.out.println("This is gonna work in our bot.  Date");
                }
            }
        }
    }

    public static void showProducts(List<Product> products){
        int index = 1;
        for (Product product : products) {
            System.out.println(index + ") Name:" + product.getName() + "| price: " + product.getPrice()
                    + "| netPrice: " + product.getNetPrice() + " | quantity: " + product.getQuantity()
                    + "| discount: " + product.getDiscount() + "| photo: " + product.getPhoto());
            index++;
        }
    }
}
