package Linked_List.class_problems;

import java.util.*;

/**
 * Problem 1: Bank Account Withdrawal System
 * 
 * Design a system for bank accounts with withdrawal functionality.
 * Savings: must maintain a minimum balance of 1000 after any withdrawal.
 * Current: can have an overdraft up to 5000 (balance can go down to -5000).
 */
public class BankAccountWithdrawalSystem {

    public static abstract class Account {
        protected String id;
        protected double balance;

        public Account(String id, double initialBalance) {
            this.id = id;
            this.balance = initialBalance;
        }

        public String getId() {
            return id;
        }

        public double getBalance() {
            return balance;
        }

        public abstract boolean withdraw(double amount);
    }

    public static class SavingsAccount extends Account {
        public static final double MIN_BALANCE = 1000.0;

        public SavingsAccount(String id, double initialBalance) {
            super(id, initialBalance);
        }

        @Override
        public boolean withdraw(double amount) {
            if (balance - amount < MIN_BALANCE) {
                System.out.println(id + " rejected: minimum balance " + formatAmount(MIN_BALANCE));
                return false;
            }
            balance -= amount;
            System.out.println(id + " balance " + formatAmount(balance));
            return true;
        }
    }

    public static class CurrentAccount extends Account {
        public static final double OVERDRAFT_LIMIT = 5000.0;

        public CurrentAccount(String id, double initialBalance) {
            super(id, initialBalance);
        }

        @Override
        public boolean withdraw(double amount) {
            if (balance - amount < -OVERDRAFT_LIMIT) {
                System.out.println(id + " rejected: overdraft limit " + formatAmount(OVERDRAFT_LIMIT));
                return false;
            }
            balance -= amount;
            System.out.println(id + " balance " + formatAmount(balance));
            return true;
        }
    }

    private static String formatAmount(double value) {
        if (value == (long) value) {
            return String.valueOf((long) value);
        }
        return String.valueOf(value);
    }

    public static void processOperations(List<String> lines) {
        Map<String, Account> accounts = new LinkedHashMap<>();

        for (String line : lines) {
            line = line.trim();
            if (line.isEmpty()) continue;
            String[] parts = line.split("\\s+");
            String command = parts[0];

            if (command.equalsIgnoreCase("Savings")) {
                String id = parts[1];
                double initialBalance = Double.parseDouble(parts[2]);
                accounts.put(id, new SavingsAccount(id, initialBalance));
            } else if (command.equalsIgnoreCase("Current")) {
                String id = parts[1];
                double initialBalance = Double.parseDouble(parts[2]);
                accounts.put(id, new CurrentAccount(id, initialBalance));
            } else if (command.equalsIgnoreCase("WITHDRAW")) {
                String id = parts[1];
                double amount = Double.parseDouble(parts[2]);
                Account account = accounts.get(id);
                if (account == null) {
                    System.out.println("Account not found");
                } else {
                    account.withdraw(amount);
                }
            }
        }
    }

    public static void main(String[] args) {
        List<String> sampleInput = Arrays.asList(
            "Savings S1 5000",
            "WITHDRAW S1 4500",
            "Current C1 2000",
            "WITHDRAW C1 6000",
            "WITHDRAW S1 3000",
            "WITHDRAW C1 2000"
        );

        System.out.println("--- Execution Output ---");
        processOperations(sampleInput);
    }
}
