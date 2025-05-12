package chocoshop;

import java.sql.Date;

public class Sale {
    private int id;
    private int chocolate_id;
    private int quantity;
    private double total;
    private String sale_date;

    public Sale(int id, int chocolate_id, int quantity, double total, String sale_date) {
        this.id = id;
        this.chocolate_id = chocolate_id;
        this.quantity = quantity;
        this.total = total;
        this.sale_date = sale_date;
    }

    public int getId(){
        return id;
    }
    public int getChocolate_Id(){
        return chocolate_id;
    }
    public int getQuantity(){
        return quantity;
    }
    public double getTotal(){
        return total;
    }
    public String getSale_Date(){
        return sale_date;
    }

    public String toString() {
        return " " + id + chocolate_id + quantity + total + sale_date;
    }
}
