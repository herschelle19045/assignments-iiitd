package Assignment2;

public class Food {
    private final int id;
    private String name;
    private double price;
    private int quantity;
    private int offer;
    private String category;
    private static int count;

    public Food(String name, double price, int quantity, int offer, String category) {
        id = ++count;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.offer = offer;
        this.category = category;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getOffer() {
        return offer;
    }

    public String getCategory() {
        return category;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setOffer(int offer) {
        this.offer = offer;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void displayDetails() {
        System.out.println(id + " " + name + " " + price + " " + quantity + " " + offer + "% off " + category);
    }

}
