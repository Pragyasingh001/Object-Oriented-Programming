import java.util.*;

public class Main {
    private static final Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {

        List<Customer> customer = new ArrayList<>();
        List<Admin> admin = new ArrayList<>();
        Menu menu = new Menu();
        OrderManager ordermanager = new OrderManager();


        customer.add(new Customer("customer1", "c1", "TOM", "REGULAR", menu, ordermanager));
        customer.add(new Customer("customer2", "c2", "JERRY", "REGULAR", menu, ordermanager));
        customer.add(new Customer("customer3", "c3", "MICKEY", "REGULAR", menu, ordermanager));

        admin.add(new Admin("admin1", "a1", menu, ordermanager));

        menu.addFoodItem(new FoodItem("starter", "paneer tikka", "200", "available"));
        menu.addFoodItem(new FoodItem("starter", "chicken tikka", "250", "available"));
        menu.addFoodItem(new FoodItem("maincourse", "butter chicken", "400", "available"));
        menu.addFoodItem(new FoodItem("maincourse", "shahi paneer", "300", "available"));
        menu.addFoodItem(new FoodItem("dessert", "brownie", "100", "available"));
        menu.addFoodItem(new FoodItem("dessert", "poundcake", "150", "available"));

        while(true){
            System.out.println("BYTE ME :-P");
            System.out.println("1. Log in");
            System.out.println("2. Exit");

            System.out.print("Enter choice: ");
            int choice = sc.nextInt();
            sc.nextLine();

            if(choice == 2){
                break;
            }
            System.out.print("Enter email: ");
            String email = sc.nextLine();

            System.out.print("Enter password: ");
            String password = sc.nextLine();

            Customer isvalcustomer = null;
            Admin isvaladmin = null;

            for(Customer x: customer){
                if(x.getEmail().equals(email) && x.getPassword().equals(password)){
                    isvalcustomer = x;
                    break;
                }
            }
            for(Admin x: admin){
                if(x.getEmail().equals(email) && x.getPassword().equals(password)){
                    isvaladmin = x;
                    break;
                }
            }
            if(isvalcustomer!=null){
                customer_menu(isvalcustomer, menu, ordermanager);
            }
            else if(isvaladmin!=null){
                admin_menu(isvaladmin,menu);
            }
            else{
                System.out.println("invalid");
            }
        }
    }
    private static void customer_menu(Customer customer, Menu menu, OrderManager orderManager){

        //asking for vip
        if(customer.getCtype().equals("REGULAR")){
            System.out.println("GET OUR VIP MEMBERSHIP AT JUST RS. 999");
            System.out.println("ENTER CHOICE : yes / not now");
            String ch = sc.nextLine();

            if(ch.equals("yes")){
                while(true){
                    System.out.print("Enter amount: ");
                    int amount = sc.nextInt();
                    sc.nextLine();

                    if(amount == 999){
                        System.out.println("Processing...");
                        customer.setCtype("VIP"); //making them vip
                        System.out.println("CONGRATULATIONS! You are a VIP member of our BYTE_ME family");
                        break;
                    }
                    else{
                        System.out.println("Enter correct amount");
                    }
                }
            }
        }
        while (true){
            System.out.println("1. BROWSE MENU");
            System.out.println("2. CART OPTIONS");
            System.out.println("3. ORDER TRACKING AND CANCELLING");
            System.out.println("4. GIVE REVIEWS");
            System.out.println("5. VIEW REVIEWS");
            System.out.println("6. SHOW HISTORY");
            System.out.println("7. EXIT APP");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice){
                case 1:
                    customer.BrowseMenu();
                    break;
                case 2:
                    customer.OrderManagement(customer);
                    break;
                case 3:
                    boolean found = false;
                    List<Order> ordersToCancel = new ArrayList<>();

                    for (Order x : orderManager.getVipOrders()) {
                        if (x.getCname().equals(customer.getName())) {
                            found = true;
                            String track = x.getOrderStatus();
                            System.out.println("Order item: " + x.getItem() + ", Quantity: " + x.getQuantity() + ", Status: " + track);

                            System.out.println("Cancel Order : yes/no");
                            String ch = sc.nextLine();
                            if (ch.equalsIgnoreCase("no")) {
                                break;
                            }
                            if (ch.equalsIgnoreCase("yes")
                                    && (!track.equalsIgnoreCase("out for delivery"))
                                    && (!track.equalsIgnoreCase("denied"))
                                    && (!track.equalsIgnoreCase("completed"))) {
                                ordersToCancel.add(x); // Add order to the list for cancellation later
                            } else {
                                System.out.println("Sorry order can't be canceled now");
                            }
                        }
                    }

                    // Remove the orders outside the iteration
                    for (Order order : ordersToCancel) {
                        customer.cancelVipOrder(order);
                    }

                    if (!found) { // for regular orders
                        ordersToCancel = new ArrayList<>();

                        for (Order x : orderManager.getRegularOrders()) {
                            if (x.getCname().equals(customer.getName())) {
                                String track = x.getOrderStatus();
                                System.out.println("Order item: " + x.getItem() + ", Quantity: " + x.getQuantity() + ", Status: " + track);

                                System.out.println("Cancel Order : yes/no");
                                String ch = sc.nextLine();
                                if (ch.equalsIgnoreCase("no")) {
                                    continue;
                                }
                                if (ch.equalsIgnoreCase("yes") && (!track.equalsIgnoreCase("out for delivery")) && (!track.equalsIgnoreCase("denied")) && (!track.equalsIgnoreCase("completed"))  ) {
                                    ordersToCancel.add(x); // Add order to the list for cancellation later
                                } else {
                                    System.out.println("Sorry order can't be canceled now");
                                }
                            }
                        }

                        // Remove the orders outside the iteration
                        for (Order order : ordersToCancel) {
                            customer.cancelRegularOrder(order);
                        }
                    }
                    break;
                case 4:
                    customer.SubmitReview();
                    break;
                case 5:
                    customer.ViewReview();
                    break;
                case 6:
                    boolean status = false;
                    for(Order x: customer.getHistory()){
                        if(x.getCname().equals(customer.getName())){
                            System.out.println("ITEM: "+ x.getItem() + "| QUANTITY: " + x.getQuantity() +
                                    "| TOTAL: " + x.getTotal() + "| Order status: " + x.getOrderStatus() +"| Address: " + x.getAddress());
                            System.out.println("---------------------------------------------------------------------------------");
                            status = true;
                        }
                    }
                    if(!status){
                        System.out.println("Your History is empty!");
                    }
                    break;
                case 7:
                    return;
                default:
                    System.out.println("Invalid choice");
            }

        }
    }
    private static void admin_menu(Admin admin, Menu menu){
        while (true){
            System.out.println("1. MENU MANAGEMENT");
            System.out.println("2. ORDER MANAGEMENT");
            System.out.println("3. REPORT GENERATION");
            System.out.println("4. VIEW CURRENT MENU ITEMS");
            System.out.println("5. EXIT APP");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice){
                case 1:
                    admin.Menu_management();
                    break;
                case 2:
                    admin.OrderManagement();
                    break;
                case 3:
                    admin.report();
                    break;
                case 4:
                    for(FoodItem x: menu.getMenuItems()){
                        System.out.println("TYPE: " + x.getType() + "| " + "NAME: " + x.getFoodname()
                                + "| " + "PRICE: " + x.getPrice() + "|" + "STATUS: " + x.getFoodstatus());
                        System.out.println("-------------------------------------------------------------------");
                    }
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Invalid choice.");

            }

        }
    }



}