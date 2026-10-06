package Object_Class.assignment_problems;

import java.util.ArrayList;
import java.util.List;

abstract class Seat {
    private String seatNumber;

    public Seat(String seatNumber) {
        this.seatNumber = seatNumber;
    }

    public String getSeatNumber() { return seatNumber; }
    public abstract double getPrice();
}

class RegularSeat extends Seat {
    public RegularSeat(String seatNumber) {
        super(seatNumber);
    }

    @Override
    public double getPrice() { return 150.0; }
}

class PremiumSeat extends Seat {
    public PremiumSeat(String seatNumber) {
        super(seatNumber);
    }

    @Override
    public double getPrice() { return 250.0; }
}

class ReclinerSeat extends Seat {
    public ReclinerSeat(String seatNumber) {
        super(seatNumber);
    }

    @Override
    public double getPrice() { return 400.0; }
}

class Customer {
    private String name;

    public Customer(String name) {
        this.name = name;
    }

    public String getName() { return name; }
}

class Show {
    private String showTime;
    private List<String> bookedSeatNumbers = new ArrayList<>();

    public Show(String showTime) {
        this.showTime = showTime;
    }

    public boolean isSeatAvailable(String seatNumber) {
        return !bookedSeatNumbers.contains(seatNumber);
    }

    public void reserveSeat(String seatNumber) {
        bookedSeatNumbers.add(seatNumber);
    }

    public void releaseSeat(String seatNumber) {
        bookedSeatNumbers.remove(seatNumber);
    }
}

class Booking {
    private Customer customer;
    private Show show;
    private List<Seat> bookedSeats;
    private double totalAmount;

    public Booking(Customer customer, Show show, List<Seat> seats) {
        this.customer = customer;
        this.show = show;
        this.bookedSeats = seats;
        this.totalAmount = calculateTotal();
    }

    public Customer getCustomer() { return customer; }
    public List<Seat> getBookedSeats() { return bookedSeats; }
    public double getTotalAmount() { return totalAmount; }

    private double calculateTotal() {
        double total = 0.0;
        for (Seat s : bookedSeats) {
            total += s.getPrice();
        }
        return total;
    }

    public String getSeatNumbersString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < bookedSeats.size(); i++) {
            sb.append(bookedSeats.get(i).getSeatNumber());
            if (i < bookedSeats.size() - 1) sb.append(", ");
        }
        return sb.toString();
    }
}

public class CampusPremiereTicketCounter {
    private List<Booking> activeBookings = new ArrayList<>();

    public boolean createBooking(Customer customer, Show show, List<Seat> requestedSeats) {
        if (requestedSeats.size() > 6) {
            System.out.println("Booking failed: Maximum 6 seats allowed per booking.");
            return false;
        }

        for (Seat seat : requestedSeats) {
            if (!show.isSeatAvailable(seat.getSeatNumber())) {
                System.out.println("Seat " + seat.getSeatNumber() + " is already booked for this show.");
                return false;
            }
        }

        for (Seat seat : requestedSeats) {
            show.reserveSeat(seat.getSeatNumber());
        }

        Booking booking = new Booking(customer, show, requestedSeats);
        activeBookings.add(booking);

        System.out.printf("Booking confirmed for %s: %s. Total: %.2f.\n",
                customer.getName(), booking.getSeatNumbersString(), booking.getTotalAmount());
        return true;
    }

    public void cancelBooking(Customer customer, Show show) {
        Booking bookingToCancel = null;
        for (Booking b : activeBookings) {
            if (b.getCustomer().getName().equals(customer.getName())) {
                bookingToCancel = b;
                break;
            }
        }

        if (bookingToCancel != null) {
            for (Seat seat : bookingToCancel.getBookedSeats()) {
                show.releaseSeat(seat.getSeatNumber());
            }
            activeBookings.remove(bookingToCancel);
            System.out.printf("%s's booking cancelled. Seats %s released.\n",
                    customer.getName(), bookingToCancel.getSeatNumbersString());
        }
    }

    public static void main(String[] args) {
        CampusPremiereTicketCounter counter = new CampusPremiereTicketCounter();
        Show show7PM = new Show("7 PM");

        Customer asha = new Customer("Asha");
        Customer ravi = new Customer("Ravi");
        Customer neha = new Customer("Neha");

        List<Seat> ashaSeats = List.of(
                new RegularSeat("A1"),
                new RegularSeat("A2"),
                new PremiumSeat("F5")
        );
        counter.createBooking(asha, show7PM, ashaSeats);

        List<Seat> raviAttemptSeats = List.of(new RegularSeat("A2"));
        counter.createBooking(ravi, show7PM, raviAttemptSeats);

        List<Seat> raviSeats = List.of(new ReclinerSeat("R1"));
        counter.createBooking(ravi, show7PM, raviSeats);

        counter.cancelBooking(asha, show7PM);

        List<Seat> nehaSeats = List.of(new RegularSeat("A2"));
        counter.createBooking(neha, show7PM, nehaSeats);
    }
}