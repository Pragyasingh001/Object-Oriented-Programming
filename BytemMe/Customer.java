import java.util.*;

public class Customer extends User{
    public List<FoodItem> items;
    Order order;
    private OrderManager ordermanager;
    private Scanner sc = new Scanner(System.in);
    Menu menu;
    public List<Cart> cart;
    public List<Order> history;


    public Customer(String email, String password, String name, String ctype, Menu menu, OrderManager ordermanager){
        super(email, password, name, ctype);
        this.menu = menu;
        this.ordermanager = ordermanager;
        items = menu.getMenuItems();
        cart = new ArrayList<>();
        history = new ArrayList<>();
    }

    public void BrowseMenu(){
        System.out.println("i. VIEW ITEMS");
        System.out.println("ii. SEARCH FOOD BY NAME");
        System.out.println("iii. SEARCH BY CATEGORY");
        System.out.println("iv. SORT ITEMS");

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter choice: ");
        String choice = sc.nextLine();


        //view items
        if(choice.equalsIgnoreCase("i")){
            for(FoodItem x: items){
                System.out.println("TYPE: " + x.getType() + "| " + "NAME: " + x.getFoodname()
                        + "| " + "PRICE: " + x.getPrice() + "|" + "STATUS: " + x.getFoodstatus());
            }
            System.out.println("-----------------------------------------------------------------------");
        }

        //search by name
        else if(choice.equalsIgnoreCase("ii")){
            int found = 0;

            System.out.print("Enter the food name you want: ");
            String name = sc.nextLine();
            for(FoodItem x: items){
                if(x.getFoodname().equals(name)){
                    System.out.println("TYPE: " + x.getType() + "| " + "NAME: " + x.getFoodname()
                            + "| " + "PRICE: " + x.getPrice() + "| STATUS: " + x.getFoodstatus());
                    found = 1;
                    break;
                }
            }
            if(found == 0){
                System.out.println("FoodItem not available");
            }
        }

        //search by category
        else if(choice.equalsIgnoreCase("iii")){

            System.out.print("Enter the category you want: ");
            String category = sc.nextLine();

            int found = 0;
            for(FoodItem x: items){
                if(x.getType().equals(category)){
                    System.out.println("TYPE: " + x.getType() + "| " + "NAME: " + x.getFoodname()
                            + "| " + "PRICE: " + x.getPrice() + "STATUS: " + x.getFoodstatus());
                    found = 1;
                    System.out.println("--------------------------------------------------------------------");
                }
            }

            if(found == 0){
                System.out.println("Enter correct Category");
            }
        }

        //sort by price
        else if(choice.equalsIgnoreCase("iv")){
            List<FoodItem> sortedItems = new ArrayList<>(items);
            sortedItems.sort(new Comparator<FoodItem>() {
                @Override
                public int compare(FoodItem first_item, FoodItem second_item) {
                    int price1 = Integer.parseInt(first_item.getPrice());
                    int price2 = Integer.parseInt(second_item.getPrice());
                    return Integer.compare(price1, price2);
                }

            });
            for (FoodItem x : sortedItems) {
                System.out.println("TYPE: " + x.getType() + "| " + "NAME: " + x.getFoodname()
                        + "| " + "PRICE: " + x.getPrice() + " | STATUS: " + x.getFoodstatus());
            }
            System.out.println("--------------------------------------------------------------------");
        }

        else{
            System.out.println("INVALID CHOICE");
        }
    }

