package Assignment2;

public class Order {
    private final String itemName;
    private final int quantity;
    private final double price;
    private final String restaurant;
    private final int deliveryCharge;

    public Order(String itemName, int quantity, double price, String restaurant, int deliveryCharge) {
        this.itemName = itemName;
        this.quantity = quantity;
        this.price = price;
        this.restaurant = restaurant;
        this.deliveryCharge = deliveryCharge;
    }

    public String getItemName() {
        return itemName;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return price;
    }

    public String getRestaurant() {
        return restaurant;
    }

    public int getDeliveryCharge() {
        return deliveryCharge;
    }
}
