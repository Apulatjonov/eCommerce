package repository;

import models.Product;

import java.util.ArrayList;
import java.util.List;

public interface ProductRepository extends BaseRepository<Product, String, List<Product>>{
    List<Product> productList = new ArrayList<>();

}
