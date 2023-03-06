package Assignment2;

import java.util.ArrayList;

public class SpecialCustomer extends Customer {

    public SpecialCustomer(String name, String address) {
        super(name, address);
    }

    @Override
    public int getDeliveryCharge() {
        return 20;
    }

    @Override
    public String getCategory() {
        return "Special";
    }

    @Override
    public int getDiscount() {
        return 25;
    }


}
