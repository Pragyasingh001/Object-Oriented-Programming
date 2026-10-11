package org.example;
import java.util.ArrayList;
import java.util.List;

public class OrderManager {
    private List<Order> VipOrders;
    private List<Order> RegularOrders;

    public OrderManager(){
        VipOrders = new ArrayList<>();
        RegularOrders = new ArrayList<>();

    }

    public List<Order> getVipOrders(){
        return VipOrders;
    }

    public void setVipOrders(List<Order> vipOrders) {
        VipOrders = vipOrders;
    }

    public List<Order> getRegularOrders() {
        return RegularOrders;
    }

    public void setRegularOrders(List<Order> regularOrders) {
        RegularOrders = regularOrders;
    }

    public void addVipOrder(Order order) {
        VipOrders.add(order);
    }

    public void addRegularOrder(Order order) {
        RegularOrders.add(order);
    }
}
