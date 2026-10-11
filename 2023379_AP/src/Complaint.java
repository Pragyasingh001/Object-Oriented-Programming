
public class Complaint{
    public String Des;
    public String status = "PENDING";

    public Complaint(String description){   //Constructor is being called from the student class
        this.Des = description;
    }

    @Override       //RUNTIME POLYMORPHISM
    public String toString() {
        return " | Description and Date: " + Des + "| Status:" + status;
    }
}

