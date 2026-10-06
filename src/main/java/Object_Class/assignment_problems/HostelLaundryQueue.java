package Object_Class.assignment_problems;

import java.util.ArrayList;
import java.util.List;

abstract class WashType {
    private String name;
    private int durationMinutes;
    private double charge;

    public WashType(String name, int durationMinutes, double charge) {
        this.name = name;
        this.durationMinutes = durationMinutes;
        this.charge = charge;
    }

    public String getName() { return name; }
    public int getDurationMinutes() { return durationMinutes; }
    public double getCharge() { return charge; }
}

class QuickWash extends WashType {
    public QuickWash() {
        super("Quick", 30, 20.00);
    }
}

class NormalWash extends WashType {
    public NormalWash() {
        super("Normal", 45, 30.00);
    }
}

class HeavyWash extends WashType {
    public HeavyWash() {
        super("Heavy", 60, 45.00);
    }
}

class Student {
    private String name;

    public Student(String name) {
        this.name = name;
    }

    public String getName() { return name; }
}

class WashingMachine {
    private String machineId;
    private boolean isBusy;

    public WashingMachine(String machineId) {
        this.machineId = machineId;
        this.isBusy = false;
    }

    public String getMachineId() { return machineId; }
    public boolean isBusy() { return isBusy; }

    public void setBusy(boolean busy) {
        this.isBusy = busy;
    }
}

class WashCycle {
    private Student student;
    private WashingMachine machine;
    private WashType washType;

    public WashCycle(Student student, WashingMachine machine, WashType washType) {
        this.student = student;
        this.machine = machine;
        this.washType = washType;
    }

    public Student getStudent() { return student; }
    public WashingMachine getMachine() { return machine; }
    public WashType getWashType() { return washType; }
}

public class HostelLaundryQueue {
    private List<WashCycle> activeCycles = new ArrayList<>();

    public boolean startWash(Student student, WashingMachine machine, WashType washType) {
        if (machine.isBusy()) {
            System.out.println("Machine " + machine.getMachineId() + " is currently busy.");
            return false;
        }

        machine.setBusy(true);
        WashCycle cycle = new WashCycle(student, machine, washType);
        activeCycles.add(cycle);

        System.out.printf("%s wash started on %s for %s (%d min). Charge: %.2f.\n",
                washType.getName(), machine.getMachineId(), student.getName(),
                washType.getDurationMinutes(), washType.getCharge());
        return true;
    }

    public void completeCycle(WashingMachine machine) {
        WashCycle cycleToComplete = null;
        for (WashCycle cycle : activeCycles) {
            if (cycle.getMachine().getMachineId().equals(machine.getMachineId())) {
                cycleToComplete = cycle;
                break;
            }
        }

        if (cycleToComplete != null) {
            activeCycles.remove(cycleToComplete);
            machine.setBusy(false);
            System.out.println(machine.getMachineId() + " cycle completed. " + machine.getMachineId() + " is now free.");
        } else {
            System.out.println("No active cycle found on " + machine.getMachineId());
        }
    }

    public static void main(String[] args) {
        HostelLaundryQueue queue = new HostelLaundryQueue();

        WashingMachine m1 = new WashingMachine("M1");
        WashingMachine m2 = new WashingMachine("M2");

        Student asha = new Student("Asha");
        Student ravi = new Student("Ravi");
        Student neha = new Student("Neha");

        queue.startWash(asha, m1, new QuickWash());
        queue.startWash(ravi, m1, new HeavyWash());
        queue.startWash(ravi, m2, new HeavyWash());

        queue.completeCycle(m1);
        queue.startWash(neha, m1, new NormalWash());
    }
}