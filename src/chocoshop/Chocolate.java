package chocoshop;

public class Chocolate {
    private int id;
    private String name;
    private String description;
    private int quantity;
    private double price;

    public Chocolate(int id, String name, String description, int quantity, double price) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.quantity = quantity;
        this.price = price;
    }

    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getDescription() {
        return description;
    }
    public int getQuantity() {
        return quantity;
    }
    public double getPrice() {
        return price;
    }

    public String toString() {
        return "Chocolate [id=" + id + ", name=" + name + ", description=" + description + ", quantity=";
    }
}
