package Object_Class.class_problems;

import java.util.ArrayList;
import java.util.List;

abstract class Room {
    private String roomNumber;
    private boolean isAvailable;
    private double basePrice;

    public Room(String roomNumber, double basePrice) {
        this.roomNumber = roomNumber;
        this.basePrice = basePrice;
        this.isAvailable = true;
    }

    public String getRoomNumber() { return roomNumber; }
    public boolean isAvailable() { return isAvailable; }
    public void setAvailable(boolean available) { isAvailable = available; }
    public double getBasePrice() { return basePrice; }

    public abstract double calculatePrice(int nights);
}

class StandardRoom extends Room {
    public StandardRoom(String roomNumber, double basePrice) {
        super(roomNumber, basePrice);
    }

    @Override
    public double calculatePrice(int nights) {
        return getBasePrice() * nights;
    }
}

class SuiteRoom extends Room {
    private double luxuryTax = 50.0;

    public SuiteRoom(String roomNumber, double basePrice) {
        super(roomNumber, basePrice);
    }

    @Override
    public double calculatePrice(int nights) {
        return (getBasePrice() + luxuryTax) * nights;
    }
}

class Guest {
    private String guestId;
    private String name;

    public Guest(String guestId, String name) {
        this.guestId = guestId;
        this.name = name;
    }

    public String getName() { return name; }
}

class Booking {
    private Room room;
    private Guest guest;
    private int nights;
    private double totalPrice;

    public Booking(Room room, Guest guest, int nights) {
        this.room = room;
        this.guest = guest;
        this.nights = nights;
        this.totalPrice = room.calculatePrice(nights);
    }

    public Room getRoom() { return room; }
    public Guest getGuest() { return guest; }
    public double getTotalPrice() { return totalPrice; }
}

public class HotelBookingSystem {
    private List<Booking> bookings = new ArrayList<>();

    public boolean createBooking(Room room, Guest guest, int nights) {
        if (!room.isAvailable()) {
            System.out.println("Room " + room.getRoomNumber() + " is currently unavailable.");
            return false;
        }
        room.setAvailable(false);
        Booking booking = new Booking(room, guest, nights);
        bookings.add(booking);
        System.out.println("Booking confirmed for " + guest.getName() + " in Room " + room.getRoomNumber() + ". Total Price: $" + booking.getTotalPrice());
        return true;
    }

    public static void main(String[] args) {
        HotelBookingSystem system = new HotelBookingSystem();

        Room room101 = new StandardRoom("101", 100.0);
        Guest guest1 = new Guest("G1", "Alice");
        Guest guest2 = new Guest("G2", "Bob");

        system.createBooking(room101, guest1, 2);
        system.createBooking(room101, guest2, 3);
    }
}