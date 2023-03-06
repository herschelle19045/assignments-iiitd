package Assignment2;

public class FastFoodRestaurant extends Restaurant {

    private int overallDiscount;

    public FastFoodRestaurant(String name, String address) {
        super(name, address);
    }

    @Override
    public void setOverallDiscount(int overallDiscount) {
        this.overallDiscount = overallDiscount;
    }

    @Override
    public String getCategory() {
        return "Fast Food";
    }

    @Override
    public int getOverallDiscount() {
        return overallDiscount;
    }

    @Override
    public int getAdditionalDiscount() {
        return 25;
    }

}
