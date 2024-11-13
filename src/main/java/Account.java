public class Account {
    private int userId;
    private int pin;
    private String name;
    private double balance;

    public Account(int userId, int pin, String name, double balance) {
        this.userId = userId;
        this.pin = pin;
        this.name = name;
        this.balance = balance;
    }

    public int getUserId() { return userId; }
    public int getPin() { return pin; }
    public String getName() { return name; }
    public double getBalance() { return balance; }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
        }
    }

    public boolean withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            return true;
        }
        return false;
    }

    public boolean transferTo(Account other, double amount) {
        if (withdraw(amount)) {
            other.deposit(amount);
            return true;
        }
        return false;
    }
}
