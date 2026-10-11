package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Testing {
    private Customer customer;
    private Menu menu;
    private OrderManager orderManager;

    @BeforeEach
    void setUp() {
        menu = new Menu();
        customer = new Customer("customer", "pass", "user", "Regular", menu, orderManager);
        FoodItem burger = new FoodItem("maincourse", "biryani", "500", "available");
        burger.setFoodstatus("available"); //sample items
        menu.addFoodItem(burger);
        customer.addToItems(burger);
        FoodItem soyachaap = new FoodItem("Starter", "soya chaap", "300", "not available");
        soyachaap.setFoodstatus("out of stock");
        menu.addFoodItem(soyachaap);
        customer.addToItems(soyachaap);

        orderManager = new OrderManager();
    }

    @Test
    void testAddingUnavailableItemCart() {

        String outOfStockItem = "soya chaap";

        StringBuilder input = new StringBuilder();
        input.append(outOfStockItem).append("\n1\n");
        customer.sc = new java.util.Scanner(input.toString());
        customer.addItem();

        assertNotNull(customer.cart, "cart object not initialized properly ");
        assertTrue(customer.cart.isEmpty(), "cart is expected to be empty since the item is out of stock");

    }

    @Test
    void testForUpdateItem() {
        // Add an item to the cart
        String availableItem = "biryani";
        customer.sc = new java.util.Scanner(availableItem + "\n1\n"); // Simulate input
        customer.addItem();

        // update the quantity of the item in the cart
        String update_quantity = "ii\nbiryani\n3\n";
        customer.sc = new java.util.Scanner(update_quantity);
        customer.OrderManagement(customer);

        // checking the updated quantity
        assertEquals(3, customer.cart.get(0).getQuantity(), "quantity of the item in the cart should now be 3");
    }

    @Test
    void testAddItem_CheckAmount() {
        // Add an item to the cart
        String availableItem = "biryani";
        customer.sc = new java.util.Scanner(availableItem + "\n2\n"); // Simulate input

        customer.addItem();
        //checking the item is added
        assertEquals(1, customer.cart.size(), "cart should not be empty and should have one item");
        Cart item = customer.cart.get(0);
        assertEquals("biryani", item.getFood().getFoodname(), "The food in the cart is expected to be biryani");
        assertEquals(2, item.getQuantity(), "The cart should contain 2 of the biryani item.");

        // checking total amount
        int totalamount = 500 * 2;
        assertEquals(totalamount, Integer.parseInt(item.getFood().getPrice()) * item.getQuantity(),
                "Total price in the cart match the expected amount");
    }

    @Test
    void testForNegativeQuantities() {
        String availableItem = "biryani";
        customer.sc = new java.util.Scanner(availableItem + "\n1\n");
        customer.addItem();  //adding to cart

        // update negative quantity
        String negative_quantity = "ii\nbiryani\n-1\n";
        customer.sc = new java.util.Scanner(negative_quantity);
        customer.OrderManagement(customer);


        // checking the quantity remains unchanged
        assertEquals(1, customer.cart.get(0).getQuantity(), "quantity should be positive not negative");
    }
}





