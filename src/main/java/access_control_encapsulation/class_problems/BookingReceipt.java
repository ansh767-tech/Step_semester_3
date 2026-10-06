package access_control_encapsulation;

import java.util.Arrays;

public class BookingReceipt {

    private final String bookingId;
    private final String[] seatNumbers;

    public BookingReceipt(String bookingId, String[] seatNumbers) {
        this.bookingId = bookingId;
        if (seatNumbers != null) {
            this.seatNumbers = Arrays.copyOf(seatNumbers, seatNumbers.length);
        } else {
            this.seatNumbers = new String[0];
        }
    }

    public String getBookingId() {
        return bookingId;
    }

    public String[] getSeatNumbers() {
        return Arrays.copyOf(this.seatNumbers, this.seatNumbers.length);
    }

    public BookingReceipt withUpdatedSeat(int index, String newSeat) {
        if (index < 0 || index >= this.seatNumbers.length) {
            return this;
        }
        String[] updatedSeats = getSeatNumbers();
        updatedSeats[index] = newSeat;
        return new BookingReceipt(this.bookingId, updatedSeats);
    }

    public static String processNightlySettlement(BookingReceipt[] receipts) {
        int individualCount = 0;
        int groupCount = 0;
        int nullCount = 0;

        if (receipts != null) {
            for (BookingReceipt receipt : receipts) {
                if (receipt == null) {
                    nullCount++;
                } else if (receipt instanceof GroupBookingReceipt) {
                    groupCount++;
                } else if (receipt instanceof BookingReceipt) {
                    individualCount++;
                }
            }
        }

        return (individualCount + groupCount) + " processed | " + nullCount + " null skipped | " + groupCount + " group | " + individualCount + " individual";
    }
}