    public void OrderManagement(Customer customer){
        System.out.println("i. ADD ITEM TO CART");
        System.out.println("ii. MODIFY QUANTITIES");
        System.out.println("iii. REMOVE ITEMS FROM CART");
        System.out.println("iv. TOTAL AMOUNT AND CHECKOUT");
        System.out.println("v. TOTAL");

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter choice: ");
        String choice = sc.nextLine();

        //add item
        if(choice.equalsIgnoreCase("i")){
            System.out.print("Enter name of the food you want to add: ");
            String foodname = sc.nextLine();
            System.out.print("Enter quantity of the food: ");
            int quantity = sc.nextInt();
            sc.nextLine();


            FoodItem food = null;
            for(FoodItem x: items){
                if((x.getFoodname().equals(foodname)) && (x.getFoodstatus().equalsIgnoreCase("available"))){
                    food = x;
                    break;
                }
            }

            if(food != null){
                cart.add(new Cart(customer.getName(), food, quantity));
                System.out.println("Item added to cart!");
            }
            else{
                System.out.println("Item not available");
            }
        }

        //modify quantities
        else if(choice.equalsIgnoreCase("ii")){

            if(cart.isEmpty()){
                System.out.println("Your cart is Empty");
            }
            else{
                System.out.println("YOUR CURRENT CART");
                for(Cart x: cart){
                    if(customer.getName().equals(x.getCname())){
                        System.out.println(x);
                    }
                }
                System.out.print("Enter the food name whose quantity you want to update: ");
                String foodname = sc.nextLine();

                System.out.print("Enter new quantity: ");
                int newquantity = sc.nextInt();
                sc.nextLine();

                for(Cart x: cart){
                    if(x.food.getFoodname().equals(foodname)){
                        x.setQuantity(newquantity);
                        break;
                    }
                }

                System.out.println("YOUR CURRENT CART");

                for(Cart x: cart){
                    if(customer.getName().equals(x.getCname())){
                        System.out.println(x);
                    }
                }
            }
        }

        //remove items
        else if (choice.equalsIgnoreCase("iii")) {
            if(cart.isEmpty()){
                System.out.println("Your cart is Empty");
            }
            else{
                System.out.println("YOUR CURRENT CART");
                for(Cart x: cart){
                    if(customer.getName().equals(x.getCname())){
                        System.out.println(x);
                    }
                }
                System.out.print("Enter the food name you want to remove from the cart: ");
                String name = sc.nextLine();

                for(Cart x: cart){
                    if(x.food.getFoodname().equals(name)){
                        cart.remove(x);
                        break;
                    }
                }
                System.out.println("YOUR CURRENT CART");

                for(Cart x: cart){
                    if(customer.getName().equals(x.getCname())){
                        System.out.println(x);
                    }
                }
            }
        }

        //total and checkout
        else if(choice.equalsIgnoreCase("iv")){

            int total = 0;
            for(Cart x: cart){
                if(customer.getName().equals(x.getCname())){ //total amount
                    total = total + (Integer.parseInt(x.food.getPrice()) * x.getQuantity());
                }
            }
            if(total==0){
                System.out.println("Cart is empty.");
                return;
            }
            System.out.print("Total amount to be payed: " + total);
            System.out.println();
            System.out.print("Any special request? Enter the request or nothing: ");
            String request = sc.nextLine();

            System.out.print("Enter your address: ");
            String address = sc.nextLine();

            List<Cart> itemsToRemove = new ArrayList<>();

            for (Cart x : cart) {
                Order order1 = new Order(x.getCname(), x.getFood(), x.getQuantity(),
                        total, address, request);
                if (customer.getName().equals(x.getCname())) { // customer who is currently logged in and their item in cart
                    if (customer.getCtype().equals("VIP")) {
                        ordermanager.addVipOrder(order1);
                    } else {
                        ordermanager.addRegularOrder(order1);
                    }
                    itemsToRemove.add(x); // Add item to removal list
                }
                history.add(order1);
            }

            // Remove items from cart outside the loop
            cart.removeAll(itemsToRemove);

            System.out.println("Order placed, ThankYou!");
        }
        //total
        else if(choice.equalsIgnoreCase("v")){
            int total = 0;
            for(Cart x: cart){
                if(customer.getName().equals(x.getCname())){ //total amount
                    total = total + (Integer.parseInt(x.food.getPrice()) * x.getQuantity());
                }
            }
            if(total==0){
                System.out.println("Cart is empty.");
                return;
            }
            System.out.print("Total amount to be payed: " + total + "\n");
        }
    }

    //REVIEWS
    public void SubmitReview(){

        System.out.print("Enter the food name for which you want to give review: ");
        String name = sc.nextLine();

        boolean found = false;
        for(FoodItem x: items){
            if(x.getFoodname().equals(name)){
                System.out.println("TYPE: " + x.getType() + "| " + "NAME: " + x.getFoodname()
                        + "| " + "PRICE: " + x.getPrice() + "| STATUS: " + x.getFoodstatus());
                found = true;
                break;
            }
        }

        if(!found){
            System.out.println("No such food in menu");
            return;
        }

        System.out.print("Enter Rating: ");
        int rating = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter message: ");
        String message  = sc.nextLine();

        for(FoodItem x: items){
            if(x.getFoodname().equals(name)){
                x.getReviews().add(new Review(rating, message)); //x is the particular item for which the reviews get added
                break;
            }
        }
        System.out.println("Thanks for Review!");
    }

    public void ViewReview(){
        System.out.print("Enter the food name for which you want to see review: ");
        String name = sc.nextLine();

        boolean found = false;

        for(FoodItem x: items){
            if(x.getFoodname().equals(name)){
                if(!x.getReviews().isEmpty()){
                    System.out.println(x.getReviews());
                    found = true;
                    break;
                }
            }
        }
        if(!found){
            System.out.println("No reviews for the item");
        }

    }

    public void cancelVipOrder(Order order){
        ordermanager.getVipOrders().remove(order);
        System.out.println("Order cancelled");
        order.setOrderStatus("Cancelled");
    }

    public void cancelRegularOrder(Order order){
        ordermanager.getRegularOrders().remove(order);
        System.out.println("Order cancelled");
        order.setOrderStatus("Cancelled");
    }

    public List<Order> getHistory(){
        return history;
    }

}

