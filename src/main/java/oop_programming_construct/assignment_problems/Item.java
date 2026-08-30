package oop_programming_construct.assignment_problems;

public class Item {
    private String itemName;
    private int stock;

    public Item(String itemName, int stock) {
        // Resolving field/parameter naming clash
        this.itemName = itemName;
        this.stock = stock;
    }

    public void restock(int stock) {
        // Resolving field/parameter naming clash
        this.stock += stock;
    }

    public void display() {
        System.out.println(this.itemName + " | Final Stock: " + this.stock);
    }

    public static void main(String[] args) {
        Item[] items = {
            new Item("Samosa", 15),
            new Item("Tea Powder", 40),
            new Item("Bread", 8),
            new Item("Biscuit Packs", 25)
        };

        for (Item item : items) {
            item.restock(20);
            item.display();
        }
    }
}