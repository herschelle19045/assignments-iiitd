package Assignment2;

import java.util.ArrayList;

public class EliteCustomer extends SpecialCustomer {

    public EliteCustomer(String name, String address) {
        super(name, address);
    }

    @Override
    public int getDeliveryCharge() {
        return 0;
    }

    @Override
    public String getCategory() {
        return "Elite";
    }

    @Override
    public int getDiscount() {
        return 50;
    }
}
