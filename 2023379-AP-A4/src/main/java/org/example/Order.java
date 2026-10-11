package org.example;
import java.util.*;

public class Order{

    String orderStatus = "PENDING";
    String Cname;
    FoodItem item;
    int quantity;
    int total;
    String address;
    String request;
    String ctype;

    public Order(String cname, FoodItem food, int quantity, int total, String address, String request) {

        this.Cname = cname;
        this.item = food;
        this.quantity = quantity;
        this.total = total;
        this.address = address;
        this.request = request;

    }

    public String getOrderStatus(){
        return orderStatus;
    }

    public String getCname() {
        return Cname;
    }

    public FoodItem getItem() {
        return item;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getTotal() {
        return total;
    }

    public String getAddress() {
        return address;
    }

    public String getRequest() {
        return request;
    }

    public String getCtype() {
        return ctype;
    }

    public void setOrderStatus(String s){
        this.orderStatus = s;
    }

}

