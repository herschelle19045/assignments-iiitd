package Assignment2;

public class Customer {
    private final String name;
    private final Wallet wallet;
    private final String address;
    private Cart cart;
    private final RecentOrders recentOrders;
    private int rewardPoints;
    private int totalRewardPointsEarned;

    public Customer(String name, String address) {
        this.name = name;
        this.address = address;
        wallet = new Wallet(1000);
        cart = new Cart(this, getDeliveryCharge());
        recentOrders = new RecentOrders();
        rewardPoints = 0;
        totalRewardPointsEarned = 0;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public int getDeliveryCharge() {
        return 40;
    }

    public String getCategory() {
        return "";
    }

    public int getDiscount() {
        return 0;
    }

    public int getRewardPoints() {
        return rewardPoints;
    }

    public double getCartValue() {
        return cart.getCartValue();
    }

    public int getTotalRewardPointsEarned() {
        return totalRewardPointsEarned;
    }

    public void setRestaurant(Restaurant r) {
        cart.setRestaurant(r);
    }

    public void setRewardPoints(int rewardPoints) {
        this.rewardPoints = rewardPoints;
    }

    public void addRewardPoints(int amount) {
        totalRewardPointsEarned += amount;
        rewardPoints += amount;
    }

    public void add(Food food, int quantity) {
        cart.addToCart(food, quantity);
        System.out.println(food.getName() + " successfully added to cart");
    }

    public void displayItemsInCart() {
        cart.displayItemsInCart();
    }

    public void deductBalance(double amount) {
        wallet.deductBalance(amount);
    }

    public double getBalance() {
        return wallet.getBalance();
    }

    public void addOrder(Order order) {
        recentOrders.addOrder(order);
    }

    public void printRecentOrders() {
        recentOrders.displayOrders();
    }

    public void emptyCart() {
        cart = new Cart(this, getDeliveryCharge());
    }
}
