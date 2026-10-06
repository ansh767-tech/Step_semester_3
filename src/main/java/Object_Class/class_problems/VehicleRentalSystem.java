package Object_Class.class_problems;

import java.util.ArrayList;
import java.util.List;

abstract class Vehicle {
    private String registrationNumber;
    private String model;
    private boolean isAvailable;

    public Vehicle(String registrationNumber, String model) {
        this.registrationNumber = registrationNumber;
        this.model = model;
        this.isAvailable = true;
    }

    public String getRegistrationNumber() { return registrationNumber; }
    public String getModel() { return model; }
    public boolean isAvailable() { return isAvailable; }
    public void setAvailable(boolean available) { isAvailable = available; }

    public abstract double calculateRentalCharge(int days);
}

class Sedan extends Vehicle {
    private double dailyRate = 50.0;

    public Sedan(String registrationNumber, String model) {
        super(registrationNumber, model);
    }

    @Override
    public double calculateRentalCharge(int days) {
        return days * dailyRate;
    }
}

class SUV extends Vehicle {
    private double dailyRate = 80.0;

    public SUV(String registrationNumber, String model) {
        super(registrationNumber, model);
    }

    @Override
    public double calculateRentalCharge(int days) {
        return days * dailyRate;
    }
}

class Customer {
    private String customerId;
    private String name;

    public Customer(String customerId, String name) {
        this.customerId = customerId;
        this.name = name;
    }

    public String getName() { return name; }
}

class Rental {
    private Vehicle vehicle;
    private Customer customer;
    private int days;
    private double totalCharge;

    public Rental(Vehicle vehicle, Customer customer, int days) {
        this.vehicle = vehicle;
        this.customer = customer;
        this.days = days;
        this.totalCharge = vehicle.calculateRentalCharge(days);
    }

    public Vehicle getVehicle() { return vehicle; }
    public Customer getCustomer() { return customer; }
    public double getTotalCharge() { return totalCharge; }
}

public class VehicleRentalSystem {
    private List<Rental> activeRentals = new ArrayList<>();

    public boolean rentVehicle(Vehicle vehicle, Customer customer, int days) {
        if (!vehicle.isAvailable()) {
            System.out.println(vehicle.getModel() + " is currently unavailable for " + customer.getName() + ".");
            return false;
        }
        vehicle.setAvailable(false);
        Rental rental = new Rental(vehicle, customer, days);
        activeRentals.add(rental);
        System.out.println(vehicle.getModel() + " rented successfully by " + customer.getName() + ". Rental charge: $" + rental.getTotalCharge());
        return true;
    }

    public void returnVehicle(Vehicle vehicle, Customer customer) {
        Rental rentalToRemove = null;
        for (Rental r : activeRentals) {
            if (r.getVehicle().equals(vehicle) && r.getCustomer().equals(customer)) {
                rentalToRemove = r;
                break;
            }
        }
        if (rentalToRemove != null) {
            activeRentals.remove(rentalToRemove);
            vehicle.setAvailable(true);
            System.out.println(vehicle.getModel() + " returned by " + customer.getName() + ".");
        } else {
            System.out.println("No active rental record found for " + vehicle.getModel() + " by " + customer.getName());
        }
    }

    public static void main(String[] args) {
        VehicleRentalSystem system = new VehicleRentalSystem();

        Vehicle sedanA = new Sedan("S001", "Sedan A");
        Vehicle suvB = new SUV("SUV001", "SUV B");

        Customer c1 = new Customer("C1", "Customer 1");
        Customer c2 = new Customer("C2", "Customer 2");
        Customer c3 = new Customer("C3", "Customer 3");

        // Workflow execution
        system.rentVehicle(sedanA, c1, 3);
        system.rentVehicle(sedanA, c2, 2);
        system.returnVehicle(sedanA, c1);
        system.rentVehicle(suvB, c3, 5);
    }
}