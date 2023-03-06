package Assignment2;

import java.util.ArrayList;
import java.util.HashMap;

public class Cart {
    private double cartValue;
    private final HashMap<Food, Integer> foodQuantity;
    private final ArrayList<Food> foodList;
    private int deliveryCharge;
    private Restaurant restaurant;
    private final Customer customer;

    public Cart(Customer customer, int deliveryCharge) {
        foodQuantity = new HashMap<>();
        this.deliveryCharge = deliveryCharge;
        foodList = new ArrayList<>();
        this.customer = customer;
    }

    public double getCartValue() {
        return cartValue;
    }

    public int getDeliveryCharge() {
        return deliveryCharge;
    }

    public int getAdditionalDiscount() {
        return 0;
    }

    public void setCartValue(int cartValue) {
        this.cartValue = cartValue;
    }

    public void setDeliveryCharge(int deliveryCharge) {
        this.deliveryCharge = deliveryCharge;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public void addToCart(Food foodItem, int quantity) {
        foodList.add(foodItem);
        foodQuantity.put(foodItem, quantity);
    }

    public void displayItemsInCart() {
        System.out.println("Items in cart - ");
        for(Food item : foodList) {
            System.out.println(item.getId() + " " + restaurant.getName() + " - "
                    + item.getName() + " - " + item.getPrice() + " - "
                    + foodQuantity.get(item) + " - " + item.getOffer() + "% off");
            System.out.println("Delivery charge - " + deliveryCharge + "/-");
            calculateCartValue();
            System.out.println("Total order value - INR " + cartValue + "/-");
        }
    }

    private void calculateCartValue() {
        double value = 0;
        for(Food item : foodList) {
            int quantity = foodQuantity.get(item);
            value += item.getPrice() * quantity;
            value = applyFoodDiscount(item, value);
            Food f = restaurant.getFoodItemById(item.getId());
            f.setQuantity(f.getQuantity()-quantity);
            Order order = new Order(item.getName(), item.getQuantity(), item.getPrice(), restaurant.getName(), deliveryCharge);
            customer.addOrder(order);
        }
        value = applyRestaurantDiscount(value);
        value = applyCustomerDiscount(value);
        value -= customer.getRewardPoints();
        value += deliveryCharge;
        cartValue = value;
        addRewardPoints();
        restaurant.incNumberOfOrders();
    }

    private double applyFoodDiscount(Food food, double value) {
        double rate = food.getOffer();
        double discount = value * rate/100;
        return value-discount;
    }

    private double applyRestaurantDiscount(double value) {
        int rate = restaurant.getOverallDiscount();
        double discount = value * rate/100;
        value -= discount;
        if(value > 100) {
            value -= restaurant.getAdditionalDiscount();
        }
        return value;
    }

    private double applyCustomerDiscount(double value) {
        if(value > 200) {
            value -= customer.getDiscount();
        }
        return value;
    }

    private void addRewardPoints() {
        customer.setRewardPoints(0);
        String category = restaurant.getCategory();
        if(category.equals("")) {
            int r = (int) (cartValue/100);
            customer.addRewardPoints(r*5);
            restaurant.addRewardPoints(r*5);
        }
        else if(category.equals("Special")) {
            int r = (int) (cartValue/150);
            customer.addRewardPoints(r*10);
            restaurant.addRewardPoints(r*10);
        }
        else {
            int r = (int) (cartValue/200);
            customer.addRewardPoints(r*25);
            restaurant.addRewardPoints(r*25);
        }
    }
}
