package service;

import models.order.Order;
import repository.OrderRepository;
import responses.Responses;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class OrderService implements OrderRepository, Responses {

    @Override
    public String add(Order order) {
        return null;
    }

    @Override
    public Order getById(UUID id) {
        return null;
    }

    @Override
    public String edit(UUID id, Order order) {
        return null;
    }

    @Override
    public String remove(UUID id) {
        return null;
    }

    @Override
    public List<Order> getList() {
        return null;
    }
}
