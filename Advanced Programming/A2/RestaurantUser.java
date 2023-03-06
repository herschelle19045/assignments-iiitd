package Assignment2;

import java.util.ArrayList;

public class RestaurantUser implements User {
    private final ArrayList<Restaurant> restaurantList;

    public RestaurantUser() {
        restaurantList = Main.restaurantList;
    }

    public void add(Restaurant r) {
        restaurantList.add(r);
    }

    @Override
    public void display() {
        displayRestaurants();
    }

    @Override
    public boolean search(String name) {
        return searchRestaurant(name);
    }

    @Override
    public void printDetails(String name) {
        if(!search(name)) {
            System.out.println("No such customer found");
        }
        else {
            Restaurant r = null;
            for(Restaurant restaurant : restaurantList) {
                if(restaurant.getName().equals(name)) {
                    r = restaurant;
                }
            }
            System.out.println(r.getName()
                    + (r.getCategory().equals("") ? "" :" (" + r.getCategory() + "), ")
                    + r.getAddress());
        }
    }

    private void displayRestaurants() {
        System.out.println("Choose Restaurant");
        for (int i = 0; i < restaurantList.size(); i++) {
            Restaurant r = restaurantList.get(i);
            String category = r.getCategory();
            System.out.println("    " + (i+1) + ") " + r.getName() + (category.equals("") ? "" :" (" + r.getCategory() + ")" ) );
        }
    }

    private boolean searchRestaurant(String name) {
        for(Restaurant r : restaurantList) {
            if(r.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }
}
