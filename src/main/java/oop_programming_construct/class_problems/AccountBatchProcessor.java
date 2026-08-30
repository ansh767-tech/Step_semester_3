package oop_programming_construct.class_problems;

class FeeAccount {
    public void pay() {
        System.out.println("Paid in one go (day-scholar account)");
    }
}

class HostelFeeAccount extends FeeAccount {
    @Override
    public void pay() {
        System.out.println("Paid in two installments (hostel account)");
    }
}

public class AccountBatchProcessor {
    public static void processPayment(FeeAccount account, double amount) {
        account.pay();
    }

    public static void main(String[] args) {
        FeeAccount[] accounts = {
            new HostelFeeAccount(),
            new HostelFeeAccount(),
            new FeeAccount(),
            new FeeAccount()
        };

        int hostelCount = 0;
        int dayScholarCount = 0;

        for (FeeAccount acc : accounts) {
            processPayment(acc, 60000);
            if (acc instanceof HostelFeeAccount) {
                hostelCount++;
            } else if (acc instanceof FeeAccount) {
                dayScholarCount++;
            }
        }

        System.out.println("Hostel accounts processed: " + hostelCount + " | Day-scholar accounts processed: " + dayScholarCount);
    }
}