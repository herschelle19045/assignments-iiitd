package Assignment2;

import java.util.ArrayList;

public class CustomerUser implements User {
    private final ArrayList<Customer> customerList;

    public CustomerUser() {
        customerList = Main.customerList;
    }

    public void add(Customer c) {
        customerList.add(c);
    }

    @Override
    public void display() {
        displayCustomers();
    }

    @Override
    public boolean search(String name) {
        return searchCustomer(name);
    }

    @Override
    public void printDetails(String name) {
        if(!search(name)) {
            System.out.println("No such customer found");
        }
        else {
            Customer c = null;
            for(Customer customer : customerList) {
                if(customer.getName().equals(name)) {
                    c = customer;
                }
            }
            System.out.println(c.getName()
                    + (c.getCategory().equals("") ? "" :" (" + c.getCategory() + "), ")
                    + c.getAddress() + ", " + c.getBalance() + "/-");
        }
    }

    private void displayCustomers() {
        for (int i = 0; i < customerList.size(); i++) {
            Customer c = customerList.get(i);
            String category = c.getCategory();
            System.out.println("    " + (i+1) + ") " + c.getName() + (category.equals("") ? "" :" (" + c.getCategory() + ")" ) );
        }
    }

    private boolean searchCustomer(String name) {
        for(Customer c : customerList) {
            if(c.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }
}
