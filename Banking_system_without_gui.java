import java.util.*;
//author - Samruddha Belsare
//Github - https://github.com/samruddhabelsare
class Account {
    private static int nextAccountNo = 1001;
    private int accountNo;
    private String holderName;
    private String type;
    private double balance;
    private boolean active;

    public Account(String holderName, double initialDeposit, String type) {
        this.accountNo = nextAccountNo++;
        this.holderName = holderName;
        this.type = type;
        this.balance = initialDeposit;
        this.active = true;
    }

    public int getAccountNo() { return accountNo; }
    public String getHolderName() { return holderName; }
    public String getType() { return type; }
    public double getBalance() { return balance; }
    public boolean isActive() { return active; }

    public void deposit(double amount) {
        if (amount > 0) this.balance += amount;
    }

    public boolean withdraw(double amount) {
        if (amount > 0 && amount <= this.balance) {
            this.balance -= amount;
            return true;
        }
        return false;
    }
}

class AccountManager {
    private List<Account> accounts = new ArrayList<>();

    public void addAccount(Account acc) { accounts.add(acc); }

    public Account findAccount(int accNo) {
        for (Account acc : accounts) {
            if (acc.getAccountNo() == accNo && acc.isActive()) {
                return acc;
            }
        }
        return null;
    }

    public void showAllAccounts() {
        System.out.println("Acc No\tName\tType\tBalance\tStatus");
        for (Account acc : accounts) {
            System.out.printf("%d\t%s\t%s\t%.2f\t%s%n",
                    acc.getAccountNo(), acc.getHolderName(),
                    acc.getType(), acc.getBalance(),
                    acc.isActive() ? "Active" : "Inactive");
        }
    }
}

public class Banking_system_without_gui {
    private static AccountManager manager = new AccountManager();
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        boolean run = true;
        while (run) {
            System.out.println("\n--- Banking System Menu ---");
            System.out.println("1. Create Account");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Show All Accounts");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");
            int choice = sc.nextInt();
            switch (choice) {
                case 1: createAccount(); break;
                case 2: deposit(); break;
                case 3: withdraw(); break;
                case 4: manager.showAllAccounts(); break;
                default: run = false;
            }
        }
    }
//author - Samruddha Belsare
//Github - https://github.com/samruddhabelsare

    private static void createAccount() {
        System.out.print("Enter Name: ");
        String name = sc.next();
        System.out.print("Account Type (Savings/Checking): ");
        String type = sc.next();
        System.out.print("Initial Deposit: ");
        double deposit = sc.nextDouble();
        Account acc = new Account(name, deposit, type);
        manager.addAccount(acc);
        System.out.println("Account created. Acc No: " + acc.getAccountNo());
    }

    private static void deposit() {
        System.out.print("Enter Account Number: ");
        int accNo = sc.nextInt();
        Account acc = manager.findAccount(accNo);
        if (acc != null) {
            System.out.print("Enter Amount: ");
            double amt = sc.nextDouble();
            acc.deposit(amt);
            System.out.println("Deposited. Current Balance: " + acc.getBalance());
        } else {
            System.out.println("Account not found.");
        }
    }

    private static void withdraw() {
        System.out.print("Enter Account Number: ");
        int accNo = sc.nextInt();
        Account acc = manager.findAccount(accNo);
        if (acc != null) {
            System.out.print("Enter Amount: ");
            double amt = sc.nextDouble();
            if (acc.withdraw(amt)) {
                System.out.println("Withdrawn. Current Balance: " + acc.getBalance());
            } else {
                System.out.println("Insufficient balance or invalid amount.");
            }
        } else {
            System.out.println("Account not found.");
        }
    }
}
//author - Samruddha Belsare
//Github - https://github.com/samruddhabelsare
