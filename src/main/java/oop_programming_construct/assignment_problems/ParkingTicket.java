package oop_programming_construct.assignment_problems;

public class ParkingTicket {
    private String vehicleNo;
    private double ratePerMinute;

    public ParkingTicket(String vehicleNo, double ratePerMinute) {
        this.vehicleNo = vehicleNo;
        this.ratePerMinute = ratePerMinute;
    }

    public final double calculateFine(int overstayMinutes) {
        return overstayMinutes * this.ratePerMinute;
    }

    public final void printReceipt(int overstayMinutes) {
        if (overstayMinutes <= 0) {
            System.out.println(this.vehicleNo + " - No fine, within allotted time");
        } else {
            double fine = calculateFine(overstayMinutes);
            System.out.println(this.vehicleNo + " - Fine: Rs " + fine);
        }
    }

    public static void main(String[] args) {
        String[] vehicleNos = {"TN09AB1234", "TN22CD5678", "TN09EF9012", "TN10GH3456"};
        double[] ratePerMinute = {2, 2, 3, 2};
        int[] overstayMinutes = {15, 0, -5, 8};

        for (int i = 0; i < vehicleNos.length; i++) {
            ParkingTicket ticket = new ParkingTicket(vehicleNos[i], ratePerMinute[i]);
            ticket.printReceipt(overstayMinutes[i]);
        }
    }
}