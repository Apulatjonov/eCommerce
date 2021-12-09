package service;

import models.Product;
import repository.ProductRepository;
import responses.Responses;

import java.util.List;
import java.util.UUID;

public class ProductService implements ProductRepository, Responses {

    @Override
    public String add(Product product) {
        return null;
    }

    @Override
    public Product getById(UUID id) {
        return null;
    }

    @Override
    public String edit(UUID id, Product product) {
        return null;
    }

    @Override
    public String remove(UUID id) {
        return null;
    }

    @Override
    public List<Product> getList() {
        return null;
    }
}
