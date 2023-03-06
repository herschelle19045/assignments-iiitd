package Assignment2;

public class AuthenticRestaurant extends FastFoodRestaurant {

    public AuthenticRestaurant(String name, String address) {
        super(name, address);
    }

    @Override
    public String getCategory() {
        return "Authentic";
    }

    public int getAdditionalDiscount() {
        return 50;
    }

}
