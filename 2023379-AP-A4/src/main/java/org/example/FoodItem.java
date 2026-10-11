package org.example;
import java.util.ArrayList;
import java.util.List;

public class FoodItem {
    private String type;
    private String foodname;
    private String price;
    private String foodstatus;
    private List<Review> reviews;

    public FoodItem(String type, String foodname, String price, String foodstatus) {
        this.type = type;
        this.foodname = foodname;
        this.price = price;
        this.foodstatus = foodstatus;
        reviews = new ArrayList<>();
    }

    public String getType() {
        return type;
    }

    public String getFoodname() {
        return foodname;
    }

    public String getPrice() {
        return price;
    }

    public String getFoodstatus() {
        return foodstatus;
    }
    public void setType(String type){
        this.type = type;
    }
    public void setFoodname(String foodname) {
        this.foodname = foodname;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setFoodstatus(String foodstatus) {
        this.foodstatus = foodstatus;
    }
    public List<Review> getReviews(){
        return reviews;
    }

    @Override
    public String toString() {
        return "TYPE: " + type + " | NAME: " + foodname + " | PRICE: " + price + " | STATUS: " + foodstatus;
    }





}