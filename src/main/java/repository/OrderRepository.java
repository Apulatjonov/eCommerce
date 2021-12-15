package repository;

import models.order.Order;

import java.util.*;

public interface OrderRepository extends BaseRepository<Order, String, List<Order>>{

    Map<UUID, List<Order>> orders = new HashMap<>(); // UUID --> userId





}
