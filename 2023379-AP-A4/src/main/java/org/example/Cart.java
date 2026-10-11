package org.example;

public class Cart {
    public String cname;
    public FoodItem food;
    public int quantity;


    public Cart(String cname,FoodItem fooditem, int quantity){
        this.cname = cname;
        this.food = fooditem;
        this.quantity = quantity;

    }

    @Override
    public String toString(){
        return "Item details :" + food +  "|" + " Quantity: " + quantity +  "|" + " Total price: " + Integer.parseInt(food.getPrice())*quantity;
    }

    public String getCname(){
        return cname;
    }
    public int getQuantity(){
        return quantity;
    }
    public FoodItem getFood(){
        return food;
    }
    public void setQuantity(int quantity){
        this.quantity = quantity;
    }
}
