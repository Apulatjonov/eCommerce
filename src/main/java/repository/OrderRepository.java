package repository;

import models.order.Order;

import java.util.ArrayList;
import java.util.List;

public interface OrderRepository extends BaseRepository<Order, String, List<Order>>{

    List<Order> orders = new ArrayList<>();





}
