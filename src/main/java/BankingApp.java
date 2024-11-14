import java.util.HashMap;
import java.util.Scanner;

public class BankingApp {
    private HashMap<Integer, Account> accounts;
    private Account currentUser;
    private Scanner scanner;
    

    public BankingApp() {
        accounts = new HashMap<>();
        accounts.put(412435, new Account(412435, 7452, "Chris Sandoval", 32000));
        accounts.put(264863, new Account(264863, 1349, "Marc Yim", 1000));
        scanner = new Scanner(System.in);
    }

    public void start() {
        System.out.println("Welcome to the Banking App!");
        if (login()) {
            showMainMenu();
        }
        System.out.println("Thank you for using the Banking App. Goodbye!");
    }

    private boolean login() {
        System.out.print("Enter User ID: ");
        int userId = scanner.nextInt();
        System.out.print("Enter PIN: ");
        int pin = scanner.nextInt();

        Account account = accounts.get(userId);
        if (account != null && account.getPin() == pin) {
            currentUser = account;
            System.out.println("Login successful! Welcome, " + currentUser.getName());
            return true;
        } else {
            System.out.println("Invalid User ID or PIN. Exiting program.");
            return false;
        }
    }

    private void showMainMenu() {
        boolean exit = false;
        while (!exit) {
            System.out.println("\nMain Menu:");
            System.out.println("1. Check Balance");
            System.out.println("2. Cash In");
            System.out.println("3. Transfer Money");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    checkBalance();
                    break;
                case 2:
                    cashIn();
                    break;
                case 3:
                    transferMoney();
                    break;
                case 4:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }

    private void checkBalance() {
        System.out.println("Your current balance is: " + currentUser.getBalance());
    }

    private void cashIn() {
        System.out.print("Enter amount to deposit: ");
        double amount = scanner.nextDouble();
        if (amount > 0) {
            currentUser.deposit(amount);
            System.out.println("Amount deposited successfully. New balance: " + currentUser.getBalance());
        } else {
            System.out.println("Invalid amount. Please enter a positive value.");
        }
    }

    private void transferMoney() {
        System.out.print("Enter recipient User ID: ");
        int recipientId = scanner.nextInt();
        Account recipient = accounts.get(recipientId);

        if (recipient != null) {
            System.out.print("Enter amount to transfer: ");
            double amount = scanner.nextDouble();
            if (currentUser.transferTo(recipient, amount)) {
                System.out.println("Transfer successful! New balance: " + currentUser.getBalance());
            } else {
                System.out.println("Transfer failed. Check your balance or amount.");
            }
        } else {
            System.out.println("Recipient not found.");
        }
    }

    public static void main(String[] args) {
        BankingApp app = new BankingApp();
        app.start();
    }
}
