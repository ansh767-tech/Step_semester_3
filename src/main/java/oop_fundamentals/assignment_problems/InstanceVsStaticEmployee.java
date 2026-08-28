package oop_fundamentals.assignment_problems;

class Trainee {
    String empName;
    double salary;

    static String companyName = "Bright Horizon Technologies";
    static int employeeCount = 0;

    public Trainee(String empName, double salary) {
        this.empName = empName;
        this.salary = salary;
        employeeCount++;
    }

    public static void printCompanyInfo() {
        System.out.println(companyName);
        System.out.println("Employees on record: " + employeeCount);
    }
}

public class InstanceVsStaticEmployee {
    public static void main(String[] args) {
        new Trainee("Divya", 65000.0);
        new Trainee("Arjun", 0.0);
        new Trainee("Rohan", 45000.0);

        Trainee.printCompanyInfo();
    }
}