package org.example;
import java.io.*;
import java.util.*;

public class FileWriter{

    public void write_menu(List<FoodItem> menuItems) throws IOException {
        try(DataOutputStream out = new DataOutputStream(new BufferedOutputStream(new FileOutputStream("menu.txt"))))
        {
            for(FoodItem x: menuItems){
                out.writeUTF(x.getType());
                out.writeUTF(x.getFoodname());
                out.writeUTF(x.getPrice());
                out.writeUTF(x.getFoodstatus());
            }
        }
        catch(EOFException e){
            System.out.println("Menu.txt: error writing " + e.getMessage());
        }
    }

public void write_order(List<Order> VipOrders, List<Order> RegularOrders) throws IOException {
    try (DataOutputStream out = new DataOutputStream(new BufferedOutputStream(new FileOutputStream("orders.txt", true)))) {
        boolean wroteAnyOrder = false;

        // VIP's pending orders
        for (Order order : VipOrders) {
            if (order.getOrderStatus().equals("PENDING")) {
                wroteAnyOrder = true;
                out.writeUTF("VIP Customer");
                out.writeUTF(order.getCname());
                out.writeUTF(order.item.getFoodname());
                out.writeInt(order.getQuantity());
                out.writeDouble(order.getTotal());
                out.writeUTF(order.getAddress());
                out.writeUTF(order.getRequest());
                out.writeUTF(order.getOrderStatus());
            }
        }

        // Regular customers' pending orders
        for (Order order : RegularOrders) {
            if (order.getOrderStatus().equals("PENDING")) {
                wroteAnyOrder = true;
                out.writeUTF("Regular Customer");
                out.writeUTF(order.getCname());
                out.writeUTF(order.item.getFoodname());
                out.writeInt(order.getQuantity());
                out.writeDouble(order.getTotal());
                out.writeUTF(order.getAddress());
                out.writeUTF(order.getRequest());
                out.writeUTF(order.getOrderStatus());
            }
        }

        if (!wroteAnyOrder) {
            System.out.println("No pending orders to write to orders.txt.");
        }
    } catch (IOException e) {
        e.printStackTrace();
    }
}



}