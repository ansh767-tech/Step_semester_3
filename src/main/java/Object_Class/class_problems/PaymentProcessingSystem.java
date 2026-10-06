package Object_Class.class_problems;

import java.util.ArrayList;
import java.util.List;

interface PaymentMethod {
    boolean processPayment(double amount);
}

class CreditCardPayment implements PaymentMethod {
    private String cardNumber;

    public CreditCardPayment(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    @Override
    public boolean processPayment(double amount) {
        System.out.println("Payment initiated via Credit Card for amount $" + amount + ".");
        return true; // Simulating successful payment
    }
}

class PayPalPayment implements PaymentMethod {
    private String email;

    public PayPalPayment(String email) {
        this.email = email;
    }

    @Override
    public boolean processPayment(double amount) {
        System.out.println("Payment initiated via PayPal for amount $" + amount + ".");
        return false; // Simulating failed payment
    }
}

class Product {
    private String name;
    private double price;

    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public double getPrice() { return price; }
}

enum OrderStatus {
    PENDING,
    PAID
}

class Order {
    private String orderId;
    private List<Product> products = new ArrayList<>();
    private OrderStatus status = OrderStatus.PENDING;

    public Order(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderId() { return orderId; }
    public OrderStatus getStatus() { return status; }

    public void addProduct(Product product) {
        products.add(product);
    }

    public double calculateTotal() {
        double total = 0.0;
        for (Product p : products) {
            total += p.getPrice();
        }
        return total;
    }

    public boolean processOrderPayment(PaymentMethod paymentMethod) {
        if (products.isEmpty()) {
            System.out.println("Cannot process payment for an empty order.");
            return false;
        }

        double amount = calculateTotal();
        boolean success = paymentMethod.processPayment(amount);

        if (success) {
            this.status = OrderStatus.PAID;
            System.out.println("Payment for Order " + orderId + " successful. Order status: " + status);
            return true;
        } else {
            System.out.println("Payment for Order " + orderId + " failed. Order status: " + status);
            return false;
        }
    }
}

public class PaymentProcessingSystem {
    public static void main(String[] args) {
        // Customer X Scenario
        System.out.println("--- Customer X Order ---");
        Order orderX = new Order("X");
        orderX.addProduct(new Product("Product A", 50.0));
        orderX.addProduct(new Product("Product A", 50.0));
        orderX.addProduct(new Product("Product B", 30.0));

        PaymentMethod creditCard = new CreditCardPayment("1234-5678-9012");
        orderX.processOrderPayment(creditCard);

        // Customer Y Scenario (Empty Order)
        System.out.println("\n--- Customer Y Order ---");
        Order orderY = new Order("Y");
        PaymentMethod creditCardY = new CreditCardPayment("9876-5432-1098");
        orderY.processOrderPayment(creditCardY);

        // Customer Z Scenario (Failed Payment)
        System.out.println("\n--- Customer Z Order ---");
        Order orderZ = new Order("Z");
        orderZ.addProduct(new Product("Product C", 100.0));

        PaymentMethod payPal = new PayPalPayment("user@example.com");
        orderZ.processOrderPayment(payPal);
    }
}