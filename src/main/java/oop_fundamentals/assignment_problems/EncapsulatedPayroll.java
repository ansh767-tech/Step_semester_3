package oop_fundamentals.assignment_problems;

class PayrollAccount {
    private double basicSalary;
    private double bonus;

    public PayrollAccount(double openingSalary) {
        if (openingSalary < 0) {
            System.out.println("Warning: Basic salary cannot be negative. Setting basic salary to 0.0");
            this.basicSalary = 0.0;
        } else {
            this.basicSalary = openingSalary;
        }
        this.bonus = 0.0;
    }

    public void creditBonus(double amount) {
        if (amount <= 0) {
            System.out.println("Bonus credit rejected: Amount must be positive.");
            return;
        }
        this.bonus += amount;
        System.out.println("Bonus credited: Rs " + amount);
    }

    public void deductTax(double percent) {
        if (percent < 0 || percent > 100) {
            System.out.println("Tax deduction rejected: Percentage must be between 0 and 100.");
            return;
        }
        this.basicSalary -= (this.basicSalary * percent / 100.0);
        System.out.println("Tax deducted: " + percent + "%");
    }

    public double getNetSalary() {
        return this.basicSalary + this.bonus;
    }
}

public class EncapsulatedPayroll {
    public static void main(String[] args) {
        PayrollAccount account = new PayrollAccount(50000.0);
        account.creditBonus(5000.0);
        account.deductTax(10);
        System.out.println("Net salary: Rs " + account.getNetSalary());
    }
}