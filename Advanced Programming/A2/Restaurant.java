package Assignment2;

import java.util.ArrayList;

public class Restaurant {
    private final String name;
    private final ArrayList<Food> foodList;
    private int rewardPoints;
    private int overallDiscount;
    private String address;
    private int numberOfOrders;

    public Restaurant(String name, String address) {
        this.name = name;
        foodList = new ArrayList<>();
        rewardPoints = 0;
        overallDiscount = 0;
        this.address = address;
        numberOfOrders = 0;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return "";
    }

    public int getRewardPoints() {
        return rewardPoints;
    }

    public int getOverallDiscount() {
        return 0;
    }

    public int getAdditionalDiscount() {
        return 0;
    }

    public void addRewardPoints(int amount) {
        rewardPoints += amount;
    }

    public void setOverallDiscount(int overallDiscount) {
    }

    public void addFood(Food food) {
        foodList.add(food);
        food.displayDetails();
    }

    public Food getFoodItemById(int id) {
        for(Food item : foodList) {
            if (item.getId() == id) {
                return item;
            }
        }
        return null;
    }

    public void displayFoodItems() {
        System.out.println("Choose item by code:");
        for(Food item : foodList) {
            System.out.print("    " + item.getId() + " " + name + " - ");
            item.displayDetails();
        }
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getNumberOfOrders() {
        return numberOfOrders;
    }

    public void incNumberOfOrders() {
        numberOfOrders++;
    }
}
