package Assignment2;

public class Wallet {
    private double balance;

    public Wallet(double balance) {
        this.balance = balance;
    }

    public double getBalance() {
        return balance;
    }

    public void deductBalance(double amount) {
        if(balance > amount) {
            balance -= amount;
            System.out.println("New balance = " + balance);
        }
        else {
            System.out.println("Insufficient balance");
        }
    }
}
