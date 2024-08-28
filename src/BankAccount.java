import java.util.Scanner;

public class BankAccount {

    // Property: balance
    private double balance;

    // Default constructor
    public BankAccount() {
        this.balance = 0.0;
    }

    // Constructor with a parameter to set the initial balance
    public BankAccount(double balance) {
        this.balance = balance;
    }

    // Method to deposit money into the account
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Deposited: " + amount);
        } else {
            System.out.println("Deposit amount must be positive.");
        }
    }

    // Method to withdraw money from the account
    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("Withdrew: " + amount);
        } else if (amount > balance) {
            System.out.println("Insufficient balance.");
        } else {
            System.out.println("Withdrawal amount must be positive.");
        }
    }

    // Method to print the current balance
    public void printBalance() {
        System.out.printf("Current Balance: $%.2f%n", balance);
    }

    // Method to transfer balance from one account to another
    public void transfer(BankAccount toAccount, double amount) {
        if (amount > 0 && amount <= balance) {
            this.withdraw(amount);
            toAccount.deposit(amount);
            System.out.println("Transferred: " + amount + " to account.");
        } else if (amount > balance) {
            System.out.println("Insufficient balance for transfer.");
        } else {
            System.out.println("Transfer amount must be positive.");
        }
    }

    // Main method to make the program interactive
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Create two accounts for testing
        BankAccount account1 = new BankAccount(1000.00);
        BankAccount account2 = new BankAccount();

        // Interact with the user
        while (true) {
            System.out.println("\nChoose an option:");
            System.out.println("1. Deposit");
            System.out.println("2. Withdraw");
            System.out.println("3. Print Balance");
            System.out.println("4. Transfer to another account");
            System.out.println("5. Exit");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter amount to deposit: ");
                    double depositAmount = scanner.nextDouble();
                    account1.deposit(depositAmount);
                    break;
                case 2:
                    System.out.print("Enter amount to withdraw: ");
                    double withdrawAmount = scanner.nextDouble();
                    account1.withdraw(withdrawAmount);
                    break;
                case 3:
                    account1.printBalance();
                    break;
                case 4:
                    System.out.print("Enter amount to transfer: ");
                    double transferAmount = scanner.nextDouble();
                    account1.transfer(account2, transferAmount);
                    System.out.println("Account 1 Balance after transfer:");
                    account1.printBalance();
                    System.out.println("Account 2 Balance after transfer:");
                    account2.printBalance();
                    break;
                case 5:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
}
