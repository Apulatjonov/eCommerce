package service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import models.Product;
import models.order.Order;
import repository.FileUtils;
import repository.OrderRepository;
import responses.Responses;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class OrderService extends FileUtils implements OrderRepository, Responses {

    public static final String orderFileUrl = TOKEN + "orderList.json";

    @Override
    public String add(Order order) {
        write(order,orderFileUrl);
        return SUCCESS;
    }

    @Override
    public Order getById(UUID id) {
        for(Order order : getList()) {
            if(order.getId().equals(id))
                return order;
        }
        return null;
    }

    @Override
    public String edit(UUID id, Order newOrder) {
        return "This method is not in use";
    }

    @Override
    public String remove(UUID id) {
        int index = 0;
        List<Order> list = getList();
        for (Order order: list) {
            if (order.getId().equals(id)) {
                list.remove(index); // removing product from list
                writeList(list, orderFileUrl); // writing list to file
                return SUCCESS;
            }
            index++;
        }
        return PRODUCT_NOT_FOUND;
    }

    @Override
    public List<Order> getList() {
        ObjectMapper objectMapper = new ObjectMapper();
        List<Order> orderList = objectMapper.convertValue(read(orderFileUrl), new TypeReference<List<Order>>() { });
        return orderList;
    }


    public  List<Order> getOrdersByCategoryId(UUID categoryId){
        List<Order> orderList = new ArrayList<>();
        for (Order order : getList()) {
            if (order.getCategoryId().equals(categoryId)){
                orderList.add(order);
            }
        }
        return orderList;
    }

    public  List<Order> getOrdersByShopId(UUID shopId){
        List<Order> orderList = new ArrayList<>();
        for (Order order : getList()) {
            if (order.getShopId().equals(shopId)){
                orderList.add(order);
            }
        }
        return orderList;
    }






}
