import java.util.Scanner;
// Class to represent the user's bank account
class BankAccount {
    private double balance;

    // Constructor to initialize the account balance
    public BankAccount(double initialBalance) {
        this.balance = initialBalance;
    }

    // Method to deposit money
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("✅ Deposit successful! Current balance: $" + balance);
        } else {
            System.out.println("⚠️ Please enter a valid amount to deposit.");
        }
    }

    // Method to withdraw money
    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("✅ Withdrawal successful! Current balance: $" + balance);
        } else if (amount > balance) {
            System.out.println("⚠️ Insufficient balance.");
        } else {
            System.out.println("⚠️ Please enter a valid amount to withdraw.");
        }
    }

    // Method to check balance
    public void checkBalance() {
        System.out.println("💵 Your current balance is: $" + balance);
    }
}

// Class to represent the ATM machine
class ATM {
    private BankAccount account;
    private Scanner scanner;

    // Constructor to initialize ATM with a bank account
    public ATM(BankAccount account) {
        this.account = account;
        this.scanner = new Scanner(System.in);
    }

    // Method to display the user interface
    public void displayMenu() {
        System.out.println("\nWelcome to the ATM! 🏦");
        System.out.println("1. Withdraw Money");
        System.out.println("2. Deposit Money");
        System.out.println("3. Check Balance");
        System.out.println("4. Exit");
    }

    // Method to handle user choices
    public void handleUserChoice() {
        int choice;
        do {
            displayMenu();
            System.out.print("✏️ Please select an option: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("💳 Enter amount to withdraw: ");
                    double withdrawAmount = scanner.nextDouble();
                    account.withdraw(withdrawAmount);
                    break;
                case 2:
                    System.out.print("💳 Enter amount to deposit: ");
                    double depositAmount = scanner.nextDouble();
                    account.deposit(depositAmount);
                    break;
                case 3:
                    account.checkBalance();
                    break;
                case 4:
                    System.out.println("👋 Thank you for using the ATM. Goodbye!");
                    break;
                default:
                    System.out.println("⚠️ Invalid choice. Please try again.");
            }
        } while (choice != 4);
    }

    // Method to close resources
    public void closeATM() {
        scanner.close();
    }
}

// Main class to run the program
public class Task3 {
    public static void main(String[] args) {
        // Initialize a bank account with a starting balance
        BankAccount userAccount = new BankAccount(1000.0);

        // Create an ATM instance and pass the bank account
        ATM atm = new ATM(userAccount);

        // Start the ATM operations
        atm.handleUserChoice();

        // Close ATM resources
        atm.closeATM();
    }
}

