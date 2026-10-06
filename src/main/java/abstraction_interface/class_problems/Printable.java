package abstraction_interface.class_problems;

public interface Printable {
    String printLabel();

    static void printAll(Printable[] items) {
        if (items == null) return;
        for (Printable item : items) {
            if (item != null) {
                System.out.println(item.printLabel());
            }
        }
    }
}

class PackageBox implements Printable {
    private final String trackingId;

    public PackageBox(String trackingId) {
        if (trackingId == null || trackingId.trim().isEmpty()) {
            throw new IllegalArgumentException("Tracking ID cannot be blank.");
        }
        this.trackingId = trackingId;
    }

    @Override
    public String printLabel() {
        return "Package label: " + trackingId;
    }
}

class Invoice implements Printable {
    private final String invoiceNumber;

    public Invoice(String invoiceNumber) {
        if (invoiceNumber == null || invoiceNumber.trim().isEmpty()) {
            throw new IllegalArgumentException("Invoice number cannot be blank.");
        }
        this.invoiceNumber = invoiceNumber;
    }

    @Override
    public String printLabel() {
        return "Invoice label: " + invoiceNumber;
    }
}