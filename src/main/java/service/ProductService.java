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
        List<Product> productList = getList(); // getting list from file
        productList.add(product); // adding product to list
        writeFile(productList); // writing list to file
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
        int index = 0;
        List<Product> list = getList();
        for (Product product: list) {
            if (product.getId().equals(id)) {
                Product editedProduct = editProduct(newProduct,product);
                list.set(index,editedProduct); // editing list
                writeFile(list); // writing to file
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
        for (Product product : list) {
            if (product.getId().equals(id)) {
                list.remove(index); // removing product from list
                writeFile(list); // writing list to file
                return SUCCESS;
            }
            index++;
        }
        return PRODUCT_NOT_FOUND;
    }

    private Product editProduct(Product newProduct, Product oldProduct){
        oldProduct.setPrice(newProduct.getPrice());
        oldProduct.setDiscount(newProduct.getDiscount());
        oldProduct.setQuantity(newProduct.getQuantity());
        return oldProduct;
    }

    @Override
    public List<Product> getList() {
        return readFile(productFileUrl);
    }


    // Working with files
    private void writeFile(List<Product> list){
        String json = toJson(list);
        writeToFile(productFileUrl, json);
    }

    private List<Product> readFile(String productFileUrl){
        return fromJson(readFromFile(productFileUrl));
    }

    public List<Product> fromJson(String json){
        ObjectMapper objectMapper = new ObjectMapper();
        List<Product> list = new ArrayList<>();
        try {
            if(!json.equals(""))
                list = objectMapper.readValue(json, new TypeReference<>() {});
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return list;
    }

    public String toJson(List<Product> list){
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(list);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return "EXCEPTION";
    }
    //working with files end
}
