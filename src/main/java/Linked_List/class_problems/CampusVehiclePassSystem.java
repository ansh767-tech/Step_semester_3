package Linked_List.class_problems;

import java.util.*;

/**
 * Problem 2: Campus Vehicle Pass System
 * 
 * Manage campus vehicle passes and charging bay bookings.
 * Pass fees:
 * - Bike: 300
 * - Car: 1000
 * - E-Bike: 300
 * - E-Car: 1000
 * Only electric vehicles (E-Bike, E-Car) implement Chargeable and can book charging bays.
 */
public class CampusVehiclePassSystem {

    public interface Chargeable {
        boolean canCharge();
    }

    public static abstract class Vehicle {
        protected String passNumber;
        protected String ownerName;
        protected String vehicleType;

        public Vehicle(String vehicleType, String passNumber, String ownerName) {
            this.vehicleType = vehicleType;
            this.passNumber = passNumber;
            this.ownerName = ownerName;
        }

        public String getPassNumber() {
            return passNumber;
        }

        public String getOwnerName() {
            return ownerName;
        }

        public String getVehicleType() {
            return vehicleType;
        }

        public abstract int getPassFee();
    }

    public static class Bike extends Vehicle {
        public Bike(String vehicleType, String passNumber, String ownerName) {
            super(vehicleType, passNumber, ownerName);
        }

        @Override
        public int getPassFee() {
            return 300;
        }
    }

    public static class Car extends Vehicle {
        public Car(String vehicleType, String passNumber, String ownerName) {
            super(vehicleType, passNumber, ownerName);
        }

        @Override
        public int getPassFee() {
            return 1000;
        }
    }

    public static class EBike extends Vehicle implements Chargeable {
        public EBike(String vehicleType, String passNumber, String ownerName) {
            super(vehicleType, passNumber, ownerName);
        }

        @Override
        public int getPassFee() {
            return 300;
        }

        @Override
        public boolean canCharge() {
            return true;
        }
    }

    public static class ECar extends Vehicle implements Chargeable {
        public ECar(String vehicleType, String passNumber, String ownerName) {
            super(vehicleType, passNumber, ownerName);
        }

        @Override
        public int getPassFee() {
            return 1000;
        }

        @Override
        public boolean canCharge() {
            return true;
        }
    }

    public static Vehicle createVehicle(String type, String passNumber, String ownerName) {
        String cleanType = type.replaceAll("[-_]", "");
        if (cleanType.equalsIgnoreCase("Bike")) {
            return new Bike(type, passNumber, ownerName);
        } else if (cleanType.equalsIgnoreCase("Car")) {
            return new Car(type, passNumber, ownerName);
        } else if (cleanType.equalsIgnoreCase("EBike")) {
            return new EBike(type, passNumber, ownerName);
        } else if (cleanType.equalsIgnoreCase("ECar")) {
            return new ECar(type, passNumber, ownerName);
        }
        throw new IllegalArgumentException("Unknown vehicle type: " + type);
    }

    public static void processOperations(List<String> lines) {
        Map<String, Vehicle> registry = new LinkedHashMap<>();

        for (String line : lines) {
            line = line.trim();
            if (line.isEmpty()) continue;
            String[] parts = line.split("\\s+");
            String command = parts[0];

            if (command.equalsIgnoreCase("PASS")) {
                String type = parts[1];
                String passNumber = parts[2];
                String ownerName = parts[3];

                Vehicle v = createVehicle(type, passNumber, ownerName);
                registry.put(passNumber, v);
                System.out.println(v.getPassNumber() + " (" + v.getVehicleType() + ") pass fee " + v.getPassFee());
            } else if (command.equalsIgnoreCase("CHARGE")) {
                String passNumber = parts[1];
                Vehicle v = registry.get(passNumber);
                if (v == null) {
                    System.out.println(passNumber + " rejected: vehicle not registered");
                } else if (v instanceof Chargeable) {
                    System.out.println(passNumber + " charging bay allotted");
                } else {
                    System.out.println(passNumber + " rejected: charging unsupported");
                }
            }
        }
    }

    public static void main(String[] args) {
        List<String> sampleInput = Arrays.asList(
            "PASS Bike KA01 Asha",
            "PASS ECar KA02 Ravi",
            "CHARGE KA02",
            "CHARGE KA01"
        );

        System.out.println("--- Execution Output ---");
        processOperations(sampleInput);
    }
}
