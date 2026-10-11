package org.example;
import java.io.IOException;
import java.util.*;


public class Admin extends User{
    FoodItem fooditem;
    private final List<FoodItem> items;
    Menu menu;
    OrderManager orderManager;
    public List<Order> VipOrders;
    public List<Order> RegularOrders;

    Map<String, List<Integer>> maxOrders = new HashMap<>();

    public Admin(String email, String password, Menu menu, OrderManager ordermanager){
        super(email, password);
        this.menu = menu;
        orderManager = new OrderManager();
        items = menu.getMenuItems();
        this.orderManager = ordermanager;

        VipOrders = orderManager.getVipOrders();
        RegularOrders = orderManager.getRegularOrders();
    }

    public void Menu_management(){

        System.out.println("i. Add item");
        System.out.println("ii. Update item");
        System.out.println("iii. Remove item");

        Scanner sc = new Scanner(System.in);

        String choice = sc.nextLine();

        //add item
        if(choice.equalsIgnoreCase("i")){

            System.out.print("Enter type: ");
            String type = sc.nextLine();

            System.out.print("Enter foodname: ");
            String foodname = sc.nextLine();

            System.out.print("Enter price: ");
            String price = sc.nextLine();

            System.out.print("Enter status: ");
            String status = sc.nextLine();

            menu.addFoodItem(new FoodItem(type, foodname, price, status));
            System.out.println("ITEM ADDED!");

        }

        //update item
        else if(choice.equalsIgnoreCase("ii")){
            if(items.isEmpty()){
                System.out.println("No items in the menu");
            }
            else{
                //shows the current menu
                System.out.println("Current menu: ");
                for(FoodItem x: items){
                    System.out.println("Type: " + x.getType() + "| " + "Name: " + x.getFoodname() + "| " +
                            "Price " + x.getPrice() + " | " + x.getFoodstatus());
                }
                System.out.println("Which thing you want to update: 1. type, 2.name, 3.price, 4.status");
                int c = sc.nextInt();
                sc.nextLine();

                //update type
                if(c == 1){
                    System.out.print("Enter food name for which you want to update its type: ");
                    String name = sc.nextLine();
                    System.out.print("Enter new type: ");
                    String newtype = sc.nextLine();

                    for(FoodItem x: items) {
                        if (x.getFoodname().equals(name)) {
                            x.setType(newtype);
                            System.out.println("TYPE UPDATED");
                        }
                    }
                }

                //update name
                if(c == 2){
                    System.out.print("Enter old name which you want to update: ");
                    String oldname = sc.nextLine();
                    System.out.print("Enter new name: ");
                    String newname = sc.nextLine();

                    for(FoodItem x: items){
                        if(x.getFoodname().equals(oldname)){
                            x.setFoodname(newname);
                            System.out.println("NAME UPDATED");
                            break;
                        }
                    }
                }

                //update price
                if(c == 3){
                    System.out.print("Enter food name whose price you want to update: ");
                    String name = sc.nextLine();
                    System.out.print("Enter new price ");
                    String newprice = sc.nextLine();

                    for(FoodItem x: items){
                        if(x.getFoodname().equals(name)){
                            x.setPrice(newprice);
                            System.out.println("PRICE UPDATED");
                        }
                    }
                }

                //update status
                if(c == 4){
                    System.out.print("Enter food name whose status you want to update: ");
                    String name = sc.nextLine();
                    System.out.print("Enter new status ");
                    String newstaus = sc.nextLine();

                    for(FoodItem x: items){
                        if(x.getFoodname().equals(name)){
                            x.setFoodstatus(newstaus);
                            System.out.println("STATUS UPDATED");
                        }
                    }
                }
            }
        }

        //remove item
        else if (choice.equalsIgnoreCase("iii")) {
            System.out.print("Enter name of item you want to remove: ");
            String name = sc.nextLine();
            for(FoodItem x: items){
                if(x.getFoodname().equals(name)){
                    items.remove(x);
                    break;
                }
            }

            //removing item would automatically set status to DENIED
            if(!VipOrders.isEmpty()){
                for(Order x : VipOrders){
                    if(x.getItem().getFoodname().equals(name)){
                        x.setOrderStatus("DENIED");
                        VipOrders.remove(x);
                        break;
                    }
                }
            }
            if(!RegularOrders.isEmpty()){
                for(Order x : RegularOrders){
                    if(x.getItem().getFoodname().equals(name)){
                        x.setOrderStatus("DENIED");
                        RegularOrders.remove(x);
                        break;
                    }
                }
            }

        }
        else{
            System.out.println("INVALID CHOICE");
        }
    }
    public void menu_writer() throws IOException {
        new FileWriter().write_menu(menu.getMenuItems());
    }

