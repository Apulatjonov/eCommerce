package service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import models.Product;
import repository.FileUtils;
import repository.ProductRepository;
import responses.Responses;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ProductService extends FileUtils<Product> implements ProductRepository, Responses {
    public static final String productFileUrl = TOKEN + "productList.json";

    @Override
    public String add(Product product) {
        write(product,productFileUrl);
        return SUCCESS;
    }

    @Override
    public Product getById(UUID id) {
        for(Product product : getList()) {
            if(product.getId().equals(id))
                return product;
        }
        return null;
    }

    @Override
    public String edit(UUID id, Product newProduct) {
        if (newProduct == null){
            return SOMETHING_WENT_WRONG;
        }
        int index = 0;
        List<Product> list = getList();
        for (Product product : list) {
            if (product.getId().equals(id)) {
                Product editedProduct = editProduct(newProduct,product);
                list.set(index,editedProduct); // editing list
                writeList(list, productFileUrl); // writing to file
                return SUCCESS;
            }
            index++;
        }
        return PRODUCT_NOT_FOUND;
    }
    @Override
    public String remove(UUID id) {
        int index = 0;
        List<Product> list = getList();
        for (Product product: list) {
            if (product.getId().equals(id)) {
                list.remove(index); // removing product from list
                writeList(list, productFileUrl); // writing list to file
                return SUCCESS;
            }
            index++;
        }
        return PRODUCT_NOT_FOUND;
    }

    @Override
    public List<Product> getList() {
        ObjectMapper objectMapper = new ObjectMapper();
        List<Product> productList = objectMapper.convertValue(read(productFileUrl), new TypeReference<List<Product>>() { });
        return productList;
    }

    public  List<Product> getProductsByCategory(UUID categoryId){
        List<Product> productList = new ArrayList<>();
        for (Product product : getList()) {
            if (product.getCategoryId().equals(categoryId)){
                productList.add(product);
            }
        }
        return productList;
    }

    private Product editProduct(Product newProduct, Product oldProduct){
        oldProduct.setPrice(newProduct.getPrice());
        oldProduct.setDiscount(newProduct.getDiscount());
        oldProduct.setQuantity(newProduct.getQuantity());
        oldProduct.setCategoryId(newProduct.getCategoryId());
        return oldProduct;
    }

}