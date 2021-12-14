package auxiliary;

import models.Category;
import models.Product;
import models.user.User;
import service.CategoryService;
import service.ProductService;

import java.util.List;
import java.util.Scanner;

public abstract class ShopPanel {
    public static void run(User shop){

        ProductService productService1 = new ProductService();
        CategoryService categoryService = new CategoryService();

        System.out.println("Shop frontend codes will be here!");

        Scanner scannerInt = new Scanner(System.in);
        Scanner scannerStr = new Scanner(System.in);

        int stepCode = 1;

        while(stepCode != 0){
            System.out.println("1.Get Product List\t 2.Get Product List by Category\t 3.Add Product\t 4.Delete Product" +
                    "\t 5.Edit Product\t 6.Set Discount\t 7.Statistics/List\t 8.Date");
            stepCode = scannerInt.nextInt();

            switch (stepCode){
                case 1: // Get product list
                    List<Product> products = productService1.getList();
                    getProducts(products);
                    break;
                case 2: // get product list by category
                    System.out.println("Select the Category to add Product");
                    List<Category> categoryList = categoryService.getList();
                    categoryService.getCategories(categoryList);
                    int categoryNum = scannerInt.nextInt() - 1;

                    products =  productService1.getProductsByCategory(categoryList.get(categoryNum).getId());
                    getProducts(products);
                    break;
                case 3: // add product
                    System.out.println("Select the Category to add Product");
                    List<Category> categoryList1 = categoryService.getList();
                    categoryService.getCategories(categoryList1);

                    categoryNum = scannerInt.nextInt() - 1;

                    Product product = new Product();
                    product.setCategoryId(categoryList1.get(categoryNum).getId());
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

                    productService1.add(product);
                    break;
                case 4: // delete product
                    System.out.println("Select the product order (index): ");
                    products = productService1.getList();
                    getProducts(products);
                    int productIndex = scannerInt.nextInt() - 1;

                    productService1.remove(products.get(productIndex).getId());
                    break;
                case 5:

                    break;

                case 6:

                    break;

                case 7:

                    break;

                case 8:

                    break;

            }
        }

    }

    public static void getProducts(List<Product> products){
        int index = 1;
        for (Product product : products) {
            System.out.println(index + ") Name:" + product.getName() + ", price: " + product.getPrice()
                    + ", netPrice: " + product.getNetPrice() + "  | quantity: " + product.getQuantity()
                    + ", discount: " + product.getDiscount());
            index++;
        }
    }





}
