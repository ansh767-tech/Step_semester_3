package Linked_List.class_problems;

import java.util.*;

/**
 * Problem 5: Payroll Register System
 * 
 * Develop a payroll system for different staff types: FullTime, PartTime, and Intern.
 * Stores payslips in an array/list, prints individual payslips, overall total, and top earner.
 */
public class PayrollRegisterSystem {

    public static abstract class StaffMember {
        protected String name;
        protected String type;

        public StaffMember(String name, String type) {
            this.name = name;
            this.type = type;
        }

        public String getName() {
            return name;
        }

        public String getType() {
            return type;
        }

        public abstract long calculatePay();

        public Payslip generatePayslip() {
            return new Payslip(name, type, calculatePay());
        }
    }

    public static class FullTimeStaff extends StaffMember {
        private long salary;

        public FullTimeStaff(String name, long salary) {
            super(name, "FullTime");
            this.salary = salary;
        }

        @Override
        public long calculatePay() {
            return salary;
        }
    }

    public static class PartTimeStaff extends StaffMember {
        private long hours;
        private long rate;

        public PartTimeStaff(String name, long hours, long rate) {
            super(name, "PartTime");
            this.hours = hours;
            this.rate = rate;
        }

        @Override
        public long calculatePay() {
            return hours * rate;
        }
    }

    public static class InternStaff extends StaffMember {
        private long stipend;

        public InternStaff(String name, long stipend) {
            super(name, "Intern");
            this.stipend = stipend;
        }

        @Override
        public long calculatePay() {
            return stipend;
        }
    }

    public static class Payslip {
        private String name;
        private String type;
        private long pay;

        public Payslip(String name, String type, long pay) {
            this.name = name;
            this.type = type;
            this.pay = pay;
        }

        public String getName() {
            return name;
        }

        public String getType() {
            return type;
        }

        public long getPay() {
            return pay;
        }

        @Override
        public String toString() {
            return "Payslip[name=" + name + ", type=" + type + ", pay=" + pay + "]";
        }
    }

    public static void processPayroll(List<String> lines) {
        List<StaffMember> staffList = new ArrayList<>();

        for (String line : lines) {
            line = line.trim();
            if (line.isEmpty()) continue;
            String[] parts = line.split("\\s+");
            String type = parts[0];
            String name = parts[1];

            if (type.equalsIgnoreCase("FullTime")) {
                long salary = Long.parseLong(parts[2]);
                staffList.add(new FullTimeStaff(name, salary));
            } else if (type.equalsIgnoreCase("PartTime")) {
                long hours = Long.parseLong(parts[2]);
                long rate = Long.parseLong(parts[3]);
                staffList.add(new PartTimeStaff(name, hours, rate));
            } else if (type.equalsIgnoreCase("Intern")) {
                long stipend = Long.parseLong(parts[2]);
                staffList.add(new InternStaff(name, stipend));
            }
        }

        Payslip[] payslips = new Payslip[staffList.size()];
        long totalPay = 0;
        Payslip topEarner = null;

        for (int i = 0; i < staffList.size(); i++) {
            payslips[i] = staffList.get(i).generatePayslip();
            System.out.println(payslips[i]);
            totalPay += payslips[i].getPay();

            if (topEarner == null || payslips[i].getPay() > topEarner.getPay()) {
                topEarner = payslips[i];
            }
        }

        System.out.println("Total " + totalPay);
        if (topEarner != null) {
            System.out.println("top earner " + topEarner.getName());
        }
    }

    public static void main(String[] args) {
        List<String> sampleInput = Arrays.asList(
            "FullTime Asha 50000",
            "PartTime Ravi 80 300",
            "Intern Neha 15000"
        );

        System.out.println("--- Execution Output ---");
        processPayroll(sampleInput);
    }
}
