import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class BankAccount {

    // Property: balance
    private double balance;
    private String accountID;

    // Default constructor
    public BankAccount(String accountID) {
        this.accountID = accountID;
        this.balance = 0.0;
    }

    // Constructor with a parameter to set the initial balance
    public BankAccount(String accountID, double balance) {
        this.accountID = accountID;
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

    // Get account ID
    public String getAccountID() {
        return accountID;
    }

    // Main method to make the program interactive
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        HashMap<String, BankAccount> accounts = new HashMap<>();

        // Create two accounts for testing
        accounts.put("001", new BankAccount("001", 1000.00));
        accounts.put("002", new BankAccount("002", 500.00));

        // Interact with the user
        while (true) {
            System.out.println("\nChoose an option:");
            System.out.println("1. Create Account");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Print Balance");
            System.out.println("5. Transfer to another account");
            System.out.println("6. Generate Report");
            System.out.println("7. Exit");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter new account ID: ");
                    String newAccountID = scanner.next();
                    if (accounts.containsKey(newAccountID)) {
                        System.out.println("Account ID already exists.");
                    } else {
                        accounts.put(newAccountID, new BankAccount(newAccountID));
                        System.out.println("Account created successfully!");
                    }
                    break;
                case 2:
                    System.out.print("Enter account ID to deposit: ");
                    String depositID = scanner.next();
                    BankAccount depositAccount = accounts.get(depositID);
                    if (depositAccount != null) {
                        System.out.print("Enter amount to deposit: ");
                        double depositAmount = scanner.nextDouble();
                        depositAccount.deposit(depositAmount);
                    } else {
                        System.out.println("Account not found.");
                    }
                    break;
                case 3:
                    System.out.print("Enter account ID to withdraw from: ");
                    String withdrawID = scanner.next();
                    BankAccount withdrawAccount = accounts.get(withdrawID);
                    if (withdrawAccount != null) {
                        System.out.print("Enter amount to withdraw: ");
                        double withdrawAmount = scanner.nextDouble();
                        withdrawAccount.withdraw(withdrawAmount);
                    } else {
                        System.out.println("Account not found.");
                    }
                    break;
                case 4:
                    System.out.print("Enter account ID to check balance: ");
                    String balanceID = scanner.next();
                    BankAccount balanceAccount = accounts.get(balanceID);
                    if (balanceAccount != null) {
                        balanceAccount.printBalance();
                    } else {
                        System.out.println("Account not found.");
                    }
                    break;
                case 5:
                    System.out.print("Enter your account ID: ");
                    String fromID = scanner.next();
                    BankAccount fromAccount = accounts.get(fromID);
                    if (fromAccount != null) {
                        System.out.print("Enter recipient account ID: ");
                        String toID = scanner.next();
                        BankAccount toAccount = accounts.get(toID);
                        if (toAccount != null) {
                            System.out.print("Enter amount to transfer: ");
                            double transferAmount = scanner.nextDouble();
                            fromAccount.transfer(toAccount, transferAmount);
                        } else {
                            System.out.println("Recipient account not found.");
                        }
                    } else {
                        System.out.println("Your account not found.");
                    }
                    break;
                case 6:
                    generateReport(accounts);
                    break;
                case 7:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    // Method to generate a report of all accounts
    public static void generateReport(HashMap<String, BankAccount> accounts) {
        try (FileWriter writer = new FileWriter("account_report.csv")) {
            writer.write("Account ID,Balance\n");
            for (Map.Entry<String, BankAccount> entry : accounts.entrySet()) {
                writer.write(entry.getKey() + ",$" + String.format("%.2f", entry.getValue().balance) + "\n");
            }
            System.out.println("Report generated successfully: account_report.csv");
        } catch (IOException e) {
            System.out.println("An error occurred while generating the report.");
            e.printStackTrace();
        }
    }
}