    public void OrderManagement(){
        System.out.println("i View all orders"); //shows vip orders first
        System.out.println("ii. View pending orders"); //handles vip orders first
        System.out.println("iii. Update order status");
        //System.out.println("iv. Refund price");

        Scanner sc = new Scanner(System.in);
        String choice = sc.nextLine();

        //view vip orders
        if(choice.equalsIgnoreCase("i")){
            if(!VipOrders.isEmpty()){
                System.out.println("VIP ORDERS");
                for(Order order : VipOrders){
                    System.out.println("Customer name: " + order.getCname() +
                            "| Item: " + order.item.getFoodname() +
                            "| Quantity: " + order.getQuantity() +
                            "| Total: " + order.getTotal() +
                            "| Address: " + order.getAddress() +
                            "| Request: " + order.getRequest() +
                            "| Status: " + order.getOrderStatus());
                    System.out.println("------------------------------------------------------------");
                }
            }
            else{
                System.out.println("No VIP orders");
            }
            System.out.println();
            //view regular order
            if(!RegularOrders.isEmpty()){
                System.out.println("Regular Orders:");
                for (Order order : RegularOrders) {
                    System.out.println("Customer name: " + order.getCname() +
                            "| Item: " + order.item.getFoodname() +
                            "| Quantity: " + order.getQuantity() +
                            "| Total: " + order.getTotal() +
                            "| Address: " + order.getAddress() +
                            "| Request: " + order.getRequest() +
                            "| Status: " + order.getOrderStatus());
                    System.out.println("------------------------------------------------------------");
                }
            }
            else{
                System.out.println("No Regular orders");
            }

        }//show pending orders
        else if(choice.equalsIgnoreCase("ii")){

            System.out.println("Pending orders: ");
            System.out.println("-> VIP's pending orders");
            boolean vipcheck = false;
            for(Order order : VipOrders){
                if(order.getOrderStatus().equals("PENDING")){
                    vipcheck = true;
                    System.out.println("Customer name: " + order.getCname() +
                            "| Item: " + order.item.getFoodname() +
                            "| Quantity: " + order.getQuantity() +
                            "| Total: " + order.getTotal() +
                            "| Address: " + order.getAddress() +
                            "| Request: " + order.getRequest() +
                            "| Status: " + order.getOrderStatus());
                    System.out.println("-----------------------------------------------------------------------");
                }
            }
            if(!vipcheck){
                System.out.println("No VIP's pending orders");
            }
            System.out.println();
            System.out.println("-> Regular customers pending orders");
            boolean regularcheck = false;
            for(Order order : RegularOrders){
                if(order.getOrderStatus().equals("PENDING")){
                    regularcheck = true;
                    System.out.println("Customer name: " + order.getCname() +
                            "| Item: " + order.item.getFoodname() +
                            "| Quantity: " + order.getQuantity() +
                            "| Total: " + order.getTotal() +
                            "| Address: " + order.getAddress() +
                            "| Request: " + order.getRequest() +
                            "| Status: " + order.getOrderStatus());
                    System.out.println("----------------------------------------------------------------------------------------");
                }
            }
            if(!regularcheck){
                System.out.println("No regular customer pending orders");
            }
        }

        //update order status
        else if(choice.equalsIgnoreCase("iii")){

            System.out.print("Enter the customer name whose order status you want to update: ");
            String name = sc.nextLine();

            System.out.print("Enter the customer's Order status: ");
            String status = sc.nextLine();

            boolean stat = false;

            if(!VipOrders.isEmpty()){
                for(Order x : VipOrders){
                    if(x.getCname().equals(name)){
                        x.setOrderStatus(status);
                        stat = true;
                    }
                }
            }

            if(!stat){
                if(!RegularOrders.isEmpty()){
                    for(Order x : RegularOrders){
                        if(x.getCname().equals(name)){
                            x.setOrderStatus(status);
                            stat = true;
                        }
                    }
                }
            }

            if(stat){
                System.out.println("Status Updated");
            }
        }
    }
    public void pending_writer() throws IOException {
        new FileWriter().write_order(VipOrders, RegularOrders);
    }

    public void report(){

        int total_orders = RegularOrders.size() + VipOrders.size();

        System.out.println("Total Orders: " + total_orders);

        int sales = 0;
        for(Order x: VipOrders){
            int p = Integer.parseInt(x.getItem().getPrice());
            sales += p * x.getQuantity();
        }
        for(Order x: RegularOrders){
            int p = Integer.parseInt(x.getItem().getPrice());
            sales += p * x.getQuantity();
        }
        System.out.println("Total sales: " + sales);

        int pr = 0;
        for(Order x: VipOrders){
            if(x.getQuantity()>pr){
                pr = x.getQuantity();
            }
        }
        for(Order x: RegularOrders){
            if(x.getQuantity()>pr){
                pr = x.getQuantity();
            }
        }

        System.out.println("Popular items: ");
        for(Order x: VipOrders){
            if(x.getQuantity() == pr){
                System.out.println(x.item.getFoodname() + " - " + x.getQuantity());
                System.out.println("------------------------");
            }
        }
        for(Order x: RegularOrders){
            if(x.getQuantity() == pr){
                System.out.println(x.item.getFoodname() + " - " + x.getQuantity());
                System.out.println("------------------------");
            }
        }
    }


}

