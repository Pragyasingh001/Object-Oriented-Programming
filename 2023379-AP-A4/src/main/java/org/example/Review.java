package org.example;
public class Review {
    int rating;
    String message;

    public Review(int rating, String message){
        this.rating = rating;
        this.message = message;
    }
    @Override
    public String toString(){
        return "Ratings: " + rating + " " + "Comments: " + message;
    }
}
