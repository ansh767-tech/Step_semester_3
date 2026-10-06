package access_control_encapsulation;

public class BookInventory {

    private final int copiesTotal;
    private int copiesAvailable;

    public BookInventory(int copiesTotal) {
        if (copiesTotal <= 0) {
            this.copiesTotal = 0;
            this.copiesAvailable = 0;
        } else {
            this.copiesTotal = copiesTotal;
            this.copiesAvailable = copiesTotal;
        }
    }

    public void checkOut() {
        if (this.copiesAvailable > 0) {
            this.copiesAvailable--;
        }
    }

    public void checkIn() {
        if (this.copiesAvailable < this.copiesTotal) {
            this.copiesAvailable++;
        }
    }

    public int getCopiesAvailable() {
        return this.copiesAvailable;
    }
}