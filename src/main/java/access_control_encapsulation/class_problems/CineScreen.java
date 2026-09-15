package access_control_encapsulation;

public class CineScreen {

    private final int seatsTotal;
    private int seatsAvailable;

    public CineScreen(int seatsTotal) {
        if (seatsTotal <= 0) {
            System.out.println("construction rejected");
            this.seatsTotal = 0;
            this.seatsAvailable = 0;
        } else {
            this.seatsTotal = seatsTotal;
            this.seatsAvailable = seatsTotal;
        }
    }

    public void bookSeat() {
        if (this.seatsTotal <= 0) return;
        if (this.seatsAvailable > 0) {
            this.seatsAvailable--;
        }
    }

    public void cancelBooking() {
        if (this.seatsTotal <= 0) return;
        if (this.seatsAvailable < this.seatsTotal) {
            this.seatsAvailable++;
        }
    }

    public int getSeatsAvailable() {
        return this.seatsAvailable;
    }
}