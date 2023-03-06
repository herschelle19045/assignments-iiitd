package Assignment2;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    static ArrayList<Restaurant> restaurantList = new ArrayList<>();
    static ArrayList<Customer> customerList = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Company company = new Company();

        restaurantList.add(new AuthenticRestaurant("Shah", "Mumbai"));
        restaurantList.add(new Restaurant("Ravi's", "Kerela"));
        restaurantList.add(new AuthenticRestaurant("The Chinese", "Jharkhand"));
        restaurantList.add(new FastFoodRestaurant("Wang's", "Sikkim"));
        restaurantList.add(new Restaurant("Paradise", "Odissa"));

        customerList.add(new EliteCustomer("Ram", "Delhi"));
        customerList.add(new EliteCustomer("Sam", "Assam"));
        customerList.add(new SpecialCustomer("Tim", "Haryana"));
        customerList.add(new Customer("Kim", "Punjab"));
        customerList.add(new Customer("Jim", "Bihar"));

        ParentMenu parentMenu = new ParentMenu();
        parentMenu.add("Enter as Restaurant Owner");
        parentMenu.add("Enter as Customer");
        parentMenu.add("Check User Details");
        parentMenu.add("Company Account Details");
        parentMenu.add("Exit");

        User restaurantUser = new RestaurantUser();
        User customerUser = new CustomerUser();

        while (true) {
            parentMenu.display();
            int input = scanner.nextInt();

            // Restaurant Owner Menu
            if(input == 1) {
                restaurantUser.display();

                int rChosen = scanner.nextInt();
                Restaurant r = restaurantList.get(rChosen-1);

                while (true) {
                    System.out.println("Welcome " + r.getName());
                    displayRestaurantOptions();

                    int rOptionChosen = scanner.nextInt();

                    // Add items to selected restaurant
                    if (rOptionChosen == 1) {

                        System.out.println("Enter food item details");

                        System.out.println("Food name:");
                        String foodName = scanner.next();

                        System.out.println("Item price:");
                        int itemPrice = scanner.nextInt();

                        System.out.println("Item quantity");
                        int itemQuantity = scanner.nextInt();

                        System.out.println("Item category:");
                        String itemCategory = scanner.next();

                        System.out.println("Offer:");
                        int itemDiscount = scanner.nextInt();

                        Food food = new Food(foodName, itemPrice, itemQuantity, itemDiscount, itemCategory);
                        r.addFood(food);
                    }

                    // Edit items to selected restaurant
                    else if (rOptionChosen == 2) {
                        r.displayFoodItems();

                        int itemChosen = scanner.nextInt();
                        Food item = r.getFoodItemById(itemChosen);

                        System.out.println("Choose an attribute to edit:");
                        displayRestaurantAttributeProperties();

                        while (true) {
                            int attributeChosen = scanner.nextInt();

                            // Change name
                            if (attributeChosen == 1) {
                                System.out.println("Enter new name - ");
                                String newName = scanner.next();
                                item.setName(newName);
                            }

                            // Change price
                            else if (attributeChosen == 2) {
                                System.out.println("Enter new price - ");
                                int newPrice = scanner.nextInt();
                                item.setPrice(newPrice);
                            }

                            // Change quantity
                            else if (attributeChosen == 3) {
                                System.out.println("Enter new quantity - ");
                                int newQuantity = scanner.nextInt();
                                item.setQuantity(newQuantity);
                            }

                            // Change category
                            else if (attributeChosen == 4) {
                                System.out.println("Enter new Category - ");
                                String newCategory = scanner.next();
                                item.setCategory(newCategory);
                            }

                            // Change offer
                            else if (attributeChosen == 5) {
                                System.out.println("Enter new offer - ");
                                int newOffer = scanner.nextInt();
                                item.setOffer(newOffer);
                            }

                            else {
                                System.out.println("Invalid Choice");
                            }

                            if (attributeChosen > 0 && attributeChosen < 6) {
                                item.displayDetails();
                                break;
                            }
                        }
                    }

                    // Print rewards
                    else if (rOptionChosen == 3) {
                        System.out.println("Reward Points: " + r.getRewardPoints());
                    }

                    // Print discount on bill value
                    else if (rOptionChosen == 4) {
                        System.out.print("Enter offer on bill value - ");
                        r.setOverallDiscount(scanner.nextInt());
                    }

                    // Exit
                    else if (rOptionChosen == 5) { break; }
                    else { System.out.println("Invalid Option Chosen"); }
                }
            }

            // Customer Menu
            else if(input == 2) {
                customerUser.display();

                int cChosen = scanner.nextInt();
                Customer c = customerList.get(cChosen-1);

                while (true) {
                    System.out.println("Welcome " + c.getName());
                    displayCustomerOptions();

                    int optionChosen = scanner.nextInt();

                    // Select Restaurants
                    if(optionChosen == 1) {
                        restaurantUser.display();

                        int rChosen = scanner.nextInt();
                        Restaurant r = restaurantList.get(rChosen-1);
                        c.setRestaurant(r);

                        r.displayFoodItems();
                        int itemChosen = scanner.nextInt();
                        Food food = r.getFoodItemById(itemChosen);

                        System.out.println("Enter item quantity - ");
                        int itemQuantity = scanner.nextInt();
                        c.add(food, itemQuantity);
                    }

                    // Checkout Cart
                    else if(optionChosen == 2) {
                        while (true) {
                            c.displayItemsInCart();                 // + calculate call internally
                            System.out.println("    1) Proceed to checkout");
                            int p = scanner.nextInt();
                            if(p == 1) {
                                System.out.println("Items successfully bought for INR " + c.getCartValue());
                                c.deductBalance(c.getCartValue());
                                company.addMoney(c.getCartValue()/100);
                                company.addDeliveryMoney(c.getDeliveryCharge());
                                c.emptyCart();
                                break;
                            }
                            else {
                                System.out.println("Invalid choice");
                            }
                        }
                    }

                    // Rewards Won
                    else if(optionChosen == 3) {
                        System.out.println("Total rewards - " + c.getTotalRewardPointsEarned());
                    }

                    // Print recent orders
                    else if(optionChosen == 4) {
                        c.printRecentOrders();
                    }

                    else if(optionChosen == 5) {
                        break;
                    }

                    else {
                        System.out.println("Invalid option");
                    }

                }
            }

            // Display customer or restaurant details
            else if(input == 3) {
                System.out.println("    1) Customer List");
                System.out.println("    2) Restaurant List");
                int choice = scanner.nextInt();
                if(choice == 1) {
                    customerUser.display();

                    int cChosen = scanner.nextInt();
                    Customer c = customerList.get(cChosen-1);

                    System.out.println(c.getName()
                            + (c.getCategory().equals("") ? "" :" (" + c.getCategory() + "), ")
                            + c.getAddress() + ", " + c.getBalance() + "/-");
                }
                else if(choice == 2) {
                    restaurantUser.display();

                    int rChosen = scanner.nextInt();
                    Restaurant r = restaurantList.get(rChosen-1);

                    System.out.println(r.getName() + ", " + r.getAddress() + ", No. of order = "
                            + r.getNumberOfOrders());
                }
                else {
                    System.out.println("Invalid choice");
                }
            }

            // Display company earnings
            else if(input == 4) {
                System.out.println("Charges collected from restaurants = INR "
                        + company.getEarnings() + "/-");
                System.out.println("Delivery charges collected from customers = INR "
                        + company.getDeliveryCharges() + "/-");
            }

            else if(input == 5) {
                break;
            }

            else {
                System.out.println("Invalid Choice");
            }
        }
    }

    private static void displayRestaurantAttributeProperties() {
        System.out.println("    1) Name" );
        System.out.println("    2) Price");
        System.out.println("    3) Quantity");
        System.out.println("    4) Category" );
        System.out.println("    5) Offer");
    }

    private static void displayRestaurantOptions() {
        System.out.println("    1) Add item" );
        System.out.println("    2) Edit item" );
        System.out.println("    3) Print Rewards" );
        System.out.println("    4) Discount on bill value" );
        System.out.println("    5) Exit" );
    }

    private static void displayCustomerOptions() {
        System.out.println("Customer Menu");
        System.out.println("    1) Select Restaurant");
        System.out.println("    2) Checkout cart");
        System.out.println("    3) Rewards won");
        System.out.println("    4) Print recent orders");
        System.out.println("    5) Exit");
    }
}
