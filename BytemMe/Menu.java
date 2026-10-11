import java.util.*;

public class Menu {

    private List<FoodItem> menuItems;

    public Menu(){
        menuItems = new ArrayList<>();
    }

    public void addFoodItem(FoodItem item) {
        menuItems.add(item);
    }

    public List<FoodItem> getMenuItems() {
        return menuItems;
    }
}
