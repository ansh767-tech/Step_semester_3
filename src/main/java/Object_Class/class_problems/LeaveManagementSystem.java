package Object_Class.class_problems;

enum LeaveStatus {
    PENDING,
    APPROVED,
    REJECTED
}

abstract class Employee {
    private String id;
    private String name;

    public Employee(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getName() { return name; }
    public abstract boolean canApplyLeave(int days);
}

class FullTimeEmployee extends Employee {
    public FullTimeEmployee(String id, String name) {
        super(id, name);
    }

    @Override
    public boolean canApplyLeave(int days) {
        return days <= 30;
    }
}

class PartTimeEmployee extends Employee {
    public PartTimeEmployee(String id, String name) {
        super(id, name);
    }

    @Override
    public boolean canApplyLeave(int days) {
        return days <= 10;
    }
}

class LeaveRequest {
    private Employee employee;
    private String dates;
    private LeaveStatus status;

    public LeaveRequest(Employee employee, String dates) {
        this.employee = employee;
        this.dates = dates;
        this.status = LeaveStatus.PENDING;
    }

    public LeaveStatus getStatus() { return status; }
    public Employee getEmployee() { return employee; }
    public String getDates() { return dates; }

    public boolean approve() {
        if (this.status == LeaveStatus.PENDING) {
            this.status = LeaveStatus.APPROVED;
            return true;
        }
        System.out.println("Cannot change leave request status from " + this.status + " to APPROVED.");
        return false;
    }

    public boolean reject() {
        if (this.status == LeaveStatus.PENDING) {
            this.status = LeaveStatus.REJECTED;
            return true;
        }
        System.out.println("Cannot change leave request status from " + this.status + " to REJECTED.");
        return false;
    }

    public void setStatus(LeaveStatus newStatus) {
        if (this.status != LeaveStatus.PENDING && newStatus == LeaveStatus.PENDING) {
            System.out.println("Cannot change leave request status from " + this.status + " to Pending.");
            return;
        }
        this.status = newStatus;
    }
}

public class LeaveManagementSystem {
    public static void main(String[] args) {
        Employee john = new FullTimeEmployee("E1", "John");
        Employee jane = new PartTimeEmployee("E2", "Jane");

        LeaveRequest johnRequest = new LeaveRequest(john, "Jan 1-5");
        System.out.println("Leave request submitted for " + john.getName() + " (" + johnRequest.getDates() + "). Status: " + johnRequest.getStatus());

        johnRequest.approve();
        System.out.println(john.getName() + "'s leave request (" + johnRequest.getDates() + ") approved. Status: " + johnRequest.getStatus());

        LeaveRequest janeRequest = new LeaveRequest(jane, "Feb 10-11");
        System.out.println("Leave request submitted for " + jane.getName() + " (" + janeRequest.getDates() + "). Status: " + janeRequest.getStatus());

        janeRequest.reject();
        System.out.println(jane.getName() + "'s leave request (" + janeRequest.getDates() + ") rejected. Status: " + janeRequest.getStatus());

        // Invalid State Transition Attempt
        johnRequest.setStatus(LeaveStatus.PENDING);
    }
}