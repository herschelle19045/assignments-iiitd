package Assignment2;

public class Company {
    private double earnings;
    private int deliveryCharges;

    public Company() {
        earnings = 0;
        deliveryCharges = 0;
    }

    public double getEarnings() {
        return earnings;
    }

    public int getDeliveryCharges() {
        return deliveryCharges;
    }

    public void addMoney(double amount) {
        earnings += amount;
    }

    public void addDeliveryMoney(int amount) {
        deliveryCharges += amount;
    }
}
