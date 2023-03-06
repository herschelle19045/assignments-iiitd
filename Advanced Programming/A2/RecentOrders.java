package Assignment2;

import java.util.ArrayList;

public class RecentOrders {
    private final ArrayList<Order> orderList;

    public RecentOrders() {
        orderList = new ArrayList<>();
    }

    public void addOrder(Order order) {
        orderList.add(order);
    }

    public void displayOrders() {
        int size = orderList.size();
        if(size < 10) {
            for(Order order : orderList) {
                System.out.println("Bought item: " + order.getItemName() + ", "
                + "quantity; " + order.getQuantity() + " for RS " + order.getPrice()
                + " from Restaurant " + order.getRestaurant() + " and delivery charge: "
                + order.getDeliveryCharge());
            }
        }
        else {
            for (int i = 0; i < 10; i++) {
                Order order = orderList.get(i);
                System.out.println("Bought item: " + order.getItemName() + ", "
                        + "quantity; " + order.getQuantity() + " for RS " + order.getPrice()
                        + " from Restaurant " + order.getRestaurant() + " and delivery charge: "
                        + order.getDeliveryCharge());
            }
        }
    }
}